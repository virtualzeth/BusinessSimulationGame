package client.datamodel;

public class Upgrade {
    private String name;
    private double cost;
    private boolean owned;

    public Upgrade(String name, double cost, boolean owned) {
        this.name = name;
        this.cost = cost;
        this.owned = owned;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public boolean isOwned() {
        return owned;
    }
}
