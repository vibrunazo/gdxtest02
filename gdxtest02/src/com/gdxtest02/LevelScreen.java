package com.gdxtest02;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Array;

public class LevelScreen implements Screen {

	GdxTest02 game;

	private OrthographicCamera camera;

	protected LevelScreenUI ui;
	
	private Array<Char> charSequence;
	private String level_name;

	public LevelScreen(GdxTest02 game) {
		this.game = game;
		charSequence = new Array<Char>();
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, game.VIRTUAL_WIDTH, game.VIRTUAL_HEIGHT);
		ui = new LevelScreenUI();
//		ui.setupUi(this);

	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		camera.update();
		ui.draw();
	}


	@Override
	public void resize(int width, int height) {
		ui.resize(width, height);
		
	}


	@Override
	public void show() {
		// TODO Auto-generated method stub

	}


	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}


	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}


	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}


	@Override
	public void dispose() {
		ui.dispose();

	}

	/**
	 * @return the charSequence
	 */
	public Array<Char> getCharSequence() {
		return charSequence;
	}

	/**
	 * @param charSequence the charSequence to set
	 */
	public void setCharSequence(Array<Char> charSequence) {
		this.charSequence = charSequence;
	}
	
	/**adds char to sequence
	 * @param c char to add to sequence
	 */
	public void addChar(Char c) {
		charSequence.add(c);
	}
	
	/**Returns the current char in the sequence
	 * @return
	 */
	public Char getCurrentChar() {
		int current = game.getGameState().getCurenemy();
		if (current >= charSequence.size) return null;
		return charSequence.get(current);
	}

	/**ends the level
	 * 
	 */
	public void endLevel() {
		log("end level");
		game.setScreen(new MainMenuScreen(game));
		game.getGameState().setCurenemy(0);
		dispose();
	}
	
	/**Logs text to Gdx.app.log()
	 * @param text
	 */
	private void log(String text) {
		Gdx.app.log("gdxtest", text);
	}

	/**
	 * @return the level_name
	 */
	public String getLevel_name() {
		return level_name;
	}

	/**
	 * @param level_name the level_name to set
	 */
	public void setLevel_name(String level_name) {
		this.level_name = level_name;
	}

}