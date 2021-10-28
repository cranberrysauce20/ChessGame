

public class King extends Piece {

    private String chessPiece;

    public King(boolean whiteTurn) {
        super(whiteTurn);
    
            this.setWhiteTurn(whiteTurn);
            //check if white piece is playing or black
            if(whiteTurn == true)
            {
                this.chessPiece = "wK";
            }
            else{
                this.chessPiece = "bK";
            }
        
    }
// checks if King is allowed to move 
    @Override
    public boolean allowedMove(int prevX, int prevY, int currX, int currY, boolean isEmpty){
        // change in x
		int x;
        //change in y
		int y;
		
		x = Math.abs(currX-prevX);
		y = Math.abs(currY-prevY);
		
		if (x == y && x == 1 && y == 1){
			return true;
		}else if (x == 0 && y == 1){
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