package edu.towson.cis.cosc442.project1.monopoly;

public class PropertyCell extends Cell {
	private String colorGroup;
	private int housePrice;
	private int numHouses;
	private int rent;
	private int sellPrice;

	/**
	 * Returns the color group to which this property belongs.
	 * @return The color group of the property as a String.
	 */
	public String getColorGroup() {
		return colorGroup;
	}

	/**
	 * Returns the cost to purchase one house on this property.
	 * @return The price of a single house for this property.
	 */
	public int getHousePrice() {
		return housePrice;
	}

	/**
	 * Returns the current number of houses built on this property.
	 * @return The number of houses currently on the property.
	 */
	public int getNumHouses() {
		return numHouses;
	}
    
    /**
     * Returns the selling price of this property.
     * @return The sell price of the property.
     */
    public int getPrice() {
		return sellPrice;
	}

	/**
	 * Calculates and returns the rent due based on ownership status, houses built, and monopolies.
	 * @return The amount of rent that must be paid on this property.
	 */
	public int getRent() {
		int rentToCharge = rent;
		String [] monopolies = theOwner.getMonopolies();
		rentToCharge = calculateMonopoliesRent(rentToCharge, monopolies);
		if(numHouses > 0) {
			rentToCharge = rent * (numHouses + 1);
		}
		return rentToCharge;
	}

	/**
	 * Calculates adjusted rent if the property owner has a monopoly on the color group.
	 * @param rentToCharge The base rent before monopoly adjustment.
	 * @param monopolies An array of color groups the owner holds monopolies in.
	 * @return The possibly increased rent after monopoly consideration.
	 */
	private int calculateMonopoliesRent(int rentToCharge, String[] monopolies) {
		for(int i = 0; i < monopolies.length; i++) {
			if(monopolies[i].equals(colorGroup)) {
				rentToCharge = rent * 2;
			}
		}
		return rentToCharge;
	}

	/**
	 * Executes the action for a player landing on this property, including charging rent if owned by another player.
	 */
	public void playAction() {
		Player currentPlayer = null;
		if(!isAvailable()) {
			currentPlayer = GameMaster.instance().getCurrentPlayer();
			if(theOwner != currentPlayer) {
				currentPlayer.payRentTo(theOwner, getRent());
			}
		}
	}

	/**
	 * Sets the color group to which this property belongs.
	 * @param colorGroup The color group to assign to this property.
	 */
	public void setColorGroup(String colorGroup) {
		this.colorGroup = colorGroup;
	}

	/**
	 * Sets the cost to purchase one house on this property.
	 * @param housePrice The house price amount to set.
	 */
	public void setHousePrice(int housePrice) {
		this.housePrice = housePrice;
	}

	/**
	 * Sets the number of houses currently built on this property.
	 * @param numHouses The number of houses to set on this property.
	 */
	public void setNumHouses(int numHouses) {
		this.numHouses = numHouses;
	}

	/**
	 * Sets the selling price of this property.
	 * @param sellPrice The selling price to assign to this property.
	 */
	public void setPrice(int sellPrice) {
		this.sellPrice = sellPrice;
	}

	/**
	 * Sets the base rent amount for this property.
	 * @param rent The rent amount to set.
	 */
	public void setRent(int rent) {
		this.rent = rent;
	}
}
