package game;

import java.awt.Rectangle;

public class Projectile {

	private int x, y, speedX, speedY;
	private boolean visible;
	private int gravity = 1;
	
	private Rectangle r;
	
	public Projectile(int startX, int startY){
		x = startX + 35;
		y = startY;
		speedX = 7;
		speedY = -20;
		visible = true;
		
		r = new Rectangle(0, 0, 0, 0);
	}
	
	public void update(){
		x += speedX;
		y += speedY / 10;
		r.setBounds(x + 8, y + 8, 20, 20);
		if (x > 800){
			visible = false;
			r = null;
		}
		if (x < 800){
			checkCollision();
		}
		speedY += gravity;
	}

	private void checkCollision() {
		
		enemyHealth(Main.hb1);
		enemyHealth(Main.hb2);
		enemyHealth(Main.hb3);
		enemyHealth(Main.hb4);
		enemyHealth(Main.hb5);
		enemyHealth(Main.hb6);
		enemyHealth(Main.hb7);
		enemyHealth(Main.hb8);
		enemyHealth(Main.hb9);
		
	}
	public void enemyHealth(Helicopter hb) {
		if(r.intersects(hb.r)){
			visible = false;
		
			if (hb.health > 0) {
				hb.health -= 1;
			}
			if (hb.health <= 0) {
				hb.setKilled(true);
				// Main.hb.setCenterX(-100);
				Main.score += 5;
			}
		}
	}


	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getSpeedX() {
		return speedX;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	
}
