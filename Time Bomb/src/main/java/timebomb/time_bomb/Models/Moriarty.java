package timebomb.time_bomb.Models;

//Moriarty.java
public class Moriarty extends Joueur {
 public Moriarty(String nom) {
     super(nom);
 }

 public Moriarty(Joueur joueur) {
        super(joueur.getNom());
    }

 public void jouer() {
     // Logique spécifique à Moriarty pour jouer un tour
     System.out.println(nom + " est en train de jouer.");
     retournerCarte();
 }

}