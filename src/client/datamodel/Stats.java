package client.datamodel;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;

public class Stats {
    public static boolean newGame;
    private double money, income, multiplier;
    private Business[] businessArray;
    private LinkedList<Upgrade> upgradesList;

    public Stats() {
        this.money = 0;
        this.income = 0;
        this.multiplier = 1;
        this.businessArray = new Business[]{
                new Business("Business 1", 1, 0, 15d, 15d, 0.1d),
                new Business("Business 2", 2, 0, 100d, 100d, 1d),
                new Business("Business 3", 3, 0, 1100d, 1100d, 8d),
                new Business("Business 4", 4, 0, 12000d, 12000d, 47d),
                new Business("Business 5", 5, 0, 130000d, 130000d, 260d),
                new Business("Business 6", 6, 0, 1400000d, 1400000d, 1400d),
                new Business("Business 7", 7, 0, 20000000d, 20000000d, 7800d)};
        this.upgradesList = new LinkedList<>(Arrays.asList(
                new Upgrade("Coffee Machine", 1, 200d, false, "Your productivity will increase x2"),
                new Upgrade("Extra Monitor", 2, 10000d, false, "Your productivity will increase x3")));
        if(!newGame) this.loadGame();
    }

    private void loadGame() {
        JSONParser jsonParser = new JSONParser();
        try (FileReader fileReader = new FileReader("game.json")) {
            Object object = jsonParser.parse(fileReader);
            JSONObject game = (JSONObject) object;
            this.money = (double) game.get("money");
            this.income = (double) game.get("income");
            this.multiplier = (double) game.get("multiplier");

            JSONArray businessArray = (JSONArray) game.get("businessArray");
            for (int i = 0; i < businessArray.size(); i++) {
                JSONObject business = (JSONObject) businessArray.get(i);
                int id = Integer.parseInt(business.get("id").toString());
                int owned = Integer.parseInt(business.get("owned").toString());
                double cost = Double.parseDouble(business.get("cost").toString());
                this.businessArray[i] = new Business(String.format("Business %s", id), id, owned, cost,
                        this.businessArray[i].getInitialCost(), this.businessArray[i].getIncomeIncrementValue());
            }

            JSONArray upgradesArray = (JSONArray) game.get("upgradesArray");
            for (int i = 0; i < upgradesArray.size(); i++) {
                JSONObject upgrade = (JSONObject) upgradesArray.get(i);
                int id = Integer.parseInt(upgrade.get("id").toString());
                boolean isOwned = Boolean.parseBoolean(upgrade.get("isOwned").toString());
                this.upgradesList.set(i, new Upgrade(
                        this.upgradesList.get(i).getName(), id, this.upgradesList.get(i).getCost(),isOwned,
                        this.upgradesList.get(i).getDescription()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveGame() {
        JSONObject game = new JSONObject();
        game.put("money", this.money);
        game.put("income", this.income);
        game.put("multiplier", this.multiplier);
        JSONArray businessArray = new JSONArray();
        for (int i = 0; i < this.businessArray.length; i++) {
            JSONObject business = new JSONObject();
            business.put("cost", this.businessArray[i].getCost());
            business.put("owned", this.businessArray[i].getOwned());
            business.put("id", this.businessArray[i].getId());
            businessArray.add(business);
        }
        game.put("businessArray", businessArray);
        
        JSONArray upgradesArray = new JSONArray();
        for (int i = 0; i < this.upgradesList.size(); i++) {
            JSONObject upgrade = new JSONObject();
            upgrade.put("id", this.upgradesList.get(i).getId());
            upgrade.put("isOwned", this.upgradesList.get(i).isOwned());
            upgradesArray.add(upgrade);
        }
        game.put("upgradesArray", upgradesArray);

        try(FileWriter fileWriter = new FileWriter("game.json")) {
            fileWriter.write(game.toJSONString());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
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
    public static void setNewGame(boolean newGame) {
        Stats.newGame = newGame;
    }
    public LinkedList<Upgrade> getUpgradesList() {
        return upgradesList;
    }
}
