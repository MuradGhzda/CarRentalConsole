package az.rent.main.controller;

import az.rent.main.entity.Car;
import az.rent.main.entity.Rent;
import az.rent.main.entity.User;
import az.rent.main.dao.inter.CarRentalServiceInterface;
import az.rent.main.service.CarRentalService;

public class CarRentalController {
    protected static CarRentalServiceInterface carRentalService;

    public CarRentalController() {
        carRentalService = new CarRentalService();
    }

    protected static final String ANSI_RESET = "\u001B[0m";
    protected static final String ANSI_RED = "\u001B[31m";
    protected static final String ANSI_GREEN = "\u001B[32m";
    protected static final String ANSI_YELLOW = "\u001B[33m";
    protected static final String ANSI_BLUE = "\u001B[34m";
    protected static final String ANSI_PURPLE = "\u001B[35m";
    protected static final String ANSI_CYAN = "\u001B[36m";

    protected void printHeader() {
        System.out.println(ANSI_PURPLE + "===============================" + ANSI_RESET);
        System.out.println(ANSI_PURPLE + "        CarRental System       " + ANSI_RESET);
        System.out.println(ANSI_PURPLE + "===============================" + ANSI_RESET);
    }

    public void start() throws InterruptedException {
        User activeUser = null;
        boolean loggedIn = false;

        printHeader();

        while (!loggedIn) {
            System.out.println(ANSI_BLUE + "Choose an option:" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "0. Log In" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "1. Sign Up" + ANSI_RESET);

            int operation = carRentalService.getIntInput();
            if (operation == 0) {
                activeUser = carRentalService.loginUser();
                if (activeUser != null) {
                    loggedIn = true;
                    carRentalService.setActiveUser(activeUser);
                    System.out.println(ANSI_GREEN + "Successfully logged in" + ANSI_RESET);
                    Thread.sleep(2000);
                } else {
                    System.out.println(ANSI_RED + "Invalid Access" + ANSI_RESET);
                    Thread.sleep(2000);
                }
            } else if (operation == 1) {
                carRentalService.signUpUser();
                System.out.println(ANSI_GREEN + "User successfully signed up. Now you can log in." + ANSI_RESET);
            } else {
                System.out.println(ANSI_RED + "Invalid operation" + ANSI_RESET);
            }
        }

        if (carRentalService.getActiveUser() != null && carRentalService.getActiveUser().isAdmin()) {
            AdminController.adminMenu();
        } else {
            userMenu();
        }
    }



    private void userMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println(ANSI_BLUE + "User Menu:" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "1. View available cars" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "2. Rent Management" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "3. Exit" + ANSI_RESET);

            int choice = carRentalService.getIntInput();
            switch (choice) {
                case 1:
                    carRentalService.displayAllCars();
                    break;
                case 2:
                    rentManagementMenu();
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println(ANSI_RED + "Invalid choice. Please try again." + ANSI_RESET);
            }
        }
    }



    protected static void rentManagementMenu() {
        boolean back = false;
        while (!back) {
            System.out.println(ANSI_YELLOW + "Rent Management:" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "1. Display all rents" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "2. Add a new rent" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "3. Delete a rent by car ID" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "4. Back to previous menu" + ANSI_RESET);

            int choice = carRentalService.getIntInput();
            switch (choice) {
                case 1:
                    carRentalService.displayAllRents();
                    break;
                case 2:
                    Rent newRent = carRentalService.getRentInfoFromUser();
                    if (carRentalService.addRent(newRent)) {
                        System.out.println(ANSI_GREEN + "Rent added successfully." + ANSI_RESET);
                    }
                    break;
                case 3:
                    System.out.println(ANSI_CYAN + "Enter the car ID to delete rent:" + ANSI_RESET);
                    int carIdToDelete = carRentalService.getIntInput();
                    carRentalService.deleteRent(carIdToDelete);
                    System.out.println(ANSI_GREEN + "Rent deleted successfully." + ANSI_RESET);
                    break;
                case 4:
                    back = true;
                    break;
                default:
                    System.out.println(ANSI_RED + "Invalid choice. Please try again." + ANSI_RESET);
            }
        }
    }


}
