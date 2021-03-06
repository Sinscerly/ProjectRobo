package Connect;

import java.util.Scanner;

public class Game {
	static Scanner scanner = new Scanner(System.in);
	private int whosTurn = 0, beginingMove = 0;
	Box whoBegan = Board.red;
	public static boolean seeProgress = false;
	private int last_AI_move = 0;
	Game() {
	}

	void pvp() {
		Board.setupBoard();
		while (GoodMoves.checkWin(Board.getBoard(), true) == false && Board.checkFull(Board.getBoard())) {
			if (whosTurn == 0) {
				executeVision eV = new executeVision();
				updateGrid(eV.execute(whosTurn));
				whosTurn = 1;
			} else {
				executeVision eV = new executeVision();
				updateGrid(eV.execute(whosTurn));
				whosTurn = 0;
			}
		}
	}

	void pvc(int difficulty) throws InterruptedException {
		AI AI = new AI();
		Board.setupBoard();
		Board.printBoard();
		beginingMove = playerFirst();
		whosTurn = beginingMove;
		if(beginingMove == 0) {
			whoBegan = Board.red;
		} else {
			whoBegan = Board.yellow;
		}
		AI = whichAI();
		executeVision eV = new executeVision();
		executeMotor  eM = new executeMotor();
		while (GoodMoves.checkWin(Board.getBoard(), true) == false && Board.checkFull(Board.getBoard())) {
			if (whosTurn == 0) {
				eV.getCurrent_board(Board.getBoard());
				AI(AI, Board.red, Board.yellow);
				//stack now false if stack exists it needs to be true
				eM.AI_Input_Row(last_AI_move, true, true);
				System.out.println("place the the ai!");
				Thread.sleep(5000);
				updateGrid(eV.execute(beginingMove));
				//stack needs to be always false here! Otherwise it will cheat the game.
				eM.AI_Input_Row(last_AI_move, false, false);
				whosTurn = 1;
			} else {
				eM.Player_on_set(true);
				System.out.println("place your move!");
				Thread.sleep(6000);
				eV.getCurrent_board(Board.getBoard());
				updateGrid(eV.execute(beginingMove));
				eM.Player_on_set(false);
				whosTurn = 0;
			}
		}
		Board.printBoard();
	}

	void cvc(AI Ai1, AI Ai2) throws InterruptedException {
		Board.setupBoard();
		Board.printBoard();

		while (GoodMoves.checkWin(Board.getBoard(), true) == false && Board.checkFull(Board.getBoard())) {
			if (whosTurn == 0) {
				AI(Ai1, Board.red, Board.yellow);
				if (seeProgress) {
					Board.printBoard();
					Thread.sleep(1000);
				}
				whosTurn = 1;
			} else {
				AI(Ai2, Board.yellow, Board.red);
				if (seeProgress) {
					Board.printBoard();
					Thread.sleep(1000);
				}
				whosTurn = 0;
			}
		}
		Board.printBoard();
	}

	void cvcad(int difficulty1, int difficulty2, AI Ai1, AI Ai2) throws InterruptedException {
		System.out.println(difficulty1 + " vs " + difficulty2);
		this.cvc(Ai1, Ai2);
		// puts who won in the print string.
		if (whosTurn == 1)
			Main.printString += difficulty1 + " VS " + difficulty2 + ": cpu 1 wins.";
		else
			Main.printString += difficulty1 + " VS " + difficulty2 + ": cpu 2 wins.";
	}

	void player(Box color) {
		Board.printBoard();
		int player = 0;
		System.out.println("");
		System.out.print("Enter a row player: ");
		while (player < 1 || player > 7) // check of het tussen de 1 en
											// 7 is
		{
			player = scanner.nextInt();
			if (player < 1 || player > 7)
				System.out.println("Number out of bounds");
		}
		Board.placeMove(player - 1, color);
	}

	void AI(AI ai, Box Ai, Box eAi) {
		int set = ai_move(ai, Ai, eAi);
		Board.placeMove(set, Ai);
	}

	public int ai_move(AI ai, Box Ai, Box eAi) {
		// change set to move
		int ai_set = 0;
		ai.start = System.currentTimeMillis();
		ai_set = ai.doSet(Board.getBoard(), whoBegan, Ai, eAi);
		ai.end = System.currentTimeMillis();
		System.out.println(ai.toString());
		System.out.print("Computer placed in row: " + (ai_set + 1));
		last_AI_move = ai_set;
		System.out.println("Took: " + ((ai.end - ai.start) / 1000) + "S.");
		return ai_set;
	}
	
	private int playerFirst(){
		System.out.println("Do you want to begin?");
		System.out.println("y/n");
		if(scanner.next().equals("y"))
			return 1;
		else
			return 0;
	}
	private AI whichAI() {
		System.out.println("Do you want to play against a miniMax Ai or our own Ai?");
		System.out.println("1/2");
		if(scanner.nextInt() == 1) {
			int diff = Main.scanDiff(Main.AiRed);
			return new MiniMax(diff);
		}
		else
			return new OwnAI();
	}
	private void updateGrid(Box[][] fotoBoard) {
		Board.copyFotoBoard(fotoBoard);
	}
}
