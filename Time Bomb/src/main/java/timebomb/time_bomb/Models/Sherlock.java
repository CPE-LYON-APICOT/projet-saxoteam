package timebomb.time_bomb.Models;


//Sherlock.java
public class Sherlock extends Joueur {
 public Sherlock(String nom) {
     super(nom);
 }

 public void jouer() {
     // Logique spécifique à Sherlock pour jouer un tour
     System.out.println(nom + " est en train de jouer.");
     retournerCarte();
 }
}