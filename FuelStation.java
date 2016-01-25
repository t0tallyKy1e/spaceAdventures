public class FuelStation{
	private Inventory fuelInventory = new Inventory();
	private Item rocketFuel = new Item("Rocket Fuel", 1.0, 10.0);
	
	/*
		requires nothing to work properly
	*/
	public FuelStation(){
		fuelInventory.addItem(rocketFuel);
	}//end of FuelStation Constructor #1
	
	/*
		adds an Item to the warehouse
		pre: requires an Item to add
		pre: requires an int for the index location of the Item in the array
	*/
	public void addItem(Item item){
		fuelInventory.addItem(item);
	}//end of addItem
	
	/*
		gets an item at a certain index
		pre: provide an item
		pre: an int for the index is required
		post: returns the Item at the index given
	*/
	public Item getItem(int index){
		return fuelInventory.getItem(index);
	}//end of getItem
}//end of FuelStation