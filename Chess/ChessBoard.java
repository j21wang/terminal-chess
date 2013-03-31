import java.util.ArrayList;
import java.util.List;

public class ChessBoard {

	static ChessPiece board[][] = new ChessPiece[8][8];
	static boolean ePW = false;
	static boolean ePB = false;
	static Position enPassantWhite = new Position(null, null);
	static Position enPassantBlack = new Position(null, null);
	static int enPassantCounterWhite;
	static int enPassantCounterBlack;
	static boolean wRookLeftMoved = false;
	static boolean wRookRightMoved = false;
	static boolean wKingMoved = false;
	static boolean bRookLeftMoved = false;
	static boolean bRookRightMoved = false;
	static boolean bKingMoved = false;
	static ChessPiece temp;
	static boolean check = false;

	public ChessBoard(ChessPiece board[][]) {

		super();
		board = ChessBoard.board;

	}

	public String kingExists() {

		boolean white = false;
		boolean black = false;

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j] != null
						&& board[i][j].pieceType.equalsIgnoreCase("king")) {
					if (board[i][j].color.equalsIgnoreCase("white")) {
						white = true;
					}
					if (board[i][j].color.equalsIgnoreCase("black")) {
						black = true;
					}
				}
			}
		}

		if (white && black)
			return "both";
		else if (white && !black)
			return "white";
		else if (!white && black)
			return "black";
		else
			return null;

	}

	public static boolean containKing(ArrayList<Position> checkMoves,
			String color) {

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j] != null
						&& board[i][j].pieceType.equalsIgnoreCase("king")) {
					if (color.equalsIgnoreCase("white")
							&& board[i][j].color.equalsIgnoreCase("black")) {

						if (checkMoves.contains(board[i][j].position)) {
							return true;
						}
					} else if (color.equalsIgnoreCase("black")
							&& board[i][j].color.equalsIgnoreCase("white")) {

						if (checkMoves.contains(board[i][j].position)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public static ChessPiece getKingPos(String clr) {

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j] != null
						&& board[i][j].pieceType.equalsIgnoreCase("king")) {

					if (clr.equalsIgnoreCase("white")
							&& board[i][j].color.equalsIgnoreCase("black")) {

						return board[i][j];

					} else if (clr.equalsIgnoreCase("black")
							&& board[i][j].color.equalsIgnoreCase("white")) {

						return board[i][j];
					}
				}
			}
		}
		return null;
	}

	public static ArrayList<Position> getAllValidMoves(String color) {

		ArrayList<Position> individualMoves = new ArrayList<Position>();
		ArrayList<Position> allMoves = new ArrayList<Position>();

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j].pieceType.equalsIgnoreCase("pawn")) {

				}
			}
		}
		return allMoves;
	}

	public static ArrayList<Position> getAttacked(String color) {

		ArrayList<Position> onePieceMoves = new ArrayList<Position>();
		ArrayList<Position> allAttackedSquares = new ArrayList<Position>();

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j] != null
						&& board[i][j].pieceType.equalsIgnoreCase("pawn")
						&& board[i][j].color.equalsIgnoreCase(color)) {
					onePieceMoves = pawnCheckMoves(board[i][j],
							board[i][j].position);
					allAttackedSquares.addAll(onePieceMoves);
				} else if (board[i][j] != null
						&& board[i][j].pieceType.equalsIgnoreCase("rook")
						&& board[i][j].color.equalsIgnoreCase(color)) {
					onePieceMoves = rookCheckMoves(board[i][j],
							board[i][j].position);
					allAttackedSquares.addAll(onePieceMoves);

				} else if (board[i][j] != null
						&& board[i][j].pieceType.equalsIgnoreCase("knight")
						&& board[i][j].color.equalsIgnoreCase(color)) {
					onePieceMoves = knightCheckMoves(board[i][j],
							board[i][j].position);
					allAttackedSquares.addAll(onePieceMoves);
				} else if (board[i][j] != null
						&& board[i][j].pieceType.equalsIgnoreCase("bishop")
						&& board[i][j].color.equalsIgnoreCase(color)) {
					onePieceMoves = bishopCheckMoves(board[i][j],
							board[i][j].position);
					allAttackedSquares.addAll(onePieceMoves);
				} else if (board[i][j] != null
						&& board[i][j].pieceType.equalsIgnoreCase("queen")
						&& board[i][j].color.equalsIgnoreCase(color)) {
					onePieceMoves = queenCheckMoves(board[i][j],
							board[i][j].position);
					allAttackedSquares.addAll(onePieceMoves);

				} else if (board[i][j] != null
						&& board[i][j].pieceType.equalsIgnoreCase("king")
						&& board[i][j].color.equalsIgnoreCase(color)) {
					onePieceMoves = kingCheckMoves(board[i][j],
							board[i][j].position);

					allAttackedSquares.addAll(onePieceMoves);
				}
			}
		}
		return allAttackedSquares;
	}

	public static boolean isCheckMate(String color) {

		ChessPiece king = getKingPos(color);
		ArrayList<Position> allAttackedSquares = getAttacked(color);

		Position left = new Position(king.position.x - 1, king.position.y);
		Position topLeft = new Position(king.position.x - 1,
				king.position.y + 1);
		Position top = new Position(king.position.x, king.position.y + 1);
		Position topRight = new Position(king.position.x + 1,
				king.position.y + 1);
		Position right = new Position(king.position.x + 1, king.position.y);
		Position bottomRight = new Position(king.position.x + 1,
				king.position.y - 1);
		Position bottom = new Position(king.position.x, king.position.y - 1);
		Position bottomLeft = new Position(king.position.x - 1,
				king.position.y - 1);

		if (board[king.position.x][king.position.y] != null
				&& allAttackedSquares
						.contains(board[king.position.x][king.position.y].position)) {

			if (king.position.x == 0 && king.position.y == 7) {
				if ((allAttackedSquares.contains(right) || board[king.position.x + 1][king.position.y] != null)
						&& (allAttackedSquares.contains(bottomRight) || board[king.position.x + 1][king.position.y - 1] != null)
						&& (allAttackedSquares.contains(bottom) || board[king.position.x][king.position.y - 1] != null)) {
					return true;
				}
			} else if (king.position.x == 7 && king.position.y == 7) {

				if ((allAttackedSquares.contains(left) || board[king.position.x - 1][king.position.y] != null)
						&& (allAttackedSquares.contains(bottomLeft) || board[king.position.x - 1][king.position.y - 1] != null)
						&& (allAttackedSquares.contains(bottom) || board[king.position.x][king.position.y - 1] != null)) {
					return true;
				}
			} else if (king.position.x == 7 && king.position.y == 0) {

				if ((allAttackedSquares.contains(left) || board[king.position.x - 1][king.position.y] != null)
						&& (allAttackedSquares.contains(topLeft) || board[king.position.x - 1][king.position.y + 1] != null)
						&& (allAttackedSquares.contains(top) || board[king.position.x][king.position.y + 1] != null)) {
					return true;
				}
			} else if (king.position.x == 0 && king.position.y == 0) {

				if ((allAttackedSquares.contains(right) || board[king.position.x + 1][king.position.y] != null)
						&& (allAttackedSquares.contains(topRight) || board[king.position.x + 1][king.position.y + 1] != null)
						&& (allAttackedSquares.contains(top) || board[king.position.x][king.position.y + 1] != null)) {
					return true;
				}
			} else if (king.position.x == 0) {

				if ((allAttackedSquares.contains(top) || board[king.position.x][king.position.y + 1] != null)
						&& (allAttackedSquares.contains(topRight) || board[king.position.x + 1][king.position.y + 1] != null)
						&& (allAttackedSquares.contains(right) || board[king.position.x + 1][king.position.y] != null)
						&& (allAttackedSquares.contains(bottomRight) || board[king.position.x + 1][king.position.y - 1] != null)
						&& (allAttackedSquares.contains(bottom) || board[king.position.x][king.position.y - 1] != null)) {
					return true;
				}
			} else if (king.position.x == 7) {

				if ((allAttackedSquares.contains(top) || board[king.position.x][king.position.y + 1] != null)
						&& (allAttackedSquares.contains(topLeft) || board[king.position.x - 1][king.position.y + 1] != null)
						&& (allAttackedSquares.contains(left) || board[king.position.x - 1][king.position.y] != null)
						&& (allAttackedSquares.contains(bottomLeft) || board[king.position.x - 1][king.position.y - 1] != null)
						&& (allAttackedSquares.contains(bottom) || board[king.position.x][king.position.y - 1] != null)) {
					return true;
				}
			} else if (king.position.y == 0) {

				if ((allAttackedSquares.contains(left) || board[king.position.x - 1][king.position.y] != null)
						&& (allAttackedSquares.contains(topLeft) || board[king.position.x - 1][king.position.y + 1] != null)
						&& (allAttackedSquares.contains(top) || board[king.position.x][king.position.y + 1] != null)
						&& (allAttackedSquares.contains(topRight) || board[king.position.x + 1][king.position.y + 1] != null)
						&& (allAttackedSquares.contains(right) || board[king.position.x + 1][king.position.y] != null)) {
					return true;
				}
			} else if (king.position.y == 7) {

				if ((allAttackedSquares.contains(left) || board[king.position.x - 1][king.position.y] != null)
						&& (allAttackedSquares.contains(bottomLeft) || board[king.position.x - 1][king.position.y - 1] != null)
						&& (allAttackedSquares.contains(bottom) || board[king.position.x][king.position.y - 1] != null)
						&& (allAttackedSquares.contains(bottomRight) || board[king.position.x + 1][king.position.y - 1] != null)
						&& (allAttackedSquares.contains(right) || board[king.position.x + 1][king.position.y] != null)) {
					return true;
				}
			} else {

				if ((allAttackedSquares.contains(left) || board[king.position.x - 1][king.position.y] != null)
						&& (allAttackedSquares.contains(topLeft) || board[king.position.x - 1][king.position.y + 1] != null)
						&& (allAttackedSquares.contains(top) || board[king.position.x][king.position.y + 1] != null)
						&& (allAttackedSquares.contains(topRight) || board[king.position.x + 1][king.position.y + 1] != null)
						&& (allAttackedSquares.contains(right) || board[king.position.x + 1][king.position.y] != null)
						&& (allAttackedSquares.contains(bottomRight) || board[king.position.x + 1][king.position.y - 1] != null)
						&& (allAttackedSquares.contains(bottom) || board[king.position.x][king.position.y - 1] != null)
						&& (allAttackedSquares.contains(bottomLeft) || board[king.position.x - 1][king.position.y - 1] != null)) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean isStaleMate(String color) {

		ChessPiece king = getKingPos(color);
		ArrayList<Position> allAttackedSquares = getAttacked(color);
		ArrayList<Position> opponentsAllAttacked;

		if (color.equalsIgnoreCase("white"))
			opponentsAllAttacked = getAttacked("black");
		else
			opponentsAllAttacked = getAttacked("white");

		Position left = new Position(king.position.x - 1, king.position.y);
		Position topLeft = new Position(king.position.x - 1,
				king.position.y + 1);
		Position top = new Position(king.position.x, king.position.y + 1);
		Position topRight = new Position(king.position.x + 1,
				king.position.y + 1);
		Position right = new Position(king.position.x + 1, king.position.y);
		Position bottomRight = new Position(king.position.x + 1,
				king.position.y - 1);
		Position bottom = new Position(king.position.x, king.position.y - 1);
		Position bottomLeft = new Position(king.position.x - 1,
				king.position.y - 1);

		int counter = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j] != null) {
					counter++;

				}
			}
		}

		if (counter == 2)
			return true;

		if (board[king.position.x][king.position.y] != null
				&& !allAttackedSquares
						.contains(board[king.position.x][king.position.y].position)) {

			if (king.position.x == 0 && king.position.y == 7) {
				if ((allAttackedSquares.contains(right) || board[king.position.x + 1][king.position.y] != null)
						&& (allAttackedSquares.contains(bottomRight) || board[king.position.x + 1][king.position.y - 1] != null)
						&& (allAttackedSquares.contains(bottom) || board[king.position.x][king.position.y - 1] != null)
						&& (board[king.position.x + 1][king.position.y] == null || board[king.position.x + 1][king.position.y].color
								.equalsIgnoreCase(color))
						&& (board[king.position.x + 1][king.position.y - 1] == null || board[king.position.x + 1][king.position.y - 1].color
								.equalsIgnoreCase(color))
						&& (board[king.position.x][king.position.y - 1] == null || board[king.position.x][king.position.y - 1].color
								.equalsIgnoreCase(color))) {
					return true;
				}
			} else if (king.position.x == 7 && king.position.y == 7) {

				if ((allAttackedSquares.contains(left) || board[king.position.x - 1][king.position.y] != null)
						&& (allAttackedSquares.contains(bottomLeft) || board[king.position.x - 1][king.position.y - 1] != null)
						&& (allAttackedSquares.contains(bottom) || board[king.position.x][king.position.y - 1] != null)
						&& (board[king.position.x - 1][king.position.y] == null || board[king.position.x - 1][king.position.y].color
								.equalsIgnoreCase(color))
						&& (board[king.position.x - 1][king.position.y - 1] == null || board[king.position.x - 1][king.position.y - 1].color
								.equalsIgnoreCase(color))
						&& (board[king.position.x][king.position.y - 1] == null || board[king.position.x][king.position.y - 1].color
								.equalsIgnoreCase(color))) {
					return true;
				}
			} else if (king.position.x == 7 && king.position.y == 0) {

				if ((allAttackedSquares.contains(left) || board[king.position.x - 1][king.position.y] != null)
						&& (allAttackedSquares.contains(topLeft) || board[king.position.x - 1][king.position.y + 1] != null)
						&& (allAttackedSquares.contains(top) || board[king.position.x][king.position.y + 1] != null)
						&& (board[king.position.x - 1][king.position.y] == null || board[king.position.x - 1][king.position.y].color
								.equalsIgnoreCase(color))
						&& (board[king.position.x - 1][king.position.y + 1] == null || board[king.position.x - 1][king.position.y + 1].color
								.equalsIgnoreCase(color))
						&& (board[king.position.x][king.position.y + 1] == null || board[king.position.x][king.position.y + 1].color
								.equalsIgnoreCase(color))) {
					return true;
				}
			} else if (king.position.x == 0 && king.position.y == 0) {

				if ((allAttackedSquares.contains(right) || board[king.position.x + 1][king.position.y] != null)
						&& (allAttackedSquares.contains(topRight) || board[king.position.x + 1][king.position.y + 1] != null)
						&& (allAttackedSquares.contains(top) || board[king.position.x][king.position.y + 1] != null)
						&& (board[king.position.x + 1][king.position.y] == null || board[king.position.x + 1][king.position.y].color
								.equalsIgnoreCase(color))
						&& (board[king.position.x + 1][king.position.y + 1] == null || board[king.position.x + 1][king.position.y + 1].color
								.equalsIgnoreCase(color))
						&& (board[king.position.x][king.position.y + 1] == null || board[king.position.x][king.position.y + 1].color
								.equalsIgnoreCase(color))) {
					return true;
				}
			} else if (king.position.x == 0) {

				if ((allAttackedSquares.contains(top) || board[king.position.x][king.position.y + 1] != null)
						&& (allAttackedSquares.contains(topRight) || board[king.position.x + 1][king.position.y + 1] != null)
						&& (allAttackedSquares.contains(right) || board[king.position.x + 1][king.position.y] != null)
						&& (allAttackedSquares.contains(bottomRight) || board[king.position.x + 1][king.position.y - 1] != null)
						&& (allAttackedSquares.contains(bottom) || board[king.position.x][king.position.y - 1] != null)
						&& (board[king.position.x][king.position.y + 1] == null || board[king.position.x][king.position.y + 1].color
								.equalsIgnoreCase(color))
						&& (board[king.position.x + 1][king.position.y + 1] == null || board[king.position.x + 1][king.position.y + 1].color
								.equalsIgnoreCase(color))
						&& (board[king.position.x + 1][king.position.y] == null || board[king.position.x + 1][king.position.y].color
								.equalsIgnoreCase(color))
						&& (board[king.position.x + 1][king.position.y - 1] == null || board[king.position.x + 1][king.position.y - 1].color
								.equalsIgnoreCase(color))
						&& (board[king.position.x][king.position.y - 1] == null || board[king.position.x][king.position.y - 1].color
								.equalsIgnoreCase(color))) {
					return true;
				}
			} else if (king.position.x == 7) {

				if ((allAttackedSquares.contains(top) || board[king.position.x][king.position.y + 1] != null)
						&& (allAttackedSquares.contains(topLeft) || board[king.position.x - 1][king.position.y + 1] != null)
						&& (allAttackedSquares.contains(left) || board[king.position.x - 1][king.position.y] != null)
						&& (allAttackedSquares.contains(bottomLeft) || board[king.position.x - 1][king.position.y - 1] != null)
						&& (allAttackedSquares.contains(bottom) || board[king.position.x][king.position.y - 1] != null)
						&& (board[king.position.x][king.position.y + 1] == null || board[king.position.x][king.position.y + 1].color
								.equalsIgnoreCase(color))
						&& (board[king.position.x - 1][king.position.y + 1] == null || board[king.position.x - 1][king.position.y + 1].color
								.equalsIgnoreCase(color))
						&& (board[king.position.x - 1][king.position.y] == null || board[king.position.x - 1][king.position.y].color
								.equalsIgnoreCase(color))
						&& (board[king.position.x - 1][king.position.y - 1] == null || board[king.position.x - 1][king.position.y - 1].color
								.equalsIgnoreCase(color))
						&& (board[king.position.x][king.position.y - 1] == null || board[king.position.x][king.position.y - 1].color
								.equalsIgnoreCase(color))) {
					return true;
				}
			} else if (king.position.y == 0) {

				if ((allAttackedSquares.contains(left) || board[king.position.x - 1][king.position.y] != null)
						&& (allAttackedSquares.contains(topLeft) || board[king.position.x - 1][king.position.y + 1] != null)
						&& (allAttackedSquares.contains(top) || board[king.position.x][king.position.y + 1] != null)
						&& (allAttackedSquares.contains(topRight) || board[king.position.x + 1][king.position.y + 1] != null)
						&& (allAttackedSquares.contains(right) || board[king.position.x + 1][king.position.y] != null)
						&& (board[king.position.x - 1][king.position.y] == null || board[king.position.x - 1][king.position.y].color
								.equalsIgnoreCase(color))
						&& (board[king.position.x - 1][king.position.y + 1] == null || board[king.position.x - 1][king.position.y + 1].color
								.equalsIgnoreCase(color))
						&& (board[king.position.x][king.position.y + 1] == null || board[king.position.x][king.position.y + 1].color
								.equalsIgnoreCase(color))
						&& (board[king.position.x + 1][king.position.y + 1] == null || board[king.position.x + 1][king.position.y + 1].color
								.equalsIgnoreCase(color))
						&& (board[king.position.x + 1][king.position.y] == null || board[king.position.x + 1][king.position.y].color
								.equalsIgnoreCase(color))) {
					return true;
				}
			} else if (king.position.y == 7) {

				if ((allAttackedSquares.contains(left) || board[king.position.x - 1][king.position.y] != null)
						&& (allAttackedSquares.contains(bottomLeft) || board[king.position.x - 1][king.position.y - 1] != null)
						&& (allAttackedSquares.contains(bottom) || board[king.position.x][king.position.y - 1] != null)
						&& (allAttackedSquares.contains(bottomRight) || board[king.position.x + 1][king.position.y - 1] != null)
						&& (allAttackedSquares.contains(right) || board[king.position.x + 1][king.position.y] != null)
						&& (board[king.position.x - 1][king.position.y] == null || board[king.position.x - 1][king.position.y].color
								.equalsIgnoreCase(color))
						&& (board[king.position.x - 1][king.position.y - 1] == null || board[king.position.x - 1][king.position.y - 1].color
								.equalsIgnoreCase(color))
						&& (board[king.position.x][king.position.y - 1] == null || board[king.position.x][king.position.y - 1].color
								.equalsIgnoreCase(color))
						&& (board[king.position.x + 1][king.position.y - 1] == null || board[king.position.x + 1][king.position.y - 1].color
								.equalsIgnoreCase(color))
						&& (board[king.position.x + 1][king.position.y] == null || board[king.position.x + 1][king.position.y].color
								.equalsIgnoreCase(color))) {
					return true;
				}
			} else {

				if ((allAttackedSquares.contains(left) || board[king.position.x - 1][king.position.y] != null)
						&& (allAttackedSquares.contains(topLeft) || board[king.position.x - 1][king.position.y + 1] != null)
						&& (allAttackedSquares.contains(top) || board[king.position.x][king.position.y + 1] != null)
						&& (allAttackedSquares.contains(topRight) || board[king.position.x + 1][king.position.y + 1] != null)
						&& (allAttackedSquares.contains(right) || board[king.position.x + 1][king.position.y] != null)
						&& (allAttackedSquares.contains(bottomRight) || board[king.position.x + 1][king.position.y - 1] != null)
						&& (allAttackedSquares.contains(bottom) || board[king.position.x][king.position.y - 1] != null)
						&& (allAttackedSquares.contains(bottomLeft) || board[king.position.x - 1][king.position.y - 1] != null)
						&& (board[king.position.x - 1][king.position.y] == null || board[king.position.x - 1][king.position.y].color
								.equalsIgnoreCase(color))
						&& (board[king.position.x - 1][king.position.y + 1] == null || board[king.position.x - 1][king.position.y + 1].color
								.equalsIgnoreCase(color))
						&& (board[king.position.x][king.position.y + 1] == null || board[king.position.x][king.position.y + 1].color
								.equalsIgnoreCase(color))
						&& (board[king.position.x + 1][king.position.y + 1] == null || board[king.position.x + 1][king.position.y + 1].color
								.equalsIgnoreCase(color))
						&& (board[king.position.x + 1][king.position.y] == null || board[king.position.x + 1][king.position.y].color
								.equalsIgnoreCase(color))
						&& (board[king.position.x + 1][king.position.y - 1] == null || board[king.position.x + 1][king.position.y - 1].color
								.equalsIgnoreCase(color))
						&& (board[king.position.x][king.position.y - 1] == null || board[king.position.x][king.position.y - 1].color
								.equalsIgnoreCase(color))
						&& (board[king.position.x - 1][king.position.y - 1] == null || board[king.position.x - 1][king.position.y - 1].color
								.equalsIgnoreCase(color))) {
					return true;
				}
			}
		}
		return false;
	}

	public static void Draw() {

		System.out.println("+----+----+----+----+----+----+----+----+");
		for (int j = 7; j >= 0; j--) {

			for (int i = 0; i < 8; i++) {

				System.out.print('|');

				if (board[i][j] != null) {
					if (i % 2 == 1 && j % 2 == 1)
						System.out.print("#" + board[i][j].pieceName + "#");
					else if (i % 2 == 0 && j % 2 == 0)
						System.out.print("#" + board[i][j].pieceName + "#");
					else
						System.out.print(" " + board[i][j].pieceName + " ");
				} else {
					if (i % 2 == 1 && j % 2 == 1)
						System.out.print("####");
					else if (i % 2 == 0 && j % 2 == 0)
						System.out.print("####");
					else
						System.out.print("    ");
				}

				if (i == 7) {
					System.out.print("| " + (j + 1));
				}

			}
			System.out.println();
			System.out.println("+----+----+----+----+----+----+----+----+");
		}

		System.out.print("  a  ");
		System.out.print("  b  ");
		System.out.print("  c  ");
		System.out.print("  d  ");
		System.out.print("  e  ");
		System.out.print("  f  ");
		System.out.print("  g  ");
		System.out.print("  h  ");
		System.out.println("");

	}

	public static int checkPieceType(ChessPiece piece, Position origin,
			Position end) {

		ArrayList<Position> validMoves = new ArrayList<Position>();
		ArrayList<Position> checkMoves = new ArrayList<Position>();
		ArrayList<Position> allAttacked = new ArrayList<Position>();
		ArrayList<Position> oppAllAttacked = new ArrayList<Position>();
		ChessPiece temp = null;
		boolean putMyselfInCheck = false;
		int valid = 1;

		if (piece.pieceType.equalsIgnoreCase("pawn")) {

			validMoves = pawnCheckMoves(piece, origin);
			if (validMoves.contains(end)) {
				
				movePiece(piece, origin, end);
				checkMoves = pawnCheckMoves(piece, end);
				allAttacked = getAttacked(piece.color);

				if (origin.y == 1 && end.y == origin.y + 2) {
					enPassantCounterWhite = Chess.counter;
					enPassantWhite = end;
					ePW = true;
				} else if (origin.y == 6 && end.y == origin.y - 2) {
					enPassantCounterBlack = Chess.counter;
					enPassantBlack = end;
					ePB = true;
				} else {
					ePB = false;
					ePW = false;
				}

				if (origin.x + 1 == end.x
						&& origin.y - 1 == end.y
						&& board[origin.x + 1][origin.y] != null
						&& board[origin.x + 1][origin.y].pieceType
								.equalsIgnoreCase("pawn")) {
					board[origin.x + 1][origin.y] = null;
				} else if (origin.x - 1 == end.x
						&& origin.y - 1 == end.y
						&& board[origin.x - 1][origin.y] != null
						&& board[origin.x - 1][origin.y].pieceType
								.equalsIgnoreCase("pawn")) {
					board[origin.x - 1][origin.y] = null;
				} else if (origin.x - 1 == end.x
						&& origin.y + 1 == end.y
						&& board[origin.x - 1][origin.y] != null
						&& board[origin.x - 1][origin.y].pieceType
								.equalsIgnoreCase("pawn")) {
					board[origin.x - 1][origin.y] = null;
				} else if (origin.x + 1 == end.x
						&& origin.y + 1 == end.y
						&& board[origin.x + 1][origin.y] != null
						&& board[origin.x + 1][origin.y].pieceType
								.equalsIgnoreCase("pawn")) {
					board[origin.x + 1][origin.y] = null;
				}
				if (isCheckMate(piece.color)) {
					Draw();
					System.out.println("Checkmate.");
					if (piece.color.equalsIgnoreCase("white"))
						return 2;
					else if (piece.color.equalsIgnoreCase("black"))
						return 3;
				} else if (containKing(checkMoves, piece.color) || containKing(allAttacked, piece.color)) {
					System.out.println("Check.");
					check = true;
				} else if (isStaleMate(piece.color)) {
					Draw();
					System.out.println("Stalemate.");
					return 4;
				}

			} else {
				
				printValidMoves(validMoves);
				valid = 0;
			}

		} else if (piece.pieceType.equalsIgnoreCase("rook")) {

			validMoves = rookCheckMoves(piece, origin);
			if (validMoves.contains(end)) {
			
				movePiece(piece, origin, end);
				checkMoves = rookCheckMoves(piece, end);
				allAttacked = getAttacked(piece.color);

				if (origin.equals(new Position(0, 0)))
					wRookLeftMoved = true;
				else if (origin.equals(new Position(0, 7)))
					wRookRightMoved = true;
				else if (origin.equals(new Position(7, 0)))
					bRookLeftMoved = true;
				else if (origin.equals(new Position(7, 7)))
					bRookRightMoved = true;

				if (isCheckMate(piece.color)) {
					Draw();
					System.out.println("Checkmate.");
					if (piece.color.equalsIgnoreCase("white"))
						return 2;
					else if (piece.color.equalsIgnoreCase("black"))
						return 3;
				} else if (containKing(checkMoves, piece.color) || containKing(allAttacked, piece.color)) {
					check = true;
					System.out.println("Check.");
				} else if (isStaleMate(piece.color)) {
					Draw();
					System.out.println("Stalemate.");
					return 4;
				}

			} else {
				
				printValidMoves(validMoves);
				valid = 0;
			}

		} else if (piece.pieceType.equalsIgnoreCase("knight")) {

			validMoves = knightCheckMoves(piece, origin);
			if (validMoves.contains(end)) {
				
				movePiece(piece, origin, end);
				checkMoves = knightCheckMoves(piece, end);
				allAttacked = getAttacked(piece.color);

				if (isCheckMate(piece.color)) {
					Draw();
					System.out.println("Checkmate.");
					if (piece.color.equalsIgnoreCase("white"))
						return 2;
					else if (piece.color.equalsIgnoreCase("black"))
						return 3;
				} else if (containKing(checkMoves, piece.color) || containKing(allAttacked, piece.color)) {
					check = true;
					System.out.println("Check.");
				} else if (isStaleMate(piece.color)) {
					Draw();
					System.out.println("Stalemate.");
					return 4;
				}

			} else {
				
				printValidMoves(validMoves);
				valid = 0;
			}

		} else if (piece.pieceType.equalsIgnoreCase("bishop")) {

			validMoves = bishopCheckMoves(piece, origin);
			if (validMoves.contains(end)) {

				movePiece(piece, origin, end);
				allAttacked = getAttacked(piece.color);
				checkMoves = bishopCheckMoves(piece, end);
				if (isCheckMate(piece.color)) {
					Draw();
					System.out.println("Checkmate.");
					if (piece.color.equalsIgnoreCase("white"))
						return 2;
					else if (piece.color.equalsIgnoreCase("black"))
						return 3;
				} else if (containKing(checkMoves, piece.color) || containKing(allAttacked, piece.color)) {
					check = true;
					System.out.println("Check.");
				} else if (isStaleMate(piece.color)) {
					Draw();
					System.out.println("Stalemate.");
					return 4;
				}

			} else {
				
				printValidMoves(validMoves);
				valid = 0;
			}

		} else if (piece.pieceType.equalsIgnoreCase("queen")) {

			validMoves = queenCheckMoves(piece, origin);
			if (validMoves.contains(end)) {
				
				movePiece(piece, origin, end);
				checkMoves = queenCheckMoves(piece, end);
				allAttacked = getAttacked(piece.color);

				if (isCheckMate(piece.color)) {
					Draw();
					System.out.println("Checkmate.");
					if (piece.color.equalsIgnoreCase("white"))
						return 2;
					else if (piece.color.equalsIgnoreCase("black"))
						return 3;
				} else if (containKing(checkMoves, piece.color) || containKing(allAttacked, piece.color)) {
					check = true;
					System.out.println("Check.");
				} else if (isStaleMate(piece.color)) {
					Draw();
					System.out.println("Stalemate.");
					return 4;
				}

			} else {
				
				printValidMoves(validMoves);
				valid = 0;
			}

		} else if (piece.pieceType.equalsIgnoreCase("king")) {

			validMoves = kingCheckMoves(piece, origin);
			
			/*
			 * for(int i=0; i<validMoves.size(); i++){
			 * System.out.println(validMoves.get(i).x + " " +
			 * validMoves.get(i).y); }
			 */
			checkMoves = kingRemoveMoves(piece, origin,
					kingCheckMoves(piece, origin));
			allAttacked = getAttacked(piece.color);

			if (isStaleMate(piece.color) && checkMoves.isEmpty()) {
				System.out.println("Stalemate.");
				return 4;
			}

			if (checkMoves.contains(end)) {
				
				movePiece(piece, origin, end);

				if (origin.equals(new Position(4, 0)))
					wKingMoved = true;
				else if (origin.equals(new Position(4, 7)))
					bKingMoved = true;

				if (piece.color.equals("white") && origin.x + 2 == end.x) {
					board[5][0] = board[7][0];
					board[7][0] = null;
				} else if (piece.color.equals("white") && origin.x - 2 == end.x) {
					board[3][0] = board[0][0];
					board[0][0] = null;
				} else if (piece.color.equals("black") && origin.x + 2 == end.x) {
					board[5][7] = board[7][7];
					board[7][7] = null;
				} else if (piece.color.equals("black") && origin.x - 2 == end.x) {
					board[3][7] = board[0][7];
					board[0][7] = null;
				}

				int counter = 0;
				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 8; j++) {
						if (board[i][j] != null) {
							counter++;

						}
					}
				}

				if (counter == 2) {
					Draw();
					System.out.println("Stalemate.");
					return 4;
				}
				
				if (isCheckMate(piece.color)) {
					Draw();
					System.out.println("Checkmate.");
					if (piece.color.equalsIgnoreCase("white"))
						return 2;
					else if (piece.color.equalsIgnoreCase("black"))
						return 3;
				} else if (containKing(checkMoves, piece.color) || containKing(allAttacked, piece.color)) {
					check = true;
					System.out.println("Check.");
				} else if (checkMoves.isEmpty() && isStaleMate(piece.color)) {
					Draw();
					System.out.println("Stalemate.");
					return 4;
				}
			} else {
				
				printValidMoves(checkMoves);
				valid = 0;
			}
		}
		return valid;
	}

	public static void movePiece(ChessPiece piece, Position origin, Position end) {

		if (board[origin.x][origin.y] != null) {
			board[end.x][end.y] = piece;
			board[origin.x][origin.y] = null;
			piece.position = end;
			Chess.counter++;
		}
	}

	public static void printValidMoves(ArrayList<Position> validMoves) {

		System.out.println("Invalid move. Valid moves are: ");
		for (Position pos : validMoves) {

			Integer yCoor = pos.y + 1;
			if (pos.x == 0)
				System.out.println("a" + yCoor);
			else if (pos.x == 1)
				System.out.println("b" + yCoor);
			else if (pos.x == 2)
				System.out.println("c" + yCoor);
			else if (pos.x == 3)
				System.out.println("d" + yCoor);
			else if (pos.x == 4)
				System.out.println("e" + yCoor);
			else if (pos.x == 5)
				System.out.println("f" + yCoor);
			else if (pos.x == 6)
				System.out.println("g" + yCoor);
			else if (pos.x == 7)
				System.out.println("h" + yCoor);
		}
	}

	public static ArrayList<Position> pawnCheckMoves(ChessPiece piece,
			Position origin) {

		ArrayList<Position> validMoves = new ArrayList<Position>();
		Position xyCoor;

		if (piece.color.equalsIgnoreCase("white")) {

			if (ePB
					&& enPassantCounterBlack == Chess.counter
					&& origin.x > 0
					&& enPassantBlack.x < 7
					&& board[enPassantBlack.x + 1][enPassantBlack.y] != null
					&& board[enPassantBlack.x + 1][enPassantBlack.y].pieceName
							.equalsIgnoreCase("wp")
					&& enPassantBlack.x == origin.x - 1
					&& enPassantBlack.y == origin.y) {
				xyCoor = new Position(origin.x - 1, origin.y + 1);
				validMoves.add(xyCoor);
			}

			if (ePB
					&& enPassantCounterBlack == Chess.counter
					&& origin.x < 7
					&& enPassantBlack.x > 0
					&& board[enPassantBlack.x - 1][enPassantBlack.y] != null
					&& board[enPassantBlack.x - 1][enPassantBlack.y].pieceName
							.equalsIgnoreCase("wp")
					&& enPassantBlack.x == origin.x + 1
					&& enPassantBlack.y == origin.y) {
				xyCoor = new Position(origin.x + 1, origin.y + 1);
				validMoves.add(xyCoor);
			}

			if (origin.y < 7) {

				if (board[origin.x][origin.y + 1] == null) {

					xyCoor = new Position(origin.x, origin.y + 1);
					validMoves.add(xyCoor);

				}
			}

			if (origin.y == 1) {

				if (board[origin.x][origin.y + 1] == null
						&& board[origin.x][origin.y + 2] == null) {
					xyCoor = new Position(origin.x, origin.y + 2);
					validMoves.add(xyCoor);

				}
			}

			if (origin.x != 0
					&& origin.y < 7
					&& board[origin.x - 1][origin.y + 1] != null
					&& board[origin.x - 1][origin.y + 1].color
							.equalsIgnoreCase("black")) {
				xyCoor = new Position(origin.x - 1, origin.y + 1);
				validMoves.add(xyCoor);
			}

			if (origin.x != 7
					&& origin.y < 7
					&& board[origin.x + 1][origin.y + 1] != null
					&& board[origin.x + 1][origin.y + 1].color
							.equalsIgnoreCase("black")) {
				xyCoor = new Position(origin.x + 1, origin.y + 1);
				validMoves.add(xyCoor);
			}

		} else {

			if (ePW
					&& Chess.counter == enPassantCounterWhite
					&& origin.x > 0
					&& enPassantWhite.x < 7
					&& board[enPassantWhite.x + 1][enPassantWhite.y] != null
					&& board[enPassantWhite.x + 1][enPassantWhite.y].pieceName
							.equalsIgnoreCase("bp")
					&& enPassantWhite.x == origin.x - 1
					&& enPassantWhite.y == origin.y) {
				xyCoor = new Position(origin.x - 1, origin.y - 1);
				validMoves.add(xyCoor);
			}

			if (ePW
					&& Chess.counter == enPassantCounterWhite
					&& origin.x < 7
					&& enPassantWhite.x > 0
					&& board[enPassantWhite.x - 1][enPassantWhite.y] != null
					&& board[enPassantWhite.x - 1][enPassantWhite.y].pieceName
							.equalsIgnoreCase("bp")
					&& enPassantWhite.x == origin.x + 1
					&& enPassantWhite.y == origin.y) {
				xyCoor = new Position(origin.x + 1, origin.y - 1);
				validMoves.add(xyCoor);
			}

			if (origin.y > 0) {
				if (board[origin.x][origin.y - 1] == null) {
					xyCoor = new Position(origin.x, origin.y - 1);
					validMoves.add(xyCoor);
				}
			}

			if (origin.y == 6) {

				if (board[origin.x][origin.y - 1] == null
						&& board[origin.x][origin.y - 2] == null) {
					xyCoor = new Position(origin.x, origin.y - 2);
					validMoves.add(xyCoor);
				}
			}

			if (origin.x != 0
					&& origin.y > 0
					&& board[origin.x - 1][origin.y - 1] != null
					&& board[origin.x - 1][origin.y - 1].color
							.equalsIgnoreCase("white")) {
				xyCoor = new Position(origin.x - 1, origin.y - 1);
				validMoves.add(xyCoor);
			}

			if (origin.x != 7
					&& origin.y > 0
					&& board[origin.x + 1][origin.y - 1] != null
					&& board[origin.x + 1][origin.y - 1].color
							.equalsIgnoreCase("white")) {
				xyCoor = new Position(origin.x + 1, origin.y - 1);
				validMoves.add(xyCoor);
			}

		}
		return validMoves;
	}

	public static ArrayList<Position> rookCheckMoves(ChessPiece piece,
			Position origin) {

		ArrayList<Position> validMoves = new ArrayList<Position>();
		Position xyCoor;
		
		int x = origin.x;
		int y = origin.y;
		
		while(x < 8){
			x += 1;
			if(x<8 && (board[x][y] == null || board[x][y].pieceType.equalsIgnoreCase("king") || !board[x][y].color.equalsIgnoreCase(piece.color))) {
				xyCoor = new Position(x,y);
				validMoves.add(xyCoor);
			} else {
				break;
			}
		}
		
		x = origin.x;
		y = origin.y;
		while(x >= 0){
			x -= 1;
			if(x>=0 && (board[x][y] == null || board[x][y].pieceType.equalsIgnoreCase("king") || !board[x][y].color.equalsIgnoreCase(piece.color))) {
				xyCoor = new Position(x,y);
				validMoves.add(xyCoor);
			} else {
				break;
			}
		}
		
		x = origin.x;
		y = origin.y;
		while(y < 8){
			y += 1;
			if(y<8 && (board[x][y] == null || board[x][y].pieceType.equalsIgnoreCase("king") || !board[x][y].color.equalsIgnoreCase(piece.color))) {
				xyCoor = new Position(x,y);
				validMoves.add(xyCoor);
			} else {
				break;
			}
		}
		
		x = origin.x;
		y = origin.y;	
		while(y >= 0){
			y -= 1;
			if(y>=0 && (board[x][y] == null || board[x][y].pieceType.equalsIgnoreCase("king") || !board[x][y].color.equalsIgnoreCase(piece.color))) {
				xyCoor = new Position(x,y);
				validMoves.add(xyCoor);
			} else {
				break;
			}
		}

		return validMoves;
	}

	public static ArrayList<Position> knightCheckMoves(ChessPiece piece,
			Position origin) {

		ArrayList<Position> validMoves = new ArrayList<Position>();
		Position xyCoor;

		if (origin.x != 7
				&& origin.y < 6
				&& (board[origin.x + 1][origin.y + 2] == null || (board[origin.x + 1][origin.y + 2] != null && board[origin.x + 1][origin.y + 2].color != piece.color))) {
			xyCoor = new Position(origin.x + 1, origin.y + 2);
			validMoves.add(xyCoor);

		}

		if (origin.x != 7
				&& origin.y > 1
				&& (board[origin.x + 1][origin.y - 2] == null || (board[origin.x + 1][origin.y - 2] != null && board[origin.x + 1][origin.y - 2].color != piece.color))) {

			xyCoor = new Position(origin.x + 1, origin.y - 2);
			validMoves.add(xyCoor);

		}

		if (origin.x != 0
				&& origin.y < 6
				&& (board[origin.x - 1][origin.y + 2] == null || (board[origin.x - 1][origin.y + 2] != null && board[origin.x - 1][origin.y + 2].color != piece.color))) {

			xyCoor = new Position(origin.x - 1, origin.y + 2);
			validMoves.add(xyCoor);

		}

		if (origin.x != 0
				&& origin.y > 1
				&& (board[origin.x - 1][origin.y - 2] == null || (board[origin.x - 1][origin.y - 2] != null && board[origin.x - 1][origin.y - 2].color != piece.color))) {

			xyCoor = new Position(origin.x - 1, origin.y - 2);
			validMoves.add(xyCoor);

		}

		if (origin.x < 6
				&& origin.y != 7
				&& (board[origin.x + 2][origin.y + 1] == null || (board[origin.x + 2][origin.y + 1] != null && board[origin.x + 2][origin.y + 1].color != piece.color))) {

			xyCoor = new Position(origin.x + 2, origin.y + 1);
			validMoves.add(xyCoor);

		}

		if (origin.x < 6
				&& origin.y != 0
				&& (board[origin.x + 2][origin.y - 1] == null || (board[origin.x + 2][origin.y - 1] != null && board[origin.x + 2][origin.y - 1].color != piece.color))) {

			xyCoor = new Position(origin.x + 2, origin.y - 1);
			validMoves.add(xyCoor);

		}

		if (origin.x > 1
				&& origin.y != 7
				&& (board[origin.x - 2][origin.y + 1] == null || (board[origin.x - 2][origin.y + 1] != null && board[origin.x - 2][origin.y + 1].color != piece.color))) {

			xyCoor = new Position(origin.x - 2, origin.y + 1);
			validMoves.add(xyCoor);

		}

		if (origin.x > 1
				&& origin.y != 0
				&& (board[origin.x - 2][origin.y - 1] == null || (board[origin.x - 2][origin.y - 1] != null && board[origin.x - 2][origin.y - 1].color != piece.color))) {

			xyCoor = new Position(origin.x - 2, origin.y - 1);
			validMoves.add(xyCoor);

		}
		return validMoves;

	}

	public static ArrayList<Position> bishopCheckMoves(ChessPiece piece,
			Position origin) {

		ArrayList<Position> validMoves = new ArrayList<Position>();
		Position xyCoor;
		
		int x = origin.x;
		int y = origin.y;
		
		while(x < 8 && y < 8){
			x += 1;
			y += 1;
			if(x<8 && y<8 && (board[x][y] == null || board[x][y].pieceType.equalsIgnoreCase("king") || !board[x][y].color.equalsIgnoreCase(piece.color))) {
				xyCoor = new Position(x,y);
				validMoves.add(xyCoor);
			} else {
				break;
			}
		}
		
		x = origin.x;
		y = origin.y;
		while(x < 8 && y > 0){
			x += 1;
			y -= 1;
			if(x<8 && y>=0 && (board[x][y] == null || board[x][y].pieceType.equalsIgnoreCase("king") || !board[x][y].color.equalsIgnoreCase(piece.color))) {
				xyCoor = new Position(x,y);
				validMoves.add(xyCoor);
			} else {
				break;
			}
		}
		
		x = origin.x;
		y = origin.y;
		while(x >= 0 && y < 8){
			x -= 1;
			y += 1;
			if(x>=0 && y<8 && (board[x][y] == null || board[x][y].pieceType.equalsIgnoreCase("king") || !board[x][y].color.equalsIgnoreCase(piece.color))) {
				xyCoor = new Position(x,y);
				validMoves.add(xyCoor);
			} else {
				break;
			}
		}
		
		x = origin.x;
		y = origin.y;	
		while(x >= 0 && y >= 0){
			x -= 1;
			y -= 1;
			if(x>=0 && y>=0 && (board[x][y] == null || board[x][y].pieceType.equalsIgnoreCase("king") || !board[x][y].color.equalsIgnoreCase(piece.color))) {
				xyCoor = new Position(x,y);
				validMoves.add(xyCoor);
			} else {
				break;
			}
		}

		return validMoves;
	}

	public static ArrayList<Position> queenCheckMoves(ChessPiece piece,
			Position origin) {

		ArrayList<Position> validMoves = new ArrayList<Position>();
		Position xyCoor;

		int x = origin.x;
		int y = origin.y;
		
		while(x < 8 && y < 8){
			x += 1;
			y += 1;
			if(x<8 && y<8 && (board[x][y] == null || board[x][y].pieceType.equalsIgnoreCase("king") || !board[x][y].color.equalsIgnoreCase(piece.color))) {
				xyCoor = new Position(x,y);
				validMoves.add(xyCoor);
			} else {
				break;
			}
		}
		
		x = origin.x;
		y = origin.y;
		while(x < 8 && y >= 0){
			x += 1;
			y -= 1;
			if(x<8 && y>=0 && (board[x][y] == null || board[x][y].pieceType.equalsIgnoreCase("king") || !board[x][y].color.equalsIgnoreCase(piece.color))) {
				xyCoor = new Position(x,y);
				validMoves.add(xyCoor);
			} else {
				break;
			}
		}
		
		x = origin.x;
		y = origin.y;
		while(x >= 0 && y < 8){
			x -= 1;
			y += 1;
			if(x>=0 && y<8 && (board[x][y] == null || board[x][y].pieceType.equalsIgnoreCase("king") || !board[x][y].color.equalsIgnoreCase(piece.color))) {
				xyCoor = new Position(x,y);
				validMoves.add(xyCoor);
			} else {
				break;
			}
		}
		
		x = origin.x;
		y = origin.y;	
		while(x >= 0 && y >= 0){
			x -= 1;
			y -= 1;
			if(x>=0 && y>=0 && (board[x][y] == null || board[x][y].pieceType.equalsIgnoreCase("king") || !board[x][y].color.equalsIgnoreCase(piece.color))) {
				xyCoor = new Position(x,y);
				validMoves.add(xyCoor);
			} else {
				break;
			}
		}
		
		x = origin.x;
		y = origin.y;	
		while(x < 8){
			x += 1;
			if(x<8 && (board[x][y] == null || board[x][y].pieceType.equalsIgnoreCase("king") || !board[x][y].color.equalsIgnoreCase(piece.color))) {
				xyCoor = new Position(x,y);
				validMoves.add(xyCoor);
			} else {
				break;
			}
		}
		
		x = origin.x;
		y = origin.y;
		while(x >= 0){
			x -= 1;
			if(x>=0 && (board[x][y] == null || board[x][y].pieceType.equalsIgnoreCase("king") || !board[x][y].color.equalsIgnoreCase(piece.color))) {
				xyCoor = new Position(x,y);
				validMoves.add(xyCoor);
			} else {
				break;
			}
		}
		
		x = origin.x;
		y = origin.y;
		while(y < 8){
			y += 1;
			if(y<8 && (board[x][y] == null || board[x][y].pieceType.equalsIgnoreCase("king") || !board[x][y].color.equalsIgnoreCase(piece.color))) {
				xyCoor = new Position(x,y);
				validMoves.add(xyCoor);
			} else {
				break;
			}
		}
		
		x = origin.x;
		y = origin.y;	
		while(y >= 0){
			y -= 1;
			if(y>=0 && (board[x][y] == null || board[x][y].pieceType.equalsIgnoreCase("king") || !board[x][y].color.equalsIgnoreCase(piece.color))) {
				xyCoor = new Position(x,y);
				validMoves.add(xyCoor);
			} else {
				break;
			}
		}


		return validMoves;
	}

	public static ArrayList<Position> kingRemoveMoves(ChessPiece piece,
			Position origin, ArrayList<Position> validMoves) {

		Position left = new Position(piece.position.x - 1, piece.position.y);
		Position topLeft = new Position(piece.position.x - 1,
				piece.position.y + 1);
		Position top = new Position(piece.position.x, piece.position.y + 1);
		Position topRight = new Position(piece.position.x + 1,
				piece.position.y + 1);
		Position right = new Position(piece.position.x + 1, piece.position.y);
		Position bottomRight = new Position(piece.position.x + 1,
				piece.position.y - 1);
		Position bottom = new Position(piece.position.x, piece.position.y - 1);
		Position bottomLeft = new Position(piece.position.x - 1,
				piece.position.y - 1);

		String color = piece.color;

		ArrayList<Position> allAttackedSquares;

		if (color.equalsIgnoreCase("white"))
			allAttackedSquares = getAttacked("black");
		else
			allAttackedSquares = getAttacked("white");
/*
		if ((allAttackedSquares.contains(origin)
				|| allAttackedSquares.contains(new Position(origin.x + 1,
						origin.y)) || allAttackedSquares.contains(new Position(
				origin.x - 1, origin.y)))
				&& (origin.x + 2 == end.x || origin.x - 2 == end.x)) {
			validMoves.remove(end);

		}*/
		
		if ((allAttackedSquares.contains(origin) || allAttackedSquares.contains(new Position(origin.x + 1, origin.y)) || allAttackedSquares.contains(new Position(origin.x + 2, origin.y)))){
			validMoves.remove(new Position(origin.x + 2, origin.y));
		}
		
		if ((allAttackedSquares.contains(origin) || allAttackedSquares.contains(new Position(origin.x - 1, origin.y)) || allAttackedSquares.contains(new Position(origin.x - 2, origin.y)))){
			validMoves.remove(new Position(origin.x - 2, origin.y));
		}
		
		

		if (!allAttackedSquares.isEmpty()) {
			if (allAttackedSquares.contains(left))
				validMoves.remove(left);
			if (allAttackedSquares.contains(topLeft))
				validMoves.remove(topLeft);
			if (allAttackedSquares.contains(top))
				validMoves.remove(top);
			if (allAttackedSquares.contains(topRight))
				validMoves.remove(topRight);
			if (allAttackedSquares.contains(right))
				validMoves.remove(right);
			if (allAttackedSquares.contains(bottomRight))
				validMoves.remove(bottomRight);
			if (allAttackedSquares.contains(bottom))
				validMoves.remove(bottom);
			if (allAttackedSquares.contains(bottomLeft))
				validMoves.remove(bottomLeft);
		}

		ChessPiece temp = null;
		ArrayList<Position> newAttacked;

		if (piece.position.x > 0
				&& (board[left.x][left.y] == null || !board[left.x][left.y].color
						.equalsIgnoreCase(piece.color))) {
			if (board[left.x][left.y] != null)
				temp = board[left.x][left.y];
			movePiece(piece, origin, left);

			if (piece.color.equalsIgnoreCase("white"))
				newAttacked = getAttacked("black");
			else
				newAttacked = getAttacked("white");

			if (newAttacked.contains(left)
					|| ((piece.color.equalsIgnoreCase("white")
							&& (left.x < 7 && left.y < 7
									&& board[left.x + 1][left.y + 1] != null && (board[left.x + 1][left.y + 1].pieceName
									.equalsIgnoreCase("bp"))) || (left.x > 0
							&& left.y < 7
							&& board[left.x - 1][left.y + 1] != null && board[left.x - 1][left.y + 1].pieceName
							.equalsIgnoreCase("bp"))))
					|| ((piece.color.equalsIgnoreCase("black") && (left.x < 7
							&& left.y > 0
							&& board[left.x + 1][left.y - 1] != null && board[left.x + 1][left.y - 1].pieceName
							.equalsIgnoreCase("wp"))) || (left.x > 0
							&& left.y > 0
							&& board[left.x - 1][left.y - 1] != null && board[left.x - 1][left.y - 1].pieceName
							.equalsIgnoreCase("wp")))) {

				validMoves.remove(left);
				movePiece(piece, left, origin);
				board[left.x][left.y] = temp;
				temp = null;
			} else {
				movePiece(piece, left, origin);
				board[left.x][left.y] = temp;
				temp = null;
			}
		}

		if (piece.position.x > 0
				&& piece.position.y < 7
				&& (board[topLeft.x][topLeft.y] == null || !board[topLeft.x][topLeft.y].color
						.equalsIgnoreCase(piece.color))) {
			if (board[topLeft.x][topLeft.y] != null)
				temp = board[topLeft.x][topLeft.y];
			movePiece(piece, origin, topLeft);

			if (piece.color.equalsIgnoreCase("white"))
				newAttacked = getAttacked("black");
			else
				newAttacked = getAttacked("white");

			if (newAttacked.contains(topLeft)
					|| ((piece.color.equalsIgnoreCase("white")
							&& (topLeft.x < 7
									&& topLeft.y < 7
									&& board[topLeft.x + 1][topLeft.y + 1] != null && (board[topLeft.x + 1][topLeft.y + 1].pieceName
									.equalsIgnoreCase("bp"))) || (topLeft.x > 0
							&& topLeft.y < 7
							&& board[topLeft.x - 1][topLeft.y + 1] != null && board[topLeft.x - 1][topLeft.y + 1].pieceName
							.equalsIgnoreCase("bp"))))
					|| ((piece.color.equalsIgnoreCase("black")
							&& (topLeft.x < 7
									&& topLeft.y > 0
									&& board[topLeft.x + 1][topLeft.y - 1] != null && (board[topLeft.x + 1][topLeft.y - 1].pieceName
									.equalsIgnoreCase("wp"))) || (topLeft.x > 0
							&& topLeft.y > 0
							&& board[topLeft.x - 1][topLeft.y - 1] != null && board[topLeft.x - 1][topLeft.y - 1].pieceName
							.equalsIgnoreCase("wp"))))) {
				validMoves.remove(topLeft);
				movePiece(piece, topLeft, origin);
				board[topLeft.x][topLeft.y] = temp;
				temp = null;
			} else {
				movePiece(piece, topLeft, origin);
				board[topLeft.x][topLeft.y] = temp;
				temp = null;
			}
		}

		if (piece.position.y < 7
				&& (board[top.x][top.y] == null || !board[top.x][top.y].color
						.equalsIgnoreCase(piece.color))) {
			if (board[top.x][top.y] != null)
				temp = board[top.x][top.y];
			movePiece(piece, origin, top);

			if (piece.color.equalsIgnoreCase("white"))
				newAttacked = getAttacked("black");
			else
				newAttacked = getAttacked("white");

			if (newAttacked.contains(top)
					|| ((piece.color.equalsIgnoreCase("white")
							&& (top.x < 7 && top.y < 7
									&& board[top.x + 1][top.y + 1] != null && (board[top.x + 1][top.y + 1].pieceName
									.equalsIgnoreCase("bp"))) || (top.x > 0
							&& top.y < 7 && board[top.x - 1][top.y + 1] != null && board[top.x - 1][top.y + 1].pieceName
							.equalsIgnoreCase("bp"))))
					|| ((piece.color.equalsIgnoreCase("black")
							&& (top.x < 7 && top.y > 0
									&& board[top.x + 1][top.y - 1] != null && (board[top.x + 1][top.y - 1].pieceName
									.equalsIgnoreCase("wp"))) || (top.x > 0
							&& top.y > 0 && board[top.x - 1][top.y - 1] != null && board[top.x - 1][top.y - 1].pieceName
							.equalsIgnoreCase("wp"))))) {
				validMoves.remove(top);
				movePiece(piece, top, origin);
				board[top.x][top.y] = temp;
				temp = null;
			} else {
				movePiece(piece, top, origin);
				board[top.x][top.y] = temp;
				temp = null;
			}
		}

		if (piece.position.y < 7
				&& piece.position.x < 7
				&& (board[topRight.x][topRight.y] == null || !board[topRight.x][topRight.y].color
						.equalsIgnoreCase(piece.color))) {
			if (board[topRight.x][topRight.y] != null)
				temp = board[topRight.x][topRight.y];
			movePiece(piece, origin, topRight);

			if (piece.color.equalsIgnoreCase("white"))
				newAttacked = getAttacked("black");
			else
				newAttacked = getAttacked("white");

			if (newAttacked.contains(topRight)
					|| ((piece.color.equalsIgnoreCase("white")
							&& (topRight.x < 7
									&& topRight.y < 7
									&& board[topRight.x + 1][topRight.y + 1] != null && (board[topRight.x + 1][topRight.y + 1].pieceName
									.equalsIgnoreCase("bp"))) || (topRight.x > 0
							&& topRight.y < 7
							&& board[topRight.x - 1][topRight.y + 1] != null && board[topRight.x - 1][topRight.y + 1].pieceName
							.equalsIgnoreCase("bp"))))
					|| ((piece.color.equalsIgnoreCase("black")
							&& (topRight.x < 7
									&& topRight.y > 0
									&& board[topRight.x + 1][topRight.y - 1] != null && (board[topRight.x + 1][topRight.y - 1].pieceName
									.equalsIgnoreCase("wp"))) || (topRight.x > 0
							&& topRight.y > 0
							&& board[topRight.x - 1][topRight.y - 1] != null && board[topRight.x - 1][topRight.y - 1].pieceName
							.equalsIgnoreCase("wp"))))) {
				validMoves.remove(topRight);
				movePiece(piece, topRight, origin);
				board[topRight.x][topRight.y] = temp;
				temp = null;
			} else {
				movePiece(piece, topRight, origin);
				board[topRight.x][topRight.y] = temp;
				temp = null;
			}
		}

		if (piece.position.x < 7
				&& (board[right.x][right.y] == null || !board[right.x][right.y].color
						.equalsIgnoreCase(piece.color))) {
			if (board[right.x][right.y] != null)
				temp = board[right.x][right.y];
			movePiece(piece, origin, right);

			if (piece.color.equalsIgnoreCase("white"))
				newAttacked = getAttacked("black");
			else
				newAttacked = getAttacked("white");

			if (newAttacked.contains(right)
					|| ((piece.color.equalsIgnoreCase("white")
							&& (right.x < 7 && right.y < 7
									&& board[right.x + 1][right.y + 1] != null && (board[right.x + 1][right.y + 1].pieceName
									.equalsIgnoreCase("bp"))) || (right.x > 0
							&& right.y < 7
							&& board[right.x - 1][right.y + 1] != null && board[right.x - 1][right.y + 1].pieceName
							.equalsIgnoreCase("bp"))))
					|| ((piece.color.equalsIgnoreCase("black")
							&& (right.x < 7 && right.y > 0
									&& board[right.x + 1][right.y - 1] != null && (board[right.x + 1][right.y - 1].pieceName
									.equalsIgnoreCase("wp"))) || (right.x > 0
							&& right.y > 0
							&& board[right.x - 1][right.y - 1] != null && board[right.x - 1][right.y - 1].pieceName
							.equalsIgnoreCase("wp"))))) {
				validMoves.remove(right);
				movePiece(piece, right, origin);
				board[right.x][right.y] = temp;
				temp = null;
			} else {
				movePiece(piece, right, origin);
				board[right.x][right.y] = temp;
				temp = null;
			}
		}

		if (piece.position.x < 7
				&& piece.position.y > 0
				&& (board[bottomRight.x][bottomRight.y] == null || !board[bottomRight.x][bottomRight.y].color
						.equalsIgnoreCase(piece.color))) {
			if (board[bottomRight.x][bottomRight.y] != null)
				temp = board[bottomRight.x][bottomRight.y];
			movePiece(piece, origin, bottomRight);

			if (piece.color.equalsIgnoreCase("white"))
				newAttacked = getAttacked("black");
			else
				newAttacked = getAttacked("white");

			if (newAttacked.contains(bottomRight)
					|| ((piece.color.equalsIgnoreCase("white")
							&& (bottomRight.x < 7
									&& bottomRight.y < 7
									&& board[bottomRight.x + 1][bottomRight.y + 1] != null && (board[bottomRight.x + 1][bottomRight.y + 1].pieceName
									.equalsIgnoreCase("bp"))) || (bottomRight.x > 0
							&& bottomRight.y < 7
							&& board[bottomRight.x - 1][bottomRight.y + 1] != null && board[bottomRight.x - 1][bottomRight.y + 1].pieceName
							.equalsIgnoreCase("bp"))))
					|| ((piece.color.equalsIgnoreCase("black")
							&& (bottomRight.x < 7
									&& bottomRight.y > 0
									&& board[bottomRight.x + 1][bottomRight.y - 1] != null && (board[bottomRight.x + 1][bottomRight.y - 1].pieceName
									.equalsIgnoreCase("wp"))) || (bottomRight.x > 0
							&& bottomRight.y > 0
							&& board[bottomRight.x - 1][bottomRight.y - 1] != null && board[bottomRight.x - 1][bottomRight.y - 1].pieceName
							.equalsIgnoreCase("wp"))))) {
				validMoves.remove(bottomRight);
				movePiece(piece, bottomRight, origin);
				board[bottomRight.x][bottomRight.y] = temp;
				temp = null;
			} else {
				movePiece(piece, bottomRight, origin);
				board[bottomRight.x][bottomRight.y] = temp;
				temp = null;
			}
		}

		if (piece.position.y > 0
				&& (board[bottom.x][bottom.y] == null || !board[bottom.x][bottom.y].color
						.equalsIgnoreCase(piece.color))) {
			if (board[bottom.x][bottom.y] != null)
				temp = board[bottom.x][bottom.y];
			movePiece(piece, origin, bottom);

			if (piece.color.equalsIgnoreCase("white"))
				newAttacked = getAttacked("black");
			else
				newAttacked = getAttacked("white");

			if (newAttacked.contains(bottom)
					|| ((piece.color.equalsIgnoreCase("white")
							&& (bottom.x < 7
									&& bottom.y < 7
									&& board[bottom.x + 1][bottom.y + 1] != null && (board[bottom.x + 1][bottom.y + 1].pieceName
									.equalsIgnoreCase("bp"))) || (bottom.x > 0
							&& bottom.y < 7
							&& board[bottom.x - 1][bottom.y + 1] != null && board[bottom.x - 1][bottom.y + 1].pieceName
							.equalsIgnoreCase("bp"))))
					|| ((piece.color.equalsIgnoreCase("black")
							&& (bottom.x < 7
									&& bottom.y > 0
									&& board[bottom.x + 1][bottom.y - 1] != null && (board[bottom.x + 1][bottom.y - 1].pieceName
									.equalsIgnoreCase("wp"))) || (bottom.x > 0
							&& bottom.y > 0
							&& board[bottom.x - 1][bottom.y - 1] != null && board[bottom.x - 1][bottom.y - 1].pieceName
							.equalsIgnoreCase("wp"))))) {
				validMoves.remove(bottom);
				movePiece(piece, bottom, origin);
				board[bottom.x][bottom.y] = temp;
				temp = null;
			} else {
				movePiece(piece, bottom, origin);
				board[bottom.x][bottom.y] = temp;
				temp = null;
			}
		}

		if (piece.position.y > 0
				&& piece.position.x > 0
				&& (board[bottomLeft.x][bottomLeft.y] == null || !board[bottomLeft.x][bottomLeft.y].color
						.equalsIgnoreCase(piece.color))) {
			if (board[bottomLeft.x][bottomLeft.y] != null)
				temp = board[bottomLeft.x][bottomLeft.y];
			movePiece(piece, origin, bottomLeft);

			if (piece.color.equalsIgnoreCase("white"))
				newAttacked = getAttacked("black");
			else
				newAttacked = getAttacked("white");

			if (newAttacked.contains(bottomLeft)
					|| ((piece.color.equalsIgnoreCase("white")
							&& (bottomLeft.x < 7
									&& bottomLeft.y < 7
									&& board[bottomLeft.x + 1][bottomLeft.y + 1] != null && (board[bottomLeft.x + 1][bottomLeft.y + 1].pieceName
									.equalsIgnoreCase("bp"))) || (bottomLeft.x > 0
							&& bottomLeft.y < 7
							&& board[bottomLeft.x - 1][bottomLeft.y + 1] != null && board[bottomLeft.x - 1][bottomLeft.y + 1].pieceName
							.equalsIgnoreCase("bp"))))
					|| ((piece.color.equalsIgnoreCase("black")
							&& (bottomLeft.x < 7
									&& bottomLeft.y > 0
									&& board[bottomLeft.x + 1][bottomLeft.y - 1] != null && (board[bottomLeft.x + 1][bottomLeft.y - 1].pieceName
									.equalsIgnoreCase("wp"))) || (bottomLeft.x > 0
							&& bottomLeft.y > 0
							&& board[bottomLeft.x - 1][bottomLeft.y - 1] != null && board[bottomLeft.x - 1][bottomLeft.y - 1].pieceName
							.equalsIgnoreCase("wp"))))) {
				validMoves.remove(bottomLeft);
				movePiece(piece, bottomLeft, origin);
				board[bottomLeft.x][bottomLeft.y] = temp;
				temp = null;
			} else {
				movePiece(piece, bottomLeft, origin);
				board[bottomLeft.x][bottomLeft.y] = temp;
				temp = null;
			}
		}

		return validMoves;
	}

	public static ArrayList<Position> kingCheckMoves(ChessPiece piece,
			Position origin) {

		ArrayList<Position> validMoves = new ArrayList<Position>();
		Position xyCoor;

		if (origin.y < 7
				&& (board[origin.x][origin.y + 1] == null || !board[origin.x][origin.y + 1].color
						.equalsIgnoreCase(piece.color))) {
			xyCoor = new Position(origin.x, origin.y + 1);
			validMoves.add(xyCoor);

		}

		if ((origin.x < 7 && origin.y < 7)
				&& (board[origin.x + 1][origin.y + 1] == null || !board[origin.x + 1][origin.y + 1].color
						.equalsIgnoreCase(piece.color))) {
			xyCoor = new Position(origin.x + 1, origin.y + 1);
			validMoves.add(xyCoor);

		}
		if (origin.x < 7
				&& (board[origin.x + 1][origin.y] == null || !board[origin.x + 1][origin.y].color
						.equalsIgnoreCase(piece.color))) {
			xyCoor = new Position(origin.x + 1, origin.y);
			validMoves.add(xyCoor);

		}

		if ((origin.x < 7 && origin.y > 0)
				&& (board[origin.x + 1][origin.y - 1] == null || !board[origin.x + 1][origin.y - 1].color
						.equalsIgnoreCase(piece.color))) {
			xyCoor = new Position(origin.x + 1, origin.y - 1);
			validMoves.add(xyCoor);

		}
		if (origin.y > 0
				&& (board[origin.x][origin.y - 1] == null || !board[origin.x][origin.y - 1].color
						.equalsIgnoreCase(piece.color))) {
			xyCoor = new Position(origin.x, origin.y - 1);
			validMoves.add(xyCoor);

		}

		if ((origin.x > 0 && origin.y > 0)
				&& (board[origin.x - 1][origin.y - 1] == null || !board[origin.x - 1][origin.y - 1].color
						.equalsIgnoreCase(piece.color))) {
			xyCoor = new Position(origin.x - 1, origin.y - 1);
			validMoves.add(xyCoor);

		}
		if (origin.x > 0
				&& (board[origin.x - 1][origin.y] == null || !board[origin.x - 1][origin.y].color
						.equalsIgnoreCase(piece.color))) {
			xyCoor = new Position(origin.x - 1, origin.y);
			validMoves.add(xyCoor);

		}

		if ((origin.x > 0 && origin.y < 7)
				&& (board[origin.x - 1][origin.y + 1] == null || !board[origin.x - 1][origin.y + 1].color
						.equalsIgnoreCase(piece.color))) {
			xyCoor = new Position(origin.x - 1, origin.y + 1);
			validMoves.add(xyCoor);

		}

		if (piece.color.equalsIgnoreCase("white")) {
			if (wKingMoved == false && wRookLeftMoved == false) {
				if (origin.x == 4 && origin.y == 0 && board[origin.x - 1][origin.y] == null
						&& board[origin.x - 2][origin.y] == null
						&& board[origin.x - 3][origin.y] == null) {
					xyCoor = new Position(origin.x - 2, origin.y);
					validMoves.add(xyCoor);
				}

				if (wKingMoved == false && wRookRightMoved == false) {
					if (origin.x == 4 && origin.y == 0 && board[origin.x + 1][origin.y] == null
							&& board[origin.x + 2][origin.y] == null) {
						xyCoor = new Position(origin.x + 2, origin.y);
						validMoves.add(xyCoor);
					}
				}
			}
		} else {
			if (bKingMoved == false && bRookLeftMoved == false) {
				if (origin.x == 4 && origin.y == 7 && board[origin.x - 1][origin.y] == null
						&& board[origin.x - 2][origin.y] == null
						&& board[origin.x - 3][origin.y] == null) {
					xyCoor = new Position(origin.x - 2, origin.y);
					validMoves.add(xyCoor);
				}

				if (bKingMoved == false && bRookRightMoved == false) {
					if (origin.x == 4 && origin.y == 7 && board[origin.x + 1][origin.y] == null
							&& board[origin.x + 2][origin.y] == null) {
						xyCoor = new Position(origin.x + 2, origin.y);
						validMoves.add(xyCoor);
					}
				}
			}
		}
		return validMoves;
	}
}
