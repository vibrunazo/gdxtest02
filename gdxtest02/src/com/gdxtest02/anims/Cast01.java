package com.gdxtest02.anims;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.esotericsoftware.spine.Animation;
import com.gdxtest02.AnimRenderer;
import com.gdxtest02.CharAnim;
import static com.gdxtest02.GdxTest02.log;

public class Cast01 extends CharAnim {

	private Animation animation;
	private ParticleEffect effect;
	private float x;
	private float y;

	public Cast01(AnimRenderer animRenderer) {
		super(animRenderer);
		animation = sd.findAnimation("cast01");
		
		iniParticles();
	}
	
	protected void iniParticles() {
		effect = addEffect();
	}

	/* (non-Javadoc)
	 * @see com.gdxtest02.CharAnim#draw()
	 */
	@Override
	public void draw() {
		super.draw();
		
		animation.apply(skeleton, lastTime, animationTime, false, null);
		if (animationTime < 0.8f) {
			x = skeleton.findBone("hand_R").getWorldX();
			y = skeleton.findBone("hand_R").getWorldY() + 10;
		}
		else {
			int flip = 1;
			if (skeleton.getFlipX()) flip = -1;
			x += delta*800*flip;
		}
		
//		log("animtime: " + animationTime);
		
		effect.setPosition(x, y);
		
		if (animationTime > animation.getDuration()) {
			end();
		}
	}

}
