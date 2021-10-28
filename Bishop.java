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

    // checks if Bishop is allowed to move
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
    

    public String toString() {
        return this.chessPiece;
    }
}
