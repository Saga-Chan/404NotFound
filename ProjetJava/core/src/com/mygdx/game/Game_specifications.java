package com.mygdx.game;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;


public class Game_specifications {
	
	public Stage stage; // g�re le jeu
	private Viewport viewport; // g�re les diff�rentes plateformes
	private Integer worldTimer;
	private float timecount;
	private Integer score;
	
	//outils Scene2D
	Label countdownLabel;
	Label scoreLabel;
	Label timeLabel;
	Label levelLabel;
	Label worldLabel;
	Label marioLabel;
	
	public Game_specifications(SpriteBatch sb) {
		//on initialise les variables 
		worldTimer = 200;
		timecount = 0;
		score = 0;
		//on utilise une autre cam�ra s�par�e de celle-ci du jeu
		viewport = new FitViewport(MyGdxGame.v_width,MyGdxGame.v_height, new OrthographicCamera());
		stage = new Stage(viewport, sb);
		// organisation des labels
		Table table = new Table();
		table.top(); //la place en haut de notre stage
		table.setFillParent(true); //stage's size
		//on d�finit les labels :textes qu'on va afficher en haut de l'�cran
		countdownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(),Color.WHITE)); //%03d pour le nombre de chiffres entier qui vont etre affich�s
		scoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(),Color.WHITE));
		timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(),Color.WHITE));
		//levelLabel = new Label("1-1", new Label.LabelStyle(new BitmapFont(),Color.WHITE));
		//worldLabel = new Label("WORLD", new Label.LabelStyle(new BitmapFont(),Color.WHITE));
		marioLabel = new Label("MARIO", new Label.LabelStyle(new BitmapFont(),Color.WHITE));
		
		//on ajoute les labels dans la table 
		table.add(marioLabel).expandX().padTop(10);
	
		table.add(worldLabel).expandX().padTop(10);
		table.add(timeLabel).expandX().padTop(10);
	
		//on ajoute une 2 eme ligne a la table
		table.row();
		table.add(scoreLabel).expandX();
		table.add(levelLabel).expandX();
		table.add(countdownLabel).expandX();
		//on ajoute la table au "stage"
		stage.addActor(table);
		
		
	}

}
