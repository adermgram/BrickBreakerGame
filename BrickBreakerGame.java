import javax.swing.JFrame;

@SuppressWarnings("serial")
public class BrickBreakerGame extends JFrame  {
	

	BrickBreakerGame(){
		
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		GamePanel gamePanel = new GamePanel();
	    this.add(gamePanel);
	
	    this.setVisible(true);
	}
	
	

}
