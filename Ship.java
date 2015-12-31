import java.util.Scanner;

/*
*     Kyle Demers 'AKA' t0tallyKy1e
*     Revisions began on: 04/23/2014
*
**    spaceAdventures - Ship class
*/

public class Ship
{
	Scanner input = new Scanner(System.in);
	
	public static final int STARTING_FUEL = 10;
	public static final int MAXIMUM_FUEL = 100;
	
	private int currentFuel = 0;
	private String shipName;
	
	private Item rocketFuel = new Item("Rocket Fuel", 0);
    Inventory fuelTank = new Inventory();
	
	
	public Ship()
	{
		fuelTank.addItem(rocketFuel);
	}//end of Ship Constructor #1
	
	/*
		takes in the ship's name
		pre: requires a String for the ship's name
	*/
	public void setName(String shipName)
	{
		this.shipName = shipName;
	}//end of setName
	
	/*
		post: returns the ship's name
	*/
	public String getName()
	{
		return shipName;
	}//end of setName
	
	/*
		adds fuel to the ship's tank
		pre: requires an int for the amount of fuel to add
	*/
	public void addFuel(int fuelAmount)
	{
		Item fuelItem = fuelTank.getItem(0);
		currentFuel = currentFuel + fuelAmount;
		fuelItem.setStock(currentFuel);
	}//end of addFuel
	
	/*
		removes fuel from the ship's fuel tank
		pre: requires an int for the amount of fuel to remove from the ship's tank
	*/
	public void useFuel(int fuelAmount)
	{
		Item fuelItem = fuelTank.getItem(0);
		currentFuel = currentFuel - fuelAmount;
		fuelItem.setStock(currentFuel);
	}//end of useFuel
	
	/*
		checks the ship's fuel
		post: returns the ship's fuel as an int
	*/
	public int checkFuel()
	{
		return currentFuel;
	}//end of checkFuel
	
	public String getItem(int index)
	{
		Item tempItem = fuelTank.getItem(index);
		return tempItem.getName();
	}
}//end of Ship class