import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class Snake {
	public ArrayList<Point> body = new ArrayList<Point>();
	private int height = 20;
	private int width = 20;
	private int gameWidth;
	private int gameHeight;
	public boolean dontShrink = false;
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
			if(counter > 0) {
				g.setColor(Color.PINK);
			}
			g.fillRect(p.x, p.y, width, height);
		}
	}
	public void move(SnakeDirection sd) {
		Point head = body.get(0);
		Point newHead = new Point(head.x, head.y);
		
		if(sd == SnakeDirection.UP) {
			newHead.y -= height;
		}
		if(sd == SnakeDirection.DOWN) {
			newHead.y += width;
		}
		if(sd == SnakeDirection.RIGHT) {
			newHead.x += width;
		}
		if(sd == SnakeDirection.LEFT) {
			newHead.x -= height;
		}
		body.add(0, newHead);
		if(dontShrink) {
			dontShrink = false;
		}else{
			body.remove(body.size() -1);
		}
	}
	
}
