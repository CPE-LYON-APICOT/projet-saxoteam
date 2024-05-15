package timebomb.time_bomb.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import timebomb.time_bomb.Models.Carte;
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
        }
    }

    private Button setButton(Joueur joueur) {
        Button button = new Button(joueur.getNom());
        button.setOnAction(event -> {
            hboxSelectCard.getChildren().clear();
            for (Carte carte: joueur.getCartes()){
                Button button1 = new Button();
                button1.setGraphic(carte.getImageView());
                button1.setOnAction(actionEvent -> {
                    carte.estRetourner();
                    button1.setGraphic(carte.getImageView());
                });
                hboxSelectCard.getChildren().add(button1);
            }
        });
        return button;
    }


}
