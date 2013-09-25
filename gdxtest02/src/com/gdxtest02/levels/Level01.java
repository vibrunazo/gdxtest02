package com.gdxtest02.levels;

import com.gdxtest02.GdxTest02;
import com.gdxtest02.LevelScreen;
import com.gdxtest02.chars.Char02;
import com.gdxtest02.chars.Char03;

public class Level01 extends LevelScreen {

	public Level01(GdxTest02 game) {
		super(game);
		addChar(new Char02("c2"));
		addChar(new Char02("c22"));
		addChar(new Char03("c3"));
	}

}
