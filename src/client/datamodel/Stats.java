package client.datamodel;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Stats {
    private double money, income, multiplier;
    private Business[] businessArray;

    public Stats(double money, double income, double multiplier) {
        this.money = money;
        this.income = income;
        this.multiplier = multiplier;
        this.businessArray = new Business[]{
                new Business("Business 1", 1, 0, 15d, 15d, 0.1d),
                new Business("Business 2", 2, 0, 100d, 100d, 1d),
                new Business("Business 3", 3, 0, 1100d, 1100d, 8d),
                new Business("Business 4", 4, 0, 12000d, 12000d, 47d),
                new Business("Business 5", 5, 0, 130000d, 130000d, 260d),
                new Business("Business 6", 6, 0, 1400000d, 1400000d, 1400d),
                new Business("Business 7", 7, 0, 20000000d, 20000000d, 7800d)};
    }

    public void saveGame() {
        Path path = Paths.get("1_saveGame.json");
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path)) {
            StringBuilder businessJSON = new StringBuilder();
            businessJSON.append("\n");
            for (int i = 0; i < this.businessArray.length; i++) {
                businessJSON.append(String.format("{\"id\": %s, \"owned\": %s, \"cost\": %s, \"initialCost\": %s}",
                        this.businessArray[i].getId(), this.businessArray[i].getOwned(), this.businessArray[i].getCost(),
                        this.businessArray[i].getInitialCost()));
                if(i != this.businessArray.length - 1) businessJSON.append(",\n");
            }
            bufferedWriter.write(String.format("""
                            {"money": %s,
                             "income": %s,
                             "multiplier": %s,
                             "businessArray": [%s]}""", this.money,
                    this.income, this.multiplier, businessJSON.toString()));
        } catch (IOException e) {
            System.out.println("Could not save game");
        }
    }

    public void addMoney(double amount) {
        this.money += amount;
    }
    public void subtractMoney(double amount) {
        this.money -= amount;
    }
    public void addIncome(double amount) {
        this.income += amount;
    }
    public void multiplyMultiplier(double amount) {
        this.multiplier *= amount;
    }

    public double getMoney() {
        return money;
    }
    public double getIncome() {
        return income;
    }
    public double getMultiplier() {
        return multiplier;
    }
    public Business getBusiness(int index) {
        return businessArray[index];
    }
}
