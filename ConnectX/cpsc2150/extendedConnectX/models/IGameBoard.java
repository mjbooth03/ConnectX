package cpsc2150.extendedConnectX.models;

public interface IGameBoard{
  char[][] boardField = new char[100][100];
/**
 * <This function will get the number of rows in GameBoard>
 * 
 * @pre None
 * 
 * @post getNumRows() = maxRow AND self = #self
 * 
 * @return an int representation of the number of rows in GameBoard
 */
public int getNumRows();

/**
 * <This function will get the number of columns in GameBoard>
 * 
 * @pre None
 * 
 * @post getNumColumns = maxCol AND self = #self
 * @return an int representation of the number of columns in GameBoard
 */
public int getNumColumns();

/**
 * <This function will get the number of tokens needed to win the game>
 * 
 * @pre None
 * 
 * @post getNumToWin() = tokenToWin AND self = #self
 * @return an int representation of the number of tokens to win the game
 */
public int getNumToWin();
  
/**
 * <This function will check if the selected column c has enough room for another token>
 * 
 * @param c int value containing the number of the selected column.
 * @pre 
 * c >= 0 and c < maxCol
 * @post checkIfFree = [The function returns true if there is a blank space ' ' in the top-most row in column c or 
 * false if all rows in column c have a player's token ('X' or 'O')] AND self = #self
 * @return Boolean dependent on the column's space availability.
*/

default boolean checkIfFree(int c) {
    BoardPosition position = new BoardPosition(getNumRows() - 1, c);
    if (whatsAtPos(position) != ' ') {
        return false;
    }
    return true;
}
  
/**
 * <This function will check to see if the last token placed in column c resulted in the player winning the game>
 * 
 * @param c int value containing the number of the selected column.
 * @pre 
 * c >= 0 and c < maxCol
 * @post checkForWin = [The function will return true if placed token is the last to make up the maximum number of consecutive 
 * player pieces needed to win either vertically, horizontally, or diagonally. False is returned if there
 * was no win.] AND self = #self
 * @return Boolean dependent on if the player's move won them the game.
*/

default boolean checkForWin(int c){
    int row = getNumRows();
    int currRow = 0;
    char playerAtPos = ' ';

    for(int i = row - 1; i > -1; i--){                    // Find the lowest available row in the specified column
        BoardPosition position = new BoardPosition(i, c);
        if(whatsAtPos(position) != ' '){
            currRow = i;
            break;
        }
    }

    BoardPosition pos = new BoardPosition(currRow, c);    // Check for a win in horizontal, diagonal, or vertical direction
    playerAtPos = whatsAtPos(pos);
    if(checkHorizWin(pos,playerAtPos) || checkDiagWin(pos,playerAtPos) || checkVertWin(pos,playerAtPos)){
        return true;
    }

    return false;
}
/**
 * <This function will place the player character p in the column c at the lowest available row>
 * 
 * @param p character containing the player's token 'X' or 'O'.
 * @param c int value containing the number of the selected column.
 * @pre 
 * (p == 'X' || p == 'O') , (c >= 0 && c < maxCol) , [column c has space for the token / checkIfFree(c) 
 * returns true]
 * @post dropToken = [column c now has a new player token p at the lowest available row, the rest of the board remains
 * unchanged] AND self = #self
*/

public void dropToken(char p, int c);

/**
 * <This function will check if the last token placed by player p in Gameboard position pos resulted
 * in a win horizontally>
 * 
 * @param pos BoardPosition object that holds the position of player p's token or an empty space.
 * @param p character containing the player's token 'X' or 'O'.
 * @pre
 * [(p == 'X' || p == 'O'), p =! ' ']
 * @post checkHorizWin = [The function will return true if player p's last move in position pos is the last of the maximum
 * consecutive tokens needed to form a horizontal win. Function returns false otherwise.] AND self = #self
 * @return Boolean dependent on if there is a horizontal win.
*/

default boolean checkHorizWin(BoardPosition pos, char p) {
    int count = 0;
    int row = pos.getRow();
    int col = pos.getColumn();
    boolean win = false;
    for(int i = col; i < getNumColumns(); i++) {
        BoardPosition Pos = new BoardPosition(row, i);
        if(isPlayerAtPos(Pos, p)) {
            count++;
        }
        else {
            break;
        }
    }
    for(int i = col - 1; i >= 0; i--) {
        BoardPosition Pos = new BoardPosition(row, i);
        if(isPlayerAtPos(Pos, p)) {
            count++;
        }
        else {
            break;
        }
    }

    if(count >= getNumToWin()) {
        return true;
    }

    return win;
}
  
/**
 * <This function will check if the last token placed by player p in Gameboard position pos resulted
 * in a win vertically>
 * 
 * @param pos BoardPosition object that holds the position of player p's token or an empty space.
 * @param p character containing the player's token 'X' or 'O'.
 * @pre
 * [(p == 'X' || p == 'O'), p =! ' ']
 * @post checkVertWin = [The function will return true if player p's last move in position pos is the last of the maximum
 * consecutive tokens needed to form a vertical win. Function returns false otherwise.] AND self = #self
 * @return Boolean dependent on if there is a vertical win.
*/

default boolean checkVertWin(BoardPosition pos, char p){
    int count = 0;
    int row = pos.getRow();
    int col = pos.getColumn();
    boolean win = false;
    for(int i = row; i < getNumRows(); i++) {
        BoardPosition Pos = new BoardPosition(i, col);
        if(isPlayerAtPos(Pos, p)) {
            count++;
        }
        else {
            break;
        }
    }

    for(int i = row - 1; i >= 0; i--) {
        BoardPosition Pos = new BoardPosition(i, col);
        if(isPlayerAtPos(Pos, p)) {
            count++;
        }
        else {
            break;
        }
    }
    if(count >= getNumToWin()) {
        win = true;
    }

    return win;
}

/**
 * <This function will check if the last token placed by player p in Gameboard position pos resulted
 * in a win diagonally>
 * 
 * @param pos BoardPosition object that holds the position of player p's token or an empty space.
 * @param p character containing the player's token 'X' or 'O'.
 * @pre
 * [(p == 'X' || p == 'O'), p =! ' ']
 * @post checkDiagWin = [The function will return true if player p's last move in position pos is the last of the maximum
 * consecutive tokens needed to form a diagonal win. Function returns false otherwise.] AND self = #self
 * @return Boolean dependent on if there is a diagonal win.
*/

default boolean checkDiagWin(BoardPosition pos, char p) {
    int rightCount = 0;
    int leftCount = 0;
    int row = pos.getRow();
    int col = pos.getColumn();
    boolean win = false;

    for (int i = row, j = col; i < getNumRows() && j < getNumColumns(); i++, j++) {
        BoardPosition newPos = new BoardPosition(i, j);
        if (isPlayerAtPos(newPos, p)) {
            rightCount++;
        }
        else {
            break;
        }
    }

    for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
        BoardPosition newPos = new BoardPosition(i, j);
        if (isPlayerAtPos(newPos, p)) {
            rightCount++;
        }
        else {
            break;
        }
    }

    for (int i = row, j = col; i < getNumRows() && j >= 0; i++, j--) {
        BoardPosition newPos = new BoardPosition(i, j);
        if (isPlayerAtPos(newPos, p)) {
            leftCount++;
        }
        else {
            break;
        }
    }

    for (int i = row - 1, j = col + 1; i >= 0 && j < getNumColumns(); i--, j++) {
        BoardPosition newPos = new BoardPosition(i, j);
        if (isPlayerAtPos(newPos, p)) {
            leftCount++;
        }
        else {
            break;
        }
    }

    if (rightCount >= getNumToWin() || leftCount>= getNumToWin()) {
        win = true;
    }

    return win;
}

/**
 * <This function will check to see if the game resulted in a tie>
 * @pre
 * None
 * @post checkTie = [The function will return true if all rows and columns of the board are filled with player tokens 
 * and the maximum consecutive pieces of the same token have not made for a win vertically, horizontally,
 * or diagonally. checkForWin() will have already retured true otherwise.] AND self = #self
 * @return Boolean dependent on if the game is tied.
*/

default boolean checkTie()
{
    for(int i = 0; i < getNumColumns(); i++ ){
        BoardPosition position1 = new BoardPosition(getNumRows()-1, i);
        if(whatsAtPos(position1) == ' '){
            return false;
        }
    }
    return true;
}

/**
 * <This function will determine if player p is at position pos>
 * 
 * @param pos BoardPosition object that holds the position of player p's token or an empty space.
 * @param player character containing the player's token 'X' or 'O'.
 * @pre
 * [pos must be a valid position on the Gameboard] and [(player == 'X' || player == 'O'), player =! ' ']
 * @post isPlayerAtPos = [function returns true if player p is at position pos or false if the player is not.] AND self = #self
 * @return Boolean dependent on if the selected player is at the selected position
*/

default boolean isPlayerAtPos(BoardPosition pos, char player){
    char p;
    boolean at = false;
    int row = pos.getRow();
    int col = pos.getColumn();
    if(whatsAtPos(pos) == player) {
        at = true;
    }
    return at;
}

/**
 * <This function returns the token of the player occupying the position pos or a blank space>
 * 
 * @param pos BoardPosition object that holds the position of player p's token or an empty space.
 * @pre
 * None
 * @post whatsAtPos = [function returns 'X' or 'O' depending on the player at position pos or ' ' if it is unoccupied.] AND 
 * self = #self
 * @return A character value that corresponds with the selected position.
*/

public char whatsAtPos(BoardPosition pos);

}
