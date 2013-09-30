package com.gdxtest02.levels;

import com.gdxtest02.GdxTest02;
import com.gdxtest02.LevelScreen;
import com.gdxtest02.chars.Char02;
import com.gdxtest02.chars.Char03;
import com.gdxtest02.gamestate.GameState;
import static com.gdxtest02.CharBuilder.*;

public class Level01 extends LevelScreen {

	public Level01(GdxTest02 game) {
		super(game);
		addChar(new Char02("c2"));
		addChar(new Char02("c22"));
		addChar(new Char03("c3"));
		
		setLevel_name("level 1");
		ui.setupUi(this);
	}
	
	public void endLevel() {
		super.endLevel();
		GameState gstate = GameState.getInstance();
		gstate.unlockChar(CHAR_01);
	}

}
