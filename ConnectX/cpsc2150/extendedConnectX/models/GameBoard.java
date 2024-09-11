package cpsc2150.extendedConnectX.models;
/*GROUP MEMBER NAMES AND GITHUB USERNAMES SHOULD GO HERE
Isaac Hine (ihin3)
Kevin Lin (kllin21)

Jerome Booth, mjbooth03

 */

public class GameBoard extends AbsGameBoard implements IGameBoard {

    /**
     * @invariants
     * piece == 'X' || piece == 'O'
     * row >= 0 && < maxRow 
     * col >= 0 && < maxCol
     * boardField[row][col]
    */
    public static final int minRow = 3;
    public static final int maxRow = 100;
    public static final int minCol = 3;
    public static final int maxCol = 100;
    public static final int minToken = 3;
    public static final int maxToken = 100;
    int rowChoice;
    int colChoice;
    int tokenChoice;
    private char[][] boardField = new char[maxRow][maxCol];
    /**
     * <This constructor allows an object of Gameboard to be created>
     * @pre
     * None
     * @post
     * [Object of class Gameboard is instantiated]
     * @return N/A
    */

    public GameBoard(int r, int c, int t) {
        rowChoice = r;
        colChoice = c;
        tokenChoice = t;
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++) {
                boardField[i][j] = ' ';
            }
        }
    }
   
    public void dropToken(char p, int c)
    {
        for(int i = 0; i < maxRow; i++){
            if(boardField[i][c] == ' '){
                boardField[i][c] = p;
                break;
            }
        }
    }

    public char whatsAtPos(BoardPosition pos)
    {
        char p = ' ';
        int row = pos.getRow();
        int col = pos.getColumn();
        if(boardField[row][col] != ' ') {
            p = boardField[row][col];
        }
        return p;
    }

   public int getNumRows()
   {
      return rowChoice;
   }

   public int getNumColumns()
   {
      return colChoice;
   }

   public int getNumToWin()
   {
      return tokenChoice;
   }
}
