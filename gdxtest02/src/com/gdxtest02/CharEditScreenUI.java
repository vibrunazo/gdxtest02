package com.gdxtest02;

import com.badlogic.gdx.Gdx;
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
import com.gdxtest02.util.ActionBar;

import static com.gdxtest02.gamestate.LevelState.*;

public class CharEditScreenUI {

	private static final int MAINLABEL_HEIGHT = 50;
	private static final int MAINLABEL_WIDTH = 300;

	private static final int FACETABLE_X = 50;
	private static final int FACETABLE_Y = 300;
	private static final int FACETABLE_WIDTH = 150;
	
	private static final int ACTIONBAR_X = 50;
	private static final int ACTIONBAR_Y = 100;
	private static final int ACTIONBAR_WIDTH = 150;
	
	private static final int ACTIONBUTTON_WIDTH = 160;
	private static final int ACTIONBUTTON_HEIGHT = 40;

	UIBuilder uibuilder;
	private TextButton gobutton;
	private CharEditScreen screen;
	private Stage stage;
	private Skin skin;
	private TextButton p1button;
	private TextButton backbutton;
	private ClickListener backlistener;
	private Array<TextButton> actionButtons;
	private Char player;
	private ClickListener actionbuttonlistener;
	private ChangeListener gobuttonlistener;

	public void setupUi(final CharEditScreen screen, Char player) {
		uibuilder = new UIBuilder(screen.game);
		stage = uibuilder.getStage();
		skin = uibuilder.getSkin();
		this.screen = screen;
		this.player = player; 
//				screen.game.getGameState().getPlayer();

		createListeners();
		
		createCenterTable();
		createLeftTable();
		createActionBar();

		
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
		stage.addActor(table);

		gobutton = new TextButton("OK", skin);
		gobutton.addListener(gobuttonlistener);
		table.add(gobutton).width(MAINLABEL_WIDTH).height(MAINLABEL_HEIGHT);
		table.row();
		
		backbutton = new TextButton("Back to Menu", skin);
		backbutton.addListener(backlistener);
		table.add(backbutton).width(300).height(50);
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
