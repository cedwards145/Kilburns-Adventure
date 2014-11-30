package kilburnsadventure;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class HPItem extends ItemDrop{

	private Vector2 position;
	private int regSize;
	private Texture graphic;
	public HPItem(Game game, MapState containingMap, Vector2 reqPosition, int size)
	{
		super(game, containingMap, reqPosition);
		position = reqPosition;
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
