package com.gdxtest02;

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
	
	public GameScreenUI(GdxTest02 game, GameScreen gameScreen) {
		super(game);
		this.screen = gameScreen;
		p1 = screen.getP1();
		p2 = screen.getP2();
	}

	public void setupUi() {
//		uibuilder = new UIBuilder(game);
//		Stage stage = uibuilder.getStage();
//		Skin skin = uibuilder.getSkin();

		// Create a table that fills the screen. Everything else will go inside this table.
		Table table = new Table();
		table.setFillParent(true);
		//				table.setBackground(skin.newDrawable("white", Color.LIGHT_GRAY));
		//				table.setSize(260, 195);
		//				table.setPosition(190, 142);
		stage.addActor(table);

		// Create a button with the "default" TextButtonStyle. A 3rd parameter can be used to specify a name other than "default".
		final TextButton gobutton = new TextButton("Go!", skin);
		final TextButton restartbutton = new TextButton("Restart", skin);
		table.add(gobutton);
		table.row();
		table.add(restartbutton);

		buttons1 = new Array<TextButton>();
		buttons2 = new Array<TextButton>();
		final IntMap<Array<TextButton>> buttonGroups = new IntMap<Array<TextButton>>(); // map with both group of buttons to choose from
		
		buttonGroups.put(1, buttons1);
		buttonGroups.put(2, buttons2);
		
		// What to do when the Go button was clicked
		ClickListener clickOnGoButton = new ClickListener() {
			public void clicked(InputEvent event, float x, float y)  {
				screen.go();
			}
		};
		// What to do when the Go button was clicked
		ClickListener clickOnRestartButton = new ClickListener() {
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
		gobutton.addListener(clickOnGoButton);
		restartbutton.addListener(clickOnRestartButton);

		Table tablep1 = new Table();
		tablep1.setBackground(skin.newDrawable("white", Color.LIGHT_GRAY));
		tablep1.setSize(100, 100);
		tablep1.setPosition(50, 50);
		stage.addActor(tablep1);

		Table tablep2 = new Table();
		tablep2.setBackground(skin.newDrawable("white", Color.LIGHT_GRAY));
		tablep2.setSize(100, 100);
		tablep2.setPosition(480 - 150, 50);
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
		table.add(button).width(80);
		button.setName(name);
		button.addListener(clickOnActionButton);
		table.row();
	}


}
