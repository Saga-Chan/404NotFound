package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class Mario extends Sprite{
	public enum State { FALLING, JUMPING, STANDING, RUNNING };
	public State currentState;
	public State previousState;
	public World world;
	public Body bdy;
	private TextureRegion marioStand;
	private Animation marioRun; 
	private Animation marioJump;
	private boolean runningRight;
	private float stateTimer; //run stage / jump stage
	
	
	public Mario(World world, PlayScreen screen) {
		super(screen.getAtlas().findRegion("little_mario"));
		this.world = world;
		currentState = State.STANDING;
		previousState = State.STANDING;
		stateTimer = 0;
		runningRight = true;
		//mario run
		Array<TextureRegion> frames = new Array<TextureRegion>();
		for(int i = 1 ; i < 4 ; i++)
			frames.add(new TextureRegion(getTexture(), i * 16, 10, 16,18));
		marioRun = new Animation<TextureRegion>(0.1f, frames);
		frames.clear();
		//mario jump
		for(int i = 4 ; i < 6; i++)
			frames.add(new TextureRegion(getTexture(), i*16, 10, 16, 18));
		marioJump = new Animation<TextureRegion>(0.1f, frames);
		
		marioStand = new TextureRegion(getTexture(), 0, 10, 16, 18);
		defineMario();
		setBounds(0, 0, 20 / MyGdxGame.PPM, 20 / MyGdxGame.PPM);
		setRegion(marioStand); //texture associée au sprite
	}

	public void update(float dt) {
		setPosition(bdy.getPosition().x - getWidth() / 2, bdy.getPosition().y  - getHeight() / 2);
		setRegion(getFrame(dt));
	}
	public TextureRegion getFrame(float dt) {
		currentState = getState();
		
		TextureRegion region;
		switch(currentState) {
		case JUMPING:
			region = (TextureRegion) marioJump.getKeyFrame(stateTimer);
			break;
		case RUNNING:
			region = (TextureRegion) marioRun.getKeyFrame(stateTimer, true);
			break;
		case FALLING:
		case STANDING:
		default:
			region = marioStand;
			break;
			
		}
		if((bdy.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()) {
			region.flip(true, false);
			runningRight = false;
		}
		else if((bdy.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()) {
			region.flip(true, false);
			runningRight = true;
		}
		stateTimer = currentState == previousState ? stateTimer + dt : 0;
		previousState = currentState;
		return region;
	}

	public State getState() {
		if(bdy.getLinearVelocity().y > 0 || bdy.getLinearVelocity().y < 0 && previousState == State.JUMPING)
			return State.JUMPING;
		else if(bdy.getLinearVelocity().y < 0)
			return State.FALLING;
		else if(bdy.getLinearVelocity().x != 0)
			return State.RUNNING;
		else
			return State.STANDING;
	}

	private void defineMario() {
		// TODO Auto-generated method stub
		BodyDef bd = new BodyDef();
		//position de mario
		bd.position.set(200/ MyGdxGame.PPM,64/ MyGdxGame.PPM);
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