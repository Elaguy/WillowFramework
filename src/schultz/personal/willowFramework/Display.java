package schultz.personal.willowFramework;
//RUN PROGRAMMINGTIMER.PY!!!
import java.util.ArrayList;
import java.util.Scanner;

public class Display {
	
	private Scanner input = new Scanner(System.in);
	
	private WillowGame willowGameObj;
	private World worldObj;
	
	public Display() {
		
	}
	
	public void displayWorld() {
		String[][] currentWorld = worldObj.getCurrentWorld();
		
		System.out.println(worldName(currentWorld));
		System.out.println();
		System.out.println("---------------------------------------------"
				+ "-------------------------------------");
		for(int i = 0; i < currentWorld.length; i++) {
			System.out.print("| ");
			for(int j = 0; j < currentWorld[i].length; j++) {
				System.out.print(currentWorld[i][j] + " | ");
			}
			System.out.println();
			System.out.println("---------------------------------------------"
					+ "-------------------------------------");
		}
	}
	
	private String worldName(String[][] currentWorld) {
		String worldName = "UNKNOWN WORLD";
		
		if(currentWorld.equals(worldObj.getAdraria()))
			worldName = "Adraria";
		else if(currentWorld.equals(worldObj.getWorld2()))
			worldName = "World2";
		else if(currentWorld.equals(worldObj.getWorld3()))
			worldName = "World3";
		else if(currentWorld.equals(worldObj.getWorld4()))
			worldName = "World4";
		else if(currentWorld.equals(worldObj.getWorld5()))
			worldName = "World5";
		
		return worldName;
	}
	
	public void userInput() {
		System.out.println();
		System.out.println("Enter a command: ");
		String userInput = input.nextLine();
		execCommand(userInput);
	}
	
	private void execCommand(String command) {
		switch(command) {
			case "Move up" :
			case "move up" :
				worldObj.movePlayer(worldObj.getUserPlayerObj(), worldObj.getUserPlayerObj().getSpeed(), "North", worldObj.getCurrentWorld());
				break;
				
			case "Move down" :
			case "move down" :
				worldObj.movePlayer(worldObj.getUserPlayerObj(), worldObj.getUserPlayerObj().getSpeed(), "South", worldObj.getCurrentWorld());
				break;
				
			case "Move left" :
			case "move left" :
				worldObj.movePlayer(worldObj.getUserPlayerObj(), worldObj.getUserPlayerObj().getSpeed(), "West", worldObj.getCurrentWorld());
				break;
				
			case "Move right" :
			case "move right" :
				worldObj.movePlayer(worldObj.getUserPlayerObj(), worldObj.getUserPlayerObj().getSpeed(), "East", worldObj.getCurrentWorld());
				break;
				
			case "Exit" :
			case "exit" :
				System.out.println("Exiting game...");
				willowGameObj.setGameRunning(false);
				break;
			
			default :
				System.out.println("ERROR: COMMAND NOT FOUND");
				break;
		}
	}
	
	public void recieveWorldObj(World worldObj) {
		this.worldObj = worldObj;
	}
	
	public void recieveWillowObj(WillowGame willowGameObj) {
		this.willowGameObj = willowGameObj;
	}
}
