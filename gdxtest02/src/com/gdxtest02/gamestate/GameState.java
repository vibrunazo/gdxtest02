package com.gdxtest02.gamestate;

import com.badlogic.gdx.utils.Array;
import com.gdxtest02.Char;
import com.gdxtest02.CharBuilder;
import com.gdxtest02.GdxTest02;

import static com.gdxtest02.CharBuilder.*;
import static com.gdxtest02.GdxTest02.log;

public class GameState {
	
		public static final int MODE_STORY = 0;
		public static final int MODE_VERSUS = 1;
		
		private Char player;
		private LevelState level;
		private int gameMode;
		private Array<Integer> unlockedChars;
		private Array<Char> charsInventory;
		
		private GdxTest02 game;
		private static GameState gameState;
		
	
		public GameState(GdxTest02 game) {
			this.game = game;
			gameState = this;
			level = new LevelState();
			unlockedChars = new Array<Integer>();
			
//			unlockChar(CHAR_04);
//			unlockChar(CHAR_02);
//			unlockChar(CHAR_03);
			
			buildCharsList();
		}
		
		/**Builds the list of characters
		 * 
		 */
		private void buildCharsList() {
			charsInventory = CharBuilder.buildListOfChars();
		}

		public static GameState getInstance() {
			return gameState;
		}
		
		public void unlockChar(int character) {
			unlockedChars.add(character);
		}
		
		public boolean isCharUnlocked(int character) {
			return unlockedChars.contains(character, true);
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

		/**
		 * @return the chars
		 */
		public Array<Char> getChars() {
//			log("chars: " + charsInventory);
			return charsInventory;
		}
		
		/**Adds this char to inventory
		 * @param c
		 */
		public void addCharToInv(Char c) {
			charsInventory.add(c);
		}
		
		/**Adds a new char of this type to inventory
		 * @param char_type
		 */
		public void addCharToInv(int char_type) {
			charsInventory.add(CharBuilder.build(char_type));
		}
}
