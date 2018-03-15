package com.mygdx.game;

import com.badlogic.gdx.Game;

public class MyGame extends Game {
	
	MainMenuScreen mainMenuScreen;
	//AnotherScreen anotherScreen;


    @Override
     public void create() {
         //   mainMenuScreen = new MainMenuScreen(this);
         //    anotherScreen = new AnotherScreen(this);
             setScreen(mainMenuScreen);              
     }

}
