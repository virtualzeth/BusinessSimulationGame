package client.datamodel;

public class Stats {
    private double money, income, multiplier;

    public Stats(double money, double income, double multiplier) {
        this.money = money;
        this.income = income;
        this.multiplier = multiplier;
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
}
