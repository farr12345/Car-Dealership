
/**
 * The {@code CarModel} class represents a specific car model with attributes such as name, weight, sales price,
 * number of units sold, and type (hatchback, saloon, or estate).
 * <p>
 * This class provides functionality to:
 * - Store details about a car model.
 * - Validate the car type during object creation.
 * - Retrieve various attributes using getter methods.
 * </p>
 */
// CarModel class represents an individual car model its attributes describing the car
public class CarModel {
   private String name;
   private double weight;
   private double sales_price;
   private int NSold;
   private String type;

    /**
     * Constructs a {@code CarModel} object with the specified attributes.
     * <p>
     * The car type must be one of: "hatchback", "saloon", or "estate". If an invalid type is provided,
     * an {@code IllegalArgumentException} is thrown.
     * </p>
     *
     * @param name        the name of the car model
     * @param weight      the weight of the car model in kilograms
     * @param sales_price the sales price of the car model in GBP
     * @param NSold       the number of units sold
     * @param type        the type of car (hatchback, saloon, estate)
     * @throws IllegalArgumentException if the car type is not hatchback, saloon, or estate
     */
    // Constructor to initialize a CarModel object
   public CarModel(String name, double weight, double sales_price, int NSold, String type) {
       // Validate car type
       if (!type.equalsIgnoreCase("hatchback") && !type.equalsIgnoreCase("saloon") && !type.equalsIgnoreCase("estate")) {
           throw new IllegalArgumentException("Invalid car type. Must be hatchback, saloon, or estate.");
       }

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

    public static void main(String[] args) {
        CarModel carModel = new CarModel("Model X", 1800, 50000, 10000, "Hatchback");
        System.out.println(carModel.getName());
        System.out.println(carModel.getWeight());
        System.out.println(carModel.getSales_price());
        System.out.println(carModel.getNSold());
        System.out.println(carModel.getType());

    }

}

