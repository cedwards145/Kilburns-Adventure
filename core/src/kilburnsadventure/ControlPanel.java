package kilburnsadventure;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class ControlPanel
{
	private static Rectangle panel = new Rectangle(0, 0, 150, 150);
	private static Rectangle upButton = new Rectangle(0, 75, 150, 75), downButton = new Rectangle(0, 0, 150, 75);
	protected static Texture joystick;	
	
	public static void init(Game game)
	{
		joystick = new Texture("graphics/joystick.png");
	}
	
	public static void staticUpdate(Game game)
	{
		upButton.x = game.getCameraPosition().x - game.getWidth() / 2;
		downButton.x = upButton.x;
	}
	
	public static void drawJoystick(SpriteBatch spriteBatch, boolean movingUp, boolean movingDown)
	{
		spriteBatch.draw(joystick, upButton.x + 50, upButton.y - 25);
		if (movingUp)
			spriteBatch.draw(joystick, upButton.x + 50, upButton.y);
		else if (movingDown)
			spriteBatch.draw(joystick, upButton.x + 50, upButton.y - 50);
	}
	
	public static boolean leftIsTouched(Vector2 position)
	{
		return (position.x < 40) && (position.y <= 100);
	}
	
	public static boolean rightIsTouched(Vector2 position)
	{
		return ((40 < position.y) && (position.x < 100) && (position.y < 100));
	}
	
	public static boolean upIsTouched(Vector2 position)
	{
		return upButton.contains(position);
	}
	
	public static boolean downIsTouched(Vector2 position)
	{
		return downButton.contains(position);
	}
	
	public static boolean contains(Vector2 position)
	{
		return upButton.contains(position.x, position.y) || downButton.contains(position.x, position.y);
	}
}
