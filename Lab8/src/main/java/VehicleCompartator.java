


import java.util.Comparator;

class VehicleComparator implements Comparator<Vehicle> {
    @Override
    public int compare(Vehicle o1, Vehicle o2) {
        return o1.getBrand().compareTo(o2.getBrand());
    }
}
