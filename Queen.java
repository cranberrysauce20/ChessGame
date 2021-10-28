

public class Queen extends Piece {
    
    private String chessPiece;
	
	public Queen(boolean whiteTurn) {
        super(whiteTurn);
    
        this.setWhiteTurn(whiteTurn);
            //check if white piece is playing or black
        if(whiteTurn == true)
        {
			this.chessPiece = "wQ"; 
		}else{
			this.chessPiece = "bQ"; 
		}
	}
    public boolean allowedMove(int prevX, int prevY, int currX, int currY, boolean isEmpty){

		// change in x
		int x;
        //change in y
		int y;
		
		x = Math.abs(currX-prevX);
		y = Math.abs(currY-prevY);
		
		if (x == 0 && y != 0){
			return true;
		}else if ( x !=0 &&y == 0)
        {
            return true;
        } 
        else if (x == y) {
			return true;
		}
				
		return false;
	}

	@Override
    public boolean isFirstMove() {
        // TODO Auto-generated method stub
        return false;
    }

	public String toString() {
		return this.chessPiece;
	}
}
