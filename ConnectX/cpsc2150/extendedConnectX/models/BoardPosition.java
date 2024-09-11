package cpsc2150.extendedConnectX.models;

/*GROUP MEMBER NAMES AND GITHUB USERNAMES SHOULD GO HERE
Isaac Hine (ihin3)

Kevin Lin (kllin21)
 */

public class BoardPosition
{
    /**
     * @invariants (row >= 0 && < GameBoard.maxRow && column >= 0 && < GameBoard.maxColumn)
     *          
     */
    private int Row;
    private int Column;
    /**
     * This constructor initializes an int for both Row and Column
     * 
     * @param aRow [The horizontal position]
     * @param aColumn [The vertical position]
     * 
     * @pre None
     * 
     * @post Row = aRow && Column = aColumn
     * 
     * @return N/A
     */
    public BoardPosition(int aRow, int aColumn)
    {
        Row = aRow;
        Column = aColumn;
        //parameterized constructor for BoardPosition
    }
    /**
     * This method is retrieving the row position of game piece
     * 
     * @pre None
     * 
     * @post getRow = Row [obtains row position]
     *       AND Row = #Row AND Column = #Column
     * 
     * @return an int representation of row position
     */
    public int getRow()
    {
        return Row;
        //returns the row
    }
    /**
     * This method is retrieving the column position of game piece
     * 
     * @pre None
     * 
     * @post getColumn = Column [obtains column position]
     *       AND [Row = #Row AND Column = #Column]
     * 
     * @return an int representation of column position
     */
    public int getColumn()
    {
        return Column;
        //returns the column
    }
    /**
     * This method will check to see if two BoardPositions are equal based on if 
     * they have the same row and column
     * 
     * @param obj contains a row and column position
     * 
     * @pre None
     * 
     * @post equals = [Method returns true if two BoardPositions have the same row and column]
     *       AND [Method return false if two BoardPositions do not have the same row and column]
     *       AND Row = #Row AND Column = #Column
     * 
     * @return Boolean dependent on if two BoardPositions have the same row and column
     */
    @Override
    public boolean equals(Object obj)
    {
        if(obj.getClass() != getClass()){
            return false;
        }
        BoardPosition x = (BoardPosition) obj;
        return (x.getRow() == getRow() && x.getColumn() == getColumn());
    }
    /**
     * This method is creating a string that will be representing the current
     * row and column such as "<Row>,<Column>"
     * 
     * @pre None
     * 
     * @post toString = [Method returns a string containing the current Row and Column "<Row><Column>"]
     * 
     * @return A string showing the current Row and Column position
     */
    @Override
    public String toString()
    {
        String s = "";
        s += getRow();
        s += ",";
        s += getColumn();
        return s;
    }
}
