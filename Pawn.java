
public class Pawn extends Piece {

    private String chessPiece;

    public Pawn(boolean whiteTurn) {
        super(whiteTurn);
    
            this.setWhiteTurn(whiteTurn);
            //check if white piece is playing or black
            if(whiteTurn == true)
            {
                this.chessPiece = "wp";
            }
            else{
                this.chessPiece = "bp";
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
		if (this.isWhite() == true){
			y = -(currY-prevY);
		}else{
			y = currY - prevY;
		}

		if (y == 1 && x==0 && isEmpty == true){
			return true;
		}else if (x == 1 && y == 1 && isEmpty == false){
			return true;
		} else if (this.first == true && x == 0 && y == 2 && isEmpty == true) {
			return true;
		}
		
        return false;
    }

    public String toString() {
		return this.chessPiece;
	}
    
}
