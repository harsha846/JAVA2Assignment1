/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package java2assignment1;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author harsha
 */
public class OrderListTest {
    
    public OrderListTest() {
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
    public void testThrowingExceptionWhenIdOrNameIsEmpty() throws OrderList.NoPurchaseException{
        boolean didThrow=false;
        OrderList ordrli=new OrderList();
        Order order=new Order();
        order.setListOfPurchase(1, 2);
        try {
            order.add(order);
        } catch (OrderList.NoCustomerException e) {
            didThrow=true;
        }
        
        assertTrue(didThrow);
    }
    
    
    @Test
    public void testDoesNotThrowExceptionWhenTheValuesareNotNull() throws OrderList.NoPurchaseException{
        boolean didThrow=false;
        Order order=new Order("A","test");
        OrderList OL=new OrderList();
        order.setListOfPurchase(1, 2);
        try {
            OL.add(order);
        } catch (OrderList.NoCustomerException e) {
            didThrow=true;
        }
        assertFalse(didThrow);
    }
    
    @Test
    public void testThrowExceptionWhenPurchaselistisZero() throws OrderList.NoCustomerException{
        boolean didThrow=false;
        Order order=new Order("A","Test");
        OrderList oli=new OrderList();
        try {
            oli.add(order);
        } catch (OrderList.NoPurchaseException e) {
            didThrow=true;
        }
        
        assertTrue(didThrow);
    }
    

    
    
    @Test
    public void testReturnNullWhenNextOrderIsNotAvailable(){
        OrderList ol=new OrderList();
        assertEquals(null,ol.nextOrder());
        assertNull(ol.nextOrder());
    }
        @Test
    public void testGetNextWhenNextOrderIsAvailable() throws OrderList.NoCustomerException, OrderList.NoPurchaseException{
        OrderList ordrli=new OrderList();
        Order o=new Order("A","Test");
        o.setListOfPurchase(1, 2);
        ordrli.add(o);
        Order o1=new Order("B","Test2");
        o1.setListOfPurchase(2, 2);
        ordrli.add(o1);
        assertEquals(o,ordrli.nextOrder());
    }
    
    @Test
    public void testWhenReceivedIsSetThenSetProcessToNow() throws OrderList.NoCustomerException, OrderList.NoPurchaseException{
        Order order=new Order("a","tst");
        OrderList ol=new OrderList();
        order.setListOfPurchase(1, 8);
        ol.add(order);
        Order order2=new Order("b","as");
        order2.setListOfPurchase(2, 4);
        ol.add(order2);
        assertEquals(ol.orderList.size(),2);
        assertEquals(ol.orderProcessed.size(),0);
        ol.processing(ol.nextOrder());
        DateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        assertEquals(formatter.format(new Date()).toString(),order.getTimeProcessed());
        assertEquals(ol.orderList.size(),1);
        assertEquals(ol.orderProcessed.size(),1);
    }
     @Test
    public void testWhenReceivedTimeProcessedTimeFixedItemAvailButTimeProcessIsNullThenThrowException() throws OrderList.NoCustomerException, OrderList.NoPurchaseException, OrderList.NoTimeProcessedException{
        Order order=new Order("a","tst");
        OrderList ordrlis=new OrderList();
        order.setListOfPurchase(1, 8);
        ordrlis.add(order);
        Order order2=new Order("b","as");
        order2.setListOfPurchase(2, 4);
        ordrlis.add(order2);
        assertEquals(ordrlis.orderList.size(),2);
        assertEquals(ordrlis.orderProcessed.size(),0);
        ordrlis.processing(ordrlis.nextOrder());
        assertEquals(ordrlis.orderList.size(),1);
        assertEquals(ordrlis.orderProcessed.size(),1);
        ordrlis.fullFill(order);
        DateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        assertEquals(formatter.format(new Date()).toString(),order.getTimeFullFilled());
    }
    
    @Test
    public void testWhenReceivedTimeProcessedTimeFixedItemAvailThenFixTimeFulfilledNow() throws OrderList.NoCustomerException, OrderList.NoPurchaseException, OrderList.NoTimeProcessedException{
        Order order=new Order("a","tst");
        OrderList ordrli=new OrderList();
        order.setListOfPurchase(1, 8);
        ordrli.add(order);
        Order order2=new Order("b","as");
        order2.setListOfPurchase(2, 4);
        ordrli.add(order2);
        assertEquals(ordrli.orderList.size(),2);
        assertEquals(ordrli.orderProcessed.size(),0);
        ordrli.processing(ordrli.nextOrder());
        assertEquals(ordrli.orderList.size(),1);
        assertEquals(ordrli.orderProcessed.size(),1);
        ordrli.fullFill(order);
        DateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        assertEquals(formatter.format(new Date()).toString(),order.getTimeFullFilled());
    }
    
   
    
    @Test
    public void testWhenNoOrdersInListThenReturnEmptyString(){
        OrderList ol=new OrderList();
        assertEquals("",ol.jsonReport());
    }
    
    @Test
    public void testWhenOrdersThereReturnJSONFormattedReport() throws OrderList.NoCustomerException, OrderList.NoPurchaseException, OrderList.NoTimeProcessedException{
        Order o1=new Order("1","Customer1");
        o1.setListOfPurchase(1, 1);
        OrderList ol=new OrderList();
        ol.add(o1);
        Order o2=new Order("2","Customer2");
        o2.setListOfPurchase(2,2);
        ol.add(o2);
        ol.processing(ol.nextOrder());
        HashMap a=new LinkedHashMap();
        a.put("customerId", o1.getCustomerId());
        a.put("customerName", o1.getCustomerName());
        a.put("timeReceived", o1.getTimeReceived());
        a.put("timeProcessesed", o1.getTimeProcessed());
        a.put("timeFulFilled", o1.getTimeFullFilled());
        JSONArray purchases=new JSONArray();
        JSONObject purchase=new JSONObject();
        purchase.put("productId", 1);
        purchase.put("quantity", 1);
        purchases.add(purchase);
        a.put("purchase", purchases);
        a.put("notes", o1.getNotes());
        JSONObject b=new JSONObject();
        b.put("customerId", 2);
        b.put("customerName", "Customer2");
        b.put("timeReceived",o2.getTimeReceived());
        b.put("timeProcessed",o2.getTimeProcessed());
        b.put("timeFulFilled",o2.getTimeFullFilled());
        JSONArray purchases2=new JSONArray();
        HashMap purchase2=new LinkedHashMap();
        purchase2.put("productId", 2);
        purchase2.put("quantity", 2);
        purchases2.add(purchase2);
        b.put("purchase", purchase2);
        b.put("notes", o2.getNotes());
        JSONArray orders=new JSONArray();
        orders.add(a);
        orders.add(b);
        JSONObject expResult=new JSONObject();
        expResult.put("Orders", orders);
        
        String result=ol.jsonReport();
        assertEquals(expResult,result);
    }
    
    
    
}
