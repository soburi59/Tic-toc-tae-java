public class ComputerPlayer extends Player {
	public ComputerPlayer(String name) {
		super(name);
	}
	public ComputerPlayer(String name,int playerNum) {
		super(name,playerNum);
	}
	
	@Override
	int getNextLocation(Board board) {
		return minimax(board,getPlayerNum(),0);
	}
	private int minimax(Board board,int nowPlayerNum,int depth) {
		if(board.checkWin(getPlayerNum())) {
			return 10-depth;//勝ちのときは10 早ければ価値が高いのでdepthを引いている
		}
		else if(board.checkWin(1-getPlayerNum())) {
			return depth-10;//負けても長引けば一瞬で負けるよりも価値が高い
		}else if(!board.checkFree()){
			return 0;//引き分け
		}
		int num=0;
		int bestValue=nowPlayerNum==getPlayerNum()?-10000:10000;//十分大きい数字
		for(int i=0;i<board.stoneNum;i++) {//次のすべての手を考える
			if(board.put(i,nowPlayerNum)) {
				if(Game.debag)board.show();
				int childValue=minimax(board,1-nowPlayerNum,depth+1);//再帰で子ノードの評価値を取得&ターンを変える&深さをインクリメント
				if(nowPlayerNum == getPlayerNum()) {
					if(childValue>bestValue) {//自分のターンなら最大値を選ぶ
						bestValue=childValue;
						if(Game.debag)System.out.println(bestValue);
						num=i;//場所を更新
					}
				}else {
					if(childValue<bestValue) {//相手ターンなら最小値を選ぶ
						bestValue=childValue;
						if(Game.debag)System.out.println(bestValue);
						num=i;
					}
				}
				board.remove(i);//盤面をもとに戻す.
			}
		}
		if(depth==0)return num;//根ノードは結果の場所を返す
		return bestValue;
	}
	@Override
	public String toString() {
		return getName()+"(Computer)";
	}
	boolean isCPU() {
		return true;
	}
}
