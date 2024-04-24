/*
 * January 11 2022
 * Cristian Diaconu
 * This program provides functions and fields for the ball.
 */
import csta.ibm.pong.GameObject;

public class Ball extends GameObject {
    
	private int dx=15;
	private int dy=15;
	
	@Override 
    public void act(){
        setX(getX()+dx);
        setY(getY()+dy);
    
    }

	// Used to bounce off the top and bottom walls of the field by changing the y direction

	public void bounceY() {
		dy=-dy;
	}
	
	// Used to bounce off the paddles by changing the x direction
	public void bounceX() {
		dx=-dx;
	}

}
