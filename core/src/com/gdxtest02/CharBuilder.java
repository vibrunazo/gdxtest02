package com.gdxtest02;

import static com.gdxtest02.gamestate.GameState.MODE_STORY;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.gdxtest02.chars.Bob;
import com.gdxtest02.chars.Goala;
import com.gdxtest02.chars.Linzer;
import com.gdxtest02.chars.Neshaga;
import com.gdxtest02.chars.Pasu;
import com.gdxtest02.chars.Tansa;
import com.gdxtest02.chars.Diras;
import com.gdxtest02.chars.TestChar01;
import com.gdxtest02.chars.TestChar02;
import com.gdxtest02.chars.TestChar03;
import com.gdxtest02.chars.TestChar04;
import com.gdxtest02.chars.TestChar05;
import com.gdxtest02.chars.TestChar06;
import com.gdxtest02.chars.TestChar07;
import com.gdxtest02.chars.TestChar08;
import com.gdxtest02.gamestate.GameState;
import com.gdxtest02.util.Util;

import static com.gdxtest02.gamestate.GameState.*;

/**Creates instances of Chars
 * @author vib
 *
 */
public class CharBuilder {
	public static final int CHAR_01 = 1;
	public static final int CHAR_02 = 2;
	public static final int CHAR_03 = 3;
	public static final int CHAR_04 = 4;
	public static final int CHAR_05 = 5;
	public static final int CHAR_06 = 6;
	public static final int CHAR_07 = 7;
	
	public static final int TEST_CHAR_01 = 6661;
	public static final int TEST_CHAR_02 = 6662;
	public static final int TEST_CHAR_03 = 6663;
	public static final int TEST_CHAR_04 = 6664;
	public static final int TEST_CHAR_05 = 6665;
	public static final int TEST_CHAR_06 = 6666;
	public static final int TEST_CHAR_07 = 6667;
	public static final int TEST_CHAR_08 = 6668;

	public static Char build(int name) {
		Char c = null;
		switch (name) {
		case CHAR_01: c = new Bob();break;
		case CHAR_02: c = new Tansa();break;
		case CHAR_03: c = new Diras();break;
		case CHAR_04: c = new Linzer();break;
		case CHAR_05: c = new Pasu();break;
		case CHAR_06: c = new Goala();break;
		case CHAR_07: c = new Neshaga();break;
		case TEST_CHAR_01: c = new TestChar01();break;
		case TEST_CHAR_02: c = new TestChar02();break;
		case TEST_CHAR_03: c = new TestChar03();break;
		case TEST_CHAR_04: c = new TestChar04();break;
		case TEST_CHAR_05: c = new TestChar05();break;
		case TEST_CHAR_06: c = new TestChar06();break;
		case TEST_CHAR_07: c = new TestChar07();break;
		case TEST_CHAR_08: c = new TestChar08();break;

		default:break;
		}
		return c;
	}
	
	public static Char build(int name, int level) {
		Char c = build(name);
		c.setLevel(level);
		return c;
	}
	
	/**List of chars for Story Mode
	 * @return
	 */
	public static Array<Char> buildListOfChars() {
		Array<Char> chars = new Array<Char>();
//		chars.add(build(CHAR_01));
		chars.add(build(CHAR_01, 3));
//		chars.add(build(CHAR_02, 1));
//		chars.add(build(TEST_CHAR_03, 3));
//		chars.add(build(TEST_CHAR_04, 3));
//		chars.add(build(CHAR_05, 3));
//		chars.add(build(CHAR_06, 4));
//		chars.add(build(CHAR_07, 3));
//		chars.add(build(CHAR_08, 3));
		
		return chars;
	}
	
	/**List of chars for Testing
	 * @return
	 */
	public static Array<Char> buildListOfTestChars() {
		Array<Char> chars = new Array<Char>();
		chars.add(build(TEST_CHAR_01));
		chars.add(build(TEST_CHAR_02, 3));
		chars.add(build(TEST_CHAR_03, 3));
		chars.add(build(TEST_CHAR_04, 3));
		chars.add(build(TEST_CHAR_05, 4));
		chars.add(build(TEST_CHAR_06, 4));
		chars.add(build(TEST_CHAR_07, 3));
		chars.add(build(TEST_CHAR_08, 3));
		chars.add(build(CHAR_02, 1));
		chars.add(build(CHAR_03, 2));
		chars.add(build(CHAR_04, 2));
		chars.add(build(CHAR_05, 2));
		chars.add(build(CHAR_06, 2));
		chars.add(build(CHAR_07, 2));
		return chars;
	}
}
