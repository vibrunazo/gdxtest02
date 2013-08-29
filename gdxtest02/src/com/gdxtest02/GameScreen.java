package com.gdxtest02;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.TimeUtils;
import com.gdxtest02.actions.Heal;

public class GameScreen implements Screen {
	final private GdxTest02 game;

	private FPSLogger fps;

	private OrthographicCamera camera;
	private ShapeRenderer shapeRenderer;

	private Char p1;
	private Char p2;

	/**fightstate, is the game paused or running?
	 * "go" = fight can go on
	 * "paused" = fight is paused
	 */
	private String fightstate; 
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
		p2.setAction(2, new Heal(200));

		setupUi();

		fightstate = "go";
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
		final TextButton gobutton = new TextButton("Go!", skin);
		final TextButton restartbutton = new TextButton("Restart", skin);
		table.add(gobutton);
		table.row();
		table.add(restartbutton);

		Array<TextButton> buttons1 = new Array<TextButton>(); // group of buttons for p1
		Array<TextButton> buttons2 = new Array<TextButton>(); // group of buttons for p2
		final IntMap<Array<TextButton>> buttonGroups = new IntMap<Array<TextButton>>(); // map with both group of buttons to choose from
		final TextButton buttonp11 = new TextButton("1", skin);
		final TextButton buttonp12 = new TextButton("2", skin);
		final TextButton buttonp21 = new TextButton("1", skin);
		final TextButton buttonp22 = new TextButton("2", skin);
		buttons1.add(buttonp11); buttons1.add(buttonp12);
		buttons2.add(buttonp21); buttons2.add(buttonp22);
		buttonGroups.put(1, buttons1);
		buttonGroups.put(2, buttons2);

		// What to do when the Go button was clicked
		ClickListener clickOnGoButton = new ClickListener() {
			public void clicked(InputEvent event, float x, float y)  {
				go();
			}
		};
		// What to do when the Go button was clicked
		ClickListener clickOnRestartButton = new ClickListener() {
			public void clicked(InputEvent event, float x, float y)  {
				restart();
			}
		};

		// what to do when the Action button was clicked
		ClickListener clickOnActionButton = new ClickListener() {
			public void clicked(InputEvent event, float x, float y)  {
				Actor actor = event.getTarget(); // the label that was clicked
				actor = actor.getParent(); // the button holding that label

				//				Gdx.app.log("gdxtest", "click actor: " + actor.toString());
				int groupnumber = Integer.parseInt(actor.getName().substring(1, 2)); // number of the group
				//				Gdx.app.log("gdxtest", "le click group: " + groupnumber);

				for (TextButton b : buttonGroups.get(groupnumber)) { // for all buttons in this group
					Gdx.app.log("gdxtest", "looping on button: " + b.getName());
					b.setChecked(false); // uncheck all buttons on this group
				}
				((TextButton)actor).setChecked(true); // set this that was just clicked as checked

				// number of the action
				int activeAction = Integer.parseInt(actor.getName().substring(2));
				// set the action number for the appropriate player
				if (groupnumber == 1) { // it's p1
					p1.setActiveAction(activeAction);
				}
				else p2.setActiveAction(activeAction); 
			}
		};
		gobutton.addListener(clickOnGoButton);
		restartbutton.addListener(clickOnRestartButton);

		Table tablep1 = new Table();
		tablep1.setBackground(skin.newDrawable("white", Color.LIGHT_GRAY));
		tablep1.setSize(100, 100);
		tablep1.setPosition(50, 50);

		stage.addActor(tablep1);

		tablep1.add(buttonp11).width(80);
		buttonp11.setName("p11");
		buttonp11.addListener(clickOnActionButton);
		tablep1.row();

		tablep1.add(buttonp12).width(80);
		buttonp12.setName("p12");
		buttonp12.addListener(clickOnActionButton);

		Table tablep2 = new Table();
		tablep2.setBackground(skin.newDrawable("white", Color.LIGHT_GRAY));
		tablep2.setSize(100, 100);
		tablep2.setPosition(480 - 150, 50);

		stage.addActor(tablep2);

		tablep2.add(buttonp21).width(80);
		buttonp21.setName("p21");
		buttonp21.addListener(clickOnActionButton);
		tablep2.row();
		tablep2.add(buttonp22).width(80);
		buttonp22.setName("p22");
		buttonp22.addListener(clickOnActionButton);

	}

	protected void restart() {
		game.setScreen(new GameScreen(game));
//		dispose();
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

		//		fps.log();

	}

	private void updateLogic() {
		//		p1.incHp(-1);
	}


	/**calculate damages
	 * 
	 */
	private void go() {
		if (fightstate.equals("paused")) return;

		int actionidp1 = p1.getActiveActionId();
		int actionidp2 = p2.getActiveActionId();
		log("p1 uses: " + actionidp1 +
				", p2 uses: " + actionidp2 + ". Fight!");
		Action actionp1 = p1.getActiveAction();
		Action actionp2 = p2.getActiveAction();
		// each player uses their skill, this won't do actual damage, but record how much dmg they want to do this round
		if (actionp1 != null) actionp1.act(p1, p2);
		if (actionp2 != null) actionp2.act(p2, p1);
		// actually applies the damage done this round by all players
		p1.applyDmg(); p2.applyDmg();

		if (p1.getHp() == 0 || p2.getHp() == 0) {
			fightstate = "paused";
			Char winner = null;
			if (p1.getHp() == 0) {
				if (p2.getHp() > 0) {
					winner = p2;
				}
			}
			if (p2.getHp() == 0) {
				if (p1.getHp() > 0) {
					winner = p1;
				}
			}
			if (winner == null) log("Fight over. Draw!");
			else log("Fight over. " + winner.getName() + " wins.");
		}

	}

	/**Logs text to Gdx.app.log()
	 * @param text
	 */
	private void log(String text) {
		Gdx.app.log("gdxtest", text);
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