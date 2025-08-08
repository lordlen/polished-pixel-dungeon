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

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.journal.Bestiary;
import com.shatteredpixel.shatteredpixeldungeon.levels.Level;

public abstract class NPC extends Mob {

	{
		HP = HT = 1;
		EXP = 0;

		alignment = Alignment.NEUTRAL;
		state = PASSIVE;
	}
	
	public boolean visibleOnFog = false;

	@Override
	protected boolean act() {
		if (Dungeon.level.heroFOV[pos]){
			Bestiary.setSeen(getClass());
		}

		return super.act();
	}

	@Override
	public void move(int step, boolean travelling) {
		super.move(step, travelling);
		if(visibleOnFog && Dungeon.level.visited[pos]) {
			sprite.visible = true;
		}
	}
	
	@Override
	protected boolean moveSprite(int from, int to) {
		Level level = Dungeon.level;
		if (visibleOnFog && !level.heroFOV[to] &&
			( level.visited[from] || level.visited[to] )) {
			
			//a bit messy, ensure the animation plays out
			Dungeon.level.heroFOV[to] = true;
			boolean result = super.moveSprite(from, to);
			Dungeon.level.heroFOV[to] = false;
			return result;
		}
		else {
			return super.moveSprite(from, to);
		}
	}
	
	@Override
	public void beckon( int cell ) {
	}
	
}