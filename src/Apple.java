import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

public class Apple {
	private int height = 20;
	private int width = 20;
	private int gameWidth;
	private int gameHeight;
	public Point p;
	public Apple(int gh, int gw) {
		gameWidth = gw;
		gameHeight = gh;
		
		p = new Point(100,100);
	}
	public void newLocation() {
		int x = new Random().nextInt(gameWidth - width);
		int y = new Random().nextInt(gameHeight - height - 22);
		int xPos = (int) Math.ceil(x/width)*width;
		int yPos = (int) Math.ceil(y/width)*width;
		System.out.println(xPos);
		System.out.println(yPos);
		p = new Point(xPos,yPos);
	}
	public void repaint(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(p.x, p.y, width, height);
	}
}
