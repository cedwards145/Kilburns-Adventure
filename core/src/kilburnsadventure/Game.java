package kilburnsadventure;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Game extends ApplicationAdapter {
	protected SpriteBatch spriteBatch;
	
	protected OrthographicCamera camera;

	protected StateManager stateManager;
	protected int width, height;
	
	protected int levelsUnlocked = 0;
	
	protected boolean cameraShaking = false;
	protected int shakeIntensity, shakeDuration, timeShaking = 0;
	
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
		
		ControlPanel.init(this);
		
		// Create game objects here
	}
	
	public void update()
	{
		camera.update();
		
		stateManager.update();
		ControlPanel.staticUpdate(this);
	
		
		if (cameraShaking)
		{
			camera.position.set(MathUtils.random(camera.position.x - shakeIntensity, camera.position.x + shakeIntensity),
				            MathUtils.random(camera.position.y - shakeIntensity, camera.position.y + shakeIntensity), 0);
			timeShaking ++;
			cameraShaking = timeShaking <= shakeDuration;
		}
		
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
		float x = position.x + 300;
		if (x < width / 2)
			x = width / 2;
		
		float y = position.y;
		
		y = height / 2;
		camera.position.set(x, y, 0);
	}
	
	public void setLevelsUnlocked(int value)
	{
		levelsUnlocked = value;
	}
	
	public int getLevelsUnlocked()
	{
		return levelsUnlocked;
	}
	
	public Vector2 getCameraPosition()
	{
		return new Vector2(camera.position.x, camera.position.y);
	}
	
	public OrthographicCamera getCamera()
	{
		return camera;
	}
	
	public void shakeCamera(int strength, int duration)
	{
		timeShaking = 0;
		shakeIntensity = strength;
		shakeDuration = duration;
		cameraShaking = true;
	}
}
