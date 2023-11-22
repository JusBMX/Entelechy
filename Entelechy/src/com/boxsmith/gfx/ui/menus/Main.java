package com.boxsmith.gfx.ui.menus;

import com.boxsmith.game.Game;
import com.boxsmith.game.State;
import com.boxsmith.gfx.sprite.Sprite;
import com.boxsmith.gfx.ui.components.Button;
import com.boxsmith.gfx.ui.components.Component;

public class Main extends Menu {

    // Loads the save select menu.
    private Button start = new Button(Sprite.BUTTON, Sprite.BUTTON_ACTIVE, "Story Mode",
            Game.width / 2 - Sprite.BUTTON.getWidth() / 2 , 100);

    // exits the game.
    private Button exit = new Button(Sprite.BUTTON, Sprite.BUTTON_ACTIVE, "Exit",
            Game.width / 2 - Sprite.BUTTON.getWidth() / 2 , 200);

    // Loads the options menu.
    private Button options = new Button(Sprite.BUTTON, Sprite.BUTTON_ACTIVE, "Options",
            Game.width / 2 - Sprite.BUTTON.getWidth() / 2 , 150);

    /**
     * The main menu for the game.
     */
    public Main(){
        add(start);
        add(exit);
        add(options);
    }

    /**
     * Listens for a mouseClick and handles the actions.
     */
    public Component mouseClick(){
        Component component = findComponentAtMouse();
        if (component == start){
            Game.state = State.LOAD;
        }
        if (component == exit){
            java.lang.System.exit(0);
        }
        return component;
    }

}
