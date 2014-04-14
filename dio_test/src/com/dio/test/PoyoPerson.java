package com.dio.test;
/**
 * Created by iovchynnikov on 4/8/14.
 * Comparing strings test
 */
public class PoyoPerson {
    final private String nameFirst;
    final private String nameMiddle;
    final private String nameLast;
    private EnumJob job;

    private int hashCode = 0;

    public static void main(String[] args) {
        PoyoPerson o1 = new PoyoPerson("Toma", null, "Hawk", EnumJob.QA);
        o1.printInfo();
        PoyoPerson o11 = new PoyoPerson("Toma", null, "Hawk");
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

    public PoyoPerson(String pf, String pm, String pl) {
        this(pf, pm, pl, null);
    }

    public PoyoPerson(String pf, String pm, String pl, EnumJob pjob) {
        nameFirst = pf == null ? "" : pf;
        nameMiddle = pm == null ? "" : pm;
        nameLast = pl == null ? "" : pl;
        job = pjob;
    }

    public void printInfo() {
        System.out.println(((("Person: " + nameLast).trim() + " " + nameFirst).trim() + " " + nameMiddle).trim() + (job == null ? "": ", " + job));
        System.out.println("Hashcode is " + hashCode());
    }

    public void setJob(EnumJob pjob) {
        job = pjob;
        hashCode = 0;
    }

    public EnumJob getJob() {
        return job;
    }

    public boolean equals(Object obj) {

        return (obj != null) &&
                (obj instanceof PoyoPerson) &&
//                we can also add hash codes to comparing if they are stored wherever
//                (getHashCode() == ((PoyoPerson)obj).getHashCode() ) &&
                (nameFirst.equals(((PoyoPerson)obj).nameFirst)) &&
                (nameMiddle.equals(((PoyoPerson)obj).nameMiddle)) &&
                (nameLast.equals(((PoyoPerson)obj).nameLast)) &&
                (job == null && ((PoyoPerson)obj).job == null || job != null && job.equals(((PoyoPerson)obj).job));
    }

    public int hashCode() {
        hashCode = 0;
        int mode = 30;
        hashCode = hashCode * mode  + nameFirst.hashCode();
        hashCode = hashCode * mode  + nameMiddle.hashCode();
        hashCode = hashCode * mode  + nameLast.hashCode();
        hashCode = hashCode * mode  + (job == null? 0 : job.hashCode());
        return hashCode;
    }

    public int getHashCode() {
        // calculate hashCode if needed
        if (hashCode == 0)
            return hashCode();
        else
            return hashCode;
    }
}
