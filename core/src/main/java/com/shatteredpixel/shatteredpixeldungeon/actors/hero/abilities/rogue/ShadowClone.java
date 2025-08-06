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

package com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.rogue;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.ArmorAbility;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.Stasis;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.DirectableAlly;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.SmokeParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.ClassArmor;
import com.shatteredpixel.shatteredpixeldungeon.levels.CityLevel;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.HeroSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.MobSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.TextureFilm;
import com.watabou.noosa.audio.Sample;
import com.watabou.noosa.particles.Emitter;
import com.watabou.noosa.tweeners.Tweener;
import com.watabou.utils.BArray;
import com.watabou.utils.Bundle;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class ShadowClone extends ArmorAbility {

	{
		baseChargeUse = 35f;
	}
	
	public boolean useTargeting(){
		return false;
	}
	
	@Override
	public float chargeUse(Hero hero) {
		if (Shadow() == null) {
			return super.chargeUse(hero);
		} else {
			return 0;
		}
	}
	
	float chargeSummon(Hero hero) {
		return super.chargeUse(hero);
	}
	
	private static ShadowAlly shadow = null;
	private static int shadowID = -1;
	
	public static void resetShadow() {
		shadow = null;
		shadowID = -1;
	}
	
	public static ShadowAlly Shadow() {
		if(shadow != null) {
			if(!shadow.isAlive()) resetShadow();
			return shadow;
		}
		
		if(shadowID != -1) {
			Actor a = Actor.findById(shadowID);
			if (a instanceof ShadowAlly){
				shadow = (ShadowAlly) a;
				return shadow;
			} else {
				shadowID = -1;
			}
		}
		
		Char ally = Stasis.getStasisAlly();
		if (ally instanceof ShadowAlly){
			shadow = (ShadowAlly) ally;
			shadowID = ally.id();
			return shadow;
		}
		
		return null;
	}
	
	@Override
	protected void activate(ClassArmor armor, Hero hero, Integer target) {
		if (Shadow() != null){
			if(shadow.stasis()) {
				GLog.i( Messages.get(this, "spawned") );
			}
			else {
				shadow.command();
			}
		}
		else {
			DirectableAlly.SummonSelector.trySummon(new ShadowAlly(hero.lvl), () -> {
				armor.charge -= chargeSummon(hero);
				Item.updateQuickslot();
				Invisibility.dispel();
			});
		}
	}

	@Override
	public int icon() {
		return HeroIcon.SHADOW_CLONE;
	}

	@Override
	public Talent[] talents() {
		return new Talent[]{Talent.SHADOW_BLADE, Talent.CLONED_ARMOR, Talent.PERFECT_COPY, Talent.HEROIC_ENERGY};
	}

	public static class ShadowAlly extends DirectableAlly {

		{
			spriteClass = ShadowSprite.class;

			HP = HT = 80;
			viewDistance = 3;

			properties.add(Property.INORGANIC);
		}

		public ShadowAlly(){
			super();
		}

		public ShadowAlly( int heroLevel ){
			super();
			int hpBonus = 15 + 5*heroLevel;
			hpBonus = Math.round(0.1f * Dungeon.hero.pointsInTalent(Talent.PERFECT_COPY) * hpBonus);
			if (hpBonus > 0){
				HT += hpBonus;
				HP += hpBonus;
			}
			defenseSkill = heroLevel + 4; //equal to base hero defense skill
		}
		
		@Override
		protected void onSummon() {
			super.onSummon();
			appear(this, pos);
		}
		
		@Override
		protected void announce() {
			switch (command) {
				case DEFEND:
					if(!defendAnnounced) {
						GLog.i(Messages.get(this, "direct_defend"));
						defendAnnounced = true;
					}
					break;
				case ATTACK:
					if(!attackAnnounced) {
						GLog.i(Messages.get(this, "direct_attack"));
						attackAnnounced = true;
					}
					break;
				case FOLLOW:
					if(!followAnnounced) {
						GLog.i(Messages.get(this, "direct_follow"));
						followAnnounced = true;
					}
					break;
				case NONE: default:
					if(!darkAnnounced) {
						GLog.n(Messages.get(this, "too_dark"));
						darkAnnounced = true;
					}
					break;
			}
		}
		
		@Override
		protected void onAdd() {
			super.onAdd();
			
			ShadowClone.shadow = this;
			ShadowClone.shadowID = id();
		}
		
		@Override
		public void destroy() {
			super.destroy();
			ShadowClone.resetShadow();
		}
		
		@Override
		protected boolean act() {
			int oldPos = pos;
			boolean result = super.act();
			//partially simulates how the hero switches to idle animation
			if ((pos == target || oldPos == pos) && sprite.looping()){
				sprite.idle();
			}
			return result;
		}

		@Override
		public int attackSkill(Char target) {
			return defenseSkill+5; //equal to base hero attack skill
		}

		@Override
		public int damageRoll() {
			int damage = Random.NormalIntRange(10, 20);
			int heroDamage = Dungeon.hero.damageRoll();
			heroDamage /= Dungeon.hero.attackDelay(); //normalize hero damage based on atk speed
			heroDamage = Math.round(0.08f * Dungeon.hero.pointsInTalent(Talent.SHADOW_BLADE) * heroDamage);
			if (heroDamage > 0){
				damage += heroDamage;
			}
			return damage;
		}

		@Override
		public int attackProc( Char enemy, int damage ) {
			damage = super.attackProc( enemy, damage );
			if (Random.Int(4) < Dungeon.hero.pointsInTalent(Talent.SHADOW_BLADE)
					&& Dungeon.hero.belongings.weapon() != null){
				return Dungeon.hero.belongings.weapon().proc( this, enemy, damage );
			} else {
				return damage;
			}
		}

		@Override
		public int drRoll() {
			int dr = super.drRoll();
			int heroRoll = Dungeon.hero.drRoll();
			heroRoll = Math.round(0.12f * Dungeon.hero.pointsInTalent(Talent.CLONED_ARMOR) * heroRoll);
			if (heroRoll > 0){
				dr += heroRoll;
			}
			return dr;
		}

		@Override
		public int glyphLevel(Class<? extends Armor.Glyph> cls) {
			if (Dungeon.hero != null && Random.Int(4) < Dungeon.hero.pointsInTalent(Talent.CLONED_ARMOR)){
				return Math.max(super.glyphLevel(cls), Dungeon.hero.glyphLevel(cls));
			} else {
				return super.glyphLevel(cls);
			}
		}

		@Override
		public int defenseProc(Char enemy, int damage) {
			damage = super.defenseProc(enemy, damage);
			if (Random.Int(4) < Dungeon.hero.pointsInTalent(Talent.CLONED_ARMOR)
					&& Dungeon.hero.belongings.armor() != null){
				return Dungeon.hero.belongings.armor().proc( enemy, this, damage );
			} else {
				return damage;
			}
		}
		
		@Override
		public int magicDefenseProc(Char enemy, int damage) {
			damage = super.magicDefenseProc(enemy, damage);
			if (Random.Int(4) < Dungeon.hero.pointsInTalent(Talent.CLONED_ARMOR)
					&& Dungeon.hero.belongings.armor() != null){
				return Dungeon.hero.belongings.armor().proc( enemy, this, damage );
			} else {
				return damage;
			}
		}

		@Override
		public float speed() {
			float speed = super.speed();

			//moves 2 tiles at a time when returning to the hero
			if (state == WANDERING
					&& commandPos == -1
					&& Dungeon.level.distance(pos, Dungeon.hero.pos) > 1){
				speed *= 2;
			}

			return speed;
		}

		@Override
		public boolean canInteract(Char c) {
			if (super.canInteract(c)){
				return true;
			} else if (Dungeon.level.distance(pos, c.pos) <= Dungeon.hero.pointsInTalent(Talent.PERFECT_COPY)) {
				return true;
			} else {
				return false;
			}
		}

		@Override
		public boolean interact(Char c) {
			if (!Dungeon.hero.hasTalent(Talent.PERFECT_COPY)){
				return super.interact(c);
			}

			//some checks from super.interact
			if (!Dungeon.level.passable[pos] && !c.flying){
				return true;
			}

			if (properties().contains(Property.LARGE) && !Dungeon.level.openSpace[c.pos]
					|| c.properties().contains(Property.LARGE) && !Dungeon.level.openSpace[pos]){
				return true;
			}

			int curPos = pos;

			//warp instantly with the clone
			PathFinder.buildDistanceMap(c.pos, BArray.or(Dungeon.level.passable, Dungeon.level.avoid, null));
			if (PathFinder.distance[pos] == Integer.MAX_VALUE){
				return true;
			}
			appear(this, Dungeon.hero.pos);
			appear(Dungeon.hero, curPos);
			return true;
		}

		private static void appear( Char ch, int pos ) {

			ch.sprite.interruptMotion();

			if (Dungeon.level.heroFOV[pos] || Dungeon.level.heroFOV[ch.pos]){
				Sample.INSTANCE.play(Assets.Sounds.PUFF);
			}

			ch.move( pos );
			if (ch.pos == pos) ch.sprite.place( pos );

			if (Dungeon.level.heroFOV[pos] || ch == Dungeon.hero ) {
				ch.sprite.emitter().burst(SmokeParticle.FACTORY, 10);
			}
		}

		private static final String DEF_SKILL = "def_skill";

		@Override
		public void storeInBundle(Bundle bundle) {
			super.storeInBundle(bundle);
			bundle.put(DEF_SKILL, defenseSkill);
		}

		@Override
		public void restoreFromBundle(Bundle bundle) {
			super.restoreFromBundle(bundle);
			defenseSkill = bundle.getInt(DEF_SKILL);
		}
	}

	public static class ShadowSprite extends MobSprite {

		private Emitter smoke;

		public ShadowSprite() {
			super();

			texture( HeroClass.ROGUE.spritesheet() );

			TextureFilm film = new TextureFilm( HeroSprite.tiers(), 6, 12, 15 );

			idle = new Animation( 1, true );
			idle.frames( film, 0, 0, 0, 1, 0, 0, 1, 1 );

			run = new Animation( 20, true );
			run.frames( film, 2, 3, 4, 5, 6, 7 );

			die = new Animation( 20, false );
			die.frames( film, 0 );

			attack = new Animation( 15, false );
			attack.frames( film, 13, 14, 15, 0 );

			idle();
			resetColor();
		}

		@Override
		public void onComplete(Tweener tweener) {
			super.onComplete(tweener);
		}

		@Override
		public void resetColor() {
			super.resetColor();
			alpha(0.8f);
			brightness(0.0f);
		}

		@Override
		public void link( Char ch ) {
			super.link( ch );
			renderShadow = false;

			if (smoke == null) {
				smoke = emitter();
				smoke.pour( CityLevel.Smoke.factory, 0.2f );
			}
		}

		@Override
		public void update() {

			super.update();

			if (smoke != null) {
				smoke.visible = visible;
			}
		}

		@Override
		public void kill() {
			super.kill();

			if (smoke != null) {
				smoke.on = false;
			}
		}
	}
}
