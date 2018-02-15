package bubble;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{

	//Поле
	public static final int WIDTH = 600;
	public static final int HEIGHT = 600;
	
	public static int mouseX;
	public static int mouseY;
	public static boolean leftMouse;
	
	private Thread thread;
	
	public static enum STATES {
		MENU,
		PLAY
	}
	public static STATES states = STATES.MENU;
	
	private BufferedImage image;
	private Graphics2D g;
	private static GameBack background;
	public static Player player;
	public static ArrayList<Bullet> bullets;
	public static ArrayList<Enemy> enemys;
	public static ArrayList<Bonus> bonus;
	public static Wave wave;
	public static Menu menu;

	
	private int FPS;
	private long timerFPS;
	private double msToFPS;
	private int sleeptime;
	
	public GamePanel(){
		super();
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		setFocusable(true);
		requestFocus();
		addKeyListener(new Listeners());
		addMouseMotionListener(new Listeners());
		addMouseListener(new Listeners());
	}

	public void start() {
		thread = new Thread(this);
		thread.start();
	}
	
	@Override
	public void run() {
		sleeptime = 0;
		FPS = 30;
		msToFPS = 1000/FPS;
		
		image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		
		leftMouse = false;
		player = new Player();
		background = new GameBack();
		bullets = new ArrayList<Bullet>();
		enemys = new ArrayList<Enemy>();
		bonus = new ArrayList<Bonus>();
		wave = new Wave();
		menu = new Menu();
		

		Toolkit kit = Toolkit.getDefaultToolkit();
		BufferedImage buffered = new BufferedImage(16,16,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g3 = (Graphics2D) buffered.getGraphics();
		g3.setColor(new Color(255,255,255));
		g3.drawOval(0, 0, 4, 4);
		g3.drawLine(2, 0, 2, 4);
		g3.drawLine(0, 2, 4, 2);
		Cursor cursor = kit.createCustomCursor(buffered, new Point(3,3),
				"cursor");
		g3.dispose();
		
		
		while(true) {
			this.setCursor(cursor);
			if(states.equals(STATES.MENU)) {
				
				background.update();
				background.draw(g);
				menu.update();
				menu.draw(g);
				gameDraw();
			}
			if(states.equals(STATES.PLAY)) {
				
				gameUpdate();
				gameRender();
				gameDraw();
			}
			timerFPS = System.nanoTime();

			
			timerFPS = (System.nanoTime() - timerFPS)/1000000;
			if(msToFPS > timerFPS) {
				sleeptime = (int)msToFPS - (int)timerFPS;
			}
			else sleeptime = 1;
			
			try {
				Thread.sleep(sleeptime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			timerFPS = 0;
			sleeptime = 1;
		}
	}
	
	public void gameUpdate() {
		background.update();
		player.update();
		
		if(player.getHealth() <= 0) {
			GamePanel.states = GamePanel.STATES.MENU;
			wave.setWaveNum();
			player.setHealth();
			if(bonus.isEmpty() != true) {
				bonus.clear();
			}
		}
		
		for(int i = 0;i<bullets.size();i++) {
			bullets.get(i).update();
			boolean remove = bullets.get(i).remove();
			if(remove) {
				bullets.remove(i);
				i--;
			}
		}
		
		for(int i = 0;i<enemys.size();i++) {
			enemys.get(i).update();
		}
		for(int i = 0;i<bonus.size();i++) {
			bonus.get(i).update();
		}
		for(int i = 0;i < enemys.size();i++) {
			 Enemy e = enemys.get(i);
			 double ex = e.getX();
			 double ey = e.getY();
			 
			for(int j = 0;j < bullets.size();j++) {
				Bullet b = bullets.get(j);
				double bx = b.getX();
				double by = b.getY();
				
				double dx = ex - bx;
				double dy = ey - by;
				double dist = Math.sqrt(dx * dx + dy * dy);
				if((int)dist <= e.getR() + b.getR()) {
					e.hit();
					bullets.remove(j);
					j--;
					boolean remove = e.remove();
					if(remove) {
						enemys.remove(i);
						i--;
						break;
					}
				}
			}
		}
	
		for(int i = 0;i < enemys.size();i++) {
			Enemy e = enemys.get(i);
			double ex = e.getX();
			double ey = e.getY();
			
			double px = player.getX();
			double py = player.getY();
			double dx = ex - px;
			double dy = ey - py;
			double dist = Math.sqrt(dx * dx + dy* dy);
			if((int)dist <= e.getR() + player.getR()) {
				e.hit();
				player.hit();
				
				boolean remove = e.remove();
				if(remove) {
					enemys.remove(i);
					i--;
				}
			}
		}
		for(int i = 0;i < bonus.size();i++) {
			Bonus e = bonus.get(i);
			double ex = e.getX();
			double ey = e.getY();
			
			double px = player.getX();
			double py = player.getY();
			double dx = ex - px;
			double dy = ey - py;
			double dist = Math.sqrt(dx * dx + dy* dy);
			if((int)dist <= e.getR() + player.getR()) {
				e.hit();
				player.setR((int)Math.random()*30);
				player.addScore();
				System.out.println((int)(player.getHealth()));
				player.bonus();
				boolean remove = e.remove();
				if(remove) {
					bonus.remove(i);
					i--;
				}
			}
		}	
		wave.update();
		
	}
	
	public void gameRender() {
		background.draw(g);
		player.draw(g);
		
		for(int i = 0;i<bullets.size();i++) {
			bullets.get(i).draw(g);
		}
		
		for(int index = 0;index<enemys.size();index++) {
			enemys.get(index).draw(g);
		}
		//wave
		if(wave.showWave()) {
		wave.draw(g);
		}
		for(int i = 0;i<bonus.size();i++) {
			bonus.get(i).draw(g);
		}
		
		
	}
	private void gameDraw() {
		Graphics g2 = this.getGraphics();
		g2.drawImage(image,0,0,null);
		g2.dispose();
		
		
		g.setFont(new Font("Consolas",Font.ROMAN_BASELINE,40));
		g.drawString(String.valueOf(player.getScore()), (int)(GamePanel.WIDTH/2),
				(int)(GamePanel.HEIGHT/2));
	}
}
