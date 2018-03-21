package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class Mario extends Sprite{
	public World world;
	public Body bdy;
	private TextureRegion marioStand;
	
	public Mario(World world, PlayScreen screen) {
		super(screen.getAtlas().findRegion("little_mario"));
		this.world = world;
		defineMario();
		marioStand = new TextureRegion(getTexture(), 0, 10, 16, 18);
		setBounds(0, 0, 20 / MyGdxGame.PPM, 20 / MyGdxGame.PPM);
		setRegion(marioStand); //texture associée au sprite
	}

	public void update(float dt) {
		setPosition(bdy.getPosition().x - getWidth() / 2, bdy.getPosition().y  - getHeight() / 2);
	}
	private void defineMario() {
		// TODO Auto-generated method stub
		BodyDef bd = new BodyDef();
		//position de mario
		bd.position.set(32/ MyGdxGame.PPM,32/ MyGdxGame.PPM);
		//type d'objet 
		bd.type = BodyDef.BodyType.DynamicBody;
		//on crée l'objet mario dans le monde
		bdy = world.createBody(bd);
		
		FixtureDef fd = new FixtureDef();
		CircleShape shape = new CircleShape();
		shape.setRadius(8/ MyGdxGame.PPM);
		fd.shape = shape;
		bdy.createFixture(fd);
		
	}
}