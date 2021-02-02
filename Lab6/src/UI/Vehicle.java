package UI;

public class Vehicle implements Comparable<Vehicle>  {
    private String brand;   //marka
    private String model;
    private ItemConditions condition;
    private int production_year;
    private double mileage;     // przebieg w km!
    private double engineSize; // pojemność silnika
    private boolean isReserved;
    private String city;
    private double price;
    private String isReservedS;

    public Vehicle(String brand, String model, ItemConditions condition, int production_year, double mileage, double engineSize, String city, double price) {
        this.brand = brand;
        this.model = model;
        this.condition = condition;
        this.production_year = production_year;
        this.mileage = mileage;
        this.engineSize = engineSize;
        this.isReserved = false;
        this.city = city;
        this.price = price;
        this.isReservedS = "no";
    }

    public String getIsReservedS() {
        return isReservedS;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void Reserve() {
        this.isReserved = true;
        this.isReservedS = "RESERVED";
    }

    public String getCity() {
        return city;
    }

    public double getPrice() {
        return price;
    }

    public String getModel() {
        return model;
    }

    public int getProduction_year() {
        return production_year;
    }

    public double getMileage() {
        return mileage;
    }

    public double getEngineSize() {
        return engineSize;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    public String getBrand() {
        return brand;
    }

    public ItemConditions getCondition() {
        return condition;
    }

    public void print(){
        System.out.println("Brand: " + this.brand);
        System.out.println("Model: " + this.model);
        System.out.println("Condition: " + this.condition);
        System.out.println("Production Year: " + this.production_year);
        System.out.println("Mileage(in km): " + this.mileage);
        System.out.println("EngineSize: " + this.engineSize);
    }

    @Override
    public int compareTo(Vehicle o) {
        return this.getBrand().compareTo(o.getBrand());
    } // tego nie musi być i zakomentuj implements

}
