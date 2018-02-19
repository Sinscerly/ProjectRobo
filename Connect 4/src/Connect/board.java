package Connect;

public class board {

	private static int width = 7, height = 6;
	private static Object[][] board = new Object[width][height];
	public static Empty empty = new Empty();										//state of a box in the board
	public static Red red = new Red();												//state of a box in the board
	public static Yellow yellow = new Yellow();									//state of a box in the board
	
	public board()
	{		
		for(int x = 0; x < width; x++)
		{
			for(int y = 0; y < height; y++)
			{
				board[x][y] = empty;
			}
		}
	}
	public int place_move(int column, Object player)
	{
		//the x tells wich player does the move.
		int lowest_free = check_empty(column-1);
		if(lowest_free != 6)
		{
			board[column-1][lowest_free] = player;
		}
		else
		{
			System.out.println("Error there is no free row, see row: "+ column);
			print_board();
			return 0;
		}
		print_board();
		return 1;
	}
	private static int check_empty(int index_x)
	{
		for(int i = 0; i < 6; i++)
		{
			if(board[index_x][i] == empty) { return i;} 
		}
		return 6;
	}
	private static void print_board()
	{
		System.out.println("");
		System.out.println("| 1   2   3   4   5   6   7 |");
		for(int y = height -1; y >= 0; y--)
		{
			System.out.print("| ");
			for(int x = 0; x < width; x++)
			{
				if(board[x][y] == empty) System.out.print(empty.name + " | ");
				else if(board[x][y] == red) System.out.print(red.name + " | ");
				else System.out.print(yellow.name + " | ");
			}
			System.out.println();
		}
		
	}
}