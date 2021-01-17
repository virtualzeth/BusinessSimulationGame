package client.datamodel;

public class Business {
    private String name;
    private int owned;
    private double cost, incomeIncrementValue, initialCost;

    public Business(String name, int owned, double cost, double incomeIncrementValue) {
        this.name = name;
        this.owned = owned;
        this.cost = cost;
        this.initialCost = cost;
        this.incomeIncrementValue = incomeIncrementValue;
    }

    public void buy() {
        this.owned++;
        this.cost += this.initialCost * 0.15;
    }

    public int getOwned() {
        return owned;
    }

    public double getCost() {
        return cost;
    }

    public double getIncomeIncrementValue() {
        return incomeIncrementValue;
    }
}
