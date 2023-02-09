import java.util.Random;
import java.util.Scanner;
class Game {
	static Scanner sc=new Scanner(System.in);
	private Player[] players; 	// プレイヤ情報
	private Board board;
	public static boolean debag=false;//debag
	Game(Player p1, Player p2,int num) {
		players = new Player[2]; // 配列の要素の確保 
		p1.setPlayerNum(0);
		p2.setPlayerNum(1);
		players[0] = p1;
		players[1] = p2;
		board = new Board(num);
	}
	void start() {
		// players[0]，player[1], boardを使ってゲームの流れを記述する．
		int turn=0;//どちらのターンか
		if(debag)turn=1;
		int num;
		/*
		 * 16マスでcpuに思考させると計算量が膨大なのである程度石を置く.
		 */
		if(board.stoneNum>=16&&(players[0].isCPU()||players[1].isCPU())) {
			Random rand = new Random();
			for(int i=0;i<6;i++) {
				while(true) {
					int a =rand.nextInt(board.stoneNum);
					if(board.put(a,i%2)) {
						if(board.checkWin(0)||board.checkWin(1)) {
							//どっちかが勝ってしまうなら再度
							board.remove(a);
						}else {
							break;
						}
					}
				}
			}
		}
		while(true) {
			System.out.println(players[turn]+" turn.");
			board.show();
			System.out.println("Stone:"+Board.CHAR_STONES[turn]);
			while(true) {
				num=players[turn].getNextLocation(board);//次置く場所
				if(board.put(num,turn))break;//置けたらbreak
				System.out.println("すでに置かれているか,不正な値です.");
				if(debag)break;
			}
			System.out.println(players[turn].getName()+" put("+num%board.boardNum+","+num/board.boardNum+")");//1,2など
			System.out.println("-----------------");
			if(board.checkWin(turn)) {
				board.show();
				System.out.println(players[turn].getName()+" WIN!");
				break;
			}
			if(!board.checkFree()) {
				board.show();
				System.out.println("DROW");
				break;
			}
			turn=1-turn;//0と1をトグル
		}
		return;
	}
	public static String scanStr(String s) {
		System.out.print(s);
		return Game.sc.next();
	}
	@FunctionalInterface
	public interface Predicate<T> {//関数型インターフェース
	    boolean test(int t);//booleanを返す
	}
	public static int scanInt(String s,Predicate<Integer> predicate) {//
		int num=0;
		while(true) {
			try {
                System.out.print(s);
                String text = Game.sc.next();
                num=Integer.valueOf(text);
                if(predicate.test(num)) {//渡された条件式で例外処理
                	break;
                }else {
                	System.out.println("不正な値です.");
                }
            } catch (Exception e) {
                System.out.println("不正な値です.");
            }
        }
		return num;
	}
}
