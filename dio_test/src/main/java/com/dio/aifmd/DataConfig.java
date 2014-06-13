package com.dio.aifmd;

import com.dio.test.PojoPerson;
import com.dio.test.PojoPersonWrapper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.SQLException;

/**
 * Created by iovchynnikov on 5/22/14.
 */
public class DataConfig {
    private DataOperations operations;

    public DataConfig(DataOperations operations) {
        this.operations = operations;
    }

    public void write(PojoPerson inputObject) throws JAXBException {
        Marshaller marshaller = getMarshaller(PojoPersonWrapper.class);
        StringWriter sw = new StringWriter();

        PojoPersonWrapper wrapper = new PojoPersonWrapper(inputObject);
        marshaller.marshal(wrapper, sw);
        System.out.println(sw.toString());

        try {
            try {
                String query = "insert into test_config (f_name, f_xml) values (?, ?)";
                System.out.println(query);
                operations.performInsertParams(query, inputObject.toString(), sw.toString());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Marshaller getMarshaller(Class objectClass) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(objectClass);
        return context.createMarshaller();
    }

    public void delete(PojoPerson inputObject) throws SQLException, ClassNotFoundException {
        String query = "delete from test_config where f_name = ?";
        operations.performInsertParams(query, inputObject.toString());
    }

    public PojoPerson read(String person) throws SQLException, ClassNotFoundException {
        String query = "select f_xml from test_config where f_name = ?";
        String xml = operations.performSelectParams(query, person)[0];

        PojoPerson result = null;
        try {
            Unmarshaller unmarshaller = getUnmarshaller(PojoPersonWrapper.class);
            StringReader sr = new StringReader(xml);
            result = ((PojoPersonWrapper)unmarshaller.unmarshal(sr)).createPojoPerson();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return result;
    }

    public Unmarshaller getUnmarshaller(Class objectClass) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(objectClass);
        return context.createUnmarshaller();
    }
}
