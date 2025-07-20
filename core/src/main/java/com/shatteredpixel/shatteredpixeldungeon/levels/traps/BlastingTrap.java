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

import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.items.Heap;
import com.shatteredpixel.shatteredpixeldungeon.items.bombs.Bomb;
import com.watabou.utils.BArray;
import com.watabou.utils.PathFinder;

public class BlastingTrap extends Trap {

	{
		color = ORANGE;
		shape = LARGE_DOT;
	}
	
	@Override
	public void activate() {
		affectTiles();
		new BlastingBomb().explode(pos);
		
		if (reclaimed && !Dungeon.hero.isAlive()) {
			Badges.validateDeathFromFriendlyMagic();
		}
	}
	
	private void affectTiles() {
		boolean[] explodable = new boolean[Dungeon.level.length()];
		BArray.not( Dungeon.level.solid, explodable);
		BArray.or( Dungeon.level.flamable, explodable, explodable);
		
		PathFinder.buildDistanceMap( pos, explodable, 2 );
		for (int i = 0; i < PathFinder.distance.length; i++) {
			if (PathFinder.distance[i] != Integer.MAX_VALUE) {
				
				Heap heap = Dungeon.level.heaps.get(i);
				if(heap != null) heap.Polished_destroyEquipables();
				
				Char ch = Actor.findChar(i);
				if (ch instanceof Mob){
					Buff.prolong(ch, Trap.HazardAssistTracker.class, HazardAssistTracker.DURATION);
				}
			}
		}
	}
	
	public static class BlastingBomb extends ExplosiveTrap.ExplosiveBomb {
		@Override
		protected int explosionRange() {
			return 2;
		}
		
		@Override
		protected int explosionDamage() {
			return Math.round(super.explosionDamage() * 0.75f);
		}
	}

}
