/**********************************************************
* Program	:  ISU
* Author	:  Nick Gaudet
* Due Date	:  Jan 21
* Description	:  Controls the bullets fired
***********************************************************/
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
public class bullet {
	//VARIABLES
	int x,y,velY = 4;
	
	public bullet(int startX, int startY, mainclass game){//bullet contructor
		x = startX;
		y = startY;
		
	}
	public void setbulletx(int x){//Sets the bullet x
		this.x = x;
	}
	public void movebullet(){//Moves bullet by a y value
		y -= velY;
	}
	//SETS THE VELY OF THE BULLET
	public void setvelY(int velY){
		this.velY = velY;
	}
	public void paint(Graphics g){//Sets the bullet colour
		g.setColor(new Color(255,0,255));
		g.fillRect(x,y,3,15);
		
	}
	
	public Rectangle getBounds(){//gets the bounds of the bullet
		return new Rectangle (x,y,3,15);
	}
	
	

}
