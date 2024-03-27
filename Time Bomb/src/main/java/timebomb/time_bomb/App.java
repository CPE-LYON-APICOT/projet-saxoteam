package timebomb.time_bomb;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import timebomb.time_bomb.Models.Partie;

import java.io.IOException;
import java.util.Scanner;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Accueil.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Accueil");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
        Scanner scanner = new Scanner(System.in);
        int nombreDeJoueurs;

        // Demander et obtenir le nombre de joueurs
        do {
            System.out.print("Entrez le nombre de joueurs (4 à 8) : ");
            nombreDeJoueurs = scanner.nextInt();
        } while (nombreDeJoueurs < 4 || nombreDeJoueurs > 8);

        // Créer et initialiser la partie
        Partie partie = new Partie(nombreDeJoueurs);
        partie.initialiser();

        // Afficher les rôles et les cartes des joueurs
        partie.afficherJoueursEtCartes();
        partie.jouerPartie();

        scanner.close();
    }
}