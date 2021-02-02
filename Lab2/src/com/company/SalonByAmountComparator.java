package com.company;

import java.util.Comparator;

public class SalonByAmountComparator implements Comparator<CarShowroom> {
    @Override
    public int compare(CarShowroom o1, CarShowroom o2) {
        return Integer.compare(o1.getMaxCars(), o2.getMaxCars());
    }
}
