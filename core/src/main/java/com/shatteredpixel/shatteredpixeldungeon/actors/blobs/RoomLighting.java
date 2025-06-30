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

package com.shatteredpixel.shatteredpixeldungeon.actors.blobs;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Challenges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Light;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.BlobEmitter;
import com.shatteredpixel.shatteredpixeldungeon.levels.HallsLevel;
import com.watabou.noosa.audio.Sample;

public class RoomLighting extends Blob {

	@Override
	protected void evolve() {

		int cell;
		for (int i = area.left; i < area.right; i++) {
			for (int j = area.top; j < area.bottom; j++) {
				cell = i + j*Dungeon.level.width();
				if (cur[cell] > 0) {

					off[cell] = cur[cell];
					volume += off[cell];

				} else {
					off[cell] = 0;
				}
			}
		}
		
		Hero hero = Dungeon.hero;
		if (hero.isAlive() && cur[hero.pos] > 0) {
			if((Dungeon.isChallenged(Challenges.DARKNESS) || Dungeon.level instanceof HallsLevel)) {
				
				boolean update = hero.buff(Light.class) == null;
				Buff.Polished.prolongAligned(hero, Light.class, 1f);
				
				if(update) {
					Sample.INSTANCE.play( Assets.Sounds.BURNING );
					Dungeon.observe();
				}
			}
		}
	}
	
	// do we add visuals?
	@Override
	public void use( BlobEmitter emitter ) {
		super.use( emitter );
		//emitter.start( ShaftParticle.FACTORY, 0.9f, 0 );
	}
	
	@Override
	public String tileDesc() {
		return super.tileDesc();
		//return Messages.get(this, "desc");
	}
}
