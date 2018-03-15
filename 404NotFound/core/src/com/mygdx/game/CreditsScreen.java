package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class CreditsScreen implements Screen {
	
	MyGdxGame game;
	Stage stage;
	
	SpriteBatch batch;
	Texture img;
	TextureRegion MesCredits;
	OrthographicCamera camera ;
	
	
	public CreditsScreen(MyGdxGame pgame) 
	{
		this.game=pgame;
		
		  stage=new Stage();
	      Gdx.input.setInputProcessor(stage);
	    //  Table table=new Table();
	    //    table.setSize(800,480);
	      
	      img = new Texture("credits.jpg");
		  MesCredits = new TextureRegion(img,0,0,512,1024);
			 
		  Image CreditsImage =new Image(MesCredits);
		  
		  CreditsImage.setPosition(160, -1000); // positionner
	//	  stage.addActor(table);
		  stage.addActor(CreditsImage);
		  
		  
		  CreditsImage.addAction(Actions.fadeIn(2));
			
		  CreditsImage.addAction(Actions.moveBy(0,1000,15));
		
		
	}
	
	
	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		 // clear the screen
		
	      Gdx.gl.glClearColor(1,1,1, 1);
	      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	      
	    
	      
	   // let the stage act and draw
	      stage.act(delta);
	      stage.draw();
	     // stage.setViewport(800,480,false);
	      
	 

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		stage.dispose();

	}

	@Override
	public void dispose() {
		stage.dispose();
	}

}
