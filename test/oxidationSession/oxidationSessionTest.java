/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oxidationSession;

import org.junit.*;
import static org.junit.Assert.*;
import oxidationModel.SiOxidationModel;
import oxidationSession.oxidationSession.oxidationPhase;

/**
 *
 * @author ollivanhoja
 */
public class oxidationSessionTest {
    
    public oxidationSessionTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of get_t_tot method, of class oxidationSession.
     */
    @Test
    public void testGet_t_tot() {
        System.out.println("get_t_tot");
        oxidationSession instance = new oxidationSession();
        double expResult = 0.0;
        double result = instance.get_t_tot();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get_Xo method, of class oxidationSession.
     */
    @Test
    public void testGet_Xo() {
        System.out.println("get_Xo");
        oxidationSession instance = new oxidationSession();
        double expResult = 0.0;
        double result = instance.get_Xo();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get_hW method, of class oxidationSession.
     */
    @Test
    public void testGet_hW() {
        System.out.println("get_hW");
        oxidationSession instance = new oxidationSession();
        double expResult = 0.0;
        double result = instance.get_hW();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getI method, of class oxidationSession.
     */
    @Test
    public void testGetI() {
        System.out.println("getI");
        oxidationSession instance = new oxidationSession();
        int expResult = 0;
        int result = instance.getI();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setI method, of class oxidationSession.
     */
    @Test
    public void testSetI() {
        System.out.println("setI");
        int i = 0;
        oxidationSession instance = new oxidationSession();
        instance.setI(i);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addPhase method, of class oxidationSession.
     */
    @Test
    public void testAddPhase() {
        System.out.println("addPhase");
        double T = 0.0;
        double var = 0.0;
        SiOxidationModel oModel = null;
        boolean calc_t = false;
        oxidationSession instance = new oxidationSession();
        instance.addPhase(T, var, oModel, calc_t);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removePhase method, of class oxidationSession.
     */
    @Test
    public void testRemovePhase_int() {
        System.out.println("removePhase");
        int ind = 0;
        oxidationSession instance = new oxidationSession();
        instance.removePhase(ind);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removePhase method, of class oxidationSession.
     */
    @Test
    public void testRemovePhase_0args() {
        System.out.println("removePhase");
        oxidationSession instance = new oxidationSession();
        instance.removePhase();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of trim method, of class oxidationSession.
     */
    @Test
    public void testTrim() {
        System.out.println("trim");
        oxidationSession instance = new oxidationSession();
        instance.trim();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSize method, of class oxidationSession.
     */
    @Test
    public void testGetSize() {
        System.out.println("getSize");
        oxidationSession instance = new oxidationSession();
        int expResult = 0;
        int result = instance.getSize();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPhase method, of class oxidationSession.
     */
    @Test
    public void testGetPhase_int() {
        System.out.println("getPhase");
        int ind = 0;
        oxidationSession instance = new oxidationSession();
        oxidationPhase expResult = null;
        oxidationPhase result = instance.getPhase(ind);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPhase method, of class oxidationSession.
     */
    @Test
    public void testGetPhase_0args() {
        System.out.println("getPhase");
        oxidationSession instance = new oxidationSession();
        oxidationPhase expResult = null;
        oxidationPhase result = instance.getPhase();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calculateNextPhase method, of class oxidationSession.
     */
    @Test
    public void testCalculateNextPhase() {
        System.out.println("calculateNextPhase");
        oxidationSession instance = new oxidationSession();
        instance.calculateNextPhase();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calculate method, of class oxidationSession.
     */
    @Test
    public void testCalculate() {
        System.out.println("calculate");
        oxidationSession instance = new oxidationSession();
        instance.calculate();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
