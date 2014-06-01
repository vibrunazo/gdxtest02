package com.gdxtest02;

import java.lang.reflect.Constructor;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.gdxtest02.effects.FireEffect;
import com.gdxtest02.effects.GreenEffect;
import com.gdxtest02.effects.IceEffect;

public abstract class Buff {
	protected String name;
	protected float power;
	protected int duration;
	protected Effect effect;
	protected Char target;
	
	private int DEFAULT_POWER = 200;
	private int DEFAULT_DURATION = 2;
	private String DEFAULT_NAME = "Buff";

	
	private static int id = 0;
	private int thisid = 0;
	private boolean visible = false;
	
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
	protected void ini() {
		setName(DEFAULT_NAME);
		thisid = id++;
		
		effect = new GreenEffect();
		
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

	public void draw(SpriteBatch batch, float delta) {
		if (!visible ) return;
		effect.setPosition(target.getPosX(), target.getPosY());
		effect.update(delta);
		effect.draw(batch);
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public void setTarget(Char target) {
		this.target = target;
		visible = true;
		effect.start();
		
	}
	
	/**
	 * @return the effect
	 */
	public Effect getEffect() {
		return effect;
	}

	/**
	 * @param effect the effect to set
	 */
	public void setEffect(Effect effect) {
		this.effect = effect;
	}
	
	public Buff getClone() {
//		return new Buff(power, duration);
		
		try {
			Constructor<? extends Buff> constructor = this.getClass().getConstructor();
			Object clone = constructor.newInstance();
			Buff b = (Buff)clone;
			b.duration = this.duration;
			b.power = this.power;
			return b;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

}