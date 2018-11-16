package game;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Player {

	// Constants are Here
	final int JUMPSPEED = -20;
	final int MOVESPEED = 5;

	private int posX = 66;
	private int posY = 100;
	public int health = 100;
	private boolean jumped = false;
	private boolean movingLeft = false;
	private boolean movingRight = false;
	private boolean ducked = false;
	private boolean readyToFire = true;
	public static boolean playerDead = false;

	private int velX = 0;
	private int velY = 0;
	public static Rectangle rect = new Rectangle(0, 0, 0, 0);
	public static Rectangle rect2 = new Rectangle(0, 0, 0, 0);
	public static Rectangle yellowRed = new Rectangle(0, 0, 0, 0);
	
	public static Rectangle footleft = new Rectangle(0,0,0,0);
	public static Rectangle footright = new Rectangle(0,0,0,0);
	
	private Background bg1 = Main.getBg1();
	private Background bg2 = Main.getBg2();

	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

	public void update() {

		
//		Moves Character or Scrolls Background accordingly.
		if (velX < 0) {
			posX += velX;
		}
		if (velX == 0 || velX < 0) {
			bg1.setVelX(0);
			bg2.setVelX(0);

		}
		if (posX <= 150 && velX > 0) {
			posX += velX;
		}
		if (velX > 0 && posX > 150) {
			bg1.setVelX(-MOVESPEED / 5);
			bg2.setVelX(-MOVESPEED / 5);
		}

		// Updates Y Position
		posY += velY;

		// Handles Jumping

			velY += 1;

		if (velY > 3){
			jumped = true;
		}

		// Prevents going beyond X coordinate of 0
		if (playerDead) {
			posY += 400;
		}
		if (posX + velX <= 60) {
			posX = 61;
		}
		
		if (ducked) {
			rect.setRect(posX + 28, posY + 20, 68, 43);
		} else {
			rect.setRect(posX + 28, posY, 68, 63);
		}

		rect2.setRect(posX + 28, posY + 63, 68, 63);
		yellowRed.setRect(posX + 28, posY, 68, 126);

		footleft.setRect(posX - 50, posY + 20, 50, 15);
		footright.setRect(posX, posY + 20, 50, 15);


	}

	public void moveRight() {
		if (ducked == false) {
			velX = MOVESPEED;
		}
	}

	public void moveLeft() {
		if (ducked == false) {
			velX = -MOVESPEED;
		}
	}

	public void stopRight() {
		setMovingRight(false);
		stop();
	}

	public void stopLeft() {
		setMovingLeft(false);
		stop();
	}

	private void stop() {
		if (isMovingRight() == false && isMovingLeft() == false) {
			velX = 0;
		}

		if (isMovingRight() == false && isMovingLeft() == true) {
			moveLeft();
		}

		if (isMovingRight() == true && isMovingLeft() == false) {
			moveRight();
		}

	}

	public void jump() {
		if (jumped == false) {
			velY = JUMPSPEED;
			jumped = true;
		}

	}

	public void shoot() {
		if (readyToFire) {
			Projectile p = new Projectile(posX + 10, posY);
			projectiles.add(p);
		}
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public boolean isJumped() {
		return jumped;
	}

	public int getVelX() {
		return velX;
	}

	public int getVelY() {
		return velY;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public void setJumped(boolean jumped) {
		this.jumped = jumped;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}

	public boolean isDucked() {
		return ducked;
	}

	public void setDucked(boolean ducked) {
		this.ducked = ducked;
	}

	public boolean isMovingRight() {
		return movingRight;
	}

	public void setMovingRight(boolean movingRight) {
		this.movingRight = movingRight;
	}

	public boolean isMovingLeft() {
		return movingLeft;
	}

	public void setMovingLeft(boolean movingLeft) {
		this.movingLeft = movingLeft;
	}

	public ArrayList getProjectiles() {
		return projectiles;
	}

	public boolean isReadyToFire() {
		return readyToFire;
	}

	public void setReadyToFire(boolean readyToFire) {
		this.readyToFire = readyToFire;
	}

}
