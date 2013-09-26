package com.gdxtest02.levels;

import com.gdxtest02.GdxTest02;
import com.gdxtest02.LevelScreen;
import com.gdxtest02.chars.Char02;
import com.gdxtest02.chars.Char03;
import com.gdxtest02.chars.Char04;
import com.gdxtest02.chars.Char05;
import com.gdxtest02.chars.Char06;

public class Level02 extends LevelScreen {

	public Level02(GdxTest02 game) {
		super(game);
		addChar(new Char03("c3"));
		addChar(new Char04("c4"));
		addChar(new Char05("c5"));
		addChar(new Char05("c5"));
		addChar(new Char06("c6"));
		
		setLevel_name("level 2");
		ui.setupUi(this);
	}

}
