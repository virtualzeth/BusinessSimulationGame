package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    @FXML
    private Label counterLabel;
    @FXML
    private Pane businessListPane1, businessListPane2, businessListPane3;
    @FXML
    private Button businessPrevButton, businessNextButton;

    private Pane[] paneArray;
    private int count, multiplier, businessListCurrentPane;

    public void handleBusinessListNavigationButtonAction(ActionEvent e) {
        if(e.getSource() == businessNextButton && businessListCurrentPane + 1 <= paneArray.length - 1) {
            businessListCurrentPane++;
        } else if(e.getSource() == businessPrevButton && businessListCurrentPane - 1 >= 0) {
            businessListCurrentPane--;
        }
        this.paneArray[businessListCurrentPane].toFront();
    }

    public void incrementCounter(ActionEvent e) {
        count++;
        counterLabel.setText(String.valueOf(this.count * this.multiplier));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        count = 0;
        multiplier = 1;
        businessListCurrentPane = 0;
        paneArray = new Pane[]{businessListPane1, businessListPane2, businessListPane3};
    }
}
