package bubble;
import java.awt.*;
import java.lang.instrument.Instrumentation;
import java.util.ArrayList;

public class Enemy implements Runnable{
	public static ArrayList<Enemy> enemys;
	private static double x;
	private static double y;
	private static int r;
	private static Color color;
	private static int type;
	private static int rank;
	private static double speed;
	private static double dx;
	private static double dy;
	private static double health;
	private static int lifecycle;
	double angle = Math.toRadians(Math.random()*360);
	public static Player player;
	
	public Enemy(int type,int rank) {
		this.rank = rank;
		this.type = type;
		switch(type) {
		case (1): 
			color = Color.GREEN;
			switch(rank) {
			case (1):
				x = Math.random() * GamePanel.WIDTH;
				y = 0;
				r = 30;
				health = 1;
				speed = Math.random() * 10;
				lifecycle = (int)Math.random()*60;
				dx = Math.sin(angle) * speed;
				dy = Math.cos(angle) * speed;
			case (2):
				x = Math.random() * GamePanel.WIDTH;
				y = 0;
				r = 15;
				health = 2;
				speed = Math.random() * 15;
				lifecycle = (int)Math.random()*80;
				dx = Math.sin(angle) * speed;
				dy = Math.cos(angle) * speed;
			}
		case (2):
				x = Math.random() * GamePanel.WIDTH;
				y = 0;
				r = 7;
				health = 3;
				speed = Math.random() * 25;
				lifecycle = (int)Math.random()*100;
				dx = Math.sin(angle) * speed;
				dy = Math.cos(angle) * speed;
		}
	}
	
	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public int getR() {
		return r;
	}
	public void setR(int Rad) {
		this.r = Rad;
	}
	
	
	public boolean remove() {
		if(health <= 0 || lifecycle <= 0) {
			return true;
		}
		return false;
	}
	
	public void hit() {
		health--;
		GamePanel.player.addScore();
	}
	
	public void update() {
		x += dx;
		y += dy;
		
		if(x < 0 && dx < 0) 
			dx = -dx;
		if(x > GamePanel.WIDTH && dx >0) 
			dx = -dx;
		if(y < 0 && dy <0) 
			dy = -dy;
		if(y > GamePanel.HEIGHT && dy > 0) 
			dy = -dy;
	}
	public void draw(Graphics2D g) {
		g.setColor(color);
		g.fillOval((int)(x - r),(int) (y - r), 2 * r, 2 * r);
		g.setStroke(new BasicStroke(3));
		g.setColor(color.darker());
		g.drawOval((int)(x - r),(int) (y - r), 2 * r, 2 * r);
	}

	@Override
	public void run() {
		enemys = new ArrayList<Enemy>();
		for(int i = 0;i<enemys.size();i++) {
			enemys.get(i).update();
		}
	}
}
