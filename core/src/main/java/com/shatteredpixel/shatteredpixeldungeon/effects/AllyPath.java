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
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.watabou.noosa.Group;
import com.watabou.noosa.Image;
import com.watabou.utils.PointF;

public class AllyPath extends Group {
	
	public static final int DEFEND = 0xFF0000FF;
	public static final int ATTACK = 0xFFFF0000;
	public static final int FOLLOW = 0xFF00FF00;
	
	public static final int CHAIN  = 0x80808080;
	
	private Line line;
	private Point point;
	
	public AllyPath(PointF s, PointF e, int color) {
		line = new Line(s, e, color);
		add(line);
		
		point = new Point(e, color);
		add(point);
		
		GameScene.effectOverFog(line);
		GameScene.effectOverFog(point);
	}
	
	@Override
	public void killAndErase() {
		super.killAndErase();
		line.killAndErase();
		point.killAndErase();
	}
	
	public static class Line extends Image {
		private static final double A = 180 / Math.PI;
		
		private Line(PointF s, PointF e, int color) {
			super( Effects.get( Effects.Type.ALLY_PATH ) );
			visible = Dungeon.hero.ready;
			
			scale.y = 0.75f;
			tint(color);
			
			origin.set( 0, height / 2 );
			x = s.x - origin.x;
			y = s.y - origin.y;
			
			float dx = e.x - s.x;
			float dy = e.y - s.y;
			angle = (float)(Math.atan2( dy, dx ) * A);
			scale.x = (float)Math.sqrt( dx * dx + dy * dy ) / width;
		}
		
		@Override
		public void update() {
			visible = Dungeon.hero.ready;
			if(visible) {
				super.update();
			}
		}
	}
	
	
	public static class Point extends Image {
		private Point(PointF c, int color) {
			super( Effects.get( Effects.Type.ALLY_POINT ) );
			visible = Dungeon.hero.ready;
			
			scale.x = 1.4f;
			scale.y = 1.4f;
			tint(color);
			
			origin.set( 1, height / 2 );
			x = c.x - origin.x;
			y = c.y - origin.y;
		}
		
		@Override
		public void update() {
			visible = Dungeon.hero.ready;
			if(visible) {
				super.update();
			}
		}
	}
}
