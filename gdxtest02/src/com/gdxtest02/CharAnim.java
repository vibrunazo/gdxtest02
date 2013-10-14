package com.gdxtest02;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.spine.Animation;
import com.esotericsoftware.spine.Bone;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonBinary;
import com.esotericsoftware.spine.SkeletonData;
import com.esotericsoftware.spine.SkeletonJson;
import com.esotericsoftware.spine.SkeletonRenderer;
import com.esotericsoftware.spine.Slot;
import com.esotericsoftware.spine.SlotData;

import static com.gdxtest02.GdxTest02.log;
import static com.gdxtest02.GdxTest02.VIRTUAL_WIDTH;

public class CharAnim {
	private AnimData animData;
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
	private Animation blueAnimation;
	private Animation redAnimation;
	private Array<Slot> slotsToChangeColor;

	public CharAnim() {

		ini();
	}

	private void ini() {
		animData = new AnimData();
		slotsToChangeColor = new Array<Slot>();
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
		blueAnimation = sd.findAnimation("blue");
		redAnimation = sd.findAnimation("red");
		animationTime = 0;
		winkTime = 0;
		renderer = new SkeletonRenderer();
//		Slot base = skeleton.findSlot("ballbase01");
		
		slotsToChangeColor.add(skeleton.findSlot("ballbase01"));
		slotsToChangeColor.add(skeleton.findSlot("leyelid_L"));
		slotsToChangeColor.add(skeleton.findSlot("ueyelid_L"));
		slotsToChangeColor.add(skeleton.findSlot("leyelid_R"));
		slotsToChangeColor.add(skeleton.findSlot("ueyelid_R"));
//		s.setAdditiveBlending(true);
//		slot = skeleton.findSlot("ballbase01");
//		color = slot.getColor();
//		color.set(1, 0, 0, 1);
//		color = base.getColor();
//		color.set(1, 1, 0, 1);
//		s.setAttachmentName(null);
//		log("after c: " + color);
//		c.r = 0;
//		c.g = 1;
//		c.b = 1;
//		log("c: " + color);
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
			
//			SlotData s = sd.findSlot("ballbase01");
//			Color c = s.getColor();
//			log("c: " + c);
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
//		redAnimation.apply(skeleton, 0, 0, false, null);
//		blueAnimation.apply(skeleton, 0, 0, false, null);
//		blueAnimation.mix(skeleton, 0, 0, false, null, 0);
		
		//		winkAnimation.apply(skeleton, lastTime*10, animationTime*10, true, null);
		winkAnimation.getDuration();
		skeleton.updateWorldTransform();
		skeleton.update(delta);
		//		renderSkeleton(skeleton);

		//        sprite.setPosition(-40, -40);
		//        sprite.setScale(10);
		batch.setColor(Color.BLUE);
		renderer.draw(batch, skeleton);
		batch.setColor(Color.BLUE);
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
	
	public void setColor(Color color) {
//		animData.setBaseColor(color);
//		this.color = base.getColor();
//		this.color.set(color);
//		applyColor();
		for (Slot slot : slotsToChangeColor) {
			Color c = slot.getColor();
			c.set(color);
		}
	}

	private void applyColor() {
		if (animData.getBaseColor().equals("red")) {
			redAnimation.apply(skeleton, 0, 0, false, null);
		}
		if (animData.getBaseColor().equals("blue")) {
			blueAnimation.apply(skeleton, 0, 0, false, null);
		}
	}
}

