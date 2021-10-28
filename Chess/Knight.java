
/**
 * This is Knight class for chess piece that extends Piece abstract class
 * 
 * @author Sujay Sayini
 * @author Pauleene Jordan
 */
public class Knight extends Piece {
	private String chessPiece;

	public Knight(boolean whiteTurn) {
		super(whiteTurn);

		if (whiteTurn) {
			this.chessPiece = "wN";
		} else {
			this.chessPiece = "bN";
		}
	}
	/**
	 * Check to see if the piece is allowed to move to the destination or not.
	 *
	 * @return true if it is allowed to 
     * @return false if it is not allowed to 
     * 
	 */

	public boolean allowedMove(int prevX, int prevY, int currX, int currY, boolean isEmpty) {

		// change in x and y
		int x = Math.abs(currX - prevX);
		int y = Math.abs(currY - prevY);

		if ((x == 2 && y == 1) || (x == 1 && y == 2)) {
			return true;
		} else {
			return false;
		}
	}

	public String toString() {
		return this.chessPiece;
	}
	
}
