package client.datamodel;

public class Upgrade {
    private String name, description;
    private double cost;
    private boolean owned;
    private int id;

    public Upgrade(String name, int id, double cost, boolean owned, String description) {
        this.name = name;
        this.id = id;
        this.cost = cost;
        this.owned = owned;
        this.description = description;
    }

    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }
    public double getCost() {
        return cost;
    }
    public boolean isOwned() {
        return owned;
    }
    public void setOwned(boolean owned) {
        this.owned = owned;
    }
    public String getDescription() {
        return description;
    }
}
