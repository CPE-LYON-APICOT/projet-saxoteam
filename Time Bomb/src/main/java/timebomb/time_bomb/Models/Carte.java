package timebomb.time_bomb.Models;

import javafx.scene.image.Image;

//Carte.java
public abstract class Carte {
    //private Image dosImage = new Image(String.valueOf(getClass().getResource("timebomb/time_bomb/Image/carteDos.png")));
    // Méthode abstraite qui sera implémentée par les sous-classes
    public abstract void estRetourner();
}