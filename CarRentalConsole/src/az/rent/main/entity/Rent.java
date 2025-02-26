package az.rent.main.entity;

import az.rent.main.dao.inter.RentInterface;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Rent implements RentInterface, Serializable {
    private static final long serialVersionUID = 1L;
    private static final String RENTS_FILE_PATH = "rents.txt";
    private static final String OLD_RENTS_FILE_PATH = "oldrents.txt";
    private static List<Rent> rents = new ArrayList<>();
    private static List<Rent> oldRents = new ArrayList<>();
    private static int rentIndex = 0;

    private LocalDate startDate;
    private LocalDate endDate;
    private int carId;
    private int userId;

    public Rent(LocalDate startDate, LocalDate endDate, int carId, int userId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.carId = carId;
        this.userId = userId;
    }

    @Override
    public LocalDate getStartDate() {
        return startDate;
    }

    @Override
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Override
    public LocalDate getEndDate() {
        return endDate;
    }

    @Override
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public int getCarId() {
        return carId;
    }

    @Override
    public void setCarId(int carId) {
        this.carId = carId;
    }

    @Override
    public int getUserId() {
        return userId;
    }

    @Override
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public static void loadRentsFromFile() {
        rents.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(RENTS_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 4) {
                    System.out.println("Invalid line in file: " + line);
                    continue;
                }

                LocalDate startDate = LocalDate.parse(parts[0]);
                LocalDate endDate = LocalDate.parse(parts[1]);
                int carId = Integer.parseInt(parts[2]);
                int userId = Integer.parseInt(parts[3]);

                Rent rent = new Rent(startDate, endDate, carId, userId);
                rents.add(rent);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading from the file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred while parsing data: " + e.getMessage());
        }
    }

    public static void displayAllRents() {
        System.out.println(String.format("%-10s %-15s %-15s %-10s %-10s", "Rent ID", "Start Date", "End Date", "Car ID", "User ID"));
        System.out.println("---------------------------------------------------------------");
        int rentId = 1;
        for (Rent rent : rents) {
            System.out.println(String.format("%-10d %-15s %-15s %-10d %-10d", rentId++, rent.getStartDate(), rent.getEndDate(), rent.getCarId(), rent.getUserId()));
        }
    }

    public static boolean checkAndAddRent(Rent newRent) {
        for (Rent rent : rents) {
            if (rent.getCarId() == newRent.getCarId() && isOverlap(rent, newRent)) {
                System.out.println("Renting period overlaps with an existing rent. Rent canceled.");
                return false;
            }
        }
        rents.add(newRent);
        saveRentsToFile();
        return true;
    }

    private static boolean isOverlap(Rent rent1, Rent rent2) {
        return (rent1.getStartDate().isBefore(rent2.getEndDate()) || rent1.getStartDate().equals(rent2.getEndDate())) &&
                (rent2.getStartDate().isBefore(rent1.getEndDate()) || rent2.getStartDate().equals(rent1.getEndDate()));
    }

    public static void deleteRent(int carId) {
        rents.removeIf(rent -> rent.getCarId() == carId);
        saveRentsToFile();
    }

    private static void saveRentsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RENTS_FILE_PATH))) {
            for (Rent rent : rents) {
                writer.write(rent.getStartDate() + "," + rent.getEndDate() + "," + rent.getCarId() + "," + rent.getUserId());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return String.format("Rent{startDate=%s, endDate=%s, carId=%d, userId=%d}", startDate, endDate, carId, userId);
    }
}
