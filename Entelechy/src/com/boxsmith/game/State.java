package com.boxsmith.game;

import com.boxsmith.gfx.Screen;
import com.boxsmith.gfx.ui.menus.Load;
import com.boxsmith.gfx.ui.menus.Main;
import com.boxsmith.gfx.ui.menus.Menu;
import com.boxsmith.gfx.ui.menus.Play;
import com.boxsmith.level.Level;
import com.boxsmith.level.MainMenuLevel;
import com.boxsmith.level.TestLevel;

/**
 * The state of the game. Each state contains a UI menu and a level.
 */
public enum State {
    MAIN (new Main(), new MainMenuLevel("/res/Levels/Main Menu/MainMenuTiles.png")), // The main menu
    LOAD (new Load(), new MainMenuLevel("/res/Levels/Main Menu/MainMenuTiles.png")), // The save select
    PLAY (Play.play, new TestLevel("/res/Levels/Test/TestLevelTiles.png")); // The main play.

    private final Menu menu;
    private final Level level;

    /**
     * A new state.
     *
     * @param menu The UI to render and update.
     * @param level The level to render and update.
     */
    State(Menu menu, Level level){
        this.menu = menu;
        this.level = level;
    }

    /**
     * Gets the current state's UI menu
     *
     * @return The menu
     */
    public Menu getStateMenu(){
        return menu;
    }

    /**
     * Gets the current state's level.
     *
     * @return The level
     */
    public Level getStateLevel(){
        return level;
    }

    /**
     * Updates the state's UI and level.
     */
    public void tick(){
        if (menu != null) menu.tick();
        if (level != null) level.tick();
    }

    /**
     * Render the state's UI and level.
     * @param screen
     */
    public void render(Screen screen){
        if (level != null) level.render(screen);
        if (menu != null) menu.render(screen);
    }

}
