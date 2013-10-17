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
		animationTime = 0;
	}

	public void draw() {
		delta = Gdx.graphics.getDeltaTime();
		lastTime = animationTime;
		animationTime += delta;
	}
	
	/**ends animation and go back to default anim
	 * 
	 */
	public void end() {
		renderer.setAnimToDefault();
	}
	
}
