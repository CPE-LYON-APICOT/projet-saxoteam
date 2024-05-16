package timebomb.time_bomb.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import timebomb.time_bomb.Models.Carte;
import timebomb.time_bomb.Models.Joueur;
import timebomb.time_bomb.Models.Partie;

public class controllerAttributeCard {
    @FXML
    private HBox HBoxViewCard;

    @FXML
    private AnchorPane anchorPaneBottom;

    @FXML
    private Button nextButton;

    @FXML
    private Button previousButton;


    Partie partie;

    Integer compteur;

    public void initialize(Partie partie){
        this.partie = partie;
        compteur = 0;

        displayFirstPage(partie.getLesJoueurs().get(compteur));

        nextButton.setOnAction(event -> handleButtonActionNext());
        previousButton.setOnAction(actionEvent -> handleButtonActionPrevious());
    }

    private void handleButtonActionPrevious() {
        if (compteur == partie.getLesJoueurs().size() * 2 -1 ){
            nextButton.setDisable(false);
        }
        compteur -= 1;
        cardOrdName();
        if(compteur == 0){
            previousButton.setDisable(true);
        }
    }

    private void handleButtonActionNext() {
        if(compteur == 0){
            previousButton.setDisable(false);
        }
        compteur+=1;
        cardOrdName();
        System.out.println(compteur);
        if (compteur == partie.getLesJoueurs().size() * 2 -1){
            nextButton.setDisable(true);
        }
    }

    private void cardOrdName() {
        if (compteur % 2 == 0){
            HBoxViewCard.getChildren().clear();
            anchorPaneBottom.getChildren().clear();
            displayFirstPage(partie.getLesJoueurs().get(compteur/2));
        }else{
            HBoxViewCard.getChildren().clear();
            anchorPaneBottom.getChildren().clear();
            displayCard(partie.getLesJoueurs().get(compteur /2));
            displayRole(partie.getLesJoueurs().get(compteur/2));
        }
    }



    private void displayFirstPage(Joueur joueur){
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefHeight(478.0);
        anchorPane.setPrefWidth(709.0);

        Label label = new Label();
        label.setText("Au joueur "+ joueur.getNom() +" de voir");
        label.setFont(new javafx.scene.text.Font(36.0));

        anchorPane.getChildren().add(label);

        double labelWidth = label.getPrefWidth();
        double labelHeight = label.getPrefHeight();
        double anchorPaneWidth = anchorPane.getPrefWidth();
        double anchorPaneHeight = anchorPane.getPrefHeight();
        double labelLayoutX = (anchorPaneWidth - labelWidth) / 2;
        double labelLayoutY = (anchorPaneHeight - labelHeight) / 2;
        label.setLayoutX(labelLayoutX);
        label.setLayoutY(labelLayoutY);
        HBoxViewCard.getChildren().add(anchorPane);

    }

    private void displayRole(Joueur joueur){
        System.out.println(joueur.retournerRole());

        Label roleLabel = new Label();
        roleLabel.setText(joueur.retournerRole());
        roleLabel.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        roleLabel.setFont(new javafx.scene.text.Font(36.0));

        anchorPaneBottom.getChildren().add(roleLabel);

    }

    private void displayCard(Joueur joueur){
        for (Carte carte: joueur.getCartes()) {
            HBoxViewCard.getChildren().add(carte.view());
        }
    }

}
