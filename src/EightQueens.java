import java.util.ArrayList;

public class EightQueens {

    public static void main(String[] args) {
        ArrayList<String> boards = EightQueens.solve();
        for ( String b : boards) {
            System.out.println(b);
        }
    }

    private static ArrayList<String> solve() {
        ArrayList<String> queensOnboard = new ArrayList<String>();
        ChessBoard board = new ChessBoard();

        board.putFirstQueen();
        while (board.hasPlaceToMove()) {
            if (board.hasNoBlank()) {
                board.moveQueen();
            } else {
                board.putQueen();
                if (board.allQueensPlaced()) {
                    queensOnboard.add(board.toString());
                    board.moveQueen();
                }
            }
        }

        return queensOnboard;
    }
}
