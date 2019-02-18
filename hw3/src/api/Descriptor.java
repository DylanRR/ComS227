
package api;

/**
 * A Descriptor encapsulates a Cell along with information about how it might
 * have been shifted or modified during a move in the game.
 * @author smkautz
 */
public class Descriptor
{
  /**
   * Original cell to be described by this descriptor.
   */
  private final Cell cell;
  
  /**
   * Original index for the cell within a cell sequence array (as defined in 
   * <code>CS227Quell#getCellSequence</code>). 
   */
  private final int index;
  
  /**
   * Updated index for the cell if it was moved.  (Note: if the cell originally
   * contained the player, then the movedTo value is index of the cell containing
   * the player, rather than the new index of the cell.)
   */
  private int movedTo;
  
  /**
   * Indicates whether the cell was removed by the move.
   */
  private boolean disappeared;

  /**
   * Constructs a Descriptor with the given Cell and index.  Normally the 
   * index is the same as the cell's array index within a cell sequence, as defined in
   * <code>CS227Quell#getCellSequence</code>.
   * @param givenCell
   *   Cell for this descriptor
   * @param currentIndex
   *   index for this descriptor
   */
  public Descriptor(Cell givenCell, int currentIndex)
  {
    cell = new Cell(givenCell);
    index = currentIndex;
    movedTo = index;
    disappeared = false;
  }

  /**
   * Returns the cell in this Descriptor.
   * @return
   *   cell in this Descriptor
   */
  public Cell getCell()
  {
    return cell;
  }

  /**
   * Returns the index in this Descriptor.
   * @return
   *   index in this Descriptor
   */
  public int getIndex()
  {
    return index;
  }

  /**
   * Sets the new index to indicate a cell moved to a new location within 
   * a cell sequence.  In the case of a cell containing the player, the
   * new index indicates the new location of the player in the cell sequence.
   * @param newIndex
   *   new index for the current cell (or player)
   */
  public void setMovedToIndex(int newIndex)
  {
    this.movedTo = newIndex;
  }
  
  /**
   * Returns the (possibly) new index for this descriptor's cell.
   * @return
   *   new index for this descriptor's cell
   */
  public int getMovedTo()
  {
    return movedTo;
  }

  /**
   * Determines whether this cell has been given a new index.
   * @return
   *   true if the new index differs from the original index
   */
  public boolean isMoved()
  {
    return movedTo != index;
  }
  
  /**
   * Determines whether this cell was deleted.
   * @return
   *   true if this cell was deleted, false otherwise
   */
  public boolean isDisappeared()
  {
    return disappeared;
  }
  
  /**
   * Sets the disappeared status of this Descriptor.
   */
  public void setDisappeared()
  {
    disappeared = true;
  }

  /**
   * Returns a string representation of this Descriptor.
   */
  public String toString()
  {
    String player = cell.isPlayerPresent() ? " ($)" : "";
    String moved = index != movedTo ? " moved to " + movedTo : "";
    String invisible = disappeared ? " disappeared" : "";
    String ret = "[";
    ret += StringUtil.getChar(cell.getState()) + player + moved + invisible + "]";
    return ret;
  }
  
}
