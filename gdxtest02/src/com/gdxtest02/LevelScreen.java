package com.gdxtest02;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class LevelScreen implements Screen {

	GdxTest02 game;

	OrthographicCamera camera;

	LevelScreenUI ui;

	public LevelScreen(GdxTest02 game) {
		this.game = game;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, game.VIRTUAL_WIDTH, game.VIRTUAL_HEIGHT);
		ui = new LevelScreenUI();
		ui.setupUi(this);

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

}