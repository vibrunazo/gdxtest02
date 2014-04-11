package com.gdxtest02.core;

import com.gdxtest02.core.levels.Level01;
import com.gdxtest02.core.levels.Level02;
import com.gdxtest02.core.levels.Level03;
import com.gdxtest02.core.levels.Level04;


/**Creates instances of Chars
 * @author vib
 *
 */
public class LevelBuilder {
	public static final int LEVEL_01 = 1;
	public static final int LEVEL_02 = 2;
	public static final int LEVEL_03 = 3;
	public static final int LEVEL_04 = 4;
	public static final int LEVEL_05 = 5;
	public static final int LEVEL_06 = 6;
	public static final int LEVEL_07 = 7;

	public static LevelScreen build(int name) {
		GdxTest02 game = GdxTest02.getInstance();
		LevelScreen level = null;
		switch (name) {
		case LEVEL_01: level = new Level01(game);break;
		case LEVEL_02: level = new Level02(game);break;
		case LEVEL_03: level = new Level03(game);break;
		case LEVEL_04: level = new Level04(game);break;
//		case LEVEL_05: level = new Level05(game);break;
//		case LEVEL_06: level = new Level06(game);break;
//		case LEVEL_07: level = new Level07(game);break;

		default:break;
		}
		return level;
	}
}
