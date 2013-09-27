package com.gdxtest02;

import com.gdxtest02.gamestate.*;

public class GameState {
	
		private Char player;
		private LevelState level;
		
	
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
}
