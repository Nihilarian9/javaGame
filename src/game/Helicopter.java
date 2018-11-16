package game;

import java.awt.Rectangle;

public class Helicopter {

	private int power;
	private int posX;
	private int velX = 0;
	private int posY;
	private int missleVelX = 2;
	private int misslePosX;
	private int misslePosY;
	private int missleVelCount = 0;
	
	public int health = 1;
	private Background bg = Main.getBg1();
	private Player player = Main.getPlayer();
	private boolean killed;
	private boolean grounded;
	private boolean missleFired = false;
//	private boolean missleLaunch = false;
	private int missleFireTimer;


	public Rectangle r = new Rectangle(0, 0, 0, 0);
	public Rectangle missle = new Rectangle(0, 0, 0, 0);
//	public Rectangle rect = new Rectangle(0, 0, 0, 0);

	

//	private int movementSpeed;
	
	public Helicopter(int X, int Y) {
		posX = X;
		posY = Y;
	}

	public void update() {
		if (posX < player.getPosX() + 1000 && posX > player.getPosX() + 400) {
			velX = bg.getVelX() * 5 - 2;
			
		} else {
			velX = bg.getVelX() * 5;
		}
		
		posX += velX;		
		r.setBounds(posX - 45, posY - 45, 75, 24);
		
		if (posX < player.getPosX() + 500 && !missleFired && missleFireTimer < 1) {
			missleFired = true;
			misslePosX = posX - 20;
			misslePosY = posY - 30;
		}
		missleFiring();
		
		missleFireTimer--;
				
		checkCollision();
	}
	
	public void crashing() {
		if(!grounded) {
			posY += 1;
		}
		
		missleFiring();
		
		r.setBounds(posX - 45, posY - 45, 75, 24);
		posX += bg.getVelX() * 5;
	}
	
	public void missleFiring() {
		if (missleFired) {
			missleVelCount++;
			if (missleVelCount > 12) {
				missleVelX += 2;
				missleVelCount = 0;
			}
			misslePosX -= missleVelX - bg.getVelX() * 5;
			

//			missle collision
			if (missle.intersects(player.rect) || missle.intersects(player.rect2)) {
				player.health -= 2;
				missleFired = false;
				missleFireTimer = 100;
				missleVelX = 0;
				misslePosY = 500;
			}
			missle.setRect(misslePosX, misslePosY, 10, 5);
			
		}
	}


	private void checkCollision() {
		if (killed == false && r.intersects(player.rect) || r.intersects(player.rect2)) {
			player.health -= 5;
			Main.score += 5;
			killed = true;
		}
	}

	public int getPower() {
		return power;
	}

	public int getVelX() {
		return velX;
	}

	public int getPosX() {
		return posX;
	}
	
	public int getPosY() {
		return posY;
	}
	
	public boolean isKilled() {
		return killed;
	}

	public void setKilled(boolean killed) {
		this.killed = killed;
	}
	public boolean isGrounded() {
		return grounded;
	}

	public void setGrounded(boolean grounded) {
		this.grounded = grounded;
	}


	public Background getBg() {
		return bg;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public void setBg(Background bg) {
		this.bg = bg;
	}
	public void setHealth(int health) {
		this.health = health;
	}

	public int getMisslePosX() {
		return misslePosX;
	}

	public int getMisslePosY() {
		return misslePosY;
	}

	public void setMisslePosX(int misslePosX) {
		this.misslePosX = misslePosX;
	}

	public void setMisslePosY(int misslePosY) {
		this.misslePosY = misslePosY;
	}


}
