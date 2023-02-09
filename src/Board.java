class Board {
	public int stoneNum; // 石を置く枠の数
	public int boardNum;//ボードの横幅,または縦幅
	static char CHAR_FREE = '.'; // 石が置かれていないときの文字
	static char[] CHAR_STONES = {'X', 'O'}; // プレイヤ0の石とプレイヤ1の石
	private int data[]; // ボード上の各マスに石が置かれているか．0:プレイヤ0の石が置かれている，1:プレイヤ1の石が置かれている，負の値:何も置かれていない．
	Board(int num) {
		this.stoneNum=num;
		boardNum = (int)Math.sqrt(stoneNum);
		data = new int[stoneNum];
		for (int i = 0; i < stoneNum; i++)
				data[i] = -1; // データの初期値は何も置かれていない状態とする．
		if(Game.debag) {
			this.put(1,0);
			this.put(2,0);
			this.put(3,0);
			this.put(4,1);
			this.put(5,1);
			this.put(7,1);
			
		}
	}
	void show() {
		// dataに応じて，ボードを表示する．
		System.out.println();
		System.out.print(" |");
		for(int i=0;i<boardNum;i++) {
			System.out.print(i);
		}
		System.out.println();
		System.out.print("-+");
		for(int i=0;i<boardNum;i++) {
			System.out.print("-");
		}
		for(int i=0;i<stoneNum;i++) {
			if(i%boardNum==0) {
				System.out.println();
				System.out.print(i/boardNum+"|");
			}
			char c=CHAR_FREE;
			if(data[i]>=0) {
				c=CHAR_STONES[data[i]];
			}
			System.out.print(c);
		}
		System.out.println();
		System.out.println();
	}
	boolean put(int locnum, int pnum) {
		// locnumの位置に，プレイヤpnumの石を置く．
		// 置く処理が成功すればtrue，そうでなければfalse
		if(locnum>=stoneNum||locnum<0||data[locnum]>=0)return false;
		data[locnum]=pnum;
		return true;
	}
	void remove(int locnum) {
		data[locnum]=-1;
		return;
	}
	boolean checkFree(){
		for(int i=0;i<stoneNum;i++) {
			if(data[i]<0)return true;
		}
		return false;
	}
	boolean checkWin(int pnum) {
		// プレイヤpnumが勝ちの状態になっているかを判定する．
		//上段だけ全部見る
		for(int i=0;i<boardNum;i++) {
			if(data[i]==pnum) {
				if(checkVertical(i,pnum)) {
					return true;
				}
				
			}
		}
		//左側だけ見る
		for(int i=0;i<stoneNum;i+=boardNum) {
			if(data[i]==pnum) {
				if(checkHorizontal(i,pnum)) {
					return true;
				}
			}
		}
		//斜め2つ
		return checkDiagonal1(pnum)||checkDiagonal2(pnum);
	}
	
	private boolean checkHorizontal(int locnum,int pnum) {
		//横が揃ってるか見る
		for(int i=locnum;i<locnum+boardNum;i++) {
			if(data[i]!=pnum)return false;
		}
		return true;
	}
	private boolean checkVertical(int locnum,int pnum)	{
		//縦が揃ってるか見る
		for(int i=locnum;i<stoneNum;i+=boardNum) {
			if(data[i]!=pnum)return false;
		}
		return true;
	}
	private boolean checkDiagonal1(int pnum) {
		//右斜め↘
		for(int i=0;i<stoneNum;i+=boardNum+1) {
			if(data[i]!=pnum)return false;
		}
		return true;
	}	
	private boolean checkDiagonal2(int pnum){
		//左斜め↙
		for(int i=boardNum-1;i<=stoneNum-boardNum;i+=boardNum-1) {
			if(data[i]!=pnum)return false;
		}
		return true;
	}
}
