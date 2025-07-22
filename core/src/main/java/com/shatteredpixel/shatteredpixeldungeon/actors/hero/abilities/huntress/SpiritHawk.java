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

package com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.huntress;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Blindness;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.BlobImmunity;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Cripple;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.ArmorAbility;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.Stasis;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.DirectableAlly;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.ShaftParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.ClassArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfTeleportation;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.MobSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.TextureFilm;
import com.watabou.utils.Bundle;
import com.watabou.utils.GameMath;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class SpiritHawk extends ArmorAbility {
	
	{
		baseChargeUse = 35f;
	}
	
	public boolean useTargeting(){
		return false;
	}

	@Override
	public float chargeUse(Hero hero) {
		if (Hawk() == null) {
			return super.chargeUse(hero);
		} else {
			return 0;
		}
	}
	
	float chargeSummon(Hero hero) {
		return super.chargeUse(hero);
	}
	
	private static HawkAlly hawk = null;
	private static int hawkID = -1;
	
	public static void resetHawk() {
		hawk = null;
		hawkID = -1;
	}
	
	public static HawkAlly Hawk() {
		if(hawk != null) {
			if(!hawk.isAlive()) resetHawk();
			return hawk;
		}
		
		if(hawkID != -1) {
			Actor a = Actor.findById(hawkID);
			if (a instanceof HawkAlly){
				hawk = (HawkAlly) a;
				return hawk;
			} else {
				hawkID = -1;
			}
		}
		
		Char ally = Stasis.getStasisAlly();
		if (ally instanceof HawkAlly){
			hawk = (HawkAlly) ally;
			hawkID = ally.id();
			return hawk;
		}
		
		return null;
	}
	
	@Override
	protected void activate(ClassArmor armor, Hero hero, Integer target) {
		if (Hawk() != null){
			if(hawk.stasis()) {
				GLog.i( Messages.get(this, "spawned") );
			}
			else {
				hawk.command();
			}
		}
		else {
			DirectableAlly.SummonSelector.trySummon(new HawkAlly(), () -> {
				armor.charge -= chargeSummon(hero);
				Item.updateQuickslot();
				Invisibility.dispel();
				
				Buff.affect(Dungeon.hero, HawkTimer.class, HawkTimer.DURATION);
            });
		}
	}

	@Override
	public int icon() {
		return HeroIcon.SPIRIT_HAWK;
	}

	@Override
	public Talent[] talents() {
		return new Talent[]{Talent.EAGLE_EYE, Talent.GO_FOR_THE_EYES, Talent.SWIFT_SPIRIT, Talent.HEROIC_ENERGY};
	}

	public static class HawkAlly extends DirectableAlly {

		{
			spriteClass = HawkSprite.class;

			HP = HT = 10;
			defenseSkill = 60;

			flying = true;
			if (Dungeon.hero != null) {
				viewDistance = GameMath.gate(5, 5 + Dungeon.hero.pointsInTalent(Talent.EAGLE_EYE), 9);
				baseSpeed = 2f + Dungeon.hero.pointsInTalent(Talent.SWIFT_SPIRIT) / 2f;
			} else {
				viewDistance = 5;
				baseSpeed = 2f;
			}

			immunities.addAll(new BlobImmunity().immunities());
		}
		
		@Override
		protected void announce() {
			switch (command) {
				case DEFEND:
					if(!defendAnnounced) {
						GLog.i(Messages.get(this, "direct_defend"));
						defendAnnounced = true;
					}
					break;
				case ATTACK:
					if(!attackAnnounced) {
						GLog.i(Messages.get(this, "direct_attack"));
						attackAnnounced = true;
					}
					break;
				case FOLLOW:
					if(!followAnnounced) {
						GLog.i(Messages.get(this, "direct_follow"));
						followAnnounced = true;
					}
					break;
				case NONE: default:
					if(!darkAnnounced) {
						GLog.n(Messages.get(this, "too_dark"));
						darkAnnounced = true;
					}
					break;
			}
		}
		
		@Override
		protected void onAdd() {
			super.onAdd();
			
			SpiritHawk.hawk = this;
			SpiritHawk.hawkID = id();
		}
		
		@Override
		public void destroy() {
			super.destroy();
			SpiritHawk.resetHawk();
			
			HawkTimer timer = Dungeon.hero.buff(HawkTimer.class);
			if(timer != null) timer.detach();
		}

		@Override
		public int attackSkill(Char target) {
			return 60;
		}

		private int dodgesUsed = 0;

		@Override
		public int defenseSkill(Char enemy) {
			if (Dungeon.hero.hasTalent(Talent.SWIFT_SPIRIT) &&
					dodgesUsed < 2*Dungeon.hero.pointsInTalent(Talent.SWIFT_SPIRIT)) {
				dodgesUsed++;
				return Char.INFINITE_EVASION;
			}
			return super.defenseSkill(enemy);
		}

		@Override
		public int damageRoll() {
			return Random.NormalIntRange(5, 10);
		}

		@Override
		public int attackProc(Char enemy, int damage) {
			damage = super.attackProc( enemy, damage );
			switch (Dungeon.hero.pointsInTalent(Talent.GO_FOR_THE_EYES)){
				case 1:
					Buff.prolong( enemy, Blindness.class, 2);
					break;
				case 2:
					Buff.prolong( enemy, Blindness.class, 5);
					break;
				case 3:
					Buff.prolong( enemy, Blindness.class, 5);
					Buff.prolong( enemy, Cripple.class, 2);
					break;
				case 4:
					Buff.prolong( enemy, Blindness.class, 5);
					Buff.prolong( enemy, Cripple.class, 5);
					break;
				default:
					//do nothing
			}

			return damage;
		}

		@Override
		protected boolean act() {
			viewDistance = 5+Dungeon.hero.pointsInTalent(Talent.EAGLE_EYE);
			baseSpeed = 2f + Dungeon.hero.pointsInTalent(Talent.SWIFT_SPIRIT)/2f;
			return super.act();
		}

		@Override
		public void die(Object cause) {
			flying = false;
			super.die(cause);
		}

		@Override
		public String description() {
			String message = Messages.get(this, "desc");
			if (Actor.chars().contains(this)){
				if (dodgesUsed < 2*Dungeon.hero.pointsInTalent(Talent.SWIFT_SPIRIT)){
					message += "\n" + Messages.get(this, "desc_dodges", (2*Dungeon.hero.pointsInTalent(Talent.SWIFT_SPIRIT) - dodgesUsed));
				}
			}
			return message;
		}

		private static final String DODGES_USED     = "dodges_used";

		@Override
		public void storeInBundle(Bundle bundle) {
			super.storeInBundle(bundle);
			bundle.put(DODGES_USED, dodgesUsed);
		}

		@Override
		public void restoreFromBundle(Bundle bundle) {
			super.restoreFromBundle(bundle);
			dodgesUsed = bundle.getInt(DODGES_USED);
		}
	}
	
	public static class HawkTimer extends FlavourBuff {
		
		{
			type=buffType.POSITIVE;
			revivePersists = true;
		}
		
		public static final float DURATION = 100f;
		
		@Override
		public int icon() {
			return BuffIndicator.SPIRIT_HAWK;
		}
		
		@Override
		public float iconFadePercent() {
			return (DURATION - cooldown()) / DURATION;
		}
		
		@Override
		public void detach() {
			super.detach();
			
			if(SpiritHawk.Hawk() != null) {
				SpiritHawk.hawk.die(null);
				Dungeon.hero.interrupt();
			}
		}
		
	}

	public static class HawkSprite extends MobSprite {

		public HawkSprite() {
			super();

			texture( Assets.Sprites.SPIRIT_HAWK );

			TextureFilm frames = new TextureFilm( texture, 15, 15 );

			int c = 0;

			idle = new Animation( 6, true );
			idle.frames( frames, 0, 1 );

			run = new Animation( 8, true );
			run.frames( frames, 0, 1 );

			attack = new Animation( 12, false );
			attack.frames( frames, 2, 3, 0, 1 );

			die = new Animation( 12, false );
			die.frames( frames, 4, 5, 6 );

			play( idle );
		}

		@Override
		public int blood() {
			return 0xFF00FFFF;
		}

		@Override
		public void die() {
			super.die();
			emitter().start( ShaftParticle.FACTORY, 0.3f, 4 );
			emitter().start( Speck.factory( Speck.LIGHT ), 0.2f, 3 );
		}
	}
}
