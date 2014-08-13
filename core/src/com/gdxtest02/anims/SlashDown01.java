package com.gdxtest02.anims;

import com.esotericsoftware.spine.Animation;
import com.gdxtest02.AnimRenderer;
import com.gdxtest02.CharAnim;

public class SlashDown01 extends CharAnim {

	private Animation punchAnimation;
	private int hits;

	public SlashDown01() {
		super();
		name = "slashdown01";
	}

	@Override
	protected void ini() {
		punchAnimation = sd.findAnimation(name);
		hits = 0;
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
		
		if (animationTime > 0.55 & hits == 0) {
			hits++;
			
			createHitEffect();
		}
	}

}
