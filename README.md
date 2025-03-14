# Car Rental Console Application

## Overview
A robust console-based car rental management system implemented in Java. This application follows object-oriented programming principles to provide a comprehensive solution for managing vehicle inventory, processing rentals, and handling user authentication.

## Features
- **User Authentication**: Secure login system for customers and administrators
- **Vehicle Management**: Add, update, and remove vehicles from the inventory
- **Rental Processing**: Create, modify, and cancel rental agreements
- **Customer Management**: Register new customers and maintain customer records
- **Admin Dashboard**: Special privileges for system administrators
- **Data Persistence**: Save and load system data between sessions

## Technologies & Concepts
- **Language**: Java
- **Design Pattern**: Object-Oriented Programming (OOP)
- **Data Structures**: Arrays, ArrayLists, HashMaps
- **File I/O**: Data persistence using file operations
- **Exception Handling**: Robust error management

## Project Structure
```
src/
├── main/
│   ├── User.java           # Base user class
│   ├── Customer.java       # Customer implementation
│   ├── Admin.java          # Admin implementation
│   ├── Vehicle.java        # Base vehicle class
│   ├── Car.java            # Car implementation
│   ├── Rental.java         # Rental agreement class
│   ├── RentalSystem.java   # Core system logic
│   └── Main.java           # Application entry point
```

## Installation & Usage
```bash
# Clone the repository
git clone https://github.com/MuradGhzda/CarRentalConsole.git

# Navigate to the project directory
cd CarRentalConsole

# Compile the Java files
javac src/main/*.java -d bin/

# Run the application
java -cp bin/ main.Main
```

## User Guide

### Admin Functions
1. Login with admin credentials
2. Add new vehicles to the inventory
3. View all registered customers
4. View all rental agreements
5. Generate reports

### Customer Functions
1. Register a new account or login
2. Browse available vehicles
3. Rent a vehicle
4. View personal rental history
5. Return a vehicle

## Class Diagram
The system implements the following class hierarchy:
- `User` (abstract)
  - `Customer`
  - `Admin`
- `Vehicle` (abstract)
  - `Car`
- `Rental`
- `RentalSystem`

## Sample Commands
```
# Login as admin
login admin password123

# Add a new vehicle
add-vehicle car Toyota Corolla 2023 50.00

# Register a new customer
register-customer John Doe john@example.com pass123

# Create a rental
create-rental CUST001 VEH003 2023-01-01 2023-01-07
```

## Future Enhancements
- GUI implementation
- Database integration
- Online payment processing
- Multiple vehicle types (motorcycles, trucks, etc.)
- Reservation system

## Contact
Murad Aghazada - muradagazade@icloud.com

Project Link: [https://github.com/MuradGhzda/CarRentalConsole](https://github.com/MuradGhzda/CarRentalConsole)
