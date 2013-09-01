package com.gdxtest02;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntMap;

/**class that specifically defines the ui for the Game Screen
 * 
 * @author vib
 *
 */
public class GameScreenUI extends UIBuilder {
	
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
	private ClickListener clickOnGoButton;
	private ClickListener clickOnRestartButton;
	private IntMap<Array<TextButton>> buttonGroups;
	
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
	}

	private void createCenterTable() {
		// Create a table that fills the screen. Everything else will go inside this table.
		Table centerTable = new Table();
		centerTable.setFillParent(true);
		//				table.setBackground(skin.newDrawable("white", Color.LIGHT_GRAY));
		//				table.setSize(260, 195);
		//				table.setPosition(190, 142);
		stage.addActor(centerTable);

		// Create a button with the "default" TextButtonStyle. A 3rd parameter can be used to specify a name other than "default".
		final TextButton gobutton = new TextButton("Go!", skin);
		final TextButton restartbutton = new TextButton("Restart", skin);
		centerTable.add(gobutton).width(160).height(40);
		centerTable.row();
		centerTable.add(restartbutton).width(160).height(40);

		gobutton.addListener(clickOnGoButton);
		restartbutton.addListener(clickOnRestartButton);
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
				if (groupnumber == 1) { // it's p1
					p1.setActiveActionId(activeActionId);
				}
				else p2.setActiveActionId(activeActionId); 
			}
		};
	}

	private void createActionBars() {
		Table tablep1 = new Table();
//		tablep1.setBackground(skin.newDrawable("white", Color.LIGHT_GRAY));
		tablep1.setSize(ACTION_BAR_WIDTH, ACTION_BAR_HEIGHT);
		tablep1.setPosition(ACTION_BAR_X, ACTION_BAR_Y);
		stage.addActor(tablep1);

		Table tablep2 = new Table();
//		tablep2.setBackground(skin.newDrawable("white", Color.LIGHT_GRAY));
		tablep2.setSize(ACTION_BAR_WIDTH, ACTION_BAR_HEIGHT);
		tablep2.setPosition(800 - ACTION_BAR_WIDTH - ACTION_BAR_X, ACTION_BAR_Y);
		stage.addActor(tablep2);

		addActionButton("1", "p11", tablep1, buttons1);
		addActionButton("2", "p12", tablep1, buttons1);
		addActionButton("3", "p13", tablep1, buttons1);
		addActionButton("4", "p14", tablep1, buttons1);
		
		addActionButton("1", "p21", tablep2, buttons2);
		addActionButton("2", "p22", tablep2, buttons2);
		addActionButton("3", "p23", tablep2, buttons2);
		addActionButton("4", "p24", tablep2, buttons2);
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
			log("null on updateAction p: "+ player + " action: " + action);
			return;
		}
		String name = a.getName();
		int cd = a.getCurcooldown();
		String text = name;
		if (cd > 0) text += " - " + Integer.toString(cd);
		setButtonText(player, action, text);
	}
	
	/**Logs text to Gdx.app.log()
	 * @param text
	 */
	private void log(String text) {
		Gdx.app.log("gdxtest", text);
	}
	
}
