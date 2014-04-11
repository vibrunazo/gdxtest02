package com.gdxtest02.core.levels;

import com.gdxtest02.core.Char;
import com.gdxtest02.core.GdxTest02;
import com.gdxtest02.core.LevelScreen;
import com.gdxtest02.core.chars.Char02;
import com.gdxtest02.core.chars.Char03;
import com.gdxtest02.core.gamestate.GameState;
import com.gdxtest02.core.gamestate.LevelState;

import static com.gdxtest02.core.CharBuilder.*;

public class Level01 extends LevelScreen {

	public Level01(GdxTest02 game) {
		super(game);
		addChar(build(CHAR_04));
		addChar(build(CHAR_06));
		addChar(build(CHAR_03));
		
		setLevel_name("level 1");
		ui.setupUi(this);
	}
	
	public void endLevel() {
		super.endLevel();
		GameState gstate = GameState.getInstance();
		if (gstate.getLevel().getFightState() == LevelState.WIN) {
//			gstate.unlockChar(CHAR_01);
			gstate.addCharToInvOnce(CHAR_06);
			Char c = game.getGameState().getPlayer();
			c.levelUp();
		}
	}

}
