public class HumanPlayer extends Player {
	public HumanPlayer(String name) {
		super(name);
	}
	public HumanPlayer(String name,int playerNum) {
		super(name,playerNum);
	}
	@Override
	int getNextLocation(Board board) {
		return (Game.scanInt("Input location number(row):",i -> i>=0&&i<board.boardNum)+Game.scanInt("Input location number(column):",i -> i>=0&&i<board.boardNum)*board.boardNum);
	}
	@Override
	public String toString() {
		return getName()+"(Human)";
	}
	boolean isCPU() {
		return false;
	}
}
