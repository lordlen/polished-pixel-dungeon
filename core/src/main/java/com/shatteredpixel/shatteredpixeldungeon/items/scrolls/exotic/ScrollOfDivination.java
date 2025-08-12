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

package com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.effects.Identification;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.Potion;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.Ring;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.Scroll;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.ui.IconButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.RedButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.RenderedTextBlock;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.windows.IconTitle;
import com.watabou.noosa.Image;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;
import com.watabou.utils.Reflection;

import java.util.ArrayList;
import java.util.HashSet;

public class ScrollOfDivination extends ExoticScroll {
	
	{
		icon = ItemSpriteSheet.Icons.SCROLL_DIVINATE;
	}
	
	@Override
	public void doRead() {
		//no need for identifiedByUse logic, since ID scrolls are always known from the start
		GameScene.show(new WndScry());
	}
	
	void onRead() {
		
		ArrayList<Item> IDed = new ArrayList<>();
		if(curPick != null) {
			Item item = Reflection.newInstance(curPick);
			item.identify();
			IDed.add(item);
			curPick = null;
		}
		
		HashSet<Class<? extends Potion>> potions = Potion.getUnknown();
		HashSet<Class<? extends Scroll>> scrolls = Scroll.getUnknown();
		HashSet<Class<? extends Ring>> rings = Ring.getUnknown();
		
		if(!potions.isEmpty()) {
			Potion p = Reflection.newInstance(Random.element(potions));
			p.identify();
			IDed.add(p);
		}
		if(!scrolls.isEmpty()) {
			Scroll s = Reflection.newInstance(Random.element(scrolls));
			s.identify();
			IDed.add(s);
		}
		if(!rings.isEmpty()) {
			Ring r = Reflection.newInstance(Random.element(rings));
			r.identify();
			IDed.add(r);
		}
		
		readAnimation();
		curUser.sprite.parent.add( new Identification( curUser.sprite.center().offset( 0, -16 ) ) );
		Sample.INSTANCE.play( Assets.Sounds.READ );
		
		if(IDed.isEmpty()) {
			GLog.i( Messages.get(this, "nothing_left") );
		} else {
			identify();
			curItem.detach(curUser.belongings.backpack);
			GameScene.show(new WndDivination(IDed));
		}
		
	}
	
	private static Class<? extends Item> curPick = null;
	public class WndScry extends Window {
		
		private static final int WIDTH = 120;
		private static final int HEIGHT = 185;
		private static final int BTN_SIZE = 20;
		
		private int placedTotal = 0;
		
		public WndScry(){
			
			IconTitle titlebar = new IconTitle();
			titlebar.icon( new ItemSprite(ScrollOfDivination.this) );
			titlebar.label( Messages.titleCase(Messages.get(ScrollOfDivination.class, "name")) );
			titlebar.setRect( 0, 0, WIDTH, 0 );
			add( titlebar );
			
			RenderedTextBlock text = PixelScene.renderTextBlock(6);
			text.text( Messages.get(this, "desc") );
			text.setPos(0, titlebar.bottom()+4);
			text.maxWidth( WIDTH );
			add(text);
			
			final RedButton btnScry = new RedButton(""){
				@Override
				protected void onClick() {
					ScrollOfDivination.this.onRead();
					hide();
				}
				
			};
			btnScry.visible = false;
			btnScry.enable(false);
			btnScry.setRect(0, HEIGHT-20, WIDTH, 20);
			add(btnScry);
			
			float top = text.bottom() + 5;
			top = addSet(new ArrayList<>(Potion.getUnknown()), btnScry, top);
			top += BTN_SIZE + 5;
			top = addSet(new ArrayList<>(Scroll.getUnknown()), btnScry, top);
			top += BTN_SIZE + 5;
			top = addSet(new ArrayList<>(Ring.getUnknown()), btnScry, top);
			
			resize(WIDTH, HEIGHT);
			
			if(placedTotal == 0) {
				ScrollOfDivination.this.onRead();
				hide();
			}
			
		}
		
		float addSet(final ArrayList<Class<? extends Item>> unIDed, final RedButton btnScry, float top) {
			float left;
			int rows;
			int placed = 0;
			
			if (unIDed.size() <= 5){
				rows = 1;
				top += BTN_SIZE/2f;
				left = (WIDTH - BTN_SIZE*unIDed.size())/2f;
			} else {
				rows = 2;
				left = (WIDTH - BTN_SIZE*((unIDed.size()+1)/2))/2f;
			}
			
			for (final Class<?extends Item> i : unIDed){
				Image itemIcon = new Image(Assets.Sprites.ITEM_ICONS);
				itemIcon.frame(ItemSpriteSheet.Icons.film.get(Reflection.newInstance(i).icon));
				itemIcon.scale.set(2f);
				
				IconButton btn = new IconButton(){
					@Override
					protected void onClick() {
						curPick = i;
						btnScry.visible = true;
						btnScry.text( Messages.titleCase(Messages.get(curPick, "name")) );
						btnScry.icon(new Image(icon));
						btnScry.enable(true);
						super.onClick();
					}
				};
				
				btn.icon(itemIcon);
				btn.setRect(left + placed*BTN_SIZE, top, BTN_SIZE, BTN_SIZE);
				add(btn);
				
				placed++;
				if (rows == 2 && placed == ((unIDed.size()+1)/2)){
					rows--;
					placed = 0;
					if (unIDed.size() % 2 == 1){
						left += BTN_SIZE/2f;
					}
					top += BTN_SIZE;
				}
			}
			
			placedTotal += placed;
			return top;
		}
	}
	
	private class WndDivination extends Window {
		
		private static final int WIDTH = 120;
		
		WndDivination(ArrayList<Item> IDed ){
			IconTitle cur = new IconTitle(new ItemSprite(ScrollOfDivination.this),
					Messages.titleCase(Messages.get(ScrollOfDivination.class, "name")));
			cur.setRect(0, 0, WIDTH, 0);
			add(cur);
			
			RenderedTextBlock msg = PixelScene.renderTextBlock(Messages.get(this, "desc"), 6);
			msg.maxWidth(120);
			msg.setPos(0, cur.bottom() + 2);
			add(msg);
			
			float pos = msg.bottom() + 5;
			
			for (Item i : IDed){
				
				cur = new IconTitle(i);
				cur.setRect(0, pos, WIDTH, 0);
				add(cur);
				pos = cur.bottom() + 2;
				
			}
			
			resize(WIDTH, (int)pos);
		}
		
	}
	
}
