
/**
 * This is Rook class for chess piece that extends Piece abstract class
 * 
 * @author Sujay Sayini
 * @author Pauleene Jordan
 */

public class Rook extends Piece {
    private String chessPiece;
	
	public Rook(boolean whiteTurn) {
		super(whiteTurn);

		if (whiteTurn)
			this.chessPiece = "wR";
		else
			this.chessPiece = "bR";
	}
	 /**
	 * Check to see if the piece is allowed to move to the destination or not.
	 *
	 * @return true if it is allowed to 
     * @return false if it is not allowed to 
     * 
	 */
    public boolean allowedMove(int prevX, int prevY, int currX, int currY, boolean isEmpty){
		
		int x = Math.abs(currX-prevX);
		int y = Math.abs(currY-prevY);
		
		if (x == 0 && y != 0){
			this.first = true;
			return true;
		} else if (x != 0 && y == 0) {
			return true;
		}
		return false;
	}

	public String toString() {
		return this.chessPiece;
	}
}
