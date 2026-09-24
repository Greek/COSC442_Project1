package edu.towson.cis.cosc442.project1.monopoly;

public abstract class Cell {
	private boolean available = true;
	private String name;
	protected Player theOwner;

	/**
	 * Returns the name of the cell.
	 * @return the name of the cell
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the current owner of the cell.
	 * @return the Player who owns the cell, or null if unowned
	 */
	public Player getTheOwner() {
		return theOwner;
	}
	
	/**
	 * Returns the price of the cell, defaulting to zero.
	 * @return the price of the cell
	 */
	public int getPrice() {
		return 0;
	}

	/**
	 * Indicates whether the cell is available to be acquired.
	 * @return true if the cell is available, false otherwise
	 */
	public boolean isAvailable() {
		return available;
	}
	
	/**
	 * Executes the action associated with landing on this cell.
	 */
	public abstract void playAction();

	/**
	 * Sets the availability status of the cell.
	 * @param available the new availability status to set
	 */
	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	/**
	 * Sets the name of the cell.
	 * @param name the new name to assign to the cell
	 */
	void setName(String name) {
		this.name = name;
	}

	/**
	 * Assigns the owner of the cell.
	 * @param owner the Player who will own the cell
	 */
	public void setTheOwner(Player owner) {
		this.theOwner = owner;
	}
    
    /**
     * Returns the string representation of the cell.
     * @return the name of the cell as its string representation
     */
    public String toString() {
        return name;
    }
}
