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

package com.shatteredpixel.shatteredpixeldungeon.items.potions;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.WealthDrop;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.Bag;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.Bundle;

public class WealthPotion extends Potion implements WealthDrop<Potion, WealthPotion> {

	{
		image = ItemSpriteSheet.MYSTERY_POTION;
	}

	@Override
	public void shatter( int cell ) {
		pot.anonymous = true;
		pot.shatter(cell);
		pot.anonymous = false;
	}
	@Override
	public void apply(Hero hero) {
		pot.anonymous = true;
		pot.apply(hero);
		pot.anonymous = false;
	}

	@Override
	public void updateStats() {
		pot.talentFactor *= 0.5f;

		talentFactor = pot.talentFactor;
		talentChance = pot.talentChance;
	}

	@Override
	public String defaultAction() {
		if (mustThrowPots.contains(pot.getClass())) {
			return AC_THROW;
		} else if (canThrowPots.contains(pot.getClass())){
			return AC_CHOOSE;
		} else {
			return AC_DRINK;
		}
	}
	@Override
	public void doThrow(Hero hero) {
		GameScene.selectCell(thrower);
	}
	@Override
	protected void onThrow(int cell) {
		if(Dungeon.level.map[cell] == Terrain.WELL) {
			WealthDrop.vanishVFX(cell);
			return;
		}
		super.onThrow(cell);
	}


	private Potion pot = null;
	@Override
	public Potion item() {
		return pot;
	}
	@Override
	public void setItem(Potion item) {
		this.pot = item;
	}

	@Override
	public Item identify( boolean byHero ) {
		if(valid()) pot.identify(byHero);
		return this;
	}
	@Override
	public boolean isKnown() {
		return valid() && pot.isKnown();
	}
	@Override
	public boolean isSimilar(Item item) {
		return matches(item);
	}

	@Override
	public boolean doPickUp(Hero hero, int pos) {
		return onPickUp(super.doPickUp(hero, pos));
	}
	@Override
	public boolean collect(Bag container) {
		return afterCollect(super.collect(container));
	}
	@Override
	public void doDrop( Hero hero ) {
		onDrop(hero);
	}
	@Override
	protected void onDetach() {
		super.onDetach();
		afterDetach();
	}

	@Override
	public int value() {
		return 0;
	}
	@Override
	public int energyVal() {
		return 0;
	}

	@Override
	public String name() {
		return dropName();
	}
	@Override
	public String desc() {
		return dropDesc();
	}

	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle(bundle);
		if(pot != null) bundle.put( WEALTH_ITEM, pot.getClass() );
	}
	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle(bundle);
		set(bundle.getClass( WEALTH_ITEM ));
	}
}
