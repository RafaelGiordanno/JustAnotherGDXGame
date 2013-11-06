package com.me.treinamento;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;

public class Jogo implements ApplicationListener {
	
	Principal principal;
	TiledMap map;
	OrthogonalTiledMapRenderer renderer;
	OrthographicCamera camera;

	@Override
	public void create() {
		// carregar animações
		principal = new Principal(10, 10);
		
		// carregar mapa
		map = new TmxMapLoader().load("data/test.tmx");
		renderer = new OrthogonalTiledMapRenderer(map, 1 / 16f);
		
		// ajustar câmera
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 30, 20);
		camera.update();
		// instanciar personagens
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		
		Gdx.gl.glClearColor(0.f, 0.6f, 0.9f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		float deltaTime = Gdx.graphics.getDeltaTime();
		
		camera.position.x = principal.position.x;
		camera.update();
		
		renderer.setView(camera);
		renderer.render();
		
		SpriteBatch batch = renderer.getSpriteBatch();
		batch.begin();
		
		principal.render(deltaTime, batch);		
		
		batch.end();
		
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
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
