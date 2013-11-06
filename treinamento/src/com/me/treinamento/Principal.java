package com.me.treinamento;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Principal {
	
	Vector2 position;
	Vector2 velocity;
	State state;
	
	Texture principalTexture;
	Animation standAnim;
	Animation jumpAnim;
	Animation walkAnim;
	
	boolean isFacingRight = true;
	boolean onGround = false;
	
	public static float WIDTH = 16.f;
	public static float HEIGHT = 32.f;
	public static final float MAX_VELOCITY = 10f;
	public static final float JUMP_VELOCITY = 40f;
	public static final float DAMPING = 0.87f;
	
	enum State {
		Standing,
		Walking,
		Jumping
	}
	
	float stateTime = 0f;
	
	public Principal(Vector2 position) {
		this.position = position;
		state = State.Walking;
		loadAnimations();
	}
	
	public Principal(float x, float y) {
		this(new Vector2(x, y));
	}
	
	private void loadAnimations() {
		principalTexture = new Texture("data/anim.png");
		TextureRegion[] regions = TextureRegion.split(principalTexture, 16, 32)[0];
		
		standAnim = new Animation(0, regions[0]);
		jumpAnim = new Animation(0, regions[1]);
		walkAnim = new Animation(0.15f, regions[0], regions[1], regions[2], regions[3]);
		
		Principal.WIDTH  = 1 / 16f * regions[0].getRegionWidth();
		Principal.HEIGHT = 1 / 16f * regions[0].getRegionHeight();
	}
	
	public void render(float deltaTime, SpriteBatch batch) {
		TextureRegion frame = null;
		
		stateTime += deltaTime;
		System.out.println(stateTime);
		
		switch (state) {
			case Standing:
				frame = standAnim.getKeyFrame(stateTime);
				break;
			case Walking:
				frame = walkAnim.getKeyFrame(stateTime);
				break;
			case Jumping:
				frame = jumpAnim.getKeyFrame(stateTime);
				break;
		}
		
		if (isFacingRight) {
			batch.draw(frame, position.x, position.y, Principal.WIDTH, Principal.HEIGHT);
		} else {
			batch.draw(frame, position.x + Principal.WIDTH, position.y, -Principal.WIDTH, Principal.HEIGHT);
		}
	}

}
