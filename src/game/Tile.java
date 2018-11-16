package game;

import java.awt.Image;
import java.awt.Rectangle;

public class Tile {

	private int tileX, tileY, velX, type;
	public Image tileImage;

	private Player player = Main.getPlayer();
	private Background bg = Main.getBg1();
		
	private Rectangle r;

	public Tile(int x, int y, int typeInt) {
		tileX = x * 40;
		tileY = y * 40;

		type = typeInt;

		r = new Rectangle();

		if (type == 1) {
			tileImage = Main.tileroad;
		} else if (type == 2) {
			tileImage = Main.tilehouse;
		} else if (type == 3) {
			tileImage = Main.tiletree;
		} else if (type == 4) {
			tileImage = Main.tiletreekill;
		} else if (type == 5) {
			tileImage = Main.tilegrassRight;
		} else {
			type = 0;
		}

	}

		public void update() {
			velX = bg.getVelX() * 5;
			tileX += velX;
			r.setBounds(tileX, tileY, 40, 40);
	
			if (r.intersects(Player.yellowRed) && type != 0) {
				checkVerticalCollision(Player.rect, Player.rect2);
				// checkSideCollision(Player.footleft, Player.footright);
			}
			checkEnemyCrash(Main.hb1);
			checkEnemyCrash(Main.hb2);
			checkEnemyCrash(Main.hb3);
			checkEnemyCrash(Main.hb4);
			checkEnemyCrash(Main.hb5);
			checkEnemyCrash(Main.hb6);
			checkEnemyCrash(Main.hb7);
			checkEnemyCrash(Main.hb8);
			checkEnemyCrash(Main.hb9);

		}
		public void checkEnemyCrash(Helicopter hb1) {
			if (r.intersects(hb1.r) && type == 1) {
				hb1.setGrounded(true);
			} else if (r.intersects(hb1.r) && type == 2) {
				tileImage = Main.tilehousekill;
			}
		}

	public int getTileX() {
		return tileX;
	}

	public void setTileX(int tileX) {
		this.tileX = tileX;
	}

	public int getTileY() {
		return tileY;
	}

	public void setTileY(int tileY) {
		this.tileY = tileY;
	}

	public Image getTileImage() {
		return tileImage;
	}

	public void setTileImage(Image tileImage) {
		this.tileImage = tileImage;
	}


	public void checkVerticalCollision(Rectangle rtop, Rectangle rbot) {
		if (rtop.intersects(r)) {
			
		}

		if (rbot.intersects(r) && type == 1) {
			player.setJumped(false);
			player.setVelY(0);
			player.setPosY(tileY-126);
		}
		
		if(type == 2) {
			tileImage = Main.tilehousekill;
		} else if(type == 3) {
			tileImage = Main.tiletreekill;
		}
	}
/*
	public void checkSideCollision(Rectangle leftfoot, Rectangle rightfoot) {
		if (type != 5 && type != 2 && type != 0){
	
			if (leftfoot.intersects(r)) {
				player.setPosX(tileX + 85);
				player.setVelX(0);
			}
			
			if (rightfoot.intersects(r)) {
				player.setPosX(tileX - 45);
				player.setVelX(0);
			}
		}
	}
*/

}