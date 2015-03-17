//Created by Russell Howell
//Last Modified 3/17/2015
//************************************//
//									  //
//	Helper class holding methods	  //
// for the Timetable.java driver class//
//									  //
//************************************//


import java.text.DecimalFormat;

public class Shuttle {
	
protected static int Departure;
protected static int Arrival;
protected static int Meridiem;
protected static String Arrival_String;
protected static String Departure_String;
protected static String Result_String;

	public Shuttle(int start_loc, int end_loc,String time_string, int meridiem) {
		
		Meridiem = meridiem;
		
		int time_int = formatTime(time_string);
		
		System.out.println("time_int: " + time_int);
		
		shuttleTimes shuttle_info = new shuttleTimes();
		
		findTrip(start_loc, end_loc, time_int, shuttle_info);
		
		formatOutput();
		
	}
	
	public String toString(){

		return Result_String;
		
	}

	private void formatOutput() {
		String arrival_string = Integer.toString(Arrival);
		String departure_string = Integer.toString(Departure);
		
		int arrival_sub, departure_sub; //these values hold the the digits of the time before the colon in for the arrival and departure time
		
		if (Arrival < 1000){ 		//A problem occurs if the number before the colon is only one digit, (example being the '9' in '9:30' 
			arrival_sub = Integer.parseInt(arrival_string.substring(0,1));		//these conditionals take care of that case by changing the index
			arrival_string = arrival_string.substring(1);						//of the substring if necessary
			}else{arrival_sub = Integer.parseInt(arrival_string.substring(0,2));
				 arrival_string = arrival_string.substring(2);
						 } 		
		if (Departure < 1000){															
			departure_sub = Integer.parseInt(departure_string.substring(0,1));	
		departure_string = departure_string.substring(1);
			}else{departure_sub = Integer.parseInt(departure_string.substring(0,2));
				departure_string= departure_string.substring(2);}	
																					
			
		//Convert back to 12 hr time
		String arrival_meridiem, departure_meridiem;
		if(arrival_sub < 12){
			arrival_meridiem = "AM";
		}else if(arrival_sub>12){
			arrival_meridiem = "PM";
			arrival_sub = arrival_sub-12;
			if (arrival_sub>=12 && Meridiem == 1){ //check if the time is after midnight
				arrival_sub = arrival_sub-12;
				arrival_meridiem = "AM";
				if(arrival_sub == 0){arrival_sub=12;}		
			}
		}else
			arrival_meridiem = "PM";
			
		if(departure_sub< 12){
			departure_meridiem = "AM";
		}else if(departure_sub>12){
			departure_meridiem = "PM";
			departure_sub = departure_sub-12;
			if (departure_sub>=12 && Meridiem == 1){ //check if the time is after midnight
				departure_sub = departure_sub-12;
				departure_meridiem = "AM";
				if(departure_sub == 0){departure_sub=12;}
			}
		}else
			departure_meridiem = "PM";
		
	System.out.println(arrival_sub + "   " + departure_sub);
		
		arrival_string =  arrival_sub + ":" + arrival_string + " " + arrival_meridiem;
		departure_string =  departure_sub + ":" + departure_string + " " + departure_meridiem;
		
		Result_String = "Departing from " + Departure_String + " at: " + departure_string + "\nArrival at " + Arrival_String + ": " + arrival_string;
		
	}
	
	private static void findTrip(int start_loc, int end_loc, int time_int, shuttleTimes shuttle_info) {
		
		//This switch case finds the arrival time based on 
		//the "arrive by" and destination parameters set by the user
		switch(end_loc){
			
			case 1:
				Arrival = findInArray(time_int, shuttle_info.southCampus);
				Arrival_String = "South Campus";
				break;
				
			case 2:
				Arrival = findInArray(time_int, shuttle_info.northIthan);
				Arrival_String = "North Ithan Gate";
				break;
		
			case 3:
				Arrival = findInArray(time_int, shuttle_info.falveyLibrary);
				Arrival_String = "Falvey Libray";
				break;
			
			case 4:
				Arrival = findInArray(time_int, shuttle_info.tolentineHall);
				Arrival_String = "Tolentine_Hall";
				break;
				
			case 5:
				Arrival = findInArray(time_int, shuttle_info.lawSchool);
				Arrival_String = "the Law School";
				break;
				 
			case 6:
				Arrival = findInArray(time_int, shuttle_info.westCampus);
				Arrival_String = "West Campus";
				break;		
		}
		
		//This switch case finds the departure time based on 
		//the arrival time and departure location parameter set by the user
		switch(start_loc){
					
			case 1:
				Departure= findInArray(Arrival, shuttle_info.southCampus);
				Departure_String = "South Campus";
				break;
			case 2:
				Departure = findInArray(Arrival, shuttle_info.northIthan);
				Departure_String = "North Ithan Gate";
				break;
			case 3:
				Departure = findInArray(Arrival, shuttle_info.falveyLibrary);
				Departure_String = "Falvey Libray";
				break;
			case 4:
				Departure = findInArray(Arrival, shuttle_info.tolentineHall);
				Departure_String = "Tolentine Hall";
				break;
			case 5:
				Departure = findInArray(Arrival, shuttle_info.lawSchool);
				Departure_String = "the Law School";
				break;
				
			case 6:
				Departure = findInArray(Arrival, shuttle_info.westCampus);
				Departure_String = "West Campus";
				break;
				
				}
	}

	//this method finds the closest lower value in the passed array
	private static int findInArray(int time_int, int[] timeArray) {
		int i = 0;
		int result=0;
		
		
	try{
		outerloop:
		while(i<= timeArray.length + 1){
			
			if(timeArray[i]>time_int){
				result = timeArray[i-1];
				break outerloop;
			}
			else if (timeArray[i]==time_int){
				result = timeArray[i];
				break outerloop;
			}else if(i + 1 == timeArray.length){
				result = timeArray[i];
				break outerloop;
			}//end of if - else
			++i;
		}//end of while loop
	}catch(Exception e){System.out.println("Sorry, no availible trips could be found for the time specified");
						System.exit(2);}
	
		return result;
	}



	private static int formatTime(String time_string) {
		
		//Remove colon from string convert to integer
		int arrive_before = Integer.parseInt(time_string.replaceAll("[\\D]",""));
		
		//Convert back to string and keep formatting (leading 0)
		DecimalFormat IntForm = new DecimalFormat("0000");
		time_string = IntForm.format(arrive_before); 
		
		//Check if the entered time is of the right format
		if(time_string.length()<4||time_string.length()>5){ //length
			System.err.println("ERROR: Make sure you enter the time in the format hh:mm");
			System.exit(0);
		} else if((Integer.parseInt(time_string.substring(2, 4))>=60)||
				Integer.parseInt(time_string.substring(0,2))>12){ 
			System.err.println("ERROR: You entered an invalid time");
			System.exit(0);
		}
		

		if(Meridiem==2){ //if the number is PM, change to 24 hour format
		
			String substring = time_string.substring(0,2);
				
			//change to 24 hr format
			int hour = 12+Integer.parseInt(substring);
			substring = Integer.toString(hour);
			time_string = substring.concat(time_string.substring(2,4));
				
			arrive_before=Integer.parseInt(time_string);
				
			}else if(Meridiem == 1 && arrive_before < 300){

				String substring = time_string.substring(0,2);
				
				int hour = Integer.parseInt(substring);
				
				if(arrive_before >= 1200 && arrive_before <= 1259){
					hour = 12 + hour;
					}
					else{
						hour = 24 + hour;
					}
				
				substring = Integer.toString(hour);
				time_string= substring.concat(time_string.substring(2,4));
				arrive_before = Integer.parseInt(time_string);
				
			}
		
		return arrive_before;
	
	}//End of method
	
	
}
