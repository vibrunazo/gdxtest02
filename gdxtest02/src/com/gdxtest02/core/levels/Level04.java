package com.gdxtest02.core.levels;

import com.gdxtest02.core.Char;
import com.gdxtest02.core.CharBuilder;
import com.gdxtest02.core.GdxTest02;
import com.gdxtest02.core.LevelScreen;
import com.gdxtest02.core.chars.Char01;
import com.gdxtest02.core.chars.Char02;
import com.gdxtest02.core.chars.Char03;
import com.gdxtest02.core.chars.Char04;
import com.gdxtest02.core.chars.Char05;
import com.gdxtest02.core.chars.Char06;
import com.gdxtest02.core.chars.Char07;
import com.gdxtest02.core.gamestate.GameState;
import com.gdxtest02.core.gamestate.LevelState;

import static com.gdxtest02.core.CharBuilder.*;

public class Level04 extends LevelScreen {

	public Level04(GdxTest02 game) {
		super(game);
		addChar(build(CHAR_01));
		addChar(build(CHAR_01, 3));
		addChar(build(CHAR_07));
		addChar(build(CHAR_07, 2));
		
		setLevel_name("level 4");
		ui.setupUi(this);
	}
	
	public void endLevel() {
		super.endLevel();
		GameState gstate = GameState.getInstance();
		if (gstate.getLevel().getFightState() == LevelState.WIN) {
//			gstate.unlockChar(CHAR_07);
			gstate.addCharToInvOnce(CHAR_07);
			Char c = game.getGameState().getPlayer();
			c.levelUp();c.levelUp();c.levelUp();
		}
	}

}
