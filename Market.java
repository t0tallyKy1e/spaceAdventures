/*
*     Kyle Demers 'AKA' t0tallyKy1e
*     Revisions began on: 04/23/2014
*
**    spaceAdventures - Market class
*/

public class Market
{	
	private Inventory warehouse = new Inventory();
	
	private Item oxygenTanks = new Item("Portable Oxygen Tanks", 1.0, 8.0);
	private Item healthPacks = new Item("Health Packs", 5.0, 10.0);
	private Item alienRepellent = new Item("Alien Repellent", 10.0, 30.0);
	private Item humanRepellent = new Item("Human Repellent", 10.0, 30.0);
	private Item spaceTunes = new Item("Space Tunes", 10.0, 30.0);
	private Item spaceArmor = new Item("Space Armor", 30.0, 60.0);
	private Item blasterGuns = new Item("Blaster Guns", 50.0, 200.0);
	private Item roboShipCrew = new Item("Robo Crew Members", 50.0, 300.0);
	private Item shipRepairKits = new Item("Ship Repair Kits", 100.0, 500.0);
	private Item magicSpaceGrass = new Item("Magic Space Grass", 10.0, 500.0);
	private Item ships = new Item("Ships", 1000.0, 10000.0);
	
	/*
		Market Constructor
		fills the market with items
	*/
	public Market()
	{
		warehouse.addItem(oxygenTanks, 0);
		warehouse.addItem(healthPacks, 1);
		warehouse.addItem(alienRepellent, 2);
		warehouse.addItem(humanRepellent, 3);
		warehouse.addItem(spaceTunes, 4);
		warehouse.addItem(spaceArmor, 5);
		warehouse.addItem(blasterGuns, 6);
		warehouse.addItem(roboShipCrew, 7);
		warehouse.addItem(shipRepairKits, 8);
		warehouse.addItem(magicSpaceGrass, 9);
		warehouse.addItem(ships, 10);
		
	}//end of Market Constructor
	
	/*
		adds an Item to the warehouse
		pre: requires an Item to add
		pre: requires an int for the index location of the Item in the array
	*/
	public void addItem(Item item, int index)
	{
		warehouse.addItem(item, index);
	}//end of addItem
	
	/*
		gets an item at a certain index
		pre: provide an item
		pre: an int for the index is required
		post: returns the Item at the index given
	*/
	public Item getItem(int index)
	{
		return warehouse.getItem(index);
	}//end of getItem
}