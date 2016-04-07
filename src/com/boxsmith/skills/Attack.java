package com.boxsmith.skills;

public class Attack extends Skill{
	public static int rank = 0;
	
	public Attack(){
		expoints = 0;
	}
	
	public int damageMod(){
		return getLevel() * 2;
	}

}
