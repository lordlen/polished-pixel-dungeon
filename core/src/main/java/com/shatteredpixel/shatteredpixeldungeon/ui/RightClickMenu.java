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

package com.shatteredpixel.shatteredpixeldungeon.ui;

import com.shatteredpixel.shatteredpixeldungeon.Chrome;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.items.Heap;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.Potion;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.Ring;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.Scroll;
import com.shatteredpixel.shatteredpixeldungeon.journal.Notes;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.watabou.input.PointerEvent;
import com.watabou.noosa.ColorBlock;
import com.watabou.noosa.Image;
import com.watabou.noosa.NinePatch;
import com.watabou.noosa.PointerArea;
import com.watabou.noosa.ui.Component;

import java.util.ArrayList;

public class RightClickMenu extends Component {

	private NinePatch bg;
	private PointerArea blocker;

	private Image icon;
	private RenderedTextBlock titleText;
	private ColorBlock separator;

	private RedButton[] buttons;

	private Item item;

	public RightClickMenu(Item item){
		ArrayList<String> actions = item.actions(Dungeon.hero);
		
		if (actions.remove(Item.AC_THROW)) {
			actions.add(actions.size(), Item.AC_THROW);
		}
		if (actions.remove(Item.AC_DROP)) {
			actions.add(actions.size(), Item.AC_DROP);
		}
		if (actions.remove(item.defaultAction())) {
			actions.add(0, item.defaultAction());
		}
		
		Notes.CustomRecord rec = Notes.findCustomRecord(item);
		if(Polished.notesAction(item)) {
			actions.add(0, rec == null ? Item.AC_NOTE : Item.AC_EDIT);
		}
		
		String[] options = actions.toArray(new String[0]);
		this.item = item;
		setup(new ItemSprite(item), rec == null ? Messages.titleCase(item.name()) : rec.title(), options);
	}

	public RightClickMenu(Image icon, String title, String... options){
		setup(icon, title, options);
	}

	private void setup(Image icon, String title, String... options){
		bg = Chrome.get(Chrome.Type.TOAST_TR_HEAVY);
		add(bg);

		this.icon = icon;
		add(icon);

		titleText = PixelScene.renderTextBlock(title, 6);
		titleText.maxWidth(50);
		titleText.hardlight(Window.TITLE_COLOR);
		add(titleText);

		separator = new ColorBlock(1, 1, 0xFF000000);
		add(separator);

		blocker = new PointerArea(0, 0, 0, 0){
			@Override
			public boolean onSignal(PointerEvent event) {
				boolean hit = event != null && target.overlapsScreenPoint( (int)event.current.x, (int)event.current.y );
				if (event != null && event.type == PointerEvent.Type.HOVER && !hit){
					RightClickMenu.this.destroy();
					RightClickMenu.this.killAndErase();
				} else if (hit){
					return true;
				}
				return false;
			}
		};
		blocker.target = bg;
		add(blocker);

		buttons = new RedButton[options.length];
		for (int i = 0; i < options.length; i++){
			int finalI = i;
			buttons[i] = new RedButton(options[finalI], 6){
				@Override
				protected void onClick() {
					super.onClick();
					if (item != null){
						item.execute(Dungeon.hero, options[finalI]);

						if (options[finalI].equals(item.defaultAction()) && item.usesTargeting){
							InventoryPane.useTargeting();
						}
					}
					onSelect(finalI);
					RightClickMenu.this.destroy();
					RightClickMenu.this.killAndErase();
				}
			};
			if (item != null){
				if (options[i].equals(item.defaultAction()) || options[i].equals(Item.AC_NOTE) || options[i].equals(Item.AC_EDIT)) {
					buttons[i].textColor(Window.TITLE_COLOR);
				}
				buttons[i].text(item.actionName(options[i], Dungeon.hero));
			}
			add(buttons[i]);
		}

	}

	public void onSelect(int index){}

	@Override
	protected void layout() {
		super.layout();

		height = 0;
		height += bg.marginVer();
		height += Math.max(icon.height(), titleText.height());
		height += 2;
		height += 13*buttons.length;

		width = icon.width + 2 + titleText.width()+bg.marginVer();
		for (RedButton button : buttons){
			if (width < button.reqWidth()+bg.marginHor()){
				width = button.reqWidth()+bg.marginHor();
			}
		}

		if (x + width > (camera.width + camera.scroll.x)){
			x -= (x + width - (camera.width + camera.scroll.x));
		}
		if (y + height > (camera.height + camera.scroll.y)){
			y -= (y + height - (camera.height + camera.scroll.y));
		}

		bg.x = x;
		bg.y = y;

		icon.x = x+bg.marginLeft();
		icon.y = y+bg.marginTop();

		titleText.setPos(icon.x+icon.width()+2, Math.max(icon.y + (icon.height()- titleText.height())/2, y+bg.marginTop()));

		separator.x = x+bg.marginLeft();
		separator.y = Math.max(icon.y + icon.height(), titleText.bottom()) + 1;
		separator.size(width - bg.marginHor(), 1);

		float top = separator.y + 2;
		for (RedButton button : buttons){
			button.setRect(x + bg.marginLeft(), top, width-bg.marginHor(), 12);
			top = button.bottom()+1;
		}

		bg.size(width, height);

	}
	
	public static class Polished {
		public static boolean notesAction(Item item) {
			return !item.isIdentified() && ( item instanceof Potion || item instanceof Scroll || item instanceof Ring );
		}
		
		public static boolean notesAction(Heap heap) {
			return (validForNotes(heap) && notesAction(heap.peek()));
		}
		
		public static boolean validForNotes(Heap heap) {
			return  heap != null && heap.peek() != null
					&& (heap.type == Heap.Type.HEAP || heap.type == Heap.Type.FOR_SALE);
		}
	}
	
}
