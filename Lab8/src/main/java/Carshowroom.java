
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.*;

@Entity
public class Carshowroom {
    @Id
    @GeneratedValue
    private long id_carshowroom;
    private String salonname;
    private int maxcars;
    //private int currentCarsAmount;
    //List<Vehicle> carList = new LinkedList<>();
    //Map<String, Integer> nameAmountMap = new TreeMap<String, Integer>();

    @OneToMany(mappedBy = "id_rating")
    List<Rating> Rating;

    @OneToMany(mappedBy = "id_vehicle")
    List<Vehicle> Vehicle;

    public Carshowroom(String salonname, int maxcars) {
        this.salonname = salonname;
        this.maxcars = maxcars;
        //this.currentCarsAmount = 0;
    }

    public Carshowroom() {

    }

    public int getMaxcars() {
        return maxcars;
    }

    public String getSalonname() {
        return salonname;
    }

//    public int getCurrentCarsAmount() {
//        return currentCarsAmount;
//    }

//    void addProduct(Vehicle v){
//        if(currentCarsAmount >= maxCars ) {
//            System.err.println("Full salon!");
//        }
//        currentCarsAmount ++;
//
//        carList.add(v);
//
//        if(nameAmountMap.containsKey(v.getBrand())) {
//            nameAmountMap.replace(v.getBrand(),
//                    nameAmountMap.get(v.getBrand()),
//                    nameAmountMap.get(v.getBrand()) + 1 );
//        }else {
//            nameAmountMap.put(v.getBrand(), 1);
//        }
//    }
//
//    void getProduct(Vehicle v) {
//
//        for ( Vehicle temp : carList) {
//            if( temp.getModel().equals(v.getModel())) {
//                if (nameAmountMap.containsKey(v.getModel())) {
//                    nameAmountMap.replace(v.getModel(),
//                            nameAmountMap.get(v.getModel()),
//                            nameAmountMap.get(v.getModel()) - 1);
//                } else {
//                    nameAmountMap.remove(v.getModel());
//                }
//                currentCarsAmount--;
//                break;
//            }
//        }
//    }
//
//    void remove(Vehicle v){
//        currentCarsAmount =- nameAmountMap.get(v.getModel())+1;
//        carList.removeIf(temp -> temp.getModel().equals(v.getModel()));
//        nameAmountMap.remove(v.getModel());
//    }
//
//    void removeByRow(int i){
//        Vehicle v = carList.get(i);
//        //currentCarsAmount =- nameAmountMap.get(v.getModel())+1;
//        currentCarsAmount -= 1;
//        carList.removeIf(temp -> temp.getModel().equals(v.getModel()));
//        nameAmountMap.remove(v.getModel());
//    }
//
//
//    List<Vehicle> search(String brandName) { // return Vehicle?
//        List<Vehicle> brandNameCarList = new LinkedList<>();
//        for(Vehicle temp : carList) {
//            if(temp.getBrand().equals(brandName)) {
//                brandNameCarList.add(temp);
//            }
//        }
//        Collections.sort(brandNameCarList, new VehicleComparator());
//        return brandNameCarList;
//    }
//
//    int countByCondition(ItemConditions conditions) {
//        int i = 0;
//        for(Vehicle temp : carList) {
//            if(temp.getCondition() == conditions) {
//                i ++;
//            }
//        }
//        return i;
//    }
//
//    void summary() {
//        for(Vehicle temp : carList) {
//            temp.print();
//            System.out.println();
//        }
//
//    }
//
//    List<Vehicle> sortByName() {
//        List<Vehicle> sortedList = carList;
//        Collections.sort(sortedList, new VehicleComparator());
//        return sortedList;
//    }
//
//    Map<String, Integer> sortByAmount() {
//        Map<String, Integer> sorted = new TreeMap<>(new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                Integer o1Value = nameAmountMap.get(o1);
//                Integer o2Value = nameAmountMap.get(o2);
//                return -1 * o1Value.compareTo(o2Value);
//            }
//        });
//        sorted.putAll(nameAmountMap);
//        return sorted;
//    }
//
//    String max(){
//        Integer max = Collections.max(nameAmountMap.values());
//
//        for(Map.Entry<String,Integer> entry : nameAmountMap.entrySet()) {
//            //String key = entry.getKey();
//            if (max.equals(entry.getValue())) return entry.getKey();
//        }
//        return null;
//    }

    void insertIntoList(){
        Vehicle v;
        v = new Vehicle("Fiat","126p", ItemConditions.DAMAGED, 1990,504000,0.3, 3200);
       // addProduct(v);
        v = new Vehicle("Fiat","Punto", ItemConditions.USED, 2005,214000,1.0, 4500);
       // addProduct(v);
        v = new Vehicle("Seat","Ibiza 4", ItemConditions.NEW, 2014,12,1.4, 13400);
       // addProduct(v);
        v = new Vehicle("Seat","Leon", ItemConditions.NEW, 2020,5,1.5, 34500);
       // addProduct(v);
        v = new Vehicle("Opel","Corsa", ItemConditions.USED, 2000,141400,1.1, 7800);
       // addProduct(v);
        v = new Vehicle("Fiat","Grande Punto", ItemConditions.USED, 2008,114000,1.4, 10000);
       // addProduct(v);
    }



}
