import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class Snake {
	private ArrayList<Point> body = new ArrayList<Point>();
	private int height = 20;
	private int width = 20;
	private int gameWidth;
	private int gameHeight;
	public Snake(int gh, int gw) {
		gameWidth = gw;
		gameHeight = gh;
		Point head = new Point(100,100);
		body.add(head);
		
	}
	public void repaint(Graphics g) {
		g.setColor(Color.black);
		for(int counter = 0; counter < body.size(); counter++){
			Point p = body.get(counter);
			g.fillRect(p.x, p.y, width, height);
		}
	}
}
