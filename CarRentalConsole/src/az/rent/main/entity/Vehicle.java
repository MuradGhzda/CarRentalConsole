package az.rent.main.entity;

public abstract class Vehicle {
    protected static int highestId = 0; // Counter to keep track of the highest vehicle ID
    protected int vehicleId;
    protected String brand;
    protected String model;
    protected int price;

    public Vehicle(int vehicleId, String brand, String model, int price) {
        this.vehicleId = vehicleId;
        this.brand = brand;
        this.model = model;
        this.price = price;
    }

    public abstract void displayDetails();

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public static void setHighestId(int id) {
        highestId = id;
    }

    public static int getNextId() {
        return ++highestId;
    }
}
