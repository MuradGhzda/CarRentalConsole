package az.rent.main.dao.inter;

import java.time.LocalDate;

public interface RentInterface {
    LocalDate getStartDate();
    void setStartDate(LocalDate startDate);
    LocalDate getEndDate();
    void setEndDate(LocalDate endDate);
    int getCarId();
    void setCarId(int carId);
    int getUserId();
    void setUserId(int userId);
}
