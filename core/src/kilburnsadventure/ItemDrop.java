package kilburnsadventure;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class ItemDrop extends MapObject{

	protected Vector2 position;
	protected Texture texture;
	//ItemDrop constructor
	public ItemDrop(Game game, MapState containingMap, Vector2 reqPosition, String reqTexture)
	{
		super(game, containingMap);
		position = reqPosition;
		texture = new Texture("graphics/drops/"+reqTexture);
	}
	
	@Override
	public void update()
	{
		
	}
	
	@Override
	public void draw(SpriteBatch spriteBatch)
	{
		spriteBatch.draw(texture, position.x, position.y);
	}
	
}
