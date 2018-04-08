import javax.swing.Timer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class GamePlay extends JPanel implements KeyListener, ActionListener {
	public Timer timer;
	public Apple apple;
	public Snake snake;
	public GameState nS = GameState.PLAY;
	public boolean snapple = false;
	public SnakeDirection currentDirection = SnakeDirection.RIGHT;
	
	public GamePlay() {
		System.out.println("Start");
		timer = new Timer(100, this);
		timer.start();
		apple = new Apple(500,500);
		snake = new Snake(500,500);
		this.addKeyListener(this);
		this.setFocusable(true);
		this.setFocusTraversalKeysEnabled(false);
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
		snake.repaint(g);
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

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_UP:
			currentDirection = SnakeDirection.UP;
			break;
		case KeyEvent.VK_DOWN:
			currentDirection = SnakeDirection.DOWN;	
			break;
		case KeyEvent.VK_RIGHT:
			currentDirection = SnakeDirection.RIGHT;
			break;
		case KeyEvent.VK_LEFT:
			currentDirection = SnakeDirection.LEFT;
			break;
		}
		System.out.println(currentDirection);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

}
