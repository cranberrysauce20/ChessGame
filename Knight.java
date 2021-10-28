

public class Knight extends Piece{
    
	private String chessPiece;
	
	public Knight(boolean whiteTurn) {
        super(whiteTurn);
    
        this.setWhiteTurn(whiteTurn);
            //check if white piece is playing or black
        if(whiteTurn == true)
        {
			this.chessPiece = "wN"; 
		}else{
			this.chessPiece = "bN"; 
		}
	}
    public boolean allowedMove(int prevX, int prevY, int currX, int currY, boolean isEmpty){

		// change in x
		int x;
        //change in y
		int y;
		
		x = Math.abs(currX-prevX);
		y = Math.abs(currY-prevY);
		
		if (x == 2 && y == 1){
			return true;
		} else if (x == 1 && y == 2) {
			return true;
		}
				
		return false;
	}

	public String toString() {
		return this.chessPiece;
	}
	
}
