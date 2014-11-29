package kilburnsadventure;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MapState extends GameState 
{
	Texture map;
	
	public MapState(Game game)
	{
		super(game);	
		map = new Texture("assets/graphics/maps/lvl1.jpg");
	}
	
	@Override
	public void update()
	{
		super.update();
	}
	
	@Override
	public void draw(SpriteBatch spriteBatch)
	{
		super.draw(spriteBatch);
		spriteBatch.draw(map, 0, 0);
		
	}
}


