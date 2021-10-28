

public class Rook extends Piece {
    private String chessPiece;
	public boolean rookMoved = false;
	
	public Rook(boolean whiteTurn) {
		super(whiteTurn);

		// this.setWhiteTurn(whiteTurn);
		// check if white piece is playing or black
		if (whiteTurn)
			this.chessPiece = "wR";
		else
			this.chessPiece = "bR";
	}
    public boolean allowedMove(int prevX, int prevY, int currX, int currY, boolean isEmpty){

		// change in x
		int x;
        //change in y
		int y;
		
		x = Math.abs(currX-prevX);
		y = Math.abs(currY-prevY);
		
		if (x == 0 && y != 0){
			first = true;
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
