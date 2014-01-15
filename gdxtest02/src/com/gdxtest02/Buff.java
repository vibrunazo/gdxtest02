package com.gdxtest02;

public abstract class Buff {
	protected String name;
	protected float power;
	protected int duration;
	
	private int DEFAULT_POWER = 200;
	private int DEFAULT_DURATION = 2;
	private String DEFAULT_NAME = "Buff";
	
	private static int id = 0;
	private int thisid = 0;
	
	public Buff() {
		setPower(DEFAULT_POWER);
		setDuration(DEFAULT_DURATION);
		ini();
	}
	
	public Buff(float power) {
		setPower(power);
		setDuration(DEFAULT_DURATION);
		ini();
	}
	
	public Buff(float power, int duration) {
		setPower(power);
		setDuration(duration);
		ini();
	}
	
	/**initialization
	 * 
	 */ 
	private void ini() {
		setName(DEFAULT_NAME);
		thisid = id++;
		
	}
	
	/**Do whatever is it that you do
	 * @param self yourself, the char doing the action
	 * @param target your target
	 */
	public abstract void act(Char self);
	
	/**Duration is how long the buff lasts, in seconds or turns
	 * -1 means it's infinite
	 */
	public void incDuration(int delta) {
		duration += delta;	
	}
	
	public Buff setPower(float power) {
		this.power = power;
		return this;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public Buff setName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * @return the duration
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * @param duration the duration to set
	 */
	public Buff setDuration(int duration) {
		this.duration = duration;
		return this;
	}

	/**
	 * @return the power
	 */
	public float getPower() {
		return power;
	}
	
}
