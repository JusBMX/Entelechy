package com.boxsmith.entity;

import com.boxsmith.gfx.Screen;
import com.boxsmith.level.Level;

public abstract class Entity {
    public int x, y;
 	protected Level level;

 	public abstract void tick();

 	public abstract void render(Screen screen);

    public boolean collision(int xAbs, int yAbs) {
        boolean sold = false;
        for (int c = 0; c < 2; c++) {
                int xt = ((x + xAbs)) / 16; //playerX+nextPixel
                int yt = ((y + yAbs) + c * 16) / 16;

                    if (level.getTile(xt, yt).solid())
                        sold = true;
            }
        return sold;
    }

    public void init(Level level) {
        this.level = level;
    }
}
