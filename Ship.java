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
		fuelTank.addItem(rocketFuel, 0);
	}//end of Ship Constructor #1
	
	public void setName(String shipName)
	{
		this.shipName = shipName;
	}//end of setName
	
	public String getName()
	{
		return shipName;
	}//end of setName
	
	public void addFuel(int fuelAmount)
	{
		Item fuelItem = fuelTank.getItem(0);
		currentFuel = currentFuel + fuelAmount;
		fuelItem.setStock(currentFuel);
	}//end of addFuel
	
	public void useFuel(int fuelAmount)
	{
		int newFuelAmount = currentFuel - fuelAmount;
		rocketFuel.setStock(fuelAmount);
	}//end of useFuel
	
	public int checkFuel()
	{
		return currentFuel;
	}//end of checkFuel
	
	public void addItem(Item item, int index)
	{
		fuelTank.addItem(item, index);
	}//end of addItem
	
	/*
		gets an item at a certain index
		pre: provide an item
		pre: an int for the index is required
		post: returns the Item at the index given
	*/
	public Item getItem(int index)
	{
		return fuelTank.getItem(index);
	}//end of getItem
}//end of Ship class