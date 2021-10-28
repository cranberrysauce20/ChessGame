
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Chess {
    public static Board gameboard;
	public static boolean isDraw  = false;
	public static boolean whiteTurn = true;
	public static String prevLoc = null;
	public static String currLoc = null;
	
	
    public static void main(String[] args) {
		//initialize game
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		String input = null;
		
		startGame();
		makeChessBoard();
		
		//game loop
		while (true){
			//read the player's move
		     input = null;
		    
		     try {
		    	 // if white turn then make the white make a move
		    	 if (whiteTurn){		
		    			System.out.println("White's move: ");	
		    	 }else{
		    		 System.out.println("Black's move:");
		    	 }
		    	// code that checks if white is check
		    	boolean whiteCheck = gameboard.detectCheck(whiteTurn);
		    	 if (whiteCheck == true) {
		    			System.out.println("Check");
		    	}
                // code that checks if black is check
                boolean blackCheck = gameboard.detectCheck(!whiteTurn);
                if(blackCheck == true)
                {
		    		System.out.println("Check");
		    	}
		    
		    	 input = buffer.readLine();
		    	 playerInput(input);
		  
		    	 makeChessBoard();
		    	 // checks if white is checkmate
		    	 boolean whiteCheckMate = gameboard.detectCheckmate(whiteTurn);
		    	 if (whiteCheckMate == true) {
		    		System.out.println("Checkmate");
		    		System.out.println("White wins");
                 }
                 //checks if black is checkmate
                 boolean blackCheckMate = gameboard.detectCheckmate(!whiteTurn);
		    	{
		    		System.out.println("Checkmate");
		    		System.out.println("Black wins");
		    	}
		    		System.exit(1);
		    	 }
		        catch (IOException e) {
		    	    System.out.println("Invalid input. Try again.");
		        }       
		}
		
	}
    public static void startGame(){
		gameboard = new Board();
	}
	
    // makes the chess board
	public static void makeChessBoard(){
		String[][] chessBoard = new String[8][8];
		
		//make the tiles white or black
		boolean white = true;
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				if (white){
					chessBoard[i][j] = "   ";
					white = false;
				}else{
					chessBoard[i][j] = "## ";
					white = true;
				}
			}
			white = !(white);
		}	

		for (int y = 0; y < 8; y++){
			for (int x = 0; x < 8; x++){
				
				if ( gameboard.chess[x][y] != null){
					chessBoard[x][y] = gameboard.chess[x][y].toString()+ " ";
				}
			}
		}
		
		//print out the whole board  
		for (int y = 0; y < 8; y++){
			System.out.print(" ");
			for (int x = 0; x < 8; x++){
				System.out.print(chessBoard[x][y]);
			}
			System.out.print(" " + (8-y));
			System.out.println();
		}
		System.out.println(" a  b  c  d  e  f  g  h"); 
	}

	// checks if the input of the player is allowed from 1-8 to a-h
    // identification of illegal move
	public static boolean legalInput(int a, int b, int c, int d){
		if ((a >= 0 && a <= 7) && (b >= 0 && b <= 7) && (c >= 0 && c <= 7) && (d >= 0 && d <= 7)){
			return true;
		}
		return false;
		
	}

    // receives the input of the player
    public static void playerInput(String input){
		StringTokenizer string = null;
		int count = 0;
		input = input.toLowerCase();
		input = input.trim();
		
		String[] array = new String[100]; 
		 
		string = new StringTokenizer(input);
		 
		while (string.hasMoreTokens()) {
	        array[count] = string.nextToken();
	        count++;
	    }
    }
	
	
	
	// method for promoting pawn
	public static void pawnPromotion() {
	}
	
    //method for resign
	public static void Resign(){
		
	}
	
	// method for draw
	public static void Draw(){
		
	}
	
    //method for Moving Chess Piece
	public static void Move(){
		
	}

	
}
