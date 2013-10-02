package com.gdxtest02.levels;

import com.gdxtest02.Char;
import com.gdxtest02.CharBuilder;
import com.gdxtest02.GdxTest02;
import com.gdxtest02.LevelScreen;
import com.gdxtest02.chars.Char01;
import com.gdxtest02.chars.Char02;
import com.gdxtest02.chars.Char03;
import com.gdxtest02.chars.Char04;
import com.gdxtest02.chars.Char05;
import com.gdxtest02.chars.Char06;
import com.gdxtest02.chars.Char07;
import com.gdxtest02.gamestate.GameState;
import com.gdxtest02.gamestate.LevelState;

import static com.gdxtest02.CharBuilder.*;

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
			gstate.addCharToInv(CHAR_07);
			Char c = game.getGameState().getPlayer();
			c.levelUp();c.levelUp();c.levelUp();
		}
	}

}
