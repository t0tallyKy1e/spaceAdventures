/*
*     Kyle Demers 'AKA' t0tallyKy1e
*     Revisions began on: 04/23/2014
*
**    spaceAdventures - Enemy class
*/

public class Enemy extends Player{
	// name
	// hitpoints --random hitpoints
	// inventory
	// attacks -- pick random attacks for each enemy
	
	public final int MAXIMUM_HIT_POINTS = 200;
	
	private String name;
	private int hitPoints;
	private Item heldItem;
	private AttackList attacks = new AttackList();
	private int hp;
	/*
		requires a String for the name of the Enemy
	*/
	public Enemy(String name){
		this.name = name;
		hitPoints = getRandomHitPoints();
		//add attacks to attack list
	}//end of Enemy Constructor #1
	
	/*
		gets random number for the enemy's hit points
		post: returns int for the enemy's hit points
	*/
	public int getRandomHitPoints(){
		int max = MAXIMUM_HIT_POINTS;
		int min = 1;
		int range = max - min;
		int offset;
		if (min > max){
			max = min;
			offset = max;
		}
		else{
			offset = min;
		}
		
		hp = (int) (Math.random() * range) + offset; 
		return hp;
	}//end of getRandomHitPoints
	
	/*
		post: returns the Enemy's health
	*/
	public int getHealth(){
		return hp;
	}//end of getHealth
	
	/*
		give enemy an inventory
		pre: requires an Item to give to the enemy
	*/
	public void giveItem(Item tempItem){
		heldItem = tempItem;
	}//end of giveItem
	
	/*
		give player the Item when the Enemy's health reaches 0
		post: returns the Enemy's heldItem
	*/
	public Item getItem(){
		return heldItem;
	}//end of getItem
	
	/*
		arms the enemy with different Attacks
		pre: requires an Attack to add to the AttackList
	*/
	public void addAttack(Attack tempAttack){
		attacks.addAttack(tempAttack);
	}//end of addAttack
	
	/*
		allows the enemy to take damage
		pre: requires an int of health to subtract from the Enemy's health
	*/
	public void removeHealth(int tempHealth){
		hp = hp - tempHealth;
	}//end of removeHealth
	
	/*
		chooses a random attack to use against the player
		post: returns an attack to use against the player
	*/
	public Attack useAttack(){
		int tempIndex;
		int max = attacks.getSize();
		int min = 0;
		int range = max - min;
		int offset;
		if (min > max){
			max = min;
			offset = max;
		}
		else{
			offset = min;
		}
		
		tempIndex = (int) (Math.random() * range) + offset; 
		return attacks.getAttack(tempIndex);
	}//end of useAttack
	
	/*
		post: returns the name of the Enemy as a String
	*/
	public String getName(){
		return name;
	}//end of getName
}//end of Enemy