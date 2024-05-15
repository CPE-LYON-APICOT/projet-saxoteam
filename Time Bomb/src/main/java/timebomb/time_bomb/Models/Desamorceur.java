package timebomb.time_bomb.Models;

import javafx.scene.image.ImageView;

import java.util.Objects;

//Desamorceur.java
public class Desamorceur extends Carte {
 public void estRetourner() {
     setImageView(new ImageView(Objects.requireNonNull(getClass().getResource("/timebomb/time_bomb/Image/carte-desamorcage.jpg")).toExternalForm()));
     this.estRetourner = true;
     System.out.println("Ouf! Désamorcé une bombe.");
 }

    @Override
    public String toString() {
        return "Desamorceur";
    }
}
