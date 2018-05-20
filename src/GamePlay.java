import javax.swing.Timer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePlay extends JPanel implements KeyListener, ActionListener,MouseListener {
	public Timer timer;
	public Apple apple;
	public Snake snake;
	public GameState nS = GameState.START;
	public boolean snapple = false;
	public SnakeDirection currentDirection = SnakeDirection.RIGHT;
	int score = 0;
	
	public GamePlay() {
		System.out.println("Start");
		setPreferredSize(new Dimension(500,500));
		timer = new Timer(130, this);
		timer.start();
		apple = new Apple(500,500);
		snake = new Snake(500,500);
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.setFocusable(true);
		this.setFocusTraversalKeysEnabled(false);
		this.setBackground(new Color(237, 42, 143));
	}

	public void paint(Graphics g) {
		drawBackground(g);
		if (nS == GameState.START) {
			drawSTARTScreen(g);
		}
		if (nS == GameState.PLAY) {
			drawPLAYScreen(g);
		}
		if (nS == GameState.GAMEOVER) {
			drawGAMEOVERScreen(g);
		}
		
	}
	public void drawBackground(Graphics g) {
		g.setColor(Color.RED);
		g.drawRect(0, 0, 500, 500);
	}
	public void drawSTARTScreen(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		try {
			BufferedImage logo = ImageIO.read(new File("./res/logo.png"));
			int x = 500/2 - logo.getWidth()/2;
			int y = 500/2 - logo.getHeight()/2;
			g.drawImage(logo,x,y,this);
			BufferedImage playLogo = ImageIO.read(new File("./res/play_button.png"));
			int xint = 500/2 - playLogo.getWidth()/2;
			int yint = 500/2 - playLogo.getHeight()/2+50;
			g.drawImage(playLogo,xint,yint,this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	public void drawGAMEOVERScreen(Graphics g) {
		try {
			BufferedImage logo = ImageIO.read(new File("./res/game_over.png"));
			int x = 500/2 - logo.getWidth()/2;
			int y = 500/2 - logo.getHeight()/2;
			g.drawImage(logo,x,y,this);
			BufferedImage playLogo = ImageIO.read(new File("./res/replay_button.png"));
			int xint = 500/2 - playLogo.getWidth()/2;
			int yint = 500/2 - playLogo.getHeight()/2+50;
			g.drawImage(playLogo,xint,yint,this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		case KeyEvent.VK_SPACE:
			nS = GameState.PLAY;
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
public void resetGame() {
	score = 0;
	snake = new Snake(500,500);
}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(nS == GameState.START || nS == GameState.GAMEOVER) {
			resetGame();
			nS = GameState.PLAY;
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
