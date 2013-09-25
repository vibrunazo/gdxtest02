package com.gdxtest02;

import com.gdxtest02.chars.Char01;
import com.gdxtest02.chars.Char02;
import com.gdxtest02.chars.Char03;
import com.gdxtest02.chars.Char04;
import com.gdxtest02.chars.Char05;
import com.gdxtest02.chars.Char06;
import com.gdxtest02.chars.Char07;

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
}
