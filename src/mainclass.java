/**********************************************************
* Program	: ISU 
* Author	:  Nick	Gaudet
* Due Date	:  Jan 21
* Description	:  Controls everything from the game and is the main component of the class files
***********************************************************/
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class mainclass extends JPanel implements ActionListener, KeyListener {
	// Beginning
	 // of
	//Checks to see if the game has started
	public static boolean gamestart = true;
	// maingame
	//Checks to see if the player died
	boolean  dead = false;
	// Width of window
	public static final int WIDTH = 600;
	// height of window
	public static final int HEIGHT = 800;
	// Checks to see if they are going left or right
	boolean right, left;
	// Checks to see if the bullet was fired
	boolean fired = false;
	static boolean collided = false;
	boolean running = false;
	static //Checks to see if the bullet hit the side of the walls
	boolean hit = false;
	// Imports
	bullet bullet;
	ship ship;
	tracker tracker = new tracker();
	enemy en;
	// Variables
	int x = 5, y = 490;
	int score = 0;
	public int enemykilled = 0;
	private int enemycount = 1;
	int lives = 3;
	int totalscore;
	int enemieskilled = 0;
	int bulletsfired = 0;
	// Imports the timer
	Timer t = new Timer(2, this);; 
	// Arrays
	int [][] background = {{0}};
	//Lists
	public ArrayList<enemy> enemies = tracker.getEnemyBounds();
	public ArrayList<bullet> b = tracker.getBulletBounds();
	// Constructor
	public mainclass() {
		dead();
		//Checks to see if the game has started
		if (gamestart == true){
			//Creates a message box that explains the instructions of the game
			JOptionPane.showMessageDialog(null, "Welcome to The Most Intense Space Shooter Of The Century\nControls: A to move Left, D to Move Right, SPACEBAR to Shoot\n\n"
					+ "The Rules Are Simple:\n\n"
					+ "- Survive As Long As You Can Without Getting Hit By The Enemy Ships\n"
					+ "- You Get Score For Killing Enemies\n" 
					+ "- Press ENTER to Begin! Have Fun!\n\n");
			//Starts the game
			t.start();
		}
		// IMPORTS
		en = new enemy(1,1);
		ship = new ship(WIDTH, (HEIGHT)-128);
		bullet = new bullet(x, y, this);
		tracker = new tracker();
		running = true;
		tracker.addEnemy(enemycount);
		// adds key listener
		addKeyListener(this);
		// Sets it so it focuses on this window and this window only
		setFocusable(true);
		// Turns off shift and tab keys
		setFocusTraversalKeysEnabled(false);
		

	}
	//CHECKS TO SEE IF THE PLAYER WAS HIT
	public void alldead(){
		if (hit){
			//IF HIT SET THE ENEMY COUNT TO 1
			setEnemycount(1);
			//SETS ENEMIES KILLED TO 0
			enemykilled = 0;
			//LOO[S THROUGHT THE ENENMIES ARRAY LIST AND REMOVES EACH ENEMY
			for(int o = 0; o < enemies.size() ;o++){
				enemies.removeAll(enemies);
				break;
			}
			//ADDS THE ONE ENEMY ONCE THE ENEMIES ARE ALL REMOVED
			tracker.addEnemy(enemycount);
			
			hit = false;
		}
	}
	//UPDATES THE ENEMY COUNT ONCE THE AMOUNT OF ENEMY KILLED IS HIGHER OR EQUAL TO THE ENEMY COUNT
	//ADDS 2 MORE ENEMIES FROM THE ORIGINAL AMOUNT ONCE ALL ENEMIES WERE KILLED
	public void update(){
		if (enemykilled >= enemycount){
			enemycount += 2;
			enemykilled = 0;
			tracker.addEnemy(enemycount);
		}	
	}
	// Imports the space background
	ImageIcon space = new ImageIcon(getClass().getResource("images/space.jpeg"));
	// Imports the ship image
	ImageIcon shipimage = new ImageIcon(getClass().getResource("images/ship.png"));
	
	

	//RENDERS THE GRAPHICS TO THE SCREEN
	public void paintComponent(Graphics g2) {
		Graphics2D g = (Graphics2D) g2;
		super.paintComponent(g);
		//ARRAY USED TO PRINT THE BACKGROUND IMAGE
		for (int i = 0; i < background.length; i++){
			for (int o = 0; o < background.length; o++){
				space.paintIcon(this, g, 0, 0);// Imports the space background
			}
		}
		//PAINTS THE ENEMIES
		tracker.paintenemy(g);
		//SETS THE 
		// g.setFont(new Font("Arial",Font.PLAIN, 25));
		// g.setColor(Color.white);

		g.setFont(new Font("Arial",Font.PLAIN, 24));

		g.setColor(Color.blue);
		g.drawString(Integer.toString(score), WIDTH/2, 30);
		
		//SETS THE COLOURN OF THE LIVES FONT
		g.setColor(Color.blue);
		//PRINTS THE AMOUNT OF LIVES LEFT TO THE SCREEN
		g.drawString("Lives: " + lives, 30,30);
		//IF THE PLAYER IS NOT HIT THEN PAINT THE SHIP
		if (!hit){
			shipimage.paintIcon(this, g, ship.x, ship.y);// Imports the ship
		}
		//IF THE BULLETS ARE FIRED
		if (fired == true) {
			//IF THE BULLETS ARE FIRED BUT THE PLAYER IS NOT HIT THEN PRINT THE BULLETS
			if (!hit){
				tracker.print(g);
			}
			
		}
		
	}
	//GETTER FOR THE ENEMIES KILLED
	public int getEnemykilled() {
		return enemykilled;
	}
	//SETTER FOR THE ENEMIES KILLED
	public void setEnemykilled(int enemykilled) {
		this.enemykilled = enemykilled;
	}
	//GETTER FOR THE ENEMY COUNT
	public int getEnemycount() {
		return enemycount;
	}
	//SETTER FOR THE ENEMY COUNT
	public void setEnemycount(int enemycount) {
		this.enemycount = enemycount;
	}
	// Main method where JFrame is created
	public static void main(String[] args) {// Where JFrame will be implemented
		// and and other J....
		JFrame j = new JFrame();		
		mainclass m = new mainclass();
		//GAME TITLE
		j.setTitle("Space Shooter");
		//GAME WINDOW DIMENSIONS
		j.setSize(WIDTH, HEIGHT);
		//SETS THE JFRAME VISIBLE
		j.setVisible(true);
		//IF THE PLAYER CLICKS THE EXIT BUTTON THEN EXIT THE JFRAME
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//ADD THE MAINCLASS TO THE JFRAME
		j.add(m);
		//MAKE THE JRAME NOT RESIZABLE
		j.setResizable(false);
		
		

	}

	// Checks to see if the key is pressed
	public void keyPressed(KeyEvent e) {
		// Gets the keycode to make it more simple to check instances
		int c = e.getKeyCode();
		//SWITCH TO MAKE THE SHIP MOVEMENT SMOOTHER
		switch (c) {
		//CASE FOR THE MOVING LEFT
		case KeyEvent.VK_D:
			ship.setvelX(1);// Sets the ship velocity to 1
			right = true;
			left = false;
			break;
		//CASE FOR MOVING RIGHT
		case KeyEvent.VK_A:
			ship.setvelX(-1);// sets ship velocity to -1 so it goes left
			left = true;
			right = false;
			break;
		
		}
		
		
	

	}
	
	
	// Checks to see if the key was released
	public void keyReleased(KeyEvent e) {
		int c = e.getKeyCode();// Gets the keycode to make it more simple to
								// check instances
		//SWITCH FOR THE RELEASED MOVEMENTS
		switch (c) {
		//WHEN MOVING LEFT SET RIGHT TO FALSE
		case KeyEvent.VK_D:
			right = false;
			break;
		//WHEN MOVING RIGHT SET LEFT TO FALSE
		case KeyEvent.VK_A:
			left = false;
			break;
		case KeyEvent.VK_O:
			JOptionPane.showMessageDialog(null,"Statistics:\n"+ "Enemies Killed: " + enemieskilled+ "\nBullets Fired: " + bulletsfired+ "\nFinal Score: " + score);
			break;
		}
		//SETS VARIABLES TO THE KEY CODE
		c = e.getKeyCode(); 
		//IF SPACE IS HIT THEN ADD A BULLET
		if (c == KeyEvent.VK_SPACE && !hit) {
			fired = true;
			tracker.addBullet(new bullet(ship.x + 30, ship.y+5, this));
			bullet.movebullet();
			bulletsfired++;

		}

	}
	//MAIN METHOD FOR ALL COLLISION
	public void collision(){
		//CALLS UPDATE METHOD
		update();
		if (hit){
			return;
		}
		//SETS A VARIABLE TO GET THE BOUNDS OF THE ENEMIES
		enemies = tracker.getEnemyBounds();
		//SETS A VARIABLE TO GET THE BOUNDS OF THE BULLETS
		b = tracker.getBulletBounds();
		//VARIBALE TO GET THE SIZE OF THE BULLETS ARRAY LIST
		int bulletSize = b.size();
		//VARIABLE TO GET THE SIZE OF THE ENEMIES ARRAY LIST
		int enemySize = enemies.size();
		//DOUBLE FOR LOOP TO CHECK THE COLLISION OF EACH ENEMY AND EACH BULLET IN THEIR SEPERATE ARRAY LISTS
		for (int i = 0; i < bulletSize ; i++){
			for(int o = 0; o < enemySize ;o++){
				if (enemies.get(o).getBounds().intersects(b.get(i).getBounds())){
					//REMOVES THE ENEMIES
					enemies.remove(o);
					//REMOVES THE BULLETS
					b.remove(i);
					//ADDS THE SCORE UP
					score = score + 1500;
					//REDUCES THE SIZE OF THE BULLET ARRAY LIST BY ONE
					bulletSize--;
					//REDUCES THE SIZE OF THE ENEMIES ARRAY LIST BY ONE
					enemySize--;
					//ADDS UP THE ENEMIES KILLED
					enemieskilled++;
					//SETS THE ENEMIES KILLED TO +1
					setEnemykilled(getEnemykilled()+ 1);
					break;
					
				}
			
			}
			
			
		}
		//FOR LOOP TO CHECK IF THE ENEMIES HAVE COLLIDED WITH THE SHIP
		for (int i = 0; i < enemySize; i++){
			if (enemies.get(i).getBounds().intersects(ship.getBounds())){
				hit = true;
				//SETS HIT VARIABLE TO TRUE
				if (hit == true){
					//PLAYER LOSES A LIFE
					lives--;
					//PRINTS A DIALOG BOX THAT SAYS THE GAME WILL RESTART AND THEY LOST A LIFE
					JOptionPane.showMessageDialog(null, "You Died the Game Will Now Restart, You have " + lives + " lives left!");
					//CALLS THE ALLDEAD METHOD
					alldead();
					
				}
				
				break;
			}
			
		}
		
		
	}
	//METHOD TO SEE IF THE PLAYER HAS DIED
	public void dead(){
		//CHECKS TO SEE IF THE PLAYER HAS ZXERO LIVES LEFT
		if (lives == 0){
			//SETS A VARIABLE TO THE RESPONSE THE PLAYER DECIDES TO CLICK
			int response = JOptionPane.showConfirmDialog(null,"Statistics:\n"
					+ "Enemies Killed: " + enemieskilled
					+ "\nBullets Fired: " + bulletsfired
					+ "\nFinal Score: " + score
					+ "\nDo You Want To Play Again?" , "You Died!",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			//YES OPTION IN THE DIALOG BOX
			if (response  == JOptionPane.YES_OPTION){
				//RESETS
				enemycount = 1;
				enemykilled = 0;
				lives = 3;
				score = 0;
				enemieskilled = 0;
				bulletsfired = 0;
				
			}
			//CHECK THE NO OPTION
			else if (response  == JOptionPane.NO_OPTION){
				//PRINT A DIALOG BOX THAT SAYS THANKS FOR PLAYING
				JOptionPane.showMessageDialog(null, "Thanks For Playing!");
				//EXITS THE SYSTEM
				System.exit(0);
			}
			//CHECKS THE CLOSED OPTION
			else if(response  == JOptionPane.CLOSED_OPTION){
				//PRINT A DIALOG BOX THAT SAYS THANKS FOR PLAYING
				JOptionPane.showMessageDialog(null, "Thanks For Playing!");
				//EXITS THE SYSTEM
				System.exit(0);
			}
			
		}
	}
	public void keyTyped(KeyEvent e) { 
		
	}
	//WHERE ALL MAIN MOVEMENT COMPONENT ARE CONTRUCTED
	public void actionPerformed(ActionEvent e) {// Makes things happen
		//CALLS THE DEAD
		dead();
		if (ship==null){//Checks to see if the ship created is null, if so exit the method
			return;
		}
		//CALLS THE COLLISION
		collision();
		if (ship.x < 0) {// If the ship hits the edge of the screen
			ship.x = 0;// Make the ship stop moving
		}
		else if (ship.x > this.WIDTH - 72) {// IF the ship hits the edge of the screen
			ship.x = this.WIDTH - 72;;// Make the ship stop moving
			
		}
		//IF RUNNING MOVE THE ENEMY
		if (running == true){
			tracker.moveenemy();
		}
 		
		// IF FIRED IS TRUE MOVE THE BULLET
		if (fired == true) {
			tracker.move();
		}
		// IF THE SHIP IS MOVING RIGHT THEN MOVE IT RIGHT
		if (right == true) {// If the ship is going right then move it
			ship.move();// Moves the ship
		}
		//IF THE SHIP IS MOVING LEFT THE MOVE THE SHIP LEFT
		
		if (left == true) {// If the ship is going left then move it
			ship.move();// Move the ship
		}
		
		
		repaint();// repaint the image

	}

}
