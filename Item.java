import java.lang.Math;

/*
*     Kyle Demers 'AKA' t0tallyKy1e
*     Revisions began on: 04/23/2014
*
**    spaceAdventures - Item class
*/

public class Item
{
	private double price;
	private double minimumPrice;
	private double maximumPrice;
	private int stock;
	private String name;
	
	private final int MINIMUM_STOCK = 0;
	private final int MAXIMUM_STOCK = 100;
	
	/*
		Item Constructor (Market)
		Requires:
				: String for a name
				: double for minimum price
				: double for maximum price
	*/
	public Item(String itemName, double itemMinimumPrice, double itemMaximumPrice)
	{
		name = itemName;
		minimumPrice = itemMinimumPrice;
		maximumPrice = itemMaximumPrice;
		
		//generate price and stock
		price = getPrice();
		stock = getStock();
	}//end of Market Item constructor #1
	
	/*
		Item Constructor (Stock)
		Requires:
				: String for a name
				: int for the stock
	*/
	public Item(String itemName, int stock)
	{
		name = itemName;
		this.stock = stock;
	}//end of Stock Item constructor #2
	
	/*
		pre: requires an Item name as a String
	*/
	public Item(String itemName)
	{
		name = itemName;
	}//end of Item constructor #3
	
	/*
		gets name of item
		post: returns a String of the name
	*/
	public String getName()
	{
		return name;
	}//end of getName
	
	/*
		sets the price of an item
		pre: requires a double for the new price of the Item
	*/
	public void setPrice(double newPrice)
	{
		price = newPrice;
	}//end of setPrice
	
	/*
		sets the stock of an item
		pre: requires an int for the new stock of the Item
	*/
	public void setStock(int newStock)
	{
		stock = newStock;
	}//end of setStock
	
	/*
		gets the price of an item
		post: returns a double of the price
	*/
	public double getPrice()
	{
		return price;
	}//end of getPrice
	
	/*
		gets the stock of an item
		post: returns the stock of the item as an int
	*/
	public int getStock()
	{
		return stock;
	}//end of getStock
	
	/*
		generates a random double for the price
		post: returns an double for the random price
	*/
	public double getRandomPrice()
	{
		double range = maximumPrice - minimumPrice + 1;
		double offset = minimumPrice;
	
		double randomPrice = (Math.random() * range) + offset;
	
		return randomPrice;
	}//end of getRandomPrice
	
	/*
		generates a random int for the stock
		post: returns an int for the random number
	*/
	public int getRandomStock()
	{
		int range = MAXIMUM_STOCK - MINIMUM_STOCK + 1;
		int offset = MINIMUM_STOCK;
	
		int randomStock = (int) (Math.random() * range) + offset;
	
		return randomStock;
	}//end of getRandomStock
}//end of Item