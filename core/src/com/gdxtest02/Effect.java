package com.gdxtest02;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.esotericsoftware.spine.Bone;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonData;
import com.gdxtest02.util.Util;

public class Effect  {
	
	protected float x;
	protected float y;
	protected int duration;
	private Char attachedChar;
	private float offsetx = 0;
	private float offsety = 0;
	private Bone bone;
	private String attachName;
	

	public Effect() {
		attachName = "";
	}

	public void update(float delta) {
//		Util.log("effect update, aC: " + attachedChar + " at: " + x + "x, " + y);
		if (attachedChar == null) return;
//		x = attachedChar.getPosX();
		
		x = bone.getWorldX() + offsetx*attachedChar.getFlipXMultiplier();
		y = bone.getWorldY() + offsety;
	}

	public void draw(SpriteBatch batch) {
		
	}

	public void start() {
		
	}

	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void setDuration(int i) {
		// TODO Auto-generated method stub
		
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
		
		if (attachName.equals("")) setAttachName("shoulderPos");
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
		if (attachName.equals("")) {
			bone = null;
			return;
		}
		Skeleton sk = attachedChar.getAnimRenderer().getSkeleton();
		SkeletonData sd = attachedChar.getAnimRenderer().getSkeletonData();
		int bi = sd.findBoneIndex(attachName);
		bone = sk.getBones().get(bi);
	}

	/**
	 * @return the offsetx
	 */
	public float getOffsetx() {
		return offsetx;
	}

	/**
	 * @param offsetx the offsetx to set
	 * @param offsety the offsety to set
	 */
	public void setOffset(float offsetx, float offsety) {
		this.offsetx = offsetx;
		this.offsety = offsety;
	}

	/**
	 * @return the offsety
	 */
	public float getOffsety() {
		return offsety;
	}

}
