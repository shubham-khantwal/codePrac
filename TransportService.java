package com.spring.app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Set;
import java.util.Scanner;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.app.*;


public class TransportService {
	private Vehicle vehicle;
	
	@Autowired
	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public void registerBooking(Booking booking) throws ParseException {
		
		

		// code here
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Customer Name");
		String name = sc.nextLine();
		System.out.println("Enter the Mobile Number");
		Long number = sc.nextLong();
//		sc.next();
		System.out.println("Enter the Destination");
		//sc.next();
		String dest = sc.next();
		//sc.next();
		System.out.println("Enter the Date of Jouney <dd-MM-yyy>");
		String date = sc.next();
		//System.out.println(date);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date dd = sdf.parse(date);
		//Date d =  new SimpleDateFormat.format("dd-MM-yyyy").parse(date);
		ApplicationContext context=new ClassPathXmlApplicationContext("beans.xml");
		Vehicle b = (Vehicle)context.getBean("vehicle"); 
		Booking obj = (Booking)context.getBean("book");
		obj.setCustomerName(name);
		obj.setMobileNumber(number);
		obj.setDestination(dest);
		obj.setDateOfJourney(dd);
		//System.out.println("inside register booking"+name+number+date+dest+dd);
		calculateTravelCost(b.getSource(),dest,dd);
		
		
	}
	public void calculateTravelCost(String source, String destination, Date doj) throws ParseException {

		// code here
		//System.out.println("inside Cal  Travel Cost"+destination);
		if(checkAvailableDateForTravel(doj)) {
			System.out.println("Total Travel Cost is Rs. "+getCost(destination));
		}
		else {
			System.out.println("Invalid Travel Date");
		}
		
	
	}
	
	public boolean checkAvailableDateForTravel(Date doj) throws ParseException {
		
	
//		Calendar dj = Calendar.getInstance();
//		dj.setTime(doj);
//		String fdj = dj.get(Calendar.DATE) + "/" + (dj.get(Calendar.MONTH) + 1) + "/" +dj.get(Calendar.YEAR);
//		System.out.println("formatedDate : " + fdj); 
//		
//		Calendar td = Calendar.getInstance();
//		td.setTime(new Date());
//		String ftd = td.get(Calendar.DATE) + "/" + (td.get(Calendar.MONTH) + 1) + "/" +td.get(Calendar.YEAR);
//		System.out.println("formatedDate : " + ftd); 
//		
//		Calendar nd = Calendar.getInstance();
//		nd.setTime(new Date());
//		nd.add(Calendar.DATE, 1);
//		String fnd = nd.get(Calendar.DATE) + "/" + (nd.get(Calendar.MONTH) + 1) + "/" +nd.get(Calendar.YEAR);
//		System.out.println("formatedDate : " + fnd); 
		// code here
		//System.out.println("inside check avail date");
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		final long MILLIS_IN_A_DAY = 1000 * 60 * 60 * 24;
		String gd =sdf.format(doj);
		String td = sdf.format(d);
		Date dj = sdf.parse(gd);
		Date tj = sdf.parse(td);
		Date p = new Date(d.getTime() + MILLIS_IN_A_DAY);
		String pd = sdf.format(p);
		Date pj = sdf.parse(pd);
		
		if(dj.equals(pj) || dj.equals(tj)) {
			return true;
		}

		return false;
	}
	
	public double getCost(String destination) {
		
		// code here
		
		@SuppressWarnings("resource")
		ApplicationContext context=new ClassPathXmlApplicationContext("beans.xml");
		Vehicle b = (Vehicle)context.getBean("vehicle"); 
		String d = destination;
		//System.out.println("inside cost"+destination+b.getDestinationMap().containsKey(d));
		if(b.getDestinationMap().containsKey(destination)) {
			//System.out.println("inside if");
			double price = b.getDestinationMap().get(destination);
			return price;
		}
		
		
		return 0.0;
	}


}
