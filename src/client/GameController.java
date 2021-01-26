package client;

import client.datamodel.Stats;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    @FXML
    private Label counterLabel, cpsLabel, businessCostLabel0, businessOwnedLabel0, businessCostLabel1, businessOwnedLabel1,
            businessCostLabel2, businessOwnedLabel2, businessCostLabel3, businessOwnedLabel3, businessCostLabel4,
            businessOwnedLabel4, businessCostLabel5, businessOwnedLabel5, businessCostLabel6, businessOwnedLabel6;
    @FXML
    private Pane businessListPane1, businessListPane2, upgradeListPane;
    @FXML
    private Button saveAndExit, businessPrevButton, businessNextButton, buyBusinessButton0, buyBusinessButton1, buyBusinessButton2,
            buyBusinessButton3, buyBusinessButton4, buyBusinessButton5, buyBusinessButton6;

    private Stats stats;
    private int businessListCurrentPane;
    private Pane[] paneArray;
    private Label[] businessCostLabelArray, businessOwnedLabelArray;
    private final ArrayList<Pane> upgradePanes = new ArrayList<>();
    private final ArrayList<Button> upgradeButtons = new ArrayList<>();

    public void handleUpgradeBuyAction(ActionEvent e) {
        Object source = e.getSource();
        if (upgradeButtons.get(0).equals(source) && this.stats.getMoney() >= this.stats.getUpgradesList().get(0).getCost()) {
            cursorUpgrade(0, 2);
            moveUpgradePanes(0);
        } else if (upgradeButtons.get(1).equals(source) && this.stats.getMoney() >= this.stats.getUpgradesList().get(1).getCost()) {
            cursorUpgrade(1, 3);
            moveUpgradePanes(1);
        }
        updateCounter();
    }
    private void cursorUpgrade(int index, double multiplier) {
        this.stats.subtractMoney(this.stats.getUpgradesList().get(index).getCost());
        this.stats.multiplyMultiplier(multiplier);
        this.stats.getUpgradesList().get(index).setOwned(true);
    }
    private void moveUpgradePanes(int index) {
        upgradeListPane.getChildren().remove(upgradePanes.get(index));
        for (int i = index + 1; i < upgradePanes.size(); i++) {
            double y = upgradePanes.get(i).getLayoutY();
            upgradePanes.get(i).setLayoutY(y - 110);
        }
    }

    public void handleBusinessBuyAction(ActionEvent e) {
        Object source = e.getSource();
        if(source.equals(buyBusinessButton0) && this.stats.getMoney() >= this.stats.getBusiness(0).getCost()) {
            addBusiness(0);
        } else if(source.equals(buyBusinessButton1) && this.stats.getMoney() >= this.stats.getBusiness(1).getCost()) {
            addBusiness(1);
        } else if(source.equals(buyBusinessButton2) && this.stats.getMoney() >= this.stats.getBusiness(2).getCost()) {
            addBusiness(2);
        } else if(source.equals(buyBusinessButton3) && this.stats.getMoney() >= this.stats.getBusiness(3).getCost()) {
            addBusiness(3);
        } else if(source.equals(buyBusinessButton4) && this.stats.getMoney() >= this.stats.getBusiness(4).getCost()) {
            addBusiness(4);
        } else if(source.equals(buyBusinessButton5) && this.stats.getMoney() >= this.stats.getBusiness(5).getCost()) {
            addBusiness(5);
        } else if(source.equals(buyBusinessButton6) && this.stats.getMoney() >= this.stats.getBusiness(6).getCost()) {
            addBusiness(6);
        }
        updateCounter();
    }

    private void addBusiness(int index) {
        double cost = this.stats.getBusiness(index).getCost();
        this.stats.getBusiness(index).buy();
        this.stats.subtractMoney(cost);
        this.updateBusinessLabels(index);
        this.stats.addIncome(this.stats.getBusiness(index).getIncomeIncrementValue());
    }

    private void updateBusinessLabels(int index) {
        this.businessOwnedLabelArray[index].setText("Owned: " + this.stats.getBusiness(index).getOwned());
        this.businessCostLabelArray[index].setText((int) Math.ceil(this.stats.getBusiness(index).getCost()) + "$");
    }

    public void handleBusinessListNavigationButtonAction(ActionEvent e) {
        if(e.getSource() == businessNextButton && businessListCurrentPane + 1 <= paneArray.length - 1) {
            businessListCurrentPane++;
            this.paneArray[businessListCurrentPane - 1].setVisible(false);
            this.paneArray[businessListCurrentPane].setVisible(true);
        } else if(e.getSource() == businessPrevButton && businessListCurrentPane - 1 >= 0) {
            businessListCurrentPane--;
            this.paneArray[businessListCurrentPane + 1].setVisible(false);
            this.paneArray[businessListCurrentPane].setVisible(true);
        }
        this.paneArray[businessListCurrentPane].toFront();
    }

    private void updateCounter() {
        counterLabel.setText(String.valueOf((int) Math.floor(this.stats.getMoney())));
        cpsLabel.setText((Math.floor(this.stats.getIncome() * 10) / 10) + " CpS");
    }

    public void incrementCounter() {
        this.stats.addMoney(1 * this.stats.getMultiplier());
        updateCounter();
    }

    public void saveAndExit() {
        this.stats.saveGame();
        ((Stage) saveAndExit.getScene().getWindow()).close();
        System.exit(1);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stats = new Stats();

        businessListCurrentPane = 0;

        paneArray = new Pane[]{businessListPane1, businessListPane2};

        businessOwnedLabelArray = new Label[]{businessOwnedLabel0, businessOwnedLabel1, businessOwnedLabel2,
                businessOwnedLabel3, businessOwnedLabel4, businessOwnedLabel5, businessOwnedLabel6};
        businessCostLabelArray = new Label[]{businessCostLabel0, businessCostLabel1, businessCostLabel2,
                businessCostLabel3, businessCostLabel4, businessCostLabel5, businessCostLabel6};

        for (int i = 0; i < this.stats.getUpgradesList().size(); i++) {
            Pane pane = new Pane();
            pane.setId("upgradePane" + i);
            pane.setPrefWidth(300);
            pane.setPrefHeight(100);
            pane.setStyle("-fx-border-style: solid; -fx-border-width: 4px; -fx-border-color: #008080;" +
                    "-fx-background-color: white");
            pane.setLayoutY(110 * i);

            Label title = new Label();
            title.setText(this.stats.getUpgradesList().get(i).getName());
            title.setFont(Font.font("Roboto", 24));
            title.setPrefWidth(200);
            title.setLayoutX(49);
            title.setLayoutY(14);
            title.setAlignment(Pos.CENTER);

            Label desc = new Label();
            desc.setText(this.stats.getUpgradesList().get(i).getDescription());
            desc.setPrefWidth(280);
            desc.setLayoutX(10);
            desc.setLayoutY(40);
            desc.setAlignment(Pos.CENTER);

            Label cost = new Label();
            cost.setText((int) this.stats.getUpgradesList().get(i).getCost() + "$");
            cost.setPrefWidth(100);
            cost.setLayoutX(100);
            cost.setLayoutY(65);
            cost.setAlignment(Pos.CENTER);

            Button buy = new Button("Buy");
            buy.setId("buyUpgradeButton" + i);
            buy.setLayoutX(250);
            buy.setLayoutY(61);
            buy.setOnAction(this::handleUpgradeBuyAction);
            upgradeButtons.add(buy);

            pane.getChildren().add(title);
            pane.getChildren().add(desc);
            pane.getChildren().add(cost);
            pane.getChildren().add(buy);
            upgradeListPane.getChildren().add(pane);
            upgradePanes.add(pane);
        }

        updateCounter();
        for (int i = 0; i < businessCostLabelArray.length; i++) {
            updateBusinessLabels(i);
        }
        for (int i = 0; i < this.stats.getUpgradesList().size(); i++) {
            if(this.stats.getUpgradesList().get(i).isOwned()) {
                moveUpgradePanes(i);
            }
        }

        (new Thread(() -> {
            while (true) {
                this.stats.addMoney(this.stats.getIncome() * 0.1);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(this::updateCounter);
            }
        })).start();
    }
}
