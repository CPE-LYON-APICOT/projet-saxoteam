package timebomb.time_bomb.Models;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

//Carte.java
public abstract class Carte {
    ImageView imageView = new ImageView(Objects.requireNonNull(getClass().getResource("/timebomb/time_bomb/Image/time-bomb-cables.jpg")).toExternalForm());
    // Méthode abstraite qui sera implémentée par les sous-classes
    boolean estRetourner;

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public abstract void estRetourner();

}