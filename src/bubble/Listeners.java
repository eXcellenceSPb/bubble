package bubble;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Listeners implements KeyListener,MouseListener,MouseMotionListener{
	
	

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_W) {
			Player.up = true;
		}
		if (key == KeyEvent.VK_S) {
			Player.down = true;
		}
		if(key == KeyEvent.VK_A) {
			Player.left = true;
		}
		if(key == KeyEvent.VK_D) {
			Player.right = true;
		}
		if(key == KeyEvent.VK_SPACE) {
			Player.isFiring = true;
		}
		if(key == KeyEvent.VK_ESCAPE) {
			GamePanel.states = GamePanel.STATES.MENU;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_W) {
			Player.up = false;
		}
		if (key == KeyEvent.VK_S) {
			Player.down = false;
		}
		if(key == KeyEvent.VK_A) {
			Player.left = false;
		}
		if(key == KeyEvent.VK_D) {
			Player.right = false;
		}
		if(key == KeyEvent.VK_SPACE) {
			Player.isFiring = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {}

	@Override
	public void mouseDragged(MouseEvent e) {
		GamePanel.mouseX = e.getX();
		GamePanel.mouseY = e.getY();
		GamePanel.player.setX();
		GamePanel.player.setY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		GamePanel.mouseX = e.getX();
		GamePanel.mouseY = e.getY();
		GamePanel.player.setX();
		GamePanel.player.setY();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		GamePanel.player.setX();
		GamePanel.player.setY();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		GamePanel.player.setX();
		GamePanel.player.setY();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			GamePanel.player.isFiring = true;
			GamePanel.leftMouse = true;
			GamePanel.player.setX();
			GamePanel.player.setY();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			GamePanel.player.isFiring = false;
			GamePanel.leftMouse = false;
			GamePanel.player.setX();
			GamePanel.player.setY();
		}
	}
	
}
