package com.gdxtest02.core.util;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class LinkedListener extends ClickListener {
	public TextButton owner;
	public TextButton getOwner() {return owner;}

	public LinkedListener setOwner(TextButton button) {
		owner = button;
		return this;
	}

}
