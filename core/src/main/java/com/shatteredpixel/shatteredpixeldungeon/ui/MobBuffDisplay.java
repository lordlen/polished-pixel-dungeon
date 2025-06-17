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

package com.shatteredpixel.shatteredpixeldungeon.ui;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.watabou.gltextures.TextureCache;
import com.watabou.noosa.Image;
import com.watabou.noosa.PointerArea;
import com.watabou.noosa.tweeners.AlphaTweener;
import com.watabou.noosa.ui.Component;
import com.watabou.utils.GameMath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

public class MobBuffDisplay extends Component {
    
    public static final int SIZE = 5;
    
    private LinkedHashMap<Buff, GrayedIcon> buffIcons = new LinkedHashMap<>();
    private boolean needsRefresh;
    private Char ch;
    
    public MobBuffDisplay( Char ch, CharHealthIndicator healthBar ) {
        super();
        this.ch = ch;
        this.healthBar = healthBar;
        height = SIZE;
        
        GameScene.Polished.add(this);
    }
    
    public void target( Char ch, CharHealthIndicator healthBar ) {
        this.ch = ch;
        this.healthBar = healthBar;
    }
    
    @Override
    public void killAndErase() {
        super.killAndErase();
        healthBar = null;
        this.ch = null;
    }
    
    //cached for performance
    CharHealthIndicator healthBar;
    ArrayList<Buff> curBuffs = new ArrayList<>();
    float offset = 0f;
    
    @Override
    public synchronized void update() {
        super.update();
        
        visible = false;
        if (ch != null && ch.isActive() && ch.sprite.visible) {
            if(needsRefresh) {
                needsRefresh = false;
                refreshBuffs();
            }
            
            if(curBuffs != null && !curBuffs.isEmpty()) {
                visible = true;
                layout();
            }
        }
    }
    
    private void refreshBuffs() {
        curBuffs.clear();
        for (Buff buff : ch.buffs()) {
            if (buff.icon() != BuffIndicator.NONE) {
                curBuffs.add(buff);
            }
        }
        
        //remove any icons no longer present
        for (Buff buff : buffIcons.keySet().toArray(new Buff[0])) {
            if (!curBuffs.contains(buff)){
                GrayedIcon icon = buffIcons.get( buff );
                
                /*Image fading = icon.icon;
                fading.originToCenter();
                fading.alpha(0.6f);
                add( fading );
                add( new AlphaTweener( fading, 0, 0.6f ) {
                    @Override
                    protected void updateValues( float progress ) {
                        super.updateValues( progress );
                        image.scale.set( 1 + 5 * progress );
                    }
                    
                    @Override
                    protected void onComplete() {
                        image.killAndErase();
                    }
                } );*/
                
                icon.destroy();
                remove(icon);
                buffIcons.remove( buff );
            }
        }
        
        //add new icons
        for (Buff buff : curBuffs) {
            if (!buffIcons.containsKey(buff)) {
                GrayedIcon icon = new GrayedIcon(buff);
                icon.icon.scale.set(PixelScene.align(0.6f));
                add(icon);
                buffIcons.put( buff, icon );
            }
        }
        
        width = Math.min(buffIcons.size(), 3) * SIZE;
        offset = 0.5f * Math.min(buffIcons.size(), 3);
    }
    
    @Override
    protected void layout() {
        CharSprite sprite = ch.sprite;
        TargetHealthIndicator targetHP = TargetHealthIndicator.instance;
        
        x = sprite.x + sprite.width()/2f - width() / 2f - offset;
        y = sprite.y - 3f - height;
        
        if ((healthBar != null && healthBar.visible) ||
                (targetHP != null && targetHP.target() == ch && targetHP.visible)) {
            y -= 2.5f;
        }
        
        //layout
        int pos = 0;
        for (GrayedIcon icon : buffIcons.values()){
            icon.updateIcon();
            icon.setRect(PixelScene.align(x + pos * (SIZE + 1)), PixelScene.align(y), SIZE + 1, SIZE + 1);
            
            icon.visible = pos < 3;
            pos++;
        }
    }
    
    public void refresh() {
        needsRefresh = true;
    }
    
    private static class GrayedIcon extends Component {
        
        private Buff buff;
        
        public BuffIcon icon;
        public Image grey;
        
        public GrayedIcon(Buff buff){
            super();
            this.buff = buff;
            icon.refresh(buff);
        }
        
        @Override
        protected void createChildren() {
            super.createChildren();
            
            icon = new BuffIcon();
            add(icon);
            
            grey = new Image( TextureCache.createSolid(0xCC666666));
            add( grey );
        }
        
        public void updateIcon(){
            float custom = Buff.Polished.customIconFade(buff);
            float generic = GameMath.gate(0, buff.iconFadePercent(), 1);
            
            float fadeHeight = custom != -1 ? custom : generic;
            fadeHeight *= icon.height();
            
            float zoom = camera() != null ? camera().zoom : 1;
            float rounded;
            if (fadeHeight < icon.height() / 2f) {
                rounded = (float)Math.ceil(zoom * fadeHeight);
            } else {
                rounded = (float)Math.floor(zoom * fadeHeight);
            }
            
            grey.scale.set(icon.width(), rounded / zoom);
            
            if(custom >= 1f) {
                grey.color(0x804040);
            }
            else {
                grey.resetColor();
            }
        }
        
        @Override
        protected void layout() {
            super.layout();
            grey.x = icon.x = this.x + 1;
            grey.y = icon.y = this.y + 2;
        }
    }
}
