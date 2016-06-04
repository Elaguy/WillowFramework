package schultz.personal.willowFramework;

import java.util.ArrayList;

public class World {
	
	private Log logObj;
	
	int worldWidth = 15;
	int worldLength = 20;
	
	private String[][] adraria;
	private String[][] world2;
	private String[][] world3;
	private String[][] world4;
	private String[][] world5;
	private String[][] currentWorld;
	
	private ArrayList<String[][]> worldList;
	private ArrayList<Player> playerList;
	
	private Player userPlayerObj;
	
	private String tempTile = " ";
	
	public World() {
		
	}
	
	public void initWorld() {
		createWorlds();
		createPlayer();
	}
	
	private void createWorlds() {
		worldList = new ArrayList<String[][]>();
		
		adraria = new String[worldWidth][worldLength];
		world2 = new String[worldWidth][worldLength];
		world3 = new String[worldWidth][worldLength];
		world4 = new String[worldWidth][worldLength];
		world5 = new String[worldWidth][worldLength];
		
		worldList.add(adraria);
		worldList.add(world2);
		worldList.add(world3);
		worldList.add(world4);
		worldList.add(world5);
		
		for(int i = 0; i < worldList.size(); i++) {
			createWorld(worldList.get(i));
		}
		
		currentWorld = adraria;
		
		buildAdraria();
		
		logObj.addEvent("Worlds created");
		
	}
	
	private void createWorld(String[][] world) {
		for(int i = 0; i < world.length; i++)
			for(int j = 0; j < world[i].length; j++)
				world[i][j] = " ";
	}
	
	private void buildAdraria() {
		buildLierough();
	}
	
	private void buildLierough() {
		String wallTile = "=";
		
		for(int i = 4; i <= 15; i++) // north wall
			adraria[1][i] = wallTile;
		
		for(int i = 2; i <= 9; i++) // west wall
			adraria[i][4] = wallTile;
		
		for(int i = 2; i <= 9; i++) // east wall
			adraria[i][15] = wallTile;
		
		for(int i = 4; i <= 15; i++) // south wall
			adraria[9][i] = wallTile;
			
		
	}
	
	private void createPlayer() {
			playerList = new ArrayList<Player>();
		
			int randomRow = (int) (Math.random() * worldWidth);
			int randomCol = (int) (Math.random() * worldLength);
			
			userPlayerObj = new Player(randomRow, randomCol, 1, "User");
			playerList.add(userPlayerObj);
			placePlayerIcon(randomRow, randomCol, adraria);
			
			logObj.addEvent("User player created");
	}
	
	public void movePlayer(Player playerObj, int num, String direction, String[][] world) {
		if(direction.equals("North") || direction.equals("north")) {
			if(playerObj.getRow() - playerObj.getSpeed() >= 0) {
				if(world[playerObj.getRow() - playerObj.getSpeed()][playerObj.getCol()] == " ")
					normalMove(playerObj, playerObj.getRow() - playerObj.getSpeed(), playerObj.getCol(), world);
				
				logObj.addEvent(playerObj.getName() + " moved " + playerObj.getSpeed() + " block(s) to the North");
			}
			else
				worldScroll(playerObj, "North");
				
		}
		
		else if(direction.equals("South") || direction.equals("south")) {
			if(playerObj.getRow() + playerObj.getSpeed() <= world.length) {
				if(world[playerObj.getRow() + playerObj.getSpeed()][playerObj.getCol()] == " ")
					normalMove(playerObj, playerObj.getRow() + playerObj.getSpeed(), playerObj.getCol(), world);
				
				logObj.addEvent(playerObj.getName() + " moved " + playerObj.getSpeed() + " block(s) to the South");
			}
			else {
				worldScroll(playerObj, "South");
			}
		}
		
		else if(direction.equals("East") || direction.equals("east")) {
			if(playerObj.getCol() + playerObj.getSpeed() <= worldLength-1) {
				if(world[playerObj.getRow()][playerObj.getCol() + playerObj.getSpeed()] == " ")
					normalMove(playerObj, playerObj.getRow(), playerObj.getCol() + playerObj.getSpeed(), world);
				
				logObj.addEvent(playerObj.getName() + " moved " + playerObj.getSpeed() + " block(s) to the East");
			}
			else {
				worldScroll(playerObj, "East");
			}
		}
		
		else if(direction.equals("West") || direction.equals("west")) {
			if(playerObj.getCol() - playerObj.getSpeed() >= 0) {
				if(world[playerObj.getRow()][playerObj.getCol() - playerObj.getSpeed()] == " ")
					normalMove(playerObj, playerObj.getRow(), playerObj.getCol() - playerObj.getSpeed(), world);
				
				logObj.addEvent(playerObj.getName() + " moved " + playerObj.getSpeed() + " block(s) to the West");
			}
			else {
				worldScroll(playerObj, "West");
			}
		}
		
		else {
			System.out.println();
			System.out.println("ERROR MOVING: " + playerObj.getName());
		}
	}
	
	private void placePlayerIcon(int row, int col, String[][] world) {
		world[row][col] = "O";
	}
	
	private void removePlayerIcon(int row, int col, String[][] world) {
		if(world[row][col] == "O")
			world[row][col] = " ";
	}
	
	private void repairTile(int row, int col, String[][] world) {
		world[row][col] = tempTile;
	}
	
	private void saveTile(int row, int col, String[][] world) {
		tempTile = world[row][col];
	}
	
	private void normalMove(Player playerObj, int row, int col, String[][] world) {
		placePlayerIcon(row, col, world);
		removePlayerIcon(playerObj.getRow(), playerObj.getCol(), world);
		playerObj.setRow(row);
		playerObj.setCol(col);
	}
	
	private void worldScroll(Player playerObj, String direction) {
		if(direction.equals("North") || direction.equals("north")) {
			if(currentWorld.equals(adraria)) {
				scrollToWorld2(playerObj);
				logObj.addEvent(playerObj.getName() + " moved to world2 from Adraria");
			}
			
			else if(currentWorld.equals(world4)) {
				scrollToAdraria(playerObj, "North");
				logObj.addEvent(playerObj.getName() + " moved to adraria from world4");
			}
			
			else
				System.out.println("ERROR: CAN'T MOVE PLAYER OUT OF WORLD");
		}
		
		else if(direction.equals("South") || direction.equals("south")) {
			if(currentWorld.equals(world2)) {
				scrollToAdraria(playerObj, "South");
				logObj.addEvent(playerObj.getName() + " moved to Adraria from world2");
			}
			
			else if(currentWorld.equals(adraria)) {
				scrollToWorld4(playerObj);
				logObj.addEvent(playerObj.getName() + " moved to world4 from Adraria");
			}
			
			else
				System.out.println("ERROR: CAN'T MOVE PLAYER OUT OF WORLD");
		}
		
		else if(direction.equals("East") || direction.equals("east")) {
			if(currentWorld.equals(world5)) {
				scrollToAdraria(playerObj, "East");
				logObj.addEvent(playerObj.getName() + " moved to Adraria from world5");
			}
			
			else if(currentWorld.equals(adraria)) {
				scrollToWorld3(playerObj);
				logObj.addEvent(playerObj.getName() + " moved to world3 from Adraria");
			}
			
			else
				System.out.println("ERROR: CAN'T MOVE PLAYER OUT OF WORLD");
		}
		
		else if(direction.equals("West") || direction.equals("west")) {
			if(currentWorld.equals(world3)) {
				scrollToAdraria(playerObj, "West");
				logObj.addEvent(playerObj.getName() + " moved to Adraria from world3");
			}
			
			else if(currentWorld.equals(adraria)) {
				scrollToWorld5(playerObj);
				logObj.addEvent(playerObj.getName() + " moved to world5 from Adraria");
			}
			
			else
				System.out.println("ERROR: CAN'T MOVE PLAYER OUT OF WORLD");
		}
	}
	
	private void scrollToAdraria(Player playerObj, String direction) {
		removePlayerIcon(playerObj.getRow(), playerObj.getCol(), currentWorld);
		currentWorld = adraria;
		
		if(direction.equals("North") || direction.equals("north")) {
			placePlayerIcon(currentWorld.length-1, playerObj.getCol(), currentWorld);
			playerObj.setRow(currentWorld.length-1);
		}
		
		else if(direction.equals("South") || direction.equals("south")) {
			placePlayerIcon(0, playerObj.getCol(), currentWorld);
			playerObj.setRow(0);
		}
		
		else if(direction.equals("East") || direction.equals("east")) {
			placePlayerIcon(playerObj.getRow(), 0, currentWorld);
			playerObj.setCol(0);
		}
		
		else if(direction.equals("West") || direction.equals("west")) {
			placePlayerIcon(playerObj.getRow(), currentWorld[0].length-1, currentWorld);
			playerObj.setCol(currentWorld[0].length-1);
		}
	}
	
	private void scrollToWorld2(Player playerObj) {
		removePlayerIcon(playerObj.getRow(), playerObj.getCol(), currentWorld);
		currentWorld = world2;
		placePlayerIcon(currentWorld.length-1, playerObj.getCol(), currentWorld);
		playerObj.setRow(currentWorld.length-1);
	}
	
	private void scrollToWorld3(Player playerObj) {
		removePlayerIcon(playerObj.getRow(), playerObj.getCol(), currentWorld);
		currentWorld = world3;
		placePlayerIcon(playerObj.getRow(), 0, currentWorld);
		playerObj.setCol(0);
	}
	
	private void scrollToWorld4(Player playerObj) {
		removePlayerIcon(playerObj.getRow(), playerObj.getCol(), currentWorld);
		currentWorld = world4;
		placePlayerIcon(0, playerObj.getCol(), currentWorld);
		playerObj.setRow(0);
	}
	
	private void scrollToWorld5(Player playerObj) {
		removePlayerIcon(playerObj.getRow(), playerObj.getCol(), currentWorld);
		currentWorld = world5;
		placePlayerIcon(playerObj.getRow(), currentWorld[0].length-1, currentWorld);
		playerObj.setCol(currentWorld[0].length-1);
	}
	
	public String[][] getAdraria() {
		return adraria;
	}
	
	public String[][] getWorld2() {
		return world2;
	}

	public String[][] getWorld3() {
		return world3;
	}

	public String[][] getWorld4() {
		return world4;
	}

	public String[][] getWorld5() {
		return world5;
	}
	
	public String[][] getCurrentWorld() {
		return currentWorld;
	}
	
	public ArrayList<String[][]> getWorldList() {
		return worldList;
	}
	
	public Player getUserPlayerObj() {
		for(int i = 0; i < playerList.size(); i++) {
			if(playerList.get(i).getName().equals("User"))
				return playerList.get(i);
		}
		System.out.println("ERROR: USER PLAYER NOT FOUND");
		return null;
	}
	
	public void setCurrentWorld(String[][] world) {
		currentWorld = world;
	}
	
	public void recieveLogObj(Log logObj) {
		this.logObj = logObj;
	}
}
