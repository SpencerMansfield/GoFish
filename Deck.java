package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck {
	private final int deckLength = 52;
	private final int numOfEachSuit = 13;
	private final String[] ranks = {"2","3","4","5","6","7","8","9","10","Jack","Queen","King","Ace"};
	private final String[] suits = {"Clubs", "Hearts", "Diamonds", "Spades"};
	private ArrayList<Card> GameDeck = new ArrayList<>();
	private Random rand = new Random();
	private int randInt;
	
	
	public Deck() {
		for (String suit : suits) {
			for (String rank: ranks) {
				Card GameCard = new Card(suit, rank);
				GameDeck.add(GameCard);
			}
		}
		for(int i=0; i <= 3; i++) {
			for(int j =0; j < numOfEachSuit; j++ ) {
				
			}
		}
//		Collections.shuffle(GameDeck);
		
	}
	
	public void assignCards(int numPlayers, ArrayList<playerCards> allPlayersCards) {
		for(int i=0; i < numPlayers; i++) {
    		allPlayersCards.add(new playerCards());
    	}
		for(int i=0; i < numPlayers ;i++) {
	    	
	    	for(int j=0; j < 5; j++) {
	    		randInt = rand.nextInt(GameDeck.size());
	    		allPlayersCards.get(i).addCard(GameDeck.get(randInt));
	    		GameDeck.remove(randInt);
	    		
	    	}
	    	
	    }
		
		for(int i=0; i < numPlayers; i++) {
			allPlayersCards.get(i).toString();
		}
	}
	
	public void addCards(playerCards player) {
		if(player.isEmpty()) {
			for(int i = 0; i < GameDeck.size(); i++) {
				randInt = rand.nextInt(GameDeck.size());
				player.addCard(GameDeck.get(randInt));
				GameDeck.remove(i);
			}
		}
	}
		
	public Card getRandomCard() {
		
		randInt = rand.nextInt(GameDeck.size());
		
		return GameDeck.remove(randInt);		
	}
	
	public String getCardRank(int place) {
		return GameDeck.get(place).getRank();
	}
	public String getCardSuit(int place) {
		return GameDeck.get(place).getSuit();
	}
	public Card getCard(int place) {
		return GameDeck.get(place);
	}
	public void remove(int place) {
		GameDeck.remove(place);
	}
	public int size() {
	 return GameDeck.size();
	}
	
	public boolean isEmpty() {
		return GameDeck.isEmpty();
	}
	
	public String toString() {
		String str = "";
		for(int i=0; i < deckLength; i++) {
			str += GameDeck.get(i).getSuit() + " " + GameDeck.get(i).getRank() + "\n";
		}
		return str;
	}
	
	public static void main(String[] args) {
		Deck deck = new Deck();
		System.out.println(deck.toString());
	}

}

