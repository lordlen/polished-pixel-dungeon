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

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.AllyBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Amok;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Dread;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Terror;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.cleric.PowerOfMany;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.huntress.SpiritHawk;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.rogue.ShadowClone;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.Stasis;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.effects.AllyPath;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.effects.TargetedCell;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.ShaftParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.DriedRose;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfTeleportation;
import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.CellSelector;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.tiles.DungeonTilemap;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.BArray;
import com.watabou.utils.Bundle;
import com.watabou.utils.Callback;
import com.watabou.utils.DeviceCompat;
import com.watabou.utils.PathFinder;

import java.util.ArrayList;

public class DirectableAlly extends NPC {

	{
		alignment = Char.Alignment.ALLY;
		intelligentAlly = true;
		
		WANDERING = new Wandering();
		HUNTING = new Hunting();
		state = WANDERING;

		//right after hero
		actPriority = HERO_PRIO-1;
		//we share our vision with hero
		viewDistance = 0;
		
		immunities.add( Terror.class );
		immunities.add( Dread.class );
		immunities.add( Amok.class );
		immunities.add( AllyBuff.class );
	}
	
	protected boolean auto = false;
	@Override
	protected Char chooseEnemy() {
		if(!auto) {
			//don't choose targets by itself
			return enemy;
		}
		else return super.chooseEnemy();
	}
	
	@Override
	public void aggro(Char ch) {
		//if(!auto) return;
		if(state == PASSIVE) return;
		
		enemy = ch;
		if(enemy != null) {
			state = HUNTING;
			command = Command.ATTACK;
			commandPos = enemy.pos;
		}
		else {
			if(state == HUNTING) {
				state = WANDERING;
			}
			if(command == Command.ATTACK) {
				command = Command.DEFEND;
			}
		}
		
		updateTarget();
	}
	
	protected boolean defendAnnounced, attackAnnounced, followAnnounced, darkAnnounced = false;
	protected void announce() {
		//do nothing by default
	}
	
	protected void onSummon() {
		defaultCommand(true);
	}
	
	protected enum Command {
		NONE,
		DEFEND,
		ATTACK,
		FOLLOW
	}
	protected Command command = Command.NONE;
	protected int commandPos = -1;
	
	public void command() {
		if(stasis()) return;
		
		if(GameScene.Polished.quickslotKeyPress && DeviceCompat.isDesktop()) {
			int cell = GameScene.Polished.getSelectedCell();
			if(cell != -1) {
				commandTo(cell);
			}
			else {
				GameScene.selectCell(commander);
			}
		}
		else {
			GameScene.selectCell(commander);
		}
	}
	public CellSelector.Listener commander = new CellSelector.Listener(){
		@Override
		public void onSelect(Integer cell) {
			commandTo(cell);
		}
		
		@Override
		public String prompt() {
			return Messages.get(DirectableAlly.class, "command_prompt");
		}
	};
	
	public void commandTo(Integer cell) {
		if(cell == null || cell == -1) return;
		cantReach = 0;
		
		if(Dungeon.level.fogEdge[cell] || Dungeon.level.mapped[cell]) {
			Char ch = Actor.findChar(cell);
			
			
			/*if(CHAIN(cell)) {
			
			}
			else*/
			
			
			if (!Dungeon.level.heroFOV[cell] || ch == null ||
				(ch != Dungeon.hero && ch.alignment != Char.Alignment.ENEMY))
			{
				defendPos( cell );
			}
			else if (ch == Dungeon.hero) {
				followHero();
			} else {
				targetChar(ch);
			}
			
			drawPath();
		}
		else {
			clearState();
			erasePath();
		}
		
		announce();
	}
	
	public static boolean CHAINING = false;
	//boolean TEST = false;
	/*public static ArrayList<Integer> chainedCommands = new ArrayList<>();
	boolean CHAIN(int cell) {
		if(CHAINING) {
			chainedCommands.add(cell);
			return true;
		}
		
		return false;
	}*/
	
	protected void defendPos( int cell ){
		command = Command.DEFEND;
		commandPos = cell;
		commandAggro(null);
		
		calculatePath();
	}
	
	protected void targetChar( Char ch ){
		command = Command.ATTACK;
		commandPos = ch.pos;
		commandAggro(ch);
		
		calculatePath();
	}
	
	public void followHero(){
		command = Command.FOLLOW;
		commandPos = Dungeon.hero.pos;
		commandAggro(null);
		
		calculatePath();
	}
	
	public void clearState() {
		command = Command.NONE;
		commandPos = -1;
		commandAggro(null);
		
		calculatePath();
	}
	
	void commandAggro(Char ch) {
		enemy = ch;
		
		if(enemy != null) {
			state = HUNTING;
		}
		else if(state == HUNTING) {
			state = WANDERING;
		}
	}
	
	protected void defaultCommand(boolean onSpawn) {
		Mob toAttack = null;
		for(Mob mob : Dungeon.hero.getVisibleEnemies()) {
			if (distance(mob) <= 3 &&
				(toAttack == null || trueDistance(toAttack) > trueDistance(mob)))
			{
				toAttack = mob;
			}
		}
		
		if(toAttack != null) {
			if(auto) {
				targetChar(toAttack);
			}
			else {
				defendPos(pos);
			}
		}
		else {
			if(onSpawn) {
				followHero();
			}
			else {
				defendPos(pos);
			}
		}
	}
	
	void updateTarget() {
		if(command == Command.ATTACK && (enemy == null || !enemy.isAlive())) {
			defaultCommand(false);
		}
		
		switch (command) {
			case DEFEND:
				break;
			case ATTACK:
				commandPos = enemy.pos;
				break;
			case FOLLOW:
				commandPos = Dungeon.hero.pos;
				break;
			case NONE: default:
				commandPos = -1;
				break;
		}
		
		target = commandPos;
	}
	
	public void calculatePath() {
		target = commandPos;
		if(target == -1 || target == pos) {
			path = null;
			return;
		}
		
		Level level = Dungeon.level;
		if(fieldOfView == null || fieldOfView.length != level.length()) {
			fieldOfView = new boolean[level.length()];
			level.updateFieldOfView(this, fieldOfView);
		}
		
		path = Dungeon.findPath(this, target, level.passable, fieldOfView, false);
		
		if(path == null || path.isEmpty()) {
			clearState();
		}
		
	}
	
	@Override
	public boolean[] modifyPassable(boolean[] passable) {
		Level level = Dungeon.level;
		boolean[] f = level.fogEdge;
		boolean[] m = level.mapped;
		
		for (int i = 0; i < level.length(); i++) {
			passable[i] = passable[i] && (f[i] || m[i]);
		}
		return passable;
	}
	
	public void updatePath() {
		updateTarget();
		
		if(path == null) {
			calculatePath();
		}
		else if (path.getLast() != target) {
			//if the new target is adjacent to the end of the path, adjust for that
			//rather than scrapping the whole path.
			if (Dungeon.level.adjacent(target, path.getLast())) {
				int last = path.removeLast();
				
				if (path.isEmpty()) {
					
					//shorten for a closer one
					if (Dungeon.level.adjacent(target, pos)) {
						path.add(target);
						//extend the path for a further target
					} else {
						path.add(last);
						path.add(target);
					}
					
				} else {
					//if the new target is simply 1 earlier in the path shorten the path
					if (path.getLast() == target) {
						
						//if the new target is closer/same, need to modify end of path
					} else if (Dungeon.level.adjacent(target, path.getLast())) {
						path.add(target);
						
						//if the new target is further away, need to extend the path
					} else {
						path.add(last);
						path.add(target);
					}
				}
				
			}
			else {
				calculatePath();
			}
			
		}
	}
	
	ArrayList<AllyPath> pathDisplay = new ArrayList<>();
	public void drawPath() {
		erasePath();
		
		/*if(!chainedCommands.isEmpty()) {
			if(path == null || pos == commandPos) {
				commandPos = chainedCommands.get(0);
				target = commandPos;
				chainedCommands.remove(0);
				
				calculatePath();
			}
		}*/
		
		if(path == null) return;
		
		int start = pos;
		int end = pos;
		int lastDirection = -1;
		
		for(Integer step : path) {
			int direction = -1;
			for (int i : PathFinder.NEIGHBOURS8) {
				if(step - end == i) {
					direction = i;
					break;
				}
			}
			
			if(direction == lastDirection || start == end) {
				end = step;
			}
			else {
				drawStep(start, end);
				
				start = end;
				end = step;
			}
			lastDirection = direction;
		}
		if(start != end) {
			drawStep(start, end);
		}
		
		
		/*if(!chainedCommands.isEmpty()) {
			TEST = true;
			for(int cell : chainedCommands) {
				drawStep(end, cell);
				end = cell;
			}
			TEST = false;
		}*/
	}
	
	void drawStep(int from, int to) {
		int color;
		switch (command) {
			case DEFEND: default:
				color = AllyPath.DEFEND;
				break;
			case ATTACK:
				color = AllyPath.ATTACK;
				break;
			case FOLLOW:
				color = AllyPath.FOLLOW;
				break;
		}
		
		/*if(TEST) {
			color = AllyPath.CHAIN;
		}*/
		
		AllyPath path = new AllyPath(
				DungeonTilemap.tileCenterToWorld(from),
				DungeonTilemap.tileCenterToWorld(to),
				color);
		
		pathDisplay.add(path);
	}
	
	public void erasePath() {
		for (AllyPath step : pathDisplay) {
			step.killAndErase();
		}
		pathDisplay.clear();
	}
	
	@Override
	public void die(Object cause) {
		super.die(cause);
		erasePath();
	}
	
	public static boolean observing = false;
	public static void observeAll() {
		
		observing = true;
		
		if(DriedRose.Ghost() != null) {
			DriedRose.Ghost().observe();
		}
		if(SpiritHawk.Hawk() != null) {
			SpiritHawk.Hawk().observe();
		}
		if(ShadowClone.Shadow() != null) {
			ShadowClone.Shadow().observe();
		}
		
		Char ally = PowerOfMany.PoweredAlly();
		if(ally != null) {
			if(ally instanceof PowerOfMany.LightAlly) {
				((PowerOfMany.LightAlly) ally).observe();
			}
			
			//have to do it manually
			else {
				if(ally != Stasis.getStasisAlly()) {
					Level level = Dungeon.level;
					if(ally.fieldOfView == null || ally.fieldOfView.length != level.length()) {
						ally.fieldOfView = new boolean[level.length()];
					}
					level.updateFieldOfView(ally, ally.fieldOfView);
				}
			}
			
		}
		
		observing = false;
		
	}
	
	protected void observe() {
		if(!stasis()) {
			Level level = Dungeon.level;
			if(fieldOfView == null || fieldOfView.length != level.length()) {
				fieldOfView = new boolean[level.length()];
			}
			level.updateFieldOfView(this, fieldOfView);
			
			Dungeon.Polished.runDelayed(this::afterObserve);
		}
	}
	
	protected void afterObserve() {
		Level level = Dungeon.level;
		if(fieldOfView == null || fieldOfView.length != level.length()) {
			fieldOfView = new boolean[level.length()];
		}
		
		System.arraycopy(
				level.heroFOV, 0,
				fieldOfView, 0,
				level.length());
	}
	
	public boolean stasis() {
		return this == Stasis.getStasisAlly();
	}
	
	public static class SummonSelector {
		static DirectableAlly toSummon;
		static ArrayList<Integer> candidates;
		
		static Callback onFinish;
		
		public static void trySummon(DirectableAlly ally) {
			trySummon(ally, null);
		}
		
		public static void trySummon(DirectableAlly ally, Callback callback) {
			toSummon = ally;
			candidates = new ArrayList<>();
			onFinish = callback;
			
			boolean[] valid = validTiles(ally);
			
			for (int offset : PathFinder.NEIGHBOURS25) {
				int cell = Dungeon.hero.pos + offset;
				if(valid[cell]) {
					candidates.add(cell);
					
					TargetedCell targeted = new TargetedCell(cell, TargetedCell.YELLOW) {
						@Override
						protected boolean startFade() {
							return !GameScene.Polished.isListenerActive(summoner);
						}
					};
					
					Dungeon.hero.sprite.parent.add(targeted);
				}
			}
			
			if(!candidates.isEmpty()) {
				GameScene.selectCell(summoner);
			}
			else {
				GLog.n(Messages.get(DirectableAlly.class, "no_space"));
				toSummon = null;
				candidates.clear();
				onFinish = null;
			}
		}
		
		public static void summon(DirectableAlly ally, int pos) {
			ally.pos = pos;
			GameScene.add(ally, 1f);
			Dungeon.level.occupyCell(ally);
			Dungeon.observe();
			
			CellEmitter.get(ally.pos).start( ShaftParticle.FACTORY, 0.3f, 4 );
			CellEmitter.get(ally.pos).start( Speck.factory(Speck.LIGHT), 0.2f, 3 );
			ScrollOfTeleportation.appear(ally, pos);
			Sample.INSTANCE.play(Assets.Sounds.TELEPORT);
			
			Hero hero = Dungeon.hero;
			hero.spend(1f);
			hero.busy();
			hero.sprite.operate(hero.pos);
			
			ally.onSummon();
		}
		
		static boolean[] validTiles(Char ch) {
			Level level = Dungeon.level;
			boolean[] valid = BArray.or( level.passable, level.avoid, null );
			
			boolean[] v = level.visited;
			boolean[] m = level.mapped;
			for (int i = 0; i < level.length(); i++) {
				valid[i] = valid[i] && (v[i] || m[i]);
			}
			
			if (!ch.flying) {
				BArray.and( valid, BArray.not(level.pit, null), valid );
			}
			
			if (Char.hasProp(ch, Char.Property.LARGE)){
				BArray.and( valid, level.openSpace, valid );
			}
			
			for (Char c : Actor.chars()) {
				if(level.heroFOV[c.pos]) {
					valid[c.pos] = false;
				}
			}
			
			return valid;
		}
		
		private static final CellSelector.Listener summoner = new CellSelector.Listener(){
			
			@Override
			public void onSelect(Integer cell) {
				if(candidates.contains(cell)) {
					Char ch = Actor.findChar(cell);
					if(ch == null) {
						
						summon(toSummon, cell);
						if(onFinish != null) onFinish.call();
						
					}
				}
				
				toSummon = null;
				candidates.clear();
				onFinish = null;
			}
			
			@Override
			public String prompt() {
				return Messages.get(DirectableAlly.class, "spawn_prompt");
			}
		};
		
	}
	
	private static final String COMMAND = "command";
	private static final String COMMAND_POS = "command_pos";
	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(COMMAND, command);
		bundle.put(COMMAND_POS, commandPos);
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		if (bundle.contains(COMMAND)) command = bundle.getEnum(COMMAND, Command.class);
		if (bundle.contains(COMMAND_POS)) commandPos = bundle.getInt(COMMAND_POS);
	}
	
	
	@Override
	protected boolean act() {
		if (!isAlive()) {
			return true;
		}
		
		updateTarget();
		boolean result = super.act();
		
		if(command == Command.NONE || commandPos == -1) {
			if(auto) {
				followHero();
			}
			else {
				defendPos(pos);
			}
		}
		
		return result;
	}
	
	private int cantReach = 0;
	private class Wandering extends Mob.Wandering {

		@Override
		public boolean act( boolean enemyInFOV, boolean justAlerted ) {
			if (auto && enemyInFOV &&
				(command == Command.NONE || canAttack(enemy))) {

				enemySeen = true;
				alerted = true;
				notice();
				
				targetChar(enemy);

			} else {

				enemySeen = false;

				int oldPos = pos;
				if (target != -1 && getCloser( target )) {
					cantReach = 0;
					
					spend( 1 / speed() );
					return moveSprite( oldPos, pos );
				}
				else {
					if (command == Command.DEFEND && pos != commandPos) {
						//if it cant move closer to defend pos, give up and defend current position instead
						cantReach++;
						if(cantReach >= 3) {
							defendPos(pos);
						}
					}
					else { cantReach = 0; }
					
					if(time() % 1 == 0) {
						spend( TICK );
					}
					else {
						//ensure we remain aligned
						spendToWhole();
					}
				}

			}
			
			return true;
		}

	}

	private class Hunting extends Mob.Hunting {
		
		{
			// TODO POLISHED
			// This should auto update when switching the settings
			// Basically it prevents allies from switching aggro when their path is blocked
			recursing = !auto;
		}

		@Override
		public boolean act(boolean enemyInFOV, boolean justAlerted) {
			return super.act(enemyInFOV, justAlerted);
		}

	}

}
