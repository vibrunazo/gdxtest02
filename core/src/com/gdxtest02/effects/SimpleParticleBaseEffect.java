package com.gdxtest02.effects;

import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter.ScaledNumericValue;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdxtest02.AnimRenderer;
import com.gdxtest02.Effect;
import com.gdxtest02.util.Util;

public class SimpleParticleBaseEffect extends Effect {

	private ParticleEffect particle;
	protected String particlename;
	private boolean isFlipped = false;
	
	@Override
	public void update(float delta) {
		super.update(delta);
		particle.setPosition(x, y);
		particle.update(delta);
//		particle.setFlip(true, true);
//		particle.getEmitters().get(0)..setFlip(false, attachedChar.getFlipX());
	}

	@Override
	public void draw(SpriteBatch batch) {
		particle.draw(batch);
	}

	@Override
	public void start() {
		particle = AnimRenderer.getParticleFromName(getParticlename());
		hasStarted = true;
		setScale(getScale());
		if (attachedChar.getFlipX() != isFlipped) {
			isFlipped = attachedChar.getFlipX();
			flipParticleX();
		}
	}

	/* (non-Javadoc)
	 * @see com.gdxtest02.Effect#setScale(float)
	 */
	@Override
	public void setScale(float scale) {
		super.setScale(scale);
		if (!hasStarted) return;
		Util.scaleParticle(particle, scale);
	}

	/**
	 * @return the particlename
	 */
	public String getParticlename() {
		return particlename;
	}
	
	private void flipParticleX() {
		ParticleEmitter e = particle.getEmitters().first();
//		ScaledNumericValue v = e.getVelocity();
//		v.setHigh(-1*v.getHighMin(), -1*v.getHighMax());
		ScaledNumericValue a = e.getAngle();
		a.setHigh(180-a.getHighMin(), 180-a.getHighMax());
		a.setLow(180-a.getLowMin(), 180-a.getLowMax());
		ScaledNumericValue r = e.getRotation();
		r.setHigh(-r.getHighMin(), -r.getHighMax());
		r.setLow(-r.getLowMin(), -r.getLowMax());
//		particle.flipY();
	}
	

}
