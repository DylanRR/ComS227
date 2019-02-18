package hw3;

import api.Cell;
import api.CellState;
import api.Descriptor;
import api.Direction;

public class GameSupport
{
	/**
	 * Utility class containing the key algorithms for moves in the
	 * CS227Quell game.  This class is completely stateless.
	 *  @author Dylan (dylanrr)
	 */
	  public GameSupport() {}
	 
	  /**
	   * Shifts the player location in a cell sequence in which no movable blocks
	   * are being moved.  That is, if the sequence contains any movable blocks at all,
	   * they are all at the far right and cannot move further. The first cell
	   * in the array contains the player and the last cell in the array
	   * must be a boundary cell, as defined by the <code>CellState.isBoundary</code>
	   * method.  The player moves only to the right (there is a <code>Direction</code>
	   * parameter, but that is only used to detect whether the player lands on
	   * spikes).
	   * <p>
	   * The player's new location will be one of the following:
	   * <ul>
	   * <li>the cell just before the first movable block in the array, if any, or
	   * <li>the cell just before the boundary, or
	   * <li>the boundary cell itself, in the case that the boundary is a cell
	   * with spikes whose direction is opposite that of the given direction argument
	   * (or is SPIKES_ALL)
	   * </ul>
	   * Any pearls in the sequence are disappeared and any open gates passed
	   * by the player are closed by this method.
	   * <p>
	   * If the descriptors array is non-null, then each descriptor
	   * is updated as follows:
	   * <ul>
	   * <li>The first descriptor's <code>moveTo</code> attribute is set to the
	   * player's new index in the array
	   * <li>All pearls are marked as <code>disappeared</code>
	   * </ul>
	   * The cell objects within each descriptor are not modified.
	   * It should be assumed that the given descriptors array is <em>consistent</em>
	   * with the cells array (allowing for prior movement of movable blocks
	   * from a previous call to <code>shiftMovableBlocks</code>).
	   * @param cells
	   *   a valid cell sequence in which movable blocks, if any, are at the far right only
	   * @param descriptors
	   *   a parallel array of <code>Descriptors</code>, consistent with the cell sequence (possibly null)
	   * @param dir
	   *   direction of the move, for the purpose of determining whether spikes may be deadly
	   */
	  public void shiftPlayer(Cell[] cells, Descriptor[] descriptors, Direction dir){
	    for(int i=0, x=i+1; i < cells.length-1; i++, x++){
	    	if(CellState.isBoundary(cells[x].getState(), false) || cells[x].getState().equals(CellState.MOVABLE_NEG) ||  cells[x].getState().equals(CellState.MOVABLE_POS)){
	    		cells[0].setPlayerPresent(false);
	    		if(CellState.isSpikes(cells[x].getState()) && (CellState.spikesAreDeadly(cells[x].getState(), dir))){
	    			descriptors[0].setMovedToIndex(x);
	    			cells[x].setPlayerPresent(true);
	    			break;
	    		}
	    		descriptors[0].setMovedToIndex(i);
	    		cells[i].setPlayerPresent(true);
	    		break;
	    	}
	    	
	    	if(cells[x].getState().equals(CellState.PEARL)){
	    		cells[x] = new Cell(CellState.EMPTY);
	    		cells[x].setPlayerPresent(cells[x].isPlayerPresent());
	    		descriptors[x].setDisappeared();
	    	}
	    	
	    	if(cells[x].getState().equals(CellState.OPEN_GATE)){
	    		cells[x] = new Cell(CellState.CLOSED_GATE);
	    		cells[x].setPlayerPresent(cells[i+1].isPlayerPresent());
	    	}
	    }
	  }
	  
	  /**
	   * Shifts movable blocks in a cell sequence to the right, if any.  Adjacent movable blocks
	   * with opposite parity are "merged" and removed.  The last cell in the array
	   * must be a boundary cell, as defined by the <code>CellState.isBoundary</code>
	   * method.  If a movable block moves over a pearl (whether or not the block is subsequently removed
	   * due to merging with an adjacent block) then the pearl is also removed.
	   * <p>
	   * If the given array of descriptors is non-null, then it must have the same length as
	   * the cell sequence and the ith descriptor must initially contain a <em>copy</em> of the ith cell
	   * in the <code>cells</code> array.  When the method completes, all descriptors for movable
	   * cells must be updated with the new index of the cell and flagged as disappeared if 
	   * the the cells were merged and removed.  <em>Note that merging is done from the right.</em>  
	   * For example, given a cell sequence represented by ".+-+#", the resulting cell sequence 
	   * would be "...+#", but the descriptors would show positions 2 and 3 as having moved to 
	   * index 4 and disappeared,  and position 1 as having moved to index 4.
	   * @param cells
	   *   given cell sequence
	   * @param descriptors
	   *   parallel array of <code>Descriptors</code> exactly matching the cell sequence, possibly null
	   */
	  public void shiftMovableBlocks(Cell[] cells, Descriptor[] descriptors) 
	  {
		  boolean twoShift = false;
		  
		  do{
			  noMergeShiftHelper(cells, descriptors);
			  
			  twoShift = false;
			  
			  int rightMost = findRightmostMovableCell(cells, cells.length-1);
			  if(rightMost > 0 && rightMost < cells.length){
				  if((cells[rightMost].getState() == CellState.MOVABLE_NEG && cells[rightMost-1].getState() == CellState.MOVABLE_POS) || (cells[rightMost].getState() == CellState.MOVABLE_POS && cells[rightMost-1].getState() == CellState.MOVABLE_NEG)){
					  cells[rightMost] = new Cell(CellState.EMPTY);
					  cells[rightMost-1] = new Cell(CellState.EMPTY);
					  twoShift = true;  
					  if(descriptors != null){
						  descriptors[rightMost].setDisappeared();
						  descriptors[rightMost-1].setDisappeared();
					  }
				  }
			  }  
		  } 
		  while(twoShift);  
	  }
	  
	  private void noMergeShiftHelper(Cell[] cells, Descriptor[] descriptors){
		  int end = cells.length-1;
		  
		  while(findRightmostMovableCell(cells, end-1) >= 0){
			  int shift = findRightmostMovableCell(cells, end-1);
			  for(int i=shift, x=i-1; i < end; i++, x++){
				  if(cells[i].getState() == CellState.PEARL){
					  cells[i] = new Cell(CellState.EMPTY);
					  if(descriptors != null)
						  descriptors[i].setDisappeared();
				  }
				  if(CellState.isBoundary(cells[x].getState(), true) || cells[x].getState().equals(CellState.MOVABLE_NEG) || cells[x].getState().equals(CellState.MOVABLE_POS)){
					  cells[shift] = new Cell(CellState.EMPTY);
					  cells[i] = new Cell(cells[shift].getState());
					  end=i;
					  if(descriptors != null)
						  descriptors[shift].setMovedToIndex(i);
					  break;
				  }
			  }
		  }
	  }
	  
	  /**
	   * Returns the index of the rightmost movable block that is at or
	   * to the left of the given index <code>start</code>.  Returns -1 if
	   * there is no movable block at <code>start</code> or to the left.
	   * @param cells
	   *   array of Cell objects
	   * @param start
	   *   starting index for searching
	   * @return
	   *   index of first movable block encountered when searching towards
	   *   the left starting from the given starting index, or -1 if there
	   *   is no such cell
	   */
	 public int findRightmostMovableCell(Cell[] cells, int start){
			if(start >= cells.length)
			    return -1;
			if(start < 0)
				return -1;
			for(int i=start; i >= 0; i--){
				if(CellState.isMovable(cells[i].getState()))
					return i;
			}
			return -1;
	  }
	  
	
}