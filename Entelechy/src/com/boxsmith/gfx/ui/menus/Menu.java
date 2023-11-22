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
     * Removes a component from the menu. Returns True if removed.
     * @param component The componment to remove.
     */
    public Boolean remove(Component component){
        return components.remove(component);
    }

    /**
     * Adds components to the menu.
     *
     * @param components The component to add.
     */
    public void add(List<Component> components){
        for (Component component: components) {
            components.add(component);
        }

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
     * Sees if a menu component is below the mouse courser. If so, it returns the top most component.
     *
     * @return The component below the mouse courser. Null if none are.
     */
    public Component findComponentAtMouse(){
        List<Component> foundComponents = new ArrayList<>();
        int[] coordinate = Mouse.screenToWorld(false);
        for (Component component: components) {
            if (component.onComponent(coordinate) != null) {
                foundComponents.add(component.onComponent(coordinate));
            }
        }
        if(!foundComponents.isEmpty()) {
            return foundComponents.get(foundComponents.size() - 1);
        }
        return null;
    }

    /**
     * Sees if a menu component is below the mouse courser. If so, it returns all components.
     *
     * @return The components below the mouse courser. Null if none are.
     */
    public List<Component> findComponentsAtMouse(){
        List<Component> foundComponents = new ArrayList<>();
        int[] coordinate = Mouse.screenToWorld(false);
        for (Component component: components) {
            if (component.onComponent(coordinate) != null) {
                foundComponents.add(component.onComponent(coordinate));
            }
        }
        return foundComponents;
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
    public abstract Component mouseClick();

}
