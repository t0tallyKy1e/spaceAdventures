import java.util.ArrayList;

/*
*     Kyle Demers 'AKA' t0tallyKy1e
*     Revisions began on: 04/23/2014
*
**    spaceAdventures - Inventory class
*/

public class Inventory
{
	public static final int MAXIMUM_ITEMS = 11;
	public static final int MAXIMUM_RANDOM_ITEMS = 5;
	
	private Item[] items = new Item[MAXIMUM_ITEMS];
	
	//no constructor needed since this class just handles the array of items
	
	/*
		gets an item
		pre: must include the array index of the item you would like
		post: returns an Item at a certain index in the items array
	*/
	public Item getItem(int index)
	{
		return items[index];
	}//end of getItem
	
	/*
		adds an item to the array
		pre: requires an Item to be added
		pre: requires an int for the index of the Item
	*/
	public void addItem(Item item, int index)
	{
		items[index] = item;
	}//end of addItem
}