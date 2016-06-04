package schultz.personal.willowFramework;

public class Player {
	
	private int row;
	private int col;
	private String name;
	private int speed;
	
	public Player(int row, int col, int speedInput, String name) {
		this.row = row;
		this.col = col;
		this.name = name;
		speed = speedInput;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public String getName() {
		return name;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setRow(int rowInput) {
		row = rowInput;
	}
	
	public void setCol(int colInput){
		col = colInput;
	}
	
	public void setName(String nameInput) {
		name = nameInput;
	}
	
	public void setSpeed(int speedNum) {
		speed = speedNum;
	}
	
	@Override
	public String toString() {
		return (Integer.toString(row)+ "\n" +
				Integer.toString(col) + "\n" + name);
	}
}
