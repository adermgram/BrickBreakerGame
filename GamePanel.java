import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
class GamePanel extends JPanel implements ActionListener, KeyListener {
    private Ball ball;
    private Paddle paddle;
    Brick[][] bricks;
    
    int rows = 5, cols = 10;
    private int lives = 3;
    private int totalBricks;
    
    private int score = 0;
    
    private Timer timer;
    private final int DELAY = 1000 / 60;

    public GamePanel() {
        ball = new Ball();
        paddle = new Paddle();
        
        // Initialize bricks
        bricks = new Brick[rows][cols];
        int brickWidth = 70, brickHeight = 20;
        totalBricks = rows * cols;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                bricks[row][col] = new Brick(
                		20 + col * (brickWidth + 5), 
                		50 + row	 * (brickHeight + 5), 
                		brickWidth,
                		brickHeight);
            }
        }
        	
        setFocusable(true);
        setBackground(Color.BLACK);
        
        addKeyListener(this);

        // Start game loop
        timer = new Timer(DELAY, this);
        timer.start();
        
        requestFocusInWindow();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ball.draw(g);
        paddle.draw(g);
        
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 650, 35);
        
       
        
     // Draw bricks
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                bricks[row][col].draw(g);
            }
        }
        
        if (totalBricks == 0) { // If player wins
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("YOU WIN!", 300, 300);
        }
        if (lives == 0) {  //I game is over
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.setColor(Color.RED);
            g.drawString("GAME OVER!", 300, 300);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ball.move(paddle); 
       
        // Check brick collisions
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
            	
                if (bricks[row][col].checkCollision(ball)) {
                	ball.reverseY(); 
                    score += 10;
                    totalBricks--;
                    ball.increaseSpeed();
                }
            }
        }
        
        
        if (ball.getY() > 600) { // If ball falls below the screen
            lives--;
            if (lives == 0) {
                timer.stop(); 
            } else {
                ball.resetBall(); 
            }
        }

        
        if (totalBricks == 0) {
            timer.stop(); 
        }
        repaint(); 
        
    }

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		 paddle.move(e);
		 repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {}
}