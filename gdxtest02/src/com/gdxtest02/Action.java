package com.gdxtest02;

public class Action {
	protected String name = "Action";
	protected int power = 100;
	protected int cooldown = 0;
	private int curcooldown = 0;
	protected int duration = 5;
	
	public Action() {
	}

	public Action(int value) {
		power = value;
	}
	
	public Action(int value, int cooldown) {
		power = value;
		this.cooldown = cooldown;
	}
	
	/**Do whatever is it that you do
	 * @param self yourself, the char doing the action
	 * @param target your target
	 */
	public void act(Char self, Char target) {
		act();
	}
	
	public void act() {
		if (curcooldown > 0) {
			curcooldown -= 1;
			return;
		}
		if (cooldown > 0) {
			curcooldown = cooldown;
		}
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

	public int getCurcooldown() {
		return curcooldown;
	}

	public void setCurcooldown(int curcooldown) {
		this.curcooldown = curcooldown;
	}
	
	public void incCurcooldown(int delta) {
		this.curcooldown += delta;
	}

}
