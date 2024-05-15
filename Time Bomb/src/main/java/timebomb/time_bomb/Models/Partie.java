package timebomb.time_bomb.Models;

import java.util.*;


public class Partie {
    private final Joueur[] joueurs;
    private int nbManche;
    private Joueur joueurActuel;
    private List<Joueur> lesJoueurs;
    private List<Carte> deck; // Assumez que cela est rempli comme avant
    private final int toursJoues = 0;
    public int desamorceursRestants = 0;
    private boolean bombeTrouvee = false;
    private final Scanner scanner = new Scanner(System.in); // Pour lire l'entrée utilisateur
    private String status;

    private int nbTour;
    
    
    public Partie(int nombreDeJoueurs) {
        joueurs = new Joueur[nombreDeJoueurs];
    }

    public Partie( List<Joueur> joueurs) {
        this.joueurs = joueurs.toArray(new Joueur[0]);
        this.lesJoueurs = joueurs;
        setNbTour();

    }

    public void initialiser() {
        // Créer un jeu de cartes
        List<Carte> deck = creerDeck(joueurs.length);
        
        // Assigner les rôles
        assignerRoles();
        
        // Distribuer les cartes
        distribuerCartes(deck);
    }

    public void initialize(){
        this.deck = creerDeck(lesJoueurs.size());
        distribuCartes(this.deck);
        setNbManche();
        int index = (int) (Math.random() * (lesJoueurs.size()-1));
        setJoueursSecateur(index,true);
        joueurActuel = lesJoueurs.get(index);
        this.status = "en cours";
    }

    public void setJoueursSecateur(int i,boolean bool){
        lesJoueurs.get(i).setSectateur(bool);
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
        this.nbTour = lesJoueurs.size();
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
    
    private void distribuerCartes(List<Carte> deck) {
        int indexCarte = 0;
        for (Joueur joueur : joueurs) {
            joueur.clearCard();
            for (int j = 0; j < 5 && indexCarte < deck.size(); j++) {
                joueur.ajouterCarte(deck.get(indexCarte++));
            }
        }
    }



    private void distribuCartes(List<Carte> deck) {
        int indexCarte = 0;
        for (Joueur joueur : lesJoueurs) {
            for (int j = 0; j < 5 && indexCarte < deck.size(); j++) {
                joueur.ajouterCarte(deck.get(indexCarte++));
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
        this.nbManche = lesJoueurs.size();
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
        }
        else if(nbManche == 0){
            if(!verifDesamorceur()){
                this.status = "fin desamorceur";
            }else{
                this.status = "fin sans desamorseur";
            }
        }
    }

    public List<Carte> getJoueurDeck(int idJoueur){
        return joueurs[idJoueur].getCartes();
    }

    public List<Carte> getDeck(){
        return this.deck;
    }
    
    public boolean verifDesamorceur(){
        System.out.println(desamorceursRestants);
        if (desamorceursRestants < 0){
            this.status = "fin desamorceur";
            return false;
        }
        return true;
    }

}
    
 

