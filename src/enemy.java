/**********************************************************
* Program	:  ISU	
* Author	:  Nick Gaudet
* Due Date	:  Jan 21
* Description	:  Controls the enemies in the game
***********************************************************/
import java.awt.*;
import java.util.Random;
import javax.swing.ImageIcon;
public class enemy {
	Random rand= new Random();
	//Variables
	private double x;
	double y =5 ;
	int r = 32 ;
	double velx = rand.nextDouble() - 0.8;
	double vely = rand.nextDouble() + 0.15;
	int type;
	int rank;
	int speed = 2;
	boolean dead;
	boolean ready;
	Color blue;
	public enemy(int type, int rank){//Contructor
		this.type = type;
		this.rank = rank;
		//IF TYPE IS 1
		
		setFoe(this.type);//SETS THE IMAGE FOR ENEMY 1
		if (rank == 1){
			//SETS THEIR POSITIONS OF THE ENEMIES
			x = rand.nextInt(mainclass.WIDTH-128);
			//y = Math.random() * mainclass.HEIGHT / 6 ;
		}
		
	}
	//SETS A RANDOM THING TO SET THE X VALUES
	public int xpos( int xpos){
		if (xpos == 1){
			x = rand.nextInt( mainclass.WIDTH - 128) ;
			
		}
		if (xpos == 2){
			x = Math.random() * mainclass.WIDTH / 4;
			
		}
		return xpos;
	}
	//GETTER FOR THE X OF THE ENEMY
	public double getx(){ return x;}
	//GETTER FOR THE Y OF THE ENEMY
	public double gety(){ return y;}
	//MOVES THE ENEMIES
	public void move(){
		//ADS THE VELX TO THE X VALUE
		x += velx;
		//ADDS THE VELY TO THE Y VALUE
		y += vely;
	
		//IF THE ENEMY HITS THE BOUNDARIES OF THE WINDOW THEN BOUNCE IT BACK
		if ( x > mainclass.WIDTH-72 || x < -10 ){
			velx = -velx;
		}
		//IF IT GOES OFF THE SCREEN RESET THE POSITION
		if (y > mainclass.HEIGHT){
			y = -10;
		}
		
	}
	//RESETS POSITION
	public void reset(){
		y = -10;
	}
	//METHOD TO SET A VARIABLE TO THE IMAGE
	public Image setFoe(int num){
		ImageIcon foe;
		switch(num){
			case 1:
				foe = new ImageIcon(getClass().getResource("images/enemy.gif"));
				return foe.getImage();
			case 2:
				foe = new ImageIcon(getClass().getResource("images/enemy2.gif"));
				return foe.getImage();
		}return null;


	}
	//PAINTS THE IMAGE
	public void paint(Graphics g){
		
		g.drawImage(setFoe(this.type), (int)x, (int)y, null);
		
	}
	//BOUNDARIES FOR THE ENEMIES
	public Rectangle getBounds(){
		return new Rectangle ((int)x,(int)y,64,64);
	}
}
