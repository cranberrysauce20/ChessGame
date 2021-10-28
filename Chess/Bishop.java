/**
 * This is Bishop class and it implements the Bishop chesspieces
 * 
 * @author Sujay Sayini
 * @author Pauleene Jordan
 */

public class Bishop extends Piece{
    private String chessPiece;

    public Bishop(boolean whiteTurn) {
        super(whiteTurn);

        if (whiteTurn)
            this.chessPiece = "wB";
        else
            this.chessPiece = "bB";

    }
/**
 * This is a method called allowedMove that checks if the move that 
 * Bishop is about to do is legal. If the move is legal, it returns true and false, if not.
 * @param prevX
 * @param prevY
 * @param currX
 * @param currY
 * @param isEmpty
 */
    @Override
    public boolean allowedMove(int prevX, int prevY, int currX, int currY, boolean isEmpty) {
        // change in x and y

        int x = Math.abs(currX - prevX);
        int y = Math.abs(currY - prevY);
        return x == y;
    }
    

    public String toString() {
        return this.chessPiece;
    }
}
