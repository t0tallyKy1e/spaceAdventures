import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

/*
*     Kyle Demers 'AKA' t0tallyKy1e
*     Revisions began on: 04/23/2014
*
**    spaceAdventures - Game class
*/

public class Game
{
	private final double SEED_MONEY = 20;
	private final double VERY_LOW_STOCK = .6;
	private final double LOW_STOCK = .4;
	private final double MED_STOCK = .2;
	private final int FULL_LINE = 126;
	
	private double wallet;
	private int current = 0;
	private int day = 1;
	private int userInt;
	private int userSelection;
	
	private Market spaceMarket = new Market();
	private Planet currentPlanet = new Planet("HQ");
	
	private Player player = new Player();
	private Ship playerShip = player.getShip();
	
	private Attack playerKick = new Attack("Kick", 7);
	private Attack playerPunch = new Attack("Punch", 4);
	private Attack playerTackle = new Attack("Tackle", 9);
	private Attack playerBite = new Attack("Bite", 5);
	
	private FuelStation fuelStation = new FuelStation();
	
	private PlanetList planets = new PlanetList();
	private Planet iceCave;
	private Planet volcanus;
	private int planetInt;
	
	//iceCave Enemies
	//---yeti
	private Enemy yeti = new Enemy("Yeti");
	private Item yetiFur = new Item("Yeti Fur", randomInt(1,15));
	//----yeti attacks
	private Attack spinKick = new Attack("Spin Kick", 8);
	private Attack stomp = new Attack("Stomp", 10);
	private Attack hammerPunch = new Attack("Hammer Punch", 5);
	private Attack bodySlam = new Attack("Body Slam", 20);
	//---iceFang
	private Enemy iceFang = new Enemy("Ice Fang");
	private Item fangs = new Item("Fangs", randomInt(1, 5));
	//----iceFang attacks
	private Attack chomp = new Attack("Chomp", 10);
	private Attack headButt = new Attack("Head Butt", 5);
	private Attack scratch = new Attack("Scratch", 5);
	private Attack lick = new Attack("Lick", 2);
	private Item iceCube = new Item("Ice Cube", 50.0, 200.0);
	
	private Enemy tempEnemy;
	
	private Scanner input = new Scanner(System.in);
	private String userString = "";
	private String saveHere = "";
	private String shipName = "";
	private String currentString = "";
	private int maximumItems = player.getSize();
	
	private final int LOTTERY_MIN_JACKPOT = 100;
	private final int LOTTERY_MIN_NUM = 1;
	private final int LOTTERY_MAX_NUM = 10;
	private final int LOTTERY_PRICE = 1;
	private final int LOTTERY_RESET = 1;
	private int lotteryCount = 1;
	private double lotteryJackpot = 100;
	
	/*
		sets up all needed aspects of the game
	*/
	public void gameSetup()
	{	
		setMarketStockAndPrice();
		addPlanets();
		player.addAttack(playerKick);
		player.addAttack(playerPunch);
		player.addAttack(playerTackle);
		player.addAttack(playerBite);
	}//end of gameSetup
	
	/*
		adds item to player and market
		pre: requires an Item to add
		pre: requires an int for the stock of the Player's Item
	*/
	public void addItem(Item tempItem)
	{
		player.addPlayerItem(tempItem);
		spaceMarket.addItem(tempItem);
	}//end of addItem
	
	/*
		adds new planet with enemies and unique items
	*/
	public void addPlanets()
	{
		iceCave = new Planet("Ice Cave", 5);
		volcanus = new Planet("Volcanus", 9);
		//-----add everything to yeti
		yeti.addAttack(spinKick);
		yeti.addAttack(stomp);
		yeti.addAttack(hammerPunch);
		yeti.addAttack(bodySlam);
		yeti.giveItem(yetiFur);
		
		//-----add everything to iceFang
		iceFang.addAttack(chomp);
		iceFang.addAttack(headButt);
		iceFang.addAttack(scratch);
		iceFang.addAttack(lick);
		iceFang.giveItem(fangs);
		//------add things to iceCave 
		iceCave.addEnemy(yeti);
		iceCave.addEnemy(iceFang);
		iceCave.addUniqueItem(iceCube);
		
		//volcanus Enemies
		Enemy flyingAnus = new Enemy("Flying Anus");
		
		planets.addPlanet(iceCave);
		iceCave.addPlanetInfo("\nWelcome to iceCave!\nThis is actually not a planet...it's just a black hole that leads to some sort of cave that is completely covered in ice\n...obviously. You will find the occasional Yeti, but the cave is mostly overrun with Ice Fangs, which are literally just\nsnow bunnies...\n");
		planets.addPlanet(volcanus);
		volcanus.addPlanetInfo("\nWelcome to volcanus!\nThis planet has an atmosphere made up entirely of some kind of element that smells terrible. The scent could be coming\nfrom the abundance of Flying Anuses that cover the planet, but no one can really be sure... Anyways... Have fun.\n");
	}//end of addPlanets
	
	/*
		- asks the user if they want to begin
		- prevents the user from entering anything but a form of yes
	*/
	public void beginPrompt()
	{
		System.out.println("Would you like to begin?");
		userString = input.next();
		switch (userString)
		{
			case "yes":
			case "y":
			case "Y":
			case "YES":
				firstDay();
				break;
			case "no":
			case "NO":
			case "n":
			case "N":
				System.out.println("Suit yourself, friend, have a nice day.");
				break;
			default:
				System.out.println("Something went wrong, " + player.getPlayerName());
				beginPrompt();
				break;
		}
	}//end of beginPrompt
	
	/*
		allows the user to buy fuel
	*/
	public void buyFuel()
	{
		Item fuelItem = fuelStation.getItem(0);
		
		//format printing to make a text version of a "GUI"
		print("_", 68, false, " ", "front");
		print("_", 38, true, "                  ", "front");
		print(" ", 27, false, "|", "front");
		System.out.print(" Pump 'n Run  ");
		print(" ", 27, false, "|", "back");
		print(" ", 15, false, "                |", "front");
		System.out.print("Backpack");
		print(" ", 15, true, "|", "back");
		print("‾", 68, false, "|", "front and back");
		System.out.print("                ");
		print("‾", 38, true, "|", "front and back");
		print("Item", 1, false, "| ", "front");
		print(" ", 30, false, "Price (§)", "back");
		print(" ", 18, false, "Stock |", "back");
		print(" ", 16, false, "| Item", "back");
		print(" ", 27, true, "Stock |", "back");
		print("‾", 68, false, " ", "front");
		print("‾", 38, true, "                  ", "front");
		
		System.out.print("  Rocket Fuel ");
		System.out.print("                      §");
	
		if (fuelItem.getPrice() < 10000 && fuelItem.getPrice() >= 1000){
			System.out.print(" ");
		}
		else if (fuelItem.getPrice() < 1000 && fuelItem.getPrice() >= 100){
			System.out.print("  ");
		}
		else if (fuelItem.getPrice() < 100 && fuelItem.getPrice() >= 10){
			System.out.print("   ");
		}
		else if (fuelItem.getPrice() < 10 && fuelItem.getPrice() >= 1){
			System.out.print("    ");
		}
		System.out.printf("%.2f ", fuelItem.getPrice());
		System.out.print("                                 ");
			
		for(int i = 0; i < maximumItems; i++)
		{
			if(i > 0){
				System.out.print("                                                                               ");
			}//close if
			String indexString = "" + i;
			Item current = spaceMarket.getItem(i);
			int currentStock = current.getStock();
			String stockString = Integer.toString(currentStock);
		
			String newName = current.getName();
			
			current = player.getItem(i);
			if(current.getStock() != 0)
			{	
				indexString = "" + i;
				System.out.print("        [");
			
				if(indexString.length() == 1)
				{
					indexString = " " + indexString + " ";
				}
				else if(indexString.length() == 2)
				{
					indexString = " " + indexString;
				}
			
				System.out.print(indexString);
				System.out.print("] ");
			
				//print appropriate spaces after item name
				if(current.getName().length() < 27)
				{
					newName = current.getName();
					int additionalCharacters = 27 - newName.length();
				
					while(newName.length() < 27)
					{
						newName = newName + " ";
					}
					System.out.print(newName);
				}
				//print appropriate space before stock
				currentStock = current.getStock();
				stockString = Integer.toString(currentStock);
				while(stockString.length() < 4)
				{
					stockString = " " + stockString;
				}
				System.out.print(stockString);
				skipLine();
			}
		}
		skipLine();
		
		print(" ", 80, false, "Wallet: ", "back");
		System.out.printf("§%.2f\n", player.getWallet());
		int shipLength = playerShip.getName().length();
		shipLength = 79 - shipLength;
		print(" ", shipLength, false, "", "none");
		System.out.print(playerShip.getName() + "'s Fuel: " + playerShip.checkFuel() + "b\n");
		
		System.out.println("How many buckets of " + fuelItem.getName() + " would you like to buy?");
		userSelection = input.nextInt();
		double price = fuelItem.getPrice() * userSelection;
		
		if(player.getWallet() < price){
			System.out.println("I'm sorry but you don't have enough money.");
			buyFuel();
		}
		else if(player.getWallet() > price){
			player.removeMoney(price);
			playerShip.addFuel(userSelection);
		}
	}//end of buyFuel
	
	/*
		allows the user to buy an item
	*/
	public void buyItem()
	{
		Item marketItem;
		Item userItem;
		Item tempItem;
		
		double itemPrice;
		double newWalletAmount;
		double playerWallet = player.getWallet();
		double totalCost;
		
		int itemStock;
		int marketIndex;
		int newStock;
		
		//prompt user for which item they would like to purchase
		System.out.println("Which item would you like to buy?");
		userSelection = input.nextInt();
		
		tempItem = spaceMarket.getItem(userSelection);
		
		while(userSelection > maximumItems)
		{
			System.out.println("That item does not exist. Please pick one of the items for sale.");
			//prompt user for which item they would like to purchase
			System.out.println("Which item would you like to buy?");
			userSelection = input.nextInt();
			tempItem = spaceMarket.getItem(userSelection);
		}
		
		//check if choice is valid
		
		//----price is too expensive
		while(tempItem.getPrice() > playerWallet)
		{
			System.out.println("You currently can't afford any of that item.");
			//prompt user for which item they would like to purchase
			System.out.println("Which item would you like to buy?");
			userSelection = input.nextInt();
			tempItem = spaceMarket.getItem(userSelection);
		}
		
		//create temporary items for transaction
		marketItem = spaceMarket.getItem(userSelection);
		itemStock = marketItem.getStock();
		itemPrice = marketItem.getPrice();
		userItem = player.getItem(userSelection);
		
		//prompt user for amount they would like to buy
		System.out.println("How many " + marketItem.getName() + " would you like to buy?");
		userSelection = input.nextInt();
		
		//----there aren't enough of that item
		while(userSelection > itemStock)
		{
			System.out.println("We currently do not have enough of that item in stock.");
			System.out.println("Please enter another amount.");
			userSelection = input.nextInt();
		}
		
		//----stock choice is not high enough
		while(userSelection < 1)
		{
			System.out.println("It seems the number you entered is not high enough.");
			System.out.println("Please choose another item.");
			userSelection = input.nextInt();
		}
		
		totalCost = userSelection * itemPrice;
		
		//check if the user has enough money
		while (totalCost > playerWallet)
		{
			System.out.println("You don't have enough money, " + player.getPlayerName());
			System.out.println("How many " + marketItem.getName() + " would you like to buy?");
			
			userSelection = input.nextInt();
			totalCost = userSelection * itemPrice;
		}
		
		newStock = userItem.getStock() + userSelection;
		
		player.removeMoney(totalCost);
		userItem.setStock(newStock);
	}//end of buyItem
	
	/*
		gives the user all of the options for each day
	*/
	public void dailyMenu()
	{
		Item marketItem;
		Item playerItem;
		Item fuelItem = fuelStation.getItem(0);
		
		int buyCount = 0;
		int sellCount = 0;
		int randomCount = 0;
		
		double fuelPrice = fuelItem.getPrice();
		double playerWallet = player.getWallet();
		
		System.out.println("What would you like to do today, " + player.getPlayerName() + "?");
		print("-", FULL_LINE, true,"", "none");
		System.out.println("[ 0 ]  Skip This Day");
		System.out.println("[ 1 ]  Buy Items");
		System.out.println("[ 2 ]  Sell Items");
		System.out.printf("[ 3 ]  Buy Fuel (Currently §%.2f per bucket)\n",fuelPrice);
		System.out.println("[ 4 ]  Visit Planet");
		
		if(day % 9 == 0)
		{
			System.out.println("[ 9 ]  Play The Lottery (§1 to play)");
		}
		System.out.println("[999]  Quit game");
		userInt = input.nextInt();
		
		//check if the user can sell an item
		boolean canSell = false;
		for(int i = 0; i < maximumItems; i++)
		{
			playerItem = player.getItem(i);
			
			if(canSell == false)
			{
				if(playerItem.getStock() > 0)
				{
					canSell = true;
				}
				else
				{
					canSell = false;
				}
			}
		}
		
		//check if the user can buy fuel
		fuelItem = fuelStation.getItem(0);
		fuelPrice = fuelItem.getPrice();
		if(playerWallet < fuelItem.getPrice() && userInt == 3)
		{
			System.out.println("You don't have enough shards to buy fuel... Hopefully you're not stranded.");
			dailyMenu();
		}
		
		//check if the user can play the lottery
		if(playerWallet < 1 && userInt == 9)
		{
			System.out.println("You don't have enough shards to play the lottery");
			dailyMenu();
		}
		else if(playerWallet >=1 && userInt == 9 && day % 9 != 0)
		{
			System.out.println("I see you memorized the menu... You can't do that today.");
			dailyMenu();
		}
		
		//complete the user's action
		if(userInt == 0)
		{
			//do nothing but increment day, which happens in dailyMessage()
		}
		//----user can buy an item
		else if(userInt == 1)
		{
			buyItem();
		}
		//----user can't buy an item
		else if(userInt == 1)
		{
			System.out.println("You don't have enough shards to buy anything today. Try something else.");
			dailyMenu();
		}
		//----user can sell
		else if(userInt == 2 && canSell == true)
		{
			sellItem();
		}
		//----user can't sell
		else if(userInt == 2 && canSell == false)
		{
			System.out.println("You don't have any items to sell, today. Try something else.");
			dailyMenu();
		}
		//----buy fuel
		else if(userInt == 3 && player.getWallet() >= fuelStation.getItem(0).getPrice())
		{
			buyFuel();
		}
		//----user can't buy fuel
		else if(userInt == 3 && player.getWallet() < fuelStation.getItem(0).getPrice())
		{
			System.out.println("You can't afford fuel today. Try something else.");
			dailyMenu();
		}
		//----visit planet
		else if(userInt == 4 && (playerShip.checkFuel() >= volcanus.getTripCost()))
		{
			visitPlanetPrompt();
		}
		else if(userInt == 4 && (playerShip.checkFuel() >= iceCave.getTripCost()))
		{
			visitPlanetPrompt();
		}
		//----user can't visit planet
		else if(userInt == 4 && (playerShip.checkFuel() < iceCave.getTripCost()))
		{
			System.out.println("You don't have enough fuel to travel right now");
			dailyMenu();
		}
		//----lottery
		else if(userInt == 9 && playerWallet >= 1)
		{
			lottery();
		}
		//----quit game
		else if(userInt == 999)
		{
			quitGame();
		}
		//----user didn't choose anything on the menu
		else
		{
			System.out.println("You didn't choose any of the options... what a rebel. Please pick something this time.");
			dailyMenu();
		}
	}//end of dailyMenu
	
	/*
		daily routine
	*/
	public void dailyMessage()
	{
		while (userInt != 999)
		{
			day++;
			setMarketStockAndPrice();
			printMarketAndPlayerInfo();
			dailyMenu();
			maximumItems = player.getBackpackSize();
			saveGame();
		}
	}//end of dailyMessage
	
	/*
		Allows the Enemy to attack
		post: returns the damage points as an int
	*/
	public int enemyAttack()
	{
		Attack tempAttack = tempEnemy.useAttack();
		int damage = tempAttack.getDamagePoints();
		System.out.println(tempEnemy.getName() + " used " + tempAttack.getAttackName() + ".");
		return damage;
	}//end of enemyAttack
	
	/*
		allows the Player to fight a random Enemy on the Planet they are currently on
	*/
	public void fightEnemy()
	{
		tempEnemy = planets.getPlanet(planetInt).getEnemy();
		System.out.println("You are about to fight a " + tempEnemy.getName() + ".");
		System.out.println("You will now do a coin toss to see who goes first.");
		System.out.println("Please pick either side 1 or side 2 (Enter just the number)");
		int winningSide = randomInt(1, 2);
		int userSide = input.nextInt();
		while(userSide > 2 || userSide < 1)
		{
			System.out.println("Invalid entry. Please try again.");
		}
		if (userSide == winningSide)
		{
			System.out.println("You will go first.");
			while(player.getPlayerHealth() > 0 && tempEnemy.getHealth() > 0)
			{
				healthBanner();
				int damagePoints = playerAttack();
				System.out.println(tempEnemy.getName() + " recevied " + damagePoints + " points of damage." + "\n");
				tempEnemy.removeHealth(damagePoints);
				if (tempEnemy.getHealth() > 0)
				{
					damagePoints = enemyAttack();
					System.out.println("You received " + damagePoints + " points of damage." + "\n");
					player.removeHealth(damagePoints);
				}
			}
		}
		else if(userSide != winningSide)
		{
			System.out.println("You lost the coin toss. " + tempEnemy.getName() + " will go first.");
			while(player.getPlayerHealth() > 0 && tempEnemy.getHealth() > 0)
			{
				healthBanner();
				int damagePoints = enemyAttack();
				System.out.println("You received " + damagePoints + " points of damage." + "\n");
				player.removeHealth(damagePoints);
				if(player.getPlayerHealth() > 0)
				{
					damagePoints = playerAttack();
					System.out.println(tempEnemy.getName() + " recevied " + damagePoints + " points of damage." + "\n");
					tempEnemy.removeHealth(damagePoints);
				}
			}
		}
	}//end of fightEnemy
	
	/*
		Check if the user is finished with whatever they were doing
	*/
	public void finishedPrompt()
	{
		System.out.println("Press [ 0 ] to continue.");
		print("-", FULL_LINE, true,"", "none");
		userInt = input.nextInt();
	}//end of finishedPrompt
	
	/*
		starts the first day without incrementing the day
	*/
	public void firstDay()
	{
		setMarketStockAndPrice();
		printMarketAndPlayerInfo();
		dailyMenu();
		saveGame();
		dailyMessage();
	}//end of firstDay
	
	/*
		prints a line of a certain symbol
		pre: a string to be printed
		pre: an int for the amount of times you want to print the string
		pre: a string for any additional strings you would like to add on once
		pre: a string for the placement of the additional string
		post: returns a string of the symbol provided
	*/
	public String getSymbolString(String symbol, int amount, String additionalString, String placement)
	{
		String symbolString = symbol;
		
		for(int count = 1; count < amount; count++)
		{
			symbolString = symbolString + symbol;
		}
		
		if(placement == "front")
		{
			symbolString = additionalString + symbolString;
		}
		else if(placement == "back")
		{
			symbolString = symbolString + additionalString;
		}
		else if(placement == "front and back")
		{
			symbolString = additionalString + symbolString + additionalString;
		}
		return symbolString;
	}//end of getSymbolString
	
	/*
		shows the Player's health and the Enemy's health
	*/
	public void healthBanner()
	{
		print("_", FULL_LINE - 2, true, " ", "front");
		String enemyBanner = "| " + tempEnemy.getName() + "'s Health: " + tempEnemy.getHealth();
		while(enemyBanner.length() < 63)
		{
			enemyBanner = enemyBanner + " ";
		}
		String playerBanner= player.getPlayerName() + "'s Health: " + player.getPlayerHealth() + " |";
		while(playerBanner.length() < 63)
		{
			playerBanner = " " + playerBanner;
		}
		System.out.println(enemyBanner + playerBanner);
		print("‾", FULL_LINE - 2, true, " ", "front");
	}//end of healthBanner
	
	/*
		Menu and options when a player lands on a planet
	*/
	public void landedOnPlanet()
	{
		int subMenu;
		System.out.println("\nWhat would you like to do while you're here?");
		print("-", FULL_LINE, true,"", "none");
		System.out.println("[ 0 ] Get Planet Info");
		System.out.println("[ 1 ] Fight An Enemy");
		System.out.println("[ 2 ] Explore For Items");
		System.out.println("[ 9 ] Go Back To Headquarters");
		subMenu = input.nextInt();
		if(subMenu == 0)
		{
			//print planet info
			String tempString = planets.getPlanet(planetInt).getPlanetInfo();
			System.out.println(tempString);
			finishedPrompt();
			if(userInt == 0)
			{
				landedOnPlanet();
			}
			else if(userInt == 1)
			{
				finishedPrompt();
				resetCurrentPlanet();
			}
		}
		else if(subMenu == 1)
		{
			fightEnemy();
			resetCurrentPlanet();
		}
		else if(subMenu == 2)
		{
			Item tempItem = currentPlanet.getUniqueItem();
			int randInt = randomInt(1, 25);
			tempItem.setStock(randInt);
			System.out.println("You search the planet thoroughly, avoid numerous attacks and manage to escape with " + randInt + " " + tempItem.getName() + "s. \n");			
			finishedPrompt();
			
			//add item to backpack and market
			player.addPlayerItem(tempItem);
			spaceMarket.addItem(tempItem);
			
			//set the prices and stock
			setMarketStockAndPrice();
			
			resetCurrentPlanet();
		}
		else if(subMenu == 9)
		{
			resetCurrentPlanet();
			//----charge fuel
			//----make the user get stranded if they don't have enough fuel
			//----allow the user to buy fuel if they are stranded
		}
	}//end of landedOnPlanet
	
	/*
		loads a previous game
	*/
	public void loadGame()
	{
		try
		{
			String userFile = "";
			String userName = "";
			String shipName = "";
			double wallet;
			Item current;
			int newStock;
			int fuel;
			
			System.out.println("What file would you like to load?");
			userFile = input.next();
			File file = new File(userFile);
			Scanner fileInput = new Scanner(file);
			
			while(fileInput.hasNextLine())
			{
				//name
				String dontUseCaptain = fileInput.next();
				userName = fileInput.next();
				player.setPlayerName(userName);
				saveHere = userName + ".txt";
				
				//day
				day = fileInput.nextInt();
				
				//wallet
				wallet = fileInput.nextDouble();
				player.addMoney(wallet);
				
				//get size of backpack
				maximumItems = player.getSize();
				
				//backpack
				for(int i = 0; i < maximumItems; i++)
				{
					newStock = fileInput.nextInt(); // <----------Problem here
					current = player.getItem(i);
					current.setStock(newStock);
				}
				shipName = fileInput.next();
				fuel = fileInput.nextInt();
				lotteryJackpot = fileInput.nextDouble();
				lotteryCount = fileInput.nextInt();
				String currentPlanetName = fileInput.next();
				playerShip.setName(shipName);
				playerShip.addFuel(fuel);
				currentPlanet.setPlanetName(currentPlanetName);
			}
			fileInput.close();
		}
		catch(IOException exception)
		{
			System.out.println("~ This is not the file you're looking for ~");
			loadGame();
		}
	}//end of loadGame

	/*
		lottery that shows up every 9 days
	*/
	public void lottery()
	{
		int magicNumber = randomInt(1, 10);
		player.removeMoney(1);
		skipLine();
		print("*", FULL_LINE, true, "", "none");
		print(" ", FULL_LINE - 2, true, "*", "front and back");
		System.out.println("*                                       *       **   *****  *****  *****  ***   *   *                                        *");
		System.out.println("*                                       *      *  *    *      *    *      *  *   * *                                         *");
		System.out.println("*                                       *      *  *    *      *    ***    ***     *                                          *");
		System.out.println("*                                       *      *  *    *      *    *      *  *    *                                          *");
		System.out.println("*                                       *****   **     *      *    *****  *   *   *                                          *");
		print(" ", FULL_LINE - 2 , true, "*", "front and back");
		print("*", FULL_LINE, true, "", "none");
		skipLine();
		System.out.printf("The current jackpot is §%.2f\n", lotteryJackpot);		
		skipLine();
		System.out.println("What do you think the magic number is today?");
		print("-", FULL_LINE, true,"", "none");
		System.out.println("(Choose a number between 1 and 10 inclusively)");
		userInt = input.nextInt();
		if(userInt == magicNumber){
			System.out.printf("\nCongratulations!! You won §%.2f!!\n", lotteryJackpot);
			player.addMoney(lotteryJackpot);
			lotteryJackpot = LOTTERY_MIN_JACKPOT;
			lotteryCount = LOTTERY_RESET;
			finishedPrompt();
		}
		else if(userInt != magicNumber){
			System.out.println("\nI'm sorry, you didn't win. Try again sometime.");
			System.out.println("The lottery number was: " + magicNumber);
			lotteryCount++;
			lotteryJackpot = lotteryJackpot * lotteryCount;
			finishedPrompt();
		}
		else if(userInt < LOTTERY_MIN_NUM){
			System.out.printf("The number you entered is too low. Try again.");
			lottery();
		}
		else if(userInt > LOTTERY_MAX_NUM)
		{
			System.out.printf("The number you entered is too high. Try again.");
			lottery();
		}
	}//end of lottery
	
	/*
		starts a fresh, new game 
	*/
	public void newGame()
	{
		System.out.println("Please enter your name, explorer.");
		userString = input.next();
		saveHere = userString + ".txt";
		player.setPlayerName(userString);
		
		System.out.println("What would you like to name your ship?");
		userString = input.next();
		playerShip.setName(userString);
		
		player.addMoney(SEED_MONEY);
		playerShip.addFuel(Ship.STARTING_FUEL);
		
		skipLine();
		System.out.println("Welcome " + player.getPlayerName() + ", you will be coming to this market every day in between your many space");
		System.out.println("expeditions to pick up new supplies. Each day you will be able to buy items, sell items, or skip the market all");
		System.out.println("together. Be careful when making your decision because the prices and stock of each item vary between each day.");
		System.out.println("This means something you need may not be in stock one day or something you do need may be out of price range. Pay");
		System.out.println("close attention to everything as something special may happen on certain days.");
		System.out.println("Good luck, " + player.getPlayerName() + "!");
		skipLine();
		String fuelName = playerShip.getItem(0);
		System.out.printf("Oh, and here's §%.2f (shards) and %d buckets of " + fuelName + " to get you started.\n", player.getWallet(), player.checkFuel());
		skipLine();
		System.out.println("The game will be automatically saved each day as " + "\"" + saveHere + "\".");
		skipLine();
		beginPrompt();
	}//end of newGame
	
	/*
		allows the user to atack
		post: returns the damage points as an int
	*/
	public int playerAttack()
	{
		System.out.println("Which attack would you like to use?");
		print("-", FULL_LINE, true,"", "none");
		player.viewAttacks();
		userInt = input.nextInt();
		Attack tempAttack = player.getAttack(userInt);
		System.out.println(player.getPlayerName() + " used " + tempAttack.getAttackName() + ".");
		return tempAttack.getDamagePoints();
	}//end of playerAttack
	
	/*
		simplifies the print line
		pre: a string to print
		pre: a boolean to decide whether or not to print a new line or on the same line
		pre: an int for the amount of time you would like to print the string on the line
		pre: a string for any additional strings you would like to add on once
		pre: a string for the placement of the additional string
	*/
	public void print(String string, int amount, boolean willPrintLine, String additionalString, String placement)
	{
		
		if(willPrintLine == true)
		{
			System.out.println(getSymbolString(string, amount, additionalString, placement));
		}
		else if(willPrintLine == false)
		{
			System.out.print(getSymbolString(string, amount, additionalString, placement));
		}
	}//end of print

	/*
		prints the header for the market and player "GUI"
	*/
	public void printHeader()
	{
		//format printing to make a text version of a "GUI"
		print("_", 68, false, " ", "front");
		print("_", 38, true, "                  ", "front");
		print(" ", 27, false, "|", "front");
		System.out.print("Ships 'n Stuff");
		print(" ", 27, false, "|", "back");
		print(" ", 15, false, "                |", "front");
		System.out.print("Backpack");
		print(" ", 15, true, "|", "back");
		print("‾", 68, false, "|", "front and back");
		System.out.print("                ");
		print("‾", 38, true, "|", "front and back");
		print("Item", 1, false, "| ", "front");
		print(" ", 30, false, "Price (§)", "back");
		print(" ", 18, false, "Stock |", "back");
		print(" ", 16, false, "| Item", "back");
		print(" ", 27, true, "Stock |", "back");
		print("‾", 68, false, " ", "front");
		print("‾", 38, true, "                  ", "front");
	}//end of printHeader
	
	/*
		print all market and player info
	*/
	public void printMarketAndPlayerInfo()
	{
		maximumItems = spaceMarket.getSize();
		print("_", FULL_LINE - 2, true, " ", "front"); 
		currentString = "| Current Day: " + day;
		while (currentString.length() < 62)
		{
			currentString = currentString + " " ;
		}
		String planetNameString = "Current Planet: " + currentPlanet.getPlanetName() + " |";
		while(planetNameString.length() < 64)
		{
			planetNameString = " " + planetNameString;
		}
		System.out.println(currentString + planetNameString);
		print("‾", FULL_LINE - 2, true, " ", "front");
		printHeader();
		
		for(int i = 0; i < maximumItems; i++)
		{
			//----market info
			Item current = spaceMarket.getItem(i);
			//print index formatted with brackets
			String indexString = "" + i;
			System.out.print(" [");
			
			if(indexString.length() == 1)
			{
				indexString = " " + indexString + " ";
			}
			else if(indexString.length() == 2)
			{
				indexString = " " + indexString;
			}
			
			System.out.print(indexString);
			System.out.print("] ");
			
			//provide appropriate space between name and price
			String newName = current.getName();
			
			while(newName.length() < 29)
			{
				newName = newName + " ";
			}
			System.out.print(newName);
			
			//print appropriate spaces after § symbol
			System.out.print("§");
			String priceSpacer = " ";
			if (current.getPrice() < 10000 && current.getPrice() >= 1000)
			{
				System.out.print(" ");
			}
			else if (current.getPrice() < 1000 && current.getPrice() >= 100)
			{
				System.out.print("  ");
			}
			else if (current.getPrice() < 100 && current.getPrice() >= 10)
			{
				System.out.print("   ");
			}
			else if (current.getPrice() < 10 && current.getPrice() >= 1)
			{
				System.out.print("    ");
			}
			System.out.printf("%.2f ", current.getPrice());
			
			//print appropriate space before stock
			int currentStock = current.getStock();
			String stockString = Integer.toString(currentStock);
			
			while(stockString.length() < 22)
			{
				stockString = " " + stockString;
			}
			
			System.out.print(stockString + "           ");
			
			//----player backpack
			current = player.getItem(i);
			if(current.getStock() != 0)
			{	
				indexString = "" + i;
				System.out.print("        [");
				
				if(indexString.length() == 1)
				{
					indexString = " " + indexString + " ";
				}
				else if(indexString.length() == 2)
				{
					indexString = " " + indexString;
				}
				
				System.out.print(indexString);
				System.out.print("] ");
				
				//print appropriate spaces after item name
				if(current.getName().length() < 27)
				{
					newName = current.getName();
					int additionalCharacters = 27 - newName.length();
					
					while(newName.length() < 27)
					{
						newName = newName + " ";
					}
					System.out.print(newName);
				}
				//print appropriate space before stock
				currentStock = current.getStock();
				stockString = Integer.toString(currentStock);
				while(stockString.length() < 4)
				{
					stockString = " " + stockString;
				}
				System.out.print(stockString);
			}
			skipLine();
		}
		skipLine();
		
		Item fuelItem = fuelStation.getItem(0);
		
		print(" ", 80, false, "Wallet: ", "back");
		System.out.printf("§%.2f\n", player.getWallet());
		int shipLength = playerShip.getName().length();
		shipLength = 79 - shipLength;
		print(" ", shipLength, false, "", "none");
		System.out.print(playerShip.getName() + "'s Fuel: " + playerShip.checkFuel() + "b\n");
	}//end of printMarketAndPlayerInfo
	
	/*
		outputs a message for the user when they quit and tells them how they did.
	*/
	public void quitGame()
	{
		Item current;
		skipLine();
		print("*", FULL_LINE, true, "", "none");
		if (player.getWallet() > 20)
		{
			double profit = player.getWallet() - 20;
			System.out.printf("You made §%.2f", profit );
		}
		else if (player.getWallet() == 20)
		{
			System.out.println("Congratulations!!! You did nothing!");
		}
		else
		{
			double loss = 20 - player.getWallet();
			System.out.printf("You lost §%.2f", loss);
		}
		System.out.println();
		System.out.println("You had the following items in your backpack:");
		System.out.println(" ______________________________________");
		System.out.println("|               Backpack               |");
		System.out.println("|‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾|");
		System.out.println("| Item                           Stock |");
		System.out.println(" ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾");
		
		for(int i = 0; i < maximumItems; i++)
		{
			current = player.getItem(i);
			if(current.getStock() != 0)
			{	
				String indexString = "" + i;
				System.out.print(" [");
				
				if(indexString.length() == 1)
				{
					indexString = " " + indexString + " ";
				}
				else if(indexString.length() == 2)
				{
					indexString = " " + indexString;
				}
				
				System.out.print(indexString);
				System.out.print("] ");
				
				//print appropriate spaces after item name
				if(current.getName().length() < 28)
				{
					String newName = current.getName();
					int additionalCharacters = 28 - newName.length();
					
					while(newName.length() < 28)
					{
						newName = newName + " ";
					}
					System.out.print(newName);
				}
				
				//print appropriate space before stock
				int currentStock = current.getStock();
				String stockString = Integer.toString(currentStock);
				while(stockString.length() < 4)
				{
					stockString = "  " + stockString + "\n";
				}
				
				System.out.print(stockString);
			}
		}
		skipLine();
		skipLine();
		System.out.println("Thank you for playing, " + player.getPlayerName() + "!");
		skipLine();
	}//end of quitGame
	
	/*
		generates a random integer
		pre: requires an integer for the start
		pre: requires an integer for the finish
	*/
	public int randomInt(int start, int finish)
	{
		int range = finish - start + 1;
		int offset;
		
		if (range < 0)
		{
			range = range * -1;
			offset = finish;
		}
		else
		{
			offset = start;
		}
		int randomInt = (int) (Math.random() * range) + offset;
		return randomInt;
	}//end of randomInt
	
	/*
		changes the current planet to HQ
	*/
	public void resetCurrentPlanet()
	{
		currentPlanet.setPlanetName("HQ");
	}//end of resetCurrentPlanet

	/*
		saves the game to a text file of the user's name
	*/
	public void saveGame()
	{
		//Something is going wrong with saving items that have a stock of 0
		try{
			File saveFile = new File(saveHere);
			PrintWriter fileOutput = new PrintWriter(saveFile);
			fileOutput.println(player.getPlayerName());
			fileOutput.println(day);
			fileOutput.printf("%.2f\n", player.getWallet());
			
			//get size of backpack
			maximumItems = player.getSize();
			
			for(int i = 0; i < maximumItems; i++){
				Item current = player.getItem(i);
				
				if(i < maximumItems - 1){
					fileOutput.println(current.getStock());
				}//close if
				else{
					fileOutput.print(current.getStock());
				}//close else
			}//close for
			fileOutput.println();
			fileOutput.println(playerShip.getName());
			fileOutput.println(player.checkFuel());
			fileOutput.println(lotteryJackpot);
			fileOutput.println(lotteryCount);
			fileOutput.print(currentPlanet.getPlanetName());
			fileOutput.close();
		}//close try
		catch(IOException exception){
			System.out.println("There is no file to save.");
		}//close catch
	}//end of saveGame
	
	/*
		allows the user to sell items
	*/	
	public void sellItem()
	{
		Item marketItem;
		Item userItem;
		Item tempItem;
		
		double itemPrice;
		double newWalletAmount;
		double playerWallet = player.getWallet();
		double totalProfit;
		
		int itemStock;
		int newStock;
		
		//prompt user for which item they would like to sell
		System.out.println("Which item would you like to sell?");
		userSelection = input.nextInt();
		
		while(userSelection > maximumItems)
		{
			System.out.println("That item does not exist. Please pick one of the items that is actually for sale.");
			//prompt user for which item they would like to sell
			System.out.println("Which item would you like to sell?");
			userSelection = input.nextInt();
		}
		
		//create a temporary item to test if the user choice is in their backpack
		tempItem = player.getItem(userSelection);
		
		//----check if choice is valid
		while(tempItem.getStock() < 1)
		{
			System.out.println("You currently have none of those to sell.");
			System.out.println("Please choose another item.");
			userSelection = input.nextInt();
			tempItem = player.getItem(userSelection);
		}
		
		//create temporary items for transaction
		marketItem = spaceMarket.getItem(userSelection);
		userItem = player.getItem(userSelection);
		itemStock = userItem.getStock();
		itemPrice = marketItem.getPrice();
		
		//prompt user for amount they would like to buy
		System.out.println("How many " + userItem.getName() + " would you like to sell?");
		userSelection = input.nextInt();
		
		while(userSelection > itemStock)
		{
			System.out.println("You currently do not have that many of that item.");
			System.out.println("Please choose another amount.");
			userSelection = input.nextInt();
		}
		
		while(userSelection < 1)
		{
			System.out.println("It seems the number you entered is not high enough.");
			System.out.println("Please enter another amount.");
			userSelection = input.nextInt();
		}
		
		totalProfit = userSelection * itemPrice;
		newStock = itemStock - userSelection;
		
		player.addMoney(totalProfit);
		userItem.setStock(newStock);
	}//end of sellItem
	
	/*
		Randomly sets stock and price for each random item in the market
	*/
	public void setMarketStockAndPrice(){
		maximumItems = player.getSize();
		for(int i = 0; i < maximumItems; i++)
		{
			Item current = spaceMarket.getItem(i);
			double newPrice;
			
			current.setStock(current.getRandomStock());
			current.setPrice(current.getRandomPrice());
			
			int currentStock = current.getStock();
			
			//randomize price but add to the price if the stock is low
			if(currentStock < 10)
			{
				newPrice = current.getPrice() * VERY_LOW_STOCK + current.getPrice();
				current.setPrice(newPrice);
			}
			else if(currentStock >= 10 && currentStock < 40)
			{
				newPrice = current.getPrice() * LOW_STOCK + current.getPrice();
				current.setPrice(newPrice);
			}
			else if(currentStock >= 40 && currentStock < 60)
			{
				newPrice = current.getPrice() * MED_STOCK + current.getPrice();
				current.setPrice(newPrice);
			}
			
			//randomize fuel stock and price
			Item fuelItem = fuelStation.getItem(0);
			fuelItem.setStock(fuelItem.getRandomStock());
			int fuelStock = fuelItem.getStock();
			fuelItem.setPrice(fuelItem.getRandomPrice());
			double fuelPrice = fuelItem.getPrice();
			
			if(fuelStock < 10)
			{
				fuelPrice = fuelItem.getPrice() * VERY_LOW_STOCK + fuelItem.getPrice();
				fuelItem.setPrice(fuelPrice);
			}
			else if(fuelStock >= 10 && fuelStock < 40)
			{
				fuelPrice = fuelItem.getPrice() * LOW_STOCK + fuelItem.getPrice();
				fuelItem.setPrice(fuelPrice);
			}
			else if(fuelStock >= 40 && fuelStock < 60)
			{
				fuelPrice = fuelItem.getPrice() * MED_STOCK + fuelItem.getPrice();
				fuelItem.setPrice(fuelPrice);
			}
		}
	}//end of setMarketStockAndPrice
	
	/*
		prints a blank line
	*/
	public void skipLine()
	{
		System.out.println("");
	}//end of skipLine
	
	/*
		Allows the user to visit a planet
		----should list planets
		----charge fuel
		----should not be allowed if user has no fuel
	*/
	public void visitPlanetPrompt()
	{
		System.out.println("\nWhich planet would you like to travel to?");
		print("-", FULL_LINE, true,"", "none");
		
		System.out.println("Planets:");
		int listLength = planets.getSize();
		
		for(int i = 0; i < listLength; i++)
		{
			System.out.print("[ " + i + " ] ");
			String tempName = planets.getPlanet(i).getPlanetName() + " (Uses " + planets.getPlanet(i).getTripCost() + "b of Rocket Fuel)";
			System.out.println(tempName);
		}
		planetInt = input.nextInt();
		
		String tempName = planets.getPlanet(planetInt).getPlanetName();
		int tempTripCost = planets.getPlanet(planetInt).getTripCost();
		boolean notEnoughFuel = (player.checkFuel() - tempTripCost) < 0;
			
		if(notEnoughFuel == true)
		{
			System.out.println("I'm sorry, but you don't have enough fuel to go there.");
			visitPlanetPrompt();
		}
		else if(notEnoughFuel == false)
		{
			playerShip.useFuel(tempTripCost);
			currentPlanet = planets.getPlanet(planetInt);	
			landedOnPlanet();
		}
	}//end of visitPlanetPrompt
	
	/*
		Displays the menu after the welcome message
	*/
	public void welcomeMenu()
	{
		System.out.println("What would you like to do?");
		System.out.println("[ 1 ] Start a new game");
		System.out.println("[ 2 ] Load game");
		userInt = input.nextInt();
		if(userInt == 1)
		{
			newGame();
		}
		else if(userInt == 2)
		{
			loadGame();
			beginPrompt();
		}
		else
		{
			System.out.println("Something went wrong.");
			welcomeMenu();
		}
	}//end of welcomeMenu
	
	/*
		Display a welcome message for the user explaining the game
	*/
	public void welcomeMessage()
	{
		print("_", FULL_LINE - 2, true, " ", "front");
		print(" ", 49, false, "|", "front");
		print("Welcome to spaceAdventures", 1, false, "", "none");
		print(" ", 49, true, "|", "back");
		print("‾", FULL_LINE - 2, true, " ", "front");
		print("*", 41, false, " ", "back");
		System.out.print("For best results, play in full-screen mode");
		print("*", 41, true, " ", "front");
	}//end of welcomeMessage
}//end of Game