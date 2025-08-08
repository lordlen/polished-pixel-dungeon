package com.shatteredpixel.shatteredpixeldungeon.actors.blobs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;

public class LandmarkBlob extends Blob {
    
    @Override
    public boolean act() {
        if(volume == 0) return super.act();
        
        spend(10*TICK);
        return true;
    }
    
    //only check every 5 observes for performance
    int checkVisible = 5;
    
    @Override
    public boolean isVisible() {
        if(checkVisible < 5) {
            checkVisible++;
            return false;
        } else {
            checkVisible = 0;
        }
        
        for (int i=area.top; i < area.bottom; i++) {
            for (int j = area.left; j < area.right; j++) {
                int cell = j + i* Dungeon.level.width();
                
                //Landmark blobs only need to be mapped
                if (cur[cell] > 0 &&
                    ( Dungeon.level.visited[cell] || Dungeon.level.mapped[cell] )) {
                    return true;
                }
            }
        }
        return false;
    }
}
