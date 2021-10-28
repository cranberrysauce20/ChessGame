/**
 * This is abtract class that defines the basic methods of all chess pieces
 * 
 * @author Sujay Sayini
 * @author Pauleene Jordan
 */

public abstract class Piece {
    private boolean whiteTurn = false;
    public boolean first = true;

    /**
	 * Constructor for Piece
	 * 
	 * @param whiteTurn if piece is white or black
	 */

    public Piece(boolean whiteTurn) {
        this.whiteTurn = whiteTurn;
    }

    /**
	 * Check to see if the piece is white or black.
	 *
	 * @return true if the piece is white 
     * @return false if the piece is black
     * 
	 */

    public boolean isWhite() {
        return this.whiteTurn;
    }

    public abstract String toString();
    
    /**
	 * Check to see if the piece is allowed to move to the destination or not.
	 *
	 * @return true if it is allowed to 
     * @return false if it is not allowed to 
     * 
	 */

    public abstract boolean allowedMove(int prevX, int prevY, int newX, int newY, boolean isEmpty);
}
