package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;



public class MyGdxGame extends Game {
	//only 1 SpriteBatch
	
	// taille virtuelle pour le FitViewport
	public static final int v_width = 400;
	public static final int v_height = 200;
	//Box2D scale
	public static final float PPM = 100;
	
	public SpriteBatch batch; // holds textures and images
	 
	@Override
	public void create () {
		batch = new SpriteBatch();
		//set du nouvel écran
		setScreen(new PlayScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		
		//on détruit le spriteBatch
		batch.dispose();
		//catMario.dispose();
	}
}
