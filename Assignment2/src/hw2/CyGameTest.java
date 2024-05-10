package hw2;

public class CyGameTest
{
	
	public static void main(String args[]) 
	{
		CyGame game = new CyGame(16, 200);
		
		System.out.println(game);
		
		game.roll(3);
		System.out.println(game);
		game.roll(1);
		System.out.println(game);
		game.roll(1);
		System.out.println(game);
		game.roll(5);
		System.out.println(game);
		
//		// Print the board square types
//		System.out.println("Board squares: ");
//		System.out.println(game.getSquareType(0));
//		System.out.println(game.getSquareType(1));
//		System.out.println(game.getSquareType(2));
//		System.out.println(game.getSquareType(3));
//		System.out.println(game.getSquareType(4));
//		System.out.println(game.getSquareType(5));
//		System.out.println(game.getSquareType(6));
//		System.out.println(game.getSquareType(7));
//		System.out.println(game.getSquareType(8));
//		System.out.println(game.getSquareType(9));
//		System.out.println(game.getSquareType(10));
//		System.out.println(game.getSquareType(11));
//		System.out.println(game.getSquareType(12));
//		System.out.println(game.getSquareType(13));
//		System.out.println(game.getSquareType(14));
//		System.out.println(game.getSquareType(15));
//		
//		// Player 1 to JUMP_FORWARD square
//		game.roll(2);
//		System.out.println("Expect Player 1 on sqaure 2 + 4 = 6.");
//		System.out.println(game);
//		
//		// Player 2 to PAY_PLAYER square
//		game.roll(5);
//		System.out.println("Expect Player 1 money 220.");
//		System.out.println("Expect Player 2 money 180.");
//		System.out.println(game);
//		
//		// Player 1 is now on STUCK, roll an odd value
//		game.roll(5);
//		System.out.println("Expect Player 1 on sqaure 6 (not moved).");
//		System.out.println(game);
//		
//		// Player 2 to EXTRA_TURN
//		game.roll(2);
//		System.out.println("Expect Player 2 is still current player.");
//		System.out.println(game);
//		game.roll(6);
//		System.out.println("Expect Player 1 is now current player.");
//		System.out.println(game);
//		
//		// Player 1 passes turn
//		game.endTurn();
//		System.out.println("Expect Player 1 has not changed.");
//		System.out.println(game);
//		
//		// Player 2 buys property unit
//		game.buyUnit();
//		System.out.println("Expect Player 2 has 2 units and it is now Player 1's turn.");
//		System.out.println(game);
//		
//		// Player 1 passes turn and Player 2 to CYCLONE
//		game.endTurn();
//		game.roll(2);
//		System.out.println("Expect Player 2 is in same location as Player 1.");
//		System.out.println(game);
//		
//		// Player 1 passing though PASS_GO
//		game.roll(2);
//		game.roll(2);
//		game.roll(6);
//		System.out.println("Expect Player 1 has $420");
//		System.out.println(game);
//		
//		// Player 1 has over $400, the game is over
//		System.out.println("Expect game over.");
//		System.out.println("Is game ended: " + game.isGameEnded());
		
	}
}