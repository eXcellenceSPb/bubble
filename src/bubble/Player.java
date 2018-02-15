package bubble;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Player {
	private double x;
	private double y;
	private int r;
	
	private int speed;
	private double dx;
	private double dy;
	private double health;
	
	private Color color1;
	private Color color2;
	
	public static boolean up;
	public static boolean down;
	public static boolean left;
	public static boolean right;
	public static boolean isFiring;
	public static int score;
	
	public Player() {
		score = 0;
		x = GamePanel.WIDTH/2;
		y = GamePanel.HEIGHT/2;
		health = 3;
		r = 7;
		speed = 5;
		dx = 0;
		dy = 0;
		color1 = Color.WHITE;
		up = false;
		down = false;
		left = false;
		right = false;
		isFiring = false;
	}
	
	public int getScore() {
		return score;
	}
	
	public void addScore() {
		score++;
	}
	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	
	public void setHealth() {
		health = 1;
	}
	
	public void setX() {
		x = GamePanel.mouseX;
	}
	public void setY() {
		y = GamePanel.mouseY;
	}
	
	public int getR() {
		return r;
	}
	public void setR(int rad) {
		this.r = rad;
	}
	
	public void hit() {
		health--;
	}
	public void bonus() {
		health++;
	}
	public double getHealth() {
		return health;
	}
	
	public void update() {
		
		if(up && y > r) {
			dy = -speed;
		}
		if(down && y < GamePanel.HEIGHT - r) {
			dy = speed;
		}
		if(left && x >r) {
			dx = -speed;
		}
		if(right && x < GamePanel.WIDTH - r) {
			dx = speed;
		}
		if(up && left || up & right || down && left || down && right) {
			
			double angle = Math.toRadians(45);
			dy = dy * Math.sin(angle);
			dx = dx * Math.cos(angle);
		}
		
		y += dy;
		x += dx;
		
		dy = 0;
		dx = 0;
		
		if(isFiring) {
			GamePanel.bullets.add(new Bullet());
		}
		
	}
	public void draw(Graphics2D g) {
		g.setColor(color1);
		g.fillOval((int)(x - r),(int)(y - r),2 * r, 2 * r);
		g.setStroke(new BasicStroke(3));
		g.setColor(color1.darker());
		g.drawOval((int)(x - r),(int)(y - r),2 * r, 2 * r);
	}
}
