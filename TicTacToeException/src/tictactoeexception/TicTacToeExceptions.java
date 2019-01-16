//Geerthika Senthil
package tictactoeexception;
import java.util.Scanner;
public class TicTacToeExceptions {
    
   public static final int EMPTY = 0;
   public static final int X = 1;
   public static final int O = 2;
   public static int PLAYING = 0;
   public static int DRAW = 1;
   public static int X_WON = 2;
   public static int O_WON = 3;
    // The game board and the game status
   public static int ROWS = 3, COLS = 3; // number of rows and columns
   public static int[][] board = new int[ROWS][COLS];  
   public static int currentState, currentPlayer, currntRow, currentCol; 
   public static Scanner in = new Scanner(System.in);
   public static void main(String[] args) {
      initGame();
      do {
         playerMove(currentPlayer); 
         updateGame(currentPlayer, currntRow, currentCol);
         printBoard();
         if (currentState == X_WON) {
          System.out.println("Player X won!");
         } else if (currentState == O_WON) {
           System.out.println("Player O won!");
         } else if (currentState == DRAW) {
         System.out.println("You both tied!");
         }
        
         currentPlayer = (currentPlayer == X) ? O : X; // changes users
      } while (currentState == PLAYING); // repeat if not game-over
   }
   //Geerthika Senthil
	public static void initGame() {
      for (int row=0; row < ROWS; ++row) { //fill in for loop parameters
         for (int col=0; col < COLS; ++col) {
            board[row][col] = EMPTY;  // all cells empty
         }
      }
      currentState = PLAYING; // ready to play
      currentPlayer = X;  // cross plays first
   }
 //Geerthika Senthil
   public static void playerMove(int userInput) {
      boolean validInput = false;  // for input validation
      do {
         if (userInput == X) {
            System.out.print("Player 'X', enter your move (row[1-3] column[1-3]): Ex. 1 1");
         } else if(userInput == O) {   
            System.out.print("Player 'O', enter your move (row[1-3] column[1-3]): Ex. 1 1 ");
         }
         int row = in.nextInt() - 1;  // array index starts at 0 instead of 1
         int col = in.nextInt() - 1;
         if (row >= 0 && row < ROWS && col >= 0 && col < COLS && board[row][col] == EMPTY) {
            currntRow = row;
            currentCol = col;
            board[currntRow][currentCol] = userInput;  // update game-board content
            validInput = true;  // input okay, exit loop
         } else {
             System.out.print("Your row and column input is invalid!");       
         }
      } while (!validInput);  
   }
   
   public static void updateGame(int userInput, int currentRow, int currentCol) {
      if (hasWon(userInput, currentRow, currentCol)) {  // check if winning move
         currentState = (userInput == X) ? X_WON : O_WON;
      } else if (isDraw()) {  // check for draw
         currentState = DRAW;
      }
   }
 
   //This checks if the player has entered a winning combination
   public static boolean isDraw() {
      for (int row = 0; row < ROWS; ++row) {
         for (int col = 0; col < COLS; ++col) {
            if (board[row][col] == EMPTY) {
               return false;  // an empty cell found, not draw, exit
            }
         }
      }
      return true;  // no empty cell, it's a draw
   }

   public static boolean hasWon(int userInput, int currentRow, int currentCol) {
      return (board[currentRow][0] == userInput         // row
                   && board[currentRow][1] == userInput
                   && board[currentRow][2] == userInput
              || board[0][currentCol] == userInput      // column
                   && board[1][currentCol] == userInput
                   && board[2][currentCol] == userInput
              || currentRow == currentCol            // diagonal
                   && board[0][0] == userInput
                   && board[1][1] == userInput
                   && board[2][2] == userInput
              || currentRow + currentCol == 2  // opposite diagonal
                   && board[0][2] == userInput
                   && board[1][1] == userInput
                   && board[2][0] == userInput);
   }
  //Geerthika Senthil 
   public static void printBoard() {
      for (int row = 0; row < ROWS; ++row) {
         for (int col = 0; col < COLS; ++col) {
            printCell(board[row][col]); // print each of the cells
            if (col != COLS - 1) {
               System.out.print(" |  ");   // print vertical lines
            }
         }
         System.out.println();
         if (row != ROWS - 1) {
            System.out.println("------------------"); // print horizontal lines
         }
      }
      System.out.println();
   }
   
	public static void printCell(int content) {
      switch (content) {
         case EMPTY: 
             System.out.print("   ");
             break;
         case O: 
              System.out.print(" O ");
             break;
         case X:  
              System.out.print(" X ");
             break;
      }
   }
}
//Geerthika Senthil
    
