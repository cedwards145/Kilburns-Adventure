package kilburnsadventure;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class ControlPanel extends GameObject{

	private Rectangle panel;
	public ControlPanel(Game game, Rectangle rectangle)
	{
		super(game);
		panel = rectangle;
	}
	
	@Override
	public void update()
	{
		
	}
	
	@Override
	public void draw(SpriteBatch spriteBatch)
	{
		//super.draw(spriteBatch);
		//spriteBatch.draw(, Rectangle rectangle);	
	}
	
	public static boolean leftIsTouched()
	{
		return ((Gdx.input.getX() < 40) && (480-Gdx.input.getY() <= 100));
	}
	
	public static boolean rightIsTouched()
	{
		return ((40 < Gdx.input.getX() )&& ((Gdx.input.getX()< 100)) && (480-Gdx.input.getY() < 100));
	}
	
	public static boolean upIsTouched()
	{
		return ((Gdx.input.getX() < 100) && (60 < 480-Gdx.input.getY()) && (480-Gdx.input.getY() < 100));
	}
	
	public static boolean downIsTouched()
	{
		return ((Gdx.input.getX() < 100) && (480-Gdx.input.getY() < 40));
	}
	
}
