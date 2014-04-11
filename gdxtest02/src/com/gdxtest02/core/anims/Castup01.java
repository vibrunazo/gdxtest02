package com.gdxtest02.core.anims;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.esotericsoftware.spine.Animation;
import com.gdxtest02.core.AnimRenderer;
import com.gdxtest02.core.CharAnim;
import com.gdxtest02.core.Effect;

import static com.gdxtest02.core.GdxTest02.log;

public class Castup01 extends CharAnim {

	private Animation animation;
	protected Effect effect1;
	protected Effect effect2;
	private float x;
	private float y;

	public Castup01(AnimRenderer animRenderer) {
		super(animRenderer);
		name = "castup01";
	}

	@Override
	protected void ini() {
		animation = sd.findAnimation(name);
		effect1 = addAnimEffect();
		effect1.setDuration(1500);
		
		effect2 = addAnimEffect();
		effect2.setDuration(1500);
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
		
		animation.apply(skeleton, lastTime, animationTime, false, null);
		
		if (animationTime > animation.getDuration()) {
			end();
		}
	}

}
