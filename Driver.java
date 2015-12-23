/*
*     Kyle Demers 'AKA' t0tallyKy1e
*     Revisions began on: 04/23/2014
*
**    spaceAdventures
**	  a space adventure, survival, text-based game. The user has the ability to fight
**	  enemies, visit planets, and make a lot of money(Shards).
*/

public class Driver
{
	public static void main (String[] args)
	{
		Game spaceAdventures = new Game();
		spaceAdventures.gameSetup();
		spaceAdventures.welcomeMessage();
		spaceAdventures.welcomeMenu();
	}
}