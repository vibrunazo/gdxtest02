package com.gdxtest02.levels;

import static com.gdxtest02.CharBuilder.CHAR_01;

import com.gdxtest02.GdxTest02;
import com.gdxtest02.LevelScreen;
import com.gdxtest02.chars.Char01;
import com.gdxtest02.chars.Char02;
import com.gdxtest02.chars.Char03;
import com.gdxtest02.chars.Char04;
import com.gdxtest02.chars.Char05;
import com.gdxtest02.chars.Char06;
import com.gdxtest02.gamestate.GameState;
import com.gdxtest02.gamestate.LevelState;

import static com.gdxtest02.CharBuilder.*;

public class Level03 extends LevelScreen {

	public Level03(GdxTest02 game) {
		super(game);
		addChar(new Char02("c2"));
		addChar(new Char03("c3"));
		addChar(new Char04("c4"));
		addChar(new Char05("c5"));
		addChar(new Char01("c1"));
		
		setLevel_name("level 3");
		ui.setupUi(this);
	}
	
	public void endLevel() {
		super.endLevel();
		GameState gstate = GameState.getInstance();
		if (gstate.getLevel().getFightState() == LevelState.WIN) {
			gstate.unlockChar(CHAR_07);
		}
	}

}
