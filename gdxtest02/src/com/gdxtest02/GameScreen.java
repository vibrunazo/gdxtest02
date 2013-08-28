package com.gdxtest02;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class GameScreen implements Screen {
	final GdxTest02 game;
	
	FPSLogger fps;

	OrthographicCamera camera;
	ShapeRenderer shapeRenderer;

	Char p1;
	Char p2;

	private UIBuilder ui;

	public GameScreen(final GdxTest02 gam) {
		this.game = gam;

		fps = new FPSLogger();
		
		// create the camera and the SpriteBatch
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);

		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(camera.combined);

		p1 = new Char("p1");
		p1.setTex("ball02red.png");
		p1.setPos(50, 150);

		p2 = new Char("p2");
		p2.setTex("ball02yell.png");
		p2.setPos(800-50-256, 150);

		setupUi();
	}
	
	private void setupUi() {
		ui = new UIBuilder(game);
		Stage stage = ui.getStage();
		Skin skin = ui.getSkin();
		
		// Create a table that fills the screen. Everything else will go inside this table.
		Table table = new Table();
		table.setFillParent(true);
		//				table.setBackground(skin.newDrawable("white", Color.LIGHT_GRAY));
		//				table.setSize(260, 195);
		//				table.setPosition(190, 142);
		stage.addActor(table);

		// Create a button with the "default" TextButtonStyle. A 3rd parameter can be used to specify a name other than "default".
		final TextButton button = new TextButton("Click me!", skin);
		table.add(button);

		// Add a listener to the button. ChangeListener is fired when the button's checked state changes, eg when clicked,
		// Button#setChecked() is called, via a key press, etc. If the event.cancel() is called, the checked state will be reverted.
		// ClickListener could have been used, but would only fire when clicked. Also, canceling a ClickListener event won't
		// revert the checked state.
		ChangeListener l = new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				System.out.println("Clicked! Is checked: " + button.isChecked());
				button.setText("Good job!");
				game.setScreen(new GameScreen(game));
				dispose();
			}
		};
		button.addListener(l);
	}

	@Override
	public void render(float delta) {
		updateLogic();

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

		game.batch.begin();
		game.font.draw(game.batch, "Test", 0, 480);

		// tell chars to draw themselves
		p1.draw(game.batch);
		p2.draw(game.batch);

		game.batch.end();

		// tell chars to draw their shapes (health bars)
		shapeRenderer.begin(ShapeType.Filled);
		p1.drawShapes(shapeRenderer);
		p2.drawShapes(shapeRenderer);
		shapeRenderer.end();
		
		ui.draw();
		
		fps.log();

	}

	private void updateLogic() {
		p1.incHp(-1);
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
		p1.dispose();
		p2.dispose();
	}

}