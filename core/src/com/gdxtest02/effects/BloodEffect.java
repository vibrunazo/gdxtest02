package com.gdxtest02.effects;

import com.gdxtest02.util.Util;

public class BloodEffect extends SimpleParticleBaseEffect {

	/* (non-Javadoc)
	 * @see com.gdxtest02.effects.SimpleParticleBaseEffect#getParticlename()
	 */
	@Override
	public String getParticlename() {
		return "blood";
	}

	/* (non-Javadoc)
	 * @see com.gdxtest02.effects.SimpleParticleBaseEffect#start()
	 */
	@Override
	public void start() {
		// TODO Auto-generated method stub
		super.start();
		setDuration(1000);
	}
	
	

}
