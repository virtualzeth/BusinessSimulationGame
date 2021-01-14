package client.datamodel;

public class Upgrade {
    private String name, description;
    private double cost;
    private boolean owned;

    public Upgrade(String name, double cost, boolean owned, String description) {
        this.name = name;
        this.cost = cost;
        this.owned = owned;
        this.description = description;
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

    public String getDescription() {
        return description;
    }
}
