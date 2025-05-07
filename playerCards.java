package application;

import java.util.ArrayList;

public class playerCards{
	private ArrayList<Card> playersCards;
	
	public playerCards () {
		this.playersCards = new ArrayList<Card>();
				
	}
	
	public void addCard(Card card) {
		playersCards.add(card);
	}

	public int size() {
		return playersCards.size();
	}
	
	public String getRank(int index) {
		return playersCards.get(index).getRank();
	}
		
	public Card getCard(int i) {
		return playersCards.get(i);
	}
	
	public boolean isEmpty() {
		return playersCards.isEmpty();
	}
	
	public void removeCard(Card card) {
		playersCards.remove(card);
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
