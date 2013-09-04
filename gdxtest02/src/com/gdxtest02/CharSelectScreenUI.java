package com.gdxtest02;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.Array;
import com.gdxtest02.chars.Char01;
import com.gdxtest02.chars.Char02;
import com.gdxtest02.chars.Char03;
import com.gdxtest02.chars.CharYagg01;

public class CharSelectScreenUI {

	UIBuilder uibuilder;
	private TextButton gobutton;
	private TextButton backbutton;
	private CharSelectScreen screen;
	
	private float MENUY = -150;
	
	private Char p1;
	private Char p2;
	private Stage stage;
	private Skin skin;
	private Table chartable;
	private ChangeListener charlistener;
	private ChangeListener gobuttonlistener;
	private ChangeListener selectlistener;
	private Array<Char> chars;

	void setupUi(final CharSelectScreen screen) {
		uibuilder = new UIBuilder(screen.game);
		stage = uibuilder.getStage();
		skin = uibuilder.getSkin();
		this.screen = screen;
		
		createListeners();
		
		createMenuTable();
		createCharTable();
	
		p1 = new CharYagg01("p1");
		p2 = new Char03("p2");
		
		chars = new Array<Char>();
		chars.add(new Char01("c1"));
		
		
	}

	private void createCharTable() {
		chartable = new Table();
		chartable.setFillParent(true);
		stage.addActor(chartable);
		
		addCharButton("C1");
		addCharButton("C2");
		addCharButton("C3");
	}

	private void addCharButton(String name) {
		TextButton cbutton = new TextButton(name, skin);
		chartable.add(cbutton).width(50).height(50);
		cbutton.addListener(charlistener);
	}

	private void createMenuTable() {
		Table menutable = new Table();
		menutable.setFillParent(true);
		stage.addActor(menutable);
	
		gobutton = new TextButton("Fight!", skin);
		menutable.add(gobutton).width(300).height(50);
		menutable.row();
		backbutton = new TextButton("Back to Menu", skin);
		menutable.add(backbutton).width(300).height(50);
		menutable.setY(MENUY);
		gobutton.addListener(gobuttonlistener);
		backbutton.addListener(selectlistener);
	}

	private void createListeners() {
		gobuttonlistener = new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				System.out.println("Clicked! Is checked: " + gobutton.isChecked());
				screen.game.setScreen(new GameScreen(screen.game, p1, p2));
				screen.dispose();
			}
		};
		
		selectlistener = new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				screen.game.setScreen(new MainMenuScreen(screen.game));
				screen.dispose();
			}
		};
		
		charlistener = new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				log("click char");
			}
		};
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
	
	private void log(String text) {
		Gdx.app.log("gdxtest", text);
	}

}
