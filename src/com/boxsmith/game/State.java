package com.boxsmith.game;

import com.boxsmith.gfx.Screen;
import com.boxsmith.gfx.ui.menus.Load;
import com.boxsmith.gfx.ui.menus.Main;
import com.boxsmith.gfx.ui.menus.Menu;

public enum State {
    MAIN (new Main()),
    LOAD (new Load());

    private final Menu menu;

    State(Menu menu){
        this.menu = menu;
    }

    public Menu getStateMenu(){
        return menu;
    }

    public void tick(){
        menu.tick();
    }

    public void render(Screen screen){
        menu.render(screen);
    }

}
