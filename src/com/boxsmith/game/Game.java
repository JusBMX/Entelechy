package com.boxsmith.game;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.*;

import com.boxsmith.gfx.sprite.SpriteAnimation;
import com.boxsmith.gfx.Screen;
import com.boxsmith.input.Keyboard;
import com.boxsmith.input.Mouse;

public class Game extends Canvas implements Runnable {

	// Game Settings
    public static int width = 480;
    public static int height = width / 16 * 9;
    public static final int SCALE = 2;

	private boolean running = false;
    private JFrame frame;

    // Image data
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

    public static Screen screen;
	public static Keyboard keys;
	public static Mouse mouse;
	public static State state;

	private Game() {
		Dimension resolution = new Dimension(width * SCALE, height * SCALE);
		screen = new Screen(width, height);
		keys = new Keyboard();
		mouse = new Mouse();

		state = State.MAIN;

		setPreferredSize(resolution); // <-- DON'T CHANGE. EVERYTHING WITH THE MOUSE LOCATION WILL BREAK.
		addKeyListener(keys);
		addMouseListener(mouse);
		addMouseMotionListener(mouse);

		frame = new JFrame("Entelechy");
		frame.add(this);
		frame.pack();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	private synchronized void start() {
		running = true;
        Thread thread = new Thread(this, "Game");
		thread.start();
	}

	private void tick() {
		keys.update();
		state.tick();
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		screen.clear();
		state.render(screen);

		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}

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

	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}
}
