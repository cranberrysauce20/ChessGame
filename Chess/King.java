/**
 * This is King class for chess piece that extends Piece abstract class
 * 
 * @author Sujay Sayini
 * @author Pauleene Jordan
 */

public class King extends Piece {

    private String chessPiece;
    public King(boolean whiteTurn) {
        super(whiteTurn);

        if (whiteTurn)
            this.chessPiece = "wK";
        else
            this.chessPiece = "bK";

    }

    /**
	 * Check to see if the piece is allowed to move to the destination or not.
	 *
	 * @return true if it is allowed to 
     * @return false if it is not allowed to 
     * 
	 */
    @Override
    public boolean allowedMove(int prevX, int prevY, int currX, int currY, boolean isEmpty){
        // change in x
		int x;
        //change in y
		int y;
		
		x = Math.abs(currX-prevX);
		y = Math.abs(currY-prevY);
		
		if (x == y && x == 1 && y == 1){
            this.first = false;
			return true;
		}else if (x == 0 && y == 1){
            first = false;
			return true;
		} else if (x == 1 && y == 0) {
			return true;
		}
		
        return false;
    }
    
    public String toString() {
        return this.chessPiece;
    }

}