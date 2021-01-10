package client;

import components.Business;
import javafx.application.Platform;
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
    private Label counterLabel, soapManufacturerCostLabel, soapManufacturerOwnedLabel;
    @FXML
    private Pane businessListPane1, businessListPane2, businessListPane3;
    @FXML
    private Button businessPrevButton, businessNextButton, buySoapManufacturerButton;

    private double money, income, multiplier;
    private int businessListCurrentPane;
    private Pane[] paneArray;
    private Business[] businessArray;
    private Thread thread;

    public void handleBusinessBuyAction(ActionEvent e) {
        if(e.getSource() == buySoapManufacturerButton && this.money >= this.businessArray[0].getCost()) {
            double cost = this.businessArray[0].getCost();
            this.businessArray[0].buy(money);
            this.money = this.money - cost;
            this.soapManufacturerOwnedLabel.setText("Owned: " + this.businessArray[0].getOwned());
            this.soapManufacturerCostLabel.setText((int) Math.ceil(this.businessArray[0].getCost()) + "$");
            income += this.businessArray[0].getIncomeIncrementValue();
            updateCounter();
        }
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
        this.money = this.money + 1 * this.multiplier;
        updateCounter();
    }

    public void updateCounter() {
        counterLabel.setText(String.valueOf((int) Math.floor(this.money)));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        money = 50;
        income = 0;
        multiplier = 1;
        businessListCurrentPane = 0;
        paneArray = new Pane[]{businessListPane1, businessListPane2, businessListPane3};
        businessArray = new Business[]{new Business("Soap Manufacturer", 1, 0, 50d, 0.1d)};
        updateCounter();

        this.thread = new Thread(() -> {
            while(true) {
                this.money += this.income * 0.1;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(this::updateCounter);
            }
        });
        this.thread.start();
    }
}
