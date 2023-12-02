package com.boxsmith.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.boxsmith.game.Game;
import com.boxsmith.gfx.ui.menus.Menu;
import com.boxsmith.level.Level;

public class Mouse implements MouseListener, MouseMotionListener {

	private static int mouseX = -1;
	private static int mouseY = -1;
	private static int mouseButton = -1;

	public static int getX() {
		return mouseX;
	}

	public static int getY() {
		return mouseY;
	}

	public static int getButton() {
		return mouseButton;
	}

	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	public void mouseClicked(MouseEvent e) {
		Menu currentMenu = Game.state.getStateMenu();
		Level currentLevel = Game.state.getStateLevel();

		if(currentMenu != null) {
			if (currentMenu.mouseClick() != null) return; //Prevents clicking through the UI.
		}
		if(currentLevel != null) {
			currentLevel.mouseClick();
		}
	}

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {
		mouseButton = e.getButton();
	}

	public void mouseReleased(MouseEvent e) {
		mouseButton = -1;
	}

	public static int[] screenToWorld(Boolean withScreenOffset) {
		int xOffset = withScreenOffset ? Game.screen.xOffset : 0;
		int yOffset = withScreenOffset ? Game.screen.yOffset : 0;
		int[] coords = {xOffset + mouseX / Game.SCALE, yOffset + mouseY / Game.SCALE};
		return coords;
	}

}
