package com.boxsmith.game;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.*;

import com.boxsmith.gfx.Screen;
import com.boxsmith.input.Keyboard;
import com.boxsmith.input.Mouse;

/**
 * The application's starting starting point. It handle the game's core processes and settings such as, resolution,
 * low level rendering and updates. This class also contains fundamental, static objects, such as the screen,
 * keyboard and mouse, and the game states.
 *
 * Last Edited: Justin Havely 1/13/18
 */
public class Game extends Canvas implements Runnable {

	// Game Settings
	// Resolution
	public static final int SCALE = 2; // Pixel scale
	public static int resolution = 1080;
	public static int height = resolution / SCALE;
    public static int width = height * 16 / 9;

    // Controls the game thread
	private boolean running = false;

    private JFrame frame;

    // Image and pixel data
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	// Static objects (input, state, and screen)
    public static Screen screen;
	public static Keyboard keys;
	public static Mouse mouse;
	public static State state;

	/**
	 * The only way this class should be constructed. Instantiates the static objects, sets the game state to MAIN,
	 * adds keyboard and mouse listeners, and sets the frame settings.
	 */
	private Game() {
		Dimension resolution = new Dimension(width * SCALE, height * SCALE);
		screen = new Screen(width, height);
		keys = new Keyboard();
		mouse = new Mouse();

		state = State.MAIN; // Sets the game the to main menu

		setPreferredSize(resolution); // <-- DON'T CHANGE. EVERYTHING WITH THE MOUSE LOCATION WILL BREAK.
		addKeyListener(keys);
		addMouseListener(mouse);
		addMouseMotionListener(mouse);

		frame = new JFrame("Entelechy");
		frame.add(this);
		frame.pack();
		frame.setResizable(false); // Cannot change resolution messes up the mouse clicks.
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	/**
	 * Starts the main game thread.
	 */
	private synchronized void start() {
		running = true;
        Thread thread = new Thread(this, "Game");
		thread.start();
	}

	/**
	 * Updates the game state and keyboard input 60 times a second.
	 */
	private void tick() {
		keys.update();
		state.tick();
	}

	/**
	 * Renders the game's graphics with a buffer of three.
	 */
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) { // Creates the buffer strategy at startup.
			createBufferStrategy(3); // Renders frames ahead of time to prevent lost frames (black flashing).
			return;
		}

		screen.clear(); // Clears old frame
		state.render(screen);

		for (int i = 0; i < pixels.length; i++) { // Sets the pixels
			pixels[i] = screen.pixels[i];
		}

		Graphics g = bs.getDrawGraphics(); // Creates the image, disposes old, and shows new.
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}

	/**
	 * The method being called the game thread. Forces a tick 60 times a second,
	 * but draws the graphics as many time it can a second for the highest FPS.
	 * Displays the tick and FPS count on the JFrame title once a second.
	 */
	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double nsPerTick = 1000000000D / 60D;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		while (running) {
			long nowTime = System.nanoTime();
			delta += (nowTime - lastTime) / nsPerTick;
			lastTime = nowTime;
			while (delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frame.setTitle("Ticks: " + updates + ", FPS: " + frames);
				frames = 0;
				updates = 0;
			}
		}
	}

	/**
	 * The starting point of the game application.
	 * @param args None
	 */
	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}
}
