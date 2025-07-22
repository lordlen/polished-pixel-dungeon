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
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.items.bombs.HolyBomb;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Shocking;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.darts.HolyDart;
import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.levels.RegularLevel;
import com.shatteredpixel.shatteredpixeldungeon.levels.rooms.Room;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.BArray;
import com.watabou.noosa.Image;
import com.watabou.utils.Bundle;
import com.watabou.utils.GameMath;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Point;
import com.watabou.utils.Random;

import java.util.ArrayList;

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

	public float damageTakenFactor(Object src){
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

			private static final String TIMER = "timer";

			final static float baseCooldown = 1f;
			Actor timer = null;
			
			{
				if(!Dungeon.Polished.loading) {
					initCooldown();
				}
			}

			void initCooldown() {
				initCooldown(baseCooldown);
			}
			void initCooldown(float cd) {
				//this should realistically never happen
				if(timer != null) return;
				
				timer = new Actor() {
					{
						actPriority = LAST_PRIO;
					}

					@Override
					protected boolean act() {
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
			if(polished.timer != null) bundle.put(Polished.TIMER, polished.timer);
		}
		@Override
		public void restoreFromBundle(Bundle bundle) {
			super.restoreFromBundle(bundle);

			if(bundle.contains(Polished.TIMER)) {
				if (polished.timer == null) polished.initCooldown();
				polished.timer.restoreFromBundle(bundle.getBundle(Polished.TIMER));
			}
		}


		@Override
		public float meleeDamageFactor(boolean adjacent) {
			polished.initCooldown();
			return 1.25f;
		}

		@Override
		public boolean canAttackWithExtraReach(Char enemy) {
			int range = polished.timer == null ? 4 : 1;

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
		public float damageTakenFactor(Object src) {
			return 0.6f;
		}

		{
			immunities.addAll(com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.AntiMagic.RESISTS);
		}

	}

	//Also makes target large, see Char.properties()
	public static class Giant extends ChampionEnemy {

		{
			color = 0x0088FF;
			rays = 5;
		}

		@Override
		public float damageTakenFactor(Object src) {
			if (Hero.Polished.isHeroSource(src, false)) {
				src = Dungeon.hero;
			}
			
			if(isDamageResisted(src)) {
				return 0.1f;
			}
			else if(target.distance((Char) src) > 1) {
				return 0.4f;
			}
			else {
				return 0.5f;
			}
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
	
	boolean isDamageResisted(Object src) {
		
		if(!(src instanceof Char)) {
			//always get def boost against debuffs, blobs and other indirect sources
			return true;
		}
		
		Char attacker = (Char)src;
		
		if(!(Dungeon.level instanceof RegularLevel)) {
			
			//just check for an open path to the attacker
			boolean[] pass = BArray.not(Dungeon.level.solid);
			BArray.and(pass, Dungeon.level.openSpace, pass);
			
			PathFinder.buildDistanceMap(target.pos, pass);
			for(int offset : PathFinder.NEIGHBOURS9) {
				if(PathFinder.distance[attacker.pos + offset] < Integer.MAX_VALUE) {
					return false;
				}
			}
			
			return true;
			
		}
		
		RegularLevel level = (RegularLevel)Dungeon.level;
		
		//resist damage unless...
		// on the same room
		Room room = (level.roomWithin(target.pos));
		if(room != null && room.within(level.cellToPoint(attacker.pos))) {
			return false;
		}
		Room roomAttacker = (level.roomWithin(attacker.pos));
		if(roomAttacker!= null && roomAttacker.within(level.cellToPoint(target.pos))) {
			return false;
		}
		
		// there's an open path to the attacker
		boolean[] pass = BArray.not(level.solid);
		BArray.and(pass, level.openSpace, pass);
		
		PathFinder.buildDistanceMap(target.pos, pass);
		for(int offset : PathFinder.NEIGHBOURS9) {
			if(PathFinder.distance[attacker.pos + offset] < Integer.MAX_VALUE) {
				return false;
			}
		}
		
		// there's an open path to their room
		if(roomAttacker != null) {
			for(Point p : roomAttacker.getPoints()) {
				if(PathFinder.distance[level.pointToCell(p)] < Integer.MAX_VALUE) {
					return false;
				}
			}
		}
		
		return true;
		
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
		
		//we add a small epsilon to avoid text display errors
		private static final float baseMulti = 1.25f 	+ .000001f;
		private static final float maxMulti  = 2f 		+ .000001f;
		private float multiplier = baseMulti;
		
		public boolean campExit = true;

		
		private void modifyMultiplier(float amount) {
			multiplier = GameMath.gate(baseMulti, multiplier + amount, maxMulti);
		}
		
		public boolean huntThreshold() {
			return multiplier >= 2f;
		}
		
		private boolean Polished_huntNoti = false;
		public void updateState() {
			Mob mob = (Mob) target;
			if(campExit) {
				campExit = mob.state != mob.HUNTING;
			}
			
			if(huntThreshold()) {
				
				if(target.buff(MagicalSleep.class) != null) {
					Polished_huntNoti = false;
					return;
				}
				if(Dungeon.hero.isStealthyTo(target) || mob.state == mob.FLEEING) {
					return;
				}
				
				mob.aggro(Dungeon.hero);
				mob.target=Dungeon.hero.pos;
				
				if(!Polished_huntNoti) {
					GLog.w(Messages.get(ChampionEnemy.Growing.class, "hunt"));
					Polished_huntNoti = true;
				}
				
			}
		}
		
		public static void relocateAll(Level level) {
			for (Mob mob : level.mobs) {
				if(mob.buff(ChampionEnemy.Growing.class) != null) {
					relocateToExit(mob, level);
				}
			}
		}
		
		public static void relocateToExit(Char ch, Level level) {
			
			int exit = level.exit();
			if(exit == 0) return;
			
			for(int offset : PathFinder.NEIGHBOURS8) {
				int cell = exit + offset;
				
				if (level.validRespawn(ch, cell)) {
					ch.pos = cell;
					return;
				}
			}
			
			for(int offset : PathFinder.NEIGHBOURS25) {
				int cell = exit + offset;
				
				if (cell >= 0 && cell < level.length() &&
					level.validRespawn(ch, cell)) {
					
					ch.pos = cell;
					return;
				}
			}
		}
		
		public static int closeToExit(Char ch) {
			
			Growing grow = ch.buff(Growing.class);
			if (grow == null || !grow.campExit) {
				return -1;
			}
			
			Level level = Dungeon.level;
			int exit = level.exit();
			
			PathFinder.buildDistanceMap(exit, Dungeon.findPassable(ch, level.passable));
			
			//cant reach exit
			if(PathFinder.distance[ch.pos] == Integer.MAX_VALUE) {
				return -1;
			}
			
			ArrayList<Integer> candidates = new ArrayList<>();
			for(int i = 0; i < level.length(); i++) {
				if(PathFinder.distance[i] <= 7 && i != exit) {
					candidates.add(i);
				}
			}
			
			//exit blocked
			if(candidates.isEmpty()) {
				return -1;
			}
			
			return Random.element(candidates);
		}
		
		//unused, leftover code
		private boolean Polished_weakenNoti = false;
		public void Polished_weaken(Mob src) {
			if(src.EXP > 0 && src.maxLvl > 0 && src != target) {
				//-10 turns
				modifyMultiplier(-0.04f);
				
				Sample.INSTANCE.play(Assets.Sounds.BURNING);
				if(!Polished_weakenNoti) {
					GLog.p(Messages.get(ChampionEnemy.Growing.class, "weaken"));
					Polished_weakenNoti = true;
				}
			}
		}

		@Override
		public boolean act() {
			modifyMultiplier(+0.02f);
			spend(6*TICK);
			return true;
		}

		@Override
		public float meleeDamageFactor(boolean adjacent) {
			return multiplier;
		}

		@Override
		public float damageTakenFactor(Object src) {
			return 1f/multiplier;
		}

		@Override
		public float accuracyFactor() {
			return multiplier;
		}

		@Override
		public String desc() {
			String desc = Messages.get(this, "desc", (int)(100*(multiplier-1)), (int)(100*(1 - 1f/multiplier)));
			if(huntThreshold()) desc += "\n\n" + Messages.get(this, "hunt_desc");
			return desc;
		}

		private static final String MULTIPLIER 	= "multiplier";
		private static final String CAMP_EXIT 	= "camp_exit";

		@Override
		public void storeInBundle(Bundle bundle) {
			super.storeInBundle(bundle);
			bundle.put(MULTIPLIER, multiplier);
			bundle.put(CAMP_EXIT, campExit);
		}

		@Override
		public void restoreFromBundle(Bundle bundle) {
			super.restoreFromBundle(bundle);
			multiplier = bundle.getFloat(MULTIPLIER);
			campExit = bundle.getBoolean(CAMP_EXIT);
		}
	}

}
