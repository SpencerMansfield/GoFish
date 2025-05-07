package application;

import java.util.ArrayList;
import java.util.Random;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.Collections;

public class goFishGame extends Application {

    private Stage primaryStage;
    private ImageView imageView;
    private Deck deck = new Deck();
    private ArrayList<Card> player1Cards = new ArrayList<>();
    private ArrayList<playerCards> playersCards = new ArrayList<playerCards>();
    private Random rand;
    private ArrayList<Integer> playerScores;
    private int amountPlayers;
    private GoFishManager manager;
    private boolean gameOn;
    private int playerPicked;
    private Card cardToCompare;
    private playerCards currentPlayerCards;
    private playerCards playerOneCards;
   


    public static void main(String[] args) {
        launch(args);
    }

    private Button onePlayerButton, twoPlayerButton, threePlayerButton, fourPlayerButton;

    public void start(Stage stage) {
        this.primaryStage = stage;
        showMainMenu();
    }

    public void showMainMenu() {
        Label title = new Label("Go Fish");
        title.setStyle("-fx-font-size: 24px;");

        Button playButton = new Button("Play");
        playButton.setOnAction(this::playButton);

        Button quitButton = new Button("Quit");
        quitButton.setOnAction(this::quitButton);
        
        Button showWin = new Button("Win");

        VBox layout = new VBox(15, title, playButton, quitButton, showWin);
        layout.setStyle("-fx-alignment: center; -fx-padding: 30;-fx-background-color: lightblue;");
     
        primaryStage.setScene(new Scene(layout, 300, 300));
        primaryStage.setTitle("Go Fish Menu");
        primaryStage.show();
    }
    
    private void winMenu() {
    	int winner = 0;
    	int winnerScore = 0;
    	for(int i=0; i < playerScores.size(); i++) {
    		if(playerScores.get(i) > winnerScore) {
    			winner = i;
    			winnerScore = playerScores.get(i);
    		}
    	}
    	
    	Label title = new Label ("Player " + winner + "Wins!!!");
    	title.setStyle("-fx-font-size: 24px;");
    	
    	Button playAgain = new Button("Play Again?");
    	playAgain.setOnAction(this::playButton);
    	
    	Button backToMenu = new Button("Back to Menu");
    	backToMenu.setOnAction(this::backToMenu);
    	
    	VBox layout = new VBox(15, title, playAgain, backToMenu);
    	layout.setStyle("-fx-alignment: center; -fx-padding: 30;-fx-background-color: lightblue;");
    	
    	primaryStage.setScene(new Scene(layout, 300, 300));
    	primaryStage.setTitle("Go Fish");
    	primaryStage.show();
    	
    }
    
    private void startGame(ActionEvent e) {
    	playersCards.clear();
    	manager = new GoFishManager(amountPlayers);
    	deck.assignCards(amountPlayers, playersCards);
    	playerScores = new ArrayList<Integer>();    	
    	
    	for(int i=0; i < amountPlayers; i++) {
    		playerScores.add(0);
    	}
    	
    	gameOn = true;
    	
    	startTurn();  	
    }
    
    private void startTurn() {
    	playerOneCards = playersCards.get(0);
    	playerCards opponentCards = playersCards.get(1);
    	
    	System.out.println("Player 1 Cards");
    	for(int i=0; i < playerOneCards.size(); i++ ) {
    		System.out.println(playerOneCards.getRank(i));
    	}
    	System.out.println("Player 2 Cards"); 
    	for(int i=0; i < opponentCards.size(); i++) {
    		System.out.println(opponentCards.getRank(i));
    	}
    	
    	pickPlayer();
    }

    private void pickPlayer() {
    	    GridPane grid = new GridPane();
    	    
    	    for(int i=0; i < amountPlayers; i++) {
    	    	int index = i;
    	    	Button button = new Button("Player: " + (i));
    	    	button.setOnAction(e -> {
    	    		playerPicked = index;
    	    		pickCardForAsk();
    	    	});
    	    	grid.add(button, i, 0);
    	    	}
        	System.out.print(manager.getCurrentPlayer());
    	    
    	    primaryStage.setScene(new Scene(grid, 300, 300));
        	primaryStage.setTitle("Go Fish");
        	primaryStage.show();
    
    }
    


    
    private void pickCardForAsk() {
    	GridPane grid = new GridPane();
 // 	Pick card to compare
    	    	
    	currentPlayerCards = playersCards.get(manager.getCurrentPlayer());
    	
    	for(int j=0; j < currentPlayerCards.size(); j++) {
    		int index = j;
    		String rank = currentPlayerCards.getRank(index);
    		Button button = new Button(rank);
    		button.setOnAction(e -> {
    			cardToCompare = currentPlayerCards.getCard(index);
    			compare();
    		});
    		grid.add(button, j, 0);
    	}

    	primaryStage.setScene(new Scene(grid, 300, 300));
      	primaryStage.setTitle("Go Fish");
      	primaryStage.show();
    }
    
    private void compare() { 
    	System.out.println("Player " + manager.getCurrentPlayer() + " asking for rank: " + cardToCompare.getRank());
       	for (int i = 0; i < playersCards.get(playerPicked).size(); i++) {
       	    Card opponentCard = playersCards.get(playerPicked).getCard(i);
       	    System.out.println("Opponent's card rank: " + opponentCard.getRank());

       	    if (manager.compareCards(cardToCompare, opponentCard)) {
       	        System.out.println("MATCH FOUND: " + opponentCard.getRank());
       	    }
       	}

    	ArrayList<Card> cardsForTransfer = new ArrayList<>();
    	
    	
   	for(int i=0; i < playersCards.get(manager.getOpponent()).size(); i++) {
    		if(manager.compareCards(cardToCompare, playersCards.get(manager.getOpponent()).getCard(i))) {
    			cardsForTransfer.add(playersCards.get(manager.getOpponent()).getCard(i));
    		}
    	}
   	
   	for(Card card : cardsForTransfer) {
   		playersCards.get(manager.getCurrentPlayer()).addCard(card);
   		playersCards.get(manager.getOpponent()).removeCard(card);
   	}
   	
   	if(cardsForTransfer.size() == 0) {
   		drawCard(manager.getCurrentPlayer());
   	}
   	
   	for(int i=0; i < playersCards.size(); i++) {
   		if(manager.checkForAllRank(playersCards.get(i))) {
   			int currentValue = playerScores.get(i);
   			playerScores.set(i, currentValue + 1);
   		}
   	}
   	
   	for(int i=0; i < playersCards.size(); i++) {
   		if(playersCards.get(i).isEmpty() && !deck.isEmpty()) {
   			deck.addCards(playersCards.get(i));
   		}
   	}
   	
   
   	System.out.println(manager.getCurrentPlayer());
   	checkWin();
    manager.nextTurn();
   	startTurn();
    }
    
    
    private void checkWin() {
		boolean allHandsEmpty = false;


    	int endCondition = 0;
    	for(int i=0; i < playersCards.size(); i++) {
    		if(playersCards.get(i).size() == 0) {
    			endCondition++;
    		}
    	}

    	if(endCondition == playersCards.size()) {
    		allHandsEmpty = true;
    		winMenu();
    	}
    	System.out.println("\nChecking win: allHandsEmpty=" + allHandsEmpty + ", deckEmpty=" + deck.isEmpty());
    }
    
    private void playButton(ActionEvent event) {
    	
        Label label = new Label("Choose number of players:");
        VBox layout = new VBox(10, label);
        layout.setStyle("-fx-alignment: center; -fx-padding: 10; -fx-background-color: lightblue;");

        onePlayerButton = new Button("1 Player");
        twoPlayerButton = new Button("2 Players");
        threePlayerButton = new Button("3 Players");
        fourPlayerButton = new Button("4 Players");

        twoPlayerButton.setOnAction(e -> {
        	amountPlayers = 2;
        	startGame(e);
        });
        threePlayerButton.setOnAction(e -> {
        	amountPlayers = 3;
        	startGame(e);
        });
        fourPlayerButton.setOnAction(e -> {
            amountPlayers = 4; 
            startGame(e);
        });

        layout.getChildren().addAll(onePlayerButton, twoPlayerButton, threePlayerButton, fourPlayerButton);
        primaryStage.setScene(new Scene(layout, 300, 300));
    }

    private void quitButton(ActionEvent event) {
        primaryStage.close();
    }

    private void playerChoice(ActionEvent event) {
      //  Button source = (Button) event.getSource();
        //String text = source.getText();
       
        Label label = new Label("Game started with " + "" + " players!");
        Button backButton = new Button("Back to Menu");
        backButton.setOnAction(this::backToMenu);

        VBox layout = new VBox(20, label, backButton);
        layout.setStyle("-fx-alignment: center; -fx-padding: 30; -fx-background-color: lightblue;");
        primaryStage.setScene(new Scene(layout, 300, 300));
    }
    
    private void drawCard(int playerIndex) {
    	playersCards.get(playerIndex).addCard(deck.getRandomCard());
    }
    

    private void backToMenu(ActionEvent event) {
        showMainMenu();
    }
}
