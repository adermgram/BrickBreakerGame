import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

class Brick {
    private int x, y, width, height;
    private boolean isVisible;

    public Brick(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.isVisible = true;
    }

    public void draw(Graphics g) {
        if (isVisible) {
            g.setColor(Color.BLUE);
            g.fillRect(x, y, width, height);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, width, height);
        }
    }

    // Check if ball hits the brick
    public boolean checkCollision(Ball ball) {
        if (isVisible && new Rectangle(x, y, width, height).intersects(ball.getBounds())) {
            isVisible = false; // Hide the brick after collision
            return true;
        }
        return false;
    }

    public boolean isVisible() {
        return isVisible;
    }
}
