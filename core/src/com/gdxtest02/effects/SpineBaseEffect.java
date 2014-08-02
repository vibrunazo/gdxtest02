package com.gdxtest02.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.esotericsoftware.spine.Animation;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonData;
import com.esotericsoftware.spine.SkeletonJson;
import com.esotericsoftware.spine.SkeletonRenderer;
import com.gdxtest02.AnimRenderer;
import com.gdxtest02.Effect;
import com.gdxtest02.util.Util;

public class SpineBaseEffect extends Effect {

	private SkeletonRenderer skrenderer;
	private Skeleton skeleton;
	private Animation skanim;
	private float lastTime;
	private float animationTime;
	private String skeletonname;
	
	@Override
	public void start() {
		hasStarted = true;
		setScale(getScale());
		
		loadSkeleton(1f);
	}

	public SpineBaseEffect() {
		super();
		setSkeletonname("hit");
	}

	/* (non-Javadoc)
	 * @see com.gdxtest02.Effect#setScale(float)
	 */
	@Override
	public void setScale(float scale) {
		super.setScale(scale);
	}

	private void loadSkeleton(float scale) {
		if (skeletonname == null || skeletonname.equals("")) skeletonname = "proj";
		skeleton = Util.loadSkeletonFromName(skeletonname, scale);
		SkeletonData sd = skeleton.getData();
		
		// animation will have the same name as the skeleton
		skanim = sd.findAnimation(skeletonname);
	}	
	
	public void update(float delta) {
		lastTime = animationTime;
		animationTime += delta;
		
		skeleton.setPosition(x, y);
		skeleton.update(delta);
		skeleton.updateWorldTransform();
	}
	
	public void draw(SpriteBatch batch) {
		skanim.apply(skeleton, lastTime, animationTime, true, null);
		
		Util.drawSkeleton(batch, skeleton);
//		log("proj draw, skel: " + skeleton);
		
		
	}

	/**
	 * @return the skeletonname
	 */
	public String getSkeletonname() {
		return skeletonname;
	}

	/**
	 * @param skeletonname the skeletonname to set
	 */
	public void setSkeletonname(String skeletonname) {
		this.skeletonname = skeletonname;
	}
		

}
