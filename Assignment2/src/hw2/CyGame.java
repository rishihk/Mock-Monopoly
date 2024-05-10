package hw2;

/**
 * Model of a Monopoly-like game. Two players take turns rolling dice to move around a board.
 * The game ends when one of the players has at least MONEY_TO_WIN money or one of the players goes bankrupt (has negative money).
 * 
 * @author - Hrishikesha Kyathsandra.
 * @version - 1.0.
 */
public class CyGame 
{

	/**
	 * Do nothing square type.
	 */
	public static final int DO_NOTHING = 0;

	/**
	 * Pass go square type.
	 */
	public static final int PASS_GO = 1;

	/**
	 * Cyclone square type.
	 */
	public static final int CYCLONE = 2;

	/**
	 * Pay the other player square type.
	 */
	public static final int PAY_PLAYER = 3;

	/**
	 * Get an extra turn square type.
	 */
	public static final int EXTRA_TURN = 4;

	/**
	 * Jump forward square type.
	 */
	public static final int JUMP_FORWARD = 5;

	/**
	 * Stuck square type.
	 */
	public static final int STUCK = 6;

	/**
	 * Points awarded when landing on or passing over go.
	 */
	public static final int PASS_GO_PRIZE = 200;

	/**
	 * The amount payed to the other player per unit when landing on a PAY_PLAYER
	 * square.
	 */
	public static final int PAYMENT_PER_UNIT = 20;

	/**
	 * The amount of money required to win.
	 */
	public static final int MONEY_TO_WIN = 400;

	/**
	 * The cost of one unit.
	 */
	public static final int UNIT_COST = 50;

	/**
	 * Tracks the value of Player 1's money throughout the game.
	 */
	private static int player1Money;

	/**
	 * Tracks the value of Player 1's square throughout the game.
	 */
	private static int player1Square;

	/**
	 * Tracks the value of Player 1's property units throughout the game.
	 */
	private static int player1Units;

	/**
	 * Tracks the value of Player 2's money throughout the game.
	 */
	private static int player2Money;

	/**
	 * Tracks the value of Player 2's square throughout the game.
	 */
	private static int player2Square;

	/**
	 * Tracks the value of Player 2's property units throughout the game.
	 */
	private static int player2Units;

	/**
	 * Keeps track of who the current player is.
	 */
	private static int currentPlayer;

	/**
	 * Last square number.
	 */
	private static int lastSquare;

	/**
	 * Boolean to check whether game is over or not.
	 */
	private static boolean gameEnded;

	/**
	 * Constructs a game that has the given number of squares, given starting money and and starts both players on square 0.
	 * The game starts with Player 1, and both players start with 1 property unit.
	 * 
	 * @param numSquares    - number of squares on board
	 * @param startingMoney - starting money for each player
	 */
	public CyGame(int numSquares, int startingMoney) 
	{
		player1Money = startingMoney;
		player2Money = startingMoney;

		player1Square = 0;
		player2Square = 0;

		player1Units = 1;
		player2Units = 1;

		currentPlayer = 1;
		
		gameEnded = false;

		lastSquare = numSquares;
	}

	/**
	 * Get the current player.
	 * 
	 * @return - 1 if it is currently Player 1's turn, otherwise 2.
	 */
	public int getCurrentPlayer() 
	{
		return currentPlayer;
	}

	/**
	 * Get Player 1's money.
	 * 
	 * @return - Player 1's money.
	 */
	public int getPlayer1Money() 
	{
		return player1Money;
	}

	/**
	 * Get Player 1's units.
	 * 
	 * @return - Player 1's units.
	 */
	public int getPlayer1Units() 
	{
		return player1Units;
	}

	/**
	 * Get Player 1's square.
	 * 
	 * @return - Player 1's square number
	 */
	public int getPlayer1Square() 
	{
		return player1Square;
	}

	/**
	 * Get Player 2's property units.
	 * 
	 * @return - Player 2's property units.
	 */
	public int getPlayer2Units() 
	{
		return player2Units;
	}

	/**
	 * Get Player 2's money.
	 * 
	 * @return - Player 2's money.
	 */
	public int getPlayer2Money() 
	{
		return player2Money;
	}

	/**
	 * Get Player 2's square.
	 * 
	 * @return - Player 2's square number.
	 */
	public int getPlayer2Square() 
	{
		return player2Square;
	}

	/**
	 * Method called to indicate the dice has been rolled.
	 * 
	 * @param value - the number rolled by the dice (1 to 6 inclusive)
	 */
	public void roll(int value) 
	{

		if (!isGameEnded()) 
		{

			if (getCurrentPlayer() == 1) 
			{
				// not stuck
				if (getSquareType(player1Square) == STUCK && value % 2 == 0 || getSquareType(player1Square) != STUCK) 
				{
					player1Square += value;
				}

				// wrapping
				if (getPlayer1Square() > lastSquare - 1) 
				{
					player1Square = player1Square % (lastSquare);
					// pass go
					player1Money += PASS_GO_PRIZE;
					isGameEnded();
				}

				// jump forward
				else if (getSquareType(player1Square) == JUMP_FORWARD) 
				{
					player1Square += 4;

					// wrapping
					if (player1Square > lastSquare - 1) 
					{
						player1Square = player1Square % (lastSquare);
						// pass go
						player1Money += PASS_GO_PRIZE;
						isGameEnded();
					}
					
					endTurn();
				}

				// pay player
				else if (getSquareType(player1Square) == PAY_PLAYER) 
				{
					player1Money -= PAYMENT_PER_UNIT * player2Units;
					player2Money += PAYMENT_PER_UNIT * player2Units;
					isGameEnded();
					endTurn();
				}

				// extra turn
				else if (getSquareType(player1Square) == EXTRA_TURN) 
				{
					endTurn();
					endTurn();
				}

				// cyclone
				else if (getSquareType(player1Square) == CYCLONE) 
				{
					player1Square = player2Square;
					endTurn();
				}

				// do nothing
				else if (getSquareType(player1Square) == DO_NOTHING) 
				{
					endTurn();
				}

				// stuck
				else if (getSquareType(player1Square) == STUCK) 
				{
					endTurn();
				}

			}

			else if (getCurrentPlayer() == 2) 
			{
				// not stuck
				if (getSquareType(player2Square) == STUCK && value % 2 == 0 || getSquareType(player2Square) != STUCK)
				{
					player2Square += value;
				}

				// wrapping
				if (player2Square > lastSquare - 1) 
				{
					player2Square = player2Square % (lastSquare);
					// pass go
					player2Money += PASS_GO_PRIZE;
					isGameEnded();
				}

				// jump forward
				else if (getSquareType(player2Square) == JUMP_FORWARD)
				{
					player2Square += 4;

					if (player2Square > lastSquare - 1) 
					{
						player2Square = player2Square % (lastSquare - 1);
						// pass go
						player2Money += PASS_GO_PRIZE;
						isGameEnded();
					}

					endTurn();
				}

				// pay player
				else if (getSquareType(player2Square) == PAY_PLAYER) 
				{
					player2Money -= PAYMENT_PER_UNIT * player1Units;
					player1Money += PAYMENT_PER_UNIT * player1Units;
					isGameEnded();
					endTurn();
				}

				// extra turn
				else if (getSquareType(player2Square) == EXTRA_TURN) 
				{
					endTurn();
					endTurn();
				}

				// cyclone
				else if (getSquareType(player2Square) == CYCLONE) 
				{
					player2Square = player1Square;
					endTurn();
				}

				// do nothing
				else if (getSquareType(player2Square) == DO_NOTHING) 
				{
					endTurn();
				}

				// stuck
				else if (getSquareType(player2Square) == STUCK) 
				{
					endTurn();
				}
				
			}

		}

	}

	/**
	 * Get the type of square for the given square number.
	 * 
	 * @param square - the square number
	 * @return - square type.
	 */
	public int getSquareType(int square) 
	{
		if (square == 0) 
		{
			return PASS_GO;
		}

		else if (square == lastSquare - 1) 
		{
            return CYCLONE;
		}

		else if (square % 5 == 0) 
		{
			return PAY_PLAYER;
		}

		else if (square % 7 == 0 || square % 11 == 0) 
		{
			return EXTRA_TURN;
		}

		else if (square % 3 == 0) 
		{
			return STUCK;
		}

		else if (square % 2 == 0) 
		{
			return JUMP_FORWARD;
		}

		else 
		{
			return DO_NOTHING;
		}
	}

	/**
	 * Method called to indicate the current player passes or completes their turn.
	 */
	public void endTurn() 
	{
		if (getCurrentPlayer() == 1) 
		{
			currentPlayer = 2;
		}

		else if (getCurrentPlayer() == 2) 
		{
			currentPlayer = 1;
		}
	}

	/**
	 * Method called to indicate the current player attempts to sell one unit.
	 */
	public void sellUnit() 
	{
		
		if( !isGameEnded() ) 
		{

			if (getCurrentPlayer() == 1 && player1Units >= 1 ) 
			{
				player1Money += UNIT_COST;
				isGameEnded();
				player1Units -= 1;
				endTurn();
			}
	
			else if (getCurrentPlayer() == 2 && player2Units >= 1) 
			{
				player2Money += UNIT_COST;
				isGameEnded();
				player2Units -= 1;
				endTurn();
		    }
			
		}
		
	}

	/**
	 * Method called to indicate the current player attempts to buy one unit.
	 */
	public void buyUnit()
	{

		if( !isGameEnded() ) 
		{
		
			if (getCurrentPlayer() == 1 && getPlayer1Money() >= UNIT_COST && getSquareType(player1Square) == DO_NOTHING) 
			{
				player1Money -= UNIT_COST;
				isGameEnded();
				player1Units += 1;
				endTurn();
			}
	
			else if (getCurrentPlayer() == 2 && getPlayer2Money() >= UNIT_COST && getSquareType(player2Square) == DO_NOTHING) 
			{
				player2Money -= UNIT_COST;
				isGameEnded();
				player2Units += 1;
				endTurn();
			}
			
		}
		
	}

	/**
	 * Method call to check whether the game is ended or not.
	 * 
	 * @return - true if the game is over, false otherwise.
	 */
	public boolean isGameEnded() 
	{

		if (getPlayer1Money() >= MONEY_TO_WIN || getPlayer2Money() >= MONEY_TO_WIN) 
		{
			gameEnded = true;
		}

		if (getPlayer1Money() < 0 || getPlayer2Money() < 0) 
		{
			gameEnded = true;
		}

		return gameEnded;
	}

	/**
	 * Returns a one-line string representation of the current game state.
	 * 
	 * @return - one line string representation of the game state
	 */
	public String toString() 
	{

		String fmt = "Player 1%s: (%d, %d, $%d) Player 2%s: (%d, %d, $%d)";

		String player1Turn = "";

		String player2Turn = "";

		if (getCurrentPlayer() == 1) 
		{
			player1Turn = "*";
		}

		else 
		{
			player2Turn = "*";
		}

		return String.format(fmt, player1Turn, getPlayer1Square(), getPlayer1Units(), getPlayer1Money(), player2Turn, getPlayer2Square(), getPlayer2Units(), getPlayer2Money());
	}
}


