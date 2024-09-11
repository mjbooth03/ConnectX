package cpsc2150.test;

import org.junit.Test;
import static org.junit.Assert.*;
import cpsc2150.extendedConnectX.models.GameBoard;
import cpsc2150.extendedConnectX.models.IGameBoard;
import cpsc2150.extendedConnectX.models.BoardPosition;
public class TestGameBoard {

    private IGameBoard gb(int r, int c, int t){
        return new GameBoard(r,c,t);
    }

    //GameBoard Constructor Tests

    private String boardToString(IGameBoard board){
        String sb = "";
        for (int i = 0; i < board.getNumColumns(); i++) {
            if (i <= 9) {
                sb += "| " + i;
            }
            else {
                sb += "|" + i;
            }
        }
        sb += "|\n";
        for (int i = board.getNumRows() - 1; i > -1; i--) {
            sb += "|";
            for (int j = 0; j < board.getNumColumns(); j++) {
                sb += " ";
                sb += " |";
            }
            sb += "\n";
        }
        return sb;
    }
    // Test initializes GameBoard to its minimum values
    @Test
    public void testGameBoard_min_values() {
        IGameBoard testBoard = gb(3,3,3);
        StringBuilder expected = new StringBuilder(boardToString(testBoard));
        StringBuilder actual = new StringBuilder(testBoard.toString());
        assertTrue(testBoard.getNumRows() == 3);
        assertTrue(testBoard.getNumColumns() == 3);
        assertTrue(testBoard.getNumToWin() == 3);
        assertEquals(0, actual.compareTo(expected));
    }

    // Test initializes GameBoard to its minimum values
    @Test
    public void testGameBoard_max_values() {
        IGameBoard testBoard = gb(100,100,25);
        StringBuilder expected = new StringBuilder(boardToString(testBoard));
        StringBuilder actual = new StringBuilder(testBoard.toString());
        assertTrue(testBoard.getNumRows() == 100);
        assertTrue(testBoard.getNumColumns() == 100);
        assertTrue(testBoard.getNumToWin() == 25);
        assertEquals(0, actual.compareTo(expected));
    }

    // Test initializes GameBoard with varying values
    @Test
    public void testGameBoard_varying_values() {
        IGameBoard testBoard = gb(7,8,3);
        StringBuilder expected = new StringBuilder(boardToString(testBoard));
        StringBuilder actual = new StringBuilder(testBoard.toString());
        assertTrue(testBoard.getNumRows() == 7);
        assertTrue(testBoard.getNumColumns() == 8);
        assertTrue(testBoard.getNumToWin() == 3);
        assertEquals(0, actual.compareTo(expected));
    }

    //checkIfFree Tests
    //Test checks if the last available row in the column is available
    @Test
    public void testCheckIfFree_true_last_space_2() {
        IGameBoard testBoard = gb(3,5,3);
        StringBuilder expected = new StringBuilder(boardToString(testBoard));
        expected.replace(52, 53, "X");
        expected.replace(35, 36, "O");
        expected.replace(18, 19, "X");
        expected.replace(55, 56, "O");
        expected.replace(38, 39, "X");
        expected.replace(21, 22, "O");
        expected.replace(58, 59, "X");
        expected.replace(41, 42, "O");
        expected.replace(61, 62, "X");
        expected.replace(44, 45, "O");
        expected.replace(27, 28, "X");
        expected.replace(64, 65, "O");
        expected.replace(47, 48, "X");
        expected.replace(30, 31, "O");
        testBoard.dropToken('X', 0);
        testBoard.dropToken('O', 0);
        testBoard.dropToken('X', 0);
        testBoard.dropToken('O', 1);
        testBoard.dropToken('X', 1);
        testBoard.dropToken('O', 1);
        testBoard.dropToken('X', 2);
        testBoard.dropToken('O', 2);
        testBoard.dropToken('X', 3);
        testBoard.dropToken('O', 3);
        testBoard.dropToken('X', 3);
        testBoard.dropToken('O', 4);
        testBoard.dropToken('X', 4);
        testBoard.dropToken('O', 4);
        StringBuilder actual = new StringBuilder(testBoard.toString());
        assertTrue(testBoard.checkIfFree(2));
        assertEquals(0, actual.compareTo(expected));

    }

    // Test checks if the column that is full is free
    @Test
    public void testCheckIfFree_false_full_col_1() {
        IGameBoard testBoard = gb(3,5,3);
        StringBuilder expected = new StringBuilder(boardToString(testBoard));
        expected.replace(52, 53, "X");
        expected.replace(35, 36, "O");
        expected.replace(55, 56, "X");
        expected.replace(38, 39, "O");
        expected.replace(21, 22, "X");
        expected.replace(58, 59, "O");
        expected.replace(41, 42, "X");
        expected.replace(61, 62, "O");
        expected.replace(44, 45, "X");
        expected.replace(64, 65, "O");
        expected.replace(47, 48, "X");
        testBoard.dropToken('X', 0);
        testBoard.dropToken('O', 0);
        testBoard.dropToken('X', 1);
        testBoard.dropToken('O', 1);
        testBoard.dropToken('X', 1);
        testBoard.dropToken('O', 2);
        testBoard.dropToken('X', 2);
        testBoard.dropToken('O', 3);
        testBoard.dropToken('X', 3);
        testBoard.dropToken('O', 4);
        testBoard.dropToken('X', 4);
        StringBuilder actual = new StringBuilder(testBoard.toString());
        assertTrue(!testBoard.checkIfFree(1));
        assertEquals(0, actual.compareTo(expected));
    }

    // Test checks if anywhere on the empty board is free
    @Test
    public void testCheckifFree_true_empty_board_0() {
        IGameBoard testBoard = gb(3,5,3);
        StringBuilder expected = new StringBuilder(boardToString(testBoard));
        StringBuilder actual = new StringBuilder(testBoard.toString());
        assertEquals(true, testBoard.checkIfFree(0));
        assertEquals(0, actual.compareTo(expected));
    }

    //checkHorizWin Tests
    // Test checks for a false result when a potential 'X' win
    // is blocked by an 'O' marker
    @Test
    public void testCheckHorizWin_broken_by_o_end() {
        IGameBoard testBoard = gb(3, 5, 4);
        StringBuilder expected = new StringBuilder(boardToString(testBoard));
        expected.replace(52, 53, "X");
        expected.replace(35, 36, "O");
        expected.replace(58, 59, "X");
        expected.replace(18, 19, "O");
        expected.replace(61, 62, "X");
        expected.replace(55, 56, "O");
        testBoard.dropToken('X', 0);
        testBoard.dropToken('O', 0);
        testBoard.dropToken('X', 2);
        testBoard.dropToken('O', 0);
        testBoard.dropToken('X', 3);
        testBoard.dropToken('O', 1);
        StringBuilder actual = new StringBuilder(testBoard.toString());
        BoardPosition pos = new BoardPosition(0, 1 );
        assertEquals(false, testBoard.checkHorizWin(pos, 'X'));
        assertEquals(0, actual.compareTo(expected));
    }

    // Test checks for a true result when an 'X' marker is placed on
    // the left to make a string of 4 consecutive 'X's
    @Test
    public void testCheckHorizWin_win_last_marker_left_end() {
        IGameBoard testBoard = gb(3, 5, 4);
        StringBuilder expected = new StringBuilder(boardToString(testBoard));
        expected.replace(64, 65, "X");
        expected.replace(47, 48, "O");
        expected.replace(61, 62, "X");
        expected.replace(44, 45, "O");
        expected.replace(58, 59, "X");
        expected.replace(30, 31, "O");
        expected.replace(55, 56, "X");
        testBoard.dropToken('X', 4);
        testBoard.dropToken('O', 4);
        testBoard.dropToken('X', 3);
        testBoard.dropToken('O', 3);
        testBoard.dropToken('X', 2);
        testBoard.dropToken('O', 4);
        testBoard.dropToken('X', 1);
        StringBuilder actual = new StringBuilder(testBoard.toString());
        BoardPosition pos = new BoardPosition(0, 1 );
        assertEquals(true, testBoard.checkHorizWin(pos, 'X'));
        assertEquals(0, actual.compareTo(expected));

    }

    // Test checks for a true result when an 'X' marker is placed in
    // the middle to make a string of 4 consecutive 'X's
    @Test
    public void testCheckHorizWin_win_last_marker_middle() {
        IGameBoard testBoard = gb(3, 5, 4);
        StringBuilder expected = new StringBuilder(boardToString(testBoard));
        expected.replace(52, 53, "X");
        expected.replace(35, 36, "O");
        expected.replace(61, 62, "X");
        expected.replace(44, 45, "O");
        expected.replace(58, 59, "X");
        expected.replace(41, 42, "O");
        expected.replace(55, 56, "X");
        testBoard.dropToken('X', 0);
        testBoard.dropToken('O', 0);
        testBoard.dropToken('X', 3);
        testBoard.dropToken('O', 3);
        testBoard.dropToken('X', 2);
        testBoard.dropToken('O', 2);
        testBoard.dropToken('X', 1);
        StringBuilder actual = new StringBuilder(testBoard.toString());
        BoardPosition pos = new BoardPosition(0, 1 );
        assertEquals(true, testBoard.checkHorizWin(pos, 'X'));
        assertEquals(0, actual.compareTo(expected));

    }

    // Test checks for a true result when an 'X' marker is placed on
    // the left to make a string of 4 consecutive 'X's on the top row
    @Test
    public void testCheckHorizWin_win_last_marker_left_end_top_row() {
        IGameBoard testBoard = gb(3, 5, 4);
        StringBuilder expected = new StringBuilder(boardToString(testBoard));
        expected.replace(52, 53, "X");
        expected.replace(55, 56, "O");
        expected.replace(61, 62, "X");
        expected.replace(58, 59, "O");
        expected.replace(38, 39, "X");
        expected.replace(64, 65, "O");
        expected.replace(21, 22, "X");
        expected.replace(35, 36, "O");
        expected.replace(18, 19, "X");
        expected.replace(41, 42, "O");
        expected.replace(24, 25, "X");
        expected.replace(44, 45, "O");
        expected.replace(27, 28, "X");
        testBoard.dropToken('X', 0);
        testBoard.dropToken('O', 1);
        testBoard.dropToken('X', 3);
        testBoard.dropToken('O', 2);
        testBoard.dropToken('X', 1);
        testBoard.dropToken('O', 4);
        testBoard.dropToken('X', 1);
        testBoard.dropToken('O', 0);
        testBoard.dropToken('X', 0);
        testBoard.dropToken('O', 2);
        testBoard.dropToken('X', 2);
        testBoard.dropToken('O', 3);
        testBoard.dropToken('X', 3);
        StringBuilder actual = new StringBuilder(testBoard.toString());
        BoardPosition pos = new BoardPosition(2, 3 );
        assertEquals(true, testBoard.checkHorizWin(pos, 'X'));
        assertEquals(0, actual.compareTo(expected));
    }

    //checkVertWin Tests
    // Test checks for a false result as there is a
    // diagonal win not a vertical win
    @Test
    public void testCheckVertWin_false_diag_win_2_1_X() {
        IGameBoard testBoard = gb(3, 5, 3);
        StringBuilder expected = new StringBuilder(boardToString(testBoard));
        expected.replace(52, 53, "X");
        expected.replace(55, 56, "O");
        expected.replace(61, 62, "X");
        expected.replace(58, 59, "O");
        expected.replace(41, 42, "X");
        expected.replace(38, 39, "O");
        expected.replace(21, 22, "X");
        testBoard.dropToken('X', 0);
        testBoard.dropToken('O', 1);
        testBoard.dropToken('X', 3);
        testBoard.dropToken('O', 2);
        testBoard.dropToken('X', 2);
        testBoard.dropToken('O', 1);
        testBoard.dropToken('X', 1);
        StringBuilder actual = new StringBuilder(testBoard.toString());
        BoardPosition pos = new BoardPosition(2, 1 );
        assertEquals(false, testBoard.checkVertWin(pos, 'X'));
        assertEquals(0, actual.compareTo(expected));

    }

    // Test checks for a false result as the 'O' marker
    // blocks the 'X's from a vertical win in column 2
    @Test
    public void testCheckVertWin_true_marker_X() {
        IGameBoard testBoard = gb(3, 5, 3);
        StringBuilder expected = new StringBuilder(boardToString(testBoard));
        expected.replace(64, 65, "X");
        expected.replace(61, 62, "O");
        expected.replace(58, 59, "X");
        expected.replace(55, 56, "O");
        expected.replace(38, 39, "X");
        expected.replace(41, 42, "O");
        expected.replace(24, 25, "X");
        testBoard.dropToken('X', 4);
        testBoard.dropToken('O', 3);
        testBoard.dropToken('X', 2);
        testBoard.dropToken('O', 1);
        testBoard.dropToken('X', 1);
        testBoard.dropToken('O', 2);
        testBoard.dropToken('X', 2);
        StringBuilder actual = new StringBuilder(testBoard.toString());
        BoardPosition pos = new BoardPosition(2, 2 );
        assertEquals(false, testBoard.checkVertWin(pos, 'X'));
        assertEquals(0, actual.compareTo(expected));
    }

    // Test checks for a true result as there is
    // a win for the 'X' marker in column 1
    @Test
    public void testCheckVertWin_false_O_win_2_1_X() {
        IGameBoard testBoard = gb(3, 5, 3);
        StringBuilder expected = new StringBuilder(boardToString(testBoard));
        expected.replace(55, 56, "X");
        expected.replace(58, 59, "O");
        expected.replace(38, 39, "X");
        expected.replace(41, 42, "O");
        expected.replace(21, 22, "X");
        testBoard.dropToken('X', 1);
        testBoard.dropToken('O', 2);
        testBoard.dropToken('X', 1);
        testBoard.dropToken('O', 2);
        testBoard.dropToken('X', 1);
        StringBuilder actual = new StringBuilder(testBoard.toString());
        BoardPosition pos = new BoardPosition(2, 1 );
        assertEquals(true, testBoard.checkVertWin(pos, 'X'));
        assertEquals(0, actual.compareTo(expected));
    }

    // Test checks for a true result as there is a win for
    // the 'X' marker on top of 'O' markers in column 0
    @Test
    public void testCheckVertWin_true_marker_X_first_column() {
        IGameBoard testBoard = gb(6, 5, 3);
        StringBuilder expected = new StringBuilder(boardToString(testBoard));
        expected.replace(103, 104, "X");
        expected.replace(106, 107, "O");
        expected.replace(109, 110, "O");
        expected.replace(89, 90, "X");
        expected.replace(86, 87, "O");
        expected.replace(69, 70, "X");
        expected.replace(72, 73, "O");
        expected.replace(52, 53, "X");
        expected.replace(35, 36, "X");
        testBoard.dropToken('X', 0);
        testBoard.dropToken('O', 1);
        testBoard.dropToken('X', 1);
        testBoard.dropToken('O', 0);
        testBoard.dropToken('X', 0);
        testBoard.dropToken('O', 1);
        testBoard.dropToken('X', 0);
        testBoard.dropToken('O', 2);
        testBoard.dropToken('X', 0);
        StringBuilder actual = new StringBuilder(testBoard.toString());
        BoardPosition pos = new BoardPosition(5, 0 );
        assertEquals(true, testBoard.checkVertWin(pos, 'X'));
        assertEquals(0, actual.compareTo(expected));
    }

    //checkDiagWin Tests
    // Test checks for a true result when an 'X' marker was placed
    // in column 0 in the top left of a consecutive 'X' string
    @Test
    public void testCheckDiagWin_true_X_win_last_marker_top_left() {
        IGameBoard testBoard = gb(3, 5, 3);
        StringBuilder expected = new StringBuilder(boardToString(testBoard));
        expected.replace(52, 53, "X");
        expected.replace(55, 56, "O");
        expected.replace(58, 59, "X");
        expected.replace(35, 36, "O");
        expected.replace(38, 39, "X");
        expected.replace(41, 42, "O");
        expected.replace(18, 19, "X");
        testBoard.dropToken('X', 0);
        testBoard.dropToken('O', 1);
        testBoard.dropToken('X', 2);
        testBoard.dropToken('O', 2);
        testBoard.dropToken('X', 1);
        testBoard.dropToken('O', 0);
        testBoard.dropToken('X', 0);
        StringBuilder actual = new StringBuilder(testBoard.toString());
        BoardPosition pos = new BoardPosition(2, 0 );
        assertEquals(true, testBoard.checkDiagWin(pos, 'X'));
        assertEquals(0, actual.compareTo(expected));
    }

    // Test checks for a true result when an 'X' marker was placed
    // in column 2 in the top right of a consecutive 'X' string
    @Test
    public void testCheckDiagWin_true_X_win_last_marker_top_right() {
        IGameBoard testBoard = gb(3, 5, 3);
        StringBuilder expected = new StringBuilder(boardToString(testBoard));
        expected.replace(52, 53, "X");
        expected.replace(55, 56, "O");
        expected.replace(35, 36, "X");
        expected.replace(58, 59, "O");
        expected.replace(38, 39, "X");
        expected.replace(41, 42, "O");
        expected.replace(24, 25, "X");
        testBoard.dropToken('X', 0);
        testBoard.dropToken('O', 1);
        testBoard.dropToken('X', 1);
        testBoard.dropToken('O', 2);
        testBoard.dropToken('X', 0);
        testBoard.dropToken('O', 2);
        testBoard.dropToken('X', 2);
        StringBuilder actual = new StringBuilder(testBoard.toString());
        BoardPosition pos = new BoardPosition(2, 2 );
        assertEquals(true, testBoard.checkDiagWin(pos, 'X'));
        assertEquals(0, actual.compareTo(expected));

    }

    // Test checks for a true result when an 'O' marker was placed
    // in column 0 in the bottom left of a consecutive 'O' string
    @Test
    public void testCheckDiagWin_true_O_win_last_marker_bottom_left() {
        IGameBoard testBoard = gb(3, 5, 3);
        StringBuilder expected = new StringBuilder(boardToString(testBoard));
        expected.replace(55, 56, "X");
        expected.replace(38, 39, "O");
        expected.replace(58, 59, "X");
        expected.replace(41, 42, "O");
        expected.replace(64, 65, "X");
        expected.replace(24, 25, "O");
        expected.replace(47, 48, "X");
        expected.replace(52, 53, "O");
        testBoard.dropToken('X', 1);
        testBoard.dropToken('O', 1);
        testBoard.dropToken('X', 2);
        testBoard.dropToken('O', 2);
        testBoard.dropToken('X', 4);
        testBoard.dropToken('O', 2);
        testBoard.dropToken('X', 4);
        testBoard.dropToken('O', 0);
        StringBuilder actual = new StringBuilder(testBoard.toString());
        BoardPosition pos = new BoardPosition(0, 0 );
        assertEquals(true, testBoard.checkDiagWin(pos, 'O'));
        assertEquals(0, actual.compareTo(expected));
    }

    // Test checks for a true result when an 'O' marker was placed
    // in column 2 in the bottom right of a consecutive 'O' string
    @Test
    public void testCheckDiagWin_true_O_win_last_marker_bottom_right() {
        IGameBoard testBoard = gb(3, 5, 3);
        StringBuilder expected = new StringBuilder(boardToString(testBoard));
        expected.replace(52, 53, "X");
        expected.replace(35, 36, "O");
        expected.replace(55, 56, "X");
        expected.replace(38, 39, "O");
        expected.replace(61, 62, "X");
        expected.replace(18, 19, "O");
        expected.replace(44, 45, "X");
        expected.replace(58, 59, "O");
        testBoard.dropToken('X', 1);
        testBoard.dropToken('O', 1);
        testBoard.dropToken('X', 0);
        testBoard.dropToken('O', 0);
        testBoard.dropToken('X', 3);
        testBoard.dropToken('O', 0);
        testBoard.dropToken('X', 3);
        testBoard.dropToken('O', 2);
        StringBuilder actual = new StringBuilder(testBoard.toString());
        BoardPosition pos = new BoardPosition(0, 2 );
        assertEquals(true, testBoard.checkDiagWin(pos, 'O'));
        assertEquals(0, actual.compareTo(expected));
    }

    // Test checks for a true result when an 'X' marker was placed
    // in column 1 in the middle of a consecutive 'X' string and must check
    // from bottom left to top right
    @Test
    public void testCheckDiagWin_true_X_win_middle_bottomleft_topright() {
        IGameBoard testBoard = gb(3, 5, 3);
        StringBuilder expected = new StringBuilder(boardToString(testBoard));
        expected.replace(52, 53, "X");
        expected.replace(58, 59, "O");
        expected.replace(41, 42, "X");
        expected.replace(55, 56, "O");
        expected.replace(24, 25, "X");
        expected.replace(35, 36, "O");
        expected.replace(38, 39, "X");
        testBoard.dropToken('X', 0);
        testBoard.dropToken('O', 2);
        testBoard.dropToken('X', 2);
        testBoard.dropToken('O', 1);
        testBoard.dropToken('X', 2);
        testBoard.dropToken('O', 0);
        testBoard.dropToken('X', 1);
        StringBuilder actual = new StringBuilder(testBoard.toString());
        BoardPosition pos = new BoardPosition(1, 1 );
        assertEquals(true, testBoard.checkDiagWin(pos, 'X'));
        assertEquals(0, actual.compareTo(expected));
    }

    // Test checks for a true result when an 'X' marker was placed
    // in column 1 in the middle of a consecutive 'X' string and must check
    // from bottom right to top left
    @Test
    public void testCheckDiagWin_true_X_win_middle_bottomright_topleft() {
        IGameBoard testBoard = gb(3, 5, 3);
        StringBuilder expected = new StringBuilder(boardToString(testBoard));
        expected.replace(52, 53, "X");
        expected.replace(55, 56, "O");
        expected.replace(58, 59, "X");
        expected.replace(35, 36, "O");
        expected.replace(18, 19, "X");
        expected.replace(41, 42, "O");
        expected.replace(38, 39, "X");
        testBoard.dropToken('X', 0);
        testBoard.dropToken('O', 0);
        testBoard.dropToken('X', 2);
        testBoard.dropToken('O', 1);
        testBoard.dropToken('X', 0);
        testBoard.dropToken('O', 2);
        testBoard.dropToken('X', 1);
        StringBuilder actual = new StringBuilder(testBoard.toString());
        BoardPosition pos = new BoardPosition(1, 1 );
        assertEquals(true, testBoard.checkDiagWin(pos, 'X'));
        assertEquals(0, actual.compareTo(expected));
    }

    // Test checks for a false result when there is a horizWin
    // from column 1 and no diagWin
    @Test
    public void testCheckDiagWin_false_X_HorizWin_true() {
        IGameBoard testBoard = gb(3, 5, 3);
        StringBuilder expected = new StringBuilder(boardToString(testBoard));
        expected.replace(52, 53, "X");
        expected.replace(55, 56, "O");
        expected.replace(35, 36, "X");
        expected.replace(58, 59, "O");
        expected.replace(41, 42, "X");
        expected.replace(24, 25, "O");
        expected.replace(38, 39, "X");
        testBoard.dropToken('X', 0);
        testBoard.dropToken('O', 1);
        testBoard.dropToken('X', 0);
        testBoard.dropToken('O', 2);
        testBoard.dropToken('X', 2);
        testBoard.dropToken('O', 2);
        testBoard.dropToken('X', 1);
        StringBuilder actual = new StringBuilder(testBoard.toString());
        BoardPosition pos = new BoardPosition(1, 1 );
        assertEquals(false, testBoard.checkDiagWin(pos, 'X'));
        assertEquals(0, actual.compareTo(expected));
    }

    //checkTie Tests
    // Test checks for a false result as there is only one
    // marker placed on the board
    @Test
    public void testcheckTie_Placement_token_tie_check() {
        IGameBoard testBoard = gb(3, 5, 3);
        StringBuilder expected = new StringBuilder(boardToString(testBoard));
        expected.replace(52, 53, "X");
        testBoard.dropToken('X', 0);
        StringBuilder actual = new StringBuilder(testBoard.toString());
        assertEquals(false, testBoard.checkTie());
        assertEquals(0, actual.compareTo(expected));
    }

    // Test checks for a false result since the board is both
    // not full and there is a vertWin
    @Test
    public void testcheckTie_false_checkVert_true() {
        IGameBoard testBoard = gb(3, 5, 3);
        StringBuilder expected = new StringBuilder(boardToString(testBoard));
        expected.replace(52, 53, "X");
        expected.replace(55, 56, "O");
        expected.replace(35, 36, "X");
        expected.replace(58, 59, "O");
        expected.replace(18, 19, "X");
        testBoard.dropToken('X', 0);
        testBoard.dropToken('O', 1);
        testBoard.dropToken('X', 0);
        testBoard.dropToken('O', 2);
        testBoard.dropToken('X', 0);
        StringBuilder actual = new StringBuilder(testBoard.toString());
        assertEquals(false, testBoard.checkTie());
        assertEquals(0, actual.compareTo(expected));
    }

    // Test checks for a false result when the board is one marker
    // away from being filled and there is no win
    @Test
    public void testcheckTie_false_one_before_win() {
        IGameBoard testBoard = gb(3, 3, 3);
        StringBuilder expected = new StringBuilder(boardToString(testBoard));
        expected.replace(34, 35, "X");
        expected.replace(23, 24, "X");
        expected.replace(12, 13, "O");
        expected.replace(37, 38, "O");
        expected.replace(26, 27, "O");
        expected.replace(18, 19, "O");
        expected.replace(40, 41, "X");
        expected.replace(29, 30, "X");
        testBoard.dropToken('X', 0);
        testBoard.dropToken('O', 1);
        testBoard.dropToken('X', 0);
        testBoard.dropToken('O', 1);
        testBoard.dropToken('X', 2);
        testBoard.dropToken('O', 0);
        testBoard.dropToken('X', 2);
        testBoard.dropToken('O', 2);
        StringBuilder actual = new StringBuilder(testBoard.toString());
        assertEquals(false, testBoard.checkTie());
        assertEquals(0, actual.compareTo(expected));
    }
    // Test checks for a true result as the board is completely
    // filled with no win
    @Test
    public void testcheckTie_true() {
        IGameBoard testBoard = gb(3, 3, 3);
        StringBuilder expected = new StringBuilder(boardToString(testBoard));
        expected.replace(34, 35, "X");
        expected.replace(23, 24, "X");
        expected.replace(12, 13, "O");
        expected.replace(37, 38, "O");
        expected.replace(26, 27, "O");
        expected.replace(15, 16, "X");
        expected.replace(18, 19, "O");
        expected.replace(40, 41, "X");
        expected.replace(29, 30, "X");
        testBoard.dropToken('X', 0);
        testBoard.dropToken('O', 1);
        testBoard.dropToken('X', 0);
        testBoard.dropToken('O', 1);
        testBoard.dropToken('X', 2);
        testBoard.dropToken('O', 0);
        testBoard.dropToken('X', 2);
        testBoard.dropToken('O', 2);
        testBoard.dropToken('X', 1);
        StringBuilder actual = new StringBuilder(testBoard.toString());
        assertEquals(true, testBoard.checkTie());
        assertEquals(0, actual.compareTo(expected));
    }

    //whatsAtPos Tests

    // Test checks for a blank space in column 2 while
    // every other column is filled
    @Test
    public void testwhatsAtPos_blank_empty_column_0_2() {
        IGameBoard testBoard = gb(3, 5, 3);
        StringBuilder expected = new StringBuilder(boardToString(testBoard));
        expected.replace(52, 53, "X");
        expected.replace(35, 36, "O");
        expected.replace(18, 19, "X");
        expected.replace(55, 56, "O");
        expected.replace(38, 39, "X");
        expected.replace(21, 22, "O");
        expected.replace(61, 62, "X");
        expected.replace(44, 45, "O");
        expected.replace(27, 28, "X");
        expected.replace(64, 65, "O");
        expected.replace(47, 48, "X");
        expected.replace(30, 31, "O");
        testBoard.dropToken('X', 0);
        testBoard.dropToken('O', 0);
        testBoard.dropToken('X', 0);
        testBoard.dropToken('O', 1);
        testBoard.dropToken('X', 1);
        testBoard.dropToken('O', 1);
        testBoard.dropToken('X', 3);
        testBoard.dropToken('O', 3);
        testBoard.dropToken('X', 3);
        testBoard.dropToken('O', 4);
        testBoard.dropToken('X', 4);
        testBoard.dropToken('O', 4);
        StringBuilder actual = new StringBuilder(testBoard.toString());
        BoardPosition pos = new BoardPosition(0, 2 );
        assertEquals(' ', testBoard.whatsAtPos(pos));
        assertEquals(0, actual.compareTo(expected));

    }

    // Tests checks for a 'C' marker in row 2 column 1
    @Test
    public void testwhatsAtPos_C_full_board_2_1() {
        IGameBoard testBoard = gb(3, 5, 3);
        StringBuilder expected = new StringBuilder(boardToString(testBoard));
        expected.replace(52, 53, "A");
        expected.replace(35, 36, "E");
        expected.replace(18, 19, "A");
        expected.replace(55, 56, "B");
        expected.replace(38, 39, "D");
        expected.replace(21, 22, "C");
        expected.replace(58, 59, "C");
        expected.replace(41, 42, "C");
        expected.replace(24, 25, "E");
        expected.replace(61, 62, "D");
        expected.replace(44, 45, "B");
        expected.replace(27, 28, "D");
        expected.replace(64, 65, "E");
        expected.replace(47, 48, "A");
        expected.replace(30, 31, "B");
        testBoard.dropToken('A', 0);
        testBoard.dropToken('B', 1);
        testBoard.dropToken('C', 2);
        testBoard.dropToken('D', 3);
        testBoard.dropToken('E', 4);
        testBoard.dropToken('A', 4);
        testBoard.dropToken('B', 3);
        testBoard.dropToken('C', 2);
        testBoard.dropToken('D', 1);
        testBoard.dropToken('E', 0);
        testBoard.dropToken('A', 0);
        testBoard.dropToken('B', 4);
        testBoard.dropToken('C', 1);
        testBoard.dropToken('D', 3);
        testBoard.dropToken('E', 2);
        StringBuilder actual = new StringBuilder(testBoard.toString());
        BoardPosition pos = new BoardPosition(2, 1 );
        assertEquals('C', testBoard.whatsAtPos(pos));
        assertEquals(0, actual.compareTo(expected));

    }

    // Test checks for a blank space for row 2 which is all empty
    @Test
    public void testwhatsAtPos_blank_top_row_empty_2_0() {
        IGameBoard testBoard = gb(3, 5, 3);
        StringBuilder expected = new StringBuilder(boardToString(testBoard));
        expected.replace(52, 53, "X");
        expected.replace(35, 36, "O");
        expected.replace(55, 56, "O");
        expected.replace(38, 39, "X");
        expected.replace(58, 59, "X");
        expected.replace(41, 42, "O");
        expected.replace(61, 62, "O");
        expected.replace(44, 45, "X");
        expected.replace(64, 65, "X");
        expected.replace(47, 48, "O");
        testBoard.dropToken('X', 0);
        testBoard.dropToken('O', 1);
        testBoard.dropToken('X', 2);
        testBoard.dropToken('O', 3);
        testBoard.dropToken('X', 4);
        testBoard.dropToken('O', 4);
        testBoard.dropToken('X', 3);
        testBoard.dropToken('O', 2);
        testBoard.dropToken('X', 1);
        testBoard.dropToken('O', 0);
        StringBuilder actual = new StringBuilder(testBoard.toString());
        BoardPosition pos = new BoardPosition(2, 0 );
        assertEquals(' ', testBoard.whatsAtPos(pos));
        assertEquals(0, actual.compareTo(expected));
    }

    // Test checks for a blank space as the board is completely empty
    @Test
    public void testwhatsAtPos_blank_empty_board_0_0() {
        IGameBoard testBoard = gb(3, 5, 3);
        StringBuilder expected = new StringBuilder(boardToString(testBoard));
        StringBuilder actual = new StringBuilder(testBoard.toString());
        BoardPosition pos = new BoardPosition(0, 0 );
        assertEquals(' ', testBoard.whatsAtPos(pos));
        assertEquals(0, actual.compareTo(expected));
    }

    // Test checks the 'X' marker in row 0 column 0 when the board
    // is somewhat filled up
    @Test
    public void testwhatsAtPos_X_slightly_filled_board_0_0() {
        IGameBoard testBoard = gb(3, 5, 3);
        StringBuilder expected = new StringBuilder(boardToString(testBoard));
        expected.replace(52, 53, "X");
        expected.replace(55, 56, "O");
        expected.replace(58, 59, "X");
        expected.replace(61, 62, "O");
        expected.replace(58, 59, "X");
        expected.replace(64, 65, "X");
        expected.replace(38, 39, "O");
        expected.replace(38, 39, "X");
        expected.replace(44, 45, "O");
        testBoard.dropToken('X', 0);
        testBoard.dropToken('O', 1);
        testBoard.dropToken('X', 2);
        testBoard.dropToken('O', 3);
        testBoard.dropToken('X', 4);
        testBoard.dropToken('O', 3);
        testBoard.dropToken('X', 1);
        StringBuilder actual = new StringBuilder(testBoard.toString());
        BoardPosition pos = new BoardPosition(0, 0 );
        assertEquals('X', testBoard.whatsAtPos(pos));
        assertEquals(0, actual.compareTo(expected));
    }

    //isPlayerAtPos Tests
    // Tests checks for a false result as the board empty and there
    // should be no players on the board currently
    @Test
    public void testisPlayerAtPos_false_empty_board_0_0_X() {
        IGameBoard testBoard = gb(3, 5, 3);
        StringBuilder expected = new StringBuilder(boardToString(testBoard));
        StringBuilder actual = new StringBuilder(testBoard.toString());
        BoardPosition pos = new BoardPosition(0, 0 );
        assertEquals(false, testBoard.isPlayerAtPos(pos, 'X'));
        assertEquals(0, actual.compareTo(expected));
    }

    // Test checks for a true result checking to see if the
    // 'X' marker is in row 0 column 0
    @Test
    public void testisPlayerAtPos_true_one_t() {
        IGameBoard testBoard = gb(3, 5, 3);
        StringBuilder expected = new StringBuilder(boardToString(testBoard));
        expected.replace(52, 53, "X");
        testBoard.dropToken('X', 0);
        StringBuilder actual = new StringBuilder(testBoard.toString());
        BoardPosition pos = new BoardPosition(0, 0 );
        assertEquals(true, testBoard.isPlayerAtPos(pos, 'X'));
        assertEquals(0, actual.compareTo(expected));
    }

    // Test checks for a false result checking to see if the 'X' marker
    // is in row 1 column 2 when it's actually the 'O' marker
    @Test
    public void testisPlayerAtPos_false_not_in_that_postion_1_2_X() {
        IGameBoard testBoard = gb(3, 5, 3);
        StringBuilder expected = new StringBuilder(boardToString(testBoard));
        expected.replace(52, 53, "X");
        expected.replace(55, 56, "O");
        expected.replace(58, 59, "X");
        expected.replace(61, 62, "O");
        expected.replace(64, 65, "X");
        expected.replace(47, 48, "O");
        expected.replace(44, 45, "X");
        expected.replace(41, 42, "O");
        expected.replace(38, 39, "X");
        expected.replace(35, 36, "O");
        testBoard.dropToken('X', 0);
        testBoard.dropToken('O', 1);
        testBoard.dropToken('X', 2);
        testBoard.dropToken('O', 3);
        testBoard.dropToken('X', 4);
        testBoard.dropToken('O', 4);
        testBoard.dropToken('X', 3);
        testBoard.dropToken('O', 2);
        testBoard.dropToken('X', 1);
        testBoard.dropToken('O', 0);
        StringBuilder actual = new StringBuilder(testBoard.toString());
        BoardPosition pos = new BoardPosition(1, 2 );
        assertEquals(false, testBoard.isPlayerAtPos(pos, 'X'));
        assertEquals(0, actual.compareTo(expected));
    }

    // Tests checks for a true result checking to see if the 'X'
    // marker is in row 0 column 4 when it is
    @Test
    public void testisPlayerAtPos_true_near_full_board_0_4_X() {
        IGameBoard testBoard = gb(3, 5, 3);
        StringBuilder expected = new StringBuilder(boardToString(testBoard));
        expected.replace(52, 53, "X");
        expected.replace(55, 56, "O");
        expected.replace(58, 59, "X");
        expected.replace(61, 62, "O");
        expected.replace(64, 65, "X");
        expected.replace(47, 48, "O");
        expected.replace(35, 36, "X");
        expected.replace(44, 45, "O");
        expected.replace(41, 42, "X");
        expected.replace(38, 39, "O");
        testBoard.dropToken('X', 0);
        testBoard.dropToken('O', 1);
        testBoard.dropToken('X', 0);
        testBoard.dropToken('O', 1);
        testBoard.dropToken('X', 2);
        testBoard.dropToken('O', 3);
        testBoard.dropToken('X', 2);
        testBoard.dropToken('O', 3);
        testBoard.dropToken('X', 4);
        testBoard.dropToken('O', 4);
        StringBuilder actual = new StringBuilder(testBoard.toString());
        BoardPosition pos = new BoardPosition(0, 4 );
        assertEquals(true, testBoard.isPlayerAtPos(pos, 'X'));
        assertEquals(0, actual.compareTo(expected));
    }

    // Test checks for a true result checking to see if the 'O'
    // marker is in row 0 column 0 amongst varies different markers
    @Test
    public void testisPlayerAtPos_true_full_board_many_tokens_0_0_O() {
        IGameBoard testBoard = gb(3, 5, 3);
        StringBuilder expected = new StringBuilder(boardToString(testBoard));
        expected.replace(52, 53, "O");
        expected.replace(55, 56, "X");
        expected.replace(58, 59, "J");
        expected.replace(61, 62, "I");
        expected.replace(64, 65, "O");
        expected.replace(47, 48, "X");
        expected.replace(44, 45, "J");
        expected.replace(41, 42, "I");
        expected.replace(38, 39, "O");
        expected.replace(35, 36, "X");
        expected.replace(18, 19, "J");
        expected.replace(21, 22, "I");
        expected.replace(24, 25, "O");
        expected.replace(27, 28, "X");
        expected.replace(30, 31, "J");
        testBoard.dropToken('O', 0);
        testBoard.dropToken('X', 1);
        testBoard.dropToken('J', 2);
        testBoard.dropToken('I', 3);
        testBoard.dropToken('O', 4);
        testBoard.dropToken('X', 4);
        testBoard.dropToken('J', 3);
        testBoard.dropToken('I', 2);
        testBoard.dropToken('O', 1);
        testBoard.dropToken('X', 0);
        testBoard.dropToken('J', 0);
        testBoard.dropToken('I', 1);
        testBoard.dropToken('O', 2);
        testBoard.dropToken('X', 3);
        testBoard.dropToken('J', 4);
        StringBuilder actual = new StringBuilder(testBoard.toString());
        BoardPosition pos = new BoardPosition(0, 0 );
        assertEquals(true, testBoard.isPlayerAtPos(pos, 'O'));
        assertEquals(0, actual.compareTo(expected));
    }

    //dropToken Tests
    // Test checks to see if the 'X' marker is placed in column 0
    @Test
    public void testdropToken_empty_board_X_0() {
        IGameBoard testBoard = gb(3, 5, 3);

    }

    // Test checks to see if nothing happens when placing a marker
    // in a full column
    @Test
    public void testdropToken_full_col_X_0() {
        IGameBoard testBoard = gb(3, 5, 3);

    }

    // Tests checks to see if the marker gets placed at the top of a column
    @Test
    public void testdropToken_last_col_space_3() {
        IGameBoard testBoard = gb(3, 5, 3);

    }

    // Test checks to see if a marker gets placed on top of a somewhat filled column
    @Test
    public void testdropToken_moderately_full_col_X_2() {
        IGameBoard testBoard = gb(3, 5, 3);

    }

    // Test checks to see if a marker gets placed in the last spot available on the board
    @Test
    public void testdropToken_almost_full_board_X_0() {
        IGameBoard testBoard = gb(3, 5, 3);

    }
}
