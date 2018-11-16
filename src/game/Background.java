package game;

public class Background {
	
	private int bgX, bgY, velX;
	
	public Background(int x, int y){
		bgX = x;
		bgY = y;
		velX = 0;
	}
	
	public void update() {
		bgX += velX;

		if (bgX <= -2160){
			bgX += 4320;
		}
	}

	public int getBgX() {
		return bgX;
	}

	public int getBgY() {
		return bgY;
	}

	public int getVelX() {
		return velX;
	}

	public void setBgX(int bgX) {
		this.bgX = bgX;
	}

	public void setBgY(int bgY) {
		this.bgY = bgY;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	
	
	
}

