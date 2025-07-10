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

package com.shatteredpixel.shatteredpixeldungeon.levels.traps;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;
import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.watabou.utils.BArray;
import com.watabou.utils.Bundle;

public class WarpingTrap extends TeleportationTrap {

	{
		color = TEAL;
		shape = STARS;
	}

	@Override
	public void disarm() {
		if(Actor.findChar(pos) != null) super.disarm();
	}

	@Override
	public void activate() {
		if (Dungeon.level.adjacent(pos, Dungeon.hero.pos)){
			Disoriented.applyToHero(scalingDepth() <= 5 ? Disoriented.DURATION : 2*Disoriented.DURATION);
		}

		super.activate();

	}

	public static class Disoriented extends FlavourBuff {

		{
			type=buffType.NEGATIVE;
		}
		public static final float DURATION = 25f;

		@Override
		public int icon() {
			return active ? BuffIndicator.DISORIENTED : BuffIndicator.NONE;
		}

		@Override
		public float iconFadePercent() {
			return (DURATION - cooldown()) / DURATION;
		}
		
		boolean active = true;
		int depth = -1;
		int branch = -1;

		boolean[] visited;
		boolean[] mapped;
		
		public static void applyToHero(float duration) {
			for (Disoriented applied : Dungeon.hero.buffs(Disoriented.class)) {
				if(applied.currentLevel()) {
					
					applied.postpone(duration);
					applied.removeVision();
					return;
				}
			}
			
			Buff.affect(Dungeon.hero, Disoriented.class, duration);
		}
		
		public void onLevelSwitch() {
			if(!active && currentLevel()) {
				detach();
			}
		}
		
		@Override
		public boolean attachTo(Char target) {
			if(Dungeon.Polished.loading) {
				return super.attachTo(target);
			}
			else if(target == Dungeon.hero && super.attachTo(target)) {
				removeVision();
				return true;
			}
			else return false;
		}
		
		@Override
		public void detach() {
			if(currentLevel()) {
				restoreVision();
				super.detach();
			}
			else {
				diactivate();
				active = false;
			}
		}
		
		void removeVision() {
			depth = Dungeon.depth;
			branch = Dungeon.branch;
			
			Level level = Dungeon.level;
			if(visited == null || visited.length != level.length()) {
				visited = level.visited.clone();
				mapped = level.mapped.clone();
			}
			else {
				BArray.or(visited, level.visited, visited);
				BArray.or(mapped, level.mapped, mapped);
			}
			
			BArray.setFalse(level.visited);
			BArray.setFalse(level.mapped);
			
			GameScene.updateFog();
			Dungeon.observe();
		}
		
		void restoreVision() {
			Level level = Dungeon.level;
			if (level != null && level.length() == visited.length) {
				
				BArray.or(level.visited, visited, level.visited);
				BArray.or(level.mapped, mapped, level.mapped);
				
				GameScene.updateFog();
				Dungeon.observe();
				
			}
		}
		
		boolean currentLevel() {
			return depth == Dungeon.depth && branch == Dungeon.branch;
		}
		
		private static final String ACTIVE 	= "active";
		private static final String DEPTH 	= "depth";
		private static final String BRANCH 	= "branch";
		
		private static final String VISITED = "visited";
		private static final String MAPPED 	= "mapped";

		@Override
		public void storeInBundle(Bundle bundle) {
			super.storeInBundle(bundle);
			bundle.put(ACTIVE, active);
			bundle.put(DEPTH, depth);
			bundle.put(BRANCH, branch);
			
			bundle.put(VISITED, visited);
			bundle.put(MAPPED, mapped);
		}

		@Override
		public void restoreFromBundle(Bundle bundle) {
			super.restoreFromBundle(bundle);
			if(bundle.contains(ACTIVE)) {
				active = bundle.getBoolean(ACTIVE);
			}
			depth = bundle.getInt(DEPTH);
			branch = bundle.getInt(BRANCH);
			
			visited = bundle.getBooleanArray(VISITED);
			mapped = bundle.getBooleanArray(MAPPED);
		}
	}
}
