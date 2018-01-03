package com.boxsmith.level;

import com.boxsmith.entity.Player;

public class TestLevel extends Level {

    public TestLevel(String path) {
        super(path);
        addEntity(new Player());
    }

}
