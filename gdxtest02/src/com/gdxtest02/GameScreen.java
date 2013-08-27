package com.gdxtest02;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class GameScreen implements Screen {
        final GdxTest02 game;

        OrthographicCamera camera;

        public GameScreen(final GdxTest02 gam) {
                this.game = gam;

                // create the camera and the SpriteBatch
                camera = new OrthographicCamera();
                camera.setToOrtho(false, 800, 480);


        }

        @Override
        public void render(float delta) {
                // clear the screen with a dark blue color. The
                // arguments to glClearColor are the red, green
                // blue and alpha component in the range [0,1]
                // of the color to be used to clear the screen.
                Gdx.gl.glClearColor(0, 0, 0.2f, 1);
                Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

                // tell the camera to update its matrices.
                camera.update();

                // tell the SpriteBatch to render in the
                // coordinate system specified by the camera.
                game.batch.setProjectionMatrix(camera.combined);

                // begin a new batch and draw the bucket and
                // all drops
                game.batch.begin();
                game.font.draw(game.batch, "Test", 0, 480);
                game.batch.end();

        }

        @Override
        public void resize(int width, int height) {
        }

        @Override
        public void show() {
        }

        @Override
        public void hide() {
        }

        @Override
        public void pause() {
        }

        @Override
        public void resume() {
        }

        @Override
        public void dispose() {
        }

}