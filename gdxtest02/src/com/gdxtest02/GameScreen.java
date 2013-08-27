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
        
        Char p1;
        Char p2;

        public GameScreen(final GdxTest02 gam) {
                this.game = gam;

                // create the camera and the SpriteBatch
                camera = new OrthographicCamera();
                camera.setToOrtho(false, 800, 480);
                
                p1 = new Char("p1");
                p1.setMaxhp(100);
                p1.setTex("ball02red.png");
                p1.setPos(50, 200);
                
                p2 = new Char("p2");
                p2.setTex("ball02yell.png");
                p2.setPos(800-50-256, 200);

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
                // all drops;
                game.batch.begin();
                game.font.draw(game.batch, "Test", 0, 480);
//                game.batch.draw(p1.getTex(), p1.getPosX(), p1.getPosY());
//                game.batch.draw(p1.getTex(), p1.getPosX(), p1.getPosY());
                p1.draw(game.batch);
                p2.draw(game.batch);
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