package com.gdxtest02;

import java.lang.reflect.Constructor;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.gdxtest02.effects.FireEffect;
import com.gdxtest02.effects.GreenEffect;
import com.gdxtest02.effects.IceEffect;
import com.gdxtest02.util.Util;

public abstract class Buff {
	protected String name;
	protected float power;
	protected int duration;
	private Array<Effect> effects;
	protected Char target;
	/**final x position of the effect, after random change
	 * 
	 */
	private float effectOffsetX;
	/**final y position of the effect, after random change
	 * 
	 */
	private float effectOffsetY;
	
	/**x Position of the effect, before random change
	 * 
	 */
	private float effectBaseOffsetX;
	/**y Position of the effect, before random change
	 * 
	 */
	private float effectBaseOffsetY;
	/** by how much the offset changes randomly with each buff
	 * 
	 */
	private float effectRandomDeltaOffset = 50;
	
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
		
		setRandomOffset();
		
		setEffect(new GreenEffect());
//		Util.log("Buff ini, effect offset x: " + x + " y: " + y);
	}

	public void setRandomOffset() {
		float x = MathUtils.random()*getEffectRandomDeltaOffset() - getEffectRandomDeltaOffset()/2;
		float y = MathUtils.random()*getEffectRandomDeltaOffset() - getEffectRandomDeltaOffset()/2;
		effectOffsetX = x;
		effectOffsetY = y;
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
//		effect.setPosition(target.getPosX(), target.getPosY() + 50);
		for (Effect effect : effects) {
			effect.update(delta);
			effect.draw(batch);
		}
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
		for (Effect effect : effects) {
			effect.setAttachedChar(target);
			effect.start();
		}
	}
	
	/**
	 * @return the effect
	 */
	public Effect getEffect() {
		return effects.first();
	}
	
	/**
	 * @return the effect
	 */
	public Effect getEffect(int index) {
		return effects.get(index);
	}

	/**
	 * @param effect the effect to set
	 */
	public void setEffect(Effect effect) {
		setEffect(0, effect);
	}
	
	/**
	 * @param effect the effect to set
	 */
	public void setEffect(int index, Effect effect) {
		if (effects == null) effects = new Array<Effect>();
		if (effects.size <= index) effects.insert(index, effect); // TODO might need to check previous slots
		else effects.set(index, effect);
		effect.setAttachedBuff(this);
		if (target != null) effect.setAttachedChar(target);
	}
	
	/**
	 * @return the effectOffsetX
	 */
	public float getEffectOffsetX() {
		return effectOffsetX;
	}

	/**
	 * @return the effectOffsetY
	 */
	public float getEffectOffsetY() {
		return effectOffsetY;
	}
	
	public void resetEffectPosition() {
		getEffect().setOffset(effectBaseOffsetX, effectBaseOffsetY);
	}

	/**
	 * @param effectBaseOffsetX the effectBaseOffsetX to set
	 * @param effectBaseOffsetY the effectBaseOffsetY to set
	 */
	public void setEffectBaseOffset(float effectBaseOffsetX, float effectBaseOffsetY) {
		this.effectBaseOffsetX = effectBaseOffsetX;
		this.effectBaseOffsetY = effectBaseOffsetY;
	}

	/**
	 * @return the effectRandomDeltaOffset
	 */
	public float getEffectRandomDeltaOffset() {
		return effectRandomDeltaOffset;
	}

	/**
	 * @param effectRandomDeltaOffset the effectRandomDeltaOffset to set
	 */
	public void setEffectRandomDeltaOffset(float effectRandomDeltaOffset) {
		this.effectRandomDeltaOffset = effectRandomDeltaOffset;
	}
	

}
