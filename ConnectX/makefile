default: cpsc2150/extendedConnectX/models/BoardPosition.java cpsc2150/extendedConnectX/models/GameBoard.java cpsc2150/extendedConnectX/models/AbsGameBoard.java cpsc2150/extendedConnectX/models/IGameBoard.java cpsc2150/extendedConnectX/models/GameBoardMem.java cpsc2150/extendedConnects/GameScreen.java
	javac cpsc2150/extendedConnectX/models/BoardPosition.java cpsc2150/extendedConnectX/models/GameBoard.java cpsc2150/extendedConnectX/models/AbsGameBoard.java cpsc2150/extendedConnectX/models/IGameBoard.java cpsc2150/extendedConnectX/models/GameBoardMem.java cpsc2150/extendedConnects/GameScreen.java
	
run: cpsc2150/extendedConnectX/models/BoardPosition.class cpsc2150/extendedConnectX/models/GameBoard.class cpsc2150/extendedConnectX/models/AbsGameBoard.class cpsc2150/extendedConnectX/models/IGameBoard.class cpsc2150/extendedConnectX/models/GameBoardMem.class cpsc2150/extendedConnects/GameScreen.class
	java cpsc2150.extendedConnects.GameScreen

test: cpsc2150/test/TestGameBoard.java cpsc2150/test/TestGameBoardMem.java cpsc2150/extendedConnectX/models/BoardPosition.java cpsc2150/extendedConnectX/models/GameBoard.java cpsc2150/extendedConnectX/models/AbsGameBoard.java cpsc2150/extendedConnectX/models/IGameBoard.java cpsc2150/extendedConnectX/models/GameBoardMem.java
    javac -cp .:/usr/share/java/junit4.jar cpsc2150/test/TestGameBoard.java cpsc2150/test/TestGameBoardMem.java cpsc2150/extendedConnectX/models/BoardPosition.java cpsc2150/extendedConnectX/models/GameBoard.java cpsc2150/extendedConnectX/models/AbsGameBoard.java cpsc2150/extendedConnectX/models/IGameBoard.java cpsc2150/extendedConnectX/models/GameBoardMem.java

runtest: cpsc2150/test/TestGameBoard.class cpsc2150/test/TestGameBoardMem.class cpsc2150/extendedConnectX/models/BoardPosition.class cpsc2150/extendedConnectX/models/GameBoard.class cpsc2150/extendedConnectX/models/AbsGameBoard.class cpsc2150/extendedConnectX/models/IGameBoard.class cpsc2150/extendedConnectX/models/GameBoardMem.class
    java -cp .:/usr/share/java/junit4.jar org.junit.runner.JUnitCore cpsc2150.test.TestGameBoard cpsc2150.test.TestGameBoardMem
clean:
    rm -f cpsc2150/test/*.class
	rm -f cpsc2150/extendedConnectX/*.class
	rm -f cpsc2150/extendedConnects/*.class