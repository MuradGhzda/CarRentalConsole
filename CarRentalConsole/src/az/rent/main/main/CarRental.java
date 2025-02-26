package az.rent.main.main;

import az.rent.main.controller.CarRentalController;

public class CarRental {
    public static void main(String[] args) throws InterruptedException {
        CarRentalController controller = new CarRentalController();
        controller.start();
    }
}
