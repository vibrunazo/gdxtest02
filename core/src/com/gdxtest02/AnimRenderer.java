package com.gdxtest02;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
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
import com.gdxtest02.anims.PunchRight01;
import com.gdxtest02.anims.Stand01;
import com.gdxtest02.util.Util;

import static com.gdxtest02.GdxTest02.VIRTUAL_WIDTH;

public class AnimRenderer {
	private CharSkin charSkinData;
	private float delta;
	private TextureAtlas atlas;
//	private SkeletonBinary sb;
	private SkeletonData sd;
	private Skeleton skeleton;
	private SkeletonRenderer skrenderer;
	private Bone root;

	float scale = 1;
	private Array<Slot> slotsToChangeColor;
	private String atlasfile;
//	private String skfile;
	private String jsonfile;
	private CharAnim anim;
	private String defaultanim;
	private String animname;
	
	
	private Array<Effect> animeffects;
	private Array<Effect> chareffects;
	
//	/**Animation specific effects. Will reset when animation changes
//	 * 
//	 */
//	private Array<ParticleEffect> animparticleeffects;
//	/**Generic char effects. Will persist through animations and only stops
//	 * when char dies or effect ends
//	 * 
//	 */
//	private Array<ParticleEffect> charparticleeffects;
	private Array<Projectile> projectiles;
	private Char owner;

	public AnimRenderer(CharSkin data) {

		ini(data);
	}

	private void ini(CharSkin data) {
		charSkinData = data;
		slotsToChangeColor = new Array<Slot>();

		atlasfile = "data/spine/ball/skeleton.atlas";
//		skfile = "data/spine/ball/skeleton.skel";
		jsonfile = "data/spine/ball/skeleton.json";

		atlas = new TextureAtlas(Gdx.files.internal(atlasfile));
//		sb = new SkeletonBinary(atlas);
		loadSkeletonDataFromJson();
		
//		charparticleeffects = new Array<ParticleEffect>();
		chareffects = new Array<Effect>();
		projectiles = new Array<Projectile>(); 
	}
	
	public String getAnimName() {
		return animname;
	}
	

	public void setAnim(String animname) {
		setAnim(animname, null);
	}
	
	public void setAnim(CharAnim anim) {
		setAnim(anim, null);
	}
	
	/**Sets the animation of the character to the animation with this generic name
	 * the renderer will decided which exact animation to use depending on the character
	 * 
	 * @param animname
	 */
	public void setAnim(String animname, Effect effect) {
		this.animname = animname;
		this.anim = getAnimByName(animname);
		setAnim(anim, effect);
	}
	
	/**Sets the animation of the character to this animation
	 * 
	 * @param anim
	 */
	public void setAnim(CharAnim anim, Effect effect) {
		if (owner != null) owner.setIsattackanim(false);
		// reset particles when changing anim
		animeffects = new Array<Effect>();
		if (anim == null || anim.getName().equals("")) {
			setAnimToDefault();
			anim.start(this);
			return;
		}
		this.anim = anim;
//		this.anim = getAnimByName(anim.getName());
		this.animname = anim.getName();
		
		if (effect != null) {
			anim.setEffect(effect);
		}
		anim.start(this);
//		log("setanim anim: " + anim + " ename: " + effectname);
	}
	
	public CharAnim getAnimByName(String name) {
		CharAnim anim = null;
		if (name.equals("stand01")) {
			anim = new Stand01();
		}
		if (name.equals("punch01")) {
			anim = new Punch01();
		}
		if (name.equals("punchright01")) {
			anim = new PunchRight01();
		}
		if (name.equals("cast01")) {
			anim = new Cast01();
		}
		if (name.equals("castup01")) {
			anim = new Castup01();
		}
//		log("getanimbyname, name: " + name + " anim: " + anim);
		return anim;
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

		skeleton.setSkin("base");
		loadBodypartsFromCharSkin();
		
		
		skeleton.setSlotsToSetupPose();
		skrenderer = new SkeletonRenderer();

		setBaseColorSlots();
		root = skeleton.getRootBone();

		applyColor();

		skeleton.updateWorldTransform();
//		anim = new Stand01(this);
		setDefaultAnim("stand01");
		setAnimToDefault();
	}

	/**Loads all the attachments from the Char's Skin
	 * For example, will load the appropriate hand on 
	 * his hands slot
	 * 
	 */
	private void loadBodypartsFromCharSkin() {
		loadSlot("hands", charSkinData.getHands());
		loadSlot("tail", charSkinData.getTailtype());
		loadSlot("mouth", charSkinData.getMouthtype());
		loadSlot("teeth", charSkinData.getTeethtype());
		loadSlot("arms", charSkinData.getArmstype());
	}

	/**loads a slot part into a specific slot
	 * example, loading scythe claws into the hands slot
	 * 
	 * @param slot
	 * @param skinname the name of the Skin in Spine that 
	 * contains the required parts, this Skin name needs
	 * to be the same name of the part the Char calls when
	 * setting its body part
	 */
	private void loadSlot(String slot, String skinname) {
		if (skinname == null) return;
		slot = slot.toLowerCase();
		Skin skin = sd.findSkin(skinname);
		if (slot.equals("hands")) {
			setAttachmentFromSkin("hand_L", skin);
			setAttachmentFromSkin("hand_R", skin);
		}
		if (slot.equals("tail")) {
			setAttachmentFromSkin("tail", skin);
			loadBoneScaleFromChar("tail");
		}
		if (slot.equals("mouth")) {
			setAttachmentFromSkin("mouth", skin);
		}
		if (slot.equals("teeth")) {
			setAttachmentFromSkin("teeth", skin);
			setAttachmentFromSkin("teeth2", skin);
		}
		if (slot.equals("arms")) {
			setAttachmentFromSkin("arm_L", skin);
			setAttachmentFromSkin("arm_R", skin);
			setAttachmentFromSkin("forearm_L", skin);
			setAttachmentFromSkin("forearm_R", skin);
		}

	}

	/**Takes a bone and scales it to match the rest of the Char
	 * This is needed for bones that are rescaled by its animations
	 * @param bonetype
	 */
	private void loadBoneScaleFromChar(String bonetype) {
		bonetype = bonetype.toLowerCase();
		if (bonetype.equals("tail")) {
			Bone b = skeleton.findBone("tailbase");
//			Util.log("loadBoneScale, bnames: " + skeleton.getBones());
			b.setScale(charSkinData.getTailscalex(), charSkinData.getTailscaley());
		}
	}

	/**Will take the Attachment from this Skin, and set it to the
	 * Skin being currently used by the Skeleton
	 * 
	 * @param slotName
	 * @param skin
	 */
	public void setAttachmentFromSkin(String slotName, Skin skin) {
		int i = sd.findSlotIndex(slotName);
		Array<Attachment> attachments = new Array<Attachment>();
		skin.findAttachmentsForSlot(i, attachments);
		Attachment a = attachments.get(0);
		Skin dskin = skeleton.getSkin();
		Array<String> names = new Array<String>();
		skin.findNamesForSlot(i, names);
		String aname;
		if (names.size > 0)	{
			aname = names.get(0);
			dskin.addAttachment(i, aname, a);
		}
		else {
			Util.log("animrend setatt, cannot find attachment for slotname: " + slotName + " skin: " + skin.getName());
		}
			
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
		slotsToChangeColor.add(skeleton.findSlot("tail"));
	}

	public void draw(PolygonSpriteBatch batch, float x, float y) {
		draw(batch, (int) x, (int) y);
	}
	public void draw(PolygonSpriteBatch batch, int posX, int posY) {
		if (skeleton.getFlipX()) posX *= -1;
		if (skeleton.getFlipY()) posY *= -1;

		root.setX(posX); root.setY(posY);
		
		delta = Gdx.graphics.getDeltaTime();
		anim.draw();

		skeleton.updateWorldTransform();
//		skeleton.update(delta);
		skrenderer.draw(batch, skeleton);
		
//		drawParticles(batch);
		
	}

	public void drawEffects(PolygonSpriteBatch batch) {
		for (Buff b : owner.getBuffs()) {
			b.draw(batch, delta);
		}
		
		for (Effect e : animeffects) {
			e.update(delta);
			e.draw(batch);
			if (e.getHasFinished()) animeffects.removeValue(e, true);
		}
		
		for (Effect e : chareffects) {
			e.update(delta);
			e.draw(batch);
			if (e.getHasFinished()) chareffects.removeValue(e, true);
		}
		
		for (Projectile p : projectiles) {
			p.update(delta);
			p.draw(batch);
		}
	}
	
	public void drawSkeleton(PolygonSpriteBatch batch, Skeleton skeleton) {
		skrenderer.draw(batch, skeleton);
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

	public void addCharEffect(Effect effect) {
		chareffects.add(effect);
		effect.setAttachedChar(owner);
		effect.start();
	}
	
	public void addAnimEffect(Effect effect) {
		animeffects.add(effect);
		effect.start();
	}

	public void createProjectile(Projectile p) {
		projectiles.add(p);
		p.start(this);
	}
	
	/**How many projectiles this char have right now
	 * @return
	 */
	public int getNumProj() {
		return projectiles.size;
	}
	
	public static ParticleEffect getParticleFromName(String effecttype) {
		ParticleEffect effect = new ParticleEffect();
		effect.load(getParticleFile(effecttype), Gdx.files.internal("effects"));
		effect.start();
		return effect;
	}
	
	public static FileHandle getParticleFile(String effecttype) {
		if (effecttype.equals("fire")) {
			return Gdx.files.internal("effects/fire03.p");
		}
		else if (effecttype.equals("blood")) {
			return Gdx.files.internal("effects/blood01.p");
		} 
		else if (effecttype.equals("bleed")) {
			return Gdx.files.internal("effects/bleeding01.p");
		} 
		else if (effecttype.equals("green")) {
			return Gdx.files.internal("effects/green02.p");
		} 
		else if (effecttype.equals("ice")) {
			return Gdx.files.internal("effects/ice04.p");
		} 
		else if (effecttype.equals("blue")) {
			return Gdx.files.internal("effects/blue02.p");
		}
		else if (effecttype.equals("red")) {
			return Gdx.files.internal("effects/red01.p");
		}
		else if (effecttype.equals("purple")) {
			return Gdx.files.internal("effects/purple01.p");
		}
		else if (effecttype.equals("yellow")) {
			return Gdx.files.internal("effects/yellow01.p");
		} 
		else if (effecttype.equals("black")) {
			return Gdx.files.internal("effects/black01.p");
		}
		else if (effecttype.equals("white")) {
			return Gdx.files.internal("effects/white01.p");
		} 
		else if (effecttype.equals("hit")) {
			return Gdx.files.internal("effects/spark01.p");
		} 
		
		effecttype = "fire";
		return getParticleFile(effecttype);
	}

	public void removeProjectile() {
		projectiles.clear();
//		projectiles.removeIndex(0);
//		projectiles = new Array<Projectile>(); 
//		charparticleeffects.clear();
		
//		clearCharEffects();
		
	}

//	private void clearCharEffects() {
//		for (ParticleEffect p : charparticleeffects) {
//			p.dispose();
//		}
//	}

	public void setOwner(Char owner) {
		this.owner = owner;
	}

	public Char getOwner() {
		return owner;
	}

	/**Reset all animations, to use when the char is just created
	 * to make sure everything is clear
	 * 
	 */
	public void reset() {
		removeProjectile();
		setAnimToDefault();
		animeffects = new Array<Effect>();
		chareffects = new Array<Effect>();
	}

}

