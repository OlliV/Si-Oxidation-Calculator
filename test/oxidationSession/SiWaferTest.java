/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oxidationSession;

import org.junit.*;
import static org.junit.Assert.*;
import oxidationSession.SiWafer.eWaferOrientation;

/**
 *
 * @author ollivanhoja
 */
public class SiWaferTest {
    SiWafer testWafer;
    
    public SiWaferTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        testWafer = new SiWafer(eWaferOrientation.mi100, 0);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getOrientation method, of class SiWafer.
     */
    @Test
    public void testGetOrientation() {
        System.out.println("getOrientation");
        SiWafer instance = null;
        eWaferOrientation expResult = null;
        eWaferOrientation result = instance.getOrientation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOrientation method, of class SiWafer.
     */
    @Test
    public void testSetOrientation() {
        System.out.println("setOrientation");
        eWaferOrientation orientation = null;
        SiWafer instance = null;
        instance.setOrientation(orientation);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getXo and setXo method, of class SiWafer.
     */
    @Test
    public void Xo() {
        double expResult = 0.0;
        double result = 0.0;
        
        System.out.println("getXo");
        SiWafer instance = testWafer;
        expResult = 0.0;
        result = instance.getXo();
        assertEquals(expResult, result, 0.0);
        
        System.out.println("setXo");
        expResult = 0.1;
        instance.setXo(expResult);
        result = instance.getXo();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of get_h method, of class SiWafer.
     */
    @Test
    public void testGet_h() {
        System.out.println("get_h");
        SiWafer instance = null;
        double expResult = 0.0;
        double result = instance.get_h();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of set_h method, of class SiWafer.
     */
    @Test
    public void testSet_h() {
        System.out.println("set_h");
        double h = 0.0;
        SiWafer instance = null;
        instance.set_h(h);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
