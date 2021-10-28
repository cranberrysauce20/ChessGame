
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Scanner;

/**
 * This is Main class that program runs first
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

	public static int X, Y, EliminateX, EliminateY, Capturer1X, Capturer1Y, Capturer2X, Capturer2Y;

	public static void main(String[] args) {
		// initialize game
		Scanner sc = new Scanner(System.in);
		String input = null;

		gameboard = new Board();
		makeChessBoard();

		while (true) {
			input = null;

			try {
				if (ifWhiteTurn) {
					System.out.println("White's move: ");
				} else {
					System.out.println("Black's move: ");
				}

				if (gameboard.detectCheck(ifWhiteTurn)) {
					System.out.println("Check");
				}

				input = sc.nextLine();
				playerInput(input);

				makeChessBoard();

				// checks if white is checkmate
				if (gameboard.detectCheck(ifWhiteTurn)) {
					System.out.println("Checkmate");
					System.out.println("White wins");
					System.exit(1);
				}
				// checks if black is checkmate
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

	
/**
 * This is a method called playerInput that receives the input of the player 
 * and it parses the input into data we can use
 * 
 * @param input
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
						System.out.println("Illegal move, try again.");
					}
				} else {
					System.out.println("Illegal move, try again.");
				}
			} else if (count == 2) {
				prevLoc = array[0];
				currLoc = array[1];
				if (prevLoc.length() == 2 && currLoc.length() == 2) {
					move();
				} else {
					System.out.println("Illegal move, try again.");
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
						System.out.println("Illegal move, try again.");
					}
				} else {
					System.out.println("Illegal move, try again.");
				}
			}
		} else {
			System.out.println("Illegal move, try again.");
		}
		prevLoc = null;
		currLoc = null;
		thirdValue = null;

	}
	 /**
     * Takes in the coordinates of the pawn and it replaces the the current pawn with the promotion in it's position
     * 
     * @param currX  x value of pawn
     * @param currY  y value of pawn
     */ 
	public static void promotePawn(int currX, int currY) {
		if (gameboard.chess[currX][currY].toString().equalsIgnoreCase("wp") && currY == 0) {
			if (thirdValue == null || thirdValue.equals("q") || thirdValue.equals("Q")) {
				gameboard.chess[currX][currY] = new Queen(true);

			} else if (thirdValue.equals("r") || thirdValue.equals("R")) {
				gameboard.chess[currX][currY] = new Rook(true);

			} else if (thirdValue.equals("b") || thirdValue.equals("B")) {
				gameboard.chess[currX][currY] = new Bishop(true);

			} else if (thirdValue.equals("n") || thirdValue.equals("N")) {
				gameboard.chess[currX][currY] = new Knight(true);
			}
		} else if (gameboard.chess[currX][currY].toString().equalsIgnoreCase("bp") && currY == 7) {
			if (thirdValue == null || thirdValue.equals("q") || thirdValue.equals("Q")) {
				gameboard.chess[currX][currY] = new Queen(false);

			} else if (thirdValue.equals("r") || thirdValue.equals("R")) {
				gameboard.chess[currX][currY] = new Rook(false);

			} else if (thirdValue.equals("b") || thirdValue.equals("B")) {
				gameboard.chess[currX][currY] = new Bishop(false);

			} else if (thirdValue.equals("n") || thirdValue.equals("N")) {
				gameboard.chess[currX][currY] = new Knight(false);

			}
		}
	}

	 /**
     * This function takes care of the piece moving from the previous location to the new location
     * 
     */

	public static void move() {
		//convert the prev and current locations into numbers we can use 
		int prevX = (int) prevLoc.toLowerCase().charAt(0) - (int)('a');
		int prevY = 7 - ((int) prevLoc.toLowerCase().charAt(1) - (int)('1'));

		int currX = (int) currLoc.toLowerCase().charAt(0) - (int)('a');
		int currY = 7 - ((int) currLoc.toLowerCase().charAt(1) - (int)('1'));

		// if (legalInput(oldx, oldy, newx, newy) == false) { // are the coordinates entered legal?
		// 	System.out.println("Invalid file and rank. Please try again.");
		// 	return;
		// }

		
		// if (gameboard.board[oldx][oldy] == null) { // is there a piece in the chosen spot?
		// 	System.out.println("A piece does not exist in this spot. Please try again.");
		// 	return;
		// }

		if (ifWhiteTurn != gameboard.chess[prevX][prevY].isWhite()) {
			System.out.println("Illegal move, try again.");
			return;
		}

		boolean isNewSpotEmpty = true;
		if (gameboard.chess[currX][prevY] != null) {
			if (gameboard.chess[currX][prevY].isWhite() == ifWhiteTurn) {
				System.out.println("Illegal move, try again.");
				return;
			}
			isNewSpotEmpty = false;
		}

		if (gameboard.castling(prevLoc, currLoc)) {
			gameboard.chess[newx][newy].first = false;

			ifWhiteTurn = !ifWhiteTurn;
			return;
		}

		if (enPassant == true) {
			enPassant = false;
			if (gameboard.doEnPassant(prevX, prevY, currX, prevY) == true) {
				gameboard.chess[currX][prevY].first = false;

				ifWhiteTurn = !ifWhiteTurn;
				return;
			}
		}

		if (gameboard.isClear(prevX, prevY, currX, prevY) == false) {
			System.out.println("Illegal move, try again.");
			return;
		}

		if (gameboard.chess[prevX][prevY].allowedMove(prevX, prevY, currX, prevY, isNewSpotEmpty) == false) {
			System.out.println("Illegal move, try again.");
			return;
		}

		gameboard.chess[currX][prevY] = gameboard.chess[prevX][prevY];
		gameboard.chess[currX][prevY].first = false;

		if (gameboard.chess[prevX][prevY].toString().equalsIgnoreCase("wp") || gameboard.chess[prevX][prevY].toString().equalsIgnoreCase("bp")) {
			promotePawn(currX, prevY);
		}

		gameboard.chess[prevX][prevY] = null;

		if (gameboard.checkEnPassant(prevX, prevY, currX, prevY)) {
			System.out.println("en passant");
		}
		ifWhiteTurn = !ifWhiteTurn;

	}
}

