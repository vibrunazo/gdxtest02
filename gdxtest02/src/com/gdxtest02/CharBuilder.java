package com.gdxtest02;

import com.badlogic.gdx.utils.ObjectMap;
import com.gdxtest02.chars.Char01;
import com.gdxtest02.chars.Char02;
import com.gdxtest02.chars.Char03;
import com.gdxtest02.chars.Char04;
import com.gdxtest02.chars.Char05;
import com.gdxtest02.chars.Char06;
import com.gdxtest02.chars.Char07;
import com.gdxtest02.gamestate.GameState;

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

	public static Char build(int name) {
		Char c = null;
		switch (name) {
		case CHAR_01: c = new Char01("c1");break;
		case CHAR_02: c = new Char02("c2");break;
		case CHAR_03: c = new Char03("c3");break;
		case CHAR_04: c = new Char04("c4");break;
		case CHAR_05: c = new Char05("c5");break;
		case CHAR_06: c = new Char06("c6");break;
		case CHAR_07: c = new Char07("c7");break;

		default:break;
		}
		return c;
	}
	
	/**Builds a list of available chars
	 * @return
	 */
	public static ObjectMap<String, Char> buildMapOfUnlockedChars() {
		ObjectMap<String, Char> chars = new ObjectMap<String, Char>();
		GameState gstate = GameState.getInstance();
		if (gstate.isCharUnlocked(CHAR_01))	chars.put("c1", build(CHAR_01));
		if (gstate.isCharUnlocked(CHAR_02))	chars.put("c2", build(CHAR_02));
		if (gstate.isCharUnlocked(CHAR_03))	chars.put("c3", build(CHAR_03));
		if (gstate.isCharUnlocked(CHAR_04))	chars.put("c4", build(CHAR_04));
		if (gstate.isCharUnlocked(CHAR_05))	chars.put("c5", build(CHAR_05));
		if (gstate.isCharUnlocked(CHAR_06))	chars.put("c6", build(CHAR_06));
		if (gstate.isCharUnlocked(CHAR_07))	chars.put("c7", build(CHAR_07));
		
		return chars;
	}
}
