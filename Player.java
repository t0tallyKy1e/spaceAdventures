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
	
	private Item oxygenTanks = new Item("Portable Oxygen Tanks", 0);
	private Item healthPacks = new Item("Health Packs", 0);
	private Item alienRepellent = new Item("Alien Repellent", 0);
	private Item humanRepellent = new Item("Human Repellent", 0);
	private Item spaceTunes = new Item("Space Tunes", 0);
	private Item spaceArmor = new Item("Space Armor", 0);
	private Item blasterGuns = new Item("Blaster Guns", 0);
	private Item roboShipCrew = new Item("Robo Crew Members", 0);
	private Item shipRepairKits = new Item("Ship Repair Kits", 0);
	private Item magicSpaceGrass = new Item("Magic Space Grass", 0);
	private Item ships = new Item("Ships", 0);
	
	public Ship playerShip = new Ship();
	
	private double playerWallet;
	private int fuelTank = playerShip.checkFuel();
	private String playerName;
	
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
		
		backpack.addItem(oxygenTanks, 0);
		backpack.addItem(healthPacks, 1);
		backpack.addItem(alienRepellent, 2);
		backpack.addItem(humanRepellent, 3);
		backpack.addItem(spaceTunes, 4);
		backpack.addItem(spaceArmor, 5);
		backpack.addItem(blasterGuns, 6);
		backpack.addItem(roboShipCrew, 7);
		backpack.addItem(shipRepairKits, 8);
		backpack.addItem(magicSpaceGrass, 9);
		backpack.addItem(ships, 10);
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
}