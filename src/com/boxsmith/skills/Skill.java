package com.boxsmith.skills;

public abstract class Skill {
	public int expoints;
	public static Skill[] skills = new Skill[]{new Attack(), new Health()};

	public int getLevel() {
		return (int) Math.sqrt(expoints) + 1;
	}

	public static int levelToExp(int level) {
		return (int) Math.pow(level, 2);
	}
	
}
