package com.gdxtest02.core.anims;

import com.esotericsoftware.spine.Animation;
import com.gdxtest02.core.AnimRenderer;
import com.gdxtest02.core.CharAnim;

public class PunchRight01 extends CharAnim {

	private Animation punchAnimation;

	public PunchRight01(AnimRenderer animRenderer) {
		super(animRenderer);
		name = "punchright01";
	}

	@Override
	protected void ini() {
		punchAnimation = sd.findAnimation(name);
	}

	/* (non-Javadoc)
	 * @see com.gdxtest02.CharAnim#draw()
	 */
	@Override
	public void draw() {
		super.draw();
		punchAnimation.apply(skeleton, lastTime, animationTime, false, null);
		if (animationTime > punchAnimation.getDuration()) {
			end();
		}
	}

}
