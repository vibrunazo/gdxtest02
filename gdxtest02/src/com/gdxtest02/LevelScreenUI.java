package com.gdxtest02;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

import static com.gdxtest02.gamestate.LevelState.*;

public class LevelScreenUI {

	private static final int MAINLABEL_HEIGHT = 50;
	private static final int MAINLABEL_WIDTH = 300;

	private static final float CHAR_Y = -150;
	private static final int CHARS_PER_ROW = 4;
	private static final int CHARBUTTON_WIDTH = 50;
	
	private static final float FACETABLE_X = 50;
	private static final  float FACETABLE_Y = 200;
	private static final  float FACETABLE_CHARY = 220;
	private static final  float FACETABLE_CHARSIZE = 0.4f;
	private static final  float FACETABLE_WIDTH = 150;
	private static final  float FACETABLE_HEIGHT = 40;

	UIBuilder uibuilder;
	private TextButton gobutton;
	private LevelScreen screen;
	private Label lvllabel;
	private Stage stage;
	private Skin skin;
	private Table chartable;
	private TextButton p1button;
	private Label statuslabel;
	private TextButton backbutton;
	private ClickListener backlistener;

	public void setupUi(final LevelScreen levelScreen) {
		uibuilder = new UIBuilder(levelScreen.game);
		stage = uibuilder.getStage();
		skin = uibuilder.getSkin();
		this.screen = levelScreen;

		createCenterTable();
		createCharTable();
		createLeftTable();

		createListeners();
	}
	
	private void createLeftTable() {
		Table lefttable = new Table();
		lefttable.setPosition(FACETABLE_X + FACETABLE_WIDTH/2, FACETABLE_Y);
		stage.addActor(lefttable);		

		Char p1 = screen.game.getGameState().getPlayer();
		p1.setPos(FACETABLE_X + FACETABLE_WIDTH/2, FACETABLE_CHARY);
		p1.setScale(FACETABLE_CHARSIZE);
		p1button = new TextButton(p1.getName(), skin);
//		Image image = new Image(p1.getTex());
//		p1button.add(image);
		p1button.setDisabled(true);
		lefttable.add(p1button).width(FACETABLE_WIDTH).height((float) (FACETABLE_HEIGHT));
	}
	
	private void createCharTable() {
		chartable = new Table();
		chartable.setFillParent(true);
		chartable.setY(CHAR_Y);
		stage.addActor(chartable);

		int i = 1;
		int n = 0;
		Array<Char> chars = screen.getCharSequence();
		for (Char c : chars) {
			if (i > CHARS_PER_ROW) {
				chartable.row();
				i = 1;
			}
			TextButton b = addCharButton(c.getName());
			if (screen.game.getGameState().getCurenemy() == n) {
				log("curenemy: " + screen.game.getGameState().getCurenemy());
				b.setChecked(true);
			}
			i++;n++;
		}

	}

	private TextButton addCharButton(String name) {
		TextButton cbutton = new TextButton(name, skin);
		chartable.add(cbutton).width(CHARBUTTON_WIDTH).height(CHARBUTTON_WIDTH);
		cbutton.setName(name);
		//		cbutton.addListener(charlistener);
		cbutton.setDisabled(true);
		return cbutton;
	}

	private void createCenterTable() {
		// Create a table that fills the screen. Everything else will go inside this table.
		Table table = new Table();
		table.setFillParent(true);
		stage.addActor(table);

		lvllabel = new Label(screen.getLevel_name(), skin);
		table.add(lvllabel).width(MAINLABEL_WIDTH).height(MAINLABEL_HEIGHT);
		table.row();
		
		statuslabel = new Label(getFightStatus(), skin);
		table.add(statuslabel).width(MAINLABEL_WIDTH).height(MAINLABEL_HEIGHT);
		table.row();
		
		gobutton = new TextButton("Fight next enemy", skin);
		table.add(gobutton).width(MAINLABEL_WIDTH).height(MAINLABEL_HEIGHT);
		table.row();
		
		backbutton = new TextButton("Back to Menu", skin);
		table.add(backbutton).width(300).height(50);
		backbutton.setDisabled(true);
	}

	private String getFightStatus() {
		int status = screen.game.getGameState().getLevel().getFightState();
		switch (status) {
		case FIGHT: return "Fight!";
		case WIN: return "You Win!";
		case LOSE: return "You Lose!";

		default:return null;
		}
	}

	private void createListeners() {
		// Add a listener to the button. ChangeListener is fired when the button's checked state changes, eg when clicked,
		// Button#setChecked() is called, via a key press, etc. If the event.cancel() is called, the checked state will be reverted.
		// ClickListener could have been used, but would only fire when clicked. Also, canceling a ClickListener event won't
		// revert the checked state.
		ChangeListener gobuttonlistener = new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
//				Char p1 = screen.game.getGameState().getPlayer().getClone();
				Char p1 = screen.game.getGameState().getPlayer();
				Char p2 = screen.getCurrentChar();
				int status = screen.game.getGameState().getLevel().getFightState();
				// if there are no more enemies left, or you lost a fight:
				if (p2 == null || status == LOSE) {
					screen.endLevel();
					return;
				}
				GameScreen gameScreen = new GameScreen(screen.game, p1, p2);
				//				gameScreen.setNextLevel(new LevelScreen(screen.game));
				gameScreen.setNextLevel(screen.getClass());
				screen.game.setScreen(gameScreen);
//				screen.game.getGameState().incCurenemy();
				screen.dispose();

			}
		};
		gobutton.addListener(gobuttonlistener);
		
		backlistener = new ClickListener() {
			public void clicked(InputEvent event, float x, float y)  {
				screen.back();
			}
		};
		backbutton.addListener(backlistener);

	}

	public void draw() {
		uibuilder.draw();
		
	}

	public void resize(int width, int height) {
		uibuilder.resize(width, height);
	}

	public void dispose() {
		uibuilder.dispose();
	}
	
	/**Logs text to Gdx.app.log()
	 * @param text
	 */
	private void log(String text) {
		Gdx.app.log("gdxtest", text);
	}

}
