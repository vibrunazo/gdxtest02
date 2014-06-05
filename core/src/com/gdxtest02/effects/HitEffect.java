package com.gdxtest02.effects;

import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdxtest02.AnimRenderer;
import com.gdxtest02.Effect;

public class HitEffect extends Effect {

	private ParticleEffect particle;

	
	
	@Override
	public void update(float delta) {
		super.update(delta);
		particle.setPosition(x, y);
		particle.update(delta);
	}

	@Override
	public void draw(SpriteBatch batch) {
		particle.draw(batch);
	}

	@Override
	public void start() {
		particle = AnimRenderer.getParticleFromName("hit");
	}
	

}
