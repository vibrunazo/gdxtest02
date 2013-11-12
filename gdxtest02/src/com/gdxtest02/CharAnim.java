package com.gdxtest02;

import static com.gdxtest02.GdxTest02.log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.esotericsoftware.spine.Animation;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonData;

public class CharAnim {

	protected AnimRenderer renderer;
	protected Skeleton skeleton;
	protected SkeletonData sd;
//	protected Animation standAnimation;
//	private Animation punchAnimation;
//	private Animation winkAnimation;
	protected float animationTime;
	protected float delta;
	protected float lastTime;
	private String effecttype = "";

	public CharAnim(AnimRenderer animRenderer) {
		renderer = animRenderer;
		skeleton = renderer.getSkeleton();
		sd = renderer.getSkeletonData();
		
		ini();
	}

	private void ini() {
		animationTime = 0;
	}

	public void draw() {
		delta = Gdx.graphics.getDeltaTime();
		lastTime = animationTime;
		animationTime += delta;
	}
	
	/**ends animation and go back to default anim
	 * 
	 */
	public void end() {
		renderer.setAnimToDefault();
	}
	
	public void setEffect(String type) {
		renderer.resetParticles();
		this.effecttype = type;
		iniParticles();
	}
	
	protected void iniParticles() {
		
	}
	
	public String getEffect(String effecttype) {
		return effecttype;
	}
	
	/**Adds an effect tied to the animation, this effect will disappear
	 * if the animation ends
	 * 
	 * @return
	 */
	protected ParticleEffect addAnimEffect() {
		ParticleEffect effect = new ParticleEffect();
		effect.load(getParticleFile(), Gdx.files.internal("effects"));
		effect.start();
		renderer.addAnimParticle(effect);
		return effect;
	}
	
	/**Adds an effect tied to the char, this effect will disappear
	 * if the char dies
	 * 
	 * @return
	 */
	protected ParticleEffect addCharEffect() {
		ParticleEffect effect = new ParticleEffect();
		effect.load(getParticleFile(), Gdx.files.internal("effects"));
		effect.start();
		renderer.addAnimParticle(effect);
		return effect;
	}

	private FileHandle getParticleFile() {
		if (effecttype.equals("fire")) {
			return Gdx.files.internal("effects/part01.p");
		}
		else if (effecttype.equals("green")) {
			return Gdx.files.internal("effects/part02.p");
		} 
		else if (effecttype.equals("ice")) {
			return Gdx.files.internal("effects/ice02.p");
		} 
		
		effecttype = "fire";
		return getParticleFile();
	}
	
	protected Projectile createProjectile(float x, float y) {
		if (renderer.getNumProj() > 0) return null;
		Projectile p = new Projectile(renderer);
		p.setPos(x, y);
		p.setEffecttype(effecttype);
		renderer.createProjectile(p);
		return p;
	}
	
}
