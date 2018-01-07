package com.boxsmith.gfx.ui.components;

import com.boxsmith.gfx.Screen;
import com.boxsmith.gfx.sprite.Sprite;

import java.util.ArrayList;
import java.util.List;

public class ContentBox extends Component {

    public Sprite background;

    public List<Component> components = new ArrayList<>();

    public ContentBox (Sprite background, int x, int y){
        this.background = background;
        this.x = x;
        this.y = y;
    }

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
        if(background != null){
            screen.renderSprite(x,y,background,false);
        }

        for(Component component : components){
            component.render(screen);
        }
    }

    /**
     * Updates the components of the menu.
     */
    public void tick(){
        for(Component component : components){
            component.tick();
        }
    }
}
