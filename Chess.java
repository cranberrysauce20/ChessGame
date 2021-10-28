import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Scanner;

/**
 * This is Main class that program runs the Chess game
 * 
 * @author Sujay Sayini
 * @author Pauleene Jordan
 */

public class Chess {
	public static Board gameboard;
	public static boolean isDraw = false;
	public static boolean ifWhiteTurn = true;

	public static String prevLoc = null;
	public static String currLoc = null;
	public static String thirdValue = null;

	public static boolean enPassant = false;

	public static int enPassantX;
	public static int enPassantY;
	public static int enPassantToEliminateX;
	public static int enPassantToEliminateY;
	public static int enPassantCapturer1X;
	public static int enPassantCapturer1Y;
	public static int enPassantCapturer2X;
	public static int enPassantCapturer2Y;

	/**
 * This is a method called main that incorporates all of the different methods together to run the game.
 * It also receives the input of the players. 
 * @param args
 * @author Sujay Sayini
 * @author Pauleene Jordan
 */
	public static void main(String[] args) {
		// initialize game
		// BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		Scanner sc = new Scanner(System.in);
		String input = null;

		// startGame();

		gameboard = new Board();
		makeChessBoard();

		// game loop
		while (true) {
			// read the player's move
			input = null;

			try {

				// if white turn then make the white make a move
				if (ifWhiteTurn) {
					System.out.println("White's move: ");
				} else {
					System.out.println("Black's move: ");
				}
				// code that checks if white is check
				// boolean whiteCheck = gameboard.detectCheck(ifWhiteTurn);

				// if (whiteCheck == true) {
				// System.out.println("Check");
				// }
				// // code that checks if black is check
				// boolean blackCheck = gameboard.detectCheck(!ifWhiteTurn);
				// if (blackCheck == true) {
				// System.out.println("Check");
				// }

				if (gameboard.detectCheck(ifWhiteTurn)) {
					System.out.println("Check");
				}

				input = sc.nextLine();
				playerInput(input);

				makeChessBoard();

				// checks if white is checkmate
				// boolean whiteCheckMate = gameboard.detectCheck(ifWhiteTurn);
				if (gameboard.detectCheck(ifWhiteTurn)) {
					System.out.println("Checkmate");
					System.out.println("White wins");
					System.exit(1);
				}
				// checks if black is checkmate
				// boolean blackCheckMate = gameboard.detectCheck(!ifWhiteTurn);
				if (gameboard.detectCheck(!ifWhiteTurn)) {
					System.out.println("Checkmate");
					System.out.println("Black wins");
					System.exit(1);
				}

			} catch (Exception e) {
				System.out.println("Invalid input. Try again.");
			}
		}

	}
	// public static void startGame(){
	// gameboard = new Board();
	// }

	/**
 * This is a method called makeChessBoard that prints out the chessboard.
 * 
 * @author Sujay Sayini
 * @author Pauleene Jordan
 */
	public static void makeChessBoard() {
		String[][] chessBoard = new String[8][8];

		// make the tiles white or black
		boolean white = true;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (white) {
					chessBoard[i][j] = "   ";
					white = false;
				} else {
					chessBoard[i][j] = "## ";
					white = true;
				}
			}
			white = !(white);
		}

		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				if (gameboard.chess[x][y] != null) {
					chessBoard[x][y] = gameboard.chess[x][y].toString() + " ";
				}
			}
		}

		// print out the whole board
		for (int y = 0; y < 8; y++) {
			System.out.print(" ");
			for (int x = 0; x < 8; x++) {
				System.out.print(chessBoard[x][y]);
			}
			System.out.print(" " + (8 - y));
			System.out.println();
		}
		System.out.println(" a  b  c  d  e  f  g  h");
	}

	// // checks if the input of the player is allowed from 1-8 to a-h
	// // identification of illegal move
	// public static boolean legalInput(int a, int b, int c, int d) {
	// 	if ((a >= 0 && a <= 7) && (b >= 0 && b <= 7) && (c >= 0 && c <= 7) && (d >= 0 && d <= 7)) {
	// 		return true;
	// 	}
	// 	return false;

	// }

	/**
 * This is a method called allowedMove that receives the input of the player
 * 
 * @param input
 * @author Sujay Sayini
 * @author Pauleene Jordan
 */

	public static void playerInput(String input) {
		StringTokenizer string = null;
		int count = 0;
		input = input.toLowerCase();
		input = input.trim();

		String[] array = new String[100];

		string = new StringTokenizer(input);

		while (string.hasMoreTokens()) {
			array[count] = string.nextToken();
			count++;
		}

		if (count > 0 && count < 4) {
			if (count == 1) {
				if (array[0].equals("resign")) {
					if (ifWhiteTurn) {
						System.out.println("Black wins");
						System.exit(1);
					} else {
						System.out.println("White wins");
						System.exit(1);
					}

				} else if (array[0].equals("draw")) {
					if (isDraw == true) {
						System.out.println("Draw");
						System.exit(1);
					} else {
						System.out.println("Ask your opponent first.");
					}
				} else {
					System.out.println("Invalid input. Please try again.");
				}
			} else if (count == 2) {
				prevLoc = array[0];
				currLoc = array[1];
				if (prevLoc.length() == 2 && currLoc.length() == 2) {
					move();
				} else {
					System.out.println("Invalid input. Please try again.");
				}

			} else if (count == 3) {
				prevLoc = array[0];
				currLoc = array[1];
				if (prevLoc.length() == 2 && currLoc.length() == 2) {
					if (array[2].equals("draw?")) {
						move();
						isDraw = true;
					} else if (array[2].equals("q") || array[2].equals("r") || array[2].equals("b") || array[2].equals("n")) {
						thirdValue = array[2];
						move();
					} else {
						System.out.println("Invalid input. Please try again.");
					}
				} else {
					System.out.println("Invalid input. Please try again.");
				}
			}
		} else {
			System.out.println("Invalid input.Please try again.");
		}
		prevLoc = null;
		currLoc = null;
		thirdValue = null;

	}
	 
	/**
 * This is a method called promotePawn that allows the pawn to move if the move is legal 
 * 
 * * @param newx  x value of pawn
     * @param newy  y value of pawn
 * @author Sujay Sayini
 * @author Pauleene Jordan
 */
	public static void promotePawn(int newx, int newy) {
		if (gameboard.chess[newx][newy].toString().equalsIgnoreCase("wp") && newy == 0) {
			if (thirdValue == null || thirdValue.equals("q") || thirdValue.equals("Q")) {
				gameboard.chess[newx][newy] = new Queen(true);

			} else if (thirdValue.equals("r") || thirdValue.equals("R")) {
				gameboard.chess[newx][newy] = new Rook(true);

			} else if (thirdValue.equals("b") || thirdValue.equals("B")) {
				gameboard.chess[newx][newy] = new Bishop(true);

			} else if (thirdValue.equals("n") || thirdValue.equals("N")) {
				gameboard.chess[newx][newy] = new Knight(true);
			}
		} else if (gameboard.chess[newx][newy].toString().equalsIgnoreCase("bp") && newy == 7) {
			if (thirdValue == null || thirdValue.equals("q") || thirdValue.equals("Q")) {
				gameboard.chess[newx][newy] = new Queen(false);

			} else if (thirdValue.equals("r") || thirdValue.equals("R")) {
				gameboard.chess[newx][newy] = new Rook(false);

			} else if (thirdValue.equals("b") || thirdValue.equals("B")) {
				gameboard.chess[newx][newy] = new Bishop(false);

			} else if (thirdValue.equals("n") || thirdValue.equals("N")) {
				gameboard.chess[newx][newy] = new Knight(false);

			}
		}
	}

	/**
 * This is a method called move that takes care of the piece moving from the previous location to the new location 
 * 
 * * 
 * @author Sujay Sayini
 * @author Pauleene Jordan
 */
	

	public static void move() {
		//convert the prev and current locations into numbers we can use 
		int oldx = (int) prevLoc.toLowerCase().charAt(0) - (int)('a');
		int oldy = 7 - ((int) prevLoc.toLowerCase().charAt(1) - (int)('1'));

		int newx = (int) currLoc.toLowerCase().charAt(0) - (int)('a');
		int newy = 7 - ((int) currLoc.toLowerCase().charAt(1) - (int)('1'));

		// if (legalInput(oldx, oldy, newx, newy) == false) { // are the coordinates entered legal?
		// 	System.out.println("Invalid file and rank. Please try again.");
		// 	return;
		// }

		
		// if (gameboard.board[oldx][oldy] == null) { // is there a piece in the chosen spot?
		// 	System.out.println("A piece does not exist in this spot. Please try again.");
		// 	return;
		// }

		if (ifWhiteTurn != gameboard.chess[oldx][oldy].isWhite()) {
			System.out.println("The piece you are attempting to move does not belong to you. Please try again.");
			return;
		}

		boolean isNewSpotEmpty = true;
		if (gameboard.chess[newx][newy] != null) {
			if (gameboard.chess[newx][newy].isWhite() == ifWhiteTurn) {
				System.out.println("Cannot advance to a location taken by a piece of the same color. Please Try again.");
				return;
			}
			isNewSpotEmpty = false;
		}

		if (gameboard.testCastling(oldx, oldy, newx, newy)) {
			// gameboard.detectCheckMate();

			// no longer the first move of the piece
			gameboard.chess[newx][newy].first = false;

			ifWhiteTurn = !ifWhiteTurn;
			return;
		}

		if (enPassant == true) {
			enPassant = false;
			if (gameboard.testEnPassant(oldx, oldy, newx, newy) == true) {
				// gameboard.detectCheckMate();

				// no longer the first move of the piece
				gameboard.chess[newx][newy].first = false;

				ifWhiteTurn = !ifWhiteTurn;
				return;
			}
		}

		if (gameboard.isClear(oldx, oldy, newx, newy) == false) {
			System.out.println("Cannot move to that location because there are pieces on the way.");
			return;
		}

		if (gameboard.chess[oldx][oldy].allowedMove(oldx, oldy, newx, newy, isNewSpotEmpty) == false) {
			System.out.println("Illegal move. This piece cannot move this way.");
			return;
		}

		gameboard.chess[newx][newy] = gameboard.chess[oldx][oldy];// move the piece to the new location
		// no longer the first move of the piece
		gameboard.chess[newx][newy].first = false;

		if (gameboard.chess[newx][newy].toString().equalsIgnoreCase("wp") || gameboard.chess[newx][newy].toString().equalsIgnoreCase("bp")) {
			promotePawn(newx, newy);
		}

		gameboard.chess[oldx][oldy] = null;

		// detect enpassant scenario for the next turn
		if (gameboard.detectEnPassant(oldx, oldy, newx, newy)) {
			System.out.println("en passant");
			// System.out.println("enPassantx:"+enPassantX);
			// System.out.println("enPassanty:"+enPassantY);
		}

		// gameboard.detectCheckMate();
		ifWhiteTurn = !ifWhiteTurn;

	}

	
}

//detectenpassant, testEnPassant, testCastling
