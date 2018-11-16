package game;

import java.awt.Rectangle;

public class Tank {
	
	private int posX;
	private int posY;
	private int velX;
	private int velY = 0;
	private int shotVelX;
	private int shotVelY;
	private int shotPosX;
	private int shotPosY;
	public int explosionX;
	public int explosionY;
	
	private int gravity = 1;
	public int health = 1;
	public int gravCount = 0;
	private boolean tankShooting = false;
	private boolean theBang = false;
	public boolean deaded = false;
	private int tankFireCount = 0;
	
	private Background bg = Main.getBg1();
	private Player player = Main.getPlayer();
	private Rectangle tankShot= new Rectangle(0, 0, 0, 0);
	private Rectangle r = new Rectangle(0, 0, 0, 0);
	
	public Tank(int X, int Y) {
		posX = X;
		posY = Y;
	}
	
	public void update() {
		
		if (posX > player.getPosX() + 500 && posX < player.getPosX() + 1000) {
			velX = bg.getVelX() * 5 - 1;
		} else {
			velX = bg.getVelX() * 5;
		}
		
		r.setRect(posX, posY, 50, 30);
		checkCollision();
		
		shooter();
		posX += velX;
		posY -= velY;
	}
	
	public void dead() {
		velX = bg.getVelX() * 5;
		posX += velX;
		shooter();
	}
	
	private void shooter() {
		
		tankFireCount--;
		
		if (!deaded && !tankShooting && posX <= player.getPosX() + 600 && tankFireCount < 1) {
			
			tankShooting = true;
			shotVelX = 4;
			shotVelY = 5;
			shotPosX = posX;
			shotPosY = posY;
			
		} else if (tankShooting) {
			
			shotPosX -= shotVelX - bg.getVelX() * 5;
			shotPosY -= shotVelY;
			bulletCollsion();
			gravCount++;
			
			if (gravCount > 10) {
				shotVelY -= gravity;
				gravCount = 0;
			}
		}
		tankShot.setRect(shotPosX, shotPosY, 5, 5);
	}
	
	private void checkCollision() {
		if (r.intersects(player.rect) || r.intersects(player.rect2)) {
			deaded = true;
			Main.score += 5;
		}
	}
	private void bulletCollsion() {
		if (tankShot.intersects(player.rect) || tankShot.intersects(player.rect2)) {
			
			player.health -= 1;
			tankShooting = false;
			tankFireCount = 100;
			explosionX = shotPosX;
			explosionY = shotPosY;
			shotPosY = 500;
			shotVelY = 0;
			shotVelX = 0;
			theBang = true;
			
		} else if (shotPosY > 500) {
			
			tankShooting = false;
			tankFireCount = 25;
			shotVelY = 0;
			shotVelX = 0;
		}
	}
	
	
	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public int getVelX() {
		return velX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public Rectangle getTankShot() {
		return tankShot;
	}
}
