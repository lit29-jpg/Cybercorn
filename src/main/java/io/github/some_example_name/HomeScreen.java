package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;


public class HomeScreen {
    // screen dimensions
	private static final float WIDTH = 800;
	private static final float HEIGHT = 480;
	
	 // Game objects
    private ShapeRenderer shape;
    private OrthographicCamera camera;
    private FitViewport viewport;


@Override
public void show() {
	shape = new ShapeRenderer;
	camera = new OrthographicCamera();
	viewport = new FitViewport();
	
	viewport.apply();
	camera.position.set(WIDTH/2, HEIGHT/2,0);
	camera.update();
}

public void render(float delta) { // runs repeatedly to display the screen
    // Temporary background color
    Gdx.gl.glClearColor(0.08f, 0.10f, 0.08f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    shape.setProjectionMatrix(camera.combined); // which camera to use



}
@Override
public void resize(int width, int height) {
    viewport.update(width, height, true);
}

@Override
public void pause() {}

@Override
public void resume() {}

@Override
public void hide() {}

@Override
public void dispose() {
    shape.dispose();
}
}