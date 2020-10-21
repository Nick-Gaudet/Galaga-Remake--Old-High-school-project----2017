/**********************************************************
* Program	:  ISU
* Author	:  Nick Gaudet
* Due Date	:  Jan 21
* Description	:  Tracks all the important things that are being constantly created
***********************************************************/
import java.awt.Graphics;
import java.util.*;
public class tracker { //tracks the many things that will take place in the game
	Random rand = new Random();													//create multiple entities as you go
	//IMPORTS
	bullet tbullet;//imports bullet
	mainclass game;//imports game
	enemy newenemy;
	//ARRAY LISTS
	public ArrayList<bullet> b;
	public ArrayList<enemy> enemies;
	//VARIABLES
	int level = -1;
	int amtofenemies = 2;
	

	

	public tracker(){//Contructor for this file
		b = new ArrayList<bullet>();//Creates a list to keep creating new bullets
		enemies = new ArrayList<enemy>();//Creates a list to keep creating new enemies , instead of creating multiple enemies seperate you can use a array list to 
		
		
		
	}
	
	public void move(){//moves the bullet in the array list
		for (int i = 0; i < b.size(); i++){
			tbullet = b.get(i);
			tbullet.movebullet();
		}
		
		
	}
	public void print(Graphics g){//repaints the bullet in the array list
		for (int i = 0; i < b.size(); i++){
			tbullet = b.get(i);
			tbullet.paint(g);
		}
		
		
		
		
	}
	public void moveenemy(){//moves the enemy in the array list
		enemy newenemy;
		for (int i = 0; i < enemies.size(); i++){
			newenemy = enemies.get(i);
			newenemy.move();
			
		}
	}
	public void paintenemy(Graphics g){//paints the enemy in the array list
		
		for (int i = 0; i < enemies.size(); i++){
			newenemy = enemies.get(i);
			newenemy.paint(g);
		}
	}
	public void addEnemy(int enemycount){//adds and enemy
		Random rand = new Random();
		float chance;
		for(int i = 0; i < enemycount; i++){
			chance = rand.nextFloat();
			if (chance < 0.30){
				enemies.add(new enemy(2,1));

			}else{
				enemies.add(new enemy(1,1));
			}
		}
		
		
	}
	public void removeEnemy(enemy block){//removes an enemy from array list
		enemies.remove(block);
	}
	public void addBullet(bullet block){//adds a bullet from the linked list
		b.add(block);
	}
	public void removeBullet(bullet block){//removes a bullet from the linked list
		b.remove(block);
	}
	public ArrayList<bullet> getBulletBounds(){//Gets each bound of the bullets from the linked list 
		return b;
	}
	public ArrayList<enemy> getEnemyBounds(){//Gets each boundaries of the enemies from the linked list
		return enemies;
	}

	
	
}
