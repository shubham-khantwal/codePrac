package com.spring.app;



import java.text.ParseException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.app.Booking;

import com.spring.app.*;

public class Main {

	public static void main(String[] args) throws ParseException {
		
	
	
	// code here
		ApplicationContext context=new ClassPathXmlApplicationContext("beans.xml");
		Booking b = (Booking)context.getBean("book");
		TransportService obj = (TransportService)context.getBean("trans");
		obj.registerBooking(b);
		
	}	

}
