package com.boxsmith.entity.weapons;

import com.boxsmith.entity.Entity;
import com.boxsmith.game.GameTimer;
import com.boxsmith.gfx.Screen;

public class Weapon extends Entity {
	public int damage, speed, range;
	public GameTimer timer;
	
	
	/**
	 * 
	 * @param damage
	 * @param speed
	 * @param range
	 */
	public Weapon(int damage, int speed, int range){
		this.range = range;
		this.damage = damage;
		this.speed = speed;
		timer = new GameTimer(speed);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Screen screen) {
		// TODO Auto-generated method stub
		
	}
}
