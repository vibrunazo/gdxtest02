package com.gdxtest02;

public class Action {
	protected String name;
	protected int power;
	protected int cooldown;
	protected int duration;
	
	protected int DEFAULT_COOLDOWN = 0;
	protected int DEFAULT_DURATION = 5;
	protected int DEFAULT_POWER = 100;
	protected String DEFAULT_NAME = "Action";
	
	
	public Action() {
		power = DEFAULT_POWER;
		name = DEFAULT_NAME;
		duration = DEFAULT_DURATION;
	}

	public Action(int value) {
		power = value;
		name = DEFAULT_NAME;
		duration = DEFAULT_DURATION;
	}
	
	public Action(int value, int duration) {
		power = value;
		this.duration = duration;
		name = DEFAULT_NAME;
	}
	
	/**Do whatever is it that you do
	 * @param self yourself, the char doing the action
	 * @param target your target
	 */
	public void act(Char self, Char target) {
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getCooldown() {
		return cooldown;
	}
	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}
	
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}

}
