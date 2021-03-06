package com.gdxtest02;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.gdxtest02.levels.Level01;
import com.gdxtest02.levels.Level02;

import static com.gdxtest02.gamestate.GameState.*;

public class MainMenuScreenUI {

	UIBuilder uibuilder;
	private TextButton gobutton;
	private TextButton selectbutton;
	private MainMenuScreen screen;
	private TextButton storybutton;

	void setupUi(final MainMenuScreen screen) {
		uibuilder = new UIBuilder(screen.game);
		Stage stage = uibuilder.getStage();
		Skin skin = uibuilder.getSkin();
		this.screen = screen;
		
		// Create a table that fills the screen. Everything else will go inside this table.
		Table table = new Table();
		table.setFillParent(true);
		stage.addActor(table);
	
		storybutton = new TextButton("Story mode", skin);
		table.add(storybutton).width(300).height(50);
		table.row();
		gobutton = new TextButton("versus", skin);
		table.add(gobutton).width(300).height(50);
		table.row();
		selectbutton = new TextButton("Character select", skin);
		table.add(selectbutton).width(300).height(50);
	
		createListeners();
		
		
	}

	private void createListeners() {
		// Add a listener to the button. ChangeListener is fired when the button's checked state changes, eg when clicked,
		// Button#setChecked() is called, via a key press, etc. If the event.cancel() is called, the checked state will be reverted.
		// ClickListener could have been used, but would only fire when clicked. Also, canceling a ClickListener event won't
		// revert the checked state.
		ChangeListener storylistener = new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
//				screen.game.setScreen(new Level02(screen.game));
				screen.game.getGameState().setGameMode(MODE_STORY);
				screen.game.setScreen(new CharSelectScreen(screen.game));
				screen.dispose();
			}
		};
		storybutton.addListener(storylistener);
		
		ChangeListener gobuttonlistener = new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				screen.game.getGameState().setGameMode(MODE_VERSUS);
				screen.game.setScreen(new FightScreen(screen.game));
				screen.dispose();
			}
		};
		gobutton.addListener(gobuttonlistener);
		
		ChangeListener selectlistener = new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				screen.game.getGameState().setGameMode(MODE_VERSUS);
				screen.game.setScreen(new CharSelectScreen(screen.game));
				screen.dispose();
			}
		};
		selectbutton.addListener(selectlistener);
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
