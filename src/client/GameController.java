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
    private Label counterLabel, cpsLabel, businessCostLabel0, businessOwnedLabel0, businessCostLabel1, businessOwnedLabel1,
            businessCostLabel2, businessOwnedLabel2, businessCostLabel3, businessOwnedLabel3, businessCostLabel4,
            businessOwnedLabel4, businessCostLabel5, businessOwnedLabel5, businessCostLabel6, businessOwnedLabel6;
    @FXML
    private Pane businessListPane1, businessListPane2, businessListPane3;
    @FXML
    private Button businessPrevButton, businessNextButton, buyBusinessButton0, buyBusinessButton1, buyBusinessButton2,
            buyBusinessButton3, buyBusinessButton4, buyBusinessButton5, buyBusinessButton6;

    private double money, income, multiplier;
    private int businessListCurrentPane;
    private Pane[] paneArray;
    private Business[] businessArray;
    private Label[] businessCostLabelArray, businessOwnedLabelArray;


    public void handleBusinessBuyAction(ActionEvent e) {
        if(e.getSource() == buyBusinessButton0 && this.money >= this.businessArray[0].getCost()) {
            addBusiness(0);
        } else if(e.getSource() == buyBusinessButton1 && this.money >= this.businessArray[1].getCost()) {
            addBusiness(1);
        } else if(e.getSource() == buyBusinessButton2 && this.money >= this.businessArray[2].getCost()) {
            addBusiness(2);
        } else if(e.getSource() == buyBusinessButton3 && this.money >= this.businessArray[3].getCost()) {
            addBusiness(3);
        } else if(e.getSource() == buyBusinessButton4 && this.money >= this.businessArray[4].getCost()) {
            addBusiness(4);
        } else if(e.getSource() == buyBusinessButton5 && this.money >= this.businessArray[5].getCost()) {
            addBusiness(5);
        } else if(e.getSource() == buyBusinessButton6 && this.money >= this.businessArray[6].getCost()) {
            addBusiness(6);
        }
        updateCounter();
    }

    public void addBusiness(int index) {
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
        this.money = this.money + 1 * this.multiplier;
        updateCounter();
    }

    public void updateCounter() {
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
