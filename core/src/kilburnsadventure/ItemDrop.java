package kilburnsadventure;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class ItemDrop extends MapObject{

	protected Vector2 position;
	//ItemDrop constructor
	public ItemDrop(Game game, MapState containingMap, Vector2 reqPosition)
	{
		super(game, containingMap);
		position = reqPosition;
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
