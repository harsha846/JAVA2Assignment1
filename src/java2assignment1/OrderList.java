/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package java2assignment1;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author harsha
 */
public class OrderList {
    Queue<Order> orderList=new ArrayDeque<Order>();
    List<Order> orderProcessed=new ArrayList<Order>();
    
    
    public void add(Order order) throws NoCustomerException,NoPurchaseException{
        if(order.getCustomerId().isEmpty() && order.getCustomerName().isEmpty()){
            throw new NoCustomerException("No customer Exists");
        }else if(order.getListOfPurchase().isEmpty()){
            throw new NoPurchaseException("No Purchase Exists");
        }
        else
        {
            orderList.add(order);
        }
    }
    
    public Order nextOrder(){
        return orderList.peek();
    }
    
    public void processing(Order next)
    {
        if(next.equals(nextOrder()))
        
        {
            boolean isOkay=true;
            for(Map.Entry<Integer,Integer> entry:next.getListOfPurchase().entrySet()){
                int productId=entry.getKey();
                int quantity=entry.getValue();
                if(Inventory.getQuantityForId(productId)>quantity){
                    isOkay=false;
                }
            }
            if(isOkay){
                orderProcessed.add(orderList.remove());
                next.setTimeProcessed();
            }
            
        }
    }
    
    public void fullFill(Order next) throws NoTimeProcessedException{
        if(orderProcessed.contains(next)){
            if(next.timeProcessed==""){
                throw new NoTimeProcessedException("Time Processed is empty");
            }
            next.setTimeFullFilled();
        }
    }
    
    public String jsonReport(){
        String report="";
        if(!(orderList.isEmpty()||orderProcessed.isEmpty())){
            JSONArray orders=new JSONArray();
            for(Order o:orderProcessed){
                orders.add(o.toJSON());
            }
            for(Order o:orderList){
                orders.add(o.toJSON());
            }
            
            JSONObject obj=new JSONObject();
            obj.put("Orders", orders);
            report=obj.toJSONString();
        }
        return report;
    }
    
      public class NoPurchaseException extends Exception{
        public NoPurchaseException(String msg){
            super(msg);
        }
    }
    
    public class NoCustomerException extends Exception{
        public NoCustomerException(String msg){
            super(msg);
        }
    }
      public class NoTimeReceivedException extends Exception{
        public NoTimeReceivedException(String msg){
            super(msg);
        }
    }
  
    public class NoTimeProcessedException extends Exception{
        public NoTimeProcessedException(String msg){
            super(msg);
        }
    }
    
  
}
