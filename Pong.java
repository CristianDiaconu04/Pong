/*
 * January 11 2022
 * Cristian Diaconu
 * This program controls the main pong game.
 */
import java.awt.Color;
import csta.ibm.pong.Game;
import javax.swing.JLabel;

public class Pong extends Game{
	// Fields
	private Ball ball;
    private int ballSize=15;
    
    private Paddle rightPaddle;
    private Paddle leftPaddle;
    private int paddleWidth=15;
    private int paddleHeight=120;
    
    private int leftScore=0;
    private int rightScore=0;
    private final int SCORE_TO_WIN=5; // Player must score 5 points to win the game
    
    JLabel scoreBoard=new JLabel();
    JLabel playerWins=new JLabel();    
    
    /**
     *  Checks the position of the ball every tick to bounce it off if appropriate, and scans for certain keypresses to control the paddles	 
     */
    @Override
    public void act(){
    	
    	// Bounces ball off the horizontal field boundaries
    	if (ball.getY()+ballSize>=getFieldHeight() || ball.getY()<=0) {
    		ball.bounceY();
    	}   
    	
    	// Runs when left player has scored a point and checks if they have won yet
    	if (ball.getX()+ballSize>=getFieldWidth()) {
    		leftScore++;
    		
    		if (leftScore<SCORE_TO_WIN) {
        		resetGame();
    		}
    		else {
    			if (leftScore==SCORE_TO_WIN) {
        			scoreBoard.setText(leftScore+":"+rightScore);
    			}
    			playerWins.setText("Left player wins!");
    		}
   		
    	}
    	
    	// Runs when right player has scored a point and checks if they have won yet
    	if (ball.getX()<=0) {
    		rightScore++;
    		
    		if (rightScore<SCORE_TO_WIN) {
    			resetGame();
    		}
    		else {
    			if (rightScore==SCORE_TO_WIN) {
    				scoreBoard.setText(leftScore+":"+rightScore);
    			}    			
    			playerWins.setText("Right player wins!");
    		}
    	}
    
    	
    	// For when the ball collides with the paddles
    	if (ball.collides(leftPaddle)|| ball.collides(rightPaddle)) {
    		ball.bounceX();    		
    	}
   
    	
    	// These are to control the paddles. Z and X are for controlling the left paddle, and N and M are for controlling the right paddle.
    	// Moves left paddle down
    	if (XKeyPressed() && leftPaddle.getY()<getFieldHeight()-leftPaddle.getHeight()) { 
    		leftPaddle.paddleDown();
    	}
    	// Moves left paddle up
    	if (ZKeyPressed() && leftPaddle.getY()>0) {   		
    		leftPaddle.paddleUp();
    	}
    	// Moves right paddle down
    	if (MKeyPressed() && rightPaddle.getY()<getFieldHeight()-rightPaddle.getHeight()) {
    		rightPaddle.paddleDown();
    	}
    	// Moves right paddle up
    	if (NKeyPressed() && rightPaddle.getY()>0) {
    		rightPaddle.paddleUp();
    	}
    }
  
    /** 
     * Resets the field after a point is won 
     */
    public void resetGame() {
    	
    	ball.setY(getFieldHeight()/2);
        ball.setX(getFieldWidth()/2);
        scoreBoard.setText(leftScore+":"+rightScore);
        repaint();
    }
 
    
    /**
     * Sets up the objects used in this program
     * Them being: ball, leftPaddle, rightPaddle, scoreBoard
     */
    @Override
    public void setup(){
        setDelay(100);
        // Setting up the ball
        ball = new Ball();
        ball.setSize(15, 15);
        ball.setColor(Color.RED);
        ball.setY(getFieldHeight()/2);
        ball.setX(getFieldWidth()/2);
        add(ball);
      
        // Setting up both paddles
        leftPaddle=new Paddle();
        leftPaddle.setSize(paddleWidth, paddleHeight);
        leftPaddle.setX(0);
        leftPaddle.setY(getFieldHeight()/2-paddleHeight/2);
        leftPaddle.setColor(Color.green);
        add(leftPaddle);
        
        rightPaddle=new Paddle();
        rightPaddle.setSize(paddleWidth, paddleHeight);
        rightPaddle.setX(getFieldWidth()-paddleWidth);
        rightPaddle.setY(getFieldHeight()/2-paddleHeight/2);
        rightPaddle.setColor(Color.green);
        add(rightPaddle);
        scoreBoard.setText(""+leftScore+":"+rightScore);
        
        // Setting up the scoreboard and win condition board using JLabel
        add(scoreBoard);
        scoreBoard.setForeground(Color.white);
        scoreBoard.setBounds(getFieldWidth()/2-20, 5, 60, 60);
        
        // Setting up the win message for when a player wins
        add(playerWins);
        playerWins.setForeground(Color.white);
        playerWins.setBounds(getFieldWidth()/2-20, 5, 500, 500);
    }

    public static void main(String[] args) {
        Pong p = new Pong();
        p.setSize(800, 600);
        p.setVisible(true);
        p.initComponents();
    }
    
}

