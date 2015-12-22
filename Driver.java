/*
*     Kyle Demers 'AKA' t0tallyKy1e
*     Revisions began on: 04/23/2014
*
**    spaceAdventures
**	  a buying and selling game. Starting with a little bit of seed money, a user can buy
**    and sell items at the daily market. Each day market prices change and the user can
**    either buy items or sell items. After a set number of days, the game ends.
*/

public class Driver
{
	public static void main (String[] args)
	{
		Game MoneyMaker = new Game();
		MoneyMaker.gameSetup();
		MoneyMaker.welcomeMessage();
		MoneyMaker.welcomeMenu();
	}
}