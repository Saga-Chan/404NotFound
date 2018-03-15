package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class MyGdxGame extends Game {
	SpriteBatch batch;
	Texture img;
	TextureRegion MonKoala;
	 Stage stage;
	 OrthographicCamera camera ;

	 MainMenuScreen mainMenuScreen;
	
	Game game;
	
	public MyGdxGame() {
		game = this;
	}
	 
	 

	@Override
	public void create () {
		 img = new Texture("logo_eukaliptus.jpg");
		 MonKoala = new TextureRegion(img,0,0,512,1024);
		 
		 camera=new OrthographicCamera();
		
		 stage=new Stage();
		 
		 Image koalaImage =new Image(MonKoala);
		 koalaImage.setSize(296, 480); // changer la dimension de l'otarie
		 koalaImage.setPosition(400-150, -500); // positionner l'otarie
		
		 stage.addActor(koalaImage);
		 batch = new SpriteBatch();
		
		 koalaImage.setColor(1,1,1,0);
		 koalaImage.addAction(Actions.fadeIn(2));
	//	 koalaImage.addAction (Actions.parallel (Actions.fadeIn ( 2 ), Actions.rotateBy ( 360 , 2 )));
		 koalaImage.addAction(Actions.moveBy(0,100,3));
		 
		//  mainMenuScreen = new MainMenuScreen(this);
	             setScreen( mainMenuScreen = new MainMenuScreen(this));
		 
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		
		
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	//	batch.begin();
		 // update the stage
		  stage.act();
		  // draw the stage
		  stage.draw();
	//	batch.draw(img, 0, 0);
	//	batch.end();
		  super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}


