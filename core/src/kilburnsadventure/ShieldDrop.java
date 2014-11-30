package kilburnsadventure;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class ShieldDrop extends ItemDrop{

	private int regSize;  //size of drop
	
	//constructor
	public ShieldDrop(Game game, MapState containingMap, Vector2 reqPosition)
	{
		super(game, containingMap, reqPosition, "dropShield.png");
		position = reqPosition;
		
	}
	
	@Override
	public void update()
	{
		position.y --;
		Player player = map.getPlayer();
		if (player.intersects(new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight())))
		{
			player.setShield();
			map.removeFromObjectList(this);
		}
	}
	
}
