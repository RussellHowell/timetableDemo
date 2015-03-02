//Russell Howell
//Created on 2/25/15

import java.util.Scanner;
import java.util.regex.*;
import javafx.util.converter.TimeStringConverter;
import java.text.DecimalFormat;



public class Timetable {

	public static void main(String[] args) {
		
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Choose starting location:"+"\n1) South Campus" + "\n2) North Ithan Gate"
							+ "\n3) Falvey Library" + "\n4) Tolintine Hall" + "\n5) Garey Hall/Law School" 
							+ "\n6) West Campus");
		
		int start_loc= scan.nextInt(); //Start Location Value (1-6)
		
		System.out.println("Choose destination:"+"\n1) South Campus" + "\n2) North Ithan Gate"
				+ "\n3) Falvey Library" + "\n4) Tolintine Hall" + "\n5) Garey Hall/Law School" 
				+ "\n6) West Campus");
		
		int end_loc = scan.nextInt(); //End Location Value (1-6)
		
		System.out.println("Enter the time you want to arrive before in the following format: hh:mm");
		
		String time_string = scan.next(); //Stores Time String
		
		System.out.println("1)AM \n2)PM");
		
		int meridiem = scan.nextInt(); //AM/PM Value
		
		//get returns a four digit integer in 24hr  format.
		int time_int = formatTime(time_string, meridiem);
		shuttleTimes shuttle_info = new shuttleTimes();
		
		int tripArray[] = {0,0}; //Create array to hold dep. (index 0) and arrival (index 1)
								// times to be passed by reference
								//(**Java Hack, fix later)
		
		findTrip(start_loc,end_loc,time_int, shuttle_info, tripArray);
		
		formatOutput(tripArray);
		System.out.println(tripArray[0]);
	}
	
	

	private static void formatOutput(int[] tripArray) {
		
		

	}



	private static void findTrip(int start_loc, int end_loc, int time_int, shuttleTimes shuttle_info, int tripArray[]) {
		
		//This switch case finds the arrival time based on 
		//the "arrive by" and destination parameters set by the user
		switch(end_loc){
			
			case 1:
				tripArray[1] = findInArray(time_int, shuttle_info.southCampus);
				break;
				
			case 2:
				tripArray[1] = findInArray(time_int, shuttle_info.northIthan);
				break;
		
			case 3:
				tripArray[1] = findInArray(time_int, shuttle_info.falveyLibrary);
				break;
			
			case 4:
				tripArray[1] = findInArray(time_int, shuttle_info.tolentineHall);
				break;
				
			case 5:
				tripArray[1] = findInArray(time_int, shuttle_info.lawSchool);
				break;
				
			case 6:
				tripArray[1] = findInArray(time_int, shuttle_info.westCampus);
				break;		
		}
		
		//This switch case finds the departure time based on 
		//the arrival time and departure location parameter set by the user
		switch(end_loc){
					
			case 1:
				tripArray[0] = findInArray(tripArray[1], shuttle_info.southCampus);
				break;
			case 2:
				tripArray[0] = findInArray(tripArray[1], shuttle_info.northIthan);
				break;
			case 3:
				tripArray[0] = findInArray(tripArray[1], shuttle_info.falveyLibrary);
				break;
			case 4:
				tripArray[0] = findInArray(tripArray[1], shuttle_info.tolentineHall);
				break;
			case 5:
				tripArray[0] = findInArray(tripArray[1], shuttle_info.lawSchool);
				break;
			case 6:
				tripArray[0] = findInArray(tripArray[1], shuttle_info.westCampus);
				break;
				
				}
	}


	//this method find the closest lower value in the passed array
	private static int findInArray(int time_int, int[] timeArray) {
		int i = 0;
		int result=0;
		
		//Increment index
		outerloop:
		while(i<= timeArray.length){
			
			if(timeArray[i]>time_int){
				result = timeArray[i-1];
				break outerloop;
			}
			else if (timeArray[i]==time_int){
				result = timeArray[i];
				break outerloop;
			}//end of if - else
				
			++i;
		}//end of while loop
		
		return result;
	}



	private static int formatTime(String time_string, int meridiem) {
		
		//Remove colon from string convert to integer
		int arrive_before = Integer.parseInt(time_string.replaceAll("[\\D]",""));
		
		//Convert back to string and keep formatting (leading 0)
		DecimalFormat IntForm = new DecimalFormat("0000");
		time_string = IntForm.format(arrive_before); 
	
		//Check if the entered time is of the right format
		if(time_string.length()<4||time_string.length()>5){ //length
			System.out.println("ERROR: Make sure you enter the time in the format hh:mm");
			System.exit(0);
		} else if((Integer.parseInt(time_string.substring(2, 4))>=60)||
				Integer.parseInt(time_string.substring(0,2))>12){ 
			System.out.println("ERROR: You entered an invalid time");
			System.exit(0);
		}
		

		if(meridiem==2){ //if the number is PM, change to 24 hour format, otherwise do nothing
		
			String substring = time_string.substring(0,2);
				
			//change to 24 hr format
			int hour = 12+Integer.parseInt(substring);
			substring = Integer.toString(hour);
			time_string = substring.concat(time_string.substring(2,4));
				
			arrive_before=Integer.parseInt(time_string);
				
			} 
		
		return arrive_before;
	
	}//End of method
	
}

