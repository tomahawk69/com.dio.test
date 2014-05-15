package com.dio.test;


import java.util.*;
import java.io.*;

/**
 * Created by yur on 21.04.2014.
 * Util class ver.3 for PojoPerson
 * Used lists
 */
public class PojoPersonUtils3 {

    /**
     * Full join
     * constructed from List.addAll
     * @param list1 PojoPerson 1
     * @param list2 PojoPerson 2
     * @return PojoPerson result
     */
    public List<PojoPerson> joinFull(List<PojoPerson> list1, List<PojoPerson> list2) throws IllegalArgumentException {
        if (list1 == null || list2 == null)
            throw new IllegalArgumentException("Argument could not be null");
        List<PojoPerson> result = new ArrayList<>(list1);
        result.addAll(list2);
        return result;
    }

    /**
     * Used set to make distinct list
     */
    public List<PojoPerson> distinct(List<PojoPerson> source) {
        return distinctPrevious(source, new ArrayList<PojoPerson>());
    }

    public List<PojoPerson> distinctPrevious(List<PojoPerson> source, List<PojoPerson> previous) {
        Set<PojoPerson> result = new LinkedHashSet<PojoPerson>(previous);
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
        //result.retainAll(list2);
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
        if (list1 == null || list2 == null)
            throw new IllegalArgumentException("Argument could not be null");
        List<PojoPerson> result1 = new ArrayList<>(list1);
        List<PojoPerson> result2 = new ArrayList<>(list2);
        result1.removeAll(list2);
        result2.removeAll(list1);
        return joinFull(result1, result2);
    }

    /**
     * Contains hand-made function
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

    public static boolean saveObject(Object inputObject, String fileName) {
        FileOutputStream fileStream = null;
        try {
            fileStream = new FileOutputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        try {
            ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
            objectStream.writeObject(inputObject);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        try {
            fileStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static Object readObject(String fileName) {
        FileInputStream fileStream = null;
        try {
            fileStream = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        Object result = null;
        try {
            ObjectInputStream objectStream = new ObjectInputStream(fileStream);
            result = objectStream.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fileStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean saveObjects(String fileName, Object... inputObjects) {
        FileOutputStream fileStream = null;
        try {
            fileStream = new FileOutputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        try {
            ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
            objectStream.writeInt(inputObjects.length);
            for (Object object : inputObjects) {
                objectStream.writeObject(object);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        try {
            fileStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static List<Object> readObjects(String fileName) {
        FileInputStream fileStream = null;
        try {
            fileStream = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        List<Object> result = new LinkedList<>();
        try {
            ObjectInputStream objectStream = new ObjectInputStream(fileStream);
            int count = objectStream.readInt();
            for (int i = 0; i < count; i++) {
                result.add(objectStream.readObject());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fileStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean saveObjects2(String fileName, Object... inputObjects) {
        FileOutputStream fileStream = null;
        try {
            fileStream = new FileOutputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        try {
            ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
            objectStream.writeObject(Arrays.asList(inputObjects));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        try {
            fileStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static List<Object> readObjects2(String fileName) {
        FileInputStream fileStream = null;
        try {
            fileStream = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        List<Object> result = null;
        try {
            ObjectInputStream objectStream = new ObjectInputStream(fileStream);
            result = (List<Object>) objectStream.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fileStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
