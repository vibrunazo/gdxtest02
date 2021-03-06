package com.gdxtest02.anims;

import com.esotericsoftware.spine.Animation;
import com.gdxtest02.AnimRenderer;
import com.gdxtest02.CharAnim;

public class GetHit01 extends CharAnim {

	private Animation animation;

	public GetHit01() {
		super();
		name = "gethit01";
		setSpeed(2f);
	}

	@Override
	protected void ini() {
		animation = sd.findAnimation(name);
	}

	/* (non-Javadoc)
	 * @see com.gdxtest02.CharAnim#draw()
	 */
	@Override
	public void draw() {
		super.draw();
		animation.apply(skeleton, lastTime, animationTime, false, null);
		if (animationTime > animation.getDuration()) {
			end();
		}
	}

}
