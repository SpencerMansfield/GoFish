package application;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.Collections;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.BackgroundFill;

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
	boolean matchFound = false;
	private ScrollPane scroll1;

   


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

        VBox layout = new VBox(15, title, playButton, quitButton);
        layout.setStyle("-fx-alignment: center; -fx-padding: 30;-fx-background-color: lightblue;");
     
        primaryStage.setScene(new Scene(layout, 1500, 1000));
        primaryStage.setTitle("Go Fish Menu");
      	primaryStage.setMaximized(true);
        primaryStage.show();
    }
    
    private void winMenu() {
        System.out.println("Win condition met, showing win menu...");
        
        int winner = 0;
        int winnerScore = 0;
        for (int i = 0; i < playerScores.size(); i++) {
            if (playerScores.get(i) > winnerScore) {
                winner = i;
                winnerScore = playerScores.get(i);
            }
        }

        Label title = new Label("Player " + (winner + 1) + " Wins!!!");
        title.setStyle("-fx-font-size: 24px;");

        Button playAgain = new Button("Play Again?");
        playAgain.setOnAction(this::playButton);

        Button backToMenu = new Button("Back to Menu");
        backToMenu.setOnAction(this::backToMenu);

        VBox layout = new VBox(15, title, playAgain, backToMenu);
        layout.setStyle("-fx-alignment: center; -fx-padding: 30;-fx-background-color: lightblue;");

        primaryStage.setTitle("Go Fish");
        primaryStage.setScene(new Scene(layout, 1500, 1000));
      	primaryStage.setMaximized(true);
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
    	    	
    	startTurn();  	
    }
    
    private void startTurn() {
    	playerOneCards = playersCards.get(0);
    	playerCards opponentCards = playersCards.get(1);
    	
//    	System.out.println("Player 1 Cards");
//    	for(int i=0; i < playerOneCards.size(); i++ ) {
//    		System.out.println(playerOneCards.getRank(i));
//    	}
//    	System.out.println("Player 2 Cards"); 
//    	for(int i=0; i < opponentCards.size(); i++) {
//    		System.out.println(opponentCards.getRank(i));
//    	}
    	
    	pickPlayer();
    }

    private void pickPlayer() {
    		FlowPane centerPane = new FlowPane();
//    	    System.out.print(manager.getCurrentPlayer());
          	primaryStage.setMaximized(true);
        	Label message = new Label("             Player " + (manager.getCurrentPlayer() + 1) + "'s Turn");
        	Label messageTwo = new Label ("Select a Player to Ask for a Card");
    	    message.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 25));  
    	    messageTwo.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 25));   


        	VBox vbox = new VBox();
        	vbox.getChildren().add(message);
        	vbox.getChildren().add(messageTwo);
        	
        	
        	FlowPane topPane = new FlowPane(vbox);
        	topPane.setHgap(10);
        	topPane.setAlignment(Pos.TOP_CENTER);
	    	topPane.setStyle("-fx-background-color: lightblue");

        	
        	BorderPane borderPane = new BorderPane();
        	borderPane.setTop(topPane);
        	borderPane.setCenter(centerPane);
        	
        	primaryStage.setScene(new Scene(borderPane, 1300, 1000));
          	primaryStage.setTitle("Go Fish");
        	primaryStage.show();
    	    
    	    for(int i=0; i < amountPlayers; i++) {
    	    	int index = i;
    	    	if (i != manager.getCurrentPlayer()) {
    	    		Button button = new Button("Player: " + (i + 1));
        	    	button.setOnAction(e -> {
          	    		playerPicked = index;
        	    		pickCardForAsk();
        	    	});
        	    	centerPane.getChildren().add(button);
//        	    	grid.setAlignment(Pos.CENTER);
        	    	centerPane.setStyle("-fx-background-color: lightblue");
        	    	
        	    	centerPane.setAlignment(Pos.CENTER);
     
        	    	centerPane.setPadding(new Insets(100, 100, 400, 100));
        	    	}
    	    	}}
    	    	
//        	System.out.print(manager.getCurrentPlayer());
//          	primaryStage.setMaximized(true);
//
//        	primaryStage.setScene(new Scene(grid, 1500, 1500));
//          	primaryStage.setTitle("Go Fish");
//        	primaryStage.show();
//
//    
//    }
    


    
    private void pickCardForAsk() {
    	FlowPane pane = new FlowPane();
    	GridPane grid = new GridPane();
 // 	Pick card to compare
    	    	
    	currentPlayerCards = playersCards.get(manager.getCurrentPlayer());
    	Text currentPlayersTurn = new Text();
    	//currentPlayersTurn.setText("Player " + (manager.getCurrentPlayer() + 1 ) + "'s Turn");
    	pane.setHgap(10);
    	pane.setVgap(10);
    	pane.setAlignment(Pos.CENTER_LEFT);
    	
    	for(int j=0; j < currentPlayerCards.size(); j++) {
    		int index = j;
    		String rank = currentPlayerCards.getRank(index);
		String suit = currentPlayerCards.getCard(index).getSuit();
		String fileName = "/" + rank.toLowerCase() + "_of_" + suit.toLowerCase() + ".png";
		InputStream img = getClass().getResourceAsStream(fileName);
		if (img == null) {
         System.err.println("Image not found: " + fileName);	          
         continue;  
        }
		Image cardImage = new Image(img);
			ImageView cardImageView = new ImageView(cardImage);
			cardImageView.setFitWidth(100);
			cardImageView.setFitHeight(150);
    		Button button = new Button();
		button.setGraphic(cardImageView);
    		button.setOnAction(e -> {
    			cardToCompare = currentPlayerCards.getCard(index);
    			compare();
    		});
    		pane.getChildren().add(button);
    	}
    	ScrollPane scroll1 = new ScrollPane(pane);
    	scroll1.setFitToWidth(true);
    	scroll1.setFitToHeight(true);
    	 scroll1.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS); 
           
    	primaryStage.setScene(new Scene(scroll1, 1500, 1000));
    	pane.setAlignment(Pos.CENTER);
    	pane.setPadding(new Insets(100, 400, 400, 100));
    	pane.setStyle("-fx-background-color: lightblue");
      	primaryStage.setTitle("Go Fish");
      	primaryStage.setMaximized(true);
      	primaryStage.show();
    }
    
    private void compare() {
    	matchFound = false;

//    	System.out.println("Player " + manager.getCurrentPlayer() + " asking for rank: " + cardToCompare.getRank());
       	for (int i = 0; i < playersCards.get(playerPicked).size(); i++) {
       	    Card opponentCard = playersCards.get(playerPicked).getCard(i);
//       	    System.out.println("Opponent's card rank: " + opponentCard.getRank());

       	    if (manager.compareCards(cardToCompare, opponentCard)) {
       	    	matchFound = true;
//       	        System.out.println("MATCH FOUND: " + opponentCard.getRank());
       	    }
       	}

    	ArrayList<Card> cardsForTransfer = new ArrayList<>();
    	
    	
    	for (int i = 0; i < playersCards.get(manager.getOpponent()).size(); i++) {
    	    Card card = playersCards.get(manager.getOpponent()).getCard(i);
    	    if (manager.compareCards(cardToCompare, card)) {
    	        cardsForTransfer.add(card);
    	    }
    	}
   	
    	for (Card card : cardsForTransfer) {
    	    playersCards.get(manager.getOpponent()).removeCard(card);
    	}

    	for (Card card : cardsForTransfer) {
    	    playersCards.get(manager.getCurrentPlayer()).addCard(card);
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
   	
   	
   	if(!matchFound) {
    manager.nextTurn();
   	}
   	currentPlayerCards = playersCards.get(manager.getCurrentPlayer()); 
   	if (manager.checkForAllRank(currentPlayerCards)) {
   	    allRankFound();
   	}
   	System.out.println(manager.checkForAllRank(currentPlayerCards));

   	for(int i =0; i < playerScores.size(); i++) {
   		System.out.println("\n" + playerScores.get(i));
   	}
   	
   	nextTurnIntermediary();
   	checkWin();

}
    
    private void allRankFound() {
    	VBox box = new VBox();
    	Text text = new Text("Four of a Rank found by " + manager.getCurrentPlayer());
    	Button button = new Button("Continue");
    	button.setOnAction(this::nextTurnIntermediary);
    	
    	box.getChildren().add(text);
    	box.getChildren().add(button);

    	
    	Scene scene = new Scene(box, 1500, 1000);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Go Fish");
        primaryStage.setMaximized(true); 
        primaryStage.show();
    	
    }
    
    private void nextTurnIntermediary() {
    	if(!matchFound) {

    	Label message = new Label("No match, go fish");
    	message.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50));
    	message.setStyle("-fx-text-fill: black; -fx-effect: dropshadow(one-pass-box, blue, 2, 1, 0, 0);");
    	Button button = new Button("Click to Continue");
    	button.setOnAction(e -> {
    		startTurn();
    	});
    	
    	VBox vbox = new VBox(20, message, button);
    	vbox.setAlignment(Pos.CENTER);
    	vbox.setStyle("-fx-background-color: lightblue;");
    	 vbox.setPadding(new Insets(100, 400, 400, 100));
        vbox.setPrefSize(1500, 1000); 

        Scene scene = new Scene(vbox, 1500, 1000);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Go Fish");
        primaryStage.setMaximized(true); 
        primaryStage.show();
     	}
    	
    	else {
    		Text text = new Text();
        	text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50));   
     	    text.setStrokeWidth(2); 
        	text.setText("Match Found! Go Again");
        	
        	Button button = new Button("Click to Continue");
        	button.setOnAction(e -> {
        		startTurn();
        	});
        	VBox vbox = new VBox(20, text, button); 
            vbox.setAlignment(Pos.CENTER);
            vbox.setStyle("-fx-background-color: lightblue;");
            vbox.setPrefSize(1500, 1000); 
            vbox.setPadding(new Insets(100, 400, 400, 100));

            Scene scene = new Scene(vbox, 1500, 1000);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Go Fish");
            primaryStage.setMaximized(true); 
            primaryStage.show();
        
        
    	}
    	
    
    	
    }
    private void nextTurnIntermediary(ActionEvent event) {
    	if(!matchFound) {

    	Label message = new Label("No match, go fish");
    	message.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50));
    	message.setStyle("-fx-text-fill: black; -fx-effect: dropshadow(one-pass-box, blue, 2, 1, 0, 0);");
    	Button button = new Button("Click to Continue");
    	button.setOnAction(e -> {
    		startTurn();
    	});
    	

    	VBox vbox = new VBox(20, message, button);
    	vbox.setAlignment(Pos.CENTER);
    	vbox.setStyle("-fx-background-color: lightblue;");
    	 vbox.setPadding(new Insets(100, 400, 400, 100));
        vbox.setPrefSize(1500, 1000); 

        Scene scene = new Scene(vbox, 1500, 1000);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Go Fish");
        primaryStage.setMaximized(true); 
        primaryStage.show();
     	}
    	
    	else {
    		Text text = new Text();
        	text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50));   
     	    text.setStrokeWidth(2); 
        	text.setText("Match Found! Go Again");
        	
        	Button button = new Button("Click to Continue");
        	button.setOnAction(e -> {
        		startTurn();
        	});
        	VBox vbox = new VBox(20, text, button); 
            vbox.setAlignment(Pos.CENTER);
            vbox.setStyle("-fx-background-color: lightblue;");
            vbox.setPrefSize(1500, 1000); 
            vbox.setPadding(new Insets(100, 400, 400, 100));

            Scene scene = new Scene(vbox, 1500, 1000);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Go Fish");
            primaryStage.setMaximized(true); 
            primaryStage.show();
        
    	}
    	
    
    	
    }
    
    
    private void checkWin() {
		boolean allHandsEmpty = false;

    	if(playersCards.get(0).size() == 0 && playersCards.get(1).size() == 0) {
    		allHandsEmpty = true;
    		winMenu();
    	}
    	
    	System.out.println("\nChecking win: allHandsEmpty=" + allHandsEmpty + ", deckEmpty=" + deck.isEmpty());
    }
    
    private void playButton(ActionEvent event) {
    	
        Label label = new Label("Choose number of players:");
        VBox layout = new VBox(10, label);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: lightblue;");
        layout.setPadding(new Insets(100, 400, 400, 100));
        layout.setPrefSize(1500, 1000);

        Scene scene = new Scene(layout, 1500, 1000);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Go Fish");
        primaryStage.setMaximized(true); // or setFullScreen(true);
        primaryStage.show();


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
        if (!deck.isEmpty()) {
            playersCards.get(playerIndex).addCard(deck.getRandomCard());
        } else {
            System.out.println("Deck is empty, cannot draw any more cards.");
        }
    }
    

    private void backToMenu(ActionEvent event) {
        showMainMenu();
    }
}
