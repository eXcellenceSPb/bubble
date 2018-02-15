package bubble;
import java.awt.*;

public class Bullet {
	private double x;
	private double y;
	private int r;
	private double speed;
	private Color color;
	
	public Bullet() {
		x = GamePanel.player.getX();
		y = GamePanel.player.getY();
		r = 2;
		color = Color.YELLOW;
		speed = 10;
	}
	
	public boolean remove() {
		if(y < 0 && y > GamePanel.HEIGHT && x < 0 && x > GamePanel.WIDTH) {
			return true;
		}
		return false;
	}
	
	public void update() {
		y -= speed;
	}
	public void draw(Graphics2D g) {
		g.setColor(color);
		g.fillOval((int) x,(int) y, r, 2 * r);
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
}
