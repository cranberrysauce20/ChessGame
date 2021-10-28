public class Board {
    Piece[][] chess;
    
    public Board()
    {
        chess = new Piece[8][8];
        defaultBoard();
    }

    public void defaultBoard(){
        for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				chess[i][j] = null;
			}
		}

        // white chess pieces 
        for(int i =0; i< 8; i++)
        {
            chess[i][1] = new Pawn(false);
        }
        chess[0][0] = new Rook(false);
		chess[1][0] = new Knight(false);
		chess[2][0] = new Bishop(false);
		chess[3][0] = new Queen(false);
		chess[4][0] = new King(false);
		chess[5][0] = new Bishop(false);
		chess[6][0] = new Knight(false);
		chess[7][0] = new Rook(false);
        
        // black chess pieces
        for(int i =0; i< 8; i++)
        {
            chess[i][6] = new Pawn(true);
        }
        chess[0][7] = new Rook(true);
		chess[1][7] = new Knight(true);
		chess[2][7] = new Bishop(true);
		chess[3][7] = new Queen(true);
		chess[4][7] = new King(true);
		chess[5][7] = new Bishop(true);
		chess[6][7] = new Knight(true);
		chess[7][7] = new Rook(true);

    }

    //checks to see if a chess piece is blocking the path for the chess piece to move 
    public boolean isClear(int prevX, int prevY, int currX, int currY){
		boolean isClear = true;
        int x, y, deltaX, deltaY, oldX, oldY;
        deltaX = 0;
        deltaY = 0;
		String prevLoc = chess[prevX][prevY].toString();
		// doesn't matter if the path is not clear if it's a knight
		 if (prevLoc.equalsIgnoreCase("wn") || prevLoc.equalsIgnoreCase("bn")) {
			return true;
		}
        // change in x and y
		x = currX-prevX;
		y = currY-prevY;
        //stores previous x and y 
		oldX = prevX;
		oldY = prevY;
		
		if (x > 0) {
			deltaX = 1;
		}else if (x < 0) {
			deltaX = -1;
		}
		
		if (y > 0) {
			deltaY = 1;
		}else if (y < 0) {
			deltaY = -1;
		}
		if (x == 0) {
			oldY +=deltaY;
            //iterates through the path to check if it is clear
			for (int i = 0; i < Math.abs(y)-1; i++) {
				if (chess[oldX][oldY] != null) {
                    // returns false if a chess piece is found in the path
					isClear = false;
					break;
				}
				oldY = oldY + deltaY;
			}
			return isClear;
		}

		if (y == 0) {
			oldX += deltaX;
            //iterates through the path to check if it is clear
			for (int i = 0; i < Math.abs(x)-1; i++) {
				if (chess[oldX][oldY] != null) {
                    // returns false if a chess piece is found in the path
					isClear = false;
					break;
				}
				oldX += deltaX;
			} 
			return isClear;
		}
		
		if (x != 0 && y != 0) {
			oldX += deltaX;
			prevY += deltaY;
			for (int i = 0; i < Math.abs(y)-1; i++) {
				if (chess[oldX][oldY] != null) {
					isClear = false;
					break;
				}
				
				oldX += deltaX;
				oldY += deltaY;
			} 
		}
		
		return isClear;	
	}

	public boolean move(int[] startCoord, int[] endCoord, boolean whiteMove){
		
		Piece thisPiece = chess[startCoord[0]][startCoord[1]];

		if(thisPiece == null){
			return false;
		}else if((thisPiece.ifWhiteTurn && whiteMove) || (!thisPiece.ifWhiteTurn && !whiteMove)){
			if(thisPiece.allowedMove(startCoord, endCoord, this)){
				//Check if this move puts the player in check
				Piece thatPiece = chess[endCoord[0]][endCoord[1]];
		
				chess[startCoord[0]][startCoord[1]] = null;
				chess[endCoord[0]][endCoord[1]] = thisPiece;
				
				for(int i = 0; i < 8; i++){
					for(int j = 0; j < 8; j++){
						int[] tempStart = {i, j};
						if(detectCheck(tempStart, whiteMove)){
							//If king goes into check, replace move
							chess[startCoord[0]][startCoord[1]] = thisPiece;
							chess[endCoord[0]][endCoord[1]] = thatPiece;		
							return false;
						}
					}
				}
				return true;
			}else
				return false;
		}
		return false;
	}
	public boolean testCastling( String from, String to)
	{
		int prevX = (int) from.toLowerCase().charAt(0) - (int)('a');
		int prevY = 7 - ((int) from.toLowerCase().charAt(1) - (int)('1'));
		int currX = (int) to.toLowerCase().charAt(0) - (int)('a');
		int currY = 7 - ((int) from.toLowerCase().charAt(1) - (int)('1'));
		int dx = currX - prevX;
		String isKing = chess[prevX][prevY].toString();
		if( isKing.equals("wK") && chess[prevX][prevY].first == true)
		{
			if(dx == 2)
			{
				if (chess[7][7] != null) {
					if (chess[7][7].toString().equals("wR") ) 
					{ 
						if (isClear(prevX, prevY, currX, currY)) {
							chess[prevX][prevY] = null;
							chess[7][7] = null;
							chess[currX][currY] = new King(true);
							chess[currX][currY].first = false;
							chess[5][7] = new Rook(true);
							return true;
						}
					}
				}
			}
			if(dx == 2)
			{
				if (chess[0][7] != null) { 

					if (chess[0][7].toString().equalsIgnoreCase("wr") ) { 

						if (isClear(prevX, prevY, currX, currY)) {

							chess[prevX][prevY] = null; 
							chess[0][7] = null; 
							chess[currX][currY] = new King(true);
							chess[currX][currY].first = false;
							chess[3][7] = new Rook(true);
							return true;
						}
					}
				}
			}
		}
		if(isKing.equals("bK") && chess[prevX][prevY].first == true)
		{
			if(dx == 2)
			{
				if (chess[7][0] != null) { 
					if (chess[7][0].toString().equalsIgnoreCase("br") ) { 
						if (isClear(prevX, prevY, currX, currY)) {
							chess[prevX][prevY] = null;
							chess[7][0] = null;
							chess[currX][currY] = new King(false);
							chess[currX][currY].first = false;
							chess[5][0] = new Rook(false);
							return true;
						}
					}
				}
			}
			if(dx == -2)
			{
				if (chess[0][0] != null) { 

					if (chess[0][0].toString().equals("br") ) { 

						if (isClear(prevX, prevY, currX, currY)) {

							chess[prevX][prevY] = null; 
							chess[0][0] = null; 
							chess[currX][currY] = new King(false);
							chess[currX][currY].first = false;
							chess[3][0] = new Rook(false);
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	// method that detects if the King is check
	public boolean detectCheck(boolean whiteturn) {
		int kingLocX = 0;
		int kingLocY = 0;
		
		if (whiteturn) { //if whiteturn is false, check if the white is in check
			for (int y = 0; y < 8; y++){
				for (int x = 0; x < 8; x++){
					if (chess[x][y] != null) {
						if (chess[x][y].toString().equalsIgnoreCase("wk")) {
							kingLocX = x;
							kingLocY = y;
							break;
						}
					}
				}
			}
			
			for (int y = 0; y < 8; y++){
				for (int x = 0; x < 8; x++){
					if (chess[x][y] != null) {
						if (chess[x][y].isWhite() == false) {
							if (chess[x][y].allowedMove(x, y, kingLocX, kingLocY, true)) {
								if (isClear(x, y, kingLocX, kingLocY)) {
									//System.out.println("white king is in check by "+board[x][y].toString());
									//System.out.println("CHECK");
									return true;
								}
							}
						}
					}
				}
			}
		}
		
		if (!whiteturn) {//if whiteturn is true, check if the black is in check
			for (int y = 0; y < 8; y++){
				for (int x = 0; x < 8; x++){
					if (chess[x][y] != null) {
						if (chess[x][y].toString().equalsIgnoreCase("bk")) {
							kingLocX = x;
							kingLocY = y;
							break;
						}
					}
				}
			}

			for (int y = 0; y < 8; y++){
				for (int x = 0; x < 8; x++){
					if (chess[x][y] != null) {
						if (chess[x][y].isWhite() == true) {
							if (chess[x][y].allowedMove(x, y, kingLocX, kingLocY, true)) {
								if (isClear(x, y, kingLocX, kingLocY)) {
									//System.out.println("black king is in check by "+board[x][y].toString());
									//System.out.println("CHECK");
									return true;
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

	public boolean detectEnPassant(int oldX, int oldY, int newX, int newY) { //call this after the move had been performed for this turn
		Chess.enPassantToEliminateX = newX;
		Chess.enPassantToEliminateY = newY;
		
		if (chess[newX][newY] == null) {
			return false;
		}

		if (!(chess[newX][newY].toString().equalsIgnoreCase("wp") || chess[newX][newY].toString().equalsIgnoreCase("bp"))) { // white pawn or black is in the new location
			return false;
		}
		if (Math.abs(newY - oldY) != 2) { //white pawn had just moved forward by 2
			return false;
		}
		
		int tempx = newX - 1;
		
		if (tempx >= 0) {
			Chess.enPassantCapturer1X = tempx;
			Chess.enPassantCapturer1Y = newY;
			
			//System.out.println("5");
			if (chess[tempx][newY] != null) {
				if (chess[tempx][newY].toString().equalsIgnoreCase("wp") && Chess.ifWhiteTurn == false) {
					Chess.enPassant = true;
					Chess.enPassantX = newX;
					Chess.enPassantY = 2;
					
					return true;
				}
				
				if (chess[tempx][newY].toString().equalsIgnoreCase("bp") && Chess.ifWhiteTurn == true) {
					Chess.enPassant = true;
					Chess.enPassantX = newX;
					Chess.enPassantY = 5;
					
					return true;
				}
			}
		}
		
	
		tempx = newX + 1;
		if (tempx <= 7) {
			Chess.enPassantCapturer2X = tempx;
			Chess.enPassantCapturer2Y = newY;
			
			//System.out.println("6");
			if (chess[tempx][newY] != null) {
				if (chess[tempx][newY].toString().equalsIgnoreCase("wp") && Chess.ifWhiteTurn == false) {
					//System.out.println("7");
					Chess.enPassant = true;
					Chess.enPassantX = newX;
					Chess.enPassantY = 2;
					return true;
				}
				
				if (chess[tempx][newY].toString().equalsIgnoreCase("bp") && Chess.ifWhiteTurn == true) {
					//System.out.println("10");
					Chess.enPassant = true;
					Chess.enPassantX = newX;
					Chess.enPassantY = 5;
					return true;
				}
			}
		}
	
		
		return false;
	}
	
	public  boolean testEnPassant(int oldX, int oldY, int newX, int newY) {
		if (((Chess.enPassantCapturer1X == oldX) && (Chess.enPassantCapturer1Y == oldY)) || ((Chess.enPassantCapturer2X == oldX) && (Chess.enPassantCapturer2Y == oldY))) {
			if (newX == Chess.enPassantX && newY == Chess.enPassantY) {
				chess[Chess.enPassantToEliminateX][Chess.enPassantToEliminateY] = null;
				chess[newX][newY] = chess[oldX][oldY];
				chess[oldX][oldY] = null;
				
				return true;
			}
		}
		
		return false;
	}
	public boolean testCastling(int oldX, int oldY, int newX, int newY) {
		return true;
	}
	
}