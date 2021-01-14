package client.datamodel;

public class Business {
    private String name;
    private int id, owned;
    private double cost, income;
    private double incomeIncrementValue;

    public Business(String name, int id, int owned, double cost, double income) {
        this.name = name;
        this.id = id;
        this.owned = owned;
        this.cost = cost;
        this.income = income;
        this.incomeIncrementValue = income;
    }

    public void buy() {
        this.income += this.incomeIncrementValue;
        this.owned++;
        this.cost = this.cost * 1.05;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getOwned() {
        return owned;
    }

    public double getCost() {
        return cost;
    }

    public double getIncome() {
        return income;
    }

    public double getIncomeIncrementValue() {
        return incomeIncrementValue;
    }
}
