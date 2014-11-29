package kilburnsadventure;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Game extends ApplicationAdapter {
	protected SpriteBatch spriteBatch;
	
	protected OrthographicCamera camera;

	protected StateManager stateManager;
	protected int width, height;
	
	@Override
	public void create () 
	{
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		width = 800;
		height = 480;
		
		spriteBatch = new SpriteBatch();
		
		stateManager = new StateManager(this);
		stateManager.addState(new TitleState(this, stateManager));
		
		// Create game objects here
	}
	
	public void update()
	{
		camera.update();
		
		stateManager.update();
		
		// Update code goes here
	}
	
	public void draw()
	{
		Gdx.graphics.getGL20().glClearColor( 0, 0, 1, 1 );
		Gdx.graphics.getGL20().glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
		  
		spriteBatch.setProjectionMatrix(camera.combined);
		
		spriteBatch.begin();		
		// Draw code goes here
		stateManager.draw(spriteBatch);
		
		spriteBatch.end();
	}


	@Override
	public void render () 
	{
		update();
		draw();
		
		// Don't change this!
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public void cameraLookAt(Vector2 position)
	{
		camera.position.set(position);
	}
}
