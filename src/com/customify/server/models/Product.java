package customify.server.models;

public class Product {
    private int id;
    private String name;
    private float price;
    private double points;

    public Product(){}
    public Product(String name, int id, float price, double points) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.points = points;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }
}
