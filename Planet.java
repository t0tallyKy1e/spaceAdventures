//add enemies
//add unique item
public class Planet
{
	private String name;
	private int tripCost;
	private String info;
	private Inventory planetInventory = new Inventory();
	private EnemyList enemies = new EnemyList();
	
	/*
		Constructor #1
		pre: requires a name for the planet
		pre: requires an int for the cost of the trip to the planet
	*/
	public Planet(String name, int tripCost)
	{
		this.name = name;
		this.tripCost = tripCost;
	}//end of Planet Constructor #1
	
	/*
		Constructor #2
		pre: requires a String for the name of the planet
	*/
	public Planet(String name)
	{
		this.name = name;
	}//end of Planet Constructor #2
	
	/*
		Constructor #3
		requires nothing to function properly
	*/
	public Planet()
	{
	}//end of Planet Constructor #3
	
	/*
		sets the name of the planet
		pre: requires a String for the new name of the planet
	*/
	public void setPlanetName(String name)
	{
		this.name = name;
	}//end of setPlanetName
	
	/*
		gets the name of the planet
		post: returns a String of the Planet name
	*/
	public String getPlanetName()
	{
		return name;
	}//end of getPlanetName
	
	/*
		pre: requires a name for the enemy
		pre: requires an int for the hit points of the enemy
	*/
	public void addEnemy(Enemy enemy)
	{
		enemies.addEnemy(enemy);
	}//end of addEnemy
	
	/*
		pre: requires an Item to add
		pre: reuires an index where you'd like to place the item
	*/
	public void addUniqueItem(Item uniqueItem)
	{
		planetInventory.addItem(uniqueItem);
	}//end of addUniqueItem
	
	/*
		pre: requires an int of the index of the Item you would like to get
		post: returns the item at the index provided
	*/
	public Item getUniqueItem()
	{
		return planetInventory.getItem(0);
	}//end of getUniqueItem
	
	/*
		gets the cost of the trip
		post: returns an int of the trip cost
	*/
	public int getTripCost()
	{
		return tripCost;
	}//end of getTripCost
	
	/*
		adds a description to the planet
		pre: requires a String of info
	*/
	public void addPlanetInfo(String info)
	{
		this.info = info;
	}//end of addPlanetInfo
	
	/*
		gets the description of the planet
		post: returns a String of the planet's description
	*/
	public String getPlanetInfo()
	{
		return info;
	}//end of getPlanetInfo
	
	/*
		post: returns an Enemy
	*/
	public Enemy getEnemy()
	{
		int tempIndex;
		int max = enemies.getSize();
		int min = 0;
		int range = max - min;
		int offset;
		if (min > max)
		{
			max = min;
			offset = max;
		}
		else
		{
			offset = min;
		}
		
		tempIndex = (int) (Math.random() * range) + offset;
		return enemies.getEnemy(tempIndex);
	}//end of getEnemy
}//end of Planet