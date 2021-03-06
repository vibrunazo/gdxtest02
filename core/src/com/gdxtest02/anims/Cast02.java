package com.gdxtest02.anims;

import com.esotericsoftware.spine.Animation;
import com.gdxtest02.AnimRenderer;
import com.gdxtest02.CharAnim;
import com.gdxtest02.Effect;
import com.gdxtest02.Projectile;
import com.gdxtest02.projectiles.Projectile01;
import com.gdxtest02.util.Util;

/**Casts from mouth
 *
 */
public class Cast02 extends CharAnim {

	private Animation animation;
	private float x;
	private float y;
	private Projectile p;
	private boolean projcreated = false;

	public Cast02() {
		super();
		name = "cast01";
	}
	
	public Cast02 setProjectile(Projectile projectile) {
		p = projectile;
		return this;
	}
	
	protected void ini() {
		
		animation = sd.findAnimation(name);
		projcreated = false;
		getOwner().setIsattackanim(true);
	}

	/* (non-Javadoc)
	 * @see com.gdxtest02.CharAnim#draw()
	 */
	@Override
	public void draw() {
		super.draw();
		
		animation.apply(skeleton, lastTime, animationTime, false, null);
		if (animationTime < 0.7f) {
			x = skeleton.findBone("lip_up").getWorldX();
			y = ( skeleton.findBone("lip_up").getWorldY() + skeleton.findBone("lip_down").getWorldY() ) / 2;
		}
		else {
			
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
		
		
		if (animationTime > animation.getDuration()) {
			end();
		}
	}

}
