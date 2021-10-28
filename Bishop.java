public class Bishop extends Piece{
    private String chessPiece;

    public Bishop(boolean whiteTurn) {
        super(whiteTurn);
    
            this.setWhiteTurn(whiteTurn);
            //check if white piece is playing or black
            if(whiteTurn == true)
            {
                this.chessPiece = "wB";
            }
            else{
                this.chessPiece = "bB";
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

		if (x==y){
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
