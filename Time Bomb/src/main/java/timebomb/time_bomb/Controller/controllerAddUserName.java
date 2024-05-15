package timebomb.time_bomb.Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import timebomb.time_bomb.Models.Joueur;
import timebomb.time_bomb.Models.Moriarty;
import timebomb.time_bomb.Models.Sherlock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class controllerAddUserName {

    @FXML
    private Button butonValide;

    @FXML
    private Button buttonQuitte;

    @FXML
    private Label numberLabel;

    @FXML
    private GridPane grid;


    private Integer number;


    public void initialize(Integer number) {
        this.number = number;
        numberLabel.setText("ajouter les nom des "+ number.toString()+" joueurs");

        TextField textField = new TextField();
        textField.setId("id0");
        grid.addRow(0,textField);
        for (int i = 1; i < this.number; i++) {
            TextField textFieldAdd = new TextField();
            textFieldAdd.setId("id"+i);
            grid.addRow(i,textFieldAdd);
        }
    }


    @FXML
    void quitteHandlers(ActionEvent event) {
        Platform.exit();
        System.exit(0);

    }


    public void valideHandlers(ActionEvent actionEvent) throws IOException {
        List<Joueur> values = new ArrayList<>(); // Créez une liste pour stocker les valeurs des TextField

        for (int i = 0; i < number; i++) {
            TextField textField = (TextField) grid.lookup("#id" + i);
            if (Objects.equals(textField.getText(), "")){
                return;
            }


            if (number < 6){
                if (values.size()<=2){
                    values.add(new Moriarty(textField.getText()));
                }else{
                    values.add(new Sherlock(textField.getText()));
                }
            }else{
                if (values.size()<=3){
                    values.add(new Moriarty(textField.getText()));
                }else{
                    values.add(new Sherlock(textField.getText()));
                }
            }
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/timebomb/time_bomb/GameTable.fxml"));
        Parent root = loader.load();

        controllerGameTable controllerGameTable = loader.getController();
        controllerGameTable.initialize(values);

        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
