package az.rent.main.dao.inter;

import az.rent.main.entity.Car;

import java.util.List;

public interface CarDaoInter {
    void insert(Car car);
    void start();
    void update();
    void delete(int id);
    void display();
    Car getCarById(int id);
    List<Car> getAllCar();
    Car[] getCars();
}
