package com.gdxtest02;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class CharSelectScreen implements Screen {

	final GdxTest02 game;

	OrthographicCamera camera;

	CharSelectScreenUI ui;

	public CharSelectScreen(final GdxTest02 gam) {
		game = gam;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, game.VIRTUAL_WIDTH, game.VIRTUAL_HEIGHT);
		ui = new CharSelectScreenUI();
		ui.setupUi(this);

	}


	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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