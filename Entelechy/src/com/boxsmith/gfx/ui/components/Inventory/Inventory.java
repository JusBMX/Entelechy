package com.boxsmith.gfx.ui.components.Inventory;

import java.util.ArrayList;

import com.boxsmith.game.Game;
import com.boxsmith.gfx.Screen;
import com.boxsmith.gfx.sprite.Sprite;
import com.boxsmith.gfx.ui.components.Button;
import com.boxsmith.gfx.ui.components.Component;

public class Inventory extends Component {
    public Button inventory = new Button(Sprite.BUTTON_INVENTORY, Sprite.BUTTON_INVENTORY_ACTIVE, "",
            Game.width - 32, Game.height - Sprite.BUTTON_INVENTORY.getHeight());

    public ArrayList<Item> items;

    public Inventory() {
        inventory.action = () -> {
        };
    }

    @Override
    public void render(Screen screen) {
        screen.renderSprite(Game.width - Sprite.INVENTORY.getWidth(), 
                            Game.height - Sprite.INVENTORY.getHeight() - 16,
                            Sprite.INVENTORY, false);
        inventory.render(screen);
    }

    @Override
    public void tick() {
        inventory.tick();
    }

}
