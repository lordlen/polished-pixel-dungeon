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

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Belongings;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.effects.Enchanting;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.PurpleParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.Bag;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfEnchantment;
import com.shatteredpixel.shatteredpixeldungeon.journal.Catalog;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndBag;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndOptions;
import com.watabou.noosa.audio.Sample;

import java.util.ArrayList;

public class Stylus extends Item {
	
	private static final float TIME_TO_INSCRIBE = 2;
	
	private static final String AC_INSCRIBE = "INSCRIBE";
	
	{
		image = ItemSpriteSheet.STYLUS;
		
		stackable = true;

		defaultAction = AC_INSCRIBE;

		bones = true;
	}
	
	@Override
	public ArrayList<String> actions( Hero hero ) {
		ArrayList<String> actions = super.actions( hero );
		actions.add( AC_INSCRIBE );
		return actions;
	}
	
	@Override
	public void execute( Hero hero, String action ) {

		super.execute( hero, action );

		if (action.equals(AC_INSCRIBE)) {

			curUser = hero;
			GameScene.selectItem( itemSelector );
			
		}
	}
	
	@Override
	public boolean isUpgradable() {
		return false;
	}
	
	@Override
	public boolean isIdentified() {
		return true;
	}
	
	private void inscribe( Armor armor ) {

		if (!armor.cursedKnown){
			GLog.w( Messages.get(this, "identify"));
			return;
		} else if (armor.cursed || armor.hasCurseGlyph(false)){
			GLog.w( Messages.get(this, "cursed_armor"));
			return;
		}
		
		detach(curUser.belongings.backpack);
		Catalog.countUse(getClass());

		GLog.w( Messages.get(this, "inscribed_armor"));

		armor.inscribe(true);
		
		curUser.sprite.operate(curUser.pos);
		curUser.sprite.centerEmitter().start(PurpleParticle.BURST, 0.05f, 10);
		Enchanting.show(curUser, armor);
		Sample.INSTANCE.play(Assets.Sounds.BURNING);
		
		curUser.spend(TIME_TO_INSCRIBE);
		curUser.busy();
	}

	private void inscribe( BrokenSeal seal ) {
		
		if (seal.hasCurseGlyph()){
			GLog.w( Messages.get(this, "cursed_seal"));
			return;
		}

		detach(curUser.belongings.backpack);
		Catalog.countUse(getClass());

		GLog.w( Messages.get(this, "inscribed_seal"));

		seal.inscribe();

		curUser.sprite.operate(curUser.pos);
		curUser.sprite.centerEmitter().start(PurpleParticle.BURST, 0.05f, 10);
		Enchanting.show(curUser, seal);
		Sample.INSTANCE.play(Assets.Sounds.BURNING);

		curUser.spend(TIME_TO_INSCRIBE);
		curUser.busy();
	}
	
	@Override
	public int value() {
		return 30 * quantity;
	}

	private final WndBag.ItemSelector itemSelector = new WndBag.ItemSelector() {

		@Override
		public String textPrompt() {
			return Messages.get(Stylus.class, "prompt");
		}

		@Override
		public Class<?extends Bag> preferredBag(){
			return Belongings.Backpack.class;
		}

		@Override
		public boolean itemSelectable(Item item) {
			return item instanceof Armor || item instanceof BrokenSeal;
		}

		@Override
		public void onSelect( Item item ) {
			if(item instanceof Armor) {
				Armor armor = (Armor)item;
				BrokenSeal seal = armor.checkSeal();
				
				if(seal == null) {
					inscribe(armor);
				}
				else if(!Armor.runic()) {
					if (!armor.cursedKnown) GLog.w( Messages.get(this, "identify"));
					else inscribe(seal);
				}
				else {
					String armorGlyph;
					if(!armor.cursedKnown && (armor.glyph() == null || armor.hasCurseGlyph())) {
						armorGlyph = Messages.get(Stylus.class, "unknown");
					}
					else if(armor.glyph() != null) {
						armorGlyph = armor.glyph().name();
					}
					else {
						armorGlyph = Messages.get(Stylus.class, "none");
					}
					String sealGlyph = seal.glyph() != null ? seal.glyph().name() : Messages.get(Stylus.class, "none");
					
					GameScene.show(new WndOptions(
							new ItemSprite(Stylus.this),
							Messages.titleCase(new Stylus().name()),
							Messages.get(Stylus.class, "choose_desc"),
							"Armor: " + armorGlyph,
							"Seal: " + sealGlyph) {

						@Override
						protected void onSelect(int index) {
							if(index == 0) 	inscribe(armor);
							else 			inscribe(seal);

							super.onSelect(index);
						}
					});
				}
			}
			else if(item instanceof BrokenSeal) {
				if (!Armor.runic()) {
					GLog.w(Messages.get(Stylus.this, "no_runic"));
				}
				else {
					inscribe( (BrokenSeal) item );
				}
			}
		}
	};
}
