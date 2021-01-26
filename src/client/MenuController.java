package client;

import client.datamodel.Stats;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    private void changeScene(ActionEvent e, String path) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource(path));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public void newGame(ActionEvent e) throws IOException {
        Stats.setNewGame(true);
        changeScene(e, "gameView.fxml");
    }
    public void loadGame(ActionEvent e) throws IOException {
        Stats.setNewGame(false);
        changeScene(e, "gameView.fxml");
    }
    public void exit(ActionEvent e) {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
