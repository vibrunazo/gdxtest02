package com.gdxtest02.effects;

public class ExplosionEffect extends SpineBaseEffect {

	public ExplosionEffect() {
		super();
	}

	public ExplosionEffect(float scale) {
		super(scale);
	}

	@Override
	public String getSkeletonname() {
		return "explosion";
	}

	@Override
	public float getAnimSpeed() {
		return 1.0f;
	}
	
	

}
