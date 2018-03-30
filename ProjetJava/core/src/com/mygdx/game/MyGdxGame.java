package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
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
	private Music music;
	 
	@Override
	public void create () {
		batch = new SpriteBatch();
		//set du nouvel écran
		setScreen(new PlayScreen(this));
		
		music=Gdx.audio.newMusic(Gdx.files.internal("C:/Users/maxen/Desktop/404NotFound-deplacement(1)/404NotFound-deplacement/ProjetJava/core/assets/chop.mp3"));
		music.setLooping(true);
		music.play();
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
		music.dispose();
	}
}
