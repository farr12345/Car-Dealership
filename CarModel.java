public class CarModel {
   private String name;
   private double weight;
   private double sales_price;
   private int NSold;
   private String type;

   public CarModel(String name, double weight, double sales_price, int NSold, String type) {
       this.name = name;
       this.weight = weight;
       this.sales_price = sales_price;
       this.NSold = NSold;
       this.type = type;
   }

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



