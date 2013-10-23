package com.gdxtest02.anims;

import static com.gdxtest02.GdxTest02.log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.esotericsoftware.spine.Animation;
import com.gdxtest02.AnimRenderer;
import com.gdxtest02.CharAnim;

public class Castup02 extends Castup01 {

	public Castup02(AnimRenderer animRenderer) {
		super(animRenderer);
	}

	/* (non-Javadoc)
	 * @see com.gdxtest02.anims.Castup01#setParticles()
	 */
	@Override
	protected void setParticles() {
		effect1 = new ParticleEffect();
		effect1.load(Gdx.files.internal("effects/part02.p"), Gdx.files.internal("effects"));
		effect1.start();
		renderer.addParticle(effect1);

		effect2 = new ParticleEffect();
		effect2.load(Gdx.files.internal("effects/part02.p"), Gdx.files.internal("effects"));
		effect2.start();
		renderer.addParticle(effect2);		
	}

	
}
