package com.gdxtest02.anims;

import com.esotericsoftware.spine.Animation;
import com.gdxtest02.AnimRenderer;
import com.gdxtest02.CharAnim;

public class PunchRight01 extends CharAnim {

	private Animation punchAnimation;
	private int hits;

	public PunchRight01() {
		super();
		name = "punchright01";
	}

	@Override
	protected void ini() {
		punchAnimation = sd.findAnimation(name);
		hits = 0;
		getOwner().setIsattackanim(true);
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
		
		if (animationTime > 0.25 & hits == 0) {
			hits++;
			getOwner().setIsattackanim(false);
			
			createHitEffect();
		}
	}

}
