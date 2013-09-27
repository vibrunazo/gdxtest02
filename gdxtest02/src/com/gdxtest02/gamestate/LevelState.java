package com.gdxtest02.gamestate;

public class LevelState {
	public static final int FIGHT = 0;
	public static final int WIN = 1;
	public static final int LOSE = 2;
	
	private int curEnemy;
	/**State of the current fight inside this level, fighting, win, lose etc
	 * 
	 */
	private int fightState;
	
	public LevelState() {
		reset();
	}
	
	/**reset level to beginning state
	 * 
	 */
	public void reset() {
		curEnemy = 0;
		fightState = FIGHT;
	}
	
	/**
	 * @return the curenemy
	 */
	public int getCurenemy() {
		return curEnemy;
	}

	/**
	 * @param curenemy the curenemy to set
	 */
	public void setCurenemy(int curenemy) {
		this.curEnemy = curenemy;
	}
	
	/**inc curenemy by 1
	 * 
	 */
	public void incCurenemy() {
		this.curEnemy += 1;
	}

	/**
	 * @return the fightState
	 */
	public int getFightState() {
		return fightState;
	}

	/**
	 * @param fightState the fightState to set
	 */
	public void setFightState(int fightState) {
		this.fightState = fightState;
	}
	
}
