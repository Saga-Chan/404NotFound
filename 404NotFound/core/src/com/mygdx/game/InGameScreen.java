package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
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
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;




public class InGameScreen implements Screen {
	
	
	/*
	 public class MyActor extends Actor {
	        Texture texture = new Texture(Gdx.files.internal("data/jet.png"));
	        public boolean started = false;

	        public MyActor(){
	            setBounds(getX(),getY(),texture.getWidth(),texture.getHeight());
	        }
	        
	        @Override
	        public void draw(Batch batch, float alpha){
	            batch.draw(texture,this.getX(),getY());
	        }
	    }
	
	 */
	MyGdxGame game;
	Stage stage;
	Skin skin;
	
	SpriteBatch batch;
	Texture img;
	Texture img2;
	TextureRegion MonPiegeBoutton;
	TextureRegion MonPiegeQuiBouge;
	OrthographicCamera camera ;
	MainMenuScreen mainMenuScreen;
	
	public InGameScreen (MyGdxGame pgame)
	{
		this.game=pgame;
		
		  stage=new Stage();
	      Gdx.input.setInputProcessor(stage);
	      
	      skin = new Skin( Gdx.files.internal( "ui/defaultskin.json" ));
	      
	      Table table=new Table();
	      table.setSize(650,480);
	      
	    final  TextButton options=new TextButton("Cliquez ici pour le bonus",skin);
	      table.add(options).width(200).padTop(10).padBottom(3);
	      table.row();
	      
	      img = new Texture("piege.jpg");
		  MonPiegeBoutton = new TextureRegion(img,0,0,512,1024);
		  
		  img2 = new Texture("piege.jpg");
		  MonPiegeQuiBouge = new TextureRegion(img,0,0,512,1024);
			 
		final  Image piegeBoutton =new Image(MonPiegeBoutton);
		piegeBoutton.setSize(1000, 30); // changer la dimension du piège boutton
		piegeBoutton.setPosition(00, 00); // positionner le piège boutton
		
		
		
		Image piegeQuiBouge = new Image(MonPiegeQuiBouge);
		piegeQuiBouge.setSize(75,30);
		piegeQuiBouge.setPosition(400, 00);
		
		
		      
	      piegeQuiBouge.setColor(1,1,1,0);
	      piegeQuiBouge.addAction(Actions.fadeIn(0));
	  /*    
	      SequenceAction sequenceAction = new SequenceAction();
	      
	      MoveToAction moveAction = new MoveToAction();
	      MoveToAction moveAction2 = new MoveToAction();
	     
	      moveAction.setPosition(200, 0);
	      moveAction.setDuration(1);
	      
	      
	      
	      moveAction.setPosition(500, 0);
	      moveAction.setDuration(2);
	      
	      
	      sequenceAction.addAction(moveAction);
	      sequenceAction.addAction(moveAction2);
	      piegeQuiBouge.addAction(sequenceAction);
	      
	      */
	      
	      MoveToAction moveAction = new MoveToAction();
	      moveAction.setPosition(200, 0);
	      moveAction.setDuration(1);
	      piegeQuiBouge.addAction(moveAction);
	      
	      
		  piegeBoutton.setColor(1,1,1,0);
		  piegeBoutton.addAction(Actions.fadeIn(1));
		  piegeBoutton.addAction(Actions.hide());
		
		  
		  options.addListener(new ClickListener(){
	            @Override
	            public void clicked(InputEvent event, float x, float y) {
	            options.addAction(Actions.hide());
	            piegeBoutton.addAction(Actions.show());
	          
	            }
	       });
		  
	//	  if ( isCheck() == true )
		  
		  stage.addActor(piegeQuiBouge);
		  stage.addActor(piegeBoutton);
	      stage.addActor(table);
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
	      if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
		  {
			  game.setScreen(mainMenuScreen = new MainMenuScreen(game));
		  }
		
	     
		  
		 

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
