package com.boxsmith.gfx.ui.menus;

import com.boxsmith.gfx.Screen;
import com.boxsmith.gfx.ui.components.Component;
import com.boxsmith.input.Mouse;

import java.util.ArrayList;
import java.util.List;

public abstract class Menu {

    private List<Component> components = new ArrayList<>(); // Components in a menu.

    /**
     * Adds a component to the menu.
     *
     * @param component The component to add.
     */
    public void add(Component component){
        components.add(component);
    }

    /**
     * Renders the menu to the screen.
     * @param screen The screen to render to.
     */
    public void render(Screen screen){
        for(Component component : components){
            component.render(screen);
        }
    }

    /**
     * Sees if a menu component is below the mouse courser.
     *
     * @return The component below the mouse courser. Null if none are.
     */
    public Component findComponentAtMouse(){
        int[] coordinate = Mouse.screenToWorld(false);
        for (Component component: components) {
            if (component.onComponent(coordinate) != null) {
                return component.onComponent(coordinate);
            }
        }
        return null;
    }

    /**
     * Updates the components of the menu.
     */
    public void tick(){
        for(Component component : components){
            component.tick();
        }
    }

    /**
     * Listens for a mouseClick and handles the actions.
     */
    public abstract void mouseClick();

}
