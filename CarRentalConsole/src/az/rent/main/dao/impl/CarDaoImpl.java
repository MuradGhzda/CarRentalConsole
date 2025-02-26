package az.rent.main.dao.impl;

import az.rent.main.dao.inter.CarDaoInter;
import az.rent.main.entity.Car;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CarDaoImpl implements CarDaoInter {
    private static final String CAR_FILE_PATH = "car.txt";
    private List<Car> cars = new ArrayList<>();
    private static int highestId = 0; // To keep track of the highest carId

    @Override
    public void start() {
        cars.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(CAR_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 4) {
                    System.out.println("Invalid line: " + line);
                    continue;
                }

                int carId = Integer.parseInt(parts[0]);
                String brand = parts[1];
                String model = parts[2];
                int price = Integer.parseInt(parts[3]);

                if (carId > highestId) {
                    highestId = carId;
                }

                Car car = new Car(carId, brand, model, price);
                cars.add(car);
            }
            Car.setCount(highestId); // Set the count to the highestId after loading all cars
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAR_FILE_PATH))) {
            for (Car car : cars) {
                writer.write(car.getVehicleId() + "," + car.getBrand() + "," + car.getModel() + "," + car.getPrice());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(Car car) {
        car.setVehicleId(++highestId); // Increment the highestId and set it to the new car
        cars.add(car);
        update();
    }

    @Override
    public void delete(int id) {
        cars.removeIf(car -> car.getVehicleId() == id);
        update();
    }

    @Override
    public Car getCarById(int id) {
        return cars.stream().filter(car -> car.getVehicleId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Car> getAllCar() {
        return new ArrayList<>(cars);
    }

    @Override
    public Car[] getCars() {
        return cars.toArray(new Car[0]);
    }

    @Override
    public void display() {
        cars.forEach(car -> System.out.println("Car ID: " + car.getVehicleId() + ", Brand: " + car.getBrand() + ", Model: " + car.getModel() + ", Price: " + car.getPrice()));
    }
}
