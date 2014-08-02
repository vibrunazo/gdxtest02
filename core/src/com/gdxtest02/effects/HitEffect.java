package com.gdxtest02.effects;

public class HitEffect extends SpineBaseEffect {

	public HitEffect() {
		super();
	}

	public HitEffect(float scale) {
		super(scale);
	}

	/* (non-Javadoc)
	 * @see com.gdxtest02.effects.SpineBaseEffect#getSkeletonname()
	 */
	@Override
	public String getSkeletonname() {
		return "hit";
	}

	/* (non-Javadoc)
	 * @see com.gdxtest02.Effect#getAnimSpeed()
	 */
	@Override
	public float getAnimSpeed() {
		return 2f;
	}
	
	

}
