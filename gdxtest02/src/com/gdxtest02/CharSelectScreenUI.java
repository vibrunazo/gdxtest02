package com.gdxtest02;

import com.badlogic.gdx.Gdx;
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
import com.badlogic.gdx.utils.ObjectMap;
import com.gdxtest02.chars.Char01;
import com.gdxtest02.chars.Char02;
import com.gdxtest02.chars.Char03;
import com.gdxtest02.chars.Char04;
import com.gdxtest02.chars.Char05;
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
	private ClickListener charlistener;
	private ChangeListener gobuttonlistener;
	private ChangeListener selectlistener;
	private ObjectMap<String, Char> chars;

	void setupUi(final CharSelectScreen screen) {
		uibuilder = new UIBuilder(screen.game);
		stage = uibuilder.getStage();
		skin = uibuilder.getSkin();
		this.screen = screen;
		
		chars = new ObjectMap<String, Char>();
		chars.put("c1", new Char01("c1"));
		chars.put("c2", new Char02("c2"));
		chars.put("c3", new Char03("c3"));
		chars.put("c4", new Char04("c4"));
		chars.put("c5", new Char05("c5"));
		chars.put("y1", new CharYagg01("y1"));
		
		createListeners();
		
		createMenuTable();
		createCharTable();
	
		p1 = new CharYagg01("p1");
		p2 = new Char05("p2");
		
	}

	private void createCharTable() {
		chartable = new Table();
		chartable.setFillParent(true);
		stage.addActor(chartable);
		
		for (Char c : chars.values()) {
			addCharButton(c.getName());
		}
		
	}

	private void addCharButton(String name) {
		TextButton cbutton = new TextButton(name, skin);
		chartable.add(cbutton).width(50).height(50);
		cbutton.setName(name);
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
		
		charlistener = new ClickListener() {
			public void clicked(InputEvent event, float x, float y)  {
				String name = event.getTarget().getParent().getName();
				Char c = chars.get(name);
				p1 = c;
				log("click char " + name + " : " + c.getFullDescription());
				
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
