package bubble;

import java.awt.*;

public class Wave {
	public static int waveNumber;
	
	private String waveText;
	
	private long waveTimer;
	private long waveDelay;
	private long waveTimerDiff;
	
	private int waveMp;
	
	public Wave() {
		
		waveNumber = 1;
		waveMp = 3;
		waveTimer = 0;
		waveDelay = 3000;
		waveTimerDiff = 0;
		waveText = "NEW WAVE!!!";
	}
	
	public Wave(int waveNumber, int waveMp, int waveTimer, int waveDelay, int waveTimerDiff, String waveText) {
		waveNumber = 1;
		waveMp = 3;
		waveTimer = 0;
		waveDelay = 3000;
		waveTimerDiff = 0;
		waveText = "NEW WAVE!!!";
	}
	
	public void setWaveNum() {
		waveNumber = 1;
	}

	public void createEnemies() {
		int enemyCount = waveNumber * waveMp;
		if(waveNumber != 0) {
			while(enemyCount > 0) {
				int type = 1;
				int rank = 1;
				GamePanel.enemys.add(new Enemy(type,rank));
				GamePanel.bonus.add(new Bonus());
				enemyCount -= type * rank;
			}
		}
		waveNumber++;
	}
	
	public boolean showWave() {
		if(waveTimer != 0) {
			return true;
		}
		else return false;
	}
	
	public void update() {
		
		if(GamePanel.enemys.size() == 0 && waveTimer == 0) {
			waveTimer = System.nanoTime();
			
		}
		if(waveTimer > 0) {
			waveTimerDiff += (System.nanoTime() - waveTimer)/1000000;
			waveTimer = System.nanoTime();
		}
		if(waveTimerDiff > waveDelay) {
			createEnemies();
			waveTimer = 0;
			waveTimerDiff = 0;
		}
		
	}
	public void draw(Graphics2D g) {
		double div = waveDelay / 180;
		double alpha = waveTimerDiff / div;
		alpha = 255 * Math.sin(Math.toRadians(alpha));
		if(alpha < 0) {
			alpha = 0;
		}
		if (alpha > 255) {
			alpha = 0;
		}
		g.setFont(new Font("consolas",Font.PLAIN,20));
		g.setColor(new Color(255,255,255, (int)alpha));
		String s = waveText + " " + waveNumber;
		g.drawString(s,GamePanel.WIDTH / 2,GamePanel.HEIGHT / 2);
	}
}
