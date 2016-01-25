import java.util.ArrayList;

public class AttackList{
	private ArrayList<Attack> attacks = new ArrayList<Attack>(0);
	
	/*
		adds an attack to the list
		pre: requires an Attack to add to the list
	*/
	public void addAttack(Attack tempAttack){
		attacks.add(tempAttack);
	}//end of AttackList
	
	/*
		grabs a specific Attack from the AttackList
		pre: requires an int for the index of the Attack
		post: returns the Attack you need
	*/
	public Attack getAttack(int index){
		Attack tempAttack = attacks.get(index);
		return tempAttack;
	}//end of getAttack
	
	/*
		returns the size of the AttackList
		post: returns an int of the size of the AttackList
	*/
	public int getSize(){
		return attacks.size();
	}//end of getSize
}//end of AttackList