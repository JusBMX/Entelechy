package com.boxsmith.gfx.ui.menus;

import com.boxsmith.gfx.sprite.Sprite;
import com.boxsmith.gfx.ui.components.Button;

public class Main extends Menu {

    private Button start = new Button(Sprite.ROCK, Sprite.GRASS, "Start", 10, 100);

    public Main(){
        add(start);
    }

    public void mouseClick(){
        if (findComponentAtMouse() == start){
            System.out.println("Start");
        }
    }

}
