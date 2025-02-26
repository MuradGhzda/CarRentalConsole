package az.rent.main.entity;

public class Car extends Vehicle {
    private static int count = 0; // Counter to keep track of the highest car ID

    public Car(int carId, String brand, String model, int price) {
        super(carId, brand, model, price);
    }

    public Car(String brand, String model, int price) {
        super(Vehicle.getNextId(), brand, model, price);
    }

    @Override
    public void displayDetails() {
        System.out.println("Car ID: " + vehicleId + ", Brand: " + brand + ", Model: " + model + ", Price: " + price);
    }

    public static void setCount(int highestId) {
        count = highestId;
    }

    public static int getCount() {
        return count;
    }
}
