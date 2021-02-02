package com.firm.code;


import java.util.Comparator;

public class AmountComparator implements Comparator<CarShowroom> {
    @Override
    public int compare(CarShowroom o1, CarShowroom o2) {
        return Integer.compare(o1.getMaxCars(), o2.getMaxCars());
    }
}
