package com.gdxtest02.gamestate;

import com.gdxtest02.Char;
import com.gdxtest02.gamestate.*;

public class GameState {
	
		public static final int MODE_STORY = 0;
		public static final int MODE_VERSUS = 1;
		private Char player;
		private LevelState level;
		private int gameMode;
		
	
		public GameState() {
			level = new LevelState();
		}

		/**
		 * @return the curenemy
		 */
		public int getCurenemy() {
			return level.getCurenemy();
		}

		/**
		 * @param curenemy the curenemy to set
		 */
		public void setCurenemy(int curenemy) {
			level.setCurenemy(curenemy);
		}
		
		/**inc curenemy by 1
		 */
		public void incCurenemy() {
			level.incCurenemy();
		}

		/**
		 * @return the player
		 */
		public Char getPlayer() {
			return player;
		}

		/**
		 * @param player the player to set
		 */
		public void setPlayer(Char player) {
			this.player = player;
		}

		/**
		 * @return the level
		 */
		public LevelState getLevel() {
			return level;
		}

		/**
		 * @return the gameMode
		 */
		public int getGameMode() {
			return gameMode;
		}

		/**
		 * @param gameMode the gameMode to set
		 */
		public void setGameMode(int gameMode) {
			this.gameMode = gameMode;
		}
}
