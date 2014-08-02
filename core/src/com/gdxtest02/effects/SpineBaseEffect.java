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
	
	@Override
	public void start() {
		hasStarted = true;
		setScale(getScale());
		
		loadSkeleton(1f);
	}

	/* (non-Javadoc)
	 * @see com.gdxtest02.Effect#setScale(float)
	 */
	@Override
	public void setScale(float scale) {
		super.setScale(scale);
	}

	private void loadSkeleton(float scale) {
		String atlasfile = "data/spine/hit/skeleton.atlas";
		String jsonfile = "data/spine/hit/skeleton.json";

		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal(atlasfile));
		SkeletonJson json = new SkeletonJson(atlas);
		json.setScale(scale);
		SkeletonData sd = json.readSkeletonData(Gdx.files.internal(jsonfile));
		skeleton = new Skeleton(sd);
//		skeleton.setSlotsToSetupPose();
		skeleton.updateWorldTransform();
		
		skanim = sd.findAnimation("hit");
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
		

}
