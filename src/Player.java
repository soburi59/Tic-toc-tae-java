abstract class Player {
	private String name;  // プレイヤの名前
	private int playerNum; //プレイヤの割り当てられた番号
	Player(String name) {
		this.name = name;
	}
	Player(String name,int playerNum) {
		this(name);
		this.playerNum = playerNum;
	}
	String getName() {
		return name;
	}
	int getPlayerNum() {
		return playerNum;
	}
	void setPlayerNum(int num) {
		this.playerNum=num;
	}
 	abstract int getNextLocation(Board board);
 	abstract boolean isCPU();
}
