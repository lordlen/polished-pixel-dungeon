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

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Blob;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Web;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.AscensionChallenge;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Dread;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Poison;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Terror;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.food.MysteryMeat;
import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.SpinnerSprite;
import com.watabou.utils.Bundle;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public class Spinner extends Mob {

	{
		spriteClass = SpinnerSprite.class;

		HP = HT = 50;
		defenseSkill = 17;

		EXP = 9;
		maxLvl = 17;

		loot = MysteryMeat.class;
		lootChance = 0.167f;

		HUNTING = new Hunting();
		FLEEING = new Fleeing();
	}

	@Override
	public float lootChance() {
		if(this instanceof FungalSpinner) 	return super.lootChance();
		else 								return super.lootChance() * ((6f - Dungeon.LimitedDrops.SPINNER_MEAT.count) / 6f);
	}

	@Override
	public Item createLoot(){
		Dungeon.LimitedDrops.SPINNER_MEAT.count++;
		return super.createLoot();
	}

	@Override
	public int damageRoll() {
		return Random.NormalIntRange(10, 20);
	}

	@Override
	public int attackSkill(Char target) {
		return 22;
	}

	@Override
	public int drRoll() {
		return super.drRoll() + Random.NormalIntRange(0, 6);
	}

	private int webCoolDown = 0;
	private int lastEnemyPos = -1;

	private static final String WEB_COOLDOWN = "web_cooldown";
	private static final String LAST_ENEMY_POS = "last_enemy_pos";

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(WEB_COOLDOWN, webCoolDown);
		bundle.put(LAST_ENEMY_POS, lastEnemyPos);
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		webCoolDown = bundle.getInt( WEB_COOLDOWN );
		lastEnemyPos = bundle.getInt( LAST_ENEMY_POS );
	}
	
	@Override
	protected boolean act() {
		if (state == HUNTING || state == FLEEING){
			webCoolDown--;
		}

		AiState lastState = state;
		boolean result = super.act();

		//We only want to update target position once per turn, so if switched from wandering, wait for a moment
		//Also want to avoid updating when we visually shot a web this turn (don't want to change the position)
		if (!(lastState == WANDERING && state == HUNTING)) {
			if (!shotWebVisually){
				if (enemy != null && enemySeen) {
					lastEnemyPos = enemy.pos;
				} else {
					lastEnemyPos = Dungeon.hero.pos;
				}
			}
			shotWebVisually = false;
		}
		
		return result;
	}

	@Override
	public int attackProc(Char enemy, int damage) {
		damage = super.attackProc( enemy, damage );
		if (Random.Int(2) == 0) {
			int duration = Random.IntRange(7, 8);
			//we only use half the ascension modifier here as total poison dmg doesn't scale linearly
			duration = Math.round(duration * (AscensionChallenge.statModifier(this)/2f + 0.5f));
			Buff.affect(enemy, Poison.class).set(duration);
			webCoolDown = 0;
			state = FLEEING;
		}

		return damage;
	}
	
	private boolean shotWebVisually = false;

	public int webPos(){

		Char enemy = this.enemy;
		if (enemy == null) return -1;

		//don't web a non-moving enemy that we're going to attack
		if (state != FLEEING && enemy.pos == lastEnemyPos && canAttack(enemy)){
			return -1;
		}
		
		Ballistica b;
		//aims web in direction enemy is moving, or between self and enemy if they aren't moving
		if (lastEnemyPos == enemy.pos){
			b = new Ballistica( enemy.pos, pos, Ballistica.WONT_STOP );
		} else {
			b = new Ballistica( lastEnemyPos, enemy.pos, Ballistica.WONT_STOP );
		}
		
		int collisionIndex = 0;
		for (int i = 0; i < b.path.size(); i++){
			if (b.path.get(i) == enemy.pos){
				collisionIndex = i;
				break;
			}
		}

		//in case target is at the edge of the map and there are no more cells in the path
		if (b.path.size() <= collisionIndex+1){
			return -1;
		}

		int webPos = b.path.get( collisionIndex+1 );

		//ensure we aren't shooting the web through walls
		int projectilePos = new Ballistica( pos, webPos, Ballistica.STOP_TARGET | Ballistica.STOP_SOLID).collisionPos;
		
		if (webPos != enemy.pos && projectilePos == webPos && Dungeon.level.passable[webPos]){
			return webPos;
		} else {
			return -1;
		}
		
	}
	
	public void shootWeb(){
		int webPos = webPos();
		if (webPos != -1){
			int i;
			for ( i = 0; i < PathFinder.CIRCLE8.length; i++){
				if ((enemy.pos + PathFinder.CIRCLE8[i]) == webPos){
					break;
				}
			}
			
			//spread to the tile hero was moving towards and the two adjacent ones
			int leftPos = enemy.pos + PathFinder.CIRCLE8[left(i)];
			int rightPos = enemy.pos + PathFinder.CIRCLE8[right(i)];
			
			if (Dungeon.level.passable[leftPos]) applyWebToCell(leftPos);
			if (Dungeon.level.passable[webPos])  applyWebToCell(webPos);
			if (Dungeon.level.passable[rightPos])applyWebToCell(rightPos);
			
			webCoolDown = 10;

			if (Dungeon.level.heroFOV[enemy.pos]){
				Dungeon.hero.interrupt();
			}
		}
		next();
	}

	protected void applyWebToCell(int cell){
		GameScene.add(Blob.seed(cell, 20, Web.class));
	}

	@Override
	protected boolean cellIsPathable( int cell ) {
		Level level = Dungeon.level;
		boolean oldval = level.openSpace[cell];

		if (level.solid[cell] && Blob.volumeAt(cell, Web.class) == 0){
			level.openSpace[cell] = false;
		} else {
			for (int i = 1; i < PathFinder.CIRCLE8.length; i += 2){
				if (level.solid[cell+PathFinder.CIRCLE8[i]]
					&& Blob.volumeAt(cell+PathFinder.CIRCLE8[i], Web.class) == 0) {

					level.openSpace[cell] = false;
				}
				else if(( !level.solid[cell+PathFinder.CIRCLE8[(i+1)%8]] || Blob.volumeAt(cell+PathFinder.CIRCLE8[(i+1)%8], Web.class) > 0 )
					 && ( !level.solid[cell+PathFinder.CIRCLE8[(i+2)%8]] || Blob.volumeAt(cell+PathFinder.CIRCLE8[(i+2)%8], Web.class) > 0 )) {

					level.openSpace[cell] = true;
					break;
				}
			}
		}

		boolean temp = super.cellIsPathable(cell);
		level.openSpace[cell] = oldval;
		return temp;
	}

	private int left(int direction){
		return direction == 0 ? 7 : direction-1;
	}
	
	private int right(int direction){
		return direction == 7 ? 0 : direction+1;
	}

	{
		resistances.add(Poison.class);
	}
	
	{
		immunities.add(Web.class);
	}

	private class Hunting extends Mob.Hunting {

		@Override
		public boolean act(boolean enemyInFOV, boolean justAlerted) {
			if (enemyInFOV && webCoolDown <= 0 && lastEnemyPos != -1){
				if (webPos() != -1){
					if (sprite != null && (sprite.visible || enemy.sprite.visible)) {
						sprite.zap( webPos() );
						shotWebVisually = true;
						return false;
					} else {
						shootWeb();
						return true;
					}
				}
			}

			return super.act(enemyInFOV, justAlerted);
		}
	}

	private class Fleeing extends Mob.Fleeing {

		@Override
		public boolean act(boolean enemyInFOV, boolean justAlerted) {
			if (buff( Terror.class ) == null && buff( Dread.class ) == null &&
					enemyInFOV && enemy.buff( Poison.class ) == null){
				state = HUNTING;
				return true;
			}

			if (enemyInFOV && webCoolDown <= 0 && lastEnemyPos != -1){
				if (webPos() != -1){
					if (sprite != null && (sprite.visible || enemy.sprite.visible)) {
						sprite.zap( webPos() );
						shotWebVisually = true;
						return false;
					} else {
						shootWeb();
						return true;
					}
				}
			}
			return super.act(enemyInFOV, justAlerted);
		}

	}
}
