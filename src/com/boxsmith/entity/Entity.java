package com.boxsmith.entity;

import java.util.Random;

import com.boxsmith.gfx.Screen;
import com.boxsmith.level.Level;

public abstract class Entity {
	
	public int x, y;
	
	private boolean removed = false;
	
	protected Level level;
	protected Random random;
	
	public abstract void update();
	
	public abstract void render (Screen screen);
	
	public void remove(){
		removed = true;
	}
	
	public boolean isRemoved(){
		return removed;
	}
	
	public void intit(Level level){
		this.level = level;
	}
}
