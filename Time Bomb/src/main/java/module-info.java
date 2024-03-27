module timebomb.time_bomb {
    requires javafx.controls;
    requires javafx.fxml;

    //requires org.controlsfx.controls;
    //requires com.dlsc.formsfx;
    //requires net.synedra.validatorfx;
    //requires org.kordamp.bootstrapfx.core;

    opens timebomb.time_bomb to javafx.fxml;
    exports timebomb.time_bomb;
    exports timebomb.time_bomb.Models;
    opens timebomb.time_bomb.Models to javafx.fxml;
}