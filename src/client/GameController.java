package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    @FXML
    private Label counterLabel;
    private int count;
    private int multiplier;

    public void incrementCounter(ActionEvent e) {
        count++;
        counterLabel.setText(String.valueOf(this.count * this.multiplier));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        count = 0;
        multiplier = 1;
    }
}
