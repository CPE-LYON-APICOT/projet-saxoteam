package timebomb.time_bomb.Models;

//Moriarty.java
public class Moriarty extends Joueur {
 public Moriarty(String nom) {
     super(nom);
     this.sectateur = true; // Supposons que Moriarty est un sectateur
 }

 public void jouer() {
     // Logique spécifique à Moriarty pour jouer un tour
     System.out.println(nom + " est en train de jouer.");
     retournerCarte();
 }
}