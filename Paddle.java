/*
 * January 11 2022
 * Cristian Diaconu
 * This program is the class which the two paddles are based in.
 */
import csta.ibm.pong.GameObject;
import java.awt.Color;

public class Paddle extends GameObject {
    
	private int paddleSpeed=15;
	
	public void act() {

	}
	// Moves the paddle up 
	public void paddleUp() {
		setY(getY()-paddleSpeed);
	}
	// Moves the paddle down 
	public void paddleDown() {
		setY(getY()+paddleSpeed); 
	}
}
