import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The ReportingIO class provides a console-based system for managing car manufacturers
 * and their car models. It allows users to enter manufacturer data, add car models,
 * list manufacturers, generate reports, and display information about cars based on various
 */
public class ReportingIO {
    private List<Manufacturer> manufacturers;
    private Scanner scanner;

    public ReportingIO() {
        this.manufacturers = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        ReportingIO reportingIO = new ReportingIO();
        reportingIO.run();
    }

    public void run() {
        while (true) {
            displayMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    enterManufacturerData();
                    break;
                case 2:
                    enterCarModelData();
                    break;
                case 3:
                    listAllManufacturer();
                    break;
                case 4:
                    listCarModelsByManufacturer();
                    break;
                case 5:
                    generateReports();
                    break;
                case 6:
                    System.out.println("Exiting program");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number betwween 1 and 6");
            }
        }
    }
    private void displayMenu() {
        System.out.println("Welcome to the Reporting System");
        System.out.println("1. Enter Manufacturer Data");
        System.out.println("2. Enter Car Model Data");
        System.out.println("3. List All Car Models by Manufacturers");
        System.out.println("4. List Car Models by Manufacturer");
        System.out.println("5. Generate Reports");
        System.out.println("6. Exit Program");
    }
    private int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid user choice. Please enter a number between 1");
            return -1;
        }
    }

    private void enterManufacturerData() {
        System.out.println("\n Enter manufacturer name: ");
        String name = scanner.nextLine();
        manufacturers.add(new Manufacturer(name));
        System.out.println("Manufacturer '" + name + "' added successfully");

    }

    private void enterCarModelData() {
        if (manufacturers.isEmpty()) {
            System.out.println("No manufacturers available. Please adda manufacturer first.");
            return;
        }
        System.out.println("\n Available Manufacturers:");
        for (int i = 0; i < manufacturers.size(); i++) {
            System.out.println((i + 1) + ". " + manufacturers.get(i).getMname());
        }

        int choice = getUserChoice();
        if (choice < 1 || choice > manufacturers.size()) {
            System.out.println("Invalid manufacturer selection.");
            return;
        }
        Manufacturer selectManufacturer = manufacturers.get(choice - 1);

        try{
            System.out.println("Enter Car Model: ");
            String name = scanner.nextLine();

            System.out.println("Enter Car Weight (kg): ");
            double weight = Double.parseDouble(scanner.nextLine());

            System.out.println("Enter Sales Price (£): ");
            double price = Double.parseDouble(scanner.nextLine());

            System.out.println("Enter Number Sold: ");
            int nSold = Integer.parseInt(scanner.nextLine());

            System.out.println("Enter Car Type (Hatchback, Saloon, Estate): ");
            String type = scanner.nextLine();

            CarModel carModel = new CarModel(name, weight, price, nSold, type);
            selectManufacturer.addCarModel(carModel);
            System.out.println("Car model '" + name + "' added successfully");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter numerical values where required.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    private void listAllManufacturer() {
        if (manufacturers.isEmpty()) {
            System.out.println("No manufacturers available.");
            return;
        }

        System.out.println("\nList of Manufacturers:");
        for (Manufacturer manufacturer : manufacturers) {
            System.out.println(manufacturer.getMname());
        }
    }

    private void listCarModelsByManufacturer() {
        if (manufacturers.isEmpty()) {
            System.out.println("No manufacturers available. add a manufacturer first");
            return;
        }
        System.out.println("Enter manufacturer name:");
        String name = scanner.nextLine();
        Manufacturer manufacturer = null;

        for (Manufacturer manufacturer1 : manufacturers) {
            if (manufacturer1.getMname().equalsIgnoreCase(name)) {
                manufacturer = manufacturer1;
                break;
            }
        }

        if (manufacturer == null) {
            System.out.println("Manufacturer not found");
        } else{
            manufacturer.displayManufacturer();
        }

    }

    private void generateReports() {
        if (manufacturers.isEmpty()) {
            System.out.println("No data available.");
            return;
        }

        System.out.println("\nReporting Options");
        System.out.println("1. Manufacturer with largest revenue for a given car type");
        System.out.println("2. Most expensive car model sold");
        System.out.println("3. Car Models above a given price");
        System.out.println("Enter choice: ");

        int reportChoice = getUserChoice();
        switch (reportChoice) {
            case 1:
                System.out.println("Enter Car Type (Hatchback, Saloon, Estate): ");
                String type = scanner.nextLine();
                Manufacturer topManufacturer = null;
                double highestRevenue = 0.0;

                for (Manufacturer m : manufacturers) {
                    double revenue = m.calculateTotalRevenueByType(type);
                    if (highestRevenue < revenue) {
                        highestRevenue = revenue;
                        topManufacturer = m;
                    }
                }

                if (topManufacturer != null) {
                    System.out.println("\nManufacturer with the highest revenue for " + type + ": " + topManufacturer.getMname());
                } else {
                    System.out.println("Manufacturer not found");
                }
                break;

            case 2:
                System.out.println("\n Most Expensive car model sold");
                for (Manufacturer manufacturer : manufacturers) {
                    manufacturer.displayMostExpensiveCarModel();

                }
                break;

            case 3:
                System.out.println("Enter minimum price (£): ");
                double price = Double.parseDouble(scanner.nextLine());
                for (Manufacturer manufacturer : manufacturers) {
                    manufacturer.displayCarModelsAbovePrice(price);
                }
                break;

                default:
                    System.out.println("Invalid choice.");
        }


    }
}
