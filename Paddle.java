import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

class Paddle {
    private int x = 350, y = 540, width = 100, height = 10;

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);
    }
    public void move(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_LEFT && x > 0) {
            x -= 20; // Move left
        } else if (keyCode == KeyEvent.VK_RIGHT && x < 700) {
            x += 20; // Move right
        }
    }
    
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
}