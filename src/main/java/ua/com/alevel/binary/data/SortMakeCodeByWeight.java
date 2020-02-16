package ua.com.alevel.binary.data;

import java.util.Comparator;

public class SortMakeCodeByWeight implements Comparator<MakeCode> {

    @Override
    public int compare(MakeCode m1, MakeCode m2) {
        return m1.getWeight() - m2.getWeight();
    }
}
