package com.gdxtest02;

import static com.gdxtest02.GdxTest02.log;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.spine.Animation;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonData;

public class CharAnim {

	protected AnimRenderer renderer;
	protected Skeleton skeleton;
	protected SkeletonData sd;
//	protected Animation standAnimation;
//	private Animation punchAnimation;
//	private Animation winkAnimation;
	protected float animationTime;
	protected float delta;
	protected float lastTime;

	public CharAnim(AnimRenderer animRenderer) {
		renderer = animRenderer;
		skeleton = renderer.getSkeleton();
		sd = renderer.getSkeletonData();
		
		ini();
	}

	private void ini() {
//		standAnimation = sd.findAnimation("stand01");
//		punchAnimation = sd.findAnimation("punch01");
//		winkAnimation = sd.findAnimation("wink");		
		
		animationTime = 0;
//		winkTime = 0;
//		log("anim ini");
	}

	public void draw() {
		delta = Gdx.graphics.getDeltaTime();
		lastTime = animationTime;
		animationTime += delta;
//		log("A animationTime: " + animationTime + " last: " + lastTime + " delta: " + delta);
//		if (winkTime > 1.5) {
//			log("A winkTime: " + winkTime + " last: " + lastWinkTime + " delta: " + delta);
//			winkTime = 0;
//		}
//		lastWinkTime = winkTime;
//		winkTime += delta;
//		standAnimation.apply(skeleton, lastTime, animationTime, true, null);
//		winkAnimation.apply(skeleton, lastWinkTime*10, winkTime*10, false, null);
	}
	
}
