package com.boxsmith.gfx.sprite;

public class SpriteAnimation {

    private SpriteSheet sheet; // The sprite sheet the sprites are located on.

    private int x, y; // The coordinate of the first sprite on the sprite sheet.
    private int width, height; // The width and height of each frame.
    private int dimension; // The width and height of the sprite in pixels.

    private int length; // The number of frames in the animation.
    private int currentFrame; // The current frame in the animation.
    private Sprite[] frames; // The sprites that make up the animation.

    private int delayCount = 0; // Counter
    private int delay = 5; // The number of ticks to display the frame.

    public static SpriteAnimation test = new SpriteAnimation(16, 0,128,SpriteSheet.TILES, 6).setDelay(10);

    /**
     * Creates an animation with sprites with the same width and height.
     * @param dimension The width and height of the animation in pixels.
     * @param x The x coordinate of the first sprite on the sprite sheet.
     * @param y The y coordinate of the first sprite on the sprite sheet.
     * @param sheet The sprite sheet the sprite is located on.
     * @param length The number of frames in the animation.
     */
    public SpriteAnimation(int dimension, int x, int y, SpriteSheet sheet, int length) {
        this.dimension = dimension;
        this.width = dimension;
        this.height = dimension;
        this.x = x;
        this.y = y;
        this.sheet = sheet;
        this.length = length;
        load();
    }

    /**
     * Creates an animation.
     * @param height The height of the animation in pixels.
     * @param width The width of the animation in pixels.
     * @param x The x coordinate of the first sprite on the sprite sheet.
     * @param y The y coordinate of the first sprite on the sprite sheet.
     * @param sheet The sprite sheet the sprite is located on.
     * @param length The number of frames in the animation.
     */
    public SpriteAnimation(int height, int width, int x, int y, SpriteSheet sheet, int length) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.sheet = sheet;
        this.length = length;
        load();
    }

    /**
     * Updates the animation to the next frame.
     */
    public void tick(){
        if(delayCount >= delay) {
            delayCount = 0;
            currentFrame = (currentFrame + 1) % length;
        }
        delayCount++;
    }

    /**
     * Load the pixel data into the array of sprites.
     */
    private void load(){
        frames = new Sprite[length];
        for(int i = 0; i < length; i++){
            int xCal = (x + i * width) % sheet.getDIMENSION(); // wraps back if x > sprite sheet width.
            int yCal = y + height * ((x + i * width) / sheet.getDIMENSION()); // Shifts down if x > sprite sheet width.
            frames[i] = new Sprite(height, width, xCal, yCal, sheet);
        }
    }

    /**
     * @return The width of the frame in pixels.
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return The height of the frame in pixels.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets how long each frame of the animation is render. (5 is default)
     * @param delay the number of ticks to render each frame.
     */
    public SpriteAnimation setDelay(int delay){
        this.delay = delay;
        return this;
    }

    /**
     * @return The dimension of the sprite in pixels. Returns <code>null</code> if width and height are not equal.
     */
    public int getDimension(){
        return dimension;
    }

    /**
     * @return The current frame (sprite) of the animation.
     */
    public Sprite getCurrentFrame(){
        return frames[currentFrame];
    }

}
