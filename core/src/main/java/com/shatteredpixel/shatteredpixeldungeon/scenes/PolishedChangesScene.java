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

package com.shatteredpixel.shatteredpixeldungeon.scenes;

import com.shatteredpixel.shatteredpixeldungeon.Chrome;
import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.ui.Icons;
import com.shatteredpixel.shatteredpixeldungeon.ui.ScrollPane;
import com.shatteredpixel.shatteredpixeldungeon.ui.StyledButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.changelist.ChangeInfo;
import com.shatteredpixel.shatteredpixeldungeon.ui.changelist.PPD.v1_0_X_PPDChanges;
import com.shatteredpixel.shatteredpixeldungeon.ui.changelist.v1_X_Changes;
import com.shatteredpixel.shatteredpixeldungeon.ui.changelist.v2_X_Changes;
import com.watabou.noosa.Image;

import java.util.ArrayList;

public class PolishedChangesScene extends ChangesScene {
	
	@Override
	protected void createChangesList(final ArrayList<ChangeInfo> changeInfos) {
		switch (changesSelected){
			case 0: default:
				v1_0_X_PPDChanges.add_v1_5_Changes(changeInfos);
				break;
			case 1:
				v1_0_X_PPDChanges.add_v1_4_Changes(changeInfos);
				v1_0_X_PPDChanges.add_v1_3_Changes(changeInfos);
				v1_0_X_PPDChanges.add_v1_2_Changes(changeInfos);
				v1_0_X_PPDChanges.add_v1_1_Changes(changeInfos);
				v1_0_X_PPDChanges.add_v1_0_Changes(changeInfos);
				break;
		}
	}
	
	@Override
	protected void createVersionButtons(ScrollPane list) {
		StyledButton v1_5 = new StyledButton(Chrome.Type.GREY_BUTTON_TR, "1.5", 8){
			@Override
			protected void onClick() {
				super.onClick();
				if (changesSelected != 0) {
					changesSelected = 0;
					ShatteredPixelDungeon.seamlessResetScene();
				}
			}
		};
		if (changesSelected != 01) v1_5.textColor( 0xBBBBBB );
		v1_5.setRect(list.left()-4f, list.bottom(), 19, changesSelected == 0 ? 19 : 15);
		addToBack(v1_5);
		
		StyledButton pre_v1_4 = new StyledButton(Chrome.Type.GREY_BUTTON_TR, "1.4-", 8){
			@Override
			protected void onClick() {
				super.onClick();
				if (changesSelected != 1) {
					changesSelected = 1;
					ShatteredPixelDungeon.seamlessResetScene();
				}
			}
		};
		if (changesSelected != 1) pre_v1_4.textColor( 0xBBBBBB );
		pre_v1_4.setRect(v1_5.right()-2, list.bottom(), 22, changesSelected == 1 ? 19 : 15);
		addToBack(pre_v1_4);
		
		
		StyledButton PPD = new StyledButton(Chrome.Type.GREY_BUTTON_TR, "", 8) {};
		PPD.icon(Icons.PPD.get());
		PPD.setRect(list.right() - 20, list.bottom(), 22, 22);
		
		StyledButton SPD = new StyledButton(Chrome.Type.GREY_BUTTON_TR, "", 8) {
			@Override
			protected void onClick() {
				super.onClick();
				
				changesSelected = 0;
				ShatteredPixelDungeon.switchNoFade( ChangesScene.class );
			}
		};
		Image icon = Icons.SHPX.get();
		icon.scale.set(PixelScene.align(0.75f));
		SPD.icon(icon);
		SPD.setRect(PPD.left() - 19, list.bottom(), 22, 19);
		
		addToBack(PPD);
		addToBack(SPD);
	}
}