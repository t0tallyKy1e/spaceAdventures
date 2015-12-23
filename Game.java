import java.util.Scanner;
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
	private final int FULL_LINE = 117;
	
	private double wallet;
	private int current = 0;
	private int day = 1;
	private int userInt;
	private int userSelection;
	
	private Market spaceMarket = new Market();
	private Player player = new Player();
	private Item[] randomItems = new Item[Inventory.MAXIMUM_RANDOM_ITEMS];
	private Ship playerShip = player.getShip();
	private FuelStation fuelStation = new FuelStation();
	
	private Item randomItem1;
	private Item randomItem2;
	private Item randomItem3;
	private Item randomItem4;
	private Item randomItem5;
	
	private int randomInt1;
	private int randomInt2;
	private int randomInt3;
	private int randomInt4;
	private int randomInt5;
	
	private Scanner input = new Scanner(System.in);
	private String userString = "";
	private String saveHere = "";
	private String shipName = "";
	
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
	}//end of gameSetup
	
	/*
		Randomly sets stock and price for each random item in the market
	*/
	public void setMarketStockAndPrice()
	{
		dailyRandomize();
		
		randomItems[0] = randomItem1;
		randomItems[1] = randomItem2;
		randomItems[2] = randomItem3;
		randomItems[3] = randomItem4;
		randomItems[4] = randomItem5;
		
		for(int i = 0; i < Inventory.MAXIMUM_RANDOM_ITEMS; i++)
		{
			Item current = randomItems[i];
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
		sets the random items from randomItem() to randomItem1, 2, 3, 4, and 5
	*/
	public void dailyRandomize()
	{
		randomItem1 = randomItem();
		randomItem2 = randomItem();
		randomItem3 = randomItem();
		randomItem4 = randomItem();
		randomItem5 = randomItem();
	}//end of dailyRandomize
	
	/*
		Picks a random item to determine 5 new items a day
		post: returns an item
	*/
	public Item randomItem()
	{
		int random = (int) (Math.random() * Inventory.MAXIMUM_ITEMS);
		Item randomItem = spaceMarket.getItem(random);
		
		while(randomItem == randomItem1 || randomItem == randomItem2 || randomItem == randomItem3 || randomItem == randomItem4 || randomItem == randomItem5)
		{
			random = (int) (Math.random() * Inventory.MAXIMUM_ITEMS);
			randomItem = spaceMarket.getItem(random);
		}
		current++;
		
		return randomItem;
	}//end of randomItem

	/*
		Display a welcome message for the user explaining the game
	*/
	public void welcomeMessage()
	{
		print("_", FULL_LINE, true, " ", "front");
		print(" ", 45, false, "|", "front");
		print("Welcome to spaceAdventures", 1, false, "", "none");
		print(" ", 46, true, "|", "back");
		print("‾", FULL_LINE, true, " ", "front");
		print("*", 37, false, " ", "back");
		System.out.print("For best results, play in full-screen mode");
		print("*", 38, true, " ", "front");
	}//end of welcomeMessage
	
	/*
		Displays the menu after the welcome message
	*/
	public void welcomeMenu()
	{
		System.out.println("What would you like to do?");
		System.out.println("[1] Start a new game");
		System.out.println("[2] Load game");
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
		Item fuelItem = playerShip.getItem(0);
		System.out.printf("Oh, and here's §%.2f (shards) and %d buckets of " + fuelItem.getName() + " to get you started.\n", player.getWallet(), player.checkFuel());
		skipLine();
		System.out.println("The game will be automatically saved each day as " + "\"" + saveHere + "\".");
		skipLine();
		beginPrompt();
	}//end of newGame
	
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
				//backpack
				for(int i = 0; i < Inventory.MAXIMUM_ITEMS; i++)
				{
					newStock = fileInput.nextInt();
					current = player.getItem(i);
					current.setStock(newStock);
				}
				shipName = fileInput.next();
				fuel = fileInput.nextInt();
				lotteryJackpot = fileInput.nextDouble();
				lotteryCount = fileInput.nextInt();
				playerShip.setName(shipName);
				playerShip.addFuel(fuel);
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
		asks the user if they want to begin
		prevents the user from entering anything but a form of yes
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
		starts the first day without incrementing the day
	*/
	public void firstDay()
	{
		setMarketStockAndPrice();
		print("_", 117, true, " ", "front"); 
		System.out.printf("| Current Day: %d", day);
		//format bar according to the number of days
		if(day < 10)
		{
			print(" ", 102, true, "|", "back");
		}
		else if(day >= 10 && day < 100)
		{
			print(" ", 101, true, "|", "back");
		}
		else if(day >= 100)
		{
			print(" ", 100, true, "|", "back");
		}
		print("‾", 117, true, " ", "front");
		System.out.println("The items for sale today are:");
		printMarketAndPlayerInfo();
		dailyMenu();
		saveGame();
		dailyMessage();
	}//end of firstDay
	
	/*
		print all market and player info
	*/
	public void printMarketAndPlayerInfo()
	{
		printHeader();
		
		for(int i = 0; i < Inventory.MAXIMUM_ITEMS; i++)
		{
			//----market info
			Item current = spaceMarket.getItem(i);
			
			//provide appropriate space between name and price
			if(current == randomItem1 || current == randomItem2 || current == randomItem3 ||current == randomItem4 ||current == randomItem5)
			{
				String indexString = " [" + i + "] ";
				
				if(indexString.length() == 7)
				{
					indexString = "" + indexString;
				}
				else if(indexString.length() == 6)
				{
					indexString = " " + indexString;
				}
				else if(indexString.length() == 5)
				{
					indexString = "  " + indexString;
				}
				System.out.print(indexString);
				
				if(current.getName().length() < 30)
				{
					String newName = current.getName();
					int additionalCharacters = 30 - newName.length();
					
					while(newName.length() < 30)
					{
						newName = newName + " ";
					}
					System.out.print(newName);
				}
				
				//print appropriate spaces after § symbol
				System.out.print("§");
				
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
				
				while(stockString.length() < 21)
				{
					stockString = " " + stockString;
				}
				
				System.out.print(stockString + "           ");
			}
			else
			{
				print(" ", 79, false, "", "none");
			}
						
			//----player backpack
			current = player.getItem(i);
			if(current.getStock() != 0)
			{	
				String indexString = " [" + i + "] ";
				
				if(indexString.length() == 7)
				{
					indexString = "" + indexString;
				}
				else if(indexString.length() == 6)
				{
					indexString = " " + indexString;
				}
				else if(indexString.length() == 5)
				{
					indexString = "  " + indexString;
				}
				System.out.print(indexString);
				
				//print appropriate spaces after item name
				if(current.getName().length() < 27)
				{
					String newName = current.getName();
					int additionalCharacters = 27 - newName.length();
					
					while(newName.length() < 27)
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
					stockString = " " + stockString;
				}
				
				System.out.print(stockString);
			}
			System.out.println();
		}
		Item fuelItem = fuelStation.getItem(0);
		skipLine();
		print(" ", 79, false, "Wallet: ", "back");
		System.out.printf("§%.2f\n", player.getWallet());
		int shipLength = playerShip.getName().length();
		shipLength = 78 - shipLength;
		print(" ", shipLength, false, "", "none");
		System.out.print(playerShip.getName() + "'s Fuel: " + playerShip.checkFuel() + "\n");
	}//end of printMarketAndPlayerInfo
	
	/*
		gives the user all of the options for each day
	*/
	public void dailyMenu()
	{
		Item marketItem;
		Item playerItem;
		
		int buyCount = 0;
		int sellCount = 0;
		int randomCount = 0;
		
		double playerWallet = player.getWallet();
		
		System.out.println();
		System.out.println("What would you like to do today, " + player.getPlayerName() + "?");
		System.out.println();
		System.out.println(" [0]  Skip this day");
		System.out.println(" [1]  Buy items");
		System.out.println(" [2]  Sell items");
		System.out.println(" [3]  Buy fuel");
		if(day % 9 == 0)
		{
			System.out.println(" [9]  Play the lottery (§1 to play)");
		}
		System.out.println("[999] Quit game");
		userInt = input.nextInt();
		
		//check if the user can buy the item 
		for(int i = 0; i < Inventory.MAXIMUM_RANDOM_ITEMS; i++)
		{
			marketItem = randomItems[i];
			if (marketItem.getPrice() > player.getWallet())
			{
				buyCount++;
			}
		}
		
		//check if the user can sell an item
		boolean canSell = false;
		for(int i = 0; i < Inventory.MAXIMUM_ITEMS; i++)
		{
			playerItem = player.getItem(i);
			
			if(canSell == false)
			{
				if(playerItem.getStock() > 0)
				{
					if (playerItem.getName() == randomItem1.getName())
					{
						canSell = true;
					}
					else if(playerItem.getName() == randomItem2.getName())
					{
						canSell = true;
					}
					else if(playerItem.getName() == randomItem3.getName())
					{
						canSell = true;
					}
					else if(playerItem.getName() == randomItem4.getName())
					{
						canSell = true;
					}
					else if(playerItem.getName() == randomItem5.getName())
					{
						canSell = true;
					}
				}
				else
				{
					canSell = false;
				}
			}
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
		else if(userInt == 1 && buyCount < 5)
		{
			buyItem();
		}
		//----user can't buy an item
		else if(userInt == 1 && buyCount == 5)
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
		else if(userInt == 3)
		{
			buyFuel();
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
		allows the user to buy fuel
	*/
	public void buyFuel()
	{
		Item fuelItem = fuelStation.getItem(0);
		print("_", FULL_LINE, true, " ", "front");
		print(" ", 44, false, "|", "front");
		print("Welcome to theStationOfFuel", 1, false, "", "none");
		print(" ", 46, true, "|", "back");
		print("‾", FULL_LINE, true, " ", "front");
		System.out.print("   fuel: " + fuelItem.getStock());
		System.out.printf(" price: %.2f\n",fuelItem.getPrice());
		System.out.println("How many buckets of " + fuelItem.getName() + " would you like to buy?");
		userSelection = input.nextInt();
		double price = fuelItem.getPrice() * userSelection;
		
		while(player.getWallet() < price)
		{
			System.out.println("I'm sorry but you don't have enough money.");
			buyFuel();
		}
		player.removeMoney(price);
		playerShip.addFuel(userSelection);
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
		
		
		while(userSelection > Inventory.MAXIMUM_ITEMS)
		{
			System.out.println("That item does not exist. Please pick one of the items for sale.");
			//prompt user for which item they would like to purchase
			System.out.println("Which item would you like to buy?");
			userSelection = input.nextInt();
		}
		
		//create a temporary item to test if the user choice is in stock
		tempItem = spaceMarket.getItem(userSelection);
		
		while(tempItem != randomItem1 && tempItem != randomItem2 && tempItem != randomItem3 && tempItem != randomItem4 && tempItem != randomItem5)
		{
			System.out.println("That item is not for sale today, " + player.getPlayerName() + ".");
			//prompt user for which item they would like to purchase
			System.out.println("Which item would you like to buy?");
			userSelection = input.nextInt();
			tempItem = spaceMarket.getItem(userSelection);
		}
		
		//check if choice is valid
		//----stock choice is not high enough
		while(tempItem.getStock() < 1)
		{
			System.out.println("We currently do not have enough of that item in stock...");
			System.out.println("Please choose another item.");
			userSelection = input.nextInt();
		}
		
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
		
		while(userSelection > Inventory.MAXIMUM_ITEMS)
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
		outputs a message for the user when they quit and tells them how they did.
	*/
	public void quitGame()
	{
		Item current;
		skipLine();
		print("*", 119, true, "", "none");
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
		
		for(int i = 0; i < Inventory.MAXIMUM_ITEMS; i++)
		{
			current = player.getItem(i);
			if(current.getStock() != 0)
			{	
				String indexString = " [" + i + "] ";
				
				if(indexString.length() == 7)
				{
					indexString = "" + indexString;
				}
				else if(indexString.length() == 6)
				{
					indexString = " " + indexString;
				}
				else if(indexString.length() == 5)
				{
					indexString = "  " + indexString;
				}
				System.out.print(indexString);
				
				//print appropriate spaces after item name
				if(current.getName().length() < 27)
				{
					String newName = current.getName();
					int additionalCharacters = 27 - newName.length();
					
					while(newName.length() < 27)
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
		saves the game to a text file of the user's name
	*/
	public void saveGame()
	{
		try
		{
			File saveFile = new File(saveHere);
			PrintWriter fileOutput = new PrintWriter(saveFile);
			fileOutput.println(player.getPlayerName());
			fileOutput.println(day);
			fileOutput.printf("%.2f\n", player.getWallet());
			for(int i = 0; i < Inventory.MAXIMUM_ITEMS; i++)
			{
				Item current = player.getItem(i);
				
				if(i < Inventory.MAXIMUM_ITEMS - 1)
				{
					fileOutput.println(current.getStock());
				}
				else
				{
					fileOutput.print(current.getStock());
				}
			}
			fileOutput.println();
			fileOutput.println(playerShip.getName());
			fileOutput.println(player.checkFuel());
			fileOutput.println(lotteryJackpot);
			fileOutput.print(lotteryCount);
			fileOutput.close();
		}
		catch(IOException exception)
		{
			System.out.println("There is no file to save.");
		}
	}//end of saveGame
	
	/*
		daily routine
	*/
	public void dailyMessage()
	{
		while (userInt != 999)
		{
			day++;
			setMarketStockAndPrice();
			print("_", 117, true, " ", "front"); 
			System.out.printf("| Current Day: %d", day);
			//format bar according to the number of days
			if(day < 10)
			{
				print(" ", 102, true, "|", "back");
			}
			else if(day >= 10 && day < 100)
			{
				print(" ", 101, true, "|", "back");
			}
			else if(day >= 100)
			{
				print(" ", 100, true, "|", "back");
			}
			print("‾", 117, true, " ", "front");System.out.println("The items for sale today are:");
			printMarketAndPlayerInfo();
			dailyMenu();
			saveGame();
		}
}//end of dailyMessage

	/*
		lottery that shows up every 9 days
	*/
	public void lottery()
	{
		int magicNumber = randomInt(1, 10);
		player.removeMoney(1);
		
		print("*", 119, true, "", "none");
		print(" ", 117, true, "*", "front and back");
		System.out.println("*                                    *       **   *****  *****  *****  ***   *   *                                    *");
		System.out.println("*                                    *      *  *    *      *    *      *  *   * *                                     *");
		System.out.println("*                                    *      *  *    *      *    ***    ***     *                                      *");
		System.out.println("*                                    *      *  *    *      *    *      *  *    *                                      *");
		System.out.println("*                                    *****   **     *      *    *****  *   *   *                                      *");
		print(" ", 117, true, "*", "front and back");
		print("*", 119, true, "", "none");
		skipLine();
		System.out.printf("The current jackpot is §%.2f\n", lotteryJackpot);		
		skipLine();
		System.out.println("What do you think the magic number is today?");
		System.out.println("(Choose a number between 1 and 10 inclusively)");
		userInt = input.nextInt();
		if(userInt == magicNumber)
		{
			System.out.printf("Congratulations!! You won §%.2f!!\n", lotteryJackpot);
			player.addMoney(lotteryJackpot);
			lotteryJackpot = LOTTERY_MIN_JACKPOT;
			lotteryCount = LOTTERY_RESET;
		}
		else if(userInt != magicNumber)
		{
			System.out.println("I'm sorry, you didn't win. Try again sometime.");
			lotteryCount++;
			lotteryJackpot = lotteryJackpot * lotteryCount;
		}
		else if(userInt < LOTTERY_MIN_NUM)
		{
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
		prints the header for the market and player "GUI"
	*/
	public void printHeader()
	{
		//format printing to make a text version of a "GUI"
		print("_", 68, false, " ", "front");
		print("_", 38, true, "           ", "front");
		print(" ", 27, false, "|", "front");
		System.out.print("Ships 'n Stuff");
		print(" ", 27, false, "|", "back");
		print(" ", 15, false, "         |", "front");
		System.out.print("Backpack");
		print(" ", 15, true, "|", "back");
		print("‾", 68, false, "|", "front and back");
		System.out.print("         ");
		print("‾", 38, true, "|", "front and back");
		print("Item", 1, false, "| ", "front");
		print(" ", 30, false, "Price (§)", "back");
		print(" ", 18, false, "Stock |", "back");
		print(" ", 9, false, "| Item", "back");
		print(" ", 27, true, "Stock |", "back");
		print("‾", 68, false, " ", "front");
		print("‾", 38, true, "           ", "front");
	}//end of printHeader
	
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
		prints a blank line
	*/
	public void skipLine()
	{
		System.out.println("");
	}//end of skipLine
	
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
	}//end of printSymbol
}