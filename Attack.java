public class Attack{
	private String name;
	private int damagePoints;
	
	/*
		pre: requires a String for the name of the attack
		pre: requires an int for the damage points of the attack
	*/
	public Attack(String name, int damagePoints){
		this.name = name;
		this.damagePoints = damagePoints;
	}//end of Attack Constructor #1
	
	/*
		post: returns the name of the Attack
	*/
	public String getAttackName(){
		return name;
	}//end of getAttackName
	
	/*
		post: returns the damagePoints of the Attack
	*/
	public int getDamagePoints(){
		return damagePoints;
	}//end of getDamagePoints
}//end of Attack