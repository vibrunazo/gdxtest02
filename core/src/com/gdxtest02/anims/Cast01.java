package com.gdxtest02.anims;

import com.esotericsoftware.spine.Animation;
import com.gdxtest02.AnimRenderer;
import com.gdxtest02.CharAnim;
import com.gdxtest02.Effect;
import com.gdxtest02.Projectile;
import com.gdxtest02.projectiles.Projectile01;
import com.gdxtest02.util.Util;

public class Cast01 extends CharAnim {

	private Animation animation;
	private Effect effect;
	private float x;
	private float y;
	private Projectile p;
	private boolean projcreated = false;

	public Cast01() {
		super();
		name = "cast01";
	}
	
	public Cast01 setProjectile(Projectile projectile) {
		p = projectile;
		return this;
	}
	
	protected void ini() {
		
		animation = sd.findAnimation(name);
		effect = addAnimEffect();
		effect.setDuration(1500);
		projcreated = false;
		getOwner().setIsattackanim(true);
		Util.log("ini charanim: " + name + " effect: " + effect + " etype: " + effecttype);
		
	}

	/* (non-Javadoc)
	 * @see com.gdxtest02.CharAnim#draw()
	 */
	@Override
	public void draw() {
		super.draw();
		
		animation.apply(skeleton, lastTime, animationTime, false, null);
		if (animationTime < 0.8f) {
			x = skeleton.findBone("hand_R").getWorldX();
			y = skeleton.findBone("hand_R").getWorldY() + 10;
		}
		else {
//			int flip = 1;
//			if (skeleton.getFlipX()) flip = -1;
//			x += delta*800*flip;
			
			
			if (projcreated == false) {
				getOwner().setIsattackanim(false);
				projcreated = true;
				if (p == null) {
					p = new Projectile01();
				}
				else {
					p = p.getClone();
				}
				addProjectile(x, y, p);
//				p.setSpeedx(800);
				p.setFlipX(skeleton.getFlipX()) ;
			}
		}
		
//		log("animtime: " + animationTime + " anim: " + animation + " name: "
//		+ animation.getName() + " dur: " + animation.getDuration());
		
		effect.setPosition(x, y);
		
		if (animationTime > animation.getDuration()) {
			end();
		}
	}

}
