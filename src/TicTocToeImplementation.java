import java.util.Scanner;

public class TicTocToeImplementation implements TicTacToe {

    private char[][] gameBoard;
    private int size;
    private int gameState = -1;
    private String playerOne;
    private String playerTwo;

    private int remainCount;

    private int turn; // 0 for player 1 && 1 for player 2;

    public Scanner input = new Scanner(System.in);

    public TicTocToeImplementation() {

    }

    // method to setSize
    private void setBoardSize() {
        remainCount = (size * size);
    }

    // Method to start the game
    public void startGame() {

        print("\n***** WELCOME TO THE TIC-TOC-TOE GAME ******");
        print("\n");
        print("Please enter player 1 name (X) : ");
        playerOne = input.nextLine();
        print("Please enter player 2 name (O) : ");
        playerTwo = input.nextLine();

        print("\nPlease enter Board size: ");
        size = input.nextInt();
        boolean isValid = false;

        while (!isValid) {
            if (size > 1) {
                reset(size);
                isValid = true;
                setBoardSize();
            } else {
                print("Board is too small, please enter board size again: ");
                size = input.nextInt();
            }
        }

        printBoard();

        // Player's first move
        firstMove();
        printBoard();

        // Running the game

        runGame();
    }

    private void runGame() {

        while (gameState < 0 && remainCount > 0) {

            if (turn == 0) {

                print(playerOne + " enter row coordinate: ");
                int row = input.nextInt();
                print(playerOne + " enter column coordinate: ");
                int col = input.nextInt();

                try {
                    gameState = playerAMove(row, col);
                    printBoard();
                    turn = 1;

                } catch (TicTacToeException ex) {
                    print(ex.getMessage());

                }

            } else {
                print(playerTwo + " enter row coordinate: ");
                int row = input.nextInt();
                print(playerTwo + " enter column coordinate: ");
                int col = input.nextInt();

                try {
                    gameState = playerBMove(row, col);
                    printBoard();
                    turn = 0;

                } catch (TicTacToeException ex) {
                    print(ex.getMessage());

                }

            }

            if (gameState == 1) {
                if (turn == 1) {
                    print(playerOne + " You Win!");
                } else {
                    print(playerTwo + " You Win!");
                }
            } else if (gameState == 0) {
                print("It's a DRAW!");
            }

        }

    }

    // String print Method;
    public <T> void print(T data) {
        System.out.print(data);
    }

    // Print board method
    public void printBoard() {
        int numRow = gameBoard.length;
        int numCol = gameBoard[0].length;

        System.out.println();

        // First write the column header
        System.out.print("    ");
        for (int i = 0; i < numCol; i++)
            System.out.print((i + 1) + "   ");
        System.out.print('\n');

        System.out.println(); // blank line after the header

        // The write the table
        for (int i = 0; i < numRow; i++) {
            System.out.print((i + 1) + "  ");
            for (int j = 0; j < numCol; j++) {
                if (j != 0)
                    System.out.print("|");
                System.out.print(" " + gameBoard[i][j] + " ");
            }

            System.out.println();

            if (i != (numRow - 1)) {
                // separator line
                System.out.print("   ");
                for (int j = 0; j < numCol; j++) {
                    if (j != 0)
                        System.out.print("+");
                    System.out.print("---");
                }
                System.out.println();
            }
        }
        System.out.println();
    }

    @Override
    public void reset(int size) {
        gameBoard = new char[size][size];
        resetBoard(gameBoard);

    }

    // Method to reset the game board
    private void resetBoard(char[][] gameBoard) {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                gameBoard[i][j] = ' ';
            }
        }
    }

    @Override
    public char boardValue(int x, int y) throws TicTacToeException {
        if (y >= gameBoard.length || y < 0 || x >= gameBoard[y].length || x < 0) {
            throw new TicTacToeException("Invalid coordinates\n");

        }
        return gameBoard[x][y];
    }

    // method for player's move/
    private int turn(int x, int y, char player) throws TicTacToeException {

        boolean done = false;

        if (player == 'X') {
            turn = 0;
        } else {
            turn = 1;
        }

        x -= 1;
        y -= 1;

        char valueAtCell = boardValue(x, y);

        if (valueAtCell != ' ') {
            throw new TicTacToeException("Cell is already used " + valueAtCell + "\n");
        }

        --remainCount;
        gameBoard[x][y] = player;

        if (!isWon() && remainCount < 1) {
            return 0;
        }

        while (!done && remainCount > 0) {

            done = isWon();

            if (done) {
                if (player == 'X') {
                    turn = 0;
                } else {
                    turn = 1;
                }
                return 1;
            } else {
                turn = (turn + 1) % 2;
                return -1;
            }

        }

        return 1;
    }

    // Winning condition.
    private boolean isWon() {
        char symbol;

        if (turn == 0) {
            symbol = 'X';
        } else {
            symbol = 'O';
        }

        int i, j;

        boolean isWin = false;

        // win by row;
        for (i = 0; i < gameBoard.length && !isWin; i++) {
            for (j = 0; j < gameBoard[0].length; j++) {
                if (gameBoard[i][j] != symbol)
                    break;
            }
            if (j == gameBoard[0].length)
                isWin = true;
        }
        // win by column
        for (j = 0; j < gameBoard[0].length && !isWin; j++) {
            for (i = 0; i < gameBoard.length; i++) {
                if (gameBoard[i][j] != symbol) {
                    break;
                }
            }
            if (i == gameBoard.length) {
                isWin = true;

            }
        }
        // win by diagonal
        if (!isWin) {
            for (i = 0; i < gameBoard.length; i++) {
                if (gameBoard[i][i] != symbol) {
                    break;
                }
            }
            if (i == gameBoard.length) {
                isWin = true;

            }
        }

        // win by opposite diagonal
        if (!isWin) {
            for (i = 0; i < gameBoard.length; i++) {
                if (gameBoard[i][gameBoard.length - 1 - 1] != symbol) {
                    break;
                }
            }
            if (i == gameBoard.length) {
                isWin = true;

            }
        }
        return isWin;
    }

    // Return 1 if the player A won
    // Return 0 if its the last move and board is full (draw)
    @Override
    public int playerAMove(int x, int y) throws TicTacToeException {
        return turn(x, y, 'X');

    }

    @Override
    public int playerBMove(int x, int y) throws TicTacToeException {
        return turn(x, y, 'O');
    }

    // First move
    private void firstMove() {

        print("\n");
        print("Do you want to go first (y/n)? ");
        char answer = input.next().toLowerCase().charAt(0);
        boolean isValid = false;
        if (answer == 'y') {
            turn = 0;
            while (!isValid) {
                try {
                    print(playerOne + " enter row coordinate: ");
                    int row = input.nextInt();
                    print(playerOne + " enter column coordinate: ");
                    int col = input.nextInt();
                    gameState = playerAMove(col, row);
                    isValid = true;
                    turn = 1;
                } catch (TicTacToeException ex) {
                    print(ex.getMessage());

                }
            }
        } else {
            turn = 1;
            while (!isValid) {
                try {
                    print(playerTwo + " enter row coordinate: ");
                    int row = input.nextInt();
                    print(playerTwo + " enter column coordinate: ");
                    int col = input.nextInt();
                    isValid = true;
                    gameState = playerBMove(col, row);
                    turn = 0;
                } catch (TicTacToeException ex) {
                    print(ex.getMessage());

                }
            }
        }

    }

}
