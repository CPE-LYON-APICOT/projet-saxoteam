package timebomb.time_bomb.Controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import timebomb.time_bomb.Models.Carte;
import timebomb.time_bomb.Models.Desamorceur;
import timebomb.time_bomb.Models.Joueur;
import timebomb.time_bomb.Models.Partie;

import java.util.List;

public class controllerGameTable {

    @FXML
    private HBox hboxSelectCard;

    @FXML
    private Label joureurActuLabel;

    @FXML
    private Label resultLabel;

    @FXML
    private VBox vboxSelectPlayer;

    private Partie partie;

    public void initialize(List<Joueur> joueurs) {
        this.partie = new Partie(joueurs);
        partie.initialize();
        Joueur joueurActuel = partie.getJoueurActuel();
        joureurActuLabel.setText("au tour de " + joueurActuel.getNom());
        for (Joueur joueur : partie.getLesJoueurs()) {
            Button button = setButton(joueur);
            vboxSelectPlayer.getChildren().add(button);
            if(joueur == joueurActuel){
                button.setDisable(true);
            }
        }
    }

    private Button setButton(Joueur joueur) {
        Button button = new Button(joueur.getNom());
        button.setOnAction(event -> handleButtonAction(joueur));
        return button;
    }

    private void handleButtonAction(Joueur joueur) {
        hboxSelectCard.getChildren().clear();
        for (Carte carte : joueur.getCartes()) {
            Button button1 = new Button();
            button1.setGraphic(carte.getImageView());
            button1.setOnAction(actionEvent -> handleCardAction(carte, button1, joueur));
            hboxSelectCard.getChildren().add(button1);
        }
    }

    private void handleCardAction(Carte carte, Button button, Joueur joueurChoisi) {
        carte.estRetourner();
        button.setGraphic(carte.getImageView());
        if (carte instanceof Desamorceur){
            partie.removeOneDesamorceur();
        }
        System.out.println(partie.verifDesamorceur());
        Joueur joueurPrecedent = partie.getJoueurActuel();
        joueurPrecedent.setSectateur(false);
        joueurChoisi.setSectateur(true);
        partie.setJoueurActuel();
        joureurActuLabel.setText("au tour de " + partie.getJoueurActuel().getNom());
        changePlayer(joueurPrecedent);

    }


    private void changePlayer(Joueur previous){
        for (Node node : vboxSelectPlayer.getChildren()) {
            if (node instanceof Button button) {
                if (button.getText().equals(partie.getJoueurActuel().getNom())) {
                    button.setDisable(true);
                }
                if (button.getText().equals(previous.getNom())){
                    button.setDisable(false);
                }
            }
        }
    }



}
