package com.gdxtest02.effects;

public class MSEffect extends SpineBaseEffect {

	public MSEffect() {
		super();
	}

	public MSEffect(float scale) {
		super(scale);
	}

	/* (non-Javadoc)
	 * @see com.gdxtest02.effects.SpineBaseEffect#getSkeletonname()
	 */
	@Override
	public String getSkeletonname() {
		return "ms";
	}

	/* (non-Javadoc)
	 * @see com.gdxtest02.Effect#getAnimSpeed()
	 */
	@Override
	public float getAnimSpeed() {
		return 0.8f;
	}
	
	

}
