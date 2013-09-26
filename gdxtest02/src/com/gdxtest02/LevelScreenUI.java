package com.gdxtest02;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.tablelayout.Value;

public class LevelScreenUI {

	private static final int MAINLABEL_HEIGHT = 50;
	private static final int MAINLABEL_WIDTH = 300;
	
	private static final float CHAR_Y = -150;
	private static final int CHARS_PER_ROW = 4;
	private static final int CHARBUTTON_WIDTH = 50;
	
	UIBuilder uibuilder;
	private TextButton gobutton;
	private LevelScreen screen;
	private Label lvllabel;
	private Stage stage;
	private Skin skin;
	private Table chartable;

	public void setupUi(final LevelScreen levelScreen) {
		uibuilder = new UIBuilder(levelScreen.game);
		stage = uibuilder.getStage();
		skin = uibuilder.getSkin();
		this.screen = levelScreen;
		
		createCenterTable();
		
		createCharTable();
	
		createListeners();
		
		
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
		gobutton = new TextButton("Fight next enemy", skin);
		table.add(gobutton).width(MAINLABEL_WIDTH).height(MAINLABEL_HEIGHT);
		table.row();
	}

	private void createListeners() {
		// Add a listener to the button. ChangeListener is fired when the button's checked state changes, eg when clicked,
		// Button#setChecked() is called, via a key press, etc. If the event.cancel() is called, the checked state will be reverted.
		// ClickListener could have been used, but would only fire when clicked. Also, canceling a ClickListener event won't
		// revert the checked state.
		ChangeListener gobuttonlistener = new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				Char p1 = screen.game.getGameState().getPlayer().getClone();
				Char p2 = screen.getCurrentChar();
				if (p2 == null) {
					screen.endLevel();
					return;
				}
				GameScreen gameScreen = new GameScreen(screen.game, p1, p2);
//				gameScreen.setNextLevel(new LevelScreen(screen.game));
				gameScreen.setNextLevel(screen.getClass());
				screen.game.setScreen(gameScreen);
				screen.game.getGameState().incCurenemy();
				screen.dispose();
				
			}
		};
		gobutton.addListener(gobuttonlistener);
		
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

}
