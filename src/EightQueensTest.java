import static org.junit.Assert.*;

import org.junit.Test;

public class EightQueensTest {

    @Test
    public void nextBlankOf01ShouldBe02() throws Exception {
        ChessBoard board = new ChessBoard();
        board.print();
        assertEquals(new Point(2, 0), board.getNextBlank(new Point(1, 0)));
    }
}
