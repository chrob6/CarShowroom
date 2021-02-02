package UI;

import java.util.*;

public class CarShowroom {
    private String salonName;
    private int maxCars;
    private int currentCarsAmount;
    List<Vehicle> carList = new LinkedList<>();
    Map<String, Integer> nameAmountMap = new TreeMap<String, Integer>();

    public CarShowroom(String salonName, int maxCars) {
        this.salonName = salonName;
        this.maxCars = maxCars;
        this.currentCarsAmount = 0;
    }

    public int getMaxCars() {
        return maxCars;
    }

    public String getSalonName() {
        return salonName;
    }

    public int getCurrentCarsAmount() {
        return currentCarsAmount;
    }

    void addProduct(Vehicle v){
        if(currentCarsAmount >= maxCars ) {
            System.err.println("Full salon!");
        }
        currentCarsAmount ++;

        carList.add(v);

        if(nameAmountMap.containsKey(v.getBrand())) {
            nameAmountMap.replace(v.getBrand(),
                    nameAmountMap.get(v.getBrand()),
                    nameAmountMap.get(v.getBrand()) + 1 );
        }else {
            nameAmountMap.put(v.getBrand(), 1);
        }
    }

    void getProduct(Vehicle v) {

        for ( Vehicle temp : carList) {
            if( temp.getBrand().equals(v.getBrand())) {
                if (nameAmountMap.containsKey(v.getBrand())) {
                    nameAmountMap.replace(v.getBrand(),
                            nameAmountMap.get(v.getBrand()),
                            nameAmountMap.get(v.getBrand()) - 1);
                } else {
                    nameAmountMap.remove(v.getBrand());
                }
                currentCarsAmount--;
                break;
            }
        }
    }

    void remove(Vehicle v){
        currentCarsAmount =- nameAmountMap.get(v.getBrand());
        carList.removeIf(temp -> temp.getBrand().equals(v.getBrand()));
        nameAmountMap.remove(v.getBrand());
    }

    List<Vehicle> search(String brandName) { // return Vehicle?
        List<Vehicle> brandNameCarList = new LinkedList<>();
        for(Vehicle temp : carList) {
            if(temp.getBrand().equals(brandName)) {
                brandNameCarList.add(temp);
            }
        }
        Collections.sort(brandNameCarList, new VehicleComparator());
        return brandNameCarList;
    }

    int countByCondition(ItemConditions conditions) {
        int i = 0;
        for(Vehicle temp : carList) {
            if(temp.getCondition() == conditions) {
                i ++;
            }
        }
        return i;
    }

    void summary() {
        for(Vehicle temp : carList) {
            temp.print();
            System.out.println();
        }
    }

    List<Vehicle> sortByName() {
        List<Vehicle> sortedList = carList;
        Collections.sort(sortedList, new VehicleComparator());
        return sortedList;
    }

    Map<String, Integer> sortByAmount() {
        Map<String, Integer> sorted = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                Integer o1Value = nameAmountMap.get(o1);
                Integer o2Value = nameAmountMap.get(o2);
                return -1 * o1Value.compareTo(o2Value);
            }
        });
        sorted.putAll(nameAmountMap);
        return sorted;
    }

    String max(){
        Integer max = Collections.max(nameAmountMap.values());

        for(Map.Entry<String,Integer> entry : nameAmountMap.entrySet()) {
            //String key = entry.getKey();
            if (max.equals(entry.getValue())) return entry.getKey();
        }
        return null;
    }

    void insertIntoList(){
        Vehicle v;
        v = new Vehicle("Fiat","126p", ItemConditions.DAMAGED, 1990,504000,0.3, "Warszawa", 2000);
        addProduct(v);
        v = new Vehicle("Fiat","Punto", ItemConditions.USED, 2005,214000,1.0, "Krakow", 7600);
        addProduct(v);
        v = new Vehicle("Seat","Ibiza 4", ItemConditions.NEW, 2014,12,1.4, "Bielsko-Biala", 17000);
        addProduct(v);
        v = new Vehicle("Seat","Leon", ItemConditions.NEW, 2020,5,1.5, "Warszawa", 34700);
        addProduct(v);
        v = new Vehicle("Opel","Corsa", ItemConditions.USED, 2000,141400,1.1,"Krakow", 7000);
        addProduct(v);
        v = new Vehicle("Fiat","Grande Punto", ItemConditions.USED, 2008,114000,1.4,"Lodz", 10000);
        addProduct(v);
        v = new Vehicle("Mercedes-Benz","Class E", ItemConditions.USED, 2017,97000,2.0, "Warszawa", 20000);
        addProduct(v);
        v = new Vehicle("Volkswagen","Golf VI", ItemConditions.NEW, 2010,99000,1.4, "Lodz", 33600);
        addProduct(v);
        v = new Vehicle("Audi","A8", ItemConditions.USED, 2018,83000,3.0,"Wroclaw", 248000);
        addProduct(v);
        v = new Vehicle("Volkswagen","Tiguan", ItemConditions.USED, 2016,79000,2.0,"Krakow", 75000);
        addProduct(v);
        v = new Vehicle("Mercedes-Benz","Class B", ItemConditions.USED, 2019,5800,1.3, "Warszawa", 139700);
        addProduct(v);
    }
}
