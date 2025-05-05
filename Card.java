package application;

public class Card {
	private String suit;
	private String rank;
	private int value;
	
	public Card(String suit, String rank, int value) {
		this.suit = suit;
		this.rank = rank;
		this.value = value;
	}

	public String getSuit() {
		return suit;
	}
	public String getRank() {
		return rank;
	}
	public void setSuit(String suit) {
		this.suit = suit;
	}
	public void setNumber(String rank) {
		this.rank = rank;
	}
	
	public String toString() {
		String str = "";
		str += "Suit: " + suit;
		str += "\nRank: " + rank;
		return str;
	}
}
