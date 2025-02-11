import java.util.ArrayList;
import java.util.List;

// CarModel class represents an individual car model its attributes describing the car
public class CarModel {
   private String name;
   private double weight;
   private double sales_price;
   private int NSold;
   private String type;

    // Constructor to initialize a CarModel object
   public CarModel(String name, double weight, double sales_price, int NSold, String type) {
       this.name = name;
       this.weight = weight;
       this.sales_price = sales_price;
       this.NSold = NSold;
       this.type = type;
   }

    // Getter methods to retrieve the values of private attributes
   public String getName() {
       return name;
   }

   public double getWeight() {
       return weight;
   }

   public double getSales_price() {
       return sales_price;
   }

   public int getNSold() {
       return NSold;
   }

   public String getType() {
       return type;
   }

}

// Manufacturer class represents a car manufacturer that inherits attributes from the Carmodel class CarModel
public class Manfacturer extends CarModel {
    //attributes for the name of the manufacturer and the list of their car models
    private String Mname;
    private List<List<String>> carModelsList = new ArrayList<>();

    // Constructor for Manufacturer class
    public Manfacturer(String Mname, String name, double weight, double sales_price, int NSold, String type) {
        super(name, weight, sales_price, NSold, type);
        this.Mname = Mname;
    }

    //getter methods for attributes
    public String getMname() {
        return Mname;
    }

    public List<List<String>> getCarModelsList() {
        return carModelsList;
    }

    public void setCarModels(CarModel[] carModels) {
        carModelsList.clear(); // Clear existing data before adding new models

        for (CarModel model : carModels) {
            List<String> attributes = new ArrayList<>();// List to store attributes of a single car model
            // Add each attribute of the car model to the list
            attributes.add("Name: " + model.getName());
            attributes.add("Weight: " + model.getWeight());
            attributes.add("Sales Price: " + model.getSales_price());
            attributes.add("Number Sold: " + model.getNSold());
            attributes.add("Type: " + model.getType());

            carModelsList.add(attributes); // Add each car's attribute list to the main list
        }
    }



    // Method to display manufacturer details and the list of car models
    public void displayManufacturer() {
        System.out.println("Manufacturer: " + getMname());
        System.out.println("Car Models: ");
        // Loop through the list of car models and print their attributes
        for (int i = 0; i < carModelsList.size(); i++) {
            System.out.println("Car Model " + (i + 1) + ": " + carModelsList.get(i));
        }
    }
}