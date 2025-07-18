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

package com.shatteredpixel.shatteredpixeldungeon.effects;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.tiles.DungeonTilemap;
import com.shatteredpixel.shatteredpixeldungeon.ui.Icons;
import com.watabou.noosa.Game;
import com.watabou.noosa.Image;

public class TargetedCell extends Image {
	
	public static final int RED = 0xFF0000;
	public static final int YELLOW = 0xFFFF00;

	private float alpha;
	
	private boolean fadeOut = false;
	protected boolean startFade() {
		return true;
	}
	
	public static TargetedCell timed( int pos, float time, Char assigned ) {
		return new TargetedCell(pos) {
			@Override
			protected boolean startFade() {
				return Actor.now() + Dungeon.hero.cooldown() > time || !assigned.isAlive();
			}
		};
	}
	
	public TargetedCell( int pos ) {
		this(pos, RED);
	}
	
	public TargetedCell( int pos, int color ) {
		super(Icons.get(Icons.TARGET));
		hardlight(color);
		
		origin.set( width/2f );
		point( DungeonTilemap.tileToWorld( pos ) );
		
		alpha = 1f;
		scale.set(alpha);
	}
	
	public void update() {
		alpha -= Game.elapsed/2f;
		
		fadeOut = fadeOut || startFade();
		if (!fadeOut) {
			alpha = Math.max(0.75f, alpha);
		}
		
		alpha( alpha );
		scale.set( alpha );
		if (alpha <= 0) killAndErase();
	}
	
}
