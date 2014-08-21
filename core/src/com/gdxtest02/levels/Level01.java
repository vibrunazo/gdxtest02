package com.gdxtest02.levels;

import com.gdxtest02.Char;
import com.gdxtest02.GdxTest02;
import com.gdxtest02.LevelScreen;
import com.gdxtest02.chars.TestChar02;
import com.gdxtest02.chars.TestChar03;
import com.gdxtest02.gamestate.GameState;
import com.gdxtest02.gamestate.LevelState;

import static com.gdxtest02.CharBuilder.*;

public class Level01 extends LevelScreen {

	public Level01(GdxTest02 game) {
		super(game);
		addChar(build(CHAR_02, 1));
		addChar(build(CHAR_02, 1));
		addChar(build(CHAR_02, 2));
		
		setLevel_name("level 1");
		ui.setupUi(this);
	}
	
	public void endLevel() {
		super.endLevel();
		GameState gstate = GameState.getInstance();
		if (gstate.getLevel().getFightState() == LevelState.WIN) {
			gstate.addCharToInvOnce(build(CHAR_02, 3));
			Char c = game.getGameState().getPlayer();
			c.levelUp();
		}
	}

}
