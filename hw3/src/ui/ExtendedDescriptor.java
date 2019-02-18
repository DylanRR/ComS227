package ui;

import api.Cell;
import api.Descriptor;

/**
 * Descriptor for a Cell in a cell sequence that includes the 
 * actual grid row and column.
 * @author smkautz
 *
 */
public class ExtendedDescriptor
{
  private Cell cell;
  private int index;
  private int newIndex;
  private boolean disappeared; 
  private int oldCol;
  private int newCol;
  private int oldRow;
  private int newRow;

  public ExtendedDescriptor(Descriptor d)
  {
    cell = d.getCell();
    index = d.getIndex();
    newIndex = d.getMovedTo();
    disappeared = d.isDisappeared();
  }
  
  public Cell getCell()
  {
    return cell;
  }

  public int getIndex()
  {
    return index;
  }

  public int getNewIndex()
  {
    return newIndex;
  }

  public boolean isDisappeared()
  {
    return disappeared;
  }
  
  public void setDisappeared()
  {
    disappeared = true;
  }
  
  public int getOldRow()
  {
    return oldRow;
  }

  public int getOldCol()
  {
    return oldCol;
  }

  public int getNewRow()
  {
    return newRow;
  }
  
  public int getNewCol()
  {
    return newCol;
  }

  public void setOldRowCol(int oldRow, int oldCol)
  {
    this.oldRow = oldRow;
    this.oldCol = oldCol;
  }

  public void setNewRowCol(int newRow, int newCol)
  {
    this.newRow = newRow;
    this.newCol = newCol;
  }

  public String toString()
  {
    String player = cell.isPlayerPresent() ? "$" : "";
    String ret = "[";
    ret += cell.getState().toString() + player + ",";
    ret += " old (" + oldRow + ", " + oldCol + ") ";
    ret += " new (" + newRow + ", " + newCol + ")]";
        return ret;
  }
}
