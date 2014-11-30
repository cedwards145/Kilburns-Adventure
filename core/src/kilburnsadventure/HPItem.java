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
		position.y --;
		Player player = map.getPlayer();
		if (player.intersects(new Rectangle(position.x, position.y, graphic.getWidth(), graphic.getHeight())))
		{
			player.addHP(regSize);
			map.removeFromObjectList(this);
		}
	}
	
	@Override
	public void draw(SpriteBatch spriteBatch)
	{
		
	}
}
