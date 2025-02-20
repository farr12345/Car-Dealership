import java.util.ArrayList;
import java.util.List;

/**
* the (@code Reporting) class manages multiple muanufacturers and provides
 * methods to analyse and report on car sales, pricing and revenue by type
 * <p>
 *   - Adding manufacturers.
 *   - Displaying all manufacturers and their car models.
 *   - Finding the most expensive car model sold.
 *   - Retrieving car models above a certain price.
 *   - Calculating and identifying the manufacturer with the highest revenue for a specific car type.
 * </p>
 */
public class Reporting {
    private List<Manufacturer> manufacturers; // List of all manufacturers

    // Constructor initializes an empty list of manufacturers
    public Reporting() {
        this.manufacturers = new ArrayList<>();
    }


    /**
     * Adds a manufacturer to the reporting system.
     *
     * @param manufacturer the manufacturer to be added
     */
    // Method to add a manufacturer to the reporting system
    public void addManufacturer(Manufacturer manufacturer) {
        manufacturers.add(manufacturer);
    }

    /**
     * Displays all manufacturers and their car models.
     * If no manufacturers exist, a message is displayed.
     */
    // Method to display all manufacturers and their car models
    public void displayAllManufacturers() {
        if (manufacturers.isEmpty()) {
            System.out.println("No manufacturers available.");
            return;
        }
        System.out.println("\n List of Manufacturers and Their Cars");
        for (Manufacturer manufacturer : manufacturers) {
            manufacturer.displayManufacturer();
        }
    }

    /**
     * Retrieves a manufacturer by its name.
     *
     * @param name the name of the manufacturer
     * @return the {@code Manufacturer} object if found, otherwise {@code null}
     */
    // Method to get a manufacturer by name
    public Manufacturer getManufacturerByName(String name) {
        for (Manufacturer manufacturer : manufacturers) {
            if (manufacturer.getMname().equalsIgnoreCase(name)) {
                return manufacturer;
            }
        }
        return null; // Return null if manufacturer not found
    }

    /**
     * Finds and displays the most expensive car model sold across all manufacturers.
     * If no cars exist, a message is displayed.
     */
    // Method to find and display the most expensive car model sold
    public void displayMostExpensiveCarModelSold() {
        if (manufacturers.isEmpty()) {
            System.out.println("No manufacturers available.");
            return;
        }

        CarModel mostExpensiveCar = null;
        Manufacturer mostExpensiveManufacturer = null;

        // Loop through manufacturers and their car models
        for (Manufacturer manufacturer : manufacturers) {
            for (CarModel model : manufacturer.getCarModels()) {
                if (mostExpensiveCar == null || model.getSales_price() > mostExpensiveCar.getSales_price()) {
                    mostExpensiveCar = model;
                    mostExpensiveManufacturer = manufacturer;
                }
            }
        }

        // Display the most expensive car model found
        if (mostExpensiveCar != null) {
            System.out.println("\n Most Expensive Car Model Sold");
            System.out.println("Name: " + mostExpensiveCar.getName());
            System.out.println("Manufacturer: " + mostExpensiveManufacturer.getMname());
            System.out.println("Sales Price: £" + mostExpensiveCar.getSales_price());
            System.out.println("Type: " + mostExpensiveCar.getType());
            System.out.println("Units Sold: " + mostExpensiveCar.getNSold());
        } else {
            System.out.println("No car models available.");
        }
    }

    /**
     * Retrieves a list of car models that have a sales price above a specified amount.
     *
     * @param price the minimum price threshold
     * @return a list of {@code CarModel} objects that are priced above the given amount
     */
    public List<CarModel> getCarModelsAbovePrice(double price) {
        List<CarModel> expensiveCars = new ArrayList<>();

        // Loop through manufacturers and their car models
        for (Manufacturer manufacturer : manufacturers) {
            for (CarModel model : manufacturer.getCarModels()) {
                if (model.getSales_price() > price) {
                    expensiveCars.add(model);
                }
            }
        }

        return expensiveCars;
    }

/**
 * Displays the revenue for each manufacturer based on the given car type
 * and identifies the manufacturer with the highest revenue for that type.
 * <p>
 * If no manufacturers exist or the car type is invalid, a message is displayed.
 * </p>
 *
 * @param carType the type of car (hatchback, saloon, or estate)
 */
    // Method to display revenue for each manufacturer and the one with the highest revenue for a specific car type
    public void displayManufacturerWithHighestRevenueByType(String carType) {
        if (manufacturers.isEmpty()) {
            System.out.println("No manufacturers available.");
            return;
        }

        // Validate car type
        if (!carType.equalsIgnoreCase("hatchback") &&
                !carType.equalsIgnoreCase("saloon") &&
                !carType.equalsIgnoreCase("estate")) {
            System.out.println("Invalid car type. Please enter Hatchback, Saloon, or Estate.");
            return;
        }

        Manufacturer highestRevenueManufacturer = null;
        double highestRevenue = 0.0;

        System.out.println("\n Revenue for " + carType + " Cars by Manufacturer");

        // Loop through manufacturers to calculate revenue for the given car type
        for (Manufacturer manufacturer : manufacturers) {
            double revenue = 0.0;
            for (CarModel model : manufacturer.getCarModels()) {
                if (model.getType().equalsIgnoreCase(carType)) {
                    revenue += model.getSales_price() * model.getNSold();
                }
            }

            // Display revenue for this manufacturer
            System.out.println(manufacturer.getMname() + "Revenue: £" + revenue);

            // Check if this manufacturer has the highest revenue for this car type
            if (revenue > highestRevenue) {
                highestRevenue = revenue;
                highestRevenueManufacturer = manufacturer;
            }
        }

        // Display manufacturer with the highest revenue for this car type
        if (highestRevenueManufacturer != null) {
            System.out.println("\n Manufacturer with Highest Revenue for " + carType + ": " + highestRevenueManufacturer.getMname());
            System.out.println("Total Revenue: £" + highestRevenue);
        }
    }

    public static void main(String[] args) {
        try {
            Reporting report = new Reporting();

            Manufacturer tesla = new Manufacturer("Tesla");
            Manufacturer ford = new Manufacturer("Ford");

            tesla.addCarModel(new CarModel("Model X", 1800, 50000, 10000, "Hatchback"));
            tesla.addCarModel(new CarModel("Model S", 1600, 80000, 6000, "Saloon"));
            ford.addCarModel(new CarModel("Mustang", 2000, 55000, 5000, "Saloon"));
            ford.addCarModel(new CarModel("Focus", 1400, 25000, 20000, "Hatchback"));

            report.addManufacturer(tesla);
            report.addManufacturer(ford);

            report.displayAllManufacturers();
            report.getManufacturerByName(tesla.getMname());

            report.getCarModelsAbovePrice(1000);
            report.getCarModelsAbovePrice(1800);
            report.getCarModelsAbovePrice(-100);

            report.displayMostExpensiveCarModelSold();
            report.displayManufacturerWithHighestRevenueByType("Saloon");
        } catch (IllegalArgumentException e) {
            // Catch and display any validation errors
            System.out.println("Error: " + e.getMessage());
        }
    }
}


