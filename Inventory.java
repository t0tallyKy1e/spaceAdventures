import java.util.ArrayList;

/*
*     Kyle Demers 'AKA' t0tallyKy1e
*     Revisions began on: 04/23/2014
*
**    spaceAdventures - Inventory class
*/

public class Inventory{
	private int size = 11;
 	private ArrayList<Item> items = new ArrayList<Item>(size);
	
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
	
	public void Inventory(){
		addItem(oxygenTanks);
		addItem(healthPacks);
		addItem(alienRepellent);
		addItem(humanRepellent);
		addItem(spaceTunes);
		addItem(spaceArmor);
		addItem(blasterGuns);
		addItem(roboShipCrew);
		addItem(shipRepairKits);
		addItem(magicSpaceGrass);
		addItem(ships);
	}
	
	/*
		adds an item to the array
		pre: requires an Item to be added
		pre: requires an int for the index of the Item
	*/
	public void addItem(Item item){
		items.add(item);
	}//end of addItem
	
	/*
		gets an item
		pre: must include the array index of the item you would like
		post: returns an Item at a certain index in the items array
	*/
	public Item getItem(int index){
		return items.get(index);
	}//end of getItem
	
	/*
		gets the size of the inventory
		post: returns an int of the size
	*/
	public int getSize(){
		return items.size();
	}//end of getLength
}//end of Inventory