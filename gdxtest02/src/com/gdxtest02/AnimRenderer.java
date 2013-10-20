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
import com.gdxtest02.anims.Cast01;
import com.gdxtest02.anims.Castup01;
import com.gdxtest02.anims.Punch01;
import com.gdxtest02.anims.Stand01;

import static com.gdxtest02.GdxTest02.log;
import static com.gdxtest02.GdxTest02.VIRTUAL_WIDTH;

public class AnimRenderer {
	private CharSkin charSkinData;
	private float delta;
	private TextureAtlas atlas;
//	private SkeletonBinary sb;
	private SkeletonData sd;
	private Skeleton skeleton;
	private SkeletonRenderer renderer;
	private Bone root;

	float scale = 1;
	private Array<Slot> slotsToChangeColor;
	private String atlasfile;
//	private String skfile;
	private String jsonfile;
	private CharAnim anim;
	private String defaultanim;
	private String animname;

	public AnimRenderer(CharSkin data) {

		ini(data);
	}

	private void ini(CharSkin data) {
		charSkinData = data;
		slotsToChangeColor = new Array<Slot>();
		// "data/spine/spineboy.atlas"
		// "data/spine/spineboy.skel"

		atlasfile = "data/spine/ball/skeleton.atlas";
//		skfile = "data/spine/ball/skeleton.skel";
		jsonfile = "data/spine/ball/skeleton.json";

		atlas = new TextureAtlas(Gdx.files.internal(atlasfile));
//		sb = new SkeletonBinary(atlas);
		loadSkeletonDataFromJson();
	}
	
	public String getAnimName() {
		return animname;
	}
	
	public void setAnim(String animname) {
		if (animname.equals("") || animname == null) {
			setAnimToDefault();
		}
		this.animname = animname;
		if (animname.equals("stand")) {
			anim = new Stand01(this);
		}
		if (animname.equals("punch")) {
			anim = new Punch01(this);
		}
		if (animname.equals("cast")) {
			anim = new Cast01(this);
		}
		if (animname.equals("castup")) {
			anim = new Castup01(this);
		}
	}
	
	public void setDefaultAnim(String animname) {
		defaultanim = animname;
	}
	
	public String getDefaultAnimName() {
		return defaultanim;
	}
	
	public void setAnimToDefault() {
		setAnim(defaultanim);
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

		skeleton.setSkin("eyes01");
		skeleton.setSlotsToSetupPose();
		renderer = new SkeletonRenderer();

		setBaseColorSlots();
		root = skeleton.getRootBone();

		applyColor();

		skeleton.updateWorldTransform();
//		anim = new Stand01(this);
		setDefaultAnim("stand");
		setAnimToDefault();
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

		root.setX(posX); root.setY(posY);
		
		anim.draw();

		skeleton.updateWorldTransform();
		skeleton.update(delta);
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

