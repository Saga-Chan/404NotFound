package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Mario extends Sprite{
	public enum State { FALLING, JUMPING, STANDING, RUNNING };
	public State currentState;
	public State previousState;
	public World world;
	public Body bdy;
	Vector2 velocity;
	private TextureRegion marioStand;
	private Animation marioRun; 
	private Animation marioJump;
	private boolean runningRight;
	private float stateTimer; //run stage / jump stage
	public static final float MARIO_MOVE_SPEED = 100 / 2;
	public static final float MAX_JUMP_DURATION = .15f;
	public static final float JUMP_SPEED = 250;
	public static final float GRAVITY = 1000;
	public static final float MARIO_EYE_HEIGHT = 16.0f;
	long jumpStartTime;
	
	
	public Mario(World world, PlayScreen screen) {
		super(screen.getAtlas().findRegion("little_mario"));
		this.world = world;
		velocity = new Vector2();
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
	
	public boolean isRunning(){
		if(currentState==State.RUNNING){
			return true;
		}
		return false;
	}
	
	public boolean isStanding(){
		if(currentState==State.STANDING){
			return true;
		}
		return false;
	}
	
	public boolean isJumping(){
		if(currentState==State.JUMPING || currentState==State.FALLING){
			return true;
		}
		return false;
	}
	
	/*private void moveLeft(float delta) {
        //position.x -= delta * MARIO_MOVE_SPEED;
        setPosition(bdy.getPosition().x - delta * MARIO_MOVE_SPEED, bdy.getPosition().y);
    }

    private void moveRight(float delta) {
        //position.x += delta * MARIO_MOVE_SPEED;
    	setPosition(bdy.getPosition().x + delta * MARIO_MOVE_SPEED, bdy.getPosition().y);
    }
    
    private void startJump() {
        // TODO: Set jumpState to JUMPING
        currentState = State.JUMPING;
        // TODO: Set the jump start time
        // Using TimeUtils.nanoTime()
        jumpStartTime = TimeUtils.nanoTime();
        // TODO: Call continueJump()
        continueJump();
    }
    
    private void continueJump() {
        // TODO: First, check if we're JUMPING, if not, just return
        if (currentState == State.JUMPING) {
            // TODO: Find out how long we've been jumping
            float jumpDuration = MathUtils.nanoToSec * (TimeUtils.nanoTime() - jumpStartTime);
            // TODO: If we have been jumping for less than the max jump duration, set GG's vertical speed to the jump speed constant
            // TODO: Else, call endJump()
            if (jumpDuration < MAX_JUMP_DURATION) {
                velocity.y = JUMP_SPEED;
            } else {
                endJump();
            }
        }
    }
    
    private void endJump() {
        // TODO: If we're JUMPING, now we're FALLING
        if (currentState == State.JUMPING) {
            currentState = State.FALLING;
        }
    }*/

	public void update(float delta) {
		setPosition(bdy.getPosition().x - getWidth() / 2, bdy.getPosition().y  - getHeight() / 2);
		/*velocity.y -= delta * GRAVITY;
		bdy.getPosition().mulAdd(velocity, delta);
		
		// TODO: If GigaGal isn't JUMPING, make her now FALLING
        if (currentState != State.JUMPING) {
            currentState = State.FALLING;
        }
        
     // TODO: Check if GigaGal has landed on the ground
        // Remember that position keeps track of GigaGal's eye position, not her feet.
        // If she has indeed landed, change her jumpState to GROUNDED, set her vertical velocity to 0,
        // and make sure her feet aren't sticking into the floor.
        if (bdy.getPosition().y - MARIO_EYE_HEIGHT < 0) {
            currentState = State.STANDING;
            setPosition(bdy.getPosition().x,MARIO_EYE_HEIGHT);
            velocity.y = 0;
        }
        
        if (Gdx.input.isKeyPressed(Keys.UP)) {
            // TODO: Handle jump key
            // Add a switch statement. If the jump key is pressed and GG is GROUNDED, then startJump()
            // If she's JUMPING, then continueJump()
            // If she's falling, then don't do anything
            switch (currentState) {
                case RUNNING:
                    startJump();
                    break;
                case JUMPING:
                    continueJump();
                    break;
                case FALLING:
                    break;
            }
        } else {
            // TODO: If the jump key wasn't pressed, endJump()
            endJump();
        }

        if (Gdx.input.isKeyPressed(Keys.LEFT)) {
            moveLeft(delta);
        } else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
            moveRight(delta);
        }*/
        
		setRegion(getFrame(delta));
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