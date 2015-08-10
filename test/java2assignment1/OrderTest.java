/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java2assignment1;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author harsha
 */
public class OrderTest {
    
    public OrderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testSomeMethod() {
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    @Test
    public void testDoubleValuedConstructor(){
        Order order=new Order("0123","Customer1");
        assertEquals("Customer1",order.getCustomerName());
        assertEquals("0123",order.getCustomerId());
        DateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        assertEquals(formatter.format(new Date()).toString(),order.getTimeReceived());
    }
    
 
    
    @Test
    public void testSetGetCustomername(){
        Order order=new Order();
        assertEquals("",order.getCustomerName());
        order.setCustomerName("Customer1");
        assertEquals("Customer1",order.getCustomerName());
    }
       @Test
    public void testSetGetCustomerId(){
        Order order=new Order();
        assertTrue(order.getCustomerId().isEmpty());
        order.setCustomerId("1");
        assertEquals("1",order.getCustomerId());
    }
    
    @Test
    public void testSetGetTimeProcessed(){
        Order order=new Order("1","Customer1");
        DateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        order.setTimeProcessed();
        assertEquals(formatter.format(new Date()).toString(),order.getTimeProcessed());
    }
    
   
    
    @Test
    public void testSetListOfPurchase(){
        Order order=new Order("1","Customer1");
        order.setListOfPurchase(1, 5);
        order.setListOfPurchase(5, 2);
        assertEquals(2,order.productId_quantity.size());
        //assertEquals(1,order.productId_quantity.keySet());
    }
     @Test
    public void testSetGetTimeFullFilled(){
        Order order=new Order("1","Customer 1");
        DateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        order.setTimeFullFilled();
        assertEquals(formatter.format(new Date()).toString(),order.getTimeFullFilled());
    }
    
    
    @Test
    public void testToJson(){
        Order order=new Order("1","Customer1");
        order.setListOfPurchase(5, 1);
        assertEquals("[{\"customerId\":\"1\",\"customerName\":\"Customer1\","
                + "\"timeReceived\":\""+order.getTimeReceived()+"\",\"timeProcessed\":"
                + "\"\",\"timeFulfilled\":\"\",\"purchases\":[{\"quantity\":1,\"productId\":5}],"
                + "\"notes\":\"\"}]",order.toJSON());
    }
    
    
    
    
}
