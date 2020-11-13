/** A chutes and ladders game */
public class ChutesAndLadders {
  /** the board is an array of int, each int value is where the player moves to */
  private int[] board;
  
  /** The players are an array of int, each int value is the player location */
  private int[] players;
  
  /** 
    * Create the ChutesAndLadders game
    * @param numPlayers  the number of players in the game
    */
  public ChutesAndLadders(int numPlayers) {
    board = new int[101];
    players = new int[numPlayers];
    
    // a square stores its own value to indicate the players stay here
    for (int i = 0; i < board.length; i++) 
      board[i] = i;
    
    // create the ladders
    board[1] = 38;
    board[4] = 14;
    board[9] = 31;
    board[28] = 84;
    board[21] = 42;
    board[80] = 100;
    
    // create the chutes
    board[92] = 73;
    board[16] = 6;
    board[87] = 24;
    board[62] = 19;
    board[49] = 11;
    board[98] = 78;
    
  }
  
  /**
   * Determines if a player has won the game
   * @param player  the player to check
   * @param players the list of players
   * @returns true if the player has won and false if not
   */
  public boolean isWinner(int player, int[] players) {
    return players[player] == board.length - 1;
  }
  
  /**
   * Moves a player on a Chutes and Ladder board
   * @param player the player to move
   * @param roll  the amount to move the player
   * @param playerLocations an array with all the player positions
   * @param board  a game board of Chutes and Ladders
   * @throws Exception if the roll takes the player off the board
   */
  public void movePlayer(int player, int roll, int[] playerLocations, int[] board) {
    try {
      int currentSpace = playerLocations[player];
      int nextSpace = currentSpace + roll;
      int finalSpace = board[nextSpace];
      playerLocations[player] = finalSpace;
    }
    catch (ArrayIndexOutOfBoundsException e) { 
       // thrown if the die roll takes a player past "100"
       // in that case, the player skips their turn
    }
  }
  
  /**
   * Gets the next player to play
   * @param currentPlayer the current player
   * @param players the list of players
   * @returns the next player to play
   */
  public int nextPlayer(int currentPlayer, int[] players) {
    return (currentPlayer + 1) % players.length;
  }
  
  /**
   * Play the Chutes and Ladders game
   * @returns the player that won the game
   */
  public int playGame() {
    int currentPlayer = 0;
    Die d = new Die(6);
    
    // until we have a winner, move the player and change players (maybe use a do-while loop?)
    while (!isWinner(currentPlayer, players)) {
      movePlayer(currentPlayer, d.roll(), players, board);
      currentPlayer = nextPlayer(currentPlayer, players);
    }
    return currentPlayer;
  }
  
  /**
   * Launches the Chutes and Ladders game
   * @param args the command line arguments are currently ignored.  Maybe we can let them enter the number of players?
   */
  public static void main(String[] args) {
    ChutesAndLadders game = new ChutesAndLadders(4);
    int winner = game.playGame();
    System.out.println("The winner is player #" + (winner + 1));
  }
  
}
