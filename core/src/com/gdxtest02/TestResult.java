package com.gdxtest02;

import com.badlogic.gdx.utils.Array;

/**Store test results from combo simulations. So tests can return multiple values
 * such as max dmg, best combo, number of combos with best dmg, etc
 * 
 * @author vib
 *
 */
public class TestResult {
	
	private Array<Integer> bestcombo;
	private int bestdmg = 0;
	private int numberofbests = 0;
	
	public TestResult() {
		bestcombo = new Array<Integer>();
	}

	/**
	 * @return the bestcombo
	 */
	public Array<Integer> getBestcombo() {
		return bestcombo;
	}

	/**
	 * @param bestcombo the bestcombo to set
	 */
	public void setBestcombo(Array<Integer> bestcombo) {
		this.bestcombo = bestcombo;
	}

	/**
	 * @return the bestdmg
	 */
	public int getBestdmg() {
		return bestdmg;
	}

	/**
	 * @param bestdmg the bestdmg to set
	 */
	public void setBestdmg(int bestdmg) {
		this.bestdmg = bestdmg;
	}

	/**
	 * @return the numberofbests
	 */
	public int getNumberofbests() {
		return numberofbests;
	}

	/**
	 * @param numberofbests the numberofbests to set
	 */
	public void setNumberofbests(int numberofbests) {
		this.numberofbests = numberofbests;
	}
	
	/**increments number of bests by 1
	 */
	public void incNumberofbests() {
		numberofbests++;
	}
	
	

}
