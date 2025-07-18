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

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Challenges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Blob;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Fire;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.items.bombs.HolyBomb;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Shocking;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.darts.HolyDart;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.BArray;
import com.watabou.noosa.Image;
import com.watabou.utils.BArray;
import com.watabou.utils.Bundle;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public abstract class ChampionEnemy extends Buff {

	{
		type = buffType.POSITIVE;
		revivePersists = true;
	}

	protected int color;
	protected int rays;

	@Override
	public int icon() {
		return BuffIndicator.CORRUPT;
	}

	@Override
	public void tintIcon(Image icon) {
		icon.hardlight(color);
	}

	@Override
	public void fx(boolean on) {
		if (on) target.sprite.aura( color, rays );
		else target.sprite.clearAura();
	}

	public void onAttackProc(Char enemy ){

	}

	public boolean canAttackWithExtraReach( Char enemy ){
		return false;
	}

	public float meleeDamageFactor(boolean adjacent) {
		return 1f;
	}

	public float damageTakenFactor(boolean externalAttack){
		return 1f;
	}

	public float accuracyFactor(){
		return 1f;
	}
	public float evasionFactor(boolean surpriseAttack){
		return 1f;
	}

	{
		immunities.add(AllyBuff.class);
	}

	public static void rollForChampion(Mob m){
		if (Dungeon.mobsToChampion <= 0) Dungeon.mobsToChampion = 8;

		Dungeon.mobsToChampion--;

		//we roll for a champion enemy even if we aren't spawning one to ensure that
		//mobsToChampion does not affect levelgen RNG (number of calls to Random.Int() is constant)
		Class<?extends ChampionEnemy> buffCls;
		switch (Random.Int(6)){
			case 0: default:    buffCls = Blazing.class;      break;
			case 1:             buffCls = Projecting.class;   break;
			case 2:             buffCls = AntiMagic.class;    break;
			case 3:             buffCls = Giant.class;        break;
			case 4:             buffCls = Blessed.class;      break;
			case 5:             buffCls = Growing.class;      break;
		}

		if (Dungeon.mobsToChampion <= 0 && Dungeon.isChallenged(Challenges.CHAMPION_ENEMIES)) {
			Buff.affect(m, buffCls);
			if (m.state != m.PASSIVE) {
				m.state = m.WANDERING;
			}
		}
	}

	public static class Blazing extends ChampionEnemy {

		{
			color = 0xFF8800;
			rays = 4;
		}

		@Override
		public void onAttackProc(Char enemy) {
			if (!Dungeon.level.water[enemy.pos]) {
				Buff.affect(enemy, Burning.class).reignite(enemy);
			}
		}

		@Override
		public void detach() {
			//don't trigger when killed by being knocked into a pit
			if (target.flying || !Dungeon.level.pit[target.pos]) {
				for (int i : PathFinder.NEIGHBOURS9) {
					if (!Dungeon.level.solid[target.pos + i] && !Dungeon.level.water[target.pos + i]) {
						GameScene.add(Blob.seed(target.pos + i, 2, Fire.class));
					}
				}
			}
			super.detach();
		}

		@Override
		public float meleeDamageFactor(boolean adjacent) {
			return 1.25f;
		}

		{
			immunities.add(Burning.class);
		}
	}

	public static class Projecting extends ChampionEnemy {

		{
			color = 0x8800FF;
			rays = 4;
		}

		public class Polished {

			private static final String COOLDOWN = "cooldown";
			private static final String TIMER = "timer";

			final static float baseCooldown = 1f;
			public boolean cooldown;
			Actor timer = null;
			{
				initCooldown();
			}

			void initCooldown() {
				initCooldown(baseCooldown);
			}
			void initCooldown(float cd) {
				cooldown = true;

				//this should realistically never happen
				if(timer != null) return;
				timer = new Actor() {

					{
						actPriority = LAST_PRIO;
					}

					@Override
					protected boolean act() {
						cooldown = false;
						timer = null;

						Actor.remove(this);
						return true;
					}
				};
				Actor.addDelayed(timer, cd);
			}
		}
		Polished polished = new Polished();

		@Override
		public void storeInBundle(Bundle bundle) {
			super.storeInBundle(bundle);
			bundle.put(Polished.COOLDOWN, polished.cooldown);
			if(polished.timer != null) bundle.put(Polished.TIMER, polished.timer);
		}
		@Override
		public void restoreFromBundle(Bundle bundle) {
			super.restoreFromBundle(bundle);

			if(bundle.contains(Polished.COOLDOWN))
				polished.cooldown = bundle.getBoolean(Polished.COOLDOWN);
			if(bundle.contains(Polished.TIMER)) {
				if (polished.timer == null) polished.initCooldown();
				polished.timer.restoreFromBundle(bundle.getBundle(Polished.TIMER));
			} else {
				//get rid of the spawn timer since we're loading
				Actor.remove(polished.timer);
				polished.timer = null;
				polished.cooldown = false;
			}
		}


		@Override
		public float meleeDamageFactor(boolean adjacent) {
			polished.initCooldown();
			return 1.25f;
		}

		@Override
		public boolean canAttackWithExtraReach(Char enemy) {
			int range = polished.cooldown ? 1 : 4;

			if (Dungeon.level.distance( target.pos, enemy.pos ) > range) {
				return false;
			} else {
				boolean[] passable = BArray.not(Dungeon.level.solid, null);
				for (Char ch : Actor.chars()) {
					//our own tile is always passable
					passable[ch.pos] = ch == target;
				}

				PathFinder.buildDistanceMap(enemy.pos, passable, 4);

				return PathFinder.distance[target.pos] <= 4;
			}
		}
	}

	public static class AntiMagic extends ChampionEnemy {

		{
			color = 0x00FF00;
			rays = 5;
		}

		@Override
		public float damageTakenFactor(boolean externalAttack) {
			return 0.6f;
		}

		{
			immunities.addAll(com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.AntiMagic.RESISTS);

			immunities.remove(HolyBomb.HolyDamage.class);
			immunities.remove(HolyDart.class);

			immunities.remove( Weakness.class );
			immunities.remove( Vulnerable.class );
			immunities.remove( Brittle.class );
			immunities.remove( Hex.class );
			immunities.remove( Degrade.class );

			immunities.remove( Blazing.class );
			immunities.remove( Shocking.class );
		}

	}

	//Also makes target large, see Char.properties()
	public static class Giant extends ChampionEnemy {

		{
			color = 0x0088FF;
			rays = 5;
		}

		@Override
		public float meleeDamageFactor(boolean adjacent) {
			return adjacent ? 1f : 1.25f;
		}

		@Override
		public float damageTakenFactor(boolean externalAttack) {
			return externalAttack ? 0.2f : 0.5f;
		}

		@Override
		public boolean canAttackWithExtraReach(Char enemy) {
			if (Dungeon.level.distance( target.pos, enemy.pos ) > 2){
				return false;
			} else {
				boolean[] passable = BArray.not(Dungeon.level.solid, null);
				for (Char ch : Actor.chars()) {
					//our own tile is always passable
					passable[ch.pos] = ch == target;
				}

				PathFinder.buildDistanceMap(enemy.pos, passable, 2);

				return PathFinder.distance[target.pos] <= 2;
			}
		}
	}

	public static class Blessed extends ChampionEnemy {

		{
			color = 0xFFFF00;
			rays = 6;
		}

		//Check Char::hit()
		@Override
		public float accuracyFactor() {
			return 5f;
		}

		@Override
		public float evasionFactor(boolean surpriseAttack) {
			return 3f;
		}
	}

	public static class Growing extends ChampionEnemy {

		{
			color = 0xFF2222; //a little white helps it stick out from background
			rays = 6;
		}

		//POLISHED: base 19%->25%
		private float multiplier = 1.25f + .00001f;

		public boolean Polished_huntThreshold() {
			return multiplier >= 2f;
		}
		
		private boolean Polished_huntNoti = false;
		public void Polished_growingHunt() {
			if(target.buff(MagicalSleep.class) != null) {
				Polished_huntNoti = false;
				return;
			}
			
			Mob mob = (Mob) target;
			if(Polished_huntThreshold() && !Dungeon.hero.isStealthyTo(target) && !(mob.state == mob.FLEEING)) {
				mob.aggro(Dungeon.hero);
				mob.target=Dungeon.hero.pos;
				
				if(!Polished_huntNoti) {
					GLog.w(Messages.get(ChampionEnemy.Growing.class, "hunt"));
					Polished_huntNoti = true;
				}
			}
		}
		
		private boolean Polished_weakenNoti = false;
		public void Polished_weaken(Mob src) {
			if(src.EXP > 0 && src.maxLvl > 0 && src != target) {
				//-10 turns
				multiplier -= 0.04f;
				multiplier = Math.max(multiplier, 1.25f + .00001f);
				
				Sample.INSTANCE.play(Assets.Sounds.BURNING);
				if(!Polished_weakenNoti) {
					GLog.p(Messages.get(ChampionEnemy.Growing.class, "weaken"));
					Polished_weakenNoti = true;
				}
			}
		}

		@Override
		public boolean act() {
			//POLISHED: .25%->.4%
			if(!Polished_huntThreshold()) multiplier += 0.02f;
			spend(5*TICK);
			return true;
		}

		@Override
		public float meleeDamageFactor(boolean adjacent) {
			return multiplier;
		}

		@Override
		public float damageTakenFactor(boolean externalAttack) {
			return 1f/multiplier;
		}

		@Override
		public float accuracyFactor() {
			return multiplier;
		}

		@Override
		public String desc() {
			String desc = Messages.get(this, "desc", (int)(100*(multiplier-1)), (int)(100*(1 - 1f/multiplier)));
			if(Polished_huntThreshold()) desc += "\n\n" + Messages.get(this, "hunt_desc");
			return desc;
		}

		private static final String MULTIPLIER = "multiplier";

		@Override
		public void storeInBundle(Bundle bundle) {
			super.storeInBundle(bundle);
			bundle.put(MULTIPLIER, multiplier);
		}

		@Override
		public void restoreFromBundle(Bundle bundle) {
			super.restoreFromBundle(bundle);
			multiplier = bundle.getFloat(MULTIPLIER);
		}
	}

}
