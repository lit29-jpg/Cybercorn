package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter; // provides basic structure of game
import com.badlogic.gdx.Gdx; // gives access to game functions
import com.badlogic.gdx.Input; // Detect keyboard presses
import com.badlogic.gdx.graphics.GL20; // provides openGl constants and commands
import com.badlogic.gdx.graphics.OrthographicCamera; // gives us a 2D camera
import com.badlogic.gdx.graphics.glutils.ShapeRenderer; // draw 2D shapes directly into game
import com.badlogic.gdx.utils.viewport.FitViewport; // keeps the game screen proportions consistent


/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    // screen dimensions
	private static final float WIDTH = 800;
	private static final float HEIGHT = 480;
	
	 // Game objects
    private ShapeRenderer shape;
    private OrthographicCamera camera;
    private FitViewport viewport;

    // Player properties
    private float playerX = 400;
    private float playerY = 240;
    private float playerSize = 24;
    private float playerSpeed = 220;

    // Arena boundaries
    private float arenaX = 80;
    private float arenaY = 50;
    private float arenaWidth = 640;
    private float arenaHeight = 380;
    
    
    @Override
    public void create() {
        shape = new ShapeRenderer();
       
        camera = new OrthographicCamera();
        
        viewport = new FitViewport(WIDTH, HEIGHT, camera);
        viewport.apply();
        
        camera.position.set(WIDTH/2, HEIGHT/2, 0);
        camera.update();
    }

    @Override
    public void render() {
    	Gdx.gl.glClearColor(0.08f, 0.10f, 0.08f,1); // background color, (red, green, blue, opaque)
    	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // clears screen using said color
    	float delta = Gdx.graphics.getDeltaTime(); // fps
    	updatePlayer(delta); // calls delta
    	shape.setProjectionMatrix(camera.combined); //which camera projection to use
    	
    	shape.begin(ShapeRenderer.ShapeType.Line); // begins drawing with outlines

    	shape.setColor(0.5f, 0.8f, 0.4f, 1); // drawing color green
    	shape.rect(arenaX, arenaY, arenaWidth, arenaHeight); // draw rectangle

    	shape.end(); //end drawing
    	
    	shape.begin(ShapeRenderer.ShapeType.Filled); // filled rectangle

    	shape.setColor(0.3f, 0.8f, 1f, 1); // light blue color
    	shape.rect(playerX, playerY, playerSize, playerSize); // draws player using coordinates

    	shape.end(); // end drawing

    }
    
    private void updatePlayer(float delta) {
    	float movement = playerSpeed*delta;
    	
    	//movement controls
    	if (Gdx.input.isKeyPressed(Input.Keys.W)||Gdx.input.isKeyPressed(Input.Keys.UP)) {
    		playerY+=movement;
    	}
    	if (Gdx.input.isKeyPressed(Input.Keys.S)||Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
    		playerY-=movement;
    	
    	}
    	if (Gdx.input.isKeyPressed(Input.Keys.A)||Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
        	playerX-=movement;
        }
    	
    	if (Gdx.input.isKeyPressed(Input.Keys.D)||Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
        	playerX+=movement;
        }
    	 
    	// Keep player inside arena
        playerX = Math.max(arenaX,Math.min(playerX, arenaX + arenaWidth - playerSize));
        playerY = Math.max(arenaY,Math.min(playerY, arenaY + arenaHeight - playerSize));
    	
    	
    	}
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }


    @Override
    public void dispose() {
       shape.dispose();
    }
}
