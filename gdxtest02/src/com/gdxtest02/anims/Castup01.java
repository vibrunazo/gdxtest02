package com.gdxtest02.anims;

import com.esotericsoftware.spine.Animation;
import com.gdxtest02.AnimRenderer;
import com.gdxtest02.CharAnim;

public class Castup01 extends CharAnim {

	private Animation punchAnimation;

	public Castup01(AnimRenderer animRenderer) {
		super(animRenderer);
		punchAnimation = sd.findAnimation("castup01");
	}

	/* (non-Javadoc)
	 * @see com.gdxtest02.CharAnim#draw()
	 */
	@Override
	public void draw() {
		super.draw();
		
		punchAnimation.apply(skeleton, lastTime, animationTime, false, null);
		float x = skeleton.findBone("hand_R").getWorldX();
		float y = skeleton.findBone("hand_R").getWorldY();
		
		if (animationTime > punchAnimation.getDuration()) {
			end();
		}
	}

}
