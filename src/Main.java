class Main {
	public static void main(String[] args) {
		while(true) {
			Player p1;
			Player p2;
			Game game;
			System.out.println("---------MODE---------");
			System.out.println("|1:player1 vs player2|");
			System.out.println("|2:player1 vs cpu    |");
			System.out.println("----------------------");
			int mode=Game.scanInt("Select mode:",i -> i==1||i==2);
			p1 = new HumanPlayer(Game.scanStr("Input player1 name:"));
			if(mode==1)p2 = new HumanPlayer(Game.scanStr("Input player2 name:"));
			else p2= new ComputerPlayer("CPU");
			int stone=Game.scanInt("Input number of a side(3~):",i -> i>=3);
			System.out.println("Which one first?");
			System.out.println("-----------------");
			System.out.println("1:"+p1.getName());
			System.out.println("2:"+p2.getName());
			System.out.println("-----------------");
			mode=Game.scanInt("Select mode:",i -> i==1||i==2);
			if(mode==1) {
				game = new Game(p1, p2,stone*stone);
			}else {
				game = new Game(p2, p1,stone*stone);//先行後攻入れ替え
			}
			System.out.println(p1 + " vs " + p2);
			System.out.println();
			game.start();
			if(!Game.scanStr("continue?(y/n):").equals("y")) {
				break;
			}
		}
		Game.sc.close();//scannerクローズ
	}
}