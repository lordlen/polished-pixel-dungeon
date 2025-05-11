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
		if (Dungeon.level.distance(pos, Dungeon.hero.pos) <= 2){
			Buff.affect(Dungeon.hero, Disoriented.class, Disoriented.DURATION);
		}

		super.activate();

		GameScene.updateFog(); //just in case hero wasn't moved
		Dungeon.observe();

	}

	public static class Disoriented extends FlavourBuff {

		{
			type=buffType.NEGATIVE;
		}
		public static final float DURATION = 25f;

		@Override
		public int icon() {
			return BuffIndicator.DISORIENTED;
		}

		@Override
		public float iconFadePercent() {
			return (DURATION - cooldown()) / DURATION;
		}

		public int depth = Dungeon.depth;
		public int branch = Dungeon.branch;

		boolean[] visited = null;
		boolean[] mapped = null;

		@Override
		public boolean attachTo(Char target) {
			if(target == Dungeon.hero) {
				Level level = Dungeon.level;
				visited = level.visited.clone();
				mapped = level.mapped.clone();

				BArray.setFalse(level.visited);
				BArray.setFalse(level.mapped);
			}

			return super.attachTo(target);
		}

		@Override
		public void detach() {
			if (target == Dungeon.hero && visited != null && mapped != null) {
				boolean thisLevel = Dungeon.depth == depth && Dungeon.branch == branch;

				Level level = thisLevel ? Dungeon.level : Dungeon.getLevel(depth, branch);

				if(level != null && level.length() == visited.length) {
					BArray.or(level.visited, visited, level.visited);
					BArray.or(level.mapped, mapped, level.mapped);

					if(thisLevel) {
						GameScene.updateFog();
						Dungeon.observe();
					} else {
						Dungeon.replaceLevel(depth, branch, level);
					}
				}
			}

			super.detach();
		}

		private static final String VISITED = "visited";
		private static final String MAPPED = "mapped";
		private static final String DEPTH = "depth";
		private static final String BRANCH = "branch";

		@Override
		public void storeInBundle(Bundle bundle) {
			super.storeInBundle(bundle);
			bundle.put(VISITED, visited);
			bundle.put(MAPPED, mapped);

			bundle.put(DEPTH, depth);
			bundle.put(BRANCH, branch);
		}

		@Override
		public void restoreFromBundle(Bundle bundle) {
			super.restoreFromBundle(bundle);
			visited = bundle.getBooleanArray(VISITED);
			mapped = bundle.getBooleanArray(MAPPED);

			depth = bundle.getInt(DEPTH);
			branch = bundle.getInt(BRANCH);
		}
	}
}
