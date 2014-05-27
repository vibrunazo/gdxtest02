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
	private static final int MENU_WIDTH = 180;
	private static final int MENU_X = 300;
	private static final int MENU_Y = -100;

	private static final int FACETABLE_X = 50;
	private static final int FACETABLE_Y = 300;
	private static final int FACETABLE_WIDTH = 150;
	
	private static final int SWAPTABLE_X = 250;
	private static final int SWAPTABLE_Y = 100;
	private static final int SWAPTABLE_WIDTH = 50;
	
	private static final int ACTIONBAR_X = 50;
	private static final int ACTIONBAR_Y = 100;
	private static final int ACTIONBAR_WIDTH = 150;
	
	private static final int ACTIONINV_X = 350;
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
	private ClickListener addlistener;
	private ClickListener removelistener;
	private ActionBar invBar;
	private ActionBar aBar;

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
		createSwapButtons();
	}
	
	private void createSwapButtons() {
		Table table = new Table();
		table.setPosition(SWAPTABLE_X + SWAPTABLE_WIDTH/2, SWAPTABLE_Y);
		stage.addActor(table);		

		TextButton addbutton = new TextButton("<-", skin);
		addbutton.setDisabled(true);
		table.add(addbutton).width(SWAPTABLE_WIDTH).height(SWAPTABLE_WIDTH);
		addbutton.addListener(addlistener);
		
		TextButton removebutton = new TextButton("->", skin);
		removebutton.setDisabled(true);
		table.add(removebutton).width(SWAPTABLE_WIDTH).height(SWAPTABLE_WIDTH);
		removebutton.addListener(removelistener);
	}

	private void createActionInv() {
		Array<Action> list = player.getActionsInventory();
		invBar = new ActionBar(list, skin);
		invBar.setPosition(ACTIONINV_X + ACTIONINV_WIDTH/2, ACTIONINV_Y);
		stage.addActor(invBar);
	}

	private void createActionBar() {
		aBar = new ActionBar(player, skin);
		aBar.setPosition(ACTIONBAR_X + ACTIONBAR_WIDTH/2, ACTIONBAR_Y);
		stage.addActor(aBar);
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
		
		addlistener = new ClickListener() {
			public void clicked(InputEvent event, float x, float y)  {
				player.addActionFromInv(invBar.getSelected());
				invBar.update();
				aBar.update();
			}
		};
		
		removelistener = new ClickListener() {
			public void clicked(InputEvent event, float x, float y)  {
				player.removeActionFromBar(aBar.getSelected());
				aBar.update();
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
