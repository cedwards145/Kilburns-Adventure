package kilburnsadventure;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class Player extends GameObject{

	//x-axis position
	private int x;
	//y-axis position
	private int y;
	//player constructor
	public Player(Game game){
	    super(game);
	}
	
	public void update(){
		
	}
	
	public void draw(){
		
	}
	
	//variable checks if key is pressed
	private boolean leftMove, rightMove, upMove, downMove;
	
	public void updateMotion(){
		leftMove = Gdx.input.isKeyPressed(Keys.LEFT);
		rightMove = Gdx.input.isKeyPressed(Keys.RIGHT);
		upMove = Gdx.input.isKeyPressed(Keys.UP);
		downMove = Gdx.input.isKeyPressed(Keys.DOWN);
		
		if (leftMove)
			x -= 80;
		
		if (rightMove)
			x += 80;
		
		if (upMove)
			y += 48;
		
		if (downMove)
			y -= 48;
		
		if (x < 0)
			x = 0;
		if (y < 0)
			y = 0;
		if (x > 800)
		    x = 800;
		if (y > 480)
			y = 480;
		
	}
}
