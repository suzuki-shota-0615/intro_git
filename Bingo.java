package sample;

import java.util.Scanner;

public class Bingo {

	public static void main(String[] args) {
		
		int[][] bingoNum = startBingo();
		int cnt = 1;

		while (true) {
			
			Scanner sc = new Scanner(System.in);
			//System.out.println("Enterを押して処理を続けます。");
			sc.nextLine(); // Enterキーが押されるまで待機する
			//System.out.println("処理を続けます。")
			System.out.println(cnt + "回目");
			int random = (int) (Math.random() * 75 + 1);
			System.out.println("出た数字:" + random);
			if (searchNum(random, bingoNum)) {
				System.out.println("Hit");
				bingoNum = showCard(random, bingoNum);
			} else {
				System.out.println("Deviate");
				bingoNum = showCard(random, bingoNum);
			}
			
			if (!(judgeBingo(bingoNum))) {
				System.out.println("リーチ数:" + countReach(bingoNum));				
			} else {
				System.out.println("congratulation!!");
				break;
			}
			System.out.println("");
			cnt+=1;
		}

	}
	
	static boolean judgeBingo(int[][] bn) {
		
		boolean finish = false;
		
		int cntHorizontalZero = 0;
		int cntVerticalZero = 0;
		int cntDiagonalLeftZero = 0;
		int cntDiagonalRightZero = 0;
		
		//Horizontal 横
		for(int i = 0 ;i <bn.length; i++) {
			for(int j = 0; j < bn.length; j++) {
				if(bn[i][j] == 0) {
					cntHorizontalZero+=1;
				}
			}
			if(cntHorizontalZero == 5) {
				finish = true;
			}
			cntHorizontalZero = 0;
		}
		
		//Vertical 縦
		for(int i = 0 ;i <bn.length; i++) {
			for(int j = 0; j < bn.length; j++) {
				if(bn[j][i] == 0) {
					cntVerticalZero+=1;
				}
			}
			if(cntVerticalZero == 5) {
				finish = true;
			}
			cntVerticalZero = 0;
		}
		
		// diagonalLeft 斜め（左上から右下）
		for(int i = 0; i < bn.length; i++) {
			if(bn[i][i] == 0) {
				cntDiagonalLeftZero+=1;
			}
		}
		if(cntDiagonalLeftZero == 5) {
			finish = true;
		}
		
		// diagonalRight 斜め（右上から左下）
		for(int i = 0; i < bn.length; i++) {
			if(bn[i][(bn.length - 1) - i] == 0) {
				cntDiagonalRightZero+=1;
			}
		}
		if(cntDiagonalRightZero == 5) {
			finish = true;
		}
		
		return finish;
	}
	
 	static int countReach(int[][] bn) {
		int cntReach = 0;
		
		int cntHorizontalZero = 0;
		int cntVerticalZero = 0;
		int cntDiagonalLeftZero = 0;
		int cntDiagonalRightZero = 0;
		
		//Horizontal 横
		for(int i = 0 ;i <bn.length; i++) {
			for(int j = 0; j < bn.length; j++) {
				if(bn[i][j] == 0) {
					cntHorizontalZero+=1;
				}
			}
			if(cntHorizontalZero == 4) {
				cntReach+=1;
			}
			cntHorizontalZero = 0;
		}
		
		//Vertical 縦
		for(int i = 0 ;i <bn.length; i++) {
			for(int j = 0; j < bn.length; j++) {
				if(bn[j][i] == 0) {
					cntVerticalZero+=1;
				}
			}
			if(cntVerticalZero == 4) {
				cntReach+=1;
			}
			cntVerticalZero = 0;
		}
		
		// diagonalLeft 斜め（左上から右下）
		for(int i = 0; i < bn.length; i++) {
			if(bn[i][i] == 0) {
				cntDiagonalLeftZero+=1;
			}
		}
		if(cntDiagonalLeftZero == 4) {
			cntReach+=1;
		}
		
		// diagonalRight 斜め（右上から左下）
		for(int i = 0; i < bn.length; i++) {
			if(bn[i][(bn.length - 1) - i] == 0) {
				cntDiagonalRightZero+=1;
			}
		}
		if(cntDiagonalRightZero == 4) {
			cntReach+=1;
		}
		
		return cntReach; 
	}

	static int[][] showCard(int n, int[][] bn) {

		String[] bingo = { "B", "I", "N", "G", "O" };

		makeHorizontalLine();

		for (int i = 0; i < bingo.length; i++) {
			System.out.print("| ");
			System.out.print(bingo[i]);
		}
		System.out.println("| ");
		makeHorizontalLine();

		for (int i = 0; i < bn.length; i++) {
			for (int j = 0; j < bn.length; j++) {
				if (!(bn[i][j] == n || bn[i][j] == 0)) {
					System.out.print("|");
					System.out.printf("%2d", bn[i][j]);
				} else {
					System.out.print("|");
					System.out.print("■");
					bn[i][j] = 0;
				}
			}
			System.out.println("| ");
			makeHorizontalLine();
		}

		return bn;
	}

	static boolean searchNum(int n, int[][] bn) {

		boolean judge = false;

		for (int i = 0; i < bn.length; i++) {
			for (int j = 0; j < bn.length; j++) {
				if (bn[i][j] == n) {
					judge = true;
					break;
				}
			}
		}

		return judge;
	}

	static void makeHorizontalLine() {
		System.out.println("----------------");
	}

	static int[][] startBingo() {
		String[] bingo = { "B", "I", "N", "G", "O" };
		int[][] num = new int[5][5];

		int rowNum = 0;

		makeHorizontalLine();

		for (int i = 0; i < bingo.length; i++) {
			System.out.print("| ");
			System.out.print(bingo[i]);
		}
		System.out.println("|");
		makeHorizontalLine();

		for (int i = 0; i < num.length; i++) {
			for (int j = 0; j < num.length; j++) {
				int runNum = (int) (Math.random() * 15 + rowNum + 1);
				num[j][i] = runNum;
				while (sameNum(i,num,runNum)) {
					runNum = (int) (Math.random() * 15 + rowNum + 1);
				}
				num[j][i] = runNum;
			}
			rowNum += 15;
		}

		for (int i = 0; i < num.length; i++) {
			for (int j = 0; j < num.length; j++) {
				System.out.print("|");
				if (!(i == 2 && j == 2)) {
					System.out.printf("%2d", num[i][j]);
				} else {
					System.out.print("■");
					num[i][j] = 0;
				}
			}
			System.out.println("| ");
			makeHorizontalLine();
		}
		System.out.println("");

		return num;
	}
	
	static boolean sameNum(int x, int[][] bn, int n) {
		
		boolean same = false;
		
		for(int i = 0; i < bn.length; i++) {
			if (bn[i][x] == n) {
				same = true;
			}
		}
		
		return same;
	}

}
