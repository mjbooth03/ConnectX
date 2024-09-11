package cpsc2150.extendedConnectX.models;
import java.util.*;

/**
     * @invariants
     * setRow >= 3 && setRow <= 100
     * setColumn >= 3 && setColumn <= 100
     * setToken >= 3
     * @correspondence
     * boardField == HashMap<Character, List<BoardPosition>
     * @define setRow in IGameBoard corresponds to setRow in GameBoardMem
     * @define setColumn in IGameBoard corresponds to setColumn in GameBoardMem
     * @define setToken in IGameBoard corresponds to setToken in GameBoardMem
    */
public class GameBoardMem extends AbsGameBoard implements IGameBoard{
    private int setRow;
    private int setColumn;
    private int setToken;
    private Map<Character, List<BoardPosition>> boardField;
    /**
     * <This constructor allows an object of GameBoardMem to be created>
     * @pre
     * None
     * @post
     * [Object of class GameBoardMem is instantiated]
     * @return N/A
     */

    public GameBoardMem(int r, int c, int t) {
        setRow = r;
        setColumn = c;
        setToken = t;
        boardField = new HashMap<>();
    }

    /**
     * <Places a token for a player in a specific column>
     * @pre
     * [p is a valid player token ('X' or 'O')]
     * c =! null
     * [boardField is not full]
     * @post
     * [A player token is placed in the lowest available position in column c.]
     * @return N/A
     */
    public void dropToken(char p, int c) {
        boardField.computeIfAbsent(p, k->new ArrayList<>());
        for (int i = 0; i < setRow; i++) {
            BoardPosition pos = new BoardPosition(i, c);
            if (whatsAtPos(pos) == ' ') {
                boardField.get(p).add(pos);
                break;
            }
        }
    }

    public char whatsAtPos(BoardPosition pos) {
        for (Map.Entry<Character, List<BoardPosition>> entry : boardField.entrySet()) {
            if (entry.getValue().contains(pos)) {
                return entry.getKey();
            }
        }
        return ' ';
    }
    public boolean isPlayerAtPos(BoardPosition pos, char player) {
        boolean is = false;
        List<BoardPosition> Pos = boardField.get(player);
        if (Pos != null && Pos.contains(pos)) {
            is = true;
        }
        return is;
    }

    public boolean checkTie() {
        boolean tie = true;
        for (int row = 0; row < getNumRows(); row++) {
            for (int col = 0; col < getNumColumns(); col++) {
                BoardPosition pos = new BoardPosition(row, col);
                if (whatsAtPos(pos) == ' ') {
                    tie = false;
                    break;
                }
            }
        }
        return tie;
    }
    public int getNumRows() {
        return setRow;
    }
    public int getNumColumns() {
        return setColumn;
    }
    public int getNumToWin() {
        return setToken;
    }
}
