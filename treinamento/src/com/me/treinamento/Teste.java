package com.me.treinamento;

package com.badlogic.gdx.tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Peripheral;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.tests.utils.GdxTest;
import com.badlogic.gdx.utils.TimeUtils;

public class MultitouchTest extends GdxTest {
        ShapeRenderer renderer;
        OrthographicCamera camera;
        long startTime = TimeUtils.nanoTime();

        Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.WHITE, Color.PINK, Color.ORANGE, 
                Color.YELLOW, Color.MAGENTA, Color.CYAN, Color.LIGHT_GRAY, Color.GRAY, Color.DARK_GRAY};

        @Override
        public void render () {
                Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
                camera.update();
                renderer.setProjectionMatrix(camera.combined);
                renderer.begin(ShapeType.Filled);
                int size = Math.max(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()) / 10;
                for (int i = 0; i < 10; i++) {
                        if (Gdx.input.isTouched(i) == false) continue;

                        float x = Gdx.input.getX(i);
                        float y = Gdx.graphics.getHeight() - Gdx.input.getY(i) - 1;
                        Color color = colors[i % colors.length];
                        renderer.setColor(color);
                        renderer.triangle(x, y + size, x + size, y - size, x - size, y - size);
                }
                renderer.end();
        }

        @Override
        public void create () {
                Gdx.app.log("Multitouch", "multitouch supported: " + Gdx.input.isPeripheralAvailable(Peripheral.MultitouchScreen));
                renderer = new ShapeRenderer();
                camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                camera.position.set(Gdx.graphics.getWidth() / 2.0f, Gdx.graphics.getHeight() / 2.0f, 0);
                Gdx.input.setInputProcessor(this);
        }

        @Override
        public void dispose () {
                renderer.dispose();
        }
}