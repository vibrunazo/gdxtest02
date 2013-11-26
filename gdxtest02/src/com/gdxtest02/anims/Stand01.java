package com.gdxtest02.anims;

import static com.gdxtest02.GdxTest02.log;

import com.esotericsoftware.spine.Animation;
import com.gdxtest02.AnimRenderer;
import com.gdxtest02.CharAnim;

public class Stand01 extends CharAnim {
	
	private float winkTime;
	private float lastWinkTime;
	private Animation winkAnimation;
	private Animation standAnimation;

	public Stand01(AnimRenderer animRenderer) {
		super(animRenderer);
		name = "stand01";
		winkTime = 0;
		
	}

	/* (non-Javadoc)
	 * @see com.gdxtest02.CharAnim#draw()
	 */
	@Override
	public void draw() {
		super.draw();
		if (winkTime > 1.5) {
//			log("A winkTime: " + winkTime + " last: " + lastWinkTime + " delta: " + delta);
			winkTime = 0;
		}
		lastWinkTime = winkTime;
		winkTime += delta;
		standAnimation.apply(skeleton, lastTime, animationTime, true, null);
		winkAnimation.apply(skeleton, lastWinkTime*10, winkTime*10, false, null);
		
	}

	@Override
	protected void ini() {
		winkAnimation = sd.findAnimation("wink");	
		standAnimation = sd.findAnimation(name);
	}

}
