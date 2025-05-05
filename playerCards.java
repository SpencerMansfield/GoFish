package application;

import java.util.ArrayList;

public class playerCards {
	private ArrayList<Card> playersCards;
	
	public playerCards () {
		this.playersCards = new ArrayList<Card>();
				
	}
	
	public void addCard(Card card) {
		playersCards.add(card);
	}
	
	public String toString() {
		String str = "";
		for(int i=0; i < playersCards.size(); i++) {
		str += "Suit: " + playersCards.get(i).getSuit();
		str += " Rank: " + playersCards.get(i).getRank() + "\n";
		}
		return str;
	}

}
