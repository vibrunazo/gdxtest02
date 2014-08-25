package com.gdxtest02.anims;

import com.esotericsoftware.spine.Animation;
import com.gdxtest02.AnimRenderer;
import com.gdxtest02.CharAnim;

public class Taunt02 extends CharAnim {
	
	private float winkTime;
	private float lastWinkTime;
	private Animation winkAnimation;
	private Animation animation;
	private float winkDelta;
	private int winks;

	public Taunt02() {
		super();
		name = "taunt02";
	}

	/* (non-Javadoc)
	 * @see com.gdxtest02.CharAnim#draw()
	 */
	@Override
	public void draw() {
		super.draw();
		if (winkTime > winkDelta && winks < 3) {
			if (winks == 0) winkDelta = 0.2f;
			winkTime = 0;
			winks++;
		}
		lastWinkTime = winkTime;
		winkTime += delta;
		animation.apply(skeleton, lastTime, animationTime, false, null);
		winkAnimation.apply(skeleton, lastWinkTime*10, winkTime*10, false, null);
		
		if (animationTime > animation.getDuration()) {
			end();
		}
	}

	@Override
	protected void ini() {
		winkAnimation = sd.findAnimation("wink");	
		animation = sd.findAnimation(name);
		winkTime = 1f;
		winkDelta = 1.4f;
		winks = 0;
	}

}
