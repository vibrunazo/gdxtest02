package com.gdxtest02.core;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.gdxtest02.core.levels.Level01;
import com.gdxtest02.core.levels.Level02;
import com.gdxtest02.core.levels.Level03;

import static com.gdxtest02.core.LevelBuilder.*;

public class LevelSelectScreenUI {

	UIBuilder uibuilder;
	private TextButton level2button;
	private TextButton level3button;
	private LevelSelectScreen screen;
	private TextButton level1button;
	private TextButton level4button;

	void setupUi(final LevelSelectScreen screen) {
		uibuilder = new UIBuilder(screen.game);
		Stage stage = uibuilder.getStage();
		Skin skin = uibuilder.getSkin();
		this.screen = screen;
		
		// Create a table that fills the screen. Everything else will go inside this table.
		Table table = new Table();
		table.setFillParent(true);
		stage.addActor(table);
	
		level1button = new TextButton("Level 1", skin);
		table.add(level1button).width(300).height(50);
		table.row();
		level2button = new TextButton("Level 2", skin);
		table.add(level2button).width(300).height(50);
		table.row();
		level3button = new TextButton("Level 3", skin);
		table.add(level3button).width(300).height(50);
		table.row();
		level4button = new TextButton("Level 4", skin);
		table.add(level4button).width(300).height(50);
	
		createListeners();
		
		
	}

	private void createListeners() {
		// Add a listener to the button. ChangeListener is fired when the button's checked state changes, eg when clicked,
		// Button#setChecked() is called, via a key press, etc. If the event.cancel() is called, the checked state will be reverted.
		// ClickListener could have been used, but would only fire when clicked. Also, canceling a ClickListener event won't
		// revert the checked state.
		ChangeListener level1listener = new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				setScreen(LEVEL_01);
				screen.dispose();
			}

		};
		level1button.addListener(level1listener);
		
		ChangeListener level2listener = new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				setScreen(LEVEL_02);
				screen.dispose();
			}
		};
		level2button.addListener(level2listener);
		
		ChangeListener level3listener = new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				setScreen(LEVEL_03);
				screen.dispose();
			}
		};
		level3button.addListener(level3listener);
		
		ChangeListener level4listener = new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				setScreen(LEVEL_04);
				screen.dispose();
			}
		};
		level4button.addListener(level4listener);
	}
	
	private void setScreen(int level) {
		screen.game.setScreen(LevelBuilder.build(level));
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
