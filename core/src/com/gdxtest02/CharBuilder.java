package com.gdxtest02;

import static com.gdxtest02.gamestate.GameState.MODE_STORY;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.gdxtest02.chars.StoryChar01;
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
		case CHAR_01: c = new StoryChar01("Moo");break;
		case TEST_CHAR_01: c = new TestChar01("c1");break;
		case TEST_CHAR_02: c = new TestChar02("c2");break;
		case TEST_CHAR_03: c = new TestChar03("c3");break;
		case TEST_CHAR_04: c = new TestChar04("c4");break;
		case TEST_CHAR_05: c = new TestChar05("c5");break;
		case TEST_CHAR_06: c = new TestChar06("c6");break;
		case TEST_CHAR_07: c = new TestChar07("c7");break;
		case TEST_CHAR_08: c = new TestChar08("c8");break;

		default:break;
		}
		return c;
	}
	
	public static Char build(int name, int level) {
		Char c = build(name);
		for (int i = 0; i < level; i++) {
			c.levelUp();
		}
		return c;
	}
	
	/**Builds a list of available chars 
	 * (DEPRECATED) Use Char inventory instead, so there can be more than
	 * one of each char
	 * 
	 * @return
	 */
//	public static ObjectMap<String, Char> buildMapOfUnlockedChars() {
//		ObjectMap<String, Char> chars = new ObjectMap<String, Char>();
//		GameState gstate = GameState.getInstance();
//		if (gstate.isCharUnlocked(CHAR_01))	chars.put("c1", build(CHAR_01));
//		if (gstate.isCharUnlocked(CHAR_02))	chars.put("c2", build(CHAR_02));
//		if (gstate.isCharUnlocked(CHAR_03))	chars.put("c3", build(CHAR_03));
//		if (gstate.isCharUnlocked(CHAR_04))	chars.put("c4", build(CHAR_04));
//		if (gstate.isCharUnlocked(CHAR_05))	chars.put("c5", build(CHAR_05));
//		if (gstate.isCharUnlocked(CHAR_06))	chars.put("c6", build(CHAR_06));
//		if (gstate.isCharUnlocked(CHAR_07))	chars.put("c7", build(CHAR_07));
//		
//		
//		return chars;
//	}
	
	/**List of chars for Story Mode
	 * @return
	 */
	public static Array<Char> buildListOfChars() {
		Array<Char> chars = new Array<Char>();
//		chars.add(build(CHAR_01));
		chars.add(build(CHAR_01, 3));
		chars.add(build(TEST_CHAR_03, 3));
		chars.add(build(TEST_CHAR_04, 3));
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
		return chars;
	}
}
