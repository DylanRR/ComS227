package hw3;
import static api.CellState.MOVABLE_NEG;
import static api.CellState.MOVABLE_POS;
import static api.CellState.PEARL;
import static api.CellState.PORTAL;

import java.util.ArrayList;

import api.Cell;
import api.CellState;
import api.Descriptor;
import api.Direction;
import api.StringUtil;

/**
 * Basic game state and operations for a simplified version of the video game 
 * "Quell".
 * @author Dylan (dylanrr)
 */
public class CS227Quell
{
	/**
	* Two-dimensional array of Cell objects representing the 
	* grid on which the game is played.
	*/
	private Cell[][] grid;
	
	/**
	 * Private variables to keep track of score, move count, last row, last column and initialize a gameSupport to be used in move
	 */
	private GameSupport support;  
	private int score = 0;
	private int moveCount = 0;
	private int lastRow;
	private int lastColumn;

  
  /**
   * Constructs a game from the given string description.  The conventions
   * for representing cell states as characters can be found in 
   * <code>StringUtil</code>.  
   * @param init
   *   string array describing initial cell states
   * @param support
   *   GameSupport instance to use in the <code>move</code> method
   */
  public CS227Quell(String[] init, GameSupport support){
    grid = StringUtil.createFromStringArray(init);
    this.support = support;
  }
  
  /**
   * Returns the number of columns in the grid.
   * @return
   *   width of the grid
   */
  public int getColumns(){
    return grid[0].length;
  }
  
  /**
   * Returns the number of rows in the grid.
   * @return
   *   height of the grid
   */
  public int getRows(){
    return grid.length;
  }
  
  /**
   * Returns the cell at the given row and column.
   * @param row
   *   row index for the cell
   * @param col
   *   column index for the cell
   * @return
   *   cell at given row and column
   */
  public Cell getCell(int row, int col){
    return grid[row][col];
  }
  
  /**
   * Returns true if the game is over, false otherwise.  The game ends when all pearls
   * are removed from the grid or when the player lands on a cell with spikes.
   * @return
   *   true if the game is over, false otherwise
   */
  public boolean isOver(){
    for(int i=0; i < grid.length; i++){
    for(int x =0; x < grid[0].length; x++){
    	if(grid[i][x].isPlayerPresent())
    		if(CellState.isSpikes(getCell(i, x).getState()))
    			return true;		
    		if(grid[i][x].getState().equals(CellState.PEARL))
    			return false;
    	}
    }
    return true;
  }
  
  
  
  
  
  
  
  
  /**
   *Finds a valid cell sequence in the given direction starting with the player's current position and ending with a boundary cell
   * as defined by the method CellState.isBoundary. The actual cell locations are obtained by following getNextRow and getNextColumn 
   * in the given direction, and the sequence ends when a boundary cell is found. A boundary cell is defined by the CellState.isBoundary and 
   * is different depending on whether a movable block has been encountered so far in the cell sequence (the player can move through open 
   * gates and portals, but the movable blocks cannot). It can be assumed that there will eventually be a boundary cell (i.e., the grid has no infinite loops). 
   * The first element of the returned array is the cell containing the player, and the last element of the array is the boundary cell. 
   * This method does not modify the grid or any aspect of the game state.
   * @param direction of the sequence
   * @return array of cells in the cell sequence
   */
  public Cell[] getCellSequence(Direction dir)
  {
	  int row = getCurrentRow();
	  int column = getCurrentColumn();
	  
	  lastRow = row;
	  lastColumn = column;
	  
	  ArrayList<Cell> newCells = new ArrayList<>();
	  
	  int rowColDir;
	  if (dir.equals(Direction.DOWN) || dir.equals(Direction.UP))
		  rowColDir = row;
	  else
		  rowColDir = column;

	  int noChange;
	  if (rowColDir == column)
		  noChange = row;
	  else
		  noChange = column;
	  
	  Cell mainCell = null;
	  boolean mainPortal = false;
	  
	  do
	  {
		  int tempX;
		  int tempY;
		  if (dir.equals(Direction.DOWN) || dir.equals(Direction.UP))
			  tempX = rowColDir;
		  else
			  tempX = noChange;
		  if (dir.equals(Direction.RIGHT) || dir.equals(Direction.LEFT))
			  tempY = rowColDir;
		  else
			  tempY = noChange;
		  
		  mainCell = getCell(tempX, tempY);
		  newCells.add(mainCell);
  		
		  
		  if (dir.equals(Direction.DOWN) || dir.equals(Direction.UP))
			  rowColDir = getNextRow(rowColDir, noChange, dir, mainCell.getState().equals(CellState.PORTAL) && !mainPortal);
		  else
			  rowColDir = getNextColumn(noChange, rowColDir, dir, mainCell.getState().equals(CellState.PORTAL) && !mainPortal);
		  
		  if (mainCell.getState().equals(CellState.PORTAL) && !mainPortal)
			  if (dir.equals(Direction.DOWN) || dir.equals(Direction.UP))
				  noChange = noChange + mainCell.getColumnOffset();
			  else
				  noChange = noChange +  mainCell.getColumnOffset();
  					
		if(mainPortal)
  			mainPortal = false;
		else
			mainPortal = mainCell.getState().equals(CellState.PORTAL);
  				
	  }
	  while(CellState.isBoundary(mainCell.getState(), false) == false);
	  
	  return newCells.toArray(new Cell[newCells.size()]);
  }
  
  /**
   * Sets the given cell sequence and updates the player position. This method effectively retraces the steps for creating 
   * a cell sequence in the given direction, starting with the player's current position, and updates the grid with the new cells. 
   * Exactly one cell in the given sequence must have the condition isPlayerPresent true. The given cell sequence can be assumed 
   * to be structurally consistent with the existing grid, e.g., no portal or wall cells are moved.
   * @param cells Cells to replace
   * @param dir Direction to move in
   */
  public void setCellSequence(Cell[] cells, Direction dir)
  {
	  int rowColDir;
	  if (dir.equals(Direction.DOWN) || dir.equals(Direction.UP))
		  rowColDir = lastRow;
	  else
		  rowColDir = lastColumn;
	  
	  int noChange;
	  if (rowColDir == lastColumn)
		  noChange = lastRow;
	  else
		  noChange = lastColumn;
	  boolean mainPortal = false;
	  
	  for(Cell selected : cells){
		  if (dir.equals(Direction.DOWN) || dir.equals(Direction.UP))
			  grid[rowColDir][noChange] = selected;
		  else
			  grid[noChange][rowColDir] = selected; 
  		
		  if (dir.equals(Direction.DOWN) || dir.equals(Direction.UP))
			  rowColDir = getNextRow(rowColDir, noChange, dir, selected.getState().equals(CellState.PORTAL) && !mainPortal);
		  else
			  rowColDir = getNextColumn(noChange, rowColDir, dir, selected.getState().equals(CellState.PORTAL) && !mainPortal);
  		
		  if (selected.getState().equals(CellState.PORTAL) && !mainPortal)
			  if(dir.equals(Direction.DOWN) || dir.equals(Direction.UP))
				  noChange = noChange + selected.getColumnOffset();
			  else
				  noChange = noChange + selected.getRowOffset();
		  	
  		if(mainPortal)
  			mainPortal = false;
  		else
  			mainPortal = selected.getState().equals(CellState.PORTAL);
  				
	  }
  }
  
 
  
  /**
   * Helper method returns the next column for a cell sequence in the given direction, possibly wrapping around. 
   * If the flag doPortalJump is true, then the next column will be obtained by adding the cell's column offset. 
   * (Normally the caller will set this flag to true when first landing on a portal, but to false for the second portal of the pair.)
   * @param row current row
   * @param col current columns
   * @param dir direction to move in
   * @param doPortalJump true if the current move needs to be based on whether previous cell was a portal
   * @return Returns the next column that the player should interact with.
   */
  public int getNextColumn(int row, int col, Direction dir, boolean doPortalJump)
  {
	  if(dir != Direction.RIGHT && dir != Direction.LEFT)
		  return col;
	  
	  if (doPortalJump)
		  return col + getCell(row, col).getColumnOffset();
	  else if(col+1 == getColumns())
		  return 0;
	  else if(dir == Direction.RIGHT)
		  return ++col;
	  else if(col-1 < 0)
		  return grid[row].length-1;
	  else
		  return --col;
	 
  }
  
  /**
   * Helper method returns the next row for a cell sequence in the given direction, possibly wrapping around. 
   * If the flag doPortalJump is true, then the next row will be obtained by adding the cell's row offset. 
   * (Normally the caller will set this flag to true when first landing on a portal, but to false for the second portal of the pair.)
   * @param row current row
   * @param col current columns
   * @param dir direction to move in
   * @param doPortalJump true if the current move needs to be based on whether previous cell was a portal
   * @return Returns the next row that the player should interact with.
   */
  public int getNextRow(int row, int col, Direction dir, boolean doPortalJump)
  {
	  if(dir != Direction.DOWN && dir != Direction.UP)
		  return row;
	  
	  if (doPortalJump)
		  return row + getCell(row, col).getRowOffset();
	  else if (row + 1 == getRows() && dir == Direction.DOWN)
		  return 0;
	  else if (dir == Direction.DOWN)
		  return ++row;
	  else if (row - 1 < 0)
		  return grid.length - 1;
	  else
		  return --row;

  }
  
  
  
  
  /**
   * Returns the current score (number of pearls disappeared) for this game.
   * @return current score
   */
  public int getScore()
  {
	  return score;
  }
  
  /**
   * Returns the current number of moves made in this game.
   * @return number of moves made
   */
  public int getMoves()
  {
	  return moveCount;
  }
  
  /**
   * Returns the row index for the player's current location.
   * @return current row index for the player
   */
  public int getCurrentRow(){
	  for(int i = 0; i < grid.length; i++)
		  for(int x = 0; x < grid[i].length; x++)
			  if(grid[i][x].isPlayerPresent())
				  return i;
	  return -1;
  }
  
  /**
   * Returns the column index for the player's current location.
   * @return current column index for the player
   */
  public int getCurrentColumn(){
	  for(int i = 0; i < grid.length; i++)
		  for(int x = 0; x < grid[i].length; x++)
			  if(grid[i][x].isPlayerPresent())
				  return x;
	  return -1;
  }
  
  /**
   * Returns the number of pearls left in the grid.
   * @return number of pearls in the grid
   */
  public int countPearls(){
	  int pearlCount = 0;
	  for(Cell[] cell1 : grid)
		  for(Cell cell2 : cell1)
			  if(cell2.getState().equals(CellState.PEARL))
				  pearlCount++;
	  return pearlCount;
  }
  
  
  
  /**
   * Returns true if the game is over and the player did not die on spikes.
   * @return true if the player wins, false otherwise
   */
  public boolean won(){
	  if(isOver()){
		  for(int i = 0; i < grid.length; i++)
			  for(int x = 0; x < grid[0].length; x++)
				  if(grid[i][x].isPlayerPresent()){
		    			Cell mainCell = getCell(i, x);
		    			if(CellState.isSpikes(mainCell.getState()))
		    				return false;		
		    		}
				  else
					  return true;
	  }
	  return false;
  }
  
  /**
   * Performs a move along a cell sequence in the given direction, updating the score, 
   * the move count, and all affected cells in the grid.  The method returns an 
   * array of Descriptor objects representing the cells in original cell sequence before 
   * modification, with their <code>movedTo</code> and <code>disappeared</code>
   * status set to indicate the cells' new locations after modification.  
   * @param dir
   *   direction of the move
   * @return
   *   array of Descriptor objects describing modified cells
   */
  public Descriptor[] move(Direction dir)
  {
    Cell[] mainSequence = getCellSequence(dir);
    Descriptor[] descript = new Descriptor[mainSequence.length];
    
    for(int i=0; i < mainSequence.length; i++)
    	descript[i] = new Descriptor(mainSequence[i], i);
    
    support.shiftMovableBlocks(mainSequence, descript);
    Descriptor[] descriptCopy = descript.clone();
    
    support.shiftPlayer(mainSequence, descript, dir);
    if(descript[0].isMoved())
    	this.moveCount++;

    if(descript[0].isDisappeared() || !descriptCopy[0].isDisappeared())
    	this.score++;
    
    setCellSequence(mainSequence, dir);
    return descript;
  }
  
}