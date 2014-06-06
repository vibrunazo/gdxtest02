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
	private float offsetx;
	private float offsety;
	private Bone bone;

	public Effect() {
//		attachedChar = null;
	}

	public void update(float delta) {
//		Util.log("effect update, aC: " + attachedChar + " at: " + x + "x, " + y);
		if (attachedChar == null) return;
//		x = attachedChar.getPosX();
		
		x = bone.getWorldX();
		y = bone.getWorldY();
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
		Skeleton sk = attachedChar.getAnimRenderer().getSkeleton();
		SkeletonData sd = attachedChar.getAnimRenderer().getSkeletonData();
		int bi = sd.findBoneIndex("shoulderPos");
		bone = sk.getBones().get(bi);
	}

}
