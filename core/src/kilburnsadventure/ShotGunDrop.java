package kilburnsadventure;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class ShotGunDrop extends ItemDrop{
	
	private Vector2 position; //position
	private int regSize;  //size of drop
	private Texture graphic; //path the graph is stored
	
	//constructor
	public ShotGunDrop(Game game, MapState containingMap, float xPosition, float yPosition, int size)
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
