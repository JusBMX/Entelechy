package com.boxsmith.gfx.ui.menus;

import com.boxsmith.game.Game;
import com.boxsmith.gfx.sprite.Sprite;
import com.boxsmith.gfx.ui.components.Component;
import com.boxsmith.gfx.ui.components.Inventory.Inventory;
import com.boxsmith.gfx.ui.components.chat.Chat;

public class Play extends Menu {

    public static Play play = new Play();

    public Chat chat = new Chat(0, Game.height - Sprite.CHAT.getHeight(),this);
    public Inventory inventory = new Inventory();

    private Play() {
        add(inventory);
    }

    @Override
    public Component mouseClick() {
        Component component = findComponentAtMouse();
        if (component == null)
            return null;

        if (component.action != null) {
            component.action.actionMethod();
        }
        return component;
    }
}
