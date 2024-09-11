package cpsc2150.extendedConnectX.models;

public abstract class AbsGameBoard implements IGameBoard
{
    public char[][] boardField = new char[getNumRows()][getNumColumns()];
    public String toString() {
        String gameBoard = "";
        //String to label the column numbers
        for (int i = 0; i < getNumColumns(); i++) {
            if (i <= 9) {
                gameBoard += "| " + i;
            }
            else {
                gameBoard += "|" + i;
            }
        }
        gameBoard += "|\n";
        //String to provide a blank GameScreen
        for (int i = getNumRows() - 1; i > -1; i--) {
            gameBoard += "|";
            for (int j = 0; j < getNumColumns(); j++) {
                BoardPosition mainPos = new BoardPosition(i, j);
                gameBoard += whatsAtPos(mainPos) + " |";
            }
            gameBoard += "\n";
        }
        return gameBoard;
    }
}
