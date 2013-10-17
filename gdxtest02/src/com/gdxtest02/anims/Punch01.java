package com.gdxtest02.anims;

import com.esotericsoftware.spine.Animation;
import com.gdxtest02.AnimRenderer;
import com.gdxtest02.CharAnim;

public class Punch01 extends CharAnim {

	private Animation punchAnimation;

	public Punch01(AnimRenderer animRenderer) {
		super(animRenderer);
		punchAnimation = sd.findAnimation("punch01");
	}

	/* (non-Javadoc)
	 * @see com.gdxtest02.CharAnim#draw()
	 */
	@Override
	public void draw() {
		super.draw();
		punchAnimation.apply(skeleton, lastTime, animationTime, true, null);
	}

}
