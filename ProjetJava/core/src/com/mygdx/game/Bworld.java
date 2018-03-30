package com.mygdx.game;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Bworld {
	public Bworld(World world, TiledMap map)
	{
		BodyDef bd = new BodyDef();
		PolygonShape shp = new PolygonShape();
		FixtureDef fd = new FixtureDef();
		Body body;
		
		
		
		
		
		//cree le sol, les corps
		for(MapObject object : map.getLayers().get(0).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rt = ((RectangleMapObject) object).getRectangle();
			
			bd.type = BodyDef.BodyType.StaticBody;
			bd.position.set((rt.getX() + rt.getWidth()/2)/MyGdxGame.PPM, (rt.getY() + rt.getHeight()/2)/MyGdxGame.PPM);
			
			body = world.createBody(bd);
			
			shp.setAsBox(rt.getWidth()/2/MyGdxGame.PPM,rt.getHeight()/2/MyGdxGame.PPM);
			fd.shape = shp;
			body.createFixture(fd);
		}
		//tuyaux
		/*for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rt = ((RectangleMapObject) object).getRectangle();
			
			bd.type = BodyDef.BodyType.StaticBody;
			bd.position.set((rt.getX() + rt.getWidth()/2)/MyGdxGame.PPM, (rt.getY() + rt.getHeight()/2)/MyGdxGame.PPM);
			
			body = world.createBody(bd);
			
			shp.setAsBox(rt.getWidth()/2/MyGdxGame.PPM,rt.getHeight()/2/MyGdxGame.PPM);
			fd.shape = shp;
			body.createFixture(fd);
		}*/
		//blocs
		for(MapObject object : map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rt = ((RectangleMapObject) object).getRectangle();
			new Brick(world,map,rt);
		}
		//pièces
		/*for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rt = ((RectangleMapObject) object).getRectangle();
			new Coin(world, map, rt);
		}*/
		
	}

}
