package com.mygdx.game;

import static com.mygdx.game.GameSettings.POSITION_ITERATIONS;
import static com.mygdx.game.GameSettings.STEP_TIME;
import static com.mygdx.game.GameSettings.VELOCITY_ITERATIONS;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.GameSettings;

import screens.GameScreen;

public class MyGdxGame extends Game {
	float accumulator = 0;
	public SpriteBatch batch;
	public OrthographicCamera camera;
	public World world;
	public GameScreen gameScreen;

	@Override
	public void create() {
		Box2D.init();
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, GameSettings.SCREEN_WIDTH, GameSettings.SCREEN_HEIGHT);
		world = new World(new Vector2(0, 0), true);
		gameScreen = new GameScreen(this);
		world.step(STEP_TIME, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
		setScreen(gameScreen);
	}

	@Override
	public void dispose() {
		batch.dispose();
	}
	public void stepWorld() {
		float delta = Gdx.graphics.getDeltaTime();
		accumulator += delta;

		if (accumulator >= STEP_TIME) {
			accumulator -= STEP_TIME;
			world.step(STEP_TIME, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
		}
	}
}
