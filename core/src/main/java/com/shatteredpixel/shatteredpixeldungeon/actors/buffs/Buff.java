/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2025 Evan Debenham
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.watabou.noosa.Image;
import com.watabou.utils.Bundle;
import com.watabou.utils.GameMath;
import com.watabou.utils.Reflection;

import java.util.HashSet;

public class Buff extends Actor {
	
	public Char target;

	//whether this buff was already extended by the mnemonic prayer spell
	public boolean mnemonicExtended = false;

	{
		actPriority = BUFF_PRIO; //low priority, towards the end of a turn
	}

	//determines how the buff is announced when it is shown.
	public enum buffType {POSITIVE, NEGATIVE, NEUTRAL}
	public buffType type = buffType.NEUTRAL;
	
	//whether or not the buff announces its name
	public boolean announced = false;

	//whether a buff should persist through revive effects or similar (e.g. transmogrify)
	public boolean revivePersists = false;
	
	protected HashSet<Class> resistances = new HashSet<>();
	
	public HashSet<Class> resistances() {
		return new HashSet<>(resistances);
	}
	
	protected HashSet<Class> immunities = new HashSet<>();
	
	public HashSet<Class> immunities() {
		return new HashSet<>(immunities);
	}
	
	public boolean attachTo( Char target ) {

		if (target.isImmune( getClass() )) {
			return false;
		}
		
		this.target = target;

		if (target.add( this )){
			if (target.sprite != null) fx( true );
			return true;
		} else {
			this.target = null;
			return false;
		}
	}
	
	public void detach() {
		if (target.remove( this ) && target.sprite != null) fx( false );
	}
	
	@Override
	public boolean act() {
		diactivate();
		return true;
	}
	
	public int icon() {
		return BuffIndicator.NONE;
	}

	//some buffs may want to tint the base texture color of their icon
	public void tintIcon( Image icon ){
		//do nothing by default
	}

	//percent (0-1) to fade out out the buff icon, usually if buff is expiring
	public float iconFadePercent(){
		return 0;
	}

	//text to display on large buff icons in the desktop UI
	public String iconTextDisplay(){
		return "";
	}

	//visual effect usually attached to the sprite of the character the buff is attacked to
	public void fx(boolean on) {
		//do nothing by default
	}

	public String heroMessage(){
		String msg = Messages.get(this, "heromsg");
		if (msg.isEmpty()) {
			return null;
		} else {
			return msg;
		}
	}

	public String name() {
		return Messages.get(this, "name");
	}

	public String desc(){
		return Messages.get(this, "desc");
	}

	//to handle the common case of showing how many turns are remaining in a buff description.
	protected String dispTurns(float input){
		return Messages.decimalFormat("#.##", input);
	}

	public float visualcooldown(){
		return Math.max(cooldown(), 0.01f);
	}
	
	private static final String MNEMONIC_EXTENDED    = "mnemonic_extended";

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		if (mnemonicExtended) bundle.put(MNEMONIC_EXTENDED, mnemonicExtended);
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		if (bundle.contains(MNEMONIC_EXTENDED)) {
			mnemonicExtended = bundle.getBoolean(MNEMONIC_EXTENDED);
		}
	}

	public static class Polished {
		public static<T extends FlavourBuff> T affectAligned(Char target, Class<T> buffClass, float duration) {
			boolean isNew = target.buff(buffClass) == null;

			T buff = affect(target, buffClass);
			if(isNew) buff.Polished_alignTurnWheel(target.cooldown());
			buff.spend( duration * target.resist(buffClass) );

			return buff;
		}

		//postpones an already active buff, or creates & attaches a new buff and delays that.
		public static<T extends FlavourBuff> T prolongAligned( Char target, Class<T> buffClass, float duration ) {

			float partial = target.cooldown() % TICK;
			if(partial < 0) partial++;
			duration += partial;

			T buff = prolong(target, buffClass, duration);
			return buff;
		}
		
		public static float customIconFade(Buff buff) {
			float fade = -1f;
			
			if(buff instanceof FlavourBuff) {
				boolean aboutToFade = 	buff.cooldown() < 1 || (buff.cooldown() == 1 && buff.actPriority > HERO_PRIO);
				if(aboutToFade) {
					aboutToFade = 		buff.cooldown() < buff.target.cooldown() ||
										(buff.cooldown() == buff.target.cooldown() && buff.actPriority > MOB_PRIO);
				}
				
				if (aboutToFade) {
					fade = 1f;
				} else {
					fade = GameMath.gate(0, (10f - buff.visualcooldown()) / 10f, 1);
				}
			}
			else {
				try {
					fade = GameMath.gate(0, (10f - Integer.parseInt(buff.iconTextDisplay())) / 10f, 1);
				} catch (NumberFormatException e) {}
			}
			
			//to avoid scaling problems
			if(fade > .99f) fade = 1.05f;
			return fade;
		}
	}

	//creates a fresh instance of the buff and attaches that, this allows duplication.
	public static<T extends Buff> T append( Char target, Class<T> buffClass ) {
		T buff = Reflection.newInstance(buffClass);
		buff.attachTo( target );
		return buff;
	}

	public static<T extends FlavourBuff> T append( Char target, Class<T> buffClass, float duration ) {
		T buff = append( target, buffClass );

		buff.spend( duration * target.resist(buffClass) );
		return buff;
	}

	//same as append, but prevents duplication.
	public static<T extends Buff> T affect( Char target, Class<T> buffClass ) {
		T buff = target.buff( buffClass );
		if (buff != null) {
			return buff;
		} else {
			return append( target, buffClass );
		}
	}
	
	public static<T extends FlavourBuff> T affect( Char target, Class<T> buffClass, float duration ) {
		T buff = affect( target, buffClass );
		buff.spend( duration * target.resist(buffClass) );
		return buff;
	}

	//postpones an already active buff, or creates & attaches a new buff and delays that.
	public static<T extends FlavourBuff> T prolong( Char target, Class<T> buffClass, float duration ) {
		T buff = affect( target, buffClass );
		buff.postpone( duration * target.resist(buffClass) );
		return buff;
	}

	public static<T extends CounterBuff> T count( Char target, Class<T> buffclass, float count ) {
		T buff = affect( target, buffclass );
		buff.countUp( count );
		return buff;
	}
	
	public static void detach( Char target, Class<? extends Buff> cl ) {
		for ( Buff b : target.buffs( cl )){
			b.detach();
		}
	}
}
