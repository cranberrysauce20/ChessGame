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
        // this.setWhiteTurn(whiteTurn);
        // check if piece is white or black
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
 * @author Sujay Sayini
 * @author Pauleene Jordan
 */
    @Override
    public boolean allowedMove(int prevX, int prevY, int currX, int currY, boolean isEmpty) {
        // change in x and y

        int x = Math.abs(currX - prevX);
        int y = Math.abs(currY - prevY);

        return x == y;

        // if (x==y){
        // return true;
        // }
        // return false;
    }
    
/**
 * This is a method called toString from the abstract class Piece and it returns the name of the chesspiece
 * e.g. wB or bB
 * 
 * @author Sujay Sayini
 * @author Pauleene Jordan
 */
    public String toString() {
        return this.chessPiece;
    }
}
