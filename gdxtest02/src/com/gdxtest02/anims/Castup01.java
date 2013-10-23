package com.gdxtest02.anims;

import static com.gdxtest02.GdxTest02.log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.esotericsoftware.spine.Animation;
import com.gdxtest02.AnimRenderer;
import com.gdxtest02.CharAnim;

public class Castup01 extends CharAnim {

	private Animation punchAnimation;
	protected ParticleEffect effect1;
	protected ParticleEffect effect2;
	private float x;
	private float y;

	public Castup01(AnimRenderer animRenderer) {
		super(animRenderer);
		punchAnimation = sd.findAnimation("castup01");
		
		setParticles();
	}

	protected void setParticles() {
		effect1 = new ParticleEffect();
		effect1.load(Gdx.files.internal("effects/part01.p"), Gdx.files.internal("effects"));
		effect1.start();
		renderer.addParticle(effect1);
		
		effect2 = new ParticleEffect();
		effect2.load(Gdx.files.internal("effects/part01.p"), Gdx.files.internal("effects"));
		effect2.start();
		renderer.addParticle(effect2);		
	}

	/* (non-Javadoc)
	 * @see com.gdxtest02.CharAnim#draw()
	 */
	@Override
	public void draw() {
		super.draw();
		
		x = skeleton.findBone("hand_R").getWorldX();
		y = skeleton.findBone("hand_R").getWorldY();
		effect1.setPosition(x, y);
		
		x = skeleton.findBone("hand_L").getWorldX();
		y = skeleton.findBone("hand_L").getWorldY();
		effect2.setPosition(x, y);
		
		punchAnimation.apply(skeleton, lastTime, animationTime, false, null);
		
		if (animationTime > punchAnimation.getDuration()) {
			end();
		}
	}

}
