package turing;

/**
 * @author Asus
 *
 */
public class Tape {

	private static Cell rootCell;
	private static Cell currentCell;
//	private String contentString;

	public Tape() {
		Cell nCell = new Cell();
		nCell.content = ' ';
		currentCell = nCell;
		rootCell = currentCell;
	}

	/**
	 * set the content of current cell
	 * 
	 * @param charAt
	 */
	public void setContent(char charAt) {
		// TODO Auto-generated method stub
		if (currentCell == null) {
			return;
		} else {
			currentCell.content = charAt;
		}

	}

	public void moveRight() {
		// TODO Auto-generated method stub
		if (currentCell.next == null) {
			Cell rightCell = new Cell();
			rightCell.content = ' ';
			insertCell(rootCell, rightCell, false);
			currentCell = rightCell;
		} else {
			currentCell = currentCell.next;
		}
	}

	/**
	 * moves the current cell one position to the left along the tape.
	 */
	public void moveLeft() {
		// TODO Auto-generated method stub
		if (currentCell.prev == null) {
			Cell lfetCell = new Cell();
			lfetCell.content = ' ';
//			currentCell.prev = lfetCell;
			insertCell(rootCell, lfetCell, true);
			currentCell = lfetCell;
		} else {
			currentCell = currentCell.prev;
		}
	}

	/**
	 * returns the pointer that points to the current cell.
	 * 
	 * @return
	 */
	public Cell getCurrentCell() {
		return currentCell;
	}

	/**
	 * returns a String consisting of the chars from all the cells on the tape, read
	 * from left to right, except that leading or trailing blank characters should
	 * be discarded.
	 * 
	 * @return
	 */
	public String getTapeContents() {
		// TODO Auto-generated method stub
//		String content = null;
		if (currentCell == null) {
			return null;
		} else {
			return this.getTapeContents(rootCell);
		}
	}

	private String getTapeContents(Cell cCell) {
		if (cCell == null) {
			return "";
		} else {
			String content = "";
			content += getTapeContents(cCell.prev);
			content += String.valueOf(cCell.content);
			content += getTapeContents(cCell.next);
			return content;
		}
	}

	public char getContent() {
		// TODO Auto-generated method stub
		if (currentCell == null) {
			return ' ';
		} else {
			return currentCell.content;
		}
	}

	/**
	 * insert the new cell into the tape
	 * 
	 * @param root
	 * @param newCell
	 * @param left
	 */
	private void insertCell(Cell root, Cell newCell, boolean left) {
		if (root == null) {
			root = newCell;
			return;
		}

		Cell runnerCell = root;
		if (left) {
			while (true) {
				if (runnerCell.prev == null) {
					runnerCell.prev = newCell;
					return;
				} else {
					runnerCell = runnerCell.prev;
				}

			}
		} else {
			while (true) {
				if (runnerCell.next == null) {
					runnerCell.next = newCell;
					return;
				} else {
					runnerCell = runnerCell.next;
				}
			}

		}
	}

}
