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

import static com.gdxtest02.GdxTest02.log;
import static com.gdxtest02.GdxTest02.VIRTUAL_WIDTH;

public class CharAnim {
	private AnimData animData;
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

	public CharAnim(AnimData data) {

		ini(data);
	}

	private void ini(AnimData data) {
		animData = data;
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


		log("ini");
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
		//			walkAnimation = sd.findAnimation("walk");
		standAnimation = sd.findAnimation("stand01");
		punchAnimation = sd.findAnimation("punch01");
		winkAnimation = sd.findAnimation("wink");
		//		blueAnimation = sd.findAnimation("blue");
		//		redAnimation = sd.findAnimation("red");
		animationTime = 0;
		winkTime = 0;
		renderer = new SkeletonRenderer();
		//		Slot base = skeleton.findSlot("ballbase01");

		setBaseColorSlots();
		//		s.setAdditiveBlending(true);
//		Slot slot = skeleton.findSlot("arm_R");
//		Slot base = skeleton.findSlot("ballbase01");
//		Attachment att = base.getAttachment();
		//		slot.setAttachment(att);
//		Skin skin = skeleton.getSkin();
//		log("slot: " + slot + " att: " + att + " skin: " + skin);
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

		applyColor();

		skeleton.updateWorldTransform();
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
		punchAnimation.apply(skeleton, lastTime, animationTime, true, null);
		//		winkAnimation.apply(skeleton, lastWinkTime*10, winkTime*10, false, null);
		//		redAnimation.apply(skeleton, 0, 0, false, null);
		//		blueAnimation.apply(skeleton, 0, 0, false, null);
		//		blueAnimation.mix(skeleton, 0, 0, false, null, 0);

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
		animData.setBaseColor(color);
		applyColor();
	}

	private void applyColor() {
		Color color = animData.getBaseColor();
		if (color == null) return;
		for (Slot slot : slotsToChangeColor) {
			Color c = slot.getColor();
			c.set(color);
		}
	}

	public void setData(AnimData data) {
		animData = data;
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

