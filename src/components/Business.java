package components;

public class Business {
    private String name;
    private int id, owned;
    private double price, income;

    public Business(String name, int id, double price, double income) {
        this.name = name;
        this.id = id;
        this.owned = 1;
        this.price = price;
        this.income = income;
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

    public double getPrice() {
        return price;
    }

    public double getIncome() {
        return income;
    }
}
