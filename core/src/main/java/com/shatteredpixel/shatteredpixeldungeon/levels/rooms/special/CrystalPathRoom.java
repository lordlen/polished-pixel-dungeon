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

package com.shatteredpixel.shatteredpixeldungeon.levels.rooms.special;

import com.shatteredpixel.shatteredpixeldungeon.Challenges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.RoomLighting;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.keys.CrystalKey;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfExperience;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic.ExoticPotion;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic.PotionOfDivineInspiration;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfTransmutation;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ExoticScroll;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfMetamorphosis;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.ExoticCrystals;
import com.shatteredpixel.shatteredpixeldungeon.levels.HallsLevel;
import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.levels.painters.Painter;
import com.shatteredpixel.shatteredpixeldungeon.levels.rooms.standard.EmptyRoom;
import com.watabou.utils.Point;
import com.watabou.utils.Random;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CrystalPathRoom extends SpecialRoom {
	
	static class Polished {
		static final int forceAdjacents = 2;
		static final boolean addLighting = false;
	}

	@Override
	public int minWidth() { return 7; }
	@Override
	public int minHeight() { return 7; }
	
	@Override
	public void paint(Level level) {

		Painter.fill( level, this, Terrain.WALL );

		//rooms are ordered from closest to furthest from the entrance
		EmptyRoom[] rooms = new EmptyRoom[6];
		for( int i=0; i<rooms.length; i++){
			rooms[i] = new EmptyRoom();
		}

		Point entry = new Point(entrance());

		int prize1 = 0, prize2 = 0;
		boolean horizontalPath;
		if (entry.x == left || entry.x == right){

			Painter.drawInside(level, this, entry, width() > 8 ? 5 : 3, Terrain.EMPTY);

			int roomW1 = width() >= 9 ? 2 : 1;
			int roomW2 = width() % 2 == 0 ? 2 : 1;
			int roomH = height() >= 9 ? 2 : 1;

			if (entry.x == left){
				rooms[0].setPos(left+1, entry.y-roomH-1).resize(roomW1-1, roomH-1);
				Painter.set(level, rooms[0].left, rooms[0].bottom+1, Terrain.CRYSTAL_DOOR);
				rooms[1].setPos(left+1, entry.y+2).resize(roomW1-1, roomH-1);
				Painter.set(level, rooms[1].left, rooms[1].top-1, Terrain.CRYSTAL_DOOR);

				rooms[2].setPos(rooms[1].right+2, entry.y-roomH-1).resize(roomW1-1, roomH-1);
				Painter.set(level, rooms[2].left, rooms[2].bottom+1, Terrain.CRYSTAL_DOOR);
				rooms[3].setPos(rooms[1].right+2, entry.y+2).resize(roomW1-1, roomH-1);
				Painter.set(level, rooms[3].left, rooms[3].top-1, Terrain.CRYSTAL_DOOR);

				rooms[4].setPos(rooms[3].right+2, entry.y-roomH-1).resize(roomW2-1, roomH);
				Painter.set(level, rooms[4].left-1, rooms[4].bottom-1, Terrain.CRYSTAL_DOOR);
				rooms[5].setPos(rooms[3].right+2, entry.y+1).resize(roomW2-1, roomH);
				Painter.set(level, rooms[5].left-1, rooms[5].top+1, Terrain.CRYSTAL_DOOR);

				prize1 = level.pointToCell(new Point(rooms[4].left, rooms[4].bottom));
				prize2 = level.pointToCell(new Point(rooms[5].left, rooms[5].top));
			} else {
				rooms[0].setPos(right-roomW1, entry.y-roomH-1).resize(roomW1-1, roomH-1);
				Painter.set(level, rooms[0].right, rooms[0].bottom+1, Terrain.CRYSTAL_DOOR);
				rooms[1].setPos(right-roomW1, entry.y+2).resize(roomW1-1, roomH-1);
				Painter.set(level, rooms[1].right, rooms[1].top-1, Terrain.CRYSTAL_DOOR);

				rooms[2].setPos(rooms[1].left-roomW1-1, entry.y-roomH-1).resize(roomW1-1, roomH-1);
				Painter.set(level, rooms[2].right, rooms[2].bottom+1, Terrain.CRYSTAL_DOOR);
				rooms[3].setPos(rooms[1].left-roomW1-1, entry.y+2).resize(roomW1-1, roomH-1);
				Painter.set(level, rooms[3].right, rooms[3].top-1, Terrain.CRYSTAL_DOOR);

				rooms[4].setPos(rooms[3].left-roomW2-1, entry.y-roomH-1).resize(roomW2-1, roomH);
				Painter.set(level, rooms[4].right+1, rooms[4].bottom-1, Terrain.CRYSTAL_DOOR);
				rooms[5].setPos(rooms[3].left-roomW2-1, entry.y+1).resize(roomW2-1, roomH);
				Painter.set(level, rooms[5].right+1, rooms[5].top+1, Terrain.CRYSTAL_DOOR);

				prize1 = level.pointToCell(new Point(rooms[4].right, rooms[4].bottom));
				prize2 = level.pointToCell(new Point(rooms[5].right, rooms[5].top));
			}
			
			horizontalPath = true;

		} else {
			Painter.drawInside(level, this, entry, height() > 8 ? 5 : 3, Terrain.EMPTY);

			int roomW = width() >= 9 ? 2 : 1;
			int roomH1 = height() >= 9 ? 2 : 1;
			int roomH2 = height() % 2 == 0 ? 2 : 1;

			if (entry.y == top){
				rooms[0].setPos(entry.x-roomW-1, top+1).resize(roomW-1, roomH1-1);
				Painter.set(level, rooms[0].right+1, rooms[0].top, Terrain.CRYSTAL_DOOR);
				rooms[1].setPos(entry.x+2, top+1).resize(roomW-1, roomH1-1);
				Painter.set(level, rooms[1].left-1, rooms[1].top, Terrain.CRYSTAL_DOOR);

				rooms[2].setPos(entry.x-roomW-1, rooms[1].bottom+2).resize(roomW-1, roomH1-1);
				Painter.set(level, rooms[2].right+1, rooms[2].top, Terrain.CRYSTAL_DOOR);
				rooms[3].setPos(entry.x+2,  rooms[1].bottom+2).resize(roomW-1, roomH1-1);
				Painter.set(level, rooms[3].left-1, rooms[3].top, Terrain.CRYSTAL_DOOR);

				rooms[4].setPos(entry.x-roomW-1, rooms[3].bottom+2).resize(roomW, roomH2-1);
				Painter.set(level, rooms[4].right-1, rooms[4].top-1, Terrain.CRYSTAL_DOOR);
				rooms[5].setPos(entry.x+1, rooms[3].bottom+2).resize(roomW, roomH2-1);
				Painter.set(level, rooms[5].left+1, rooms[5].top-1, Terrain.CRYSTAL_DOOR);

				prize1 = level.pointToCell(new Point(rooms[4].right, rooms[4].top));
				prize2 = level.pointToCell(new Point(rooms[5].left, rooms[5].top));
			} else {
				rooms[0].setPos(entry.x-roomW-1, bottom-roomH1).resize(roomW-1, roomH1-1);
				Painter.set(level, rooms[0].right+1, rooms[0].bottom, Terrain.CRYSTAL_DOOR);
				rooms[1].setPos(entry.x+2, bottom-roomH1).resize(roomW-1, roomH1-1);
				Painter.set(level, rooms[1].left-1, rooms[1].bottom, Terrain.CRYSTAL_DOOR);

				rooms[2].setPos(entry.x-roomW-1, rooms[1].top-roomH1-1).resize(roomW-1, roomH1-1);
				Painter.set(level, rooms[2].right+1, rooms[2].bottom, Terrain.CRYSTAL_DOOR);
				rooms[3].setPos(entry.x+2, rooms[1].top-roomH1-1).resize(roomW-1, roomH1-1);
				Painter.set(level, rooms[3].left-1, rooms[3].bottom, Terrain.CRYSTAL_DOOR);

				rooms[4].setPos(entry.x-roomW-1, rooms[3].top-roomH2-1).resize(roomW, roomH2-1);
				Painter.set(level, rooms[4].right-1, rooms[4].bottom+1, Terrain.CRYSTAL_DOOR);
				rooms[5].setPos(entry.x+1,  rooms[3].top-roomH2-1).resize(roomW, roomH2-1);
				Painter.set(level, rooms[5].left+1, rooms[5].bottom+1, Terrain.CRYSTAL_DOOR);

				prize1 = level.pointToCell(new Point(rooms[4].right, rooms[4].bottom));
				prize2 = level.pointToCell(new Point(rooms[5].left, rooms[5].bottom));
			}
			
			horizontalPath = false;

		}

		for (EmptyRoom room : rooms) {
			Painter.fill(level, room, Terrain.EMPTY_SP);
		}
		Painter.set(level, prize1, Terrain.PEDESTAL);
		Painter.set(level, prize2, Terrain.PEDESTAL);
		
		boolean nearsighted = Dungeon.isChallenged(Challenges.DARKNESS) || level instanceof HallsLevel;
		if(Polished.addLighting && nearsighted) {
			RoomLighting light = (RoomLighting) level.blobs.get( RoomLighting.class );
			if (light == null) {
				light = new RoomLighting();
			}
			for (int i=top + 1; i < bottom; i++) {
				for (int j=left + 1; j < right; j++) {
					light.seed( level, level.pointToCell(j, i), 1 );
				}
			}
			level.blobs.put(RoomLighting.class, light);
		}

		//random potion/scroll in rooms 1-4, with lower value ones going into earlier rooms
		ArrayList<Item> potions = new ArrayList<>();
		ArrayList<Item> scrolls = new ArrayList<>();

		ArrayList<Item> duplicates = new ArrayList<>();

		if (Random.Int(2) == 0){
			addRewardItem(Generator.Category.POTION, potions, duplicates);
			scrolls.add(Random.Float() < ExoticCrystals.consumableExoticChance()
					? new ScrollOfMetamorphosis() : new ScrollOfTransmutation());
		} else {
			potions.add(Random.Float() < ExoticCrystals.consumableExoticChance()
					? new PotionOfDivineInspiration() : new PotionOfExperience());
			addRewardItem(Generator.Category.SCROLL, scrolls, duplicates);
		}
		addRewardItem(Generator.Category.POTION, potions, duplicates);
		addRewardItem(Generator.Category.SCROLL, scrolls, duplicates);
		addRewardItem(Generator.Category.POTION, potions, duplicates);
		addRewardItem(Generator.Category.SCROLL, scrolls, duplicates);

		//need to undo the changes to spawn chances that the duplicates created
		for (Item i : duplicates){
			if (i instanceof ExoticPotion){
				Generator.undoDrop(ExoticPotion.exoToReg.get(i.getClass()));
			} else if (i instanceof ExoticScroll){
				Generator.undoDrop(ExoticScroll.exoToReg.get(i.getClass()));
			} else {
				Generator.undoDrop(i);
			}
		}

		//rarer potions/scrolls go later in the order
		Collections.sort(potions, new Comparator<Item>() {
			@Override
			public int compare(Item a, Item b) {
				int aVal = 0, bVal = 0;
				Class aCls = a.getClass(), bCls = b.getClass();
				if (a instanceof ExoticPotion){
					aCls = ExoticPotion.exoToReg.get(aCls);
				}
				if (b instanceof ExoticPotion){
					bCls = ExoticPotion.exoToReg.get(bCls);
				}
				for (int i = 0; i < Generator.Category.POTION.classes.length; i++){
					if (aCls == Generator.Category.POTION.classes[i]) aVal = (int)Generator.Category.POTION.defaultProbsTotal[i];
					if (bCls == Generator.Category.POTION.classes[i]) bVal = (int)Generator.Category.POTION.defaultProbsTotal[i];
				}
				return bVal - aVal;
			}
		});
		Collections.sort(scrolls, new Comparator<Item>() {
			@Override
			public int compare(Item a, Item b) {
				int aVal = 0, bVal = 0;
				Class aCls = a.getClass(), bCls = b.getClass();
				if (a instanceof ExoticScroll){
					aCls = ExoticScroll.exoToReg.get(aCls);
				}
				if (b instanceof ExoticScroll){
					bCls = ExoticScroll.exoToReg.get(bCls);
				}
				for (int i = 0; i < Generator.Category.SCROLL.classes.length; i++){
					if (aCls == Generator.Category.SCROLL.classes[i]) aVal = (int)Generator.Category.SCROLL.defaultProbsTotal[i];
					if (bCls == Generator.Category.SCROLL.classes[i]) bVal = (int)Generator.Category.SCROLL.defaultProbsTotal[i];
				}
				return bVal - aVal;
			}
		});
		
		int shuffle = Random.Int(2);
		
		Point p1, s1, p2, s2;
		boolean adj1, adj2, adj3, adj4;
		
		int adjacents;
		do {
			//least valuable items go into rooms 2&3, then rooms 0&1, then 4&5, which are reserved for prize 1&2
			p1 = rooms[shuffle == 1 ? 2 : 3].center();
			s1 = rooms[shuffle == 1 ? 3 : 2].center();
			
			p2 = rooms[shuffle == 1 ? 0 : 1].center();
			s2 = rooms[shuffle == 1 ? 1 : 0].center();
			
			
			adjacents = 0;
			adj1 = (horizontalPath ? Point.difference(p1, entry).y : Point.difference(p1, entry).x) <= 2;
			if(adj1) adjacents++;
			
			adj2 = (horizontalPath ? Point.difference(s1, entry).y : Point.difference(s1, entry).x) <= 2;
			if(adj2) adjacents++;
			
			adj3 = (horizontalPath ? Point.difference(p2, entry).y : Point.difference(p2, entry).x) <= 2;
			if(adj3) adjacents++;
			
			adj4 = (horizontalPath ? Point.difference(s2, entry).y : Point.difference(s2, entry).x) <= 2;
			if(adj4) adjacents++;
		}
		while(adjacents != Polished.forceAdjacents && (horizontalPath ? height() : width()) >= 9);
		
		boolean auto = nearsighted && !Polished.addLighting;
		level.drop(potions.remove(0), level.pointToCell(p1)).autoExplored = auto && !adj1;
		level.drop(scrolls.remove(0), level.pointToCell(s1)).autoExplored = auto && !adj2;

		level.drop(potions.remove(0), level.pointToCell(p2)).autoExplored = auto && !adj3;
		level.drop(scrolls.remove(0), level.pointToCell(s2)).autoExplored = auto && !adj4;

		//player can only see these if they unlock the previous doors, so never count them for exploration
		level.drop(potions.remove(0), shuffle == 1 ? prize1 : prize2).autoExplored = true;
		level.drop(scrolls.remove(0), shuffle == 1 ? prize2 : prize1).autoExplored = true;

		level.addItemToSpawn( new CrystalKey( Dungeon.depth ) );
		level.addItemToSpawn( new CrystalKey( Dungeon.depth ) );
		level.addItemToSpawn( new CrystalKey( Dungeon.depth ) );

		entrance().set( Door.Type.UNLOCKED );

	}

	//this prevents duplicates
	public void addRewardItem(Generator.Category cat, ArrayList<Item> items, ArrayList<Item> dupes){
		while (true) {
			Item reward = Generator.random(cat);

			//we have to de-exotify for comparison here to weed out duplicates
			Class rewardClass = reward.getClass();
			if (reward instanceof ExoticPotion){
				rewardClass = ExoticPotion.exoToReg.get(rewardClass);
			} else if (reward instanceof ExoticScroll){
				rewardClass = ExoticScroll.exoToReg.get(rewardClass);
			}

			boolean dupe = false;
			for (Item i : items){
				Class iClass = i.getClass();
				if (i instanceof ExoticPotion){
					iClass = ExoticPotion.exoToReg.get(iClass);
				} else if (i instanceof ExoticScroll){
					iClass = ExoticScroll.exoToReg.get(iClass);
				}
				if (iClass == rewardClass){
					dupes.add(reward);
					dupe = true;
					break;
				}
			}

			if (!dupe){
				items.add(reward);
				return;
			}
		}
	}

	@Override
	public boolean canConnect(Point p) {
		if (!super.canConnect(p)){
			return false;
		}
		//only place doors in the center
		if (Math.abs(p.x - (right - (width()-1)/2f)) < 1f){
			return true;
		}
		if (Math.abs(p.y - (bottom - (height()-1)/2f)) < 1f){
			return true;
		}
		return false;
	}

	@Override
	public boolean canPlaceGrass(Point p) {
		return false;
	}

	@Override
	public boolean canPlaceWater(Point p) {
		return false;
	}

	@Override
	public boolean canPlaceTrap(Point p) {
		return false;
	}
}
