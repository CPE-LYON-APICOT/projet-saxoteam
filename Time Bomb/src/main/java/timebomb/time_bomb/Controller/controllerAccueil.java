package timebomb.time_bomb.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class controllerAccueil {

        @FXML
        public Button buttonCancel;

        @FXML
        public Button buttonValide;

        @FXML
        public ComboBox<Integer> idList;
        private ObservableList<Integer> observableList;

        @FXML
        void quitApp(ActionEvent event) {
        }

        public void initialize() {
                // Création d'une liste d'entiers de 1 à 5
                observableList = FXCollections.observableArrayList(1, 2, 3, 4, 5);
                idList.setItems(observableList);

                // Sélectionner le premier élément par défaut
                idList.getSelectionModel().selectFirst();


        }


}
