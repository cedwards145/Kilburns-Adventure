package kilburnsadventure;

import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class HPItem extends ItemDrop{

	private Vector2 position;
	private int regSize;
	private Texture graphic;
	public HPItem(Game game, MapState containingMap, float xPosition, float yPosition, int size)
	{
		super(game, containingMap, yPosition, yPosition);
		position = new Vector2(xPosition, yPosition);
		regSize = size;
		graphic = new Texture("graphics/ballon.png");
		
	}
	
	@Override
	public void update()
	{
		List<MapObject> mapObjects = map.getObjectList();
		
		for (MapObject object : mapObjects)
		{
			if (object instanceof Player && object instanceof HPItem)
			{
				Player player = (Player)object;
				if (player.getCollisionBox().contains(position))
				{
					player.addHP(regSize);
					map.removeFromObjectList(this);
				}
			}
		}
	}
	
	@Override
	public void draw(SpriteBatch spriteBatch)
	{
		
	}
	
	public boolean collides(Vector2 point)
	{
		Rectangle collisionBox = new Rectangle(position.x, position.y, graphic.getWidth(), graphic.getHeight());
		return collisionBox.contains(point);
	}
	
	public Rectangle getCollisionBox()
	{
		return new Rectangle(position.x, position.y, graphic.getWidth(), graphic.getHeight());
	}
}
