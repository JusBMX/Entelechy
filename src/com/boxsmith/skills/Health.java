package com.boxsmith.skills;

public class Health extends Skill{
	public static int rank = 1;
	
	public Health (){
		expoints = levelToExp(10);
	}

	public int healthMod(){
		return getLevel() * 2;
	}
	
}
