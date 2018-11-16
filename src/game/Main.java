package game;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class Main extends Applet implements Runnable, KeyListener {
	
	/* CONCEPT
	 * THE MONSTER OF TINCKLETOWN
	 * 
	 * character selection menu.
	 * characters that are ready:
	 * DEMON
	 * WALKYMAN
	 * PENIS
	 * under construction:
	 * POOMAN 
	 *
	 *
	 * TODO:
	 * change player.update() to if statements to differentiate characters.
	 *
	 * starting background destroyed future building with penis standing in wreckage.
	 *  
	 * 
	 * 
	 * STORY:
	 * working at some futuristic science place with fusion power or something.
	 * penis gets caught in door or something
	 * exposed to futuristic radiation
	 * penis grows massive and starts rampaging
	 * 
	 * game ends with civilization worshiping the monster?
	 * 
	 * way to end animation... if (anim.getImage() == last_picture_in_animation) { object.posX += displacement_in_first_animation; play new animation and object.method(); }
	 * 
	 * ADD CHARACTER CHECKLIST
	 * create image variables
	 * load image files
	 * create animation class
	 * add frames to animation
	 * add character to KEY_EVENT_LISTEN
	 * add character to intro == 10 in paint section
	 * 
	 */

	enum GameState {
		Running, Dead, Intro
	}

	GameState state = GameState.Intro;

	private static Player player;
	public static Helicopter hb1, hb2, hb3, hb4, hb5, hb6, hb7, hb8, hb9;
	public static Tank tankie1, tankie2, tankie3, tankie4;
	public boolean playPenis = true, playDemon = false, playWalker = false, playPoo = false;
	
	public static int score = 0;
	private int introCount = 10;
	
	private Font font20 = new Font(null, Font.PLAIN, 20);
	private Font font30 = new Font(null, Font.PLAIN, 30);
	private Font font40 = new Font(null, Font.BOLD, 40);

	private Image image, currentSprite, character, characterDown, characterJumped,
			penis1, penis2, penis3, penisDown, penisJumped, pew,
			charwalk1, charwalk2, charwalk3, charwalk4, charwalk5, charwalk6, charwalk7, charwalk8, charwalkJumped, charwalkDown,
			demon1, demon2, demon3, demonJumped, demonDown,
			pooman1, pooman2, pooman3, poomanpunch;
			
	private Image heliboy, heliboy2, heliboy3, heliboy4, heliboy5, heliSprite1, heliSprite2,
			heliSprite3, heliSprite4, heliSprite5, heliSprite6, heliSprite7, heliSprite8, heliSprite9,
			heliCrashing1, heliCrashing2, heliCrashing3, heliCrashing4, heliCrashing5, heliCrashing6,
			heliCrashed1, heliCrashed2,
			tank1, deadTank1, tankSprite1, tankSprite2, tankSprite3, tankSprite4;
	
	private Image charSelection, introOne, introTwo, introThree, background, charSelectionDemon, charSelectionWalker, charSelectionPenis, charSelectionPoo;

	public static Image tileroad, tilehousekill, tiletreekill,
			tilegrassRight, tilehouse, tiletree;

	private Graphics second;
	private URL base;
	private static Background bg1, bg2;
	private Animation anim, panim, danim, wanim, pooanim,
	hanim, hcanim, hganim, introExplosion;

	private ArrayList<Tile> tilearray = new ArrayList<Tile>();

	@Override
	public void init() {

		setSize(800, 480);
		setBackground(Color.BLACK);
		setFocusable(true);
		addKeyListener(this);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("The Monster of Tinckle Town");
		try {
			base = getDocumentBase();
		} catch (Exception e) {
			// TODO: handle exception
		}

		// Image Setups
		
// ENVIRONMENT AND STORY GRAPHICS
		
		background = getImage(base, "../res//backgrounds/background.png");
		introOne = getImage(base, "../res/backgrounds/introOne.png");
		introTwo = getImage(base, "../res/backgrounds/introTwo.png");
		introThree = getImage(base, "../res/backgrounds/introThree.png");
		charSelection = getImage(base, "../res/backgrounds/charSelection.png");
		charSelectionWalker = getImage(base, "../res/backgrounds/walker.png");
		charSelectionPenis = getImage(base, "../res/backgrounds/penis.png");
		charSelectionDemon = getImage(base, "../res/backgrounds/demon.png");
		charSelectionPoo = getImage(base, "../res/backgrounds/pooman.png");
		
		tileroad = getImage(base, "../res/tiles/tileroad.png");
		tilehouse = getImage(base, "../res/tiles/tilehouse.png");
		tilehousekill = getImage(base, "../res/tiles/tilehousekill.png");
		tiletree = getImage(base, "../res/tiles/tiletree.png");
		
		tiletreekill = getImage(base, "../res/tiles/tiletreekill.png");
		tilegrassRight = getImage(base, "../res/tiles/tilegrassright.png");

// CHARACTER GRAPHICS		
		
// testing
		penis1 = getImage(base, "../res/char/penis/charwalk.png");
		penis2 = getImage(base, "../res/char/penis/charwalk2.png");
		penis3 = getImage(base, "../res/char/penis/charwalk3.png");
		penisDown = getImage(base, "../res/char/penis/down.png");
		penisJumped = getImage(base, "../res/char/penis/jumped.png");
	
		pooman1 = getImage(base, "../res/char/pooman/pooman1.png");
		pooman2 = getImage(base, "../res/char/pooman/pooman2.png");
		pooman3 = getImage(base, "../res/char/pooman/pooman3.png");
		poomanpunch = getImage(base, "../res/char/pooman/poomanpunch.png");
		
		charwalk1 = getImage(base, "../res/char/newchar/walkanim1.png");
		charwalk2 = getImage(base, "../res/char/newchar/walkanim2.png");
		charwalk3 = getImage(base, "../res/char/newchar/walkanim3.png");
		charwalk4 = getImage(base, "../res/char/newchar/walkanim4.png");
		charwalk5 = getImage(base, "../res/char/newchar/walkanim5.png");
		charwalk6 = getImage(base, "../res/char/newchar/walkanim6.png");
		charwalk7 = getImage(base, "../res/char/newchar/walkanim7.png");
		charwalk8 = getImage(base, "../res/char/newchar/walkanim8.png");
		charwalkJumped = getImage(base, "../res/char/newchar/walkanim1.png");
		charwalkDown = getImage(base, "../res/char/newchar/walkanim1.png");
		
		
		demon1 = getImage(base, "../res/char/demon/demon1.png");
		demon2 = getImage(base, "../res/char/demon/demon2.png");
		demon3 = getImage(base, "../res/char/demon/demon3.png");
		demonJumped = getImage(base, "../res/char/demon/demon2.png");
		demonDown = getImage(base, "../res/char/demon/demon1.png");

		
// MUNITIONS GRAPHICS
		//need different ammo for different characters.
		pew = getImage(base, "../res/pew.png");


// ENEMY GRAPHICS
		heliboy = getImage(base, "../res/heli/heli.png");
		heliboy2 = getImage(base, "../res/heli/heli2.png");
		heliboy3 = getImage(base, "../res/heli/heli3.png");
		heliboy4 = getImage(base, "../res/heli/heli2.png");
		heliboy5 = getImage(base, "../res/heli/heli.png");
		
		heliCrashing1 = getImage(base, "../res/heli/helicrash1.png");
		heliCrashing2 = getImage(base, "../res/heli/helicrash2.png");
		heliCrashing3 = getImage(base, "../res/heli/helicrash3.png");
		heliCrashing4 = getImage(base, "../res/heli/helicrash4.png");
		heliCrashing5 = getImage(base, "../res/heli/helicrash5.png");
		heliCrashing6 = getImage(base, "../res/heli/helicrash6.png");
				
		heliCrashed1 = getImage(base, "../res/heli/helicrashed1.png");
		heliCrashed2 = getImage(base, "../res/heli/helicrashed2.png");
		
		tank1 = getImage(base, "../res/tank/tank1.png");
		deadTank1 = getImage(base, "../res/tank/deadtank1.png");
		
		
// DECLARE ANIMATIONS
		
//		DEMON
		danim = new Animation();
		danim.addFrame(demon1, 50);
		danim.addFrame(demon2, 50);
		danim.addFrame(demon3, 50);
		
//		WALKY LEGS
		wanim = new Animation();
		wanim.addFrame(charwalk1, 50);
		wanim.addFrame(charwalk2, 50);
		wanim.addFrame(charwalk3, 50);
		wanim.addFrame(charwalk4, 50);
		wanim.addFrame(charwalk5, 50);
		wanim.addFrame(charwalk6, 50);
		wanim.addFrame(charwalk7, 50);
		wanim.addFrame(charwalk8, 50);

//		PENIS
		panim = new Animation();
		panim.addFrame(penis1, 50);
		panim.addFrame(penis2, 50);
		panim.addFrame(penis3, 50);
		panim.addFrame(penis2, 50);	
		
//		POOMAN
		pooanim = new Animation();
		pooanim.addFrame(pooman1, 50);
		pooanim.addFrame(pooman2, 50);
		pooanim.addFrame(pooman3, 50);
		
		
//		ENEMIES
		hanim = new Animation();
		hanim.addFrame(heliboy, 100);
		hanim.addFrame(heliboy2, 100);
		
		hcanim = new Animation();
		hcanim.addFrame(heliCrashing1, 100);
		hcanim.addFrame(heliCrashing2, 100);
		hcanim.addFrame(heliCrashing4, 100);
		hcanim.addFrame(heliCrashing5, 100);
		
		hganim = new Animation();
		hganim.addFrame(heliCrashed1, 100);
		hganim.addFrame(heliCrashed2, 100);
		
		introExplosion = new Animation();
//		add explosion frames once created

		
//		OBJECT SPRITES
		heliSprite1 = hanim.getImage();
		heliSprite2 = hanim.getImage();
		heliSprite3 = hanim.getImage();
		heliSprite4 = hanim.getImage();
		heliSprite5 = hanim.getImage();
		heliSprite6 = hanim.getImage();
		heliSprite7 = hanim.getImage();
		heliSprite8 = hanim.getImage();
		heliSprite9 = hanim.getImage();
		
		tankSprite1 = tank1;
		tankSprite2 = tank1;
		tankSprite3 = tank1;
		tankSprite4 = tank1;
		

		
	}

	@Override
	public void start() {
		bg1 = new Background(0, 0);
		bg2 = new Background(2160, 0);
		player = new Player();
		// Initialize Tiles
		try {
			loadMap("../res/map1.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		hb1 = new Helicopter(1100, 350);
		hb2 = new Helicopter(1700, 350);
		hb3 = new Helicopter(2200, 350);
		hb4 = new Helicopter(2500, 350);
		hb5 = new Helicopter(3200, 350);
		hb6 = new Helicopter(3500, 350);
		hb7 = new Helicopter(3800, 350);
		hb8 = new Helicopter(4200, 350);
		hb9 = new Helicopter(5000, 350);
		
		tankie1 = new Tank(1500, 417);
		tankie2 = new Tank(2500, 417);
		tankie3 = new Tank(2900, 417);
		tankie4 = new Tank(4000, 417);

		Thread thread = new Thread(this);
		thread.start();
	}

	private void loadMap(String filename) throws IOException {
		ArrayList lines = new ArrayList();
		int width = 0;
		int height = 0;

	    
	    BufferedReader reader = new BufferedReader(new FileReader(filename));
		while (true) {
			String line = reader.readLine();
			// no more lines to read
			if (line == null) {
				reader.close();
				break;
			}

			if (!line.startsWith("!")) {
				lines.add(line);
				width = Math.max(width, line.length());

			}
		}
		height = lines.size();

		for (int j = 0; j < 12; j++) {
			String line = (String) lines.get(j);
			for (int i = 0; i < width; i++) {

				if (i < line.length()) {
					char ch = line.charAt(i);
					Tile t = new Tile(i, j, Character.getNumericValue(ch));
					tilearray.add(t);
				}

			}
		}

	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void run() {
		while (introCount > 0) {

			repaint();
		}
			
		state = GameState.Running;
	
		if (state == GameState.Running) {

			while (true) {
//				player bits
				player.update();
				if (player.health <= 0) {
					player.playerDead = true;
					state = GameState.Dead;
				}
				if (player.isJumped()) {
					currentSprite = characterJumped;
				} else if (player.isJumped() == false && player.isDucked() == false) {
					if (player.getVelX() == 0) {
						currentSprite = character;
					} else {
						currentSprite = anim.getImage();
					}
				}
				
//				projectiles
				ArrayList projectiles = player.getProjectiles();
				for (int i = 0; i < projectiles.size(); i++) {
					Projectile p = (Projectile) projectiles.get(i);
					if (p.isVisible() == true) {
						p.update();
					} else {
						projectiles.remove(i);
					}
				}
	
				updateTiles();

				heliSprite1 = heliState(hb1);
				heliSprite2 = heliState(hb2);
				heliSprite3 = heliState(hb3);
				heliSprite4 = heliState(hb4);
				heliSprite5 = heliState(hb5);
				heliSprite6 = heliState(hb6);
				heliSprite7 = heliState(hb7);
				heliSprite8 = heliState(hb8);
				heliSprite9 = heliState(hb9);
				
				tankSprite1 = checkTankie(tankie1);
				tankSprite2 = checkTankie(tankie2);
				tankSprite3 = checkTankie(tankie3);
				tankSprite4 = checkTankie(tankie4);
				
				bg1.update();
				bg2.update();
				animate();
				repaint();
				try {
					Thread.sleep(17);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (player.getPosY() > 400) {
					state = GameState.Dead;
					player.setVelX(0);
					player.setVelY(1);
				}
			}
		}
	}
	public Image heliState(Helicopter hb) {
		
		if (hb.isGrounded()) {
			hb.crashing();
			return hganim.getImage();
		} else if (hb.isKilled()) {
			hb.crashing();
			return hcanim.getImage();
		} else {
			hb.update();
			return hanim.getImage();			
		}
	}
	public Image checkTankie(Tank tankie) {
		if (tankie.deaded) {
			tankie.dead();
			return deadTank1;
		} else {
			tankie.update();
			return tank1;
		}
	}


	public void animate() {
		anim.update(10);
		hanim.update(50);
		hcanim.update(25);
		hganim.update(20);
	}

	@Override
	public void update(Graphics g) {
		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			second = image.getGraphics();
		}

		second.setColor(getBackground());
		second.fillRect(0, 0, getWidth(), getHeight());
		second.setColor(getForeground());
		paint(second);

		g.drawImage(image, 0, 0, this);

	}

	@Override
	public void paint(Graphics g) {

//////////////////
// INTRO SCENES //
//////////////////
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 800, 480);
		int introTempNum;
		String introTempString;
		g.setFont(font20);
			// change to while(gamestate.intro) { g.drawing image && g.drawstring && intro.update() }
			// intro.update( if int 10 image = intro 10 );
			// or some such. but most of the work can be done elsewhere with just printing here..
		if (state == GameState.Intro) {

			if (introCount == 10)	{
				String charSelected = "";
				g.setFont(font40);
				if(playPenis) {
					anim = panim;
					character = penis1;
					characterJumped = penisJumped;
					characterDown = penisDown;
					System.out.println("play as penis");
					g.drawImage(charSelectionPenis, 100, 125, this);
					charSelected = "THE PENIS";
					
				} else if (playWalker) {
					anim = wanim;
					character = charwalk1;
					characterJumped = charwalk1;
					characterDown = charwalk1;
					System.out.println("play as walker");
					g.drawImage(charSelectionWalker, 100, 125, this);
					charSelected = "THE AMORPHIS";
					
				} else if (playDemon) {
					anim = danim;
					character = demon1;
					characterJumped = demon1;
					characterDown = demon1;
					System.out.println("play as demon");
					g.drawImage(charSelectionDemon, 100, 125, this);
					charSelected = "THE DEMONIC";
				} else if (playPoo) {
					anim = pooanim;
					character = pooman1;
					characterJumped = pooman1;
					characterDown = pooman1;
					System.out.println("play as pooman");
					g.drawImage(charSelectionPoo, 100, 125, this);
					charSelected = "THE POOMAN";
					
				}
				
				g.drawImage(charSelection, 0, 0, this);
				currentSprite = anim.getImage();
				g.drawString(charSelected, 350, 200);
				
			} else if (introCount == 9)	{
				
				g.drawImage(introOne, 0, 0, this);
				g.setColor(Color.BLACK);
				g.fillRect(-15, 440, 850, 80);
				g.setColor(Color.WHITE);
				introTempString = "In the year of 2030 a great engineer named Fred was on the verge";
				introTempNum = (800 - g.getFontMetrics().stringWidth(introTempString)) / 2;
				g.drawString(introTempString, introTempNum, 480 - 10);
				
			} else if (introCount == 8)	{
				
				g.drawImage(introOne, 0, 0, this);
				g.setColor(Color.BLACK);
				g.fillRect(-15, 440, 850, 80);
				g.setColor(Color.WHITE);
				introTempString = "of creating a new form of energy that would take man to the stars.";
				introTempNum = (800 - g.getFontMetrics().stringWidth(introTempString)) / 2;
				g.drawString(introTempString, introTempNum, 480 - 10);
				
			} else if (introCount == 7)	{
				
				g.drawImage(introTwo, 0, 0, this);				
				g.setColor(Color.BLACK);
				g.fillRect(-15, 440, 850, 80);
				g.setColor(Color.WHITE);
				introTempString = "But late one evening the experimental reactor became unstable.";
				introTempNum = (800 - g.getFontMetrics().stringWidth(introTempString)) / 2;
				g.drawString(introTempString, introTempNum, 480 - 10);
				
			} else if (introCount == 6)	{
				
				g.drawImage(introTwo, 0, 0, this);				
				g.setColor(Color.BLACK);
				g.fillRect(-15, 440, 850, 80);
				g.setColor(Color.WHITE);
				introTempString = "Fred went into the reactor room to try and stop a meltdown,";
				introTempNum = (800 - g.getFontMetrics().stringWidth(introTempString)) / 2;
				g.drawString(introTempString, introTempNum, 480 - 10);
				
			} else if (introCount == 5)	{
				
				g.drawImage(introTwo, 0, 0, this);				
				g.setColor(Color.BLACK);
				g.fillRect(-15, 440, 850, 80);
				g.setColor(Color.WHITE);
				introTempString = "but found someone had sabotaged the reactor's safety controls.";
				introTempNum = (800 - g.getFontMetrics().stringWidth(introTempString)) / 2;
				g.drawString(introTempString, introTempNum, 480 - 10);
				
			} else if (introCount == 4)	{
				
				g.drawImage(introThree, 0, 0, this);
				g.setColor(Color.BLACK);
				g.fillRect(-15, 440, 850, 80);
				g.setColor(Color.WHITE);
				introTempString = "Fred knew a catastrophy was inevitable and with no safety controls";
				introTempNum = (800 - g.getFontMetrics().stringWidth(introTempString)) / 2;
				g.drawString(introTempString, introTempNum, 480 - 10);
				
			} else if (introCount == 3)	{
				
				g.drawImage(introThree, 0, 0, this);
				g.setColor(Color.BLACK);
				g.fillRect(-15, 440, 850, 80);
				g.setColor(Color.WHITE);
				introTempString = "his only choice was to manually close the protective doors";
				introTempNum = (800 - g.getFontMetrics().stringWidth(introTempString)) / 2;
				g.drawString(introTempString, introTempNum, 480 - 10);
				
			} else if (introCount == 2)	{
				
				g.drawImage(introThree, 0, 0, this);
				g.setColor(Color.BLACK);
				g.fillRect(-15, 440, 850, 80);
				g.setColor(Color.WHITE);
				introTempString = "with himself still inside.";
				introTempNum = (800 - g.getFontMetrics().stringWidth(introTempString)) / 2;
				g.drawString(introTempString, introTempNum, 480 - 10);
				
			} else if (introCount == 1)	{
				
				g.setFont(font40);
				g.setColor(Color.RED);
				introTempString = "The Monster of TinkleTown";
				introTempNum = (800 - g.getFontMetrics().stringWidth(introTempString)) / 2;
				g.drawString(introTempString, introTempNum, 200);
	//			paint the explosion animation here
	//			maybe through a long for loop slowly incrementing the animation frame?
			}
		}
		
////////////////////
// MAIN GAME BODY //
////////////////////
		
		if (state == GameState.Running) {

//			Environment
			g.drawImage(background, bg1.getBgX(), bg1.getBgY(), this);
			g.drawImage(background, bg2.getBgX(), bg2.getBgY(), this);
			paintTiles(g);

			
//			Helicopters
			g.drawImage(heliSprite1, hb1.getPosX() - 48, hb1.getPosY() - 48, this);
			g.drawImage(heliSprite2, hb2.getPosX() - 48, hb2.getPosY() - 48, this);
			g.drawImage(heliSprite3, hb3.getPosX() - 48, hb3.getPosY() - 48, this);
			g.drawImage(heliSprite4, hb4.getPosX() - 48, hb4.getPosY() - 48, this);
			g.drawImage(heliSprite5, hb5.getPosX() - 48, hb5.getPosY() - 48, this);
			g.drawImage(heliSprite6, hb6.getPosX() - 48, hb6.getPosY() - 48, this);
			g.drawImage(heliSprite7, hb7.getPosX() - 48, hb7.getPosY() - 48, this);
			g.drawImage(heliSprite8, hb8.getPosX() - 48, hb8.getPosY() - 48, this);
			g.drawImage(heliSprite9, hb9.getPosX() - 48, hb9.getPosY() - 48, this);
			
//			Projectiles
			ArrayList projectiles = player.getProjectiles();
			for (int i = 0; i < projectiles.size(); i++) {
				Projectile p = (Projectile) projectiles.get(i);
				g.drawImage(pew, p.getX(), p.getY(), this);
//				g.drawRect((int)p.getX() +8, (int)p.getY() +8, 20, 20);
			}

//			Player
			g.drawImage(currentSprite, player.getPosX(), player.getPosY(), this);
			
//			Tanks
			g.drawImage(tankSprite1, tankie1.getPosX(), tankie1.getPosY(), this);
			g.drawImage(tankSprite2, tankie2.getPosX(), tankie2.getPosY(), this);
			g.drawImage(tankSprite3, tankie3.getPosX(), tankie3.getPosY(), this);
			g.drawImage(tankSprite4, tankie4.getPosX(), tankie4.getPosY(), this);

//			Score & health
			g.setFont(font20);
			g.setColor(Color.BLACK);
			int scoreWidth = g.getFontMetrics().stringWidth("Molecular Instability: " + Integer.toString(score));
			g.drawString("Molecular Instability:" + Integer.toString(score), 790 - scoreWidth, 30);
			g.drawString(Integer.toString(player.health), player.health + 15, 28);


			//health bar
			g.setColor(Color.RED);
			g.fillRect(10, 10, player.health, 20);
			
// collision testing

			
/////////////////////////////
//			ENEMY WEAPONS  //
/////////////////////////////
			
//			Heli Missles
			g.drawRect((int)hb1.getMisslePosX(), (int)hb1.getMisslePosY(), (int)hb1.missle.getWidth(), (int)hb1.missle.getHeight());
			g.drawRect((int)hb2.getMisslePosX(), (int)hb2.getMisslePosY(), (int)hb2.missle.getWidth(), (int)hb2.missle.getHeight());
			g.drawRect((int)hb3.getMisslePosX(), (int)hb3.getMisslePosY(), (int)hb3.missle.getWidth(), (int)hb3.missle.getHeight());
			g.drawRect((int)hb4.getMisslePosX(), (int)hb4.getMisslePosY(), (int)hb4.missle.getWidth(), (int)hb4.missle.getHeight());
			g.drawRect((int)hb5.getMisslePosX(), (int)hb5.getMisslePosY(), (int)hb5.missle.getWidth(), (int)hb5.missle.getHeight());
			g.drawRect((int)hb6.getMisslePosX(), (int)hb6.getMisslePosY(), (int)hb6.missle.getWidth(), (int)hb6.missle.getHeight());
			g.drawRect((int)hb7.getMisslePosX(), (int)hb7.getMisslePosY(), (int)hb7.missle.getWidth(), (int)hb7.missle.getHeight());
			g.drawRect((int)hb8.getMisslePosX(), (int)hb8.getMisslePosY(), (int)hb8.missle.getWidth(), (int)hb8.missle.getHeight());
			g.drawRect((int)hb9.getMisslePosX(), (int)hb9.getMisslePosY(), (int)hb9.missle.getWidth(), (int)hb9.missle.getHeight());
			
//			Tank Shooting
			g.drawRect((int)tankie1.getTankShot().getX(), (int)tankie1.getTankShot().getY(), (int)tankie1.getTankShot().getWidth(), (int)tankie1.getTankShot().getHeight());
			g.drawRect((int)tankie2.getTankShot().getX(), (int)tankie2.getTankShot().getY(), (int)tankie2.getTankShot().getWidth(), (int)tankie2.getTankShot().getHeight());
			g.drawRect((int)tankie3.getTankShot().getX(), (int)tankie3.getTankShot().getY(), (int)tankie3.getTankShot().getWidth(), (int)tankie3.getTankShot().getHeight());
			g.drawRect((int)tankie4.getTankShot().getX(), (int)tankie4.getTankShot().getY(), (int)tankie4.getTankShot().getWidth(), (int)tankie4.getTankShot().getHeight());

//			
//			g.drawRect((int)tankie1.r.getX(), (int) tankie1.r.getY(), (int)tankie1.r.getWidth(), (int)tankie1.r.getHeight());
//			g.drawRect((int)tankie2.r.getX(), (int) tankie2.r.getY(), (int)tankie2.r.getWidth(), (int)tankie2.r.getHeight());
//			g.drawRect((int)tankie3.r.getX(), (int) tankie3.r.getY(), (int)tankie3.r.getWidth(), (int)tankie3.r.getHeight());
//			g.drawRect((int)tankie4.r.getX(), (int) tankie4.r.getY(), (int)tankie4.r.getWidth(), (int)tankie4.r.getHeight());
//
//			g.drawRect((int)player.rect.getX(), (int)player.rect.getY(), (int)player.rect.getWidth(), (int)player.rect.getHeight());
//			g.drawRect((int)player.rect2.getX(), (int)player.rect2.getY(), (int)player.rect2.getWidth(), (int)player.rect2.getHeight());
//			g.drawRect((int)player.yellowRed.getX(), (int)player.yellowRed.getY(), (int)player.yellowRed.getWidth(), (int)player.yellowRed.getHeight());
//			g.drawRect((int)hb1.getRectX() - 45, (int)hb1.getRectY() - 45, 75, 24);
//			g.drawRect((int)hb2.getRectX() - 45, (int)hb2.getRectY() - 45, 75, 24);
//			
//////////////////////////
// GAME OVER CONDITIONS //
//////////////////////////
			
		} else if (state == GameState.Dead) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 800, 480);
			g.setFont(font30);
			
			if (player.playerDead) {
				g.setColor(Color.RED);
				g.drawImage(currentSprite, player.getPosX() - 61, player.getPosY() - 63, this);
				g.drawString("You look into the sky as you lay dying,", 50, 150);
				g.drawString("and remember what what it was", 50, 200);
				g.drawString("to be human.", 50, 250);
					
			} else {
				g.setColor(Color.WHITE);
				g.drawImage(currentSprite, player.getPosX() - 61, player.getPosY() - 63, this);
				g.drawString("And so the abomination,", 120, 150);
				g.drawString("realising what it had become and,", 120, 200);
				g.drawString("beset on all sides by foes,", 120, 250);
				g.drawString("threw itself into the abyss.", 120, 300);	
			}
		}
	}

	private void updateTiles() {

		for (int i = 0; i < tilearray.size(); i++) {
			Tile t = (Tile) tilearray.get(i);
			t.update();
		}
	}

	private void paintTiles(Graphics g) {
		for (int i = 0; i < tilearray.size(); i++) {
			Tile t = (Tile) tilearray.get(i);
			g.drawImage(t.getTileImage(), t.getTileX(), t.getTileY(), this);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

		switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				System.out.println("Move up");
				break;
	
			case KeyEvent.VK_DOWN:
				currentSprite = characterDown;
				if (player.isJumped() == false) {
					player.setDucked(true);
					player.setVelX(0);
				}
				break;
	
			case KeyEvent.VK_LEFT:
				if (state == GameState.Intro && introCount == 10) {
					if (playPenis) {
						playPenis = false;
						playWalker = true;
						playDemon = false;
						playPoo = false;
					} else if (playWalker) {
						playPenis = false;
						playWalker = false;
						playDemon = true;
						playPoo = false;
					} else if (playDemon) {
						playPenis = false;
						playWalker = false;
						playDemon = false;
						playPoo = true;
					} else if (playPoo) {
						playPenis = true;
						playWalker = false;
						playDemon = false;
						playPoo = false;
					}
				}
				player.moveLeft();
				player.setMovingLeft(true);
				break;
	
			case KeyEvent.VK_RIGHT:
				if (state == GameState.Intro && introCount == 10) {
					if (playPenis) {
						playPenis = false;
						playWalker = false;
						playDemon = false;
						playPoo = true;
					} else if (playWalker) {
						playPenis = true;
						playWalker = false;
						playDemon = false;
						playPoo = false;
					} else if (playDemon) {
						playPenis = false;
						playWalker = true;
						playDemon = false;
						playPoo = false;
					} else if (playPoo) {
						playPenis = false;
						playWalker = false;
						playDemon = true;
						playPoo = false;
					}
				}
				
				player.moveRight();
				player.setMovingRight(true);
				break;
	
			case KeyEvent.VK_Z:
				player.jump();
				break;
	
			case KeyEvent.VK_X:
				if (player.isDucked() == false && player.isJumped() == false
						&& player.isReadyToFire()) {
					player.shoot();
					player.setReadyToFire(false);
				}
				if (state == GameState.Intro) {
					introCount -= 1;
				}
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			System.out.println("Stop moving up");
			break;

		case KeyEvent.VK_DOWN:
			currentSprite = anim.getImage();
			player.setDucked(false);
			break;

		case KeyEvent.VK_LEFT:
			player.stopLeft();
			break;

		case KeyEvent.VK_RIGHT:
			player.stopRight();
			break;

		case KeyEvent.VK_Z:
			break;

		case KeyEvent.VK_X:
			player.setReadyToFire(true);
			break;

		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public static Background getBg1() {
		return bg1;
	}

	public static Background getBg2() {
		return bg2;
	}

	public static Player getPlayer() {
		return player;
	}

}
