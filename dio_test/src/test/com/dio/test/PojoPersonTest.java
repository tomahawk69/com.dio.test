package test.com.dio.test;
import com.dio.test.PojoPerson;
import com.dio.test.EnumJob;

import static org.junit.Assert.*;


/**
 * Created by iovchynnikov on 4/22/14.
 */
public class PojoPersonTest {
    private PojoPerson o1, o2, o3;

    @org.junit.Before
    public void setUp() throws Exception {
        o1 = new PojoPerson("Toma", null, "Hawk", EnumJob.QA);
        o2 = new PojoPerson("Toma", null, "Hawk", null);
        o3 = new PojoPerson("Toma", null, "Hawk", EnumJob.QA);
    }

    @org.junit.Test
    public void testEquals() throws Exception {
        assertTrue("Objects must be equal", o1.equals(o3));
    }

    @org.junit.Test
    public void testEqualsNegative() throws Exception {
        assertFalse("Objects must not be equal", o1.equals(o2));
    }

    @org.junit.Test
    public void testToString() throws Exception {
        assertEquals("PojoPerson{job=QA, nameMiddle='', nameLast='Hawk', nameFirst='Toma'}", o1.toString());
    }
}
