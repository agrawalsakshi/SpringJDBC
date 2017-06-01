package com.sakshi.springjdbc.beans;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	ApplicationContext context= new ClassPathXmlApplicationContext("com/sakshi/springjdbc/beanConfig/beans.xml");
    	
    	OfferDAO offerDAO = (OfferDAO)context.getBean("offerDAO");
    	Offer offer1 = new Offer ("Michael","michael@gmail.com","Software testing at low cost");
    	Offer offer2 = new Offer ("Alex","alex@gmail.com","Software UI at low cost");
    	Offer offer3 = new Offer ("Fabio","fabio@gmail.com","Software development at low cost");

    	try{
    		
    		if(offerDAO.createOffer(offer1)){
    			System.out.println("Offer created successfully");
    		}
    		if(offerDAO.createOffer(offer2)){
    			System.out.println("Offer created successfully");
    		}
    		if(offerDAO.createOffer(offer3)){
    			System.out.println("Offer created successfully");
    		}
    		
    		List<Offer> offers = offerDAO.getOffers();
       	 for(Offer offer: offers){
       		 System.out.println(offer);
       	 }
       	 
       	 Offer offer = offerDAO.getOffer(12);
       	 System.out.println("offer with id : "+offer);
    	
       	 
       	 boolean result = offerDAO.deleteOffer(12);
       	System.out.println("Is offer with id  delete : "+result);
       	
       	Offer offer4 = new Offer(5,"Karen","karen@gmail.com", "Web Designer");
       	boolean update = offerDAO.updateOffer(offer4);
       	if(update){
       		System.out.println("Offer updated successfully");
       	}
    	}catch(Exception e){
    		e.getMessage();
    		e.getClass();
    	}
    	
    	
    	
    }
}
