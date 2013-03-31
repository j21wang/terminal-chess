import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
/**
 * 
 * @author Joyce
 *
 */
public class Chess {

	static int counter = 0;

	public static void makeChessPieces() {
		
		ChessBoard.board[0][0] = new ChessPiece("rook", "white", "wR",
				new Position(0, 0));
		ChessBoard.board[1][0] = new ChessPiece("knight", "white", "wN",
				new Position(1, 0));
		ChessBoard.board[2][0] = new ChessPiece("bishop", "white", "wB",
				new Position(2, 0));
		ChessBoard.board[3][0] = new ChessPiece("queen", "white", "wQ",
				new Position(3, 0));
		ChessBoard.board[4][0] = new ChessPiece("king", "white", "wK",
				new Position(4, 0));
		ChessBoard.board[5][0] = new ChessPiece("bishop", "white", "wB",
				new Position(5, 0));
		ChessBoard.board[6][0] = new ChessPiece("knight", "white", "wN",
				new Position(6, 0));
		ChessBoard.board[7][0] = new ChessPiece("rook", "white", "wR",
				new Position(7, 0));
		ChessBoard.board[0][1] = new ChessPiece("pawn", "white", "wp",
				new Position(0, 1));
		ChessBoard.board[1][1] = new ChessPiece("pawn", "white", "wp",
				new Position(1, 1));
		ChessBoard.board[2][1] = new ChessPiece("pawn", "white", "wp",
				new Position(2, 1));
		ChessBoard.board[3][1] = new ChessPiece("pawn", "white", "wp",
				new Position(3, 1));
		ChessBoard.board[4][1] = new ChessPiece("pawn", "white", "wp",
				new Position(4, 1));
		ChessBoard.board[5][1] = new ChessPiece("pawn", "white", "wp",
				new Position(5, 1));
		ChessBoard.board[6][1] = new ChessPiece("pawn", "white", "wp",
				new Position(6, 1));
		ChessBoard.board[7][1] = new ChessPiece("pawn", "white", "wp",
				new Position(7, 1));

		ChessBoard.board[0][7] = new ChessPiece("rook", "black", "bR",
				new Position(0, 7));
		ChessBoard.board[1][7] = new ChessPiece("knight", "black", "bN",
				new Position(1, 7));
		ChessBoard.board[2][7] = new ChessPiece("bishop", "black", "bB",
				new Position(2, 7));
		ChessBoard.board[3][7] = new ChessPiece("queen", "black", "bQ",
				new Position(3, 7));
		ChessBoard.board[4][7] = new ChessPiece("king", "black", "bK",
				new Position(4, 7));
		ChessBoard.board[5][7] = new ChessPiece("bishop", "black", "bB",
				new Position(5, 7));
		ChessBoard.board[6][7] = new ChessPiece("knight", "black", "bN",
				new Position(6, 7));
		ChessBoard.board[7][7] = new ChessPiece("rook", "black", "bR",
				new Position(7, 7));
		ChessBoard.board[0][6] = new ChessPiece("pawn", "black", "bp",
				new Position(0, 6));
		ChessBoard.board[1][6] = new ChessPiece("pawn", "black", "bp",
				new Position(1, 6));
		ChessBoard.board[2][6] = new ChessPiece("pawn", "black", "bp",
				new Position(2, 6));
		ChessBoard.board[3][6] = new ChessPiece("pawn", "black", "bp",
				new Position(3, 6));
		ChessBoard.board[4][6] = new ChessPiece("pawn", "black", "bp",
				new Position(4, 6));
		ChessBoard.board[5][6] = new ChessPiece("pawn", "black", "bp",
				new Position(5, 6));
		ChessBoard.board[6][6] = new ChessPiece("pawn", "black", "bp",
				new Position(6, 6));
		ChessBoard.board[7][6] = new ChessPiece("pawn", "black", "bp",
				new Position(7, 6));
	}
	
	public static int String2Int(String letter) {

		int coor;

		if (letter.equalsIgnoreCase("a") || letter.equalsIgnoreCase("1"))
			coor = 0;
		else if (letter.equalsIgnoreCase("b") || letter.equalsIgnoreCase("2"))
			coor = 1;
		else if (letter.equalsIgnoreCase("c") || letter.equalsIgnoreCase("3"))
			coor = 2;
		else if (letter.equalsIgnoreCase("d") || letter.equalsIgnoreCase("4"))
			coor = 3;
		else if (letter.equalsIgnoreCase("e") || letter.equalsIgnoreCase("5"))
			coor = 4;
		else if (letter.equalsIgnoreCase("f") || letter.equalsIgnoreCase("6"))
			coor = 5;
		else if (letter.equalsIgnoreCase("g") || letter.equalsIgnoreCase("7"))
			coor = 6;
		else if (letter.equalsIgnoreCase("h") || letter.equalsIgnoreCase("8"))
			coor = 7;
		else
			coor = -1;

		return coor;
	}

	public static void main(String[] args) throws Exception{

		makeChessPieces();
		ChessBoard chessBoard = new ChessBoard(new ChessPiece[8][8]);

		String line = null;
		int first_x = -1;
		int first_y = -1;
		int end_x = -1;
		int end_y = -1;
		String turn = "white";
		int valid = 0;
		boolean draw = false;

		while (chessBoard.kingExists().equalsIgnoreCase("both")) {
		
			ChessBoard.Draw();

			if (turn.equalsIgnoreCase("white"))
				System.out.println("White, please enter a move: ");
			else
				System.out.println("Black, please enter a move: ");

			Scanner s = new Scanner(System.in);

			try{
				line = s.nextLine();
			} catch(NoSuchElementException e){
				System.exit(1);
			}
			
			String arr[] = line.split(" ");

			if (arr.length >= 2) {
				first_x = String2Int(arr[0].substring(0, 1));
				first_y = String2Int(arr[0].substring(1));
				end_x = String2Int(arr[1].substring(0, 1));
				end_y = String2Int(arr[1].substring(1));
			}

			if (arr.length >= 2
					&& ((first_x < 0 || first_x > 7)
							|| (first_y < 0 || first_y > 7)
							|| (end_x < 0 || end_x > 7) || (end_x < 0 || end_x > 7))) {
				System.out
						.println("Input must be in the format: <a-h><1-8> <a-h><1-8>");
			} else if(arr.length == 2 && first_x == end_x && first_y == end_y){
				
				System.out.println("Invalid move: Must move to a different location.");
			
			}else {

				if (draw) {
					if (arr.length == 1) {
						if (arr[0].equalsIgnoreCase("draw")) {
							System.out.println("Draw.");
							break;
						}
					}
				}

				if (arr.length == 1 && arr[0].equalsIgnoreCase("resign")) {
					if (turn.equalsIgnoreCase("white")) {
						System.out.println("Black wins!");
						break;
					} else {
						System.out.println("White wins!");
						break;
					}
				} else if (arr.length >= 2 && ChessBoard.board[first_x][first_y] != null
						&& ChessBoard.board[first_x][first_y].color
								.equalsIgnoreCase("white")
						&& turn.equalsIgnoreCase("white")) {
					draw = false;
					valid = ChessBoard.checkPieceType(
							ChessBoard.board[first_x][first_y], new Position(
									first_x, first_y), new Position(end_x,
									end_y));
					
					if (valid==1)
						turn = "black";
					else if(valid==0)
						turn = "white";
					else if(valid==2){
						System.out.println("White wins!");
						break;
					} else if(valid==3){
						System.out.println("Black wins!");
						break;
					} else if(valid==4){
						break;
					}
					
					if (arr.length == 3 && arr[2].equalsIgnoreCase("draw?")) {
						System.out
								.println("The opponent offered a draw. Type 'draw' to accept. Otherwise, enter a valid move to keep playing.");
						draw = true;
						
					} else if (arr.length == 2 && first_y == 6 && ChessBoard.board[end_x][end_y].pieceType.equalsIgnoreCase("pawn")) {
						ChessBoard.board[end_x][end_y].pieceName = ChessBoard.board[end_x][end_y].color
								.substring(0, 1) + "Q";
						
						ArrayList<Position> checkMoves = new ArrayList<Position>();
						ChessBoard.board[end_x][end_y].pieceType = "queen";
						checkMoves = ChessBoard.queenCheckMoves(ChessBoard.board[end_x][end_y], ChessBoard.board[end_x][end_y].position);
						if (ChessBoard.isCheckMate("white")) {
							ChessBoard.Draw();
							System.out.println("Checkmate.");
							System.out.println("White wins!");
							break;
						} else if (ChessBoard.containKing(checkMoves,ChessBoard.board[end_x][end_y].color)) {
							System.out.println("Check.");
						} else if (ChessBoard.isStaleMate("white")){
							ChessBoard.Draw();
							System.out.println("Stalemate.");
							break;
						}
						
					} else if (arr.length == 3
							&& first_y == 6
							&& (arr[2].equalsIgnoreCase("b")
									|| arr[2].equalsIgnoreCase("n")
									|| arr[2].equalsIgnoreCase("r") || arr[2]
										.equalsIgnoreCase("q"))) {
						ChessBoard.board[end_x][end_y].pieceName = ChessBoard.board[end_x][end_y].color
								.substring(0, 1) + arr[2].toUpperCase();
						ArrayList<Position> checkMoves = new ArrayList<Position>();
						
						if (arr[2].equalsIgnoreCase("b")) {
							ChessBoard.board[end_x][end_y].pieceType = "bishop";
							checkMoves = ChessBoard.bishopCheckMoves(ChessBoard.board[end_x][end_y], ChessBoard.board[end_x][end_y].position);
							if (ChessBoard.isCheckMate("white")) {
								ChessBoard.Draw();
								System.out.println("Checkmate.");
								System.out.println("White wins!");
								break;
							} else if (ChessBoard.containKing(checkMoves,ChessBoard.board[end_x][end_y].color)) {
								System.out.println("Check.");
							} else if (ChessBoard.isStaleMate("black")){
								ChessBoard.Draw();
								System.out.println("Stalemate.");
								break;
							}
						} else if (arr[2].equalsIgnoreCase("n")) {
							ChessBoard.board[end_x][end_y].pieceType = "knight";
							checkMoves = ChessBoard.knightCheckMoves(ChessBoard.board[end_x][end_y], ChessBoard.board[end_x][end_y].position);
							if (ChessBoard.isCheckMate("white")) {
								ChessBoard.Draw();
								System.out.println("Checkmate.");
								System.out.println("White wins!");
								break;
							} else if (ChessBoard.containKing(checkMoves,ChessBoard.board[end_x][end_y].color)) {
								System.out.println("Check.");
							} else if (ChessBoard.isStaleMate("black")){
								ChessBoard.Draw();
								System.out.println("Stalemate.");
								break;
							}
						} else if (arr[2].equalsIgnoreCase("r")) {
							ChessBoard.board[end_x][end_y].pieceType = "rook";
							checkMoves = ChessBoard.rookCheckMoves(ChessBoard.board[end_x][end_y], ChessBoard.board[end_x][end_y].position);
							if (ChessBoard.isCheckMate("white")) {
								ChessBoard.Draw();
								System.out.println("Checkmate.");
								System.out.println("White wins!");
								break;
							} else if (ChessBoard.containKing(checkMoves,ChessBoard.board[end_x][end_y].color)) {
								System.out.println("Check.");
							} else if (ChessBoard.isStaleMate("black")){
								ChessBoard.Draw();
								System.out.println("Stalemate.");
								break;
							}
						} else if (arr[2].equalsIgnoreCase("q")) {
							ChessBoard.board[end_x][end_y].pieceType = "queen";
							checkMoves = ChessBoard.queenCheckMoves(ChessBoard.board[end_x][end_y], ChessBoard.board[end_x][end_y].position);
							if (ChessBoard.isCheckMate("white")) {
								ChessBoard.Draw();
								System.out.println("Checkmate.");
								System.out.println("White wins!");
								break;
							} else if (ChessBoard.containKing(checkMoves,ChessBoard.board[end_x][end_y].color)) {
								System.out.println("Check.");
							} else if (ChessBoard.isStaleMate("black")){
								ChessBoard.Draw();
								System.out.println("Stalemate.");
								break;
							}
						}
						
					} else if (arr.length == 3) {
						System.out.println("Invalid input. Try again.");
						turn = "white";
					}
					
				} else if (arr.length >= 2 && ChessBoard.board[first_x][first_y] != null
						&& ChessBoard.board[first_x][first_y].color
								.equalsIgnoreCase("black")
						&& turn.equalsIgnoreCase("black")) {
					draw = false;
					valid = ChessBoard.checkPieceType(
							ChessBoard.board[first_x][first_y], new Position(
									first_x, first_y), new Position(end_x,
									end_y));
					
					if (valid==1)
						turn = "white";
					else if(valid==0)
						turn = "black";
					else if(valid==2){
						System.out.println("White wins!");
						break;
					} else if(valid==3){
						System.out.println("Black wins!");
						break;
					} else if(valid==4){
						break;
					}

					if (arr.length == 3 && arr[2].equalsIgnoreCase("draw?")) {
						System.out
								.println("The opponent offered a draw. Type 'draw' to accept. Otherwise, enter a valid move to keep playing.");
						draw = true;
						
					} else if (arr.length == 2 && first_y == 1 && ChessBoard.board[end_x][end_y].pieceType.equalsIgnoreCase("pawn")) {
							ChessBoard.board[end_x][end_y].pieceName = ChessBoard.board[end_x][end_y].color
									.substring(0, 1) + "Q";
							
							ArrayList<Position> checkMoves = new ArrayList<Position>();
							ChessBoard.board[end_x][end_y].pieceType = "queen";
							checkMoves = ChessBoard.queenCheckMoves(ChessBoard.board[end_x][end_y], ChessBoard.board[end_x][end_y].position);
							if (ChessBoard.isCheckMate("black")) {
								ChessBoard.Draw();
								System.out.println("Checkmate.");
								System.out.println("Black wins!");
								break;
							} else if (ChessBoard.containKing(checkMoves,ChessBoard.board[end_x][end_y].color)) {
								System.out.println("Check.");
							} else if (ChessBoard.isStaleMate("black")){
								ChessBoard.Draw();
								System.out.println("Stalemate.");
								break;
							}
							
					} else if (arr.length == 3
							&& first_y == 1
							&& (arr[2].equalsIgnoreCase("b")
									|| arr[2].equalsIgnoreCase("n")
									|| arr[2].equalsIgnoreCase("r") || arr[2]
										.equalsIgnoreCase("q"))) {
						ChessBoard.board[end_x][end_y].pieceName = ChessBoard.board[end_x][end_y].color
								.substring(0, 1) + arr[2].toUpperCase();

						ArrayList<Position> checkMoves = new ArrayList<Position>();
								
						if (arr[2].equalsIgnoreCase("b")) {
							ChessBoard.board[end_x][end_y].pieceType = "bishop";
							checkMoves = ChessBoard.bishopCheckMoves(ChessBoard.board[end_x][end_y], ChessBoard.board[end_x][end_y].position);
							if (ChessBoard.isCheckMate("black")) {
								ChessBoard.Draw();
								System.out.println("Checkmate.");
								System.out.println("Black wins!");
								break;
							} else if (ChessBoard.containKing(checkMoves,ChessBoard.board[end_x][end_y].color)) {
								System.out.println("Check.");
							} else if (ChessBoard.isStaleMate("black")){
								ChessBoard.Draw();
								System.out.println("Stalemate.");
								break;
							}
						} else if (arr[2].equalsIgnoreCase("n")) {
							ChessBoard.board[end_x][end_y].pieceType = "knight";
							checkMoves = ChessBoard.knightCheckMoves(ChessBoard.board[end_x][end_y], ChessBoard.board[end_x][end_y].position);
							if (ChessBoard.isCheckMate("black")) {
								ChessBoard.Draw();
								System.out.println("Checkmate.");
								System.out.println("Black wins!");
								break;
							} else if (ChessBoard.containKing(checkMoves,ChessBoard.board[end_x][end_y].color)) {
								System.out.println("Check.");
							} else if (ChessBoard.isStaleMate("black")){
								ChessBoard.Draw();
								System.out.println("Stalemate.");
								break;
							}
						} else if (arr[2].equalsIgnoreCase("r")) {
							ChessBoard.board[end_x][end_y].pieceType = "rook";
							checkMoves = ChessBoard.rookCheckMoves(ChessBoard.board[end_x][end_y], ChessBoard.board[end_x][end_y].position);
							if (ChessBoard.isCheckMate("black")) {
								ChessBoard.Draw();
								System.out.println("Checkmate.");
								System.out.println("Black wins!");
								break;
							} else if (ChessBoard.containKing(checkMoves,ChessBoard.board[end_x][end_y].color)) {
								System.out.println("Check.");
							} else if (ChessBoard.isStaleMate("black")){
								ChessBoard.Draw();
								System.out.println("Stalemate.");
								break;
							}
						} else if (arr[2].equalsIgnoreCase("q")) {
							ChessBoard.board[end_x][end_y].pieceType = "queen";

							checkMoves = ChessBoard.queenCheckMoves(ChessBoard.board[end_x][end_y], ChessBoard.board[end_x][end_y].position);
							
							if (ChessBoard.isCheckMate("black")) {
								ChessBoard.Draw();
								System.out.println("Checkmate.");
								System.out.println("Black wins!");
								break;
							} else if (ChessBoard.containKing(checkMoves,ChessBoard.board[end_x][end_y].color)) {
								System.out.println("Check.");
							} else if (ChessBoard.isStaleMate("black")){
								ChessBoard.Draw();
								System.out.println("Stalemate.");
								break;
							}
						}

					} else if (arr.length == 3) {
						System.out.println("Invalid input. Try again.");
						turn = "black";
					}

				} else if (arr.length == 2 && ChessBoard.board[first_x][first_y] != null
						&& ChessBoard.board[first_x][first_y].color
								.equalsIgnoreCase("white")
						&& turn.equalsIgnoreCase("black")) {
					System.out.println("It is black's turn.");
					turn = "black";
				} else if (arr.length == 2 && ChessBoard.board[first_x][first_y] != null
						&& ChessBoard.board[first_x][first_y].color
								.equalsIgnoreCase("black")
						&& turn.equalsIgnoreCase("white")) {
					System.out.println("It is white's turn.");
					turn = "white";
				} else {
					System.out.println("Not a valid move. Try again.");
				}
			}
		}
		if(chessBoard.kingExists().equalsIgnoreCase("white")) System.out.println("White wins!");
		else if(chessBoard.kingExists().equalsIgnoreCase("black")) System.out.println("Black wins!");
	}
}
