package timebomb.time_bomb.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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

    public void initialize(List<Joueur> joueurs) {
        System.out.println(joueurs);
        Partie partie = new Partie(joueurs);
        partie.initialize();
        System.out.println(partie.getDeck());
    }

}
