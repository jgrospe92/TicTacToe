# TicTacToe
 TicTacToe Game

# Description
- Implement an ADT that can be used as a Tic-Tac-Toe game board. Your ADT will implement the TicTacToe interface. In the main method also include a simple usage example.
- Here are the rules of N x N Tic-Tac-Toe game:

# Rules to implement
- Players take turns placing characters into empty squares (" ").
- The first player A always places "X" characters, while the second player B always places "O" characters.
- "X" and "O" characters are always placed into empty squares, never on filled ones.
- The game ends when there are N of the same (non-empty) character filling any row, column, or diagonal.
- The game also ends if all squares are non-empty.
- No more moves can be played if the game is over

# TicTacToe Interface
```java
/** Starts a new game on a board of size x size.
 @return void. */
 public void reset(int size);

/* Retrieves either ' ', 'X' or 'O' from the position x and y on the
board.
 @return Char ' ', 'X' or 'O'.
 @throws TicTacToeException("Invalid coordinates") if values x,y
 exceeds board size. */
 public Char boardValue(int x, int y);

 /* Simulates palyer A move. Player A plays 'X'.
 @return 1 if player A won. 0 if this was the last move and the board
is full, thus draw. -1 if the game is not finished.
 @throws TicTacToeException("Invalid coordinates") if values x,y
 exceeds board size. TicTacToeException("Cell is already used") if
 the cell at coordinates x,y was already used before */
 public int playerAMove(int x, int y);

/* Simulates palyer B move. Player B plays 'O'.
 @return 1 if player B won. 0 if this was the last move and the board
is full, thus draw. -1 if the game is not finished.
 @throws TicTacToeException("Invalid coordinates") if values x,y
 exceeds board size. TicTacToeException("Cell is already used") if
 the cell at coordinates x,y was already used before */
 public int playerBMove(int x, int y); 
```

# Scalable Board size NxN
- The max playable board size is 12


# Demo

<p align=center>
<img src="src\demo\demo1.JPG" alt="TicTocToe screen shot demo" height="300">
<img src="src\demo\demo2.JPG" alt="TicTocToe screen shot demo" height="300">
</p>