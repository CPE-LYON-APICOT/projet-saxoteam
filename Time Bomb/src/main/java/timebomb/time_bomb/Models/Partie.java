package timebomb.time_bomb.Models;

import java.util.Collections;
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;




public class Partie {
    private final Joueur[] joueurs;
    private List<Carte> deck; // Assumez que cela est rempli comme avant
    private final int toursJoues = 0;
    private int desamorceursRestants;
    private boolean bombeTrouvee = false;
    private final Scanner scanner = new Scanner(System.in); // Pour lire l'entrée utilisateur
    
    
    public Partie(int nombreDeJoueurs) {
        joueurs = new Joueur[nombreDeJoueurs];
    }

    public void initialiser() {
        // Créer un jeu de cartes
        List<Carte> deck = creerDeck(joueurs.length);
        
        // Assigner les rôles
        assignerRoles();
        
        // Distribuer les cartes
        distribuerCartes(deck);
    }
    
    private List<Carte> creerDeck(int nombreDeJoueurs) {
        List<Carte> deck = new ArrayList<>();
        deck.add(new Bomb()); // Une seule carte Bomb
        for (int i = 0; i < nombreDeJoueurs; i++) {
            deck.add(new Desamorceur()); // Un Desamorceur par joueur
        }
        int nombreDeCartesNull = nombreDeJoueurs * 5 - (nombreDeJoueurs + 1); // Le reste sont des Carte_Null
        for (int i = 0; i < nombreDeCartesNull; i++) {
            deck.add(new Carte_Null());
        }
        Collections.shuffle(deck);
        return deck;
    }
    
    private void distribuerCartes(List<Carte> deck) {
        int indexCarte = 0;
        for (Joueur joueur : joueurs) {
            for (int j = 0; j < 5 && indexCarte < deck.size(); j++) {
                joueur.ajouterCarte(deck.get(indexCarte++));
            }
        }
    }
    
    private void assignerRoles() {
        int half = joueurs.length / 2;
        for (int i = 0; i < half; i++) {
            joueurs[i] = new Sherlock("Sherlock " + (i + 1));
        }
        for (int i = half; i < joueurs.length; i++) {
            joueurs[i] = new Moriarty("Moriarty " + (i + 1 - half));
        }
        // Si le nombre de joueurs est différent de 4, ajouter un Sherlock supplémentaire
        if (joueurs.length != 4) {
            joueurs[joueurs.length - 1] = new Sherlock("Sherlock Extra");
        }
    }

    public void afficherJoueursEtCartes() {
        for (Joueur joueur : joueurs) {
            System.out.println(joueur);
            for (Carte carte : joueur.getCartes()) {
                System.out.println("\t" + carte.getClass().getSimpleName());
            }
        }
    }
    public void jouerPartie() {
        // Initialisation
        initialiser(); // S'assure que les joueurs et les cartes sont prêts
        desamorceursRestants = joueurs.length; // Un désamorceur par joueur au début

        while (!bombeTrouvee && desamorceursRestants > 0) {
            jouerManche();
            if (bombeTrouvee) {
                System.out.println("L'équipe Moriarty gagne !");
                break;
            } else if (desamorceursRestants == 0) {
                System.out.println("L'équipe Sherlock gagne !");
                break;
            }

            // Préparation pour la prochaine manche
            reinitialiserDeckEtDistribuer();
        }
    }

  

    private void jouerManche() {
        int joueurActuelIndex = 0;

        for (int tour = 0; tour < joueurs.length; tour++) { // Une manche dure pour un nombre de tours égal au nombre de joueurs
            Joueur joueurActuel = joueurs[joueurActuelIndex];
            System.out.println("\nC'est le tour de " + joueurActuel.getNom() + ".");
            
            // Le joueur actuel choisit un joueur cible
            Joueur joueurCible = choisirJoueur(joueurActuelIndex);
            Carte carteRevelee = choisirCarteDuJoueur(joueurCible); // Laissez le joueur actuel choisir une carte du joueur cible
            assert carteRevelee != null;
            carteRevelee.estRetourner(); // Révèle l'effet de la carte
            
            // Vérifiez si c'est une Bombe ou un Désamorceur
            if (carteRevelee instanceof Desamorceur) {
                desamorceursRestants--;
                System.out.println("Un désamorceur a été trouvé. Désamorceurs restants : " + desamorceursRestants);
                if (desamorceursRestants == 0) {
                    System.out.println("Tous les désamorceurs ont été trouvés. L'équipe Sherlock gagne !");
                    break; // Sortie de la boucle, fin de la manche et de la partie
                }
            } else if (carteRevelee instanceof Bomb) {
                bombeTrouvee = true;
                System.out.println("La bombe a été trouvée. L'équipe Moriarty gagne !");
                break; // Sortie de la boucle, fin de la manche et de la partie
            }

            // Le joueur cible devient le prochain joueur actuel
            joueurActuelIndex = indexOf(joueurs, joueurCible);
        }

        // Remélanger les cartes et les distribuer pour la prochaine manche si la partie n'est pas terminée
        if (!bombeTrouvee && desamorceursRestants > 0) {
            reinitialiserDeckEtDistribuer();
        }
    }

    // Méthode pour obtenir l'index d'un joueur dans le tableau joueurs
    private int indexOf(Joueur[] joueurs, Joueur joueur) {
        for (int i = 0; i < joueurs.length; i++) {
            if (joueur.equals(joueurs[i])) {
                return i;
            }
        }
        return -1; // Si le joueur n'est pas trouvé, cela indiquerait une erreur
    }

    private Carte choisirCarteDuJoueur(Joueur joueurCible) {
        List<Carte> cartesDuJoueurCible = joueurCible.getCartes();
        int nombreDeCartes = cartesDuJoueurCible.size();
        
        if (nombreDeCartes == 0) {
            System.out.println(joueurCible.getNom() + " n'a pas de cartes !");
            return null; // Vous pourriez avoir besoin de gérer ce cas.
        }
        
        // Demander au joueur actuel de choisir une carte du joueur cible
        System.out.println(joueurCible.getNom() + " a " + nombreDeCartes + " cartes. Choisissez un numéro de carte entre 1 et " + nombreDeCartes + ":");
        int choixCarte = -1;
        while (choixCarte < 1 || choixCarte > nombreDeCartes) {
            try {
                choixCarte = scanner.nextInt();
                if (choixCarte < 1 || choixCarte > nombreDeCartes) {
                    System.out.println("Numéro invalide. Veuillez choisir un numéro de carte entre 1 et " + nombreDeCartes + ":");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ce n'est pas un nombre valide. Veuillez réessayer.");
                scanner.next(); // Important pour éviter une boucle infinie
            }
        }
        
        return cartesDuJoueurCible.remove(choixCarte - 1); // Retirer la carte choisie de la main du joueur cible
    }



    private Joueur choisirJoueur(int joueurActuelIndex) {
        System.out.println("Choisissez un joueur à cibler (par numéro) :");
        for (int i = 0; i < joueurs.length; i++) {
            if (i != joueurActuelIndex) { // Ne pas permettre de s'auto-choisir
                System.out.println(i + ": " + joueurs[i].getNom());
            }
        }
        int choix;
        do {
            System.out.println("Entrez le numéro du joueur : ");
            while (!scanner.hasNextInt()) {
                System.out.println("Ce n'est pas un numéro valide. Essayez à nouveau : ");
                scanner.next(); // cela est important !
            }
            choix = scanner.nextInt();
        } while (choix == joueurActuelIndex || choix < 0 || choix >= joueurs.length); // Valider le choix

        return joueurs[choix];
    }


    private void reinitialiserDeckEtDistribuer() {
        // Remélangez le deck et redistribuez les cartes comme au début de la partie
    }
}
    
 

