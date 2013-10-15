package com.gdxtest02;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntMap;
import com.esotericsoftware.tablelayout.Value;

/**class that specifically defines the ui for the Game Screen
 * 
 * @author vib
 *
 */
public class GameScreenUI extends UIBuilder {
	
	private static final int CENTERBUTTON_WIDTH = 140;
	private static final int CENTERBUTTON_HEIGHT = 40;
	private static final float CENTERTABLE_Y = 50;
	private GameScreen screen;
	private Char p1;
	private Char p2;
	
	ClickListener clickOnActionButton;
	private Array<TextButton> buttons1;
	private Array<TextButton> buttons2;
	
	private int ACTION_BAR_WIDTH = 160;
	private int ACTION_BAR_HEIGHT = 160;
	private int ACTION_BAR_X = 20;
	private int ACTION_BAR_Y = 20;
	
	private int CONSOLE_WIDTH = 380;
	private int CONSOLE_HEIGHT = 160;
//	private int CONSOLE_X = 20;
	private int CONSOLE_Y = 20;
	
	
	private ClickListener clickOnGoButton;
	private ClickListener clickOnRestartButton;
	private IntMap<Array<TextButton>> buttonGroups;
	private Array<String> consoleData;
	private List consoleList;
	private ScrollPane scroll;
	private ClickListener clickOnBackButton;
	private Label animTime;
	
	public GameScreenUI(GdxTest02 game, GameScreen gameScreen) {
		super(game);
		this.screen = gameScreen;
		p1 = screen.getP1();
		p2 = screen.getP2();
	}

	public void setupUi() {

		buttons1 = new Array<TextButton>();
		buttons2 = new Array<TextButton>();
		buttonGroups = new IntMap<Array<TextButton>>();
		
		buttonGroups.put(1, buttons1);
		buttonGroups.put(2, buttons2);
		
		createListeners();
		createCenterTable();
		createActionBars();
		createConsole();
	}

	private void createConsole() {
		Table table = new Table();
//		table.setFillParent(true);
		table.setBackground(skin.newDrawable("white", Color.LIGHT_GRAY));
		table.setSize(CONSOLE_WIDTH, CONSOLE_HEIGHT);
		table.setPosition(400 - CONSOLE_WIDTH/2, CONSOLE_Y);
		stage.addActor(table);
		
		
		consoleData = new Array<String>();
//		String[] consoleItems = (String[])consoleData.items;
		String[] consoleItems = {};
		consoleList = new List(consoleItems, skin);
		scroll = new ScrollPane(consoleList, skin);
		scroll.setFillParent(true);
		scroll.setOverscroll(false, false);
//		scroll.setScrollingDisabled(true, false);
//		scroll.setFlickScroll(false);
//		scroll.setClamp(false);
		table.addActor(scroll);
	}

	private void createCenterTable() {
		// Create a table that fills the screen. Everything else will go inside this table.
		Table centerTable = new Table();
		centerTable.setFillParent(true);
		//				table.setBackground(skin.newDrawable("white", Color.LIGHT_GRAY));
		//				table.setSize(260, 195);
		//				table.setPosition(190, 142);
		stage.addActor(centerTable);
		centerTable.setPosition(0, CENTERTABLE_Y);
		
		animTime = new Label("0", skin);
		centerTable.add(animTime);
		centerTable.row();
		
		// Create a button with the "default" TextButtonStyle. A 3rd parameter can be used to specify a name other than "default".
		final TextButton gobutton = new TextButton("Go!", skin);
		gobutton.setDisabled(true);
		final TextButton restartbutton = new TextButton("Restart", skin);
		final TextButton backbutton = new TextButton("Back", skin);
		centerTable.add(gobutton).width(CENTERBUTTON_WIDTH).height(CENTERBUTTON_HEIGHT);
		centerTable.row();
		centerTable.add(restartbutton).width(CENTERBUTTON_WIDTH).height(CENTERBUTTON_HEIGHT);
		centerTable.row();
		centerTable.add(backbutton).width(CENTERBUTTON_WIDTH).height(CENTERBUTTON_HEIGHT);

		gobutton.addListener(clickOnGoButton);
		restartbutton.addListener(clickOnRestartButton);
		backbutton.addListener(clickOnBackButton);
	}

	private void createListeners() {
		clickOnGoButton = new ClickListener() {
			public void clicked(InputEvent event, float x, float y)  {
				screen.go();
			}
		};
		clickOnRestartButton = new ClickListener() {
			public void clicked(InputEvent event, float x, float y)  {
				screen.restart();
			}
		};
		clickOnBackButton = new ClickListener() {
			public void clicked(InputEvent event, float x, float y)  {
				screen.back();
			}
		};

		// what to do when the Action button was clicked
		clickOnActionButton = new ClickListener() {
			public void clicked(InputEvent event, float x, float y)  {
				Actor actor = event.getTarget(); // the label that was clicked
				actor = actor.getParent(); // the button holding that label

				//				Gdx.app.log("gdxtest", "click actor: " + actor.toString());
				int groupnumber = Integer.parseInt(actor.getName().substring(1, 2)); // number of the group
				//				Gdx.app.log("gdxtest", "le click group: " + groupnumber);

				for (TextButton b : buttonGroups.get(groupnumber)) { // for all buttons in this group
//					Gdx.app.log("gdxtest", "looping on button: " + b.getName());
					b.setChecked(false); // uncheck all buttons on this group
				}
				((TextButton)actor).setChecked(true); // set this that was just clicked as checked

				// number of the action
				int activeActionId = Integer.parseInt(actor.getName().substring(2));
				
				// set the action number for the appropriate player
				clickedActionButton(groupnumber, activeActionId);
			}
		};
	}

	private void createActionBars() {
		Table tablep1 = new Table();
		createActionBar(tablep1, "p1", buttons1, ACTION_BAR_X, ACTION_BAR_Y);

		if (screen.p2control != GameScreen.CONTROL_AI) {
			Table tablep2 = new Table();
			createActionBar(tablep2, "p2", buttons2, 800 - ACTION_BAR_WIDTH - ACTION_BAR_X,
					ACTION_BAR_Y);
		}
	}

	private void createActionBar(Table table, String name, Array<TextButton> buttongroup,
			int x, int y) {
		table.setSize(ACTION_BAR_WIDTH, ACTION_BAR_HEIGHT);
		table.setPosition(x, y);
		stage.addActor(table);
		
		addActionButton("1", name + "1", table, buttongroup);
		addActionButton("2", name + "2", table, buttongroup);
		addActionButton("3", name + "3", table, buttongroup);
		addActionButton("4", name + "4", table, buttongroup);
	}

	private void addActionButton(String label, String name, Table table, Array<TextButton> group) {
		TextButton button = new TextButton(label, skin);
		group.add(button);
		table.add(button).width(160).height(40);
		button.setName(name);
		button.addListener(clickOnActionButton);
		table.row();
	}
	
	/**Returns the group of action buttons for either p1 or p2
	 * @param group 1 or 2
	 * @return buttons1 or buttons2
	 */
	private Array<TextButton> getButtonGroup(int group) {
		if (group == 1) return buttons1;
		if (group == 2) return buttons2;
		return null;
	}
	
	/**Sets the text of a text button, specified by action group (p1 or p2),
	 * and button number (starting from button 1)
	 * @param group
	 * @param button
	 * @param text
	 */
	private void setButtonText(int group, int button, String text) {
		if (screen.p2control == GameScreen.CONTROL_AI && group > 1) return;
		TextButton b = getButtonGroup(group).get(button-1);
		b.setText(text);
	}
	
	/**Update the action button text with the action name and cooldown
	 * 
	 * @param player
	 * @param action
	 */
	public void updateActionText(int player, int action) {
		Char c = screen.getPlayer(player);
		Action a = c.getAction(action);
		if (a == null) {
//			log("null on updateAction p: "+ player + " action: " + action);
			return;
		}
		String name = a.getName();
		int cd = a.getCurcooldown();
		String text = name;
		if (cd > 0) text += " - " + Integer.toString(cd);
		setButtonText(player, action, text);
	}
	
	/**logs text to the console log on screen
	 * @param text
	 */
	public void logToConsole(String text) {
		consoleData.add(text);
		consoleList.setItems(consoleData.toArray());
		log(text);
	}
	
	/**Updates scroll pane of the console log, to scroll smoothly over time
	 * @param delta
	 */
	public void updateScroll(float delta) {
		float py = scroll.getScrollPercentY();
		if (py - 10 < 100) py += 0.5*delta;
		else py = 100;
		scroll.setScrollPercentY(py);
		
		scroll.setScrollPercentX(0);
	}
	
	/**Logs text to Gdx.app.log()
	 * @param text
	 */
	private void log(String text) {
		Gdx.app.log("gdxtest", text);
	}

	/**What to do now that I know what action button was clicked and for what player
	 * @param player
	 * @param activeActionId
	 */
	private void clickedActionButton(int player, int activeActionId) {
		Char c = screen.getPlayer(player);
		c.setActiveActionId(activeActionId);
		Action a = c.getActiveAction();
		if (a == null) {
			logToConsole("Action " + activeActionId + " is null. Choose another.");
			return;
		}
		logToConsole(activeActionId + ": " + a.getTooltip());
	}

	/**
	 * @param animTime the animTime to set
	 */
	public void setAnimTime(int time) {
		animTime.setText(Integer.toString(time));
	}
	
}
