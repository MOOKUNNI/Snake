import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Main {
		
	public static void main(String[] args) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int gameHeight = 500;
		int gameWidth = 500;
		int frameHeight = (int) dimension.getHeight();
		int frameWidth = (int) dimension.getWidth();
		int y = frameHeight/2 - gameHeight/2;
		int x = frameWidth/2 - gameWidth/2;
		JFrame jf = new JFrame();
		GamePlay play = new GamePlay();
		jf.add(play);
		jf.setBounds(x,y,gameWidth,gameHeight);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setBackground(Color.PINK);
		jf.setVisible(true);

	}

}
