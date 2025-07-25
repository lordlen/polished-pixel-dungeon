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

package com.shatteredpixel.shatteredpixeldungeon.levels.features;

import com.shatteredpixel.shatteredpixeldungeon.actors.Timer;
import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Challenges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.SPDSettings;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.BArray;
import com.watabou.utils.PathFinder;

public class Door {

	public static void enter( int pos ) {
		Level.set( pos, Terrain.OPEN_DOOR );
		GameScene.updateMap( pos );
		
		if (Dungeon.level.heroFOV[pos]) {
			Dungeon.observe();
			Sample.INSTANCE.play( Assets.Sounds.OPEN );
		}
		
		if(Polished.interruptHero(pos)) {
			Level level = Dungeon.level;
			Mob mob = level.findMob(pos);
			
			Timer.addTimer(() -> {
				
				//make sure the door is still open by hero's turn
				if(level.map[pos] == Terrain.OPEN_DOOR) {
					Dungeon.hero.mobInterrupt(mob);
					GameScene.Polished.blockInput(0.75f);
					
					mob.polished.spot();
					Polished.initTimer();
				}
				
			}).setBeforeHero();
		}
	}

	public static void leave( int pos ) {
		int chars = 0;
		
		for (Char ch : Actor.chars()){
			if (ch.pos == pos) chars++;
		}
		
		//door does not shut if anything else is also on it
		if (Dungeon.level.heaps.get( pos ) == null && chars <= 1) {
			Level.set( pos, Terrain.DOOR );
			GameScene.updateMap( pos );
			if (Dungeon.level.heroFOV[pos]) {
				Dungeon.observe();
			}
		}
	}
	
	
	static class Polished {
		static boolean interruptHero( int pos ) {
			Level level = Dungeon.level;
			Hero hero = Dungeon.hero;
			
			if(!SPDSettings.Polished.inputBlock() || !Dungeon.isChallenged(Challenges.DARKNESS)) {
				return false;
			}
			if(Polished.onCooldown) {
				return false;
			}
			
			if(level.heroFOV[pos]) {
				return false;
			}
			if(!level.visited[pos] && !level.mapped[pos]) {
				return false;
			}
			if(pos == hero.pos || level.distance(pos, hero.pos) > 7) {
				return false;
			}
			
			Mob mob = level.findMob(pos);
			// Allies and fleeing enemies usually have predictable paths anyway,
			// so we're not really giving away much info here.
			if(mob == null || mob.alignment == Char.Alignment.ALLY || mob.state == mob.FLEEING) {
				return false;
			}
			// This does give information however. Would prefer if there was another way.
			if(mob.polished.recentlySpot || Hero.Polished.ignoreMobs.contains(mob)) {
				return false;
			}
			
			boolean[] pass = BArray.or(BArray.not(level.solid), level.passable, null);
			PathFinder.buildDistanceMap(pos, pass, 7);
			
			int dist = PathFinder.distance[hero.pos];
			if (dist == Integer.MAX_VALUE) {
				return false;
			}
			if(Hero.Polished.nextStep() == -1 || PathFinder.distance[Hero.Polished.nextStep()] > dist) {
				return false;
			}
			if(3*Hero.Polished.pathLength() < dist) {
				return false;
			}
			
			if(hero.isStealthy()) {
				return false;
			}
			
			return true;
		}
		
		static final int interruptCooldown = 10;
		static boolean onCooldown = false;
		
		static Timer timer = null;
		static void initTimer() {
			onCooldown = true;
			
			if(timer == null) {
				timer = Timer.addTimer(() -> {
					onCooldown = false;
					timer = null;
				}, interruptCooldown);
			}
		}
	}
}
