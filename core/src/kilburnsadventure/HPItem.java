package kilburnsadventure;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class HPItem extends ItemDrop{

	private int regSize;
	public HPItem(Game game, MapState containingMap, Vector2 reqPosition, int size)
	{
		super(game, containingMap, reqPosition, "dropHeart.png");
		position = reqPosition;
		regSize = size;
		
	}
	
	@Override
	public void update()
	{
		position.y --;
		Player player = map.getPlayer();
		if (player.intersects(new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight())))
		{
			player.addHP(regSize);
			map.removeFromObjectList(this);
		}
	}
	
}
