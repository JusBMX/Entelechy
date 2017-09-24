package com.boxsmith.gfx.ui.menus;

import com.boxsmith.gfx.sprite.Sprite;
import com.boxsmith.gfx.ui.components.Button;

public class Main extends Menu {

    private Button start = new Button(Sprite.rock, "Start", 10, 100);

    public Main(){
        add(start);
    }

    public void mouseClick(){
        if (findComponentAt() == start){
            System.out.println("gg");
        }
    }

}
