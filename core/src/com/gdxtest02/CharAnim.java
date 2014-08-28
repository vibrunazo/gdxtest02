package com.gdxtest02;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonData;
import com.gdxtest02.effects.FireEffect;
import com.gdxtest02.effects.HitEffect;
import com.gdxtest02.projectiles.Projectile02;
import com.gdxtest02.util.Util;

/**This is the base class every animation will extend, it should have commonly used
 * methods that most animations use
 *  
 *
 */
public class CharAnim {

	protected AnimRenderer renderer;
	protected Skeleton skeleton;
	protected SkeletonData sd;
//	protected Animation standAnimation;
//	private Animation punchAnimation;
//	private Animation winkAnimation;
	protected float animationTime;
	protected float delta;
	protected float speed;
	protected float lastTime;
	protected Effect effecttype;
	protected String name = "";

	public CharAnim() {
		speed = 1f;
//		ini();
	}

	/**Called when the animation needs to start, must call this before using the animation
	 * 
	 * @return
	 */
	public void start(AnimRenderer animRenderer) {
		renderer = animRenderer;
		skeleton = renderer.getSkeleton();
		sd = renderer.getSkeletonData();
//		Char owner = renderer.getOwner();
//		if (owner != null) {
//			log("charanim ini, skeleton: " + skeleton.hashCode() + " owner: " + renderer.getOwner()
//					+ " hash: " + renderer.getOwner().hashCode());
//		}
		animationTime = 0;
		ini();
	}

	public void draw() {
//		log("charanim draw this: " + this + " name: " + name + " etype: " + effecttype + 
//				" time:" + animationTime);
		delta = Gdx.graphics.getDeltaTime();
		lastTime = animationTime;
		animationTime += delta*speed;
	}
	
	/**ends animation and go back to default anim
	 * 
	 */
	public void end() {
		renderer.setAnimToDefault();
	}
	
	public CharAnim setEffect(Effect type) {
//		renderer.resetParticles();
		this.effecttype = type;
		return this;
	}
	
	protected void ini() {
		
	}
	
	public String getEffect(String effecttype) {
		return effecttype;
	}
	
	/**Adds an effect tied to the animation, this effect will disappear
	 * if the animation ends
	 * 
	 * @return
	 */
	protected Effect addAnimEffect() {
		Effect effect = getNewEffect();
//		effect.load(getParticleFile(), Gdx.files.internal("effects"));
		effect.start();
		renderer.addAnimEffect(effect);
		return effect;
	}
	
	/**Adds an effect tied to the char, this effect will disappear
	 * if the char dies
	 * 
	 * @return
	 */
	protected Effect addCharEffect() {
		Effect effect = getNewEffect();
//		effect.load(getParticleFile(), Gdx.files.internal("effects"));
		effect.start();
		renderer.addCharEffect(effect);
		return effect;
	}
	
	protected Effect getNewEffect() {
		Effect effect;
		if (effecttype != null) effect = Util.copy(effecttype);
		else effect = new FireEffect();
		return effect;
	}

//	private FileHandle getParticleFile() {
//		return AnimRenderer.getParticleFile(effecttype);
//	}
	
	protected Projectile addProjectile(float x, float y, Projectile p) {
//		if (renderer.getNumProj() > 0) return null;
		if (p == null) p = new Projectile02();
		p.setPos(x, y);
		p.setEffecttype(getNewEffect());
		renderer.createProjectile(p);
		return p;
	}
	
	/**Create a hit effect on the target
	 * 
	 */
	protected void createHitEffect() {
		Char c = renderer.getOwner();
		Char t = c.getTarget();
		
		t.setGetHit();
	}
	
	/**Create a hit effect on the target
	 * 
	 * @param e the Effect to show as a hit effect
	 */
	protected void createHitEffect(Effect e) {
		Char c = renderer.getOwner();
		Char t = c.getTarget();
		
		t.setGetHit(e);
	}

	public String getName() {
		return name;
	}

	/**
	 * @return the speed
	 */
	public float getSpeed() {
		return speed;
	}

	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	/**
	 * @return the Char who is performing this Anim
	 */
	public Char getOwner() {
		return renderer.getOwner();
	}
}
