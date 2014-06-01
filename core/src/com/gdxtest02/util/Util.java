package com.gdxtest02.util;

import com.badlogic.gdx.Gdx;
import com.gdxtest02.GdxTest02;

public class Util {
	public Util() {
		
	}

	public static void log(String text) {
		Gdx.app.log("gdxtest", text);
	}
	
	public static <T> T copy (T object) {
		return GdxTest02.getKryo().copy(object);
	}
	
}
