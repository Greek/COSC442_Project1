package edu.towson.cis.cosc442.project1.monopoly;

import java.util.ArrayList;
import java.util.Hashtable;

public class GameBoard {

	private ArrayList<Cell> cells = new ArrayList<Cell>();
    private ArrayList<Card> chanceCards = new ArrayList<Card>();
	//the key of colorGroups is the name of the color group.
	private Hashtable<String, Integer> colorGroups = new Hashtable<String, Integer>();
	private ArrayList<Card> communityChestCards = new ArrayList<Card>();
	/**
	 * Initializes a GameBoard with the starting "Go" cell added.
	 */
	public GameBoard() {
		Cell go = new GoCell();
		addCell(go);
	}

    /**
     * Adds a card to the appropriate deck based on its type (Community Chest or Chance).
     * @param card the Card object to add to the deck
     */
    public void addCard(Card card) {
        if(card.getCardType() == Card.TYPE_CC) {
            communityChestCards.add(card);
        } else {
            chanceCards.add(card);
        }
    }
	
	/**
	 * Adds a cell to the game board's list of cells.
	 * @param cell the Cell object to add to the board
	 */
	public void addCell(Cell cell) {
		cells.add(cell);
	}
	
	/**
	 * Adds a property cell to the board and updates the color group property count accordingly.
	 * @param cell the PropertyCell object to add
	 */
	public void addCell(PropertyCell cell) {
		String cellColorGroup = cell.getColorGroup();
		int propertyNumber = getPropertyNumberForColor(cellColorGroup);
		colorGroups.put(cellColorGroup, new Integer(propertyNumber + 1));
        cells.add(cell);
	}

    /**
     * Draws the top Community Chest card, removes it from the deck, then re-adds it to the deck's bottom.
     * @return the drawn Community Chest Card
     */
    public Card drawCCCard() {
        Card card = (Card)communityChestCards.get(0);
        communityChestCards.remove(0);
        addCard(card);
        return card;
    }

    /**
     * Draws the top Chance card, removes it from the deck, then re-adds it to the deck's bottom.
     * @return the drawn Chance Card
     */
    public Card drawChanceCard() {
        Card card = (Card)chanceCards.get(0);
        chanceCards.remove(0);
        addCard(card);
        return card;
    }

	/**
	 * Retrieves the cell at the specified index from the game board.
	 * @param newIndex the index of the cell to retrieve
	 * @return the Cell at the given index
	 */
	public Cell getCell(int newIndex) {
		return (Cell)cells.get(newIndex);
	}
	
	/**
	 * Returns the total number of cells currently on the game board.
	 * @return the number of cells on the board
	 */
	public int getCellNumber() {
		return cells.size();
	}
	
	/**
	 * Returns an array of PropertyCell objects that belong to the specified color group (monopoly).
	 * @param color the color group name to filter properties by
	 * @return an array of properties in the specified color group
	 */
	public PropertyCell[] getPropertiesInMonopoly(String color) {
		PropertyCell[] monopolyCells = 
			new PropertyCell[getPropertyNumberForColor(color)];
		int counter = 0;
		for (int i = 0; i < getCellNumber(); i++) {
			Cell c = getCell(i);
			if(c instanceof PropertyCell) {
				PropertyCell pc = (PropertyCell)c;
				if(pc.getColorGroup().equals(color)) {
					monopolyCells[counter] = pc;
					counter++;
				}
			}
		}
		return monopolyCells;
	}
	
	/**
	 * Gets the count of properties for a given color group name.
	 * @param name the color group name
	 * @return the number of properties in the specified color group
	 */
	public int getPropertyNumberForColor(String name) {
		Integer number = (Integer)colorGroups.get(name);
		if(number != null) {
			return number.intValue();
		}
		return 0;
	}

	/**
	 * Searches for and returns the first cell with a matching name, or null if none found.
	 * @param string the name of the cell to find
	 * @return the Cell with the matching name, or null if not found
	 */
	public Cell queryCell(String string) {
		for(int i = 0; i < cells.size(); i++){
			Cell temp = (Cell)cells.get(i); 
			if(temp.getName().equals(string)) {
				return temp;
			}
		}
		return null;
	}
	
	/**
	 * Finds and returns the index of the cell with the matching name, or -1 if not found.
	 * @param string the name of the cell to locate
	 * @return the index of the matching cell, or -1 if not found
	 */
	public int queryCellIndex(String string){
		for(int i = 0; i < cells.size(); i++){
			Cell temp = (Cell)cells.get(i); 
			if(temp.getName().equals(string)) {
				return i;
			}
		}
		return -1;
	}

    /**
     * Removes all Community Chest cards from the deck, clearing it.
     */
    public void removeCards() {
        communityChestCards.clear();
    }
}
