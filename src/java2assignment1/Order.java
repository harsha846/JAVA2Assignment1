/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java2assignment1;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.TimeZone;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


/**
 *
 * @author harsha
 */
public class Order extends OrderList{
    String customerId;
    String customerName;
    String timeReceived;
    String timeProcessed;
    String timeFulfilled;
    Map<Integer,Integer> productId_quantity=new LinkedHashMap<>();
    String notes;
    
    public Order(){
        this.customerId="";
        this.customerName="";
        this.timeProcessed="";
        this.timeFulfilled="";
        this.notes="";
      
    }
    
    public Order(String customerId,String customerName){
        this.customerId=customerId;
        this.customerName=customerName;
        this.setTimeReceived();
        this.timeProcessed="";
        this.timeFulfilled="";
        this.notes="";
    }
    
    public void setCustomerId(String customerId){
        this.customerId=customerId;
        this.setTimeReceived();
    }
    
    
    
    public String getCustomerName(){
        return this.customerName;
    }
    
    public void setTimeReceived(){
        DateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        this.timeReceived=formatter.format(new Date()).toString();
    }
    
    public String getCustomerId(){
        return this.customerId;
    }
    
    public void setCustomerName(String customerName){
        this.customerName=customerName;
        this.setTimeReceived();
    }
    public String getTimeReceived(){
        return this.timeReceived;
    }
    
    public void setTimeProcessed(){
        DateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        this.timeProcessed=formatter.format(new Date()).toString();
    }
    
    public String getTimeProcessed(){
        return this.timeProcessed;
    }
    
 
    public void setListOfPurchase(Integer productId,Integer quantity){
        productId_quantity.put(productId, quantity);
    }
    
    public Map<Integer,Integer> getListOfPurchase(){
        return this.productId_quantity;
    }
    
    public void setNotes(String notes){
        this.notes=notes;
    }
    
    public String getNotes(){
        return this.notes;
    }
    
       public void setTimeFullFilled(){
        DateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        this.timeFulfilled=formatter.format(new Date()).toString();
    }
    
    public String getTimeFullFilled(){
        return this.timeFulfilled;
    }
    
    
    public String toJSON(){
        JSONObject root=new JSONObject();
        
           JSONArray purchasesArray=new JSONArray();
        for(Map.Entry<Integer,Integer> entry:this.productId_quantity.entrySet()){
            JSONObject purchases=new JSONObject();
            purchases.put("productId", entry.getKey());
            purchases.put("quantity",entry.getValue());
            purchasesArray.add(purchases);
        }
        
        
        HashMap classObj=new LinkedHashMap();
        classObj.put("customerId", this.getCustomerId());
        classObj.put("customerName", this.getCustomerName());
        classObj.put("timeReceived", this.getTimeReceived());
        classObj.put("timeProcessed", this.getTimeProcessed());
        classObj.put("timeFulfilled", this.getTimeFullFilled());
        classObj.put("purchases", purchasesArray);
        classObj.put("notes", this.getNotes());
        
        JSONArray orders=new JSONArray();
        orders.add(classObj);
        
        root.put("", orders);
        return orders.toJSONString();
    }
    
}
