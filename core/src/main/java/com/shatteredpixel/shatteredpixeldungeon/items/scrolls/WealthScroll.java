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

package com.shatteredpixel.shatteredpixeldungeon.items.scrolls;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.WealthDrop;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.Bag;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.Potion;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.Bundle;

public class WealthScroll extends Scroll implements WealthDrop<Scroll> {

	{
		image = ItemSpriteSheet.MYSTERY_SCROLL;
	}

	@Override
	public void doRead() {
		scroll.anonymous = true;
		scroll.doRead();
		scroll.anonymous = false;
	}

	@Override
	public void updateStats() {
		scroll.talentFactor *= 0.5f;

		talentFactor = scroll.talentFactor;
		talentChance = scroll.talentChance;
	}

	@Override
	protected void onThrow(int cell) {
		WealthDrop.vanishVFX(cell);
	}


	private Scroll scroll = null;
	@Override
	public Scroll item() {
		return scroll;
	}
	@Override
	public void setItem(Scroll item) {
		this.scroll = item;
	}
	
	Decay decay = null;
	@Override
	public Decay decay() {
		return decay;
	}
	public void setDecay(Decay decay) {
		this.decay = decay;
	}

	@Override
	public Item identify( boolean byHero ) {
		if(valid()) scroll.identify(byHero);
		return this;
	}
	@Override
	public boolean isKnown() {
		return valid() && scroll.isKnown();
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
		if(scroll != null) bundle.put( WEALTH_ITEM, scroll.getClass() );
	}
	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle(bundle);
		set(bundle.getClass( WEALTH_ITEM ));
	}
}
