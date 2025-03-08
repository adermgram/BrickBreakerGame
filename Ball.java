import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


class Ball {
    private int x = 400, y = 300, diameter = 20;
    private int dx = 2, dy = 2;
    private double speedMultiplier = 1.0;

    public void move(Paddle paddle) {
    	x += dx * speedMultiplier;
    	y += dy * speedMultiplier;

        // Bounce off walls
        if (x <= 0 || x >= 780) dx = -dx;
        if (y <= 0) dy = -dy;
        
        if (y + diameter >= paddle.getY() && x + diameter >= paddle.getX() && x <= paddle.getX() + paddle.getWidth()) {
            dy = -dy; // Reverse ball direction
            
            // Adjust ball's angle based on where it hit the paddle
            int hitPosition = x - (paddle.getX() + paddle.getWidth() / 2);
            dx = hitPosition / 10; // Small angle variation
        }
        
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x, y, diameter, diameter);
    }
    
    public void resetBall() {
        x = 400;
        y = 300;
        dx = 2;
        dy = 2;
    }
    
    public void reverseY() {
        dy = -dy;
    }
    
    public Rectangle getBounds() {
        return new Rectangle(x, y, diameter, diameter);
    }
    
    public void increaseSpeed() {
        if (speedMultiplier < 2.0) {
            speedMultiplier += 0.05;
        }
    }

	public int getY() {
		return y;
	}


}

