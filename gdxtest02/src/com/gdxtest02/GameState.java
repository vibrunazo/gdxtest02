package com.gdxtest02;

public class GameState {
	
		private Char player;
		private int curenemy;
	
		public GameState() {
			curenemy = 0;
		}

		/**
		 * @return the curenemy
		 */
		public int getCurenemy() {
			return curenemy;
		}

		/**
		 * @param curenemy the curenemy to set
		 */
		public void setCurenemy(int curenemy) {
			this.curenemy = curenemy;
		}
		
		/**inc curenemy by 1
		 */
		public void incCurenemy() {
			this.curenemy += 1;
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
}
