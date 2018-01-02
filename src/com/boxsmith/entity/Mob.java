package com.boxsmith.entity;

public abstract  class Mob extends Entity {
    public int direction, healthPoints, maxHealthPoints, respawnTime;

    public void move(int xAbs, int yAbs) {
            if (xAbs != 0 && yAbs != 0) {
                    move(xAbs, 0);
                    move(0, yAbs);
                    return;
                }
            if (xAbs > 0)
                    direction = 1;
            if (xAbs < 0)
                    direction = 3;
            if (yAbs > 0)
                    direction = 2;
            if (yAbs < 0)
                    direction = 0;
            if (!collision(xAbs, yAbs)) {
                    x += xAbs;
                    y += yAbs;
            }
    }
}
