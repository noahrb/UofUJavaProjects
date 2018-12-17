package c4;

/**
 * Represents the state of a Connect Four board on which multiple games can be played. The board consists of a
 * rectangular grid of squares in which playing pieces can be placed. Squares are identified by their zero-based row and
 * column numbers, where rows are numbered starting with the bottom row, and columns are numbered by starting from the
 * leftmost column.
 * 
 * Multiple games can be played on a single board. Whenever a new game begins, the board is empty. There are two
 * players, identified by the constants P1 (Player 1) and P2 (Player 2). P1 moves first in the first game, P2 moves
 * first in the second game, and so on in alternating fashion.
 * 
 * A C4Board also keeps track of the outcomes of the games that have been played on it. It tracks the number of wins by
 * P1, the number of wins by P2, and the number of ties. It does not track games that were abandoned before being
 * completed.
 */
public class C4Board
{
    /** The number used to indicate Player 1 */
    public static final int P1 = 1;

    /** The number used to indicate Player 2 */
    public static final int P2 = 2;

    /** The number used to indicate a tie */
    public static final int TIE = 3;

    /** Number used to indicate who's turn it is */
    private int turn = P1;
    
    /** Used to hold specifications of the C4Board and holds tokens. */
    private int board[][];
    
    /** The number used to indicate the status of the game. 0 if the game is active, 1 if the game is over b/c of a win or a tie. */
    private int gameStatus;
    
    /** The number used to indicate the number of games started. */
    private int gameNumber;
    
    /** Number used to count P1's wins. */
    private int p1WinCount;
    
    /** Number used to count P2's wins. */
    private int p2WinCount;
    
    /** Number used to count number of TIES */
    private int tieCount;
    
    /** Number used to count tiles created since creation of the board */
    private int tileCount;

    /**
     * Creates a C4Board with the specified number of rows and columns. There should be no pieces on the board and it
     * should be player 1's turn to move.
     * 
     * However, if either rows or cols is less than four, throws an IllegalArgumentException.
     */
    public C4Board (int rows, int cols)
    {
        if (rows < 4 || cols < 4)
        {
            throw new IllegalArgumentException();
        }
        board = new int[rows][cols];
        
        for (int index = 0; index < board.length; index++)
        {
            for (int inner = 0; inner < board[index].length; inner++)
            {
                board[index][inner] = 0;
            }
        }
        
        gameStatus = 0;
        gameNumber = 0;
        tileCount = 0;

    }

    /**
     * Sets up the board to play a new game, whether or not the current game is complete. All of the pieces should be
     * removed from the board. The player who had the second move in the previous game should have the first move in the
     * new game.
     */
    public void newGame ()
    {
        gameNumber = gameNumber + 1;
        if (gameNumber % 2 == 0)
        {
            turn = P2;
        }
        else
        {
            turn = P1;
        }               
        for (int index = 0; index < board.length; index++)
        {
            for (int inner = 0; inner < board[index].length; inner++)
            {
                board[index][inner] = 0;
            }
        }
    }

    /**
     * Records a move, by the player whose turn it is to move, in the first open square in the specified column. Returns
     * P1 if the move resulted in a win for player 1, returns P2 if the move resulted in a win for player 2, returns TIE
     * if the move resulted in a tie, and returns 0 otherwise.
     * 
     * If the column is full, or if the game is over because a win or a tie has previously occurred, does nothing but
     * return zero.
     * 
     * If a non-existent column is specified, throws an IllegalArgumentException.
     */
    public int moveTo (int col)
    {
        boolean isFull = true;
        /** Checks for full board */
        for (int index = 0; index < board.length; index++)
        {
            for (int inner = 0; inner < board[index].length; inner++)
            {
                if(board[index][inner] != P1 && board[index][inner] != P2)
                {
                    isFull = false;
                    return 0;
                }
            }
        }
        
        /** Checks if the game is inactive(winner has been selected), if so, returns 0. */
        if(gameStatus != 0)
        {
            return 0;
        }
//        
//        int g = board[0].length;
//        g = 1;
//        System.out.println(g);
        /** Puts the turn value in the first position of the column. */
        for (int i = 0; i < board[0].length; i++)
        {
            if (board[i][col] == 0)
            {
                   board[i][col] = turn;
                   System.out.println(board[i][col]);
                   tileCount = tileCount + 1;
                   break;
            }
        }

        if(FourInARow.fourInRow(board))
        {
            gameStatus = 1;
            if(turn == P1)
            {
                p1WinCount = p1WinCount + 1;
            }
            if(turn == P2)
            {
                p2WinCount = p2WinCount + 1;
            }
            return turn;
        }
        
        if(FourInARow.fourInRow(board) == false && isFull == true)
        {
            gameStatus = 1;
            tieCount = tieCount + 1;
            return TIE;
        }
        if (turn == P1)
        {
            turn = P2;
        }
        if (turn == P2)
        {
            turn = P1;
        }
        return 0;
    }

    /**
     * Reports the occupant of the board square at the specified row and column. If the row or column doesn't exist,
     * throws an IllegalArgumentException. If the square is unoccupied, returns 0. Otherwise, returns P1 (if the square
     * is occupied by player 1) or P2 (if the square is occupied by player 2).
     */
    public int getOccupant (int row, int col)
    {
        //checks to see if row or col is bigger then the board from constructor, 
        if(board.length >= row && board[row].length >= col)
        {
            if(board[row][col] == 0)
            {
                return 0;
            }
            if(board[row][col] == P1)
            {
                return P1;
            }
            if(board[row][col] == P2)
            {
                return P2;
            }
        }
        else
        {
            throw new IllegalArgumentException();
        }
        return 0;
    }

    /**
     * Reports whose turn it is to move. Returns P1 (if it is player 1's turn to move), P2 (if it is player 2's turn to
     * move, or 0 (if it is neither player's turn to move because the current game is over because of a win or tie).
     */
    public int getToMove ()
    {
        return turn;
    }

    /**
     * Reports how many games have been won by player 1 since this board was created.
     */
    public int getWinsForP1 ()
    {
        return p1WinCount;
    }

    /**
     * Reports how many games have been won by player 2 since this board was created.
     */
    public int getWinsForP2 ()
    {
        return p2WinCount;
    }

    /**
     * Reports how many ties have occurred since this board was created.
     */
    public int getTies ()
    {
        return tieCount;
    }
    
    public int getTileCount()
    {
        return tileCount;
    }
}
