/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2024 Evan Debenham
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

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.effects.FloatingText;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.effects.SpellSprite;
import com.shatteredpixel.shatteredpixeldungeon.items.BrokenSeal.WarriorShield;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.ActionIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.Image;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.GameMath;

public class UndyingRage extends FlavourBuff {

	{
		type = buffType.POSITIVE;
	}

	private enum State{
		RECOVERING, READY, RAGE
	}
	private State state = State.READY;

	private static final float LEVEL_RECOVER = 1f;
	private float levelRecovery;

	private static final String STATE = "state";
	private static final String LEVEL_RECOVERY = "level_recovery";

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(STATE, state);
		bundle.put(LEVEL_RECOVERY, levelRecovery);
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);

		state = bundle.getEnum(STATE, State.class);
		levelRecovery = bundle.getFloat(LEVEL_RECOVERY);
	}

	@Override
	public void detach() {
		//Buff.affect(target, );
		super.detach();
	}

	public boolean raging(){
		if (target.HP <= 0
			&& state == State.READY
			&& target.buff(WarriorShield.class) != null
			&& ((Hero)target).hasTalent(Talent.DEATHLESS_FURY)){

			startRaging();
		}

		return state == State.RAGE;
	}

	private void startRaging(){
		state = State.RAGE;
		//start duration

		Berserk berserk = Buff.affect(target, Berserk.class);
		BuffIndicator.refreshHero();

		//on stopberserk()
		{
			//turnRecovery = TURN_COOLDOWN_START;
		}
	}

	public void recover(float percent){
		if (state == State.RECOVERING){
			levelRecovery += percent;

			if(levelRecovery >= LEVEL_RECOVER) {
				levelRecovery = 0;
				state = State.READY;
				BuffIndicator.refreshHero();
			}
		}
	}

	@Override
	public int icon() {
		if(state != State.READY)
			return BuffIndicator.BERSERK;

		else return BuffIndicator.NONE;
	}
	
	@Override
	public void tintIcon(Image icon) {
		switch (state){
			case RECOVERING:
				icon.hardlight(0f, 0.0f, 1f);
				break;
			case RAGE:
				icon.hardlight(1f, 0f, 0f);
				break;

			case READY: default: break;
		}
	}
	
	@Override
	public float iconFadePercent() {
		if(state == State.RECOVERING)
			return 0f; //replace with % progress
		else if(state == State.RAGE)
			return .5f; //replace with % used

		else return 0f;
	}

	public String iconTextDisplay(){
		if(state == State.RECOVERING)
			return (int)(levelRecovery*100) + "%";

		else return "";
	}

	@Override
	public String name() {
		switch (state){
			case RECOVERING:
				return Messages.get(this, "recovering");
			case RAGE:
				return Messages.get(this, "rage");

			case READY: default: return "";
		}
	}

	@Override
	public String desc() {
		switch (state){
			case RECOVERING:
				return Messages.get(this, "recovering_desc", levelRecovery);
			case RAGE:
				return Messages.get(this, "rage_desc");

			case READY: default: return "";
		}
	}
}
