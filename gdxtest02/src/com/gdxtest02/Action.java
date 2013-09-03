package com.gdxtest02;

import com.badlogic.gdx.Gdx;

public abstract class Action {
	protected String name = "Action";
	protected int power = 100;
	protected int cooldown = 0;
	private int curcooldown = 0;
	protected int duration = 5;
	private String description = "This is a skill";
	
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
//		act();
		if (curcooldown > 0) return;
		if (getCurcooldown() > 0) return;
		go(self, target);
		if (cooldown > 0) {
			curcooldown = cooldown;
		}
	}

	/**Updates the current cooldown, reduce it by 1
	 * 
	 */
	public void updateCooldown() {
		if (curcooldown > 0) curcooldown -= 1;
	}
	
	/**The actual action class needs to implement this method to
	 * do its thing
	 * @param self the Char doing the action
	 * @param target the Char being targetted
	 */
	abstract protected void go(Char self, Char target);

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
	
	/**Gets the action name
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

}
