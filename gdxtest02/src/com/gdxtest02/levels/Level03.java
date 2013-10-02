package com.gdxtest02.levels;

import static com.gdxtest02.CharBuilder.CHAR_01;
import static com.gdxtest02.CharBuilder.build;

import com.gdxtest02.Char;
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
		addChar(build(CHAR_02));
		addChar(build(CHAR_03));
		addChar(build(CHAR_04));
		addChar(build(CHAR_05));
		addChar(build(CHAR_01));
		
		setLevel_name("level 3");
		ui.setupUi(this);
	}
	
	public void endLevel() {
		super.endLevel();
		GameState gstate = GameState.getInstance();
		if (gstate.getLevel().getFightState() == LevelState.WIN) {
//			gstate.unlockChar(CHAR_07);
			gstate.addCharToInvOnce(CHAR_01);
			Char c = game.getGameState().getPlayer();
			c.levelUp();c.levelUp();c.levelUp();
		}
	}

}
