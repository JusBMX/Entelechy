package com.boxsmith.gfx.ui.menus;

import com.boxsmith.game.Game;
import com.boxsmith.game.State;
import com.boxsmith.gfx.sprite.Sprite;
import com.boxsmith.gfx.ui.components.Button;
import com.boxsmith.sound.Sound;

public class Load extends Menu {

    private Button start = new Button(Sprite.BUTTON, Sprite.BUTTON_ACTIVE, "start",
            Game.width / 2 - Sprite.BUTTON.getWidth() / 2 , 200);

    /**
     * The main menu for the game.
     */
    public Load(){
        add(start);
    }

    /**
     * Listens for a mouseClick and handles the actions.
     */
    public void mouseClick(){
        if (findComponentAtMouse() == start){
            Game.state = State.PLAY;
            Sound.playSound("");
        }
    }
}
