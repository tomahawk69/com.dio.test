package com.dio.test;

import org.mockito.internal.util.collections.ListUtil;

import java.util.*;

/**
 * Created by yur on 21.04.2014.
 * Util class ver.3 for PojoPerson
 * Used lists
 */
public class PojoPersonUtils3 {

    private int length(List<PojoPerson> list) {
        if (list == null)
            return 0;
        else
            return list.size();
    }
    /**
     * Full join
     * constructed from List.addAll
     * @param list1 PojoPerson 1
     * @param list2 PojoPerson 2
     * @return PojoPerson result
     */
    public List<PojoPerson> joinFull(List<PojoPerson> list1, List<PojoPerson> list2) {
        List<PojoPerson> result = new ArrayList<>();
        if (length(list1) > 0)
            result.addAll(list1);
        if (length(list2) > 0)
            result.addAll(list2);
        return result;
    }

    /**
     * Used set to make distinct list
     * @param source
     * @param previous
     * @return set list created from a set
     */
    private List<PojoPerson> distinct(List<PojoPerson> source, List<PojoPerson> previous) {
        Set<PojoPerson> result = new HashSet<PojoPerson>(previous);
        result.addAll(source);
        return new ArrayList<PojoPerson>(result);
    }


    /**
     * Inner join
     * calculate result with an iterator
     */
    public List<PojoPerson> joinInner(List<PojoPerson> list1, List<PojoPerson> list2)  throws IllegalArgumentException {
        if (list1 == null || list2 == null)
            throw new IllegalArgumentException("Argument could not be null");
        List<PojoPerson> result = new ArrayList<PojoPerson>(list1);
        Iterator it = result.iterator();
        while (it.hasNext()) {
            if (!list2.contains(it.next()))
                it.remove();
        }
        return result;
    }

    /**
     * Outer join
     * made with removeAll method and later full join results
     */
    public List<PojoPerson> joinOuter(List<PojoPerson> list1, List<PojoPerson> list2)  throws IllegalArgumentException {
        List<PojoPerson> result1 = new ArrayList<>(list1);
        List<PojoPerson> result2 = new ArrayList<>(list2);
        result1.removeAll(list2);
        result2.removeAll(list1);
        return joinFull(result1, result2);
    }

    /**
     * Contains function for class
     *
     */
    public boolean contains(List<PojoPerson> persons, PojoPerson person) throws IllegalArgumentException {
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
