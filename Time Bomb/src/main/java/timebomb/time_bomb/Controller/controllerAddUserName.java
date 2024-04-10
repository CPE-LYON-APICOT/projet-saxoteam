package timebomb.time_bomb.Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

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
        grid.addRow(0,textField);
        System.out.println(grid.getRowCount());
        for (int i = 1; i < this.number; i++) {
            TextField textFieldAdd = new TextField();
            grid.addRow(i,textFieldAdd);
        }
    }


    @FXML
    void quitteHandlers(ActionEvent event) {
        Platform.exit();

    }


}
