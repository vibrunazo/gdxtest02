package com.gdxtest02;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonData;
import com.esotericsoftware.spine.SkeletonJson;
import com.gdxtest02.anims.GetHit01;
import com.gdxtest02.effects.ExplosionEffect;
import com.gdxtest02.effects.FireEffect;
import com.gdxtest02.effects.HitEffect;
import com.gdxtest02.util.Util;

public class Projectile {
	private float x;
	private float y;
	private float speedx;
	private float speedy;
	
	private float time;
	private float duration;

	private Char source;
	private Char target;
	private AnimRenderer renderer;
	protected Effect effect;
	private boolean flipX;
	private int flipXFactor;
	private Skeleton skeleton;
	
	public Projectile() {
		x = 0;
		y = 0;
		speedx = 200;
		speedy = 0;
		duration = 2;
		time = 0;
		setFlipXFactor();
		
		loadSkeleton(1f);
	}
	
	private void loadSkeleton(float scale) {
		skeleton = Util.loadSkeletonFromName("proj", scale);
	}

	public void start(AnimRenderer renderer) {
		this.renderer = renderer;
		
		if (effect == null) effect = new FireEffect();
		time = 0;
		effect.start();
		effect.setPosition(x, y);
		target = renderer.getOwner().getTarget(); // default projectile target is the Char target
	}
	
	public void setPos(float x, float y) {
		this.x = x;
		this.y = y;
		skeleton.setX(x);
		skeleton.setY(y);
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public Char getSource() {
		return source;
	}

	public void setSource(Char source) {
		this.source = source;
	}

	public Char getTarget() {
		return target;
	}

	public void setTarget(Char target) {
		this.target = target;
	}

	public void update(float delta) {
		time += delta;
		x += delta * speedx * flipXFactor;
		y += delta * speedy;
		skeleton.setX(x);
		skeleton.setY(y);
		effect.setPosition(x, y);
		effect.update(delta);
//		log("projupdate time: " + time + " duration: "+ duration);
		if (time >= duration) end();
	}
	
	public void end() {
		renderer.removeProjectile();
		if (target == null) return;
		ExplosionEffect e = new ExplosionEffect(0.35f);
		target.setGetHit(e);
	}

	public void draw(PolygonSpriteBatch batch) {
//		renderer.drawSkeleton(batch, skeleton);
		Util.drawSkeleton(batch, skeleton);
		effect.draw(batch);
//		log("proj draw, skel: " + skeleton);
	}

	public float getSpeedx() {
		return speedx;
	}

	public void setSpeedx(float speedx) {
		this.speedx = speedx;
	}

	public float getSpeedy() {
		return speedy;
	}

	public void setSpeedy(float speedy) {
		this.speedy = speedy;
	}

	public float getDuration() {
		return duration;
	}

	public void setDuration(float duration) {
		this.duration = duration;
	}

	public float getTime() {
		return time;
	}

	public void setFlipX(boolean flipX) {
		this.flipX = flipX;
		setFlipXFactor();
	}

	private void setFlipXFactor() {
		if (flipX) flipXFactor = -1;
		else flipXFactor = 1;
	}
	
	public Projectile getClone() {
		Projectile c = new Projectile();
		c.setDuration(getDuration());
		c.setFlipX(this.flipX);
		c.setSource(getSource());
		c.setTarget(getTarget());
		c.setSpeedx(speedx);
		c.setSpeedy(speedy);
		return c;
	}

}
