import javax.swing.Timer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
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
	int score = 0;
	
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

		snake.move(currentDirection);	
		
		if (snakeEatsApple()) { 
			snake.dontShrink = true;
			apple.newLocation();
			score = score + 1;
		}
		printScore(g);
		Point head = snake.body.get(0);
		if (head.y < 0 || head.y > 500 - 22) {
			System.out.println("dead");
			nS = GameState.GAMEOVER;
		}			
		
		if (head.x < 0 || head.x > 500) {
			nS = GameState.GAMEOVER;

		}
		headEatsBody();
		
		
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
		}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	public boolean snakeEatsApple()  {
		Point head = snake.body.get(0);
		if (apple.p.x == head.x && apple.p.y == head.y) {
			return true;
		}else{
			return false;
		}
	
	}
	public void printScore(Graphics g) {
		g.setFont(new Font("Arial", Font.PLAIN, 30));
		g.drawString(Integer.toString(score), 500 - 50, 50);
	}
	public void headEatsBody() {
		Point head= snake.body.get(0);
		for(int counter = 1; counter < snake.body.size(); counter++){
			Point currentBody = snake.body.get(counter);
		
			if (head.x == currentBody.x && head.y == currentBody.y) {
				nS = GameState.GAMEOVER;
			}
		}
	}

}
