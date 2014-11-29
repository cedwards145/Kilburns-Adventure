package kilburnsadventure;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameOverState extends GameState 
{
	protected Texture gameOverImage;
	
	public GameOverState(Game game, StateManager manager)
	{
		super(game, manager);
		
		gameOverImage = new Texture("graphics/gameover.png");
	}
	
	@Override
	public void draw(SpriteBatch spriteBatch)
	{
		spriteBatch.draw(gameOverImage, 0, 0);
	}
	
	@Override
	public void update()
	{
		
	}

}
