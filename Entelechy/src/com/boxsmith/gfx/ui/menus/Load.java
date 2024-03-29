package com.boxsmith.gfx.ui.menus;

import com.boxsmith.game.Game;
import com.boxsmith.game.State;
import com.boxsmith.gfx.sprite.Sprite;
import com.boxsmith.gfx.ui.components.Button;
import com.boxsmith.gfx.ui.components.Component;

public class Load extends Menu {

    private Button start = new Button(Sprite.BUTTON, Sprite.BUTTON_ACTIVE, "start",
            20 , 200);
    private Button back = new Button(Sprite.BUTTON, Sprite.BUTTON_ACTIVE, "back",
            Game.width - Sprite.BUTTON.getWidth() - 20, 200);

    /**
     * The main menu for the game.
     */
    public Load(){
        add(start);
        add(back);
    }

    /**
     * Listens for a mouseClick and handles the actions.
     */
    public Component mouseClick(){
        Component component = findComponentAtMouse();
        if (component == start){
            Game.state = State.PLAY;
            //Sound.playSound("/Sounds/697340_1.mid");
        }
        if(component == back){
            Game.state = State.MAIN;
        }
        return component;
    }
}
