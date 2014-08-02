package com.gdxtest02;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.Animation;
import com.esotericsoftware.spine.Bone;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonData;
import com.gdxtest02.util.Util;

public class Effect  {
	
	protected float x;
	protected float y;
	protected float duration;
	protected float lastTime;
	protected float animationTime;
	
	protected boolean hasStarted;
	protected boolean hasFinished;
	protected boolean isLoop;
	private Char attachedChar;
	private Buff attachedBuff;
	private float offsetx = 0;
	private float offsety = 0;
	/**The attached Char's root bone
	 */
	private Bone bone;
	private String attachName = "shoulderPos";
	private float scale = 1;
	

	public Effect() {
//		attachName = "";
	}
	
	public Effect(float scale) {
		setScale(scale);
	}

	public void update(float delta) {
//		Util.log("effect update, aC: " + attachedChar + " at: " + x + "x, " + y);
		if (attachedChar == null) return;
//		x = attachedChar.getPosX();
		
		x = bone.getWorldX() + offsetx*attachedChar.getFlipXMultiplier();
		y = bone.getWorldY() + offsety;
//		Util.log("Effect update, x: " + x + " offsetx: " + offsetx + 
//				" buffoff: " + attachedBuff.getEffectOffsetX()
//				+ " buff: " + attachedBuff);
		
		lastTime = animationTime;
		animationTime += delta;
//		duration = skanim.getDuration();
		if (animationTime > duration && !isLoop()) {
			hasFinished = true;
		}
		
	}

	public void draw(SpriteBatch batch) {
		
	}

	public void start() {
		
	}

	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void setDuration(float f) {
		duration = f;
	}

	/**
	 * @return the attachedChar
	 */
	public Char getAttachedChar() {
		return attachedChar;
	}

	/**
	 * @param attachedChar the attachedChar to set
	 */
	public void setAttachedChar(Char attachedChar) {
		this.attachedChar = attachedChar;
		
//		if (attachName.equals("")) setAttachName("shoulderPos");
//		if (attachName.equals("")) setAttachName("hand_L");
		applyAttachment();
	}

	/**
	 * @return the attachName
	 */
	public String getAttachName() {
		return attachName;
	}

	/**
	 * @param attachName the attachName to set
	 */
	public void setAttachName(String attachName) {
		this.attachName = attachName;
		applyAttachment();
	}

	private void applyAttachment() {
		if (attachName.equals("") || attachedChar == null) {
			bone = null;
			return;
		}
		Skeleton sk = attachedChar.getAnimRenderer().getSkeleton();
		SkeletonData sd = attachedChar.getAnimRenderer().getSkeletonData();
		int bi = sd.findBoneIndex(attachName);
		bone = sk.getBones().get(bi);
	}

	/**
	 * @param offsetx the offsetx to set
	 * @param offsety the offsety to set
	 */
	public void setOffset(float offsetx, float offsety) {
		this.offsetx = offsetx;
//		this.offsetx += MathUtils.random()*100;
//		Util.log("effect setoffset, offsetx: " + this.offsetx);
		this.offsety = offsety;
		if (attachedBuff != null) {
			this.offsetx += attachedBuff.getEffectOffsetX();
			this.offsety += attachedBuff.getEffectOffsetY();
		}
	}

	/**
	 * @return the offsety
	 */
	public float getOffsety() {
		return offsety;
	}
	
	/**
	 * @return the offsetx
	 */
	public float getOffsetx() {
		return offsetx;
	}

	/**
	 * @return the attachedBuff
	 */
	public Buff getAttachedBuff() {
		return attachedBuff;
	}

	/**
	 * @param attachedBuff the attachedBuff to set
	 */
	public void setAttachedBuff(Buff attachedBuff) {
		this.attachedBuff = attachedBuff;
	}

	/**
	 * @return the scale
	 */
	public float getScale() {
		return scale;
	}

	/**
	 * @param scale the scale to set
	 */
	public void setScale(float scale) {
		this.scale = scale;
	}

	/**
	 * @return the isLoop
	 */
	public boolean isLoop() {
		return isLoop;
	}

	/**
	 * @param isLoop the isLoop to set
	 */
	public void setLoop(boolean isLoop) {
		this.isLoop = isLoop;
	}

	/**
	 * @return the hasStarted
	 */
	public boolean hasStarted() {
		return hasStarted;
	}

	/**
	 * @return the hasFinished
	 */
	public boolean getHasFinished() {
		return hasFinished;
	}

	/**
	 * @param hasFinished the hasFinished to set
	 */
	public void setHasFinished(boolean hasFinished) {
		this.hasFinished = hasFinished;
	}

}
