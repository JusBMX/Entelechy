package com.boxsmith.gfx.ui.components;

import com.boxsmith.gfx.Screen;
import com.boxsmith.gfx.sprite.Sprite;
import com.boxsmith.input.Mouse;

public class Button extends Component {

    private String text; // Text to display on the button.
    private Sprite hoverSprite; // The sprite to render when the button is active.
    private boolean hover = false; // If the mouse is over the component.

    /**
     * Create a button with two different sprites, one for nonactive and active.
     *
     * @param sprite Nonactive background sprite.
     * @param hoverSprite Active background sprite.
     * @param text The text to display on the button.
     * @param x The horizontal position.
     * @param y The vertical position.
     */
    public Button(Sprite sprite, Sprite hoverSprite, String text, int x, int y) {
        this.width = sprite.getWidth();
        this.height = sprite.getHeight();
        this.hoverSprite = hoverSprite;
        this.text = text;
        this.sprite = sprite;
        this.x = x;
        this.y = y;
    }

    /**
     * Create a button.
     *
     * @param sprite The background sprite.
     * @param text The text to display on the button.
     * @param x The horizontal position.
     * @param y The vertical position.
     */
    public Button(Sprite sprite, String text, int x, int y) {
        this.width = sprite.getWidth();
        this.height = sprite.getHeight();
        this.text = text;
        this.sprite = sprite;
        this.hoverSprite = sprite;
        this.x = x;
        this.y = y;
    }

    /**
     * Creates a button with no text.
     *
     * @param sprite The background sprite.
     * @param x The horizontal position.
     * @param y The vertical position.
     */
    public Button(Sprite sprite, int x, int y) {
        this.width = sprite.getWidth();
        this.height = sprite.getHeight();
        this.text = "";
        this.sprite = sprite;
        this.hoverSprite = sprite;
        this.x = x;
        this.y = y;
    }

    /**
     * Renders the button. Also checks if the <code>hover</code> is true or not to render a different sprite.
     * @param screen The screen to render to.
     */
    @Override
    public void render(Screen screen){
        Sprite sprite = hover ? hoverSprite : this.sprite;
        screen.renderSprite(x, y, sprite, false);
        screen.renderText(text, x - (text.length() * 4) + width / 2, y + 4, false);
    }

    /**
     * Checks if the mouse is hovering over the button.
     */
    @Override
    public void tick() {
        hover = onComponent(Mouse.screenToWorld(false)) == this;
    }
}