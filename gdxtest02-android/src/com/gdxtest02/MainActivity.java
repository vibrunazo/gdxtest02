package com.gdxtest02;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.gdxtest02.core.GdxTest02;

import static com.gdxtest02.core.GdxTest02.log;

public class MainActivity extends AndroidApplication {
    private View gameView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = true;
        
//        initialize(new GdxTest02(), cfg);

        // Do the stuff that initialize() would do for you
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
        		WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

        gameView = initializeForView(new GdxTest02(), cfg);
        setContentView(gameView);
        
        setImmersiveMode();
//        com.gdxtest02.GdxTest02.log("activity ui vis: " + gameView.getSystemUiVisibility());
    }

    @Override
	protected void onResume() {
		super.onResume();
		setImmersiveMode();
	}

	@SuppressLint("NewApi")
	private void setImmersiveMode() {
		if (Integer.valueOf(android.os.Build.VERSION.SDK_INT) >= 11) {
        	gameView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        	gameView.setSystemUiVisibility(
        			View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        			| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        			| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        			| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
        			| View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
        			| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
	}
}