package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;

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


public class MainMenuScreen implements Screen {
	
	Skin skin;
    Stage stage;
    MyGdxGame game;
    InGameScreen inGameScreen;
    CreditsScreen creditsScreen;
    public boolean checked;
    
    public Boolean isCheck()
    {
    	return  checked;
    }

    
    public MainMenuScreen(MyGdxGame pgame) {
    	this.game = pgame;
        
        stage=new Stage();
        Gdx.input.setInputProcessor(stage);
        
        skin = new Skin( Gdx.files.internal( "ui/defaultskin.json" ));

        Table table=new Table();
        table.setSize(650,480);

        final TextButton startGame=new TextButton("start game",skin);
        table.add(startGame).width(200).height(50);
        table.row();

        TextButton options=new TextButton("options",skin);
        table.add(options).width(150).padTop(10).padBottom(3);
        table.row();
    	
        final   TextButton credits=new TextButton("credits",skin);
        table.add(credits).width(150);
        table.row();

         TextButton quit=new TextButton("quit",skin);
        table.add(quit).width(100).padTop(10);
        table.row();
        
        TextField text=new TextField("",skin);
        table.add(text).width(100).padTop(10);
        table.row();
         // [...]
        //String value=text.getText();

       CheckBox box=new CheckBox("done",skin);
        table.add(box).width(100);
        table.row();
        // [...]
    
        checked=box.isChecked();
     /*   String[] items={"cool","mega","awesome"};    
        SelectBox selectbox = new SelectBox(items, skin);
        table.add(selectbox).width(150);
        // [...]
        //String selection=selectbox.getSelection();
        
        */
        stage.addActor(table);
        
        startGame.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
             startGame.addAction(Actions.fadeOut(0.7f));
             game.setScreen(inGameScreen = new InGameScreen(game));
            }
       });
        
        credits.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
             credits.addAction(Actions.fadeOut(0.7f));
             game.setScreen(creditsScreen = new CreditsScreen(game));
            }
       });
        
    }
    
	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		  // called when current screen changes from this to a different screen
        stage.dispose();

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		  // never called automatically
        stage.dispose();

	}

}
