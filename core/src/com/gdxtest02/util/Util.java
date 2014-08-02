package com.gdxtest02.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonRenderer;
import com.gdxtest02.GdxTest02;

public class Util {
	static SkeletonRenderer skrenderer;
	
	public Util() {
		
	}
	
	/**Initializes Utility class
	 * 
	 */
	public static void ini() {
		skrenderer = new SkeletonRenderer();
	}

	public static void log(String text) {
		Gdx.app.log("gdxtest", text);
	}
	
	public static <T> T copy (T object) {
		return GdxTest02.getKryo().copy(object);
	}

	public static void scaleParticle(ParticleEffect particle, float scale) {
		float scaling;
		for (ParticleEmitter e : particle.getEmitters()) {
			scaling = e.getScale().getHighMax();
			e.getScale().setHigh(scaling * scale);

			scaling = e.getScale().getLowMax();
			e.getScale().setLow(scaling * scale);

			scaling = e.getVelocity().getHighMax();
			e.getVelocity().setHigh(scaling * scale);

			scaling = e.getVelocity().getLowMax();
			e.getVelocity().setLow(scaling * scale);
		}
	}
	
	public static SkeletonRenderer getSkeletonRenderer() {
		return skrenderer;
	}
	
	public static void drawSkeleton(SpriteBatch batch, Skeleton skeleton) {
		skrenderer.draw(batch, skeleton);
	}
	
}
