/*
*     Kyle Demers 'AKA' t0tallyKy1e
*     Revisions began on: 04/23/2014
*
**    spaceAdventures
**	  A space adventure game. Starting with a little bit of seed money, a user can buy
**    and sell items at the daily market. The user is also given rocket fuel to travel
**    between planets and attack enemies. Each day market prices change to tempt the user
**    to buy when prices are low and sell when prices are high.
*/

public class Driver{
	public static void main (String[] args){
		Game spaceAdventures = new Game();
		spaceAdventures.gameSetup();
		spaceAdventures.welcomeMessage();
		spaceAdventures.welcomeMenu();
	}
}//end of Driver