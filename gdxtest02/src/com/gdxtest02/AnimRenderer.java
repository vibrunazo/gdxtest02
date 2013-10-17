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
import com.esotericsoftware.spine.Skin;
import com.esotericsoftware.spine.Slot;
import com.esotericsoftware.spine.SlotData;
import com.esotericsoftware.spine.attachments.Attachment;
import com.gdxtest02.anims.Punch01;
import com.gdxtest02.anims.Stand01;

import static com.gdxtest02.GdxTest02.log;
import static com.gdxtest02.GdxTest02.VIRTUAL_WIDTH;

public class AnimRenderer {
	private CharSkin charSkinData;
	private float delta;
	private TextureAtlas atlas;
	private SkeletonBinary sb;
	private SkeletonData sd;
	private Skeleton skeleton;
	private Animation standAnimation;
	private float animationTime;
	private float winkTime;
	private SkeletonRenderer renderer;
	private Bone root;
	private Animation winkAnimation;
	private float lastTime;
	private float lastWinkTime;

	float scale = 1;
	private Animation blueAnimation;
	private Animation redAnimation;
	private Array<Slot> slotsToChangeColor;
	private Animation punchAnimation;
	private String atlasfile;
	private String skfile;
	private String jsonfile;
	private CharAnim standAnim;

	public AnimRenderer(CharSkin data) {

		ini(data);
	}

	private void ini(CharSkin data) {
		charSkinData = data;
		slotsToChangeColor = new Array<Slot>();
		// "data/spine/spineboy.atlas"
		// "data/spine/spineboy.skel"

		atlasfile = "data/spine/ball/skeleton.atlas";
		skfile = "data/spine/ball/skeleton.skel";
		jsonfile = "data/spine/ball/skeleton.json";

		atlas = new TextureAtlas(Gdx.files.internal(atlasfile));
		sb = new SkeletonBinary(atlas);
		//		sd = sb.readSkeletonData(Gdx.files
		//				.internal(skfile));
		loadSkeletonDataFromJson();
		
		

	}
	
	public Skeleton getSkeleton() {
		return skeleton;
	}
	
	public SkeletonData getSkeletonData() {
		return sd;
	}

	private void loadSkeletonDataFromJson() {
		loadSkeletonDataFromJson(1f);
	}
	private void loadSkeletonDataFromJson(float scale) {
		SkeletonJson json = new SkeletonJson(atlas);
		json.setScale(scale);
		sd = json.readSkeletonData(Gdx.files.internal(jsonfile));
		skeleton = new Skeleton(sd);


		//		skeleton.setFlipX(true);
		skeleton.setSkin("eyes01");
		skeleton.setSlotsToSetupPose();
		standAnimation = sd.findAnimation("stand01");
		punchAnimation = sd.findAnimation("punch01");
		winkAnimation = sd.findAnimation("wink");
		animationTime = 0;
		winkTime = 0;
		renderer = new SkeletonRenderer();
		//		Slot base = skeleton.findSlot("ballbase01");

		setBaseColorSlots();
		root = skeleton.getRootBone();

		applyColor();

		skeleton.updateWorldTransform();
		standAnim = new Stand01(this);
	}

	private void setBaseColorSlots() {
		slotsToChangeColor.add(skeleton.findSlot("ballbase01"));
		slotsToChangeColor.add(skeleton.findSlot("leyelid_L"));
		slotsToChangeColor.add(skeleton.findSlot("ueyelid_L"));
		slotsToChangeColor.add(skeleton.findSlot("leyelid_R"));
		slotsToChangeColor.add(skeleton.findSlot("ueyelid_R"));
		slotsToChangeColor.add(skeleton.findSlot("arm_L"));
		slotsToChangeColor.add(skeleton.findSlot("forearm_L"));
		slotsToChangeColor.add(skeleton.findSlot("hand_L"));
		slotsToChangeColor.add(skeleton.findSlot("arm_R"));
		slotsToChangeColor.add(skeleton.findSlot("forearm_R"));
		slotsToChangeColor.add(skeleton.findSlot("hand_R"));
	}

	public void draw(SpriteBatch batch, float x, float y) {
		draw(batch, (int) x, (int) y);
	}
	public void draw(SpriteBatch batch, int posX, int posY) {
		if (skeleton.getFlipX()) posX *= -1;
		if (skeleton.getFlipY()) posY *= -1;
		delta = Gdx.graphics.getDeltaTime();
		lastTime = animationTime;
		animationTime += delta;

		if (winkTime > 1.5) {
//			log("A winkTime: " + winkTime + " last: " + lastWinkTime + " delta: " + delta);
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
		
//		punchAnimation.apply(skeleton, lastTime, animationTime, true, null);
		
		standAnim.draw();
//		standAnimation.apply(skeleton, lastTime, animationTime, true, null);
//		winkAnimation.apply(skeleton, lastWinkTime*10, winkTime*10, false, null);

		//		winkAnimation.apply(skeleton, lastTime*10, animationTime*10, true, null);
		skeleton.updateWorldTransform();
		skeleton.update(delta);
		//		renderSkeleton(skeleton);

		//        sprite.setPosition(-40, -40);
		//        sprite.setScale(10);
		renderer.draw(batch, skeleton);
	}

	/**Scales the whole character, will reload everything again, use with care
	 * @param scale
	 */
	public void setScale(float scale) {
		this.scale = scale;
		loadSkeletonDataFromJson(scale);
	}

	public float getScale() {
		return scale;
	}

	public void setColor(Color color) {
		charSkinData.setBaseColor(color);
		applyColor();
	}

	private void applyColor() {
		Color color = charSkinData.getBaseColor();
		if (color == null) return;
		for (Slot slot : slotsToChangeColor) {
			Color c = slot.getColor();
			c.set(color);
		}
	}

	public void setData(CharSkin data) {
		charSkinData = data;
		applyColor();
	}

	public void flipY(boolean flip) {
		skeleton.setFlipY(flip);
	}

	public void flipX(boolean flip) {
		skeleton.setFlipX(flip);
	}

	public boolean getFlipX() {
		return skeleton.getFlipX();
	}

}

