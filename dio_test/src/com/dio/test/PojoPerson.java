package com.dio.test;

/**
 * Created by iovchynnikov on 4/8/14.
 * Comparing strings test
 */
public class PojoPerson {
    final private String nameFirst;
    final private String nameMiddle;
    final private String nameLast;
    private EnumJob job;


    public PojoPerson(String pf, String pm, String pl) {
        this(pf, pm, pl, null);
    }

    public PojoPerson(String pf, String pm, String pl, EnumJob pjob) {
        nameFirst = pf == null ? "" : pf;
        nameMiddle = pm == null ? "" : pm;
        nameLast = pl == null ? "" : pl;
        job = pjob;
    }

    public void printInfo() {
        System.out.println(((("Person: " + nameLast).trim() + " " + nameFirst).trim() + " " + nameMiddle).trim() + (job == null ? "" : ", " + job));
        System.out.println("Hashcode is " + hashCode());
    }

    public EnumJob getJob() {
        return job;
    }

    public void setJob(EnumJob pjob) {
        job = pjob;
        ///hashCode = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PojoPerson that = (PojoPerson) o;

        if (job != that.job) return false;
        if (nameFirst != null ? !nameFirst.equals(that.nameFirst) : that.nameFirst != null) return false;
        if (nameLast != null ? !nameLast.equals(that.nameLast) : that.nameLast != null) return false;
        if (nameMiddle != null ? !nameMiddle.equals(that.nameMiddle) : that.nameMiddle != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nameFirst != null ? nameFirst.hashCode() : 0;
        result = 31 * result + (nameMiddle != null ? nameMiddle.hashCode() : 0);
        result = 31 * result + (nameLast != null ? nameLast.hashCode() : 0);
        result = 31 * result + (job != null ? job.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PojoPerson{");
        sb.append("job=").append(job);
        sb.append(", nameMiddle='").append(nameMiddle).append('\'');
        sb.append(", nameLast='").append(nameLast).append('\'');
        sb.append(", nameFirst='").append(nameFirst).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
