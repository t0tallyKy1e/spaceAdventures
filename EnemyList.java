import java.util.ArrayList;

public class EnemyList{
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>(0);
	
	/*
		adds an Enemy to the list
		pre: requires an Enemy to add to the list
	*/
	public void addEnemy(Enemy tempEnemy){
		enemies.add(tempEnemy);
	}//end of addEnemy
	
	/*
		pre: an int for the index of the enemy you wish to see
		post: returns an Enemy at the index provided
	*/
	public Enemy getEnemy(int index){
		return enemies.get(index);
	}//end of getEnemy
	
	public Enemy getRandomEnemy(){
		int tempIndex;
		int max = enemies.size() - 1;
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
		return enemies.get(tempIndex);
	}
	
	/*
		post: returns the size of the EnemyList
	*/
	public int getSize(){
		return enemies.size();
	}//end of getSize
}//end of EnemyList