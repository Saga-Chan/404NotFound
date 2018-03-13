package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class PlayScreen implements Screen {
	
	MyGdxGame game;
	
	
	private OrthographicCamera gamecam; //cam�ra du jeu
	private Viewport gameP;
	
	private Game_specifications gs;
	//variable pour la map
	private TmxMapLoader mapLoader; //ce qui va lancer la map
	private TiledMap map; //reference vers la map
	private OrthogonalTiledMapRenderer renderer; // rendu de la map sur l'�cran
	
	//variables Box2d
	private World world;
	private Box2DDebugRenderer bdb;
	
	private Mario player;
	
	//constructeur
	public PlayScreen(MyGdxGame game) {
		this.game = game;
		//cree la cam�ra qui va suivre mario
		gamecam = new OrthographicCamera();
		//cree le fitviewport
		gameP = new FitViewport(MyGdxGame.v_width / MyGdxGame.PPM ,MyGdxGame.v_height / MyGdxGame.PPM ,gamecam);
		//cree pour les scores, temps, niveaux
		gs = new Game_specifications(game.batch);
		// TODO Auto-generated constructor stub
		//g�n�re la map et cr�e le rendu de la map
		mapLoader = new TmxMapLoader();
		map = mapLoader.load("level1.tmx");
		renderer = new OrthogonalTiledMapRenderer(map, 1 / MyGdxGame.PPM ) ;
		//centre la cam�ra au d�but du jeu au niveau du viewport
		gamecam.position.set(gameP.getWorldWidth() / 2 , gameP.getWorldHeight() / 2 , 0);
		
		world = new World(new Vector2(0,-10), true); //  gravit� 
		bdb = new Box2DDebugRenderer();
		
		BodyDef bd = new BodyDef();
		PolygonShape shp = new PolygonShape();
		FixtureDef fd = new FixtureDef();
		Body body;
		
		
		
		player = new Mario(world);
		
		//cree le sol, les corps
		for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rt = ((RectangleMapObject) object).getRectangle();
			
			bd.type = BodyDef.BodyType.StaticBody;
			bd.position.set((rt.getX() + rt.getWidth()/2)/MyGdxGame.PPM, (rt.getY() + rt.getHeight()/2)/MyGdxGame.PPM);
			
			body = world.createBody(bd);
			
			shp.setAsBox(rt.getWidth()/2/MyGdxGame.PPM,rt.getHeight()/2/MyGdxGame.PPM);
			fd.shape = shp;
			body.createFixture(fd);
		}
		//tuyaux
		for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rt = ((RectangleMapObject) object).getRectangle();
			
			bd.type = BodyDef.BodyType.StaticBody;
			bd.position.set((rt.getX() + rt.getWidth()/2)/MyGdxGame.PPM, (rt.getY() + rt.getHeight()/2)/MyGdxGame.PPM);
			
			body = world.createBody(bd);
			
			shp.setAsBox(rt.getWidth()/2/MyGdxGame.PPM,rt.getHeight()/2/MyGdxGame.PPM);
			fd.shape = shp;
			body.createFixture(fd);
		}
		//blocs
		for(MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rt = ((RectangleMapObject) object).getRectangle();
			
			bd.type = BodyDef.BodyType.StaticBody;
			bd.position.set((rt.getX() + rt.getWidth()/2)/MyGdxGame.PPM, (rt.getY() + rt.getHeight()/2)/MyGdxGame.PPM);
			
			body = world.createBody(bd);
			
			shp.setAsBox(rt.getWidth()/2/MyGdxGame.PPM,rt.getHeight()/2/MyGdxGame.PPM);
			fd.shape = shp;
			body.createFixture(fd);
		}
		//pi�ces
		for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rt = ((RectangleMapObject) object).getRectangle();
			
			bd.type = BodyDef.BodyType.StaticBody;
			bd.position.set((rt.getX() + rt.getWidth()/2)/MyGdxGame.PPM, (rt.getY() + rt.getHeight()/2)/MyGdxGame.PPM);
			
			body = world.createBody(bd);
			
			shp.setAsBox(rt.getWidth()/2/MyGdxGame.PPM,rt.getHeight()/2/MyGdxGame.PPM);
			fd.shape = shp;
			body.createFixture(fd);
		}
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}
	//action de mario quand une touche est entr�e par l'utilisateur
	public void handleInput(float dt) {
		if(Gdx.input.isKeyJustPressed(Input.Keys.UP))
			player.bdy.applyLinearImpulse(new Vector2(0,4f), player.bdy.getWorldCenter(), true);
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.bdy.getLinearVelocity().x <= 2)
			player.bdy.applyLinearImpulse(new Vector2(0.1f,0),player.bdy.getWorldCenter(), true);
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.bdy.getLinearVelocity().x >= -2)
			player.bdy.applyLinearImpulse(new Vector2(-0.1f,0),player.bdy.getWorldCenter(), true);	
	}
	
	public void update(float dt) {
		handleInput(dt);
		world.step(1/90f, 6, 2);
		
		gamecam.position.x = player.bdy.getPosition().x;
		gamecam.update();
		renderer.setView(gamecam);
	}

	  
	
	
	@Override
	public void render(float delta) {
		update(delta);
		// TODO Auto-generated mGame_specificationsethod stub
		Gdx.gl.glClearColor(0, 0, 0, 1); //clear color
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // clear the screen
		
		renderer.render();
		
		bdb.render(world, gamecam.combined);
		
		game.batch.setProjectionMatrix(gs.stage.getCamera().combined);//ce qui va �tre afficher avec la cam�ra
		gs.stage.draw(); //ce qui va d�ssiner � l'�cran ce qui compose notre stage
		

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		gameP.update(width, height);//si la taille de l'�cran change pour que le viewport puisse se mettre � jour
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

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
