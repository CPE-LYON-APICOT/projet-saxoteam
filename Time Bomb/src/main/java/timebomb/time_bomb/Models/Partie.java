package timebomb.time_bomb.Models;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import timebomb.time_bomb.Controller.controllerAttributeCard;

import java.io.IOException;
import java.util.*;


public class Partie {
    private final Joueur[] joueurs;
    private int nbManche;
    private Joueur joueurActuel;
    private List<Joueur> lesJoueurs;
    private List<Carte> deck; // Assumez que cela est rempli comme avant
    public int desamorceursRestants = 0;
    private String status;
    private int nbTour;

    //création de l'objet partie
    public Partie( List<Joueur> joueurs) {
        this.joueurs = joueurs.toArray(new Joueur[0]);
        this.lesJoueurs = joueurs;
        setNbTour();

    }

    //Initialisation de la partie ce qui implique la création du deck, la création du nombre de manche, le choix du premier joueur aléatoir.
    public void initialize(){
        this.deck = creerDeck(lesJoueurs.size());
        distribuCartes(this.deck);
        setNbManche();
        int index = (int) (Math.random() * (lesJoueurs.size()-1));
        joueurActuel = lesJoueurs.get(index);
        joueurActuel.setSectateur(true);
        this.status = "en cours";
    }

    public Joueur getJoueurActuel(){
        return this.joueurActuel;
    }

    public List<Joueur> getLesJoueurs(){
        return this.lesJoueurs;
    }

    public void setJoueurActuel(){
        for (Joueur joueur: lesJoueurs) {
            if (joueur.sectateur){
                this.joueurActuel = joueur;
            }
        }
    }

    public void setNbTour(){
        this.nbTour = lesJoueurs.size()-1;
    }

    public int getNbTour(){
        return this.nbTour;
    }

    public int getNbManche(){ return this.nbManche;}

    public int getDesamorceursRestants() {
        return this.desamorceursRestants;
    }

    private List<Carte> creerDeck(int nombreDeJoueurs) {
        List<Carte> deck = new ArrayList<>();
        deck.add(new Bomb()); // Une seule carte Bomb
        for (int i = 0; i < nombreDeJoueurs; i++) {
            deck.add(new Desamorceur()); // Un Desamorceur par joueur
            desamorceursRestants++;
        }
        int nombreDeCartesNull = nombreDeJoueurs * 5 - (nombreDeJoueurs + 1); // Le reste sont des Carte_Null
        for (int i = 0; i < nombreDeCartesNull; i++) {
            deck.add(new Carte_Null());
        }
        Collections.shuffle(deck);
        return deck;
    }

    private void distribuCartes(List<Carte> deck) {
        int nombreDeJoueurs = lesJoueurs.size();
        int nombreDeCartesParJoueur = deck.size() / nombreDeJoueurs;
        int indexCarte = 0;

        for (Joueur joueur : lesJoueurs) {
            joueur.clearCard();
            for (int j = 0; j < nombreDeCartesParJoueur; j++) {
                joueur.ajouterCarte(deck.get(indexCarte++));
            }
        }

        while (indexCarte < deck.size()) {
            for (Joueur joueur : lesJoueurs) {
                joueur.ajouterCarte(deck.get(indexCarte++));
                if (indexCarte >= deck.size()) {
                    break;
                }
            }
        }
    }
    
    private void assignerRoles() {
        int half = joueurs.length / 2;
        for (int i = 0; i < half; i++) {
            joueurs[i] = new Sherlock(joueurs[i]);
        }
        for (int i = half; i < joueurs.length; i++) {
            joueurs[i] = new Moriarty(joueurs[i]);
        }
        // Si le nombre de joueurs est différent de 4, ajouter un Sherlock supplémentaire
        if (joueurs.length != 4) {
            joueurs[joueurs.length - 1] = new Sherlock(joueurs[joueurs.length - 1]);
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


    public void setNbManche(){
        this.nbManche = lesJoueurs.size()-1;
    }

    private void reinitialiserDeckEtDistribuer() {
        deck.removeIf(carte -> carte.estRetourner);
        distribuCartes(deck);
    }

    public void removeOneDesamorceur(){
        if ( desamorceursRestants > 0){
            this.desamorceursRestants += -1;
        }
    }

    public void setNewManche(){
        if (nbManche>0){
            reinitialiserDeckEtDistribuer();
            nbManche--;
            setNbTour();
        }
        else if(nbManche == 0){
            if(verifDesamorceur()){
                this.status = "fin desamorceur";
            }else{
                this.status = "fin sans desamorseur";
            }
        }
    }

    public List<Carte> getDeck(){
        return this.deck;
    }
    
    public boolean verifDesamorceur(){
        return desamorceursRestants <= 0;
    }

    public boolean revealBomb() {
        for (Carte carte : getDeck()) {
            if (carte instanceof Bomb && carte.isEstRetourner()) {
                return true;
            }
        }
        return false;
    }

    public void isMancheFinit(){
        if(nbTour>0){
            nbTour--;
        }else{
            setNewManche();
            this.showRoleAndCard();
        }
    }

    public void showRoleAndCard(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/timebomb/time_bomb/attributCard.fxml"));
            Parent root = (Parent) fxmlLoader.load();


            controllerAttributeCard controllerAttributeCard = fxmlLoader.getController();
            controllerAttributeCard.initialize(this);


            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
    
 

