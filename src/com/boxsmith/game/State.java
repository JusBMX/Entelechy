package com.boxsmith.game;

import com.boxsmith.gfx.Screen;
import com.boxsmith.gfx.ui.menus.Load;
import com.boxsmith.gfx.ui.menus.Main;
import com.boxsmith.gfx.ui.menus.Menu;
import com.boxsmith.gfx.ui.menus.Play;
import com.boxsmith.level.Level;
import com.boxsmith.level.MainMenuLevel;
import com.boxsmith.level.TestLevel;

public enum State {
    MAIN (new Main(), new MainMenuLevel("/Levels/Main Menu/MainMenuTiles.png")),
    LOAD (new Load(), new MainMenuLevel("/Levels/Main Menu/MainMenuTiles.png")),
    PLAY (Play.play, new TestLevel("/Levels/Test/TestLevelTiles.png"));

    private final Menu menu;
    private final Level level;

    State(Menu menu, Level level){
        this.menu = menu;
        this.level = level;
    }

    public Menu getStateMenu(){
        return menu;
    }

    public Level getStateLevel(){
        return level;
    }

    public void tick(){
        if (menu != null) menu.tick();
        if (level != null) level.tick();
    }

    public void render(Screen screen){
        if (level != null) level.render(screen);
        if (menu != null) menu.render(screen);
    }

}
