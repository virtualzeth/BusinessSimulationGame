package client;

import client.datamodel.Business;
import client.datamodel.Upgrade;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

// TODO Add other fun ways to earn coins
public class GameController implements Initializable {
    @FXML
    private Label counterLabel, cpsLabel, businessCostLabel0, businessOwnedLabel0, businessCostLabel1, businessOwnedLabel1,
            businessCostLabel2, businessOwnedLabel2, businessCostLabel3, businessOwnedLabel3, businessCostLabel4,
            businessOwnedLabel4, businessCostLabel5, businessOwnedLabel5, businessCostLabel6, businessOwnedLabel6;
    @FXML
    private Pane businessListPane1, businessListPane2, businessListPane3, upgradeListPane;
    @FXML
    private Button businessPrevButton, businessNextButton, buyBusinessButton0, buyBusinessButton1, buyBusinessButton2,
            buyBusinessButton3, buyBusinessButton4, buyBusinessButton5, buyBusinessButton6;

    private double money, income, multiplier;
    private int businessListCurrentPane;
    private Pane[] paneArray;
    private Business[] businessArray;
    private Label[] businessCostLabelArray, businessOwnedLabelArray;
    private ArrayList<Upgrade> upgradeList;
    private final ArrayList<Pane> upgradePanes = new ArrayList<>();
    private final ArrayList<Button> upgradeButtons = new ArrayList<>();

    public void handleUpgradeBuyAction(ActionEvent e) {
        Object source = e.getSource();
        if (upgradeButtons.get(0).equals(source) && this.money >= this.upgradeList.get(0).getCost()) {
            cursorUpgrade(0, 2);
            moveUpgradePanes(0);
        } else if (upgradeButtons.get(1).equals(source) && this.money >= this.upgradeList.get(1).getCost()) {
            cursorUpgrade(1, 3);
            moveUpgradePanes(1);
        }
        updateCounter();
    }
    private void cursorUpgrade(int index, double multiplier) {
        this.money -= this.upgradeList.get(index).getCost();
        this.multiplier *= multiplier;
        upgradeListPane.getChildren().remove(upgradePanes.get(index));
    }
    private void moveUpgradePanes(int index) {
        for (int i = index + 1; i < upgradePanes.size(); i++) {
            double y = upgradePanes.get(i).getLayoutY();
            upgradePanes.get(i).setLayoutY(y - 110);
        }
    }

    public void handleBusinessBuyAction(ActionEvent e) {
        Object source = e.getSource();
        if(source.equals(buyBusinessButton0) && this.money >= this.businessArray[0].getCost()) {
            addBusiness(0);
        } else if(source.equals(buyBusinessButton1) && this.money >= this.businessArray[1].getCost()) {
            addBusiness(1);
        } else if(source.equals(buyBusinessButton2) && this.money >= this.businessArray[2].getCost()) {
            addBusiness(2);
        } else if(source.equals(buyBusinessButton3) && this.money >= this.businessArray[3].getCost()) {
            addBusiness(3);
        } else if(source.equals(buyBusinessButton4) && this.money >= this.businessArray[4].getCost()) {
            addBusiness(4);
        } else if(source.equals(buyBusinessButton5) && this.money >= this.businessArray[5].getCost()) {
            addBusiness(5);
        } else if(source.equals(buyBusinessButton6) && this.money >= this.businessArray[6].getCost()) {
            addBusiness(6);
        }
        updateCounter();
    }

    private void addBusiness(int index) {
        double cost = this.businessArray[index].getCost();
        this.businessArray[index].buy();
        this.money -= cost;
        this.businessOwnedLabelArray[index].setText("Owned: " + this.businessArray[index].getOwned());
        this.businessCostLabelArray[index].setText((int) Math.ceil(this.businessArray[index].getCost()) + "$");
        this.income += this.businessArray[index].getIncomeIncrementValue();
    }

    public void handleBusinessListNavigationButtonAction(ActionEvent e) {
        if(e.getSource() == businessNextButton && businessListCurrentPane + 1 <= paneArray.length - 1) {
            businessListCurrentPane++;
        } else if(e.getSource() == businessPrevButton && businessListCurrentPane - 1 >= 0) {
            businessListCurrentPane--;
        }
        this.paneArray[businessListCurrentPane].toFront();
    }

    public void incrementCounter(ActionEvent e) {
        this.money += 1 * this.multiplier;
        updateCounter();
    }

    private void updateCounter() {
        counterLabel.setText(String.valueOf((int) Math.floor(this.money)));
        cpsLabel.setText((Math.floor(this.income * 10) / 10) + " CpS");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        money = 100000000;
        income = 0;
        multiplier = 1;
        businessListCurrentPane = 0;

        paneArray = new Pane[]{businessListPane1, businessListPane2, businessListPane3};

        businessArray = new Business[]{new Business("Business 1", 1, 0, 15d, 0.1d),
                new Business("Business 2", 2, 0, 100d, 1d), new Business("Business 3", 3, 0, 1100d, 8d),
                new Business("Business 4", 4, 0, 12000d, 47d), new Business("Business 5", 5, 0, 130000d, 260d),
                new Business("Business 6", 6, 0, 1400000d, 1400d), new Business("Business 7", 7, 0, 20000000d, 7800d)};

        businessOwnedLabelArray = new Label[]{businessOwnedLabel0, businessOwnedLabel1, businessOwnedLabel2,
                businessOwnedLabel3, businessOwnedLabel4, businessOwnedLabel5, businessOwnedLabel6};
        businessCostLabelArray = new Label[]{businessCostLabel0, businessCostLabel1, businessCostLabel2,
                businessCostLabel3, businessCostLabel4, businessCostLabel5, businessCostLabel6};

        upgradeList = new ArrayList<>(Arrays.asList(new Upgrade("Coffee Machine", 200, false, "Your productivity will increase x2"),
                new Upgrade("Extra Monitor", 10000, false, "Your productivity will increase x3")));

        for (int i = 0; i < upgradeList.size(); i++) {
            Pane pane = new Pane();
            pane.setId("upgradePane" + i);
            pane.setPrefWidth(300);
            pane.setPrefHeight(100);
            pane.setStyle("-fx-border-style: solid; -fx-border-width: 4px; -fx-border-color: #008080;");
            pane.setLayoutY(110 * i);

            Label title = new Label();
            title.setText(upgradeList.get(i).getName());
            title.setFont(Font.font("Roboto", 24));
            title.setPrefWidth(200);
            title.setLayoutX(49);
            title.setLayoutY(14);
            title.setAlignment(Pos.CENTER);

            Label desc = new Label();
            desc.setText(upgradeList.get(i).getDescription());
            desc.setPrefWidth(280);
            desc.setLayoutX(10);
            desc.setLayoutY(40);
            desc.setAlignment(Pos.CENTER);

            Label cost = new Label();
            cost.setText((int) upgradeList.get(i).getCost() + "$");
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

        Thread thread = new Thread(() -> {
            while (true) {
                this.money += this.income * 0.1;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(this::updateCounter);
            }
        });
        thread.start();
    }
}
