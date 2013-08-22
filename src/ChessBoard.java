import java.util.Stack;

public class ChessBoard {

    private static final int BOARD_SIZE = 8;
    private static final int BLANK = 0;
    private static final int QUEEN = 1;
    private static final int MARK = 2;
    private int[][] map;
    private Stack<Point> queens;

    public ChessBoard() {
        this.map = new int[BOARD_SIZE][BOARD_SIZE];
        this.queens = new Stack<Point>();
    }

    public void putFirstQueen() {
        putQueen(new Point(0, 0));
    }

    public void putQueen() {
        Point p = getBlank();
        putQueen(p);
    }

    private void putQueen(Point p) {
        queens.push(p);
        markQueens();
    }

    private Point removeQueen() {
        Point p = queens.pop();
        markQueens();
        return p;
    }

    private void markQueens() {
        clearMark();
        for (Point p : queens) {
            mark(p);
        }
    }

    private void mark(Point p) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            mark(i, p.y);
            mark(p.x, i);
            mark(p.x + i, p.y + i);
            mark(p.x - i, p.y + i);
            mark(p.x + i, p.y - i);
            mark(p.x - i, p.y - i);
        }
        map[p.y][p.x] = QUEEN;
    }

    private void mark(int x, int y) {
        if (x < 0 || x >= BOARD_SIZE)
            return;
        if (y < 0 || y >= BOARD_SIZE)
            return;
        if (map[y][x] == BLANK) {
            map[y][x] = MARK;
        }
    }

    private void clearMark() {
        for (int y = 0; y < BOARD_SIZE; y++) {
            for (int x = 0; x < BOARD_SIZE; x++) {
                map[y][x] = BLANK;
            }
        }
    }

    private Point getBlank() {
        Point queenOnTop = queens.peek();
        if (queenOnTop == null)
            return null;
        return getNextBlank(queenOnTop);
    }
    
    public Point getNextBlank(Point p) {
        for (int x = p.x+1; x < BOARD_SIZE; x++) {
            if (map[p.y][x] == BLANK) {
                return new Point(x, p.y);
            }
        }

        for (int y = p.y+1; y < BOARD_SIZE; y++) {
            for (int x = 0; x < BOARD_SIZE; x++) {
                if (map[y][x] == BLANK) {
                    return new Point(x, y);
                }
            }
        }
        return null;
    }

    public boolean hasNoBlank() {
        return getBlank() == null;
    }

    public void moveQueen() {
//        System.out.println("move queen");
        Point p = removeQueen();
        
        Point blank = getNextBlank(p);
        while (!queens.isEmpty() && blank == null) {
            p = removeQueen();
            blank = getNextBlank(p);
        }
        
        if (blank != null) {
            if (queens.isEmpty() && blank.y != 0)
                return;
            putQueen(blank);
        }
    }

    public boolean allQueensPlaced() {
        return queens.size() == BOARD_SIZE;
    }

    public boolean hasPlaceToMove() {
        return queens.isEmpty() == false;
    }

    public void print() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    @Override
    public String toString() {
        return "ChessBoard [queens=" + queens + "]";
    }
    
}