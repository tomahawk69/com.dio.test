package com.dio.test;

import org.mockito.internal.util.collections.ListUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yur on 21.04.2014.
 * Util class ver.3 for PojoPerson
 * Used lists
 */
public class PojoPersonUtils3 {

    private int length(List<Object> list) {
        if (list == null)
            return 0;
        else
            return list.size();
    }
    /**
     * Full join
     * Considerate nulls in parameters
     * @param arr1 PojoPerson 1
     * @param arr2 PojoPerson 2
     * @return PojoPerson result
     */
    public List<PojoPerson> joinFull(List<Object> list1, List<Object> list2) {
        List<PojoPerson> result = new ArrayList<>(length(list1) + length(list2));
        /*
        if (arr1 != null)
            System.arraycopy(arr1, 0, result, 0, length(arr1));
        if (arr2 != null)
            System.arraycopy(arr2, 0, result, length(arr1), length(arr2));
        */
        return result;
    }

    private PojoPerson[] scanDistinct(PojoPerson[] source, PojoPerson[] previous) {
        PojoPerson[] result = new PojoPerson[0];
//                Arrays.copyOf(previous, length(source) + length(previous));
        int i = previous.length;
//        for (PojoPerson item : source)
//            if (!contains(result, item))
//                result[i++] = item;
        return Arrays.copyOf(result, i);
    }
    /**
     * Distinct join
     * @param arr1 PojoPerson 1
     * @param arr2 PojoPerson 2
     * @return PojoPerson result
     */
    public PojoPerson[] joinDistinct(PojoPerson[] arr1, PojoPerson[] arr2) {
        return (scanDistinct(arr2, scanDistinct(arr1, new PojoPerson[0])));
    }

    /**
     * Inner join
     * @param arr1 PojoPerson 1
     * @param arr2 PojoPerson 2
     * @return PojoPerson result
     */
    public PojoPerson[] joinInner(PojoPerson[] arr1, PojoPerson[] arr2) {
        PojoPerson[] temp = new PojoPerson[0];//length(arr1)];
        int i = 0;
        // pass only first array
        if (arr1 != null)
            for (PojoPerson s : arr1)
                if (contains(arr2, s) && !contains(temp, s))
                    temp[i++] = s;

        return Arrays.copyOf(temp, i);
    }

    private PojoPerson[] scanOuter(PojoPerson[] source, PojoPerson[] other, PojoPerson[] previous) {
        PojoPerson[] result = Arrays.copyOf(previous, source.length);//length(source) + length(previous));
        int i = previous.length;//length(previous);
        for (PojoPerson item : source)
            if (!contains(result, item) && (!contains(other, item)))
                result[i++] = item;
        return Arrays.copyOf(result, i);
    }
    /**
     * Outer join
     * @param arr1 PojoPerson 1
     * @param arr2 PojoPerson 2
     * @return PojoPerson result
     */
    public PojoPerson[] joinOuter(PojoPerson[] arr1, PojoPerson[] arr2) {
        return scanOuter(arr2, arr1, scanOuter(arr1, arr2, new PojoPerson[0]));
    }

    public boolean contains(PojoPerson[] persons, PojoPerson person) throws IllegalArgumentException {
        if (persons == null)
            return false;
        if (person == null)
            throw new IllegalArgumentException("Person could not be null");

        for (PojoPerson item : persons) {
            if (item != null && person.equals(item)) {
                return true;
            }
        }
        return false;
    }

}
