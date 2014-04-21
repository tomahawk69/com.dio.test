package test.com.dio.test;

import com.dio.test.EnumJob;
import com.dio.test.PojoPerson;

/**
 * Created by yur on 21.04.2014.
 */
public class PojoPersonTest extends BaseTest {
    public static void main(String[] args) {
        PojoPerson o1 = new PojoPerson("Toma", null, "Hawk", EnumJob.QA);
        o1.printInfo();
        PojoPerson o11 = new PojoPerson("Toma", null, "Hawk");
        o11.printInfo();
        System.out.println("o1 == o11? - " + (o1 == o11));
        System.out.println("o1 is equal o11? - " + o1.equals(o11));
        System.out.println("o11 is equal o1? - " + o11.equals(o1));


        o11.setJob(o1.getJob());
        o11.printInfo();
        System.out.println("o1 == o11? - " + (o1 == o11));
        System.out.println("o1 is equal o11? - " + o1.equals(o11));
        System.out.println("o11 is equal o1? - " + o11.equals(o1));

        o1.setJob(null);
        o11.setJob(o1.getJob());
        o1.printInfo();
        o11.printInfo();
        System.out.println("o1 == o11? - " + (o1 == o11));
        System.out.println("o1 is equal o11? - " + o1.equals(o11));
        System.out.println("o11 is equal o1? - " + o11.equals(o1));

        o11 = o1; // 011 and 01 now referenced the same object
        o11.printInfo();
        System.out.println("o1 == o11? - " + (o1 == o11));
        System.out.println("o1 is equal o11? - " + o1.equals(o11));
        System.out.println("o11 is equal o1? - " + o11.equals(o1));
    }
}
