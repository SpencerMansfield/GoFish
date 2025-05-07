package application;

import java.util.ArrayList;

public class GoFishManager {
	private int currentPlayer;
	private int amountPlayers;
	private ArrayList<playerCards> playersCards;
	
	public GoFishManager(int amountPlayers) {
		this.currentPlayer = 0;
		this.amountPlayers = amountPlayers;
	    this.playersCards = new ArrayList<>();

	    for (int i = 0; i < amountPlayers; i++) {
	        this.playersCards.add(new playerCards()); 
	    }
		
	}
	
	public int nextTurn() {
		currentPlayer++;
		if(currentPlayer == 2) {
			currentPlayer = 0;
		}
		return currentPlayer;
	}
	
	public int getCurrentPlayer() {
		return currentPlayer;
	}
	
	public int getOpponent() {
		if (currentPlayer == 0) {
			return 1;
		}
		else return 0;
	}
	
	public boolean compareCards(Card card1, Card card2) {
	    return card1.getRank().equals(card2.getRank());
	}
	
	public boolean checkEmpty(playerCards player) {
		
		if(player.size() == 0) {
			return true;
		}
		
		return false;
	}
	
	
	
	public boolean checkForAllRank(playerCards player) {
	    for (int i = 0; i < player.size(); i++) {
	        String rankToCheck = player.getRank(i);
	        int count = 0;

	        for (int j = 0; j < player.size(); j++) {
	            if (rankToCheck.equals(player.getRank(j))) {
	                count++;
	            }
	        }

	        if (count == 4) {
	            ArrayList<Card> cardsToRemove = new ArrayList<>();

	            for (int j = 0; j < player.size(); j++) {
	                if (rankToCheck.equals(player.getRank(j))) {
	                    cardsToRemove.add(player.getCard(j));
	                }
	            }

	            for (Card card : cardsToRemove) {
	                player.removeCard(card);
	            }

	            return true;
	        }
	    }

	    return false;
	}
}
