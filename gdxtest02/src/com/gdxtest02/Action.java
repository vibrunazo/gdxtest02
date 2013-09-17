package com.gdxtest02;

import com.badlogic.gdx.Gdx;

public abstract class Action {
	protected String name = "Action";
	protected float power = 100;
	protected int cooldown = 0;
	private int curcooldown = 0;
	protected int duration = 5;
	private String description = "This is a skill";
	private float avgdps;
	
	public Action() {
		ini();
	}

	public Action(float value) {
		power = value;
		ini();
	}
	
	public Action(float value, int cooldown) {
		power = value;
		this.cooldown = cooldown;
		ini();
	}
	
	public Action(float value, int cooldown, int duration) {
		power = value;
		this.cooldown = cooldown;
		this.duration = duration;
		ini();
	}

	/**Do whatever is it that you do
	 * @param self yourself, the char doing the action
	 * @param target your target
	 */
	public void act(Char self, Char target) {
//		act();
		if (curcooldown > 0) return;
		if (getCurcooldown() > 0) return;
		go(self, target);
		if (cooldown > 0) {
			curcooldown = cooldown + 1;
		}
	}

	/**Updates the current cooldown, reduce it by 1
	 * 
	 */
	public void updateCooldown() {
		if (curcooldown > 0) curcooldown -= 1;
	}
	
	/**Reset all stats to initial values
	 * 
	 */
	public void reset() {
		curcooldown = 0;
	}
	
	/**The actual action class needs to implement this method to
	 * do its thing
	 * @param self the Char doing the action
	 * @param target the Char being targetted
	 */
	abstract protected void go(Char self, Char target);
	
	/**Do whatever initialization and updates here
	 * call this to update description
	 * 
	 */
	abstract public void ini();

	/**Gets the action name
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**Sets the name, returns self
	 * @param name
	 * @return
	 */
	public Action setName(String name) {
		this.name = name;
		return this;
	}
	
	/**Gets the action description
	 * @return
	 */
	public String getDescription() {
		return description;
	}
	/**Sets the description, returns self
	 * @param name
	 * @return
	 */
	public Action setDescription(String description) {
		this.description = description;
		return this;
	}
	
	/**Gets the tooltip description, includes formal description,
	 * power, cost, cooldown
	 * @return
	 */
	public String getTooltip() {
		return getName() + " - " + getDescription() + " " + getCooldown() + "sec cooldown.";
	}

	/**Gets the maximum cooldown
	 * @return
	 */
	public int getCooldown() {
		return cooldown;
	}
	/**Sets the maximum cooldown, returns self
	 * @param cooldown
	 * @return
	 */
	public Action setCooldown(int cooldown) {
		this.cooldown = cooldown;
		return this;
	}
	
	/**Get duration
	 * @return
	 */
	public int getDuration() {
		return duration;
	}
	/**Sets duration, returns self
	 * @param duration
	 * @return
	 */
	public Action setDuration(int duration) {
		this.duration = duration;
		return this;
	}

	/**Returns the current cooldown
	 * @return
	 */
	public int getCurcooldown() {
		return curcooldown;
	}

	/**Sets the current cooldown, returns self
	 * @param curcooldown
	 * @return
	 */
	public Action setCurcooldown(int curcooldown) {
		this.curcooldown = curcooldown;
		return this;
	}
	
	public void incCurcooldown(int delta) {
		this.curcooldown += delta;
	}
	
	/**Returns if this action is legal or not right
	 * Checks cooldown, mana, requirements etc
	 */
	public boolean isLegal() {
//		log("islegal name: " + name + " cooldown: " + cooldown + " curcooldown: " + curcooldown);
		if (curcooldown == 0) return true;
		return false;
	}
	
	/**Logs text to Gdx.app.log()
	 * @param text
	 */
	private void log(String text) {
		Gdx.app.log("gdxtest", text);
	}

	/**
	 * @return the power
	 */
	public float getPower() {
		return power;
	}

	/**
	 * @param power the power to set
	 */
	public void setPower(float power) {
		this.power = power;
	}
	
	public void setAvgDps(float avg) {
		avgdps =  avg;
	}
	
	public float getAvgDps() {
		return avgdps;
	}
	
	/**How many damage this skill does after X rounds
	 * @param rounds
	 * @return
	 */
	public float getDmgAfterRounds(int rounds) {
		return power;
	}
	
	/**How much heal this skill does after X rounds
	 * @param rounds
	 * @return
	 */
	public float getHealAfterRounds(int rounds) {
		return 0;
	}
	
	/**How much damage this skill can do this round
	 * @param round
	 * @param maxrounds
	 * @return
	 */
	public float getDmgThisRound(int round, int maxrounds) {
		return getDmgAfterRounds(maxrounds - round + 1);
	}
	
}
