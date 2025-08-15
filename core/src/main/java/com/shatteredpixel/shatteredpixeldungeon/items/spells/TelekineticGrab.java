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

package com.shatteredpixel.shatteredpixeldungeon.items.spells;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.SPDSettings;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.PinCushion;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.DwarfKing;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.MagicMissile;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.items.Heap;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.LiquidMetal;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRecharging;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.SpiritBow;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;
import com.watabou.utils.PathFinder;

public class TelekineticGrab extends TargetedSpell {

	{
		image = ItemSpriteSheet.TELE_GRAB;

		talentChance = 1/(float)Recipe.OUT_QUANTITY;
	}

	@Override
	protected void fx(Ballistica bolt, Callback callback) {
		MagicMissile.boltFromChar( curUser.sprite.parent,
				MagicMissile.BEACON,
				curUser.sprite,
				bolt.collisionPos,
				callback);
		Sample.INSTANCE.play( Assets.Sounds.ZAP );
	}

	@Override
	protected void affectTarget(Ballistica bolt, Hero hero) {
		Char ch = Actor.findChar(bolt.collisionPos);

		//special logic for DK when he is on his throne
		if (ch == null && bolt.path.size() > bolt.dist+1){
			ch = Actor.findChar(bolt.path.get(bolt.dist+1));
			if (!(ch instanceof DwarfKing && Dungeon.level.solid[ch.pos])){
				ch = null;
			}
		}

		SpiritBow bow = Dungeon.hero.belongings.getItem(SpiritBow.class);
		if (ch != null && ch.buff(PinCushion.class) != null){

			while (ch.buff(PinCushion.class) != null) {
				Item item = ch.buff(PinCushion.class).grabOne();

				if (item.doPickUp(hero, ch.pos)) {
					hero.spend(-Item.TIME_TO_PICK_UP); //casting the spell already takes a turn
					GLog.i( Messages.capitalize(Messages.get(hero, "you_now_have", item.name())) );

				} else {
					GLog.w(Messages.get(this, "cant_grab"));
					Dungeon.level.drop(item, ch.pos).sprite.drop();
					return;
				}

			}

		} else if (	ch != null && ch == Dungeon.hero &&
					bow != null && SPDSettings.Polished.huntress()) {
			bow.Polished_resetCharges();
			updateQuickslot();
			
			ScrollOfRecharging.charge(curUser);
			Sample.INSTANCE.play( Assets.Sounds.CHARGEUP );

		} else {
			
			int pickedUp = 0;
			boolean cantGrab = false;
			boolean grabbedAdjacent = false;
			
			nearbyHeaps:
			for (int offset : PathFinder.NEIGHBOURS9) {
				Heap h = Dungeon.level.heaps.get(bolt.collisionPos + offset);
				if(h != null) {
					if (h.type != Heap.Type.HEAP){
						h.sprite.drop();
						cantGrab = true;
						continue;
					}
					
					while (!h.isEmpty()) {
						
						//avoid spamming loud pickup noises
						boolean oldVal = Sample.INSTANCE.isEnabled();
						Sample.INSTANCE.enable(false);
						
						Item item = h.peek();
						boolean successful = item.doPickUp(hero, h.pos);
						Sample.INSTANCE.enable(oldVal);
						
						if (successful) {
							h.pickUp();
							hero.spend(-Item.TIME_TO_PICK_UP); //casting the spell already takes a turn
							
							if(offset != 0) {
								CellEmitter.get(bolt.collisionPos + offset).start(Speck.factory(Speck.LIGHT), 0.2f, 3);
								grabbedAdjacent = true;
							}
							GLog.i( Messages.capitalize(Messages.get(hero, "you_now_have", item.name())) );
							pickedUp++;
							
						} else {
							h.sprite.drop();
							cantGrab = true;
							continue nearbyHeaps;
						}
					}
				}
			}
			
			if(pickedUp == 0) {
				GLog.w(Messages.get(this, cantGrab ? "cant_grab" : "no_target"));
			} else {
				Sample.INSTANCE.play(Assets.Sounds.ITEM);
				if(grabbedAdjacent) {
					for(int i = 0; i < Math.min(pickedUp, 3); i++) {
						Sample.INSTANCE.play(Assets.Sounds.TELEPORT);
					}
				}
			}
			
			/*if (Dungeon.level.heaps.get(bolt.collisionPos) != null){
				
				Heap h = Dungeon.level.heaps.get(bolt.collisionPos);
				
				if (h.type != Heap.Type.HEAP){
					GLog.w(Messages.get(this, "cant_grab"));
					h.sprite.drop();
					return;
				}
				
				while (!h.isEmpty()) {
					Item item = h.peek();
					if (item.doPickUp(hero, h.pos)) {
						h.pickUp();
						hero.spend(-Item.TIME_TO_PICK_UP); //casting the spell already takes a turn
						GLog.i( Messages.capitalize(Messages.get(hero, "you_now_have", item.name())) );
						
					} else {
						GLog.w(Messages.get(this, "cant_grab"));
						h.sprite.drop();
						return;
					}
				}
				
			} else {
				GLog.w(Messages.get(this, "no_target"));
			}*/
		}

	}
	
	@Override
	public String desc() {
		if (Dungeon.hero != null && Dungeon.hero.heroClass == HeroClass.HUNTRESS
			&& SPDSettings.Polished.huntress()) {
			return super.desc() + Messages.get(this, "huntress_desc");
		}
		else return super.desc();
	}
	
	@Override
	public int value() {
		return (int)(50 * (quantity/(float)Recipe.OUT_QUANTITY));
	}

	@Override
	public int energyVal() {
		return (int)(10 * (quantity/(float)Recipe.OUT_QUANTITY));
	}

	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {

		private static final int OUT_QUANTITY = 8;

		{
			inputs =  new Class[]{LiquidMetal.class};
			inQuantity = new int[]{10};

			cost = 10;

			output = TelekineticGrab.class;
			outQuantity = OUT_QUANTITY;
		}

	}

}
