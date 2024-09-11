package cpsc2150.extendedConnects;
import cpsc2150.extendedConnectX.models.GameBoard;
import cpsc2150.extendedConnectX.models.GameBoardMem;
import cpsc2150.extendedConnectX.models.IGameBoard;
import java.util.*;
import java.util.Scanner;
/*GROUP MEMBER NAMES AND GITHUB USERNAMES SHOULD GO HERE
Isaac Hine (ihin3)
Kevin Lin (kllin21)
 */

public class GameScreen {
    /**
     * @invariants
     * MIN_PLAYERS = 2
     * MAX_PLAYERS = 10
     */
    static final int MIN_PLAYERS = 2;
    static final int MAX_PLAYERS = 10;

    public static void main(String[] args) {
        IGameBoard connectBoardField = null;
        List<Character> playerList = new ArrayList<>();
        int numPlayer = 0;
        int tokenWin;
        int numCol;
        int numRow;
        String playerAnswer = " ";
        int colChoice;
        int numMove = 0;

        Scanner myObj = new Scanner(System.in);

        while (!playerAnswer.equals("n") && !playerAnswer.equals("N")) {
            boolean gameWin = false;
            playerList.clear();
            System.out.println("How many players will be playing?");
            numPlayer = myObj.nextInt();
            //Get Players and tokens
            while (numPlayer < MIN_PLAYERS || numPlayer > MAX_PLAYERS) {
                if (numPlayer < MIN_PLAYERS) {
                    System.out.println("Must be at least 2 players");
                } else {
                    System.out.println("Must be 10 players or fewer");
                }
                System.out.println("How many players?");
                numPlayer = myObj.nextInt();
            }
            myObj.nextLine();
            //Get Player tokens
            for (int i = 0; i < numPlayer; i++) {
                System.out.println("Enter the character to represent player " + (i + 1));
                playerAnswer = myObj.nextLine();

                //Converts string to uppercase if lowercase

                if (!Character.isUpperCase(playerAnswer.charAt(0))) {
                    playerAnswer = playerAnswer.toUpperCase();
                }
                while (playerList.contains(playerAnswer.charAt(0))) {
                    System.out.println(playerAnswer + " is already taken as a player token!");
                    System.out.println("Enter the character to represent player " + (i + 1));
                    playerAnswer = myObj.nextLine();
                    if (!Character.isUpperCase(playerAnswer.charAt(0))) {
                        playerAnswer = playerAnswer.toUpperCase();
                    }
                }
                playerList.add(playerAnswer.charAt(0));
            }
            System.out.println("How many rows will be on the board?");
            numRow = myObj.nextInt();
            //Get num row
            while (numRow < GameBoard.minRow || numRow > GameBoard.maxRow) {
                if (numRow < GameBoard.minRow) {
                    System.out.println("There must be at least 3 rows");
                } else {
                    System.out.println("There must be 100 or less rows");
                }
                System.out.println("How many rows will be on the board?");
                numRow = myObj.nextInt();
            }
            System.out.println("How many columns will be on the board?");
            numCol = myObj.nextInt();
            //Get num col
            while (numCol < GameBoard.minCol || numCol > GameBoard.maxCol) {
                if (numCol < GameBoard.minCol) {
                    System.out.println("There must be at least 3 columns");
                } else {
                    System.out.println("There must be 100 or less columns");
                }
                System.out.println("How many columns will be on the board?");
                numCol = myObj.nextInt();
            }
            //Get num to win
            System.out.println("How many tokens in a row to win?");
            tokenWin = myObj.nextInt();
            while (tokenWin < GameBoard.minToken || tokenWin > GameBoard.maxToken ||
                    (tokenWin > numCol && tokenWin > numRow)) {
                if (tokenWin < GameBoard.minToken) {
                    System.out.println("There must be at least 3 tokens to win");
                } else if (tokenWin > GameBoard.maxToken) {
                    System.out.println("There must be 100 or fewer tokens to win");
                } else {
                    System.out.println("Tokens must be fewer than the highest row or column");
                }
                System.out.println("How many tokens in a row to win?");
                tokenWin = myObj.nextInt();
            }
            myObj.nextLine();
            System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m?");
            playerAnswer = myObj.nextLine();
            while (playerAnswer.charAt(0) != 'F' && playerAnswer.charAt(0) != 'f' && playerAnswer.charAt(0) != 'M'
                    && playerAnswer.charAt(0) != 'm') {
                System.out.println("Invalid input, enter F or M");
                System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m?)");
                playerAnswer = myObj.nextLine();
            }
            if (playerAnswer.charAt(0) == 'F' || playerAnswer.charAt(0) == 'f') {
                connectBoardField = new GameBoard(numRow, numCol, tokenWin);
            }
            else {
                connectBoardField = new GameBoardMem(numRow, numCol, tokenWin);
            }
            System.out.println(connectBoardField.toString());
            while (!gameWin && !connectBoardField.checkTie()) {
                System.out.println("Player " + playerList.get(numMove % numPlayer) + ", " +
                        "What column would you like to choose?");
                colChoice = myObj.nextInt();
                while (colChoice < 0 || colChoice > connectBoardField.getNumColumns() - 1) {
                    System.out.println("Please enter column between 0-" + (connectBoardField.getNumColumns() - 1));
                    colChoice = myObj.nextInt();
                }
                while (!connectBoardField.checkIfFree(colChoice)) {
                    System.out.println("Column is full, choose another column");
                    colChoice = myObj.nextInt();
                }
                connectBoardField.dropToken(playerList.get(numMove % numPlayer), colChoice);
                System.out.println(connectBoardField.toString());
                gameWin = connectBoardField.checkForWin(colChoice);
                numMove += 1;
            }
            if (gameWin == true) {
                numMove -= 1;
                System.out.println("Player " + playerList.get(numMove % numPlayer) + " has won!\n");
                System.out.println("Play again?(Y/N)");
                myObj.nextLine();
                playerAnswer = myObj.nextLine();
            }
            if (connectBoardField.checkTie() == true) {
                System.out.println("Draw!");
                System.out.println("Play again?(Y/N)");
                myObj.nextLine();
                playerAnswer = myObj.nextLine();
            }
        }
    }
}





