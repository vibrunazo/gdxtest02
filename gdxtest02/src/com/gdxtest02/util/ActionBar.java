package com.gdxtest02.util;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.gdxtest02.Action;
import com.gdxtest02.Char;
import static com.gdxtest02.GdxTest02.log;

public class ActionBar extends Table {
	private Array<TextButton> actionButtons;
	private ClickListener actionbuttonlistener;
	private Skin skin;
	private Array<Action> actionlist;


	private static final int ACTIONBUTTON_WIDTH = 160;
	private static final int ACTIONBUTTON_HEIGHT = 40;
	
	public ActionBar(Char player, Skin skin) {
		this(player.getActionBar(), skin);
	}
	

	public ActionBar(Array<Action> list, Skin skin) {
		actionlist = list;
		this.skin = skin;
		createListeners();
		createButtons();
	}


	/**Creates the buttons of the action bar
	 * 
	 */
	private void createButtons() {
		actionButtons = new Array<TextButton>();
		for (Action a : actionlist) {
			addActionButton(a);
			log("adding to table: " + a.getName());
		}
	}
	
	/**Adds one action button
	 * @param a
	 */
	private void addActionButton(Action a) {
		TextButton button = new TextButton(a.getName(), skin);
		button.addListener(actionbuttonlistener);
		actionButtons.add(button);
		add(button).width(ACTIONBUTTON_WIDTH).height(ACTIONBUTTON_HEIGHT);
		row();
	}

	private void createListeners() {
		actionbuttonlistener = new ClickListener() {
			public void clicked(InputEvent event, float x, float y)  {
				Actor actor = event.getTarget(); // the label that was clicked
				actor = actor.getParent(); // the button holding that label
				
				toggleButtonFromGroup(actionButtons, actor);
			}
		};
	}

	/**Sets only the actor button as checked on this grop
	 * @param group
	 * @param actor
	 */
	protected void toggleButtonFromGroup(Array<TextButton> group,
			Actor actor) {
		for (TextButton b : group) {
			b.setChecked(false);
		}
		((TextButton) actor).setChecked(true);
	}

}
