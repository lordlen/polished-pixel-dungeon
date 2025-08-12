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
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;

public class Light extends FlavourBuff {
	
	{
		type = buffType.POSITIVE;
	}

	public static final float DURATION	= 250f;
	public static final int DISTANCE	= 6;
	
	@Override
	public boolean attachTo( Char target ) {
		if (super.attachTo( target )) {
			if (Dungeon.level != null) {
				target.viewDistance = Math.max( Dungeon.level.viewDistance, DISTANCE );
				Dungeon.observe();
			}
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void detach() {
		target.viewDistance = Dungeon.level.viewDistance;
		
		if(target == Dungeon.hero) {
			int updateDist = DISTANCE;
			updateDist *= 1f + 0.18f*Dungeon.hero.pointsInTalent(Talent.FARSIGHT);
			//updateDist *= EyeOfNewt.visionRangeMultiplier();
			
			Dungeon.observe();
			GameScene.updateFog(Dungeon.hero.pos, updateDist+1);
		}
		super.detach();
	}
	
	public void onLevelSwitch() {
		if(Dungeon.level != null && Dungeon.level.feeling == Level.Feeling.DARK) {
			if(cooldown() > Math.round(DURATION * 2/3f)) {
				spendConstant(Math.round(DURATION * 2/3f) - cooldown());
				
				Dungeon.Polished.runDelayed(() -> GLog.n(Messages.get(this, "dark")));
				Sample.INSTANCE.playDelayed(Assets.Sounds.BURNING, 0.1f);
			}
		}
	}

	public void weaken( int amount ){
		spend(-amount);
	}

	public void extend( float amount ){
		spend(amount);
	}
	
	@Override
	public int icon() {
		return BuffIndicator.LIGHT;
	}
	
	@Override
	public float iconFadePercent() {
		return Math.max(0, (DURATION - visualcooldown()) / DURATION);
	}

	@Override
	public void fx(boolean on) {
		if (on) target.sprite.add(CharSprite.State.ILLUMINATED);
		else target.sprite.remove(CharSprite.State.ILLUMINATED);
	}
}
