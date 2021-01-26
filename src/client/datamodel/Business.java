package client.datamodel;

public class Business {
    private String name;
    private int id, owned;
    private double cost, incomeIncrementValue, initialCost;

    public Business(String name, int id, int owned, double cost, double initialCost, double incomeIncrementValue) {
        this.name = name;
        this.id = id;
        this.owned = owned;
        this.cost = cost;
        this.initialCost = initialCost;
        this.incomeIncrementValue = incomeIncrementValue;
    }

    public int getId() {
        return id;
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

    public double getInitialCost() {
        return initialCost;
    }

    public double getIncomeIncrementValue() {
        return incomeIncrementValue;
    }
}
