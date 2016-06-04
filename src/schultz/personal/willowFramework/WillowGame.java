package schultz.personal.willowFramework;

import java.io.IOException;

// RUN PROGRAMMINGTIMER.PY!!!
public class WillowGame {
	
	private boolean gameRunning;
	
	private World worldObj;
	private Display displayObj;
	private Log logObj;
	
	public WillowGame() {
		gameRunning = true;
		worldObj = new World();
		displayObj = new Display();
		logObj = new Log();
	}
	
	public void runGame(WillowGame willowGameObj) {
		initGame(willowGameObj);
		
		while(gameRunning) {
			displayObj.displayWorld();
			displayObj.userInput();
		}
		logObj.exportEventList();
	}
	
	private void initGame(WillowGame willowGameObj) {
		logObj.initLog();
		worldObj.recieveLogObj(logObj);
		worldObj.initWorld();
		displayObj.recieveWorldObj(worldObj);
		displayObj.recieveWillowObj(willowGameObj);
	}
	
	public boolean getGameRunning() {
		return gameRunning;
	}
	
	public void setGameRunning(boolean gameRunningInput) {
		gameRunning = gameRunningInput;
	}
	
	public static void main(String[] args) {
		WillowGame willowGameObj = new WillowGame();
		willowGameObj.runGame(willowGameObj);
	}
}
