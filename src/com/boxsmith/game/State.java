package com.boxsmith.game;

import com.boxsmith.gfx.Screen;
import com.boxsmith.gfx.ui.menus.Load;
import com.boxsmith.gfx.ui.menus.Main;
import com.boxsmith.gfx.ui.menus.Menu;
import com.boxsmith.level.Level;
import com.boxsmith.level.MainMenu;

public enum State {
    MAIN (new Main(), new MainMenu("/Levels/Main Menu/MainMenuTiles.png")),
    LOAD (new Load(), null);

    private final Menu menu;
    private final Level level;

    State(Menu menu, Level level){
        this.menu = menu;
        this.level = level;
    }

    public Menu getStateMenu(){
        return menu;
    }

    public void tick(){
        menu.tick();
    }

    public void render(Screen screen){
        level.render(screen);
        menu.render(screen);

    }

}
