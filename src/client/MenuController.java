package client;

import client.datamodel.Stats;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button menu_loadGame_button;

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
        if(!new File("game.json").isFile()) anchorPane.getChildren().remove(menu_loadGame_button);
    }
}
