package com.dio.test;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by iovchynnikov on 5/22/14.
 */
@XmlRootElement
public class PojoPersonWrapper implements Serializable {
    private String nameFirst;
    private String nameMiddle;
    private String nameLast;
    private EnumJob job;

    public PojoPersonWrapper() {}

    public PojoPersonWrapper(PojoPerson source) {
        this.nameFirst = source.getNameFirst();
        this.nameLast = source.getNameLast();
        this.nameMiddle = source.getNameMiddle();
        this.job = source.getJob();
    }

    public String getNameFirst() {
        return nameFirst;
    }

    public String getNameMiddle() {
        return nameMiddle;
    }

    public String getNameLast() {
        return nameLast;
    }

    public EnumJob getJob() {
        return job;
    }

    @XmlElement
    public void setNameFirst(String nameFirst) {
        this.nameFirst = nameFirst;
    }

    @XmlElement
    public void setNameMiddle(String nameMiddle) {
        this.nameMiddle = nameMiddle;
    }

    @XmlElement
    public void setNameLast(String nameLast) {
        this.nameLast = nameLast;
    }

    @XmlElement
    public void setJob(EnumJob job) {
        this.job = job;
    }

    public PojoPerson createPojoPerson() {
        return new PojoPerson(nameFirst, nameMiddle, nameLast, job);
    }
}
