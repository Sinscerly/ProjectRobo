package Connect;

import java.util.Random;
import java.util.Scanner;

public class main {

	static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int player1 = 0, player2 = 0;
		board play1 = new board();
		Random r = new Random();
		for(int i = 0; i < 42; i=i+2)
		{
			System.out.println("");
			System.out.print("Enter a row player 1: ");
			while(player1 < 1 || player1 > 7)									//check of het tussen de 1 en 7 is
			{
				player1 = scanner.nextInt();
				if(player1 < 1 || player1 > 7) System.out.println("Number out of bounds");
			}
			play1.place_move(player1, board.yellow);
			player2 = 0; player1 = 0;
			while(player2 == 0)
			{
				player2 = r.nextInt(7);
			}
			System.out.println("");
			System.out.print("Player2 entered: " + player2);
			play1.place_move(player2, board.red);
		}
	}
}