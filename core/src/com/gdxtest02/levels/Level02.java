package com.gdxtest02.levels;

import static com.gdxtest02.CharBuilder.TEST_CHAR_01;
import static com.gdxtest02.CharBuilder.TEST_CHAR_02;
import static com.gdxtest02.CharBuilder.build;

import com.gdxtest02.Char;
import com.gdxtest02.GdxTest02;
import com.gdxtest02.LevelScreen;
import com.gdxtest02.chars.TestChar02;
import com.gdxtest02.chars.TestChar03;
import com.gdxtest02.chars.TestChar04;
import com.gdxtest02.chars.TestChar05;
import com.gdxtest02.chars.TestChar06;
import com.gdxtest02.gamestate.GameState;
import com.gdxtest02.gamestate.LevelState;

import static com.gdxtest02.CharBuilder.*;

public class Level02 extends LevelScreen {

	public Level02(GdxTest02 game) {
		super(game);
		addChar(build(TEST_CHAR_03));
		addChar(build(TEST_CHAR_04));
		addChar(build(TEST_CHAR_05));
		addChar(build(TEST_CHAR_05));
		addChar(build(TEST_CHAR_06));
		
		setLevel_name("level 2");
		ui.setupUi(this);
	}
	
	public void endLevel() {
		super.endLevel();
		GameState gstate = GameState.getInstance();
		if (gstate.getLevel().getFightState() == LevelState.WIN) {
//			gstate.unlockChar(CHAR_05);
			gstate.addCharToInvOnce(TEST_CHAR_05);
			Char c = game.getGameState().getPlayer();
			c.levelUp();c.levelUp();
		}
	}

}
