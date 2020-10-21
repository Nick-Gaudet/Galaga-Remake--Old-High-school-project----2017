/**********************************************************
* Program	:  ISU	
* Author	:  Nick Gaudet
* Due Date	:  Jan 21
* Description	:  Class file for the ship
***********************************************************/
import java.awt.Graphics;
import java.awt.Rectangle;
public class ship {
	int x, y;
	double velX = 7;
	
	public ship(int x, int y){//Ships contructor
		this.x = x;
		this.y = y;
		
	}
	public void move(){//Moves ship
		x += velX;
	}
	//SETTER FOR THE X VALUE OF THE SHIP
	public int setx(int x){return this.x = x;}
	//GETTER FOR THE X VALUE OF THE SHIP
	public int getx(){return x;}
	//SETTER FOR THE VELX
	public void setvelX(double velX){//sets the velocity of the ships x
		this.velX = velX;
	}
	//BOUNDS OF THE SHIP
	public Rectangle getBounds(){//Gets the bounds of the ship
		return new Rectangle (x + 20,y+20,32,32);
	}
	public void clearShip(Graphics g){
		g.clearRect(x,y,32,32);
	}
}
