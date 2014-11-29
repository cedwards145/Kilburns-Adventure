package kilburnsadventure;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game extends ApplicationAdapter {
	protected SpriteBatch spriteBatch;
	protected Texture img;
	
	protected OrthographicCamera camera;

	protected StateManager stateManager;
	
	@Override
	public void create () 
	{
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		
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
}
