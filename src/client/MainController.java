package client;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class MainController {
    public void exit(ActionEvent e) {
        ((Stage) ((Node)e.getSource()).getScene().getWindow()).close();
    }
}
