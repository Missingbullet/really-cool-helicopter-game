package com.missingbullet.robschatzfinalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;

public class Game extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //this turns the title off
        /*requestWindowFeature(Window.FEATURE_NO_TITLE);*/

        //set screen to full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //GamePanel is being passed into ContentView as the context
        setContentView(new GamePanel(this));
    }
}
