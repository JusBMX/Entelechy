package com.boxsmith.gfx.ui.menus;

import com.boxsmith.gfx.Screen;
import com.boxsmith.gfx.ui.components.Component;
import com.boxsmith.input.Mouse;
import com.boxsmith.level.Level;

import java.util.ArrayList;
import java.util.List;

public abstract class Menu{

    private List<Component> components = new ArrayList<>();

    public void add(Component component){
        components.add(component);
    }

    public void render(Screen screen){
        for(Component component : components){
            component.render(screen);
        }
    }

    public Component findComponentAt(){
        int[] coor = Mouse.screenToWorld(false);
        for (Component component: components) {
            if (component.onComponent(coor) != null) {
                return component.onComponent(coor);
            }
        }
        return null;
    }

    public abstract void mouseClick();

}
