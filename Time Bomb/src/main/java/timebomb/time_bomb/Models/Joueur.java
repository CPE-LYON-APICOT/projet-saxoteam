package timebomb.time_bomb.Models;
import java.util.ArrayList;
import java.util.List;

//Joueur.java
public abstract class Joueur {
 protected String nom;
 protected boolean sectateur;
 protected List<Carte> cartes;

 public Joueur(String nom) {
     this.nom = nom;
     this.cartes = new ArrayList<>();
     this.sectateur = false;
 }

 public abstract void jouer();

 // Méthode pour ajouter une carte à un joueur
 public void ajouterCarte(Carte carte) {
     cartes.add(carte);
 }
 
 public String getNom() {
     return nom;
 }

 // Méthode pour retourner une carte
 public void retournerCarte() {
     // Supposons que le joueur retourne la première carte
     if (!cartes.isEmpty()) {
         Carte carte = cartes.remove(0);
         carte.estRetourner();
     }
 }

 public abstract String retournerRole();

 public List<Carte> getCartes() {
     return cartes;
 }

 // Redéfinissez toString pour imprimer le rôle du joueur
 @Override
 public String toString() {
     String statut = this instanceof Sherlock ? "Moriaty" : "Sherlock";
     return nom + " (" + statut + ")";
 }
 
 public Carte choisirCarteAuHasard() {
     if (!cartes.isEmpty()) {
         int index = (int) (Math.random() * cartes.size());
         return cartes.get(index);
     }
	return null;
 }
 
 
 public Carte choisirCarte(int index) {
     if (index >= 0 && index < cartes.size()) {
         return cartes.remove(index); // Retourne et enlève la carte de la main
     }
     return null; // Ou gérer autrement si l'index est invalide
 }

 public Boolean aLeSecateur(){
     return this.sectateur;
 }

 public void setSectateur(boolean sectateur) {
     this.sectateur = sectateur;
 }

 public void clearCard(){
     cartes.clear();
 }

}



