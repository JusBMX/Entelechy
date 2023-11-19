package com.boxsmith.gfx.sprite;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private final String path; // PNG image location.
	private final int DIMENSION; // PNG image dimensions.
	private int[] pixels; // PNG pixel data for the .

	public static final SpriteSheet TILES = new SpriteSheet("/res/Textures/Spritesheet16x16.png", 256);
	public static final SpriteSheet FONT = new SpriteSheet("/res/Textures/Font.png", 126);
	public static final SpriteSheet MENUS = new SpriteSheet("/res/Textures/UI/MenuSprites.png", 256);

    /**
     * Creates a new 'sheet' from an image file containing a group of sprites.
     * The source image has to have a 1:1 ratios (i.e. 256x256) and should be PNG.
     *
     * @param path URL of the PNG image file.
     * @param dimension The dimension (width or height since they are the same) of the image.
     */
	private SpriteSheet(String path, int dimension) {
		this.path = path;
		DIMENSION = dimension;
		pixels = new int[DIMENSION * DIMENSION];

		try {
            load();
        } catch (MismatchDimensionException e){
		    e.printStackTrace();
        }
	}

    /**
     * Loads the sprite sheet's pixels data into the pixels array.
     * @throws MismatchDimensionException Happens if the image's width or height are not equal to <code>DIMENSION</code>.
     */
	private void load() throws MismatchDimensionException {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			
			if(image.getHeight() != DIMENSION || image.getWidth() != DIMENSION){
			    throw new MismatchDimensionException();
            }
			
			image.getRGB(0, 0, DIMENSION, DIMENSION, pixels, 0, DIMENSION);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    /**
     * @return The dimension of the sprite sheet.
     */
	public int getDIMENSION(){
	    return DIMENSION;
    }

    /**
     * @return A copy of the pixel array. This way the array is immutable outside the class.
     */
    public int[] getPixels(){
        return pixels;
    }

    class MismatchDimensionException extends Exception{
        MismatchDimensionException(){
            super("The image's dimensions are not the same, or do not match the DIMENSION of the sprite sheet.");
        }
    }
}


