import javax.swing.Timer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class GamePlay extends JPanel implements ActionListener {
	public Timer timer;
	public Apple apple;
	public GameState nS = GameState.PLAY;
	public boolean snapple = false;

	public GamePlay() {
		System.out.println("Start");
		timer = new Timer(100, this);
		timer.start();
		apple = new Apple(500,500);
	}

	public void paint(Graphics g) {
		drawBackground(g);
		if (nS == GameState.START) {
			drawSTARTScreen();
		}
		if (nS == GameState.PLAY) {
			drawPLAYScreen(g);
		}
		if (nS == GameState.GAMEOVER) {
			drawGAMEOVERScreen();
		}
		
	}
	public void drawBackground(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, 500, 500);
	}
	public void drawSTARTScreen() {

	}
	public void drawPLAYScreen(Graphics g) {
		apple.repaint(g);
		if (snapple) { 
			apple.newLocation();
		}
		
	}
	public void drawGAMEOVERScreen() {
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == timer) {
			repaint();
		}
	}

}
