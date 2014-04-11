package com.gdxtest02.core.levels;

import com.gdxtest02.core.Char;
import com.gdxtest02.core.GdxTest02;
import com.gdxtest02.core.LevelScreen;
import com.gdxtest02.core.chars.Char01;
import com.gdxtest02.core.chars.Char02;
import com.gdxtest02.core.chars.Char03;
import com.gdxtest02.core.chars.Char04;
import com.gdxtest02.core.chars.Char05;
import com.gdxtest02.core.chars.Char06;
import com.gdxtest02.core.gamestate.GameState;
import com.gdxtest02.core.gamestate.LevelState;

import static com.gdxtest02.core.CharBuilder.*;

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
