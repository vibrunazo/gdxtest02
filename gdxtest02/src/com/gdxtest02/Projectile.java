package com.gdxtest02;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import static com.gdxtest02.GdxTest02.log;

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
	private String effecttype;
	private ParticleEffect effect;
	private boolean flipX;
	private int flipXFactor;
	
	public Projectile(AnimRenderer renderer) {
		this.renderer = renderer;
		x = 0;
		y = 0;
		speedx = 200;
		speedy = 0;
		duration = 2;
		time = 0;
		setFlipXFactor();
	}
	
	public void start() {
		effect = new ParticleEffect();
		effect.load(AnimRenderer.getParticleFile(effecttype), Gdx.files.internal("effects"));
		effect.start();
		effect.setPosition(x, y);
		renderer.addCharParticle(effect);
	}
	
	public void setPos(float x, float y) {
		this.x = x;
		this.y = y;
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
		effect.setPosition(x, y);
//		log("proj time: " + time + " duration: "+ duration);
		if (time >= duration) end();
	}
	
	public void end() {
		renderer.removeProjectile();
//		log("end proj time: " + time + " duration: "+ duration);
	}

	public void draw(SpriteBatch batch) {
		
	}

	public String getEffecttype() {
		return effecttype;
	}

	public void setEffecttype(String effecttype) {
		this.effecttype = effecttype;
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

}
