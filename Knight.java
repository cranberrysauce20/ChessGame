/**
 * This is Knight class and it implements the Knight chesspieces
 * 
 * @author Sujay Sayini
 * @author Pauleene Jordan
 */
public class Knight extends Piece {

	private String chessPiece;

	public Knight(boolean whiteTurn) {
		super(whiteTurn);

		// this.setWhiteTurn(whiteTurn);
		// check if white piece is playing or black
		if (whiteTurn) {
			this.chessPiece = "wN";
		} else {
			this.chessPiece = "bN";
		}
	}

	public boolean allowedMove(int prevX, int prevY, int currX, int currY, boolean isEmpty) {

		// change in x and y
		int x = Math.abs(currX - prevX);
		int y = Math.abs(currY - prevY);

		if ((x == 2 && y == 1) || (x == 1 && y == 2)) {
			return true;
		} else {
			return false;
		}
		// } else if (x == 1 && y == 2) {
		// return true;
		// }
		// return false;
	}

	public String toString() {
		return this.chessPiece;
	}
	
}
