package timebomb.time_bomb.Models;

import javafx.scene.image.ImageView;

import java.util.Objects;

//Carte_Null.java
public class Carte_Null extends Carte {
 // Implémentation de la méthode 'estRetourner'
 public void estRetourner() {
  setImageView(new ImageView(Objects.requireNonNull(getClass().getResource("/timebomb/time_bomb/Image/carteDos.png")).toExternalForm()));
  this.estRetourner = true;
 }

 @Override
 public String toString() {
  return "Carte Null";
 }
}
