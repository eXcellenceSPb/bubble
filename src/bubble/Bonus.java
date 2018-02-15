package bubble;
import java.awt.*;

public class Bonus {
	private double x;
	private double y;
	private int r;
	private double speed;
	private double dx;
	private double dy;
	private int health;
	private Color color;
	
	public Bonus() {
		color = Color.MAGENTA;
		speed = Math.random()*7;
		x = Math.random() * GamePanel.WIDTH;
		y = 0;
		health = 1;
		r = 30;
		double angle = Math.toRadians(Math.random()*360);
		dx = Math.sin(angle) * speed;
		dy = Math.cos(angle) * speed;
	}

	public void hit() {
		health--;
	}
	
	public boolean remove() {
		if(health <= 0) {
			return true;
		}
		return false;
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
}
