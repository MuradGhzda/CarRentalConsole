package az.rent.main.service;

import az.rent.main.config.Context;
import az.rent.main.dao.inter.CarDaoInter;
import az.rent.main.dao.inter.CarRentalServiceInterface;
import az.rent.main.entity.Car;
import az.rent.main.entity.Rent;
import az.rent.main.entity.User;

import java.io.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class CarRentalService implements CarRentalServiceInterface {
    private User[] users = new User[500];
    private int userIndex = 0;
    private static final String USERS_FILE_PATH = "users.txt";
    private CarDaoInter carDao;
    private Scanner scanner;
    private User activeUser;

    // ANSI escape codes for colors
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_CYAN = "\u001B[36m";

    public CarRentalService() {
        this.carDao = Context.getInstance().getCarDao();
        this.scanner = new Scanner(System.in);
        carDao.start();
        loadUsersFromFile();
        Rent.loadRentsFromFile();
    }

    @Override
    public User getActiveUser() {
        return activeUser;
    }

    @Override
    public void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }

    @Override
    public void loadUsersFromFile() {
        users = new User[500]; // Reset the users array
        userIndex = 0; // Reset the userIndex
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(USERS_FILE_PATH))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] splitted = line.split(",");
                User user = new User(splitted[0], splitted[1], Boolean.parseBoolean(splitted[2]));
                users[userIndex++] = user;
            }
        } catch (IOException e) {
            System.out.println("Error in user file: " + e.getMessage());
        }
    }

    @Override
    public User loginUser() {
        System.out.println(ANSI_CYAN + "Enter your UserName:" + ANSI_RESET);
        String userName = scanner.nextLine();
        System.out.println(ANSI_CYAN + "Enter your Password:" + ANSI_RESET);
        String password = scanner.nextLine();

        for (User user : users) {
            if (user != null && user.getName().equals(userName) && user.getPassword().equals(password)) {
                activeUser = user;
                return user;
            }
        }
        return null;
    }

    @Override
    public void signUpUser() {
        System.out.println(ANSI_CYAN + "Enter new UserName:" + ANSI_RESET);
        String newUserName = scanner.nextLine();
        System.out.println(ANSI_CYAN + "Enter new Password:" + ANSI_RESET);
        String newPassword = scanner.nextLine();

        System.out.println(ANSI_CYAN + "Is the user an admin? (true/false):" + ANSI_RESET);
        boolean isAdmin = Boolean.parseBoolean(scanner.nextLine());

        users[userIndex++] = new User(newUserName, newPassword, isAdmin);
        saveUsersToFile();
    }

    @Override
    public void saveUsersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE_PATH))) {
            for (int i = 0; i < userIndex; i++) {
                if (users[i] != null) {
                    writer.write(users[i].getName() + "," + users[i].getPassword() + "," + users[i].isAdmin());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    @Override
    public void displayAllUsers() {
        System.out.println(ANSI_YELLOW + "=== All Users ===" + ANSI_RESET);
        System.out.println(String.format("%-10s %-20s %-10s", "User ID", "Name", "Admin"));
        System.out.println("-----------------------------------------------------");
        for (int i = 0; i < userIndex; i++) {
            if (users[i] != null) {
                System.out.println(String.format("%-10d %-20s %-10s", users[i].getId(), users[i].getName(), users[i].isAdmin()));
            }
        }
    }


    @Override
    public void changeUserRole() {
        System.out.println(ANSI_CYAN + "Enter the username of the user to change role:" + ANSI_RESET);
        String userName = scanner.nextLine();
        User user = null;
        for (int i = 0; i < userIndex; i++) {
            if (users[i] != null && users[i].getName().equals(userName)) {
                user = users[i];
                break;
            }
        }
        if (user != null) {
            System.out.println(ANSI_YELLOW + "Current role of " + userName + " is " + (user.isAdmin() ? "Admin" : "User") + ANSI_RESET);
            System.out.println(ANSI_CYAN + "Enter new role (true for Admin, false for User):" + ANSI_RESET);
            boolean newRole = Boolean.parseBoolean(scanner.nextLine());
            user.setAdmin(newRole);
            saveUsersToFile();
            System.out.println(ANSI_GREEN + "Role updated successfully." + ANSI_RESET);
        } else {
            System.out.println(ANSI_RED + "User not found." + ANSI_RESET);
        }
    }

    @Override
    public void displayAllCars() {
        System.out.println(ANSI_YELLOW + "=== All Cars ===" + ANSI_RESET);
        System.out.println(String.format("%-10s %-15s %-15s %-10s", "Car ID", "Brand", "Model", "Price"));
        System.out.println("-----------------------------------------------------------");
        for (Car car : carDao.getAllCar()) {
            System.out.println(String.format("%-10d %-15s %-15s %-10d", car.getVehicleId(), car.getBrand(), car.getModel(), car.getPrice()));
        }
    }

    @Override
    public void addCar(Car car) {
        carDao.insert(car);
    }

    @Override
    public void deleteCar(int carId) {
        Car car = carDao.getCarById(carId);
        if (car != null) {
            carDao.delete(carId);
            System.out.println(ANSI_GREEN + "Car deleted successfully." + ANSI_RESET);
        } else {
            System.out.println(ANSI_RED + "Invalid car ID. Please try again." + ANSI_RESET);
        }
    }

    @Override
    public List<Car> getAllCars() {
        return carDao.getAllCar();
    }

    @Override
    public Car getCarInfoFromUser() {
        System.out.print(ANSI_CYAN + "Enter car brand: " + ANSI_RESET);
        String brand = scanner.nextLine();
        System.out.print(ANSI_CYAN + "Enter car model: " + ANSI_RESET);
        String model = scanner.nextLine();
        System.out.print(ANSI_CYAN + "Enter car price: " + ANSI_RESET);
        int price = getIntInput();

        return new Car(brand, model, price);
    }

    @Override
    public int getValidCarIdFromUser() {
        int carId;
        Car car;
        do {
            displayAllCars();
            System.out.print(ANSI_CYAN + "Enter car ID: " + ANSI_RESET);
            carId = getIntInput();
            car = carDao.getCarById(carId);
            if (car == null) {
                System.out.println(ANSI_RED + "Invalid car ID. Please try again." + ANSI_RESET);
            }
        } while (car == null);
        return carId;
    }

    @Override
    public void displayAllRents() {
        System.out.println(ANSI_YELLOW + "=== All Rents ===" + ANSI_RESET);
        Rent.displayAllRents();
    }

    @Override
    public boolean addRent(Rent rent) {
        return Rent.checkAndAddRent(rent);
    }

    @Override
    public void deleteRent(int carId) {
        Rent.deleteRent(carId);
    }

    @Override
    public Rent getRentInfoFromUser() {
        displayAllCars();
        System.out.print(ANSI_CYAN + "Enter car ID: " + ANSI_RESET);
        int carId = getIntInput();
        System.out.print(ANSI_CYAN + "Enter start date (YYYY-MM-DD): " + ANSI_RESET);
        LocalDate startDate = LocalDate.parse(scanner.nextLine());
        System.out.print(ANSI_CYAN + "Enter end date (YYYY-MM-DD): " + ANSI_RESET);
        LocalDate endDate = LocalDate.parse(scanner.nextLine());

        return new Rent(startDate, endDate, carId, activeUser.getId());
    }

    @Override
    public int getIntInput() {
        while (!scanner.hasNextInt()) {
            scanner.next(); // consume the invalid input
        }
        int input = scanner.nextInt();
        scanner.nextLine(); // consume the newline character
        return input;
    }
}
