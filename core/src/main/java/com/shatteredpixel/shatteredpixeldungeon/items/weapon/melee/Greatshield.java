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

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class Greatshield extends MeleeWeapon {

	{
		image = ItemSpriteSheet.GREATSHIELD;

		tier = 5;
	}

	@Override
	public int max(int lvl) {
		return  Math.round(3f*(tier+1)) +   //18 base, down from 20
				lvl*(tier-1);               //+3 per level, down from +6
	}

	@Override
	public int defenseFactor( Char owner ) {
		return DRMax();
	}

	public int DRMax(){
		return DRMax(buffedLvl());
	}

	//6 extra defence, plus 2 per level
	public int DRMax(int lvl){
		return 6 + 2*lvl;
	}
	
	public String statsInfo(){
		if (isIdentified()){
			return Messages.get(this, "stats_desc", 6+2*buffedLvl());
		} else {
			return Messages.get(this, "typical_stats_desc", 6);
		}
	}

	@Override
	protected void duelistAbility(Hero hero, Integer target) {
		RoundShield.guardAbility(hero, 5 + 2*buffedLvl(), 2 + buffedLvl(), this);
	}

	@Override
	public String abilityInfo() {
		if (levelKnown){
			return Messages.get(this, "ability_desc", 2+buffedLvl(), 5 + 2*buffedLvl());
		} else {
			return Messages.get(this, "typical_ability_desc", 2, 5);
		}
	}

	@Override
	public String upgradeAbilityStat(int level) {
		return (2 + level) + ", " + (5 + 2*level);
	}
}
