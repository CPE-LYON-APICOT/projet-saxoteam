package Models;

import java.util.Collections;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
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