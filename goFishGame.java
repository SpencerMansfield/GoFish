package application;

import java.util.ArrayList;
import java.util.Random;

import javafx.application.Application;
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
    

    public static void main(String[] args) {
        launch(args);
    }

    
    private Button onePlayerButton, twoPlayerButton, threePlayerButton, fourPlayerButton;

    public void start(Stage stage) {
        this.primaryStage = stage;
        showMainMenu();
    }

    private void showMainMenu() {
        Label title = new Label("Go Fish");
        title.setStyle("-fx-font-size: 24px;");

        Button playButton = new Button("Play");
        playButton.setOnAction(this::playButton);

        Button quitButton = new Button("Quit");
        quitButton.setOnAction(this::quitButton);
        
        Button showWin = new Button("Win");
        showWin.setOnAction(this::winMenu);

        VBox layout = new VBox(15, title, playButton, quitButton, showWin);
        layout.setStyle("-fx-alignment: center; -fx-padding: 30;-fx-background-color: lightblue;");
     
        primaryStage.setScene(new Scene(layout, 300, 300));
        primaryStage.setTitle("Go Fish Menu");
        primaryStage.show();
    }
    
    private void winMenu(ActionEvent event) {
    	Label title = new Label ("Player wins!");
    	title.setStyle("-fx-font-size: 24px;");
    	
    	Button playAgain = new Button("Play Again?");
    	playAgain.setOnAction(this::playButton);
    	
    	Button backToMenu = new Button("Back to Menu");
    	backToMenu.setOnAction(this::backToMenu);
    	
//    	Button assignCards = new Button("Assign Player1 Cards");
//    	assignCards.setOnAction(this::assignPlayerCards);
    	
    	VBox layout = new VBox(15, title, playAgain, backToMenu);
    	layout.setStyle("-fx-alignment: center; -fx-padding: 30;-fx-background-color: lightblue;");
    	
    	primaryStage.setScene(new Scene(layout, 300, 300));
    	primaryStage.setTitle("Go Fish");
    	primaryStage.show();
    	
    }
    
    private void twoPlayer(ActionEvent event) {
    	amountPlayers = 2;
    	
    	deck.assignCards(amountPlayers, playersCards);
    	
    	GridPane gridPane = new GridPane();
    	
    	 gridPane.setPadding(new Insets(20));        
    	    gridPane.setHgap(50);                      
    	    gridPane.setVgap(10);                       
    	    gridPane.setAlignment(Pos.CENTER);
    	
    	Text firstPlayerCards = new Text("PLAYER 1 CARDS\n" + playersCards.get(0).toString());
    	Text secondPlayerCards = new Text("PLAYER 2 CARDS\n" + playersCards.get(1).toString());
    	
   
    	
    	gridPane.add(firstPlayerCards, 0, 0);
    	gridPane.add(secondPlayerCards, 0, 1);
    	
    	Scene scene = new Scene(gridPane, 600, 400);
    	
    	
    	
    	primaryStage.setScene(scene);
    	primaryStage.setTitle("Go Fish");
    	primaryStage.show();
    	
    }
    
    private void threePlayer(ActionEvent event) {
    	amountPlayers = 3;
    	
    	deck.assignCards(amountPlayers, playersCards);
    	
    	GridPane gridPane = new GridPane();
    	
    	 gridPane.setPadding(new Insets(20));        
    	    gridPane.setHgap(50);                      
    	    gridPane.setVgap(10);                       
    	    gridPane.setAlignment(Pos.CENTER);
    	
    	Text firstPlayerCards = new Text("PLAYER 1 CARDS\n" + playersCards.get(0).toString());
    	Text secondPlayerCards = new Text("PLAYER 2 CARDS\n" + playersCards.get(1).toString());
    	Text thirdPlayerCards = new Text("PLAYER 3 CARDS\n" + playersCards.get(2).toString());
    	
   
    	
    	gridPane.add(firstPlayerCards, 1, 1);
    	gridPane.add(secondPlayerCards, 2, 2);
    	gridPane.add(thirdPlayerCards, 2, 0);
    	
    	Scene scene = new Scene(gridPane, 600, 400);
    	
    	
    	
    	primaryStage.setScene(scene);
    	primaryStage.setTitle("Go Fish");
    	primaryStage.show();
    }
    
    private void fourPlayer(ActionEvent event) {
    	amountPlayers = 4;
    	Image cards = new Image("pngtree-spades-hearts-playing-cards-clip-art-png-image_2962158.jpg");
    	ImageView image = new ImageView(cards);
    	
    	VBox layout = new VBox(image);
    	
    	primaryStage.setScene(new Scene(layout,300,300));
    	
    }
    
    private void startGame() {
    	
    }

    private void playButton(ActionEvent event) {
        Label label = new Label("Choose number of players:");
        VBox layout = new VBox(10, label);
        layout.setStyle("-fx-alignment: center; -fx-padding: 10; -fx-background-color: lightblue;");

        onePlayerButton = new Button("1 Player");
        twoPlayerButton = new Button("2 Players");
        threePlayerButton = new Button("3 Players");
        fourPlayerButton = new Button("4 Players");

        onePlayerButton.setOnAction(this::winMenu);
        twoPlayerButton.setOnAction(this::twoPlayer);
        threePlayerButton.setOnAction(this::threePlayer);
        fourPlayerButton.setOnAction(this::fourPlayer);

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
    
//    private void assignPlayerCards() {
//    	for(int i=0; i < amountPlayers; i++) {
//    		playersCards.add(new playerCards());
//    	}
//    	rand = new Random();
//    	for(int i=0; i < playersCards.size() ;i++) {
//    		
//    	
//    	for(int j=0; j < 5; j++) {
//    		playersCards.get(i).addCard(Deck.getCard(rand.nextInt(52)));
//    	}
//    	
//    	}
//    	
//    	for(int i=0; i < playersCards.size(); i++) {
//    		System.out.println(playersCards.get(i).toString());
//    	}
//    	
//    }
    

    private void backToMenu(ActionEvent event) {
        showMainMenu();
    }
}
