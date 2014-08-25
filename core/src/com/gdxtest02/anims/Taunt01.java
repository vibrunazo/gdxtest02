package com.gdxtest02.anims;

import com.esotericsoftware.spine.Animation;
import com.gdxtest02.AnimRenderer;
import com.gdxtest02.CharAnim;

public class Taunt01 extends CharAnim {

	private Animation punchAnimation;
	
	public Taunt01() {
		super();
		name = "taunt01";
		
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
