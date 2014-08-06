package com.gdxtest02.effects;

public class ShieldEffect extends SpineBaseEffect {

	public ShieldEffect() {
		super();
	}

	public ShieldEffect(float scale) {
		super(scale);
	}

	@Override
	public String getSkeletonname() {
		return "shield";
	}

	@Override
	public float getAnimSpeed() {
		return 0.7f;
	}
	
	

}
