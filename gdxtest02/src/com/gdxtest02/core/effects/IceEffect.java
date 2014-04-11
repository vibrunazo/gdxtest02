package com.gdxtest02.core.effects;

import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdxtest02.core.AnimRenderer;
import com.gdxtest02.core.Effect;

public class IceEffect extends Effect {

	private ParticleEffect particle;

	
	
	@Override
	public void update(float delta) {
		particle.setPosition(x, y);
		particle.update(delta);
	}

	@Override
	public void draw(SpriteBatch batch) {
		particle.draw(batch);
	}

	@Override
	public void start() {
		particle = AnimRenderer.getParticleFromName("ice");
	}
	

}
