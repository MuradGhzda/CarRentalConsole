package az.rent.main.config;

import az.rent.main.dao.impl.CarDaoImpl;
import az.rent.main.dao.inter.CarDaoInter;

public class Context {
    private static Context instance;
    private CarDaoInter carDao;

    private Context() {
        carDao = new CarDaoImpl();
    }

    public static Context getInstance() {
        if (instance == null) {
            instance = new Context();
        }
        return instance;
    }

    public CarDaoInter getCarDao() {
        return carDao;
    }
}
