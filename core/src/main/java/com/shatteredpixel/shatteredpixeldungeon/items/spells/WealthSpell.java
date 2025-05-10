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

package com.shatteredpixel.shatteredpixeldungeon.items.spells;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.WealthDrop;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.Bag;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.Bundle;

public class WealthSpell extends Spell implements WealthDrop<Spell, WealthSpell> {

	{
		image = ItemSpriteSheet.SPELL_HOLDER;
	}

	@Override
	public void onCast(Hero hero) {
		spell.onCast(hero);
	}

	@Override
	public void updateStats() {
		spell.talentFactor *= 0.5f;

		talentFactor = spell.talentFactor;
		talentChance = spell.talentChance;
	}

	@Override
	protected void onThrow(int cell) {
		vanishVFX(cell);
	}

	@Override
	public ItemSprite.Glowing glowing() {
		return valid() ? spell.glowing() : null;
	}


	private Spell spell = null;
	@Override
	public Spell item() {
		return spell;
	}
	@Override
	public void setItem(Spell item) {
		this.spell = item;
	}

	@Override
	public Item identify( boolean byHero ) {
		return spell.identify(byHero);
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
		if(spell != null) bundle.put( WEALTH_ITEM, spell.getClass() );
	}
	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle(bundle);
		set(bundle.getClass( WEALTH_ITEM ));
	}
}
