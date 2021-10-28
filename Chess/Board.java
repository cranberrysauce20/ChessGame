/**
 * This is Board class and it implements the methods that would be used on Chess class 
 * 
 * @author Sujay Sayini
 * @author Pauleene Jordan
 */

public class Board {
	Piece[][] chess;
/**
 * This is a method called Board that checks that declares the chessboard
 */

	public Board() {
		chess = new Piece[8][8];
		defaultBoard();
	}
/**
 * This is a method called defaultBoard that checks that initializes the chessboard. 
 * The method creates the chessboard before the game starts
 * 
 */

	public void defaultBoard() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				chess[i][j] = null;
			}
		}

		// white chess pieces
		for (int i = 0; i < 8; i++) {
			chess[i][1] = new Pawn(false);
		}
		chess[0][0] = new Rook(false);
		chess[1][0] = new Knight(false);
		chess[2][0] = new Bishop(false);
		chess[3][0] = new Queen(false);
		chess[4][0] = new King(false);
		chess[5][0] = new Bishop(false);
		chess[6][0] = new Knight(false);
		chess[7][0] = new Rook(false);

		// black chess pieces
		for (int i = 0; i < 8; i++) {
			chess[i][6] = new Pawn(true);
		}
		chess[0][7] = new Rook(true);
		chess[1][7] = new Knight(true);
		chess[2][7] = new Bishop(true);
		chess[3][7] = new Queen(true);
		chess[4][7] = new King(true);
		chess[5][7] = new Bishop(true);
		chess[6][7] = new Knight(true);
		chess[7][7] = new Rook(true);

	}
/**
 * This is a method called isClear that checks to see if a chess piece is blocking the path for the chess piece to move 
 * @param prevX
 * @param prevY
 * @param currX
 * @param currY
 * @return true if the path is clear
 * @return false if the path is not clear
 */

	public boolean isClear(int prevX, int prevY, int currX, int currY) {
		boolean isClear = true;
		int x, y, deltaX, deltaY, oldX, oldY;
		deltaX = 0;
		deltaY = 0;
		String prevLoc = chess[prevX][prevY].toString();
		if (prevLoc.equalsIgnoreCase("wn") || prevLoc.equalsIgnoreCase("bn")) {
			return true;
		}
		// change in x and y
		x = currX - prevX;
		y = currY - prevY;
		// write prev x and y 
		oldX = prevX;
		oldY = prevY;

		if (x > 0) {
			deltaX = 1;
		} else if (x < 0) {
			deltaX = -1;
		}

		if (y > 0) {
			deltaY = 1;
		} else if (y < 0) {
			deltaY = -1;
		}
		if (x == 0) {
			oldY += deltaY;
			for (int i = 0; i < Math.abs(y) - 1; i++) {
				if (chess[oldX][oldY] != null) {
					isClear = false;
					break;
				}
				oldY = oldY + deltaY;
			}
			return isClear;
		}

		if (y == 0) {
			oldX += deltaX;
			for (int i = 0; i < Math.abs(x) - 1; i++) {
				if (chess[oldX][oldY] != null) {
					isClear = false;
					break;
				}
				oldX += deltaX;
			}
			return isClear;
		}

		if (x != 0 && y != 0) {
			oldX += deltaX;
			prevY += deltaY;
			for (int i = 0; i < Math.abs(y) - 1; i++) {
				if (chess[oldX][oldY] != null) {
					isClear = false;
					break;
				}

				oldX += deltaX;
				oldY += deltaY;
			}
		}

		return isClear;
	}

/**
 * This is a method called castling that allows king to move two spaces to its right or left,
 * while the rook on that side moves to the opposite side of the king.
 * @param from
 * @param to
 * @return true if there is a castling 
 * @return false if there is not a castling 
 * 
 * 
 */


	public boolean castling(String from, String to) {
		int prevX = (int) from.toLowerCase().charAt(0) - (int) ('a');
		int prevY = 7 - ((int) from.toLowerCase().charAt(1) - (int) ('1'));
		int currX = (int) to.toLowerCase().charAt(0) - (int) ('a');
		int currY = 7 - ((int) from.toLowerCase().charAt(1) - (int) ('1'));
		int dx = currX - prevX;
		String isKing = chess[prevX][prevY].toString();
		if (isKing.equals("wK") && chess[prevX][prevY].first == true) {
			if (dx == 2) {
				if (chess[7][7] != null) {
					if (chess[7][7].toString().equals("wR")) {
						if (isClear(prevX, prevY, currX, currY)) {
							chess[prevX][prevY] = null;
							chess[7][7] = null;
							chess[currX][currY] = new King(true);
							chess[currX][currY].first = false;
							chess[5][7] = new Rook(true);
							return true;
						}
					}
				}
			}
			if (dx == 2) {
				if (chess[0][7] != null) {

					if (chess[0][7].toString().equalsIgnoreCase("wr")) {

						if (isClear(prevX, prevY, currX, currY)) {

							chess[prevX][prevY] = null;
							chess[0][7] = null;
							chess[currX][currY] = new King(true);
							chess[currX][currY].first = false;
							chess[3][7] = new Rook(true);
							return true;
						}
					}
				}
			}
		}
		if (isKing.equals("bK") && chess[prevX][prevY].first == true) {
			if (dx == 2) {
				if (chess[7][0] != null) {
					if (chess[7][0].toString().equalsIgnoreCase("br")) {
						if (isClear(prevX, prevY, currX, currY)) {
							chess[prevX][prevY] = null;
							chess[7][0] = null;
							chess[currX][currY] = new King(false);
							chess[currX][currY].first = false;
							chess[5][0] = new Rook(false);
							return true;
						}
					}
				}
			}
			if (dx == -2) {
				if (chess[0][0] != null) {

					if (chess[0][0].toString().equals("br")) {

						if (isClear(prevX, prevY, currX, currY)) {

							chess[prevX][prevY] = null;
							chess[0][0] = null;
							chess[currX][currY] = new King(false);
							chess[currX][currY].first = false;
							chess[3][0] = new Rook(false);
							return true;
						}
					}
				}
			}
		}
		return false;
	}
/**
 * This is a method called deteckCheck that checks if the King is check
 * @param whiteturn
 * @return true if there is a check 
 * @return false if there is a check 
 */
	public boolean detectCheck(boolean whiteturn) {
		int kingLocX = 0;
		int kingLocY = 0;

		if (whiteturn) {
			for (int y = 0; y < 8; y++) {
				for (int x = 0; x < 8; x++) {
					if (chess[x][y] != null) {
						if (chess[x][y].toString().equalsIgnoreCase("wk")) {
							kingLocX = x;
							kingLocY = y;
							break;
						}
					}
				}
			}

			for (int y = 0; y < 8; y++) {
				for (int x = 0; x < 8; x++) {
					if (chess[x][y] != null) {
						if (chess[x][y].isWhite() == false) {
							if (chess[x][y].allowedMove(x, y, kingLocX, kingLocY, true)) {
								if (isClear(x, y, kingLocX, kingLocY)) {
									return true;
								}
							}
						}
					}
				}
			}
		}

		if (!whiteturn) {
			for (int y = 0; y < 8; y++) {
				for (int x = 0; x < 8; x++) {
					if (chess[x][y] != null) {
						if (chess[x][y].toString().equalsIgnoreCase("bk")) {
							kingLocX = x;
							kingLocY = y;
							break;
						}
					}
				}
			}

			for (int y = 0; y < 8; y++) {
				for (int x = 0; x < 8; x++) {
					if (chess[x][y] != null) {
						if (chess[x][y].isWhite() == true) {
							if (chess[x][y].allowedMove(x, y, kingLocX, kingLocY, true)) {
								if (isClear(x, y, kingLocX, kingLocY)) {
									return true;
								}
							}
						}
					}
				}
			}
		}
		return false;
	}
/**
 * This is a method called checkEnPassant that returns true or false if it is legal to perform an en passant
 * @param prevX
 * @param prevY
 * @param currX
 * @param currY
 * @return true if it is legal to perform an en passant
 * @return true if it is not legal to perform an en passant
 * 
 */

	public boolean checkEnPassant(int prevX, int prevY, int currX, int currY) {
		Chess.EliminateX = currX;
		Chess.EliminateY = currY;

		if (chess[currX][currY] == null) {
			return false;
		}

		if (!(chess[currX][currY].toString().equalsIgnoreCase("wp")
				|| chess[currX][currY].toString().equalsIgnoreCase("bp"))) {
			return false;
		}
		if (Math.abs(currX - prevY) != 2) { 
			return false;
		}

		int x = currX - 1;

		if (x >= 0) {
			Chess.Capturer1X = x;
			Chess.Capturer1Y = currY;

			if (chess[x][currY] != null) {
				if (chess[x][currY].toString().equalsIgnoreCase("wp") && Chess.ifWhiteTurn == false) {
					Chess.enPassant = true;
					Chess.X = currX;
					Chess.Y = 2;

					return true;
				}

				if (chess[x][currX].toString().equalsIgnoreCase("bp") && Chess.ifWhiteTurn == true) {
					Chess.enPassant = true;
					Chess.X = currX;
					Chess.Y = 5;

					return true;
				}
			}
		}

		x = currX + 1;
		if (x <= 7) {
			Chess.Capturer2X = x;
			Chess.Capturer2Y = currY;
			if (chess[x][currY] != null) {
				if (chess[x][currY].toString().equalsIgnoreCase("bp") && Chess.ifWhiteTurn == true) {
					Chess.enPassant = true;
					Chess.X = currX;
					Chess.Y = 5;
					return true;
				}
				if (chess[x][currY].toString().equalsIgnoreCase("wp") && Chess.ifWhiteTurn == false) {
					Chess.enPassant = true;
					Chess.X = currX;
					Chess.Y = 2;
					return true;
				}

				
			}
		}

		return false;
	}
/**
 * This is a method called doEnPassant that performs the special move en Passant in the chess game.
 * @param prevX
 * @param prevY
 * @param currX
 * @param currY
 * @return true if the enpassant is successful
 * @return true if the enpassant is not successful
 * 
 */
	public boolean doEnPassant(int prevX, int prevY, int currX, int currY) {
		if (((Chess.Capturer1X == prevX) && (Chess.Capturer1Y == prevY)) || ((Chess.Capturer2X == prevX) && (Chess.Capturer2Y == prevY))) {
			if (currX == Chess.X && currY == Chess.Y) {
				chess[Chess.EliminateX][Chess.EliminateY] = null;
				chess[currX][currY] = chess[prevX][prevY];
				chess[prevX][prevY] = null;

				return true;
			}
		}

		return false;
	}

}