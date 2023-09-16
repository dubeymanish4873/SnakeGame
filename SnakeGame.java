package snake_Game;
import javax.swing.*;
public class SnakeGame extends JFrame {
	SnakeGame(){
		super("Snake Game");
		add(new Board());
		pack(); /// For refresh
		//setLocation(300,400); --> To put screen at any Location
		setLocationRelativeTo(null); //To put Frame at the center of the screen
		setResizable(false); // So that no one can resize the frame;
		setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new SnakeGame();
	}

}
