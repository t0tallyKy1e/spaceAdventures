import java.util.ArrayList;

public class PlanetList
{
	private ArrayList<Planet> planets;
	
	public PlanetList()
	{
		planets = new ArrayList<Planet>(0);
	}
	
	/*
		adds a planet to the list
		pre: requires a Planet to add to the list
	*/
	public void addPlanet(Planet tempPlanet)
	{
		planets.add(tempPlanet);
	}//end of addPlanet
	
	/*
		prints out the list of planets
	*/
	public void viewPlanets()
	{
		int listLength = planets.size();
		System.out.println(listLength);
		for(int i = 0; i < listLength; i++)
		{
			Planet tempPlanet = planets.get(i);
			String tempName = tempPlanet.getPlanetName();
			System.out.println(tempName);
		}
	}//end of viewPlanets
	
	/*
		pre: an int is required to choose which Planet you want
		post: returns a specific Planet
	*/
	public Planet getPlanet(int index)
	{
		return planets.get(index);
	}//end of getPlanet
	
	/*
		post: returns an int of the size of the PlanetList
	*/
	public int getSize()
	{
		return planets.size();
	}//end of getSize
}//end of PlanetList