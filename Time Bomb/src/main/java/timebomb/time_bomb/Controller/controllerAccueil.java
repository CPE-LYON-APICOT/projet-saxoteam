package timebomb.time_bomb.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class controllerAccueil {

        @FXML
        public Button buttonCancel;

        @FXML
        public Button buttonValide;

        @FXML
        public ImageView imageBomb;

        @FXML
        public ChoiceBox<Integer> choiceNumber;
        private ObservableList<Integer> observableList;
        private Integer[] numbers = {4,5,6};

        @FXML
        void quitApp(ActionEvent event) {
        }

        public void initialize() {
                if(choiceNumber!= null) {
                        choiceNumber.getItems().addAll(numbers);
                }
        }



        public void validate(ActionEvent actionEvent) throws IOException {

                Integer number = choiceNumber.getValue();
                if (number != null){

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/timebomb/time_bomb/addUserName.fxml"));
                        Parent root = loader.load();

                        controllerAddUserName controllerAddUserName = loader.getController();
                        controllerAddUserName.initialize(number);

                        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();

                }
        }
}
