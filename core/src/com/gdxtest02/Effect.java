package com.gdxtest02;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Effect  {
	
	protected float x;
	protected float y;
	protected int duration;

	public Effect() {
		
	}

	public void update(float delta) {
		
	}

	public void draw(SpriteBatch batch) {
		
	}

	public void start() {
		
	}

	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void setDuration(int i) {
		// TODO Auto-generated method stub
		
	}

	public Effect getClone() {
		try {
			Constructor<? extends Effect> constructor = this.getClass().getConstructor();
			Object clone = constructor.newInstance();
			return (Effect)clone;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return null;

	}

}
