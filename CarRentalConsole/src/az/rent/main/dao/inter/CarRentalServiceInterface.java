package az.rent.main.dao.inter;

import az.rent.main.entity.Car;
import az.rent.main.entity.Rent;
import az.rent.main.entity.User;

import java.util.List;

public interface CarRentalServiceInterface {
    void loadUsersFromFile();

    User loginUser();
    void signUpUser();

    void saveUsersToFile();

    void displayAllUsers();
    void changeUserRole();
    User getActiveUser();
    void setActiveUser(User activeUser);

    void displayAllCars();
    void addCar(Car car);
    void deleteCar(int carId);
    List<Car> getAllCars();
    Car getCarInfoFromUser();
    int getValidCarIdFromUser();

    void displayAllRents();
    boolean addRent(Rent rent);
    void deleteRent(int carId);
    Rent getRentInfoFromUser();
    int getIntInput();
}
