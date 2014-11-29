package kilburnsadventure;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class ItemDrop extends MapObject{

	private Vector2 position;
	//ItemDrop constructor
	public ItemDrop(Game game, MapState containingMap, float xPosition, float yPosition)
	{
		super(game, containingMap);
		position = new Vector2(xPosition, yPosition);
	}
	
	@Override
	public void update()
	{
		
	}
	
	@Override
	public void draw(SpriteBatch spriteBatch)
	{
		
	}
	
}
