package com.gdxtest02;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.esotericsoftware.spine.Animation;
import com.esotericsoftware.spine.Bone;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonBinary;
import com.esotericsoftware.spine.SkeletonData;
import com.esotericsoftware.spine.SkeletonJson;
import com.esotericsoftware.spine.SkeletonRenderer;
import static com.gdxtest02.GdxTest02.log;
import static com.gdxtest02.GdxTest02.VIRTUAL_WIDTH;

public class CharAnim {
	private float delta;
	private TextureAtlas atlas;
	private SkeletonBinary sb;
	private SkeletonData sd;
	private Skeleton skeleton;
	private Animation walkAnimation;
	private float animationTime;
	private float winkTime;
	private SkeletonRenderer renderer;
	private Bone root;
	private Animation winkAnimation;
	private float lastTime;
	private float lastWinkTime;
	
	float scalex = 1;
	float scaley = 1;

	public CharAnim() {

		ini();
	}

	private void ini() {
		// "data/spine/spineboy.atlas"
		// "data/spine/spineboy.skel"

		// "data/spine/ball/skeleton.atlas"
		// "data/spine/ball/skeleton.skel"
		String atlasfile =  "data/spine/ball/skeleton.atlas";
//		String skfile = "data/spine/ball/skeleton.skel";
		String jsonfile = "data/spine/ball/skeleton.json";

		atlas = new TextureAtlas(Gdx.files.internal(atlasfile));
		sb = new SkeletonBinary(atlas);
		//			sd = sb.readSkeletonData(Gdx.files
		//	                .internal(skfile));
		SkeletonJson json = new SkeletonJson(atlas);
		sd = json.readSkeletonData(Gdx.files.internal(jsonfile)); //.readSkeletonData("mySkeleton.json");

		skeleton = new Skeleton(sd);
		skeleton.setSkin("eyes01");
		//			walkAnimation = sd.findAnimation("walk");
		walkAnimation = sd.findAnimation("stand01");
		winkAnimation = sd.findAnimation("wink");
		animationTime = 0;
		winkTime = 0;
		renderer = new SkeletonRenderer();

		root = skeleton.getRootBone();
		root.setX(120);
		root.setY(20);

		skeleton.updateWorldTransform();
		
		log("ini");
	}

	public void draw(SpriteBatch batch, int posX, int posY) {
		delta = Gdx.graphics.getDeltaTime();
		lastTime = animationTime;
		animationTime += delta;

		if (winkTime > 1.5) {
			log("A winkTime: " + winkTime + " last: " + lastWinkTime + " delta: " + delta);
			winkTime = 0;
		}
		lastWinkTime = winkTime;
		winkTime += delta;

//		log("B winkTime: " + winkTime + " last: " + lastWinkTime + " delta: " + delta +
//				"animTime: " + animationTime);
//		root.setX(((root.getX() + 230 * delta)%(VIRTUAL_WIDTH*1.2f)));
//		root.setX((230 * animationTime)%(VIRTUAL_WIDTH + 400) - 200);
		root.setX(posX); root.setY(posY);
//		log("rootx: " + root.getX());
		//		walkAnimation.apply(skeleton, animationTime, true); // true is for loop
		walkAnimation.apply(skeleton, lastTime, animationTime, true, null);
		winkAnimation.apply(skeleton, lastWinkTime*10, winkTime*10, false, null);
		//		winkAnimation.apply(skeleton, lastTime*10, animationTime*10, true, null);
		winkAnimation.getDuration();
		skeleton.updateWorldTransform();
		skeleton.update(delta);
		//		renderSkeleton(skeleton);

		//        sprite.setPosition(-40, -40);
		//        sprite.setScale(10);
		renderer.draw(batch, skeleton);
	}

	public void setScale(float x, float y) {
		scalex = x;
		scaley = y;
		root.setScaleX(x);
		root.setScaleY(y);
	}

	public float getScaleX() {
		return scalex;
	}

	public float getScaleY() {
		return scaley;
	}
}
