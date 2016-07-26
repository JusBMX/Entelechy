package com.boxsmith.game;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.boxsmith.gfx.Screen;
import com.boxsmith.input.Keyboard;
import com.boxsmith.input.Mouse;
import com.boxsmith.level.Level;
import com.boxsmith.skills.Skill;

public class Game extends Canvas implements Runnable {

	public static int width = 720;
	public static int height = width / 16 * 9;
	public static int scale = 3;

	private static final long serialVersionUID = 1L;
	private boolean running = false;

	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	private Thread thread;
	private JFrame frame;
	public static Screen screen;
	private Level level;
	public static Keyboard keys;
	public static Mouse mouse;


	public Game() {
		Dimension resolution = new Dimension(width * scale, height * scale);
		screen = new Screen(width, height);
		keys = new Keyboard();
		mouse = new Mouse();

		level = Level.spawnlevel;

		frame = new JFrame("Game");
		frame.setPreferredSize(resolution);
		frame.add(this);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		addKeyListener(keys);
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Game");
		thread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void tick() {
		keys.update();
		level.update();
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		screen.clear();
		int xScroll = level.getPlayer().x - screen.width / 2;
		int yScroll = level.getPlayer().y - screen.height / 2;
		level.render(xScroll, yScroll, screen);
		screen.renderText("Hp: " + level.getPlayer().healthPoints + " Attack: " + (Skill.skills[0]).getLevel(), 0, 0, false);
		screen.renderText(mouse.getX() + " " + mouse.getY(), 0, 50, false);
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
