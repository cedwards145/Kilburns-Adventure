package kilburnsadventure;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class AmmoDrop extends ItemDrop{
	
	private Vector2 position; //position
	private Texture graphic; //path the graph is stored
	private Weapon weapon;
	private int numberBullets;
	
	//constructor
	public AmmoDrop(Game game, MapState containingMap, Vector2 reqPosition, Weapon reqWeapon, int reqNumberBullets)
	{
		super(game, containingMap, reqPosition);
		position = reqPosition;
		weapon = reqWeapon;
		numberBullets = reqNumberBullets;
	}
	
	@Override
	public void update()
	{
		position.y --;
		Player player = map.getPlayer();
		if (player.intersects(new Rectangle(position.x, position.y, graphic.getWidth(), graphic.getHeight())))
		{
			weapon.increaseAmmo(numberBullets);
			map.removeFromObjectList(this);
		}
	}
	
	@Override
	public void draw(SpriteBatch spriteBatch)
	{
		
	}	

}
