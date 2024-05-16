package timebomb.time_bomb.Models;

import javafx.scene.image.ImageView;

import java.util.Objects;

//Bomb.java
public class Bomb extends Carte {
 public void estRetourner() {
     setImageView(new ImageView(Objects.requireNonNull(getClass().getResource("/timebomb/time_bomb/Image/carte-bombe.jpg")).toExternalForm()));
     this.estRetourner = true;
     System.out.println("Boom! Une bombe a explosé.");
 }

    @Override
    public ImageView view() {
        return new ImageView(Objects.requireNonNull(getClass().getResource("/timebomb/time_bomb/Image/carte-bombe.jpg")).toExternalForm());
    }

    @Override
    public String toString() {
        return "Bombe";
    }
}

