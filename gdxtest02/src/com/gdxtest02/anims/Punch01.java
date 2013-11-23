package com.gdxtest02.anims;

import com.esotericsoftware.spine.Animation;
import com.gdxtest02.AnimRenderer;
import com.gdxtest02.CharAnim;

public class Punch01 extends CharAnim {

	private Animation punchAnimation;
	
	private int hits = 0;

	public Punch01(AnimRenderer animRenderer) {
		super(animRenderer);
		name = "punch01";
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
		
		if (animationTime > 0.3 & hits == 0) {
			hits++;
			createHitEffect();
		}
	}

}
