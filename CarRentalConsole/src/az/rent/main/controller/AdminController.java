package az.rent.main.controller;

import az.rent.main.entity.Car;
import az.rent.main.entity.Rent;
import az.rent.main.entity.User;
import az.rent.main.dao.inter.CarRentalServiceInterface;
import az.rent.main.service.CarRentalService;

public class AdminController extends CarRentalController {

    public AdminController() {
        super();
    }




    protected static void adminMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println(ANSI_BLUE + "Admin Menu:" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "1. Car Management" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "2. Rent Management" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "3. User Management" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "4. Exit" + ANSI_RESET);

            int categoryChoice = carRentalService.getIntInput();
            switch (categoryChoice) {
                case 1:
                    carManagementMenu();
                    break;
                case 2:
                    rentManagementMenu();
                    break;
                case 3:
                    userManagementMenu();
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println(ANSI_RED + "Invalid choice. Please try again." + ANSI_RESET);
            }
        }
    }


    protected static void carManagementMenu() {
        boolean back = false;
        while (!back) {
            System.out.println(ANSI_YELLOW + "Car Management:" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "1. Display all cars" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "2. Add a new car" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "3. Delete a car by ID" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "4. Back to admin menu" + ANSI_RESET);

            int choice = carRentalService.getIntInput();
            switch (choice) {
                case 1:
                    carRentalService.displayAllCars();
                    break;
                case 2:
                    Car newCar = carRentalService.getCarInfoFromUser();
                    carRentalService.addCar(newCar);
                    System.out.println(ANSI_GREEN + "Car added successfully." + ANSI_RESET);
                    break;
                case 3:
                    int deleteId = carRentalService.getValidCarIdFromUser();
                    carRentalService.deleteCar(deleteId);
                    break;
                case 4:
                    back = true;
                    break;
                default:
                    System.out.println(ANSI_RED + "Invalid choice. Please try again." + ANSI_RESET);
            }
        }
    }



    private static void userManagementMenu() {
        boolean back = false;
        while (!back) {
            System.out.println(ANSI_YELLOW + "User Management:" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "1. Display all users" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "2. Change user role" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "3. Back to admin menu" + ANSI_RESET);

            int choice = carRentalService.getIntInput();
            switch (choice) {
                case 1:
                    carRentalService.displayAllUsers();
                    break;
                case 2:
                    carRentalService.changeUserRole();
                    break;
                case 3:
                    back = true;
                    break;
                default:
                    System.out.println(ANSI_RED + "Invalid choice. Please try again." + ANSI_RESET);
            }
        }
    }
}
