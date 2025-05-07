package application;

public class Card {
	private String suit;
	private String rank;
	
	public Card(String suit, String rank) {
		this.suit = suit;
		this.rank = rank;
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
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true;
	    if (obj == null || getClass() != obj.getClass()) return false;
	    Card card = (Card) obj;
	    return this.rank.equals(card.rank) && this.suit.equals(card.suit);
	}

	
	public String toString() {
		String str = "";
		str += "Suit: " + suit;
		str += "\nRank: " + rank;
		return str;
	}
}
