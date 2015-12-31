import java.util.Scanner;

/*
*     Kyle Demers 'AKA' t0tallyKy1e
*     Revisions began on: 04/23/2014
*
**    spaceAdventures - Player class
*/

public class Player extends Market
{
	Scanner input = new Scanner(System.in);
	
	private Inventory backpack = new Inventory();
	
	public Ship playerShip = new Ship();
	
	private double playerWallet;
	private int fuelTank = playerShip.checkFuel();
	private AttackList attacks = new AttackList();
	
	private String playerName;
	private int playerHealth;
	
	private String userString;
	private int userInt;
	
	/*
		Player Constructor
		contains:
				 the player's name
				 wallet amount
				 and it fill the player's backack
	*/
	public Player()
	{
		playerName = "Captain ";
		playerWallet = 0.0;
		playerHealth = 100;
	}//end of Player Constructor
	
	/*
		sets the player's name to "Captain [user name here]"
		pre: requires a string for the user's name
	*/
	public void setPlayerName(String userString)
	{
		playerName = playerName + userString;
	}//end of setPlayerName
	
	/*
		gets the player's name
		post: returns a String of the player's name
	*/
	public String getPlayerName()
	{
		return playerName;
	}//end of getPlayerName
	
	/*
		adds money to the player's wallet
		
		pre: requires a double for the amount to add to the player's wallet
	*/
	public void addMoney(double money)
	{
		playerWallet = playerWallet + money;
	}//end of addMoney
	
	/*
		removes money from the player's wallet
		
		pre: requires a double for the amount to be taken away from the player's wallet
	*/
	public void removeMoney(double money)
	{
		playerWallet = playerWallet - money;
	}//end of removeMoney
	
	/*
		gets the player's wallet amount
		post: returns the player's wallet as a double
	*/
	public double getWallet()
	{
		return playerWallet;
	}//end of getWallet
	
	/*
		adds fuel to the player's ship
		pre: requires an integer of fuel to add
	*/
	public void addFuel(int fuel)
	{
		playerShip.addFuel(fuel);
	}//end of addFuel
	
	/*
		removes fuel from the player's ship
		pre: requires an int of fuel to remove
	*/
	public void useFuel(int fuel)
	{
		playerShip.useFuel(fuel);
	}//end of useFuel
	
	/*
		checks the fuel of the player's ship
		post: returns an integer of the ship's fuel
	*/
	public int checkFuel()
	{
		return playerShip.checkFuel();
	}//end of checkFuel
	
	/*
		gets the player's ship
		post: returns a Ship
	*/
	public Ship getShip()
	{
		return playerShip;
	}//end of getShip
	
	/*
		post: return the Player's health as an int
	*/
	public int getPlayerHealth()
	{
		return playerHealth;
	}//end of getPlayerHealth
	
	/*
		adds attacks to the player's AttackList
		pre: requires an Attack to add to the AttackList
	*/
	public void addAttack(Attack tempAttack)
	{
		attacks.addAttack(tempAttack);
	}//end of addAttack
	
	/*
		allows the Player to use a certain Attack
		pre: requires an int for the index of the Attack
		post: returns an Attack that the Player chooses
	*/
	public Attack getAttack(int index)
	{
		return attacks.getAttack(index);
	}//end of getAttack
	
	/*
		Allows the Player to view all of their attacks
	*/
	public void viewAttacks()
	{
		for(int i = 0; i < attacks.getSize(); i++)
		{
			System.out.println("[ " + i + " ] " + attacks.getAttack(i).getAttackName());
		}
	}//end of viewAttacks
	
	/*
		allows the Player to be damaged
		pre: requires an int for the amount of damage done
	*/
	public void removeHealth(int damage)
	{
		playerHealth = playerHealth - damage;
	}//end of removeHealth
	
	/*
		adds an Item to the Player's backpack
		pre: requires an Item to give to the player
		pre: requires an int for the stock
	*/
	public void addPlayerItem(Item tempItem)
	{
		backpack.addItem(tempItem);
	}//end of addPlayerItem
	
	/*
		post: returns int of the backpack's size
	*/
	public int getBackpackSize()
	{
		return backpack.getSize();
	}//end of getBackpackSize
}//end of Player