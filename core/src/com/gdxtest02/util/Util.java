package com.gdxtest02.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonData;
import com.esotericsoftware.spine.SkeletonJson;
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
	
	public static void drawSkeleton(PolygonSpriteBatch batch, Skeleton skeleton) {
		skrenderer.draw(batch, skeleton);
	}
	
	public static String getSkeletonPathFromName(String name) {
		if (name.toLowerCase().equals("proj")) {
			return "data/spine/proj/";
		}
		else if (name.toLowerCase().equals("hit")) {
			return "data/spine/hit/";
		}
		else {
			return "data/spine/" + name + "/";	
		}
		
	}
	
	public static Skeleton loadSkeletonFromName(String name, float scale) {
		String atlasfile = getSkeletonPathFromName(name) + "skeleton.atlas";
		String jsonfile = getSkeletonPathFromName(name) + "skeleton.json";
		
		Skeleton skeleton; 

		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal(atlasfile));
		SkeletonJson json = new SkeletonJson(atlas);
		json.setScale(scale);
		SkeletonData sd = json.readSkeletonData(Gdx.files.internal(jsonfile));
		skeleton = new Skeleton(sd);
		skeleton.setSlotsToSetupPose();
		skeleton.updateWorldTransform();
		
		return skeleton;
	}
	
	/**Returns if the game is in debug mode or not
	 * In debug mode, test Chars should be available
	 * 
	 * @return
	 */
	public static boolean getDebugMode() {
		return GdxTest02.DEBUG_MODE;
	}
	
}
