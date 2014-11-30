package kilburnsadventure;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class ShieldDrop extends ItemDrop{

	private Vector2 position; //position
	private int regSize;  //size of drop
	private Texture graphic; //path the graph is stored
	
	//constructor
	public ShieldDrop(Game game, MapState containingMap, Vector2 reqPosition)
	{
		super(game, containingMap, reqPosition);
		position = reqPosition;
		graphic = new Texture("graphics/ballon.png");
		
	}
	
	@Override
	public void update()
	{
		position.y --;
		Player player = map.getPlayer();
		if (player.intersects(new Rectangle(position.x, position.y, graphic.getWidth(), graphic.getHeight())))
		{
			player.setShield();
			map.removeFromObjectList(this);
		}
	}
	
	@Override
	public void draw(SpriteBatch spriteBatch)
	{
		
	}	
}
