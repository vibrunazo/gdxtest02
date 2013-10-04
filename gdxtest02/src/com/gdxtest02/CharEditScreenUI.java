package com.gdxtest02;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.gdxtest02.util.ActionBar;

public class CharEditScreenUI {

	private static final int MENU_HEIGHT = 50;
	private static final int MENU_WIDTH = 200;
	private static final int MENU_X = 250;
	private static final int MENU_Y = -100;

	private static final int FACETABLE_X = 50;
	private static final int FACETABLE_Y = 300;
	private static final int FACETABLE_WIDTH = 150;
	
	private static final int ACTIONBAR_X = 50;
	private static final int ACTIONBAR_Y = 100;
	private static final int ACTIONBAR_WIDTH = 150;
	
	private static final int ACTIONINV_X = 300;
	private static final int ACTIONINV_Y = 300;
	private static final int ACTIONINV_WIDTH = 150;
	
	UIBuilder uibuilder;
	private TextButton gobutton;
	private CharEditScreen screen;
	private Stage stage;
	private Skin skin;
	private TextButton p1button;
	private TextButton backbutton;
	private ClickListener backlistener;
	private Char player;
	private ChangeListener gobuttonlistener;

	public void setupUi(final CharEditScreen screen, Char player) {
		uibuilder = new UIBuilder(screen.game);
		stage = uibuilder.getStage();
		skin = uibuilder.getSkin();
		this.screen = screen;
		this.player = player; 

		createListeners();
		
		createCenterTable();
		createLeftTable();
		createActionBar();
		createActionInv();
	}
	
	private void createActionInv() {
		Array<Action> list = player.getActionsInventory();
		ActionBar bar = new ActionBar(list, skin);
		bar.setPosition(ACTIONINV_X + ACTIONINV_WIDTH/2, ACTIONINV_Y);
		stage.addActor(bar);
	}

	private void createActionBar() {
		ActionBar bar = new ActionBar(player, skin);
		bar.setPosition(ACTIONBAR_X + ACTIONBAR_WIDTH/2, ACTIONBAR_Y);
		stage.addActor(bar);
	}

	private void createLeftTable() {
		Table lefttable = new Table();
		lefttable.setPosition(FACETABLE_X + FACETABLE_WIDTH/2, FACETABLE_Y);
		stage.addActor(lefttable);		

		Char c = player;
		p1button = new TextButton(c.getName(), skin);
		Image image = new Image(c.getTex());
		p1button.add(image);
		p1button.setDisabled(true);
		lefttable.add(p1button).width(FACETABLE_WIDTH).height((float) (FACETABLE_WIDTH*1.2));
	}
	
	private void createCenterTable() {
		// Create a table that fills the screen. Everything else will go inside this table.
		Table table = new Table();
		table.setFillParent(true);
		table.setX(MENU_X);
		table.setY(MENU_Y);
		stage.addActor(table);

		gobutton = new TextButton("OK", skin);
		gobutton.addListener(gobuttonlistener);
		table.add(gobutton).width(MENU_WIDTH).height(MENU_HEIGHT);
		table.row();
		
		backbutton = new TextButton("Back to Menu", skin);
		backbutton.addListener(backlistener);
		table.add(backbutton).width(MENU_WIDTH).height(MENU_HEIGHT);
		backbutton.setDisabled(true);
	}


	private void createListeners() {
		gobuttonlistener = new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
//				screen.dispose();

			}
		};
		
		backlistener = new ClickListener() {
			public void clicked(InputEvent event, float x, float y)  {
				screen.back();
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
	
	/**Logs text to Gdx.app.log()
	 * @param text
	 */
	private void log(String text) {
		Gdx.app.log("gdxtest", text);
	}

}
