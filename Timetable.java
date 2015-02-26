//Russell Howell
//Created on 2/25/15

import java.util.Scanner;
import java.util.regex.*;
import javafx.util.converter.TimeStringConverter;

public class Timetable {

	public static void main(String[] args) {
		
		int[] southCampus = {10,30,50,110,130,810,830,850,910,930,950,1010,1030,1050,1110,1130,1150,
				1230,1250,1310,1330,1350,1410,1430,1450,1510,1530,1550,1610,1630,1650,
				1710,1730,1750,1810,1830,1850,1910,1930,1950,2010,2030,2050,
				2110,2130,2150,2210,2230,2250,2310,2330,2350};		
		int[] northIthan = {806,826,835,846,855,906,915,926,935,946,955,1006,1015,1026,
				1035,1046,1055,1106,1115,1126,1135,1146,1155,1235,1235,1246,1255,1306,
				1315,1326,1335,1346,1355,1406,1415,1426,1435,1446,1455,1506,1515,1526,1535,1546,
				1555,1606,1615,1626,1635,1646,1655,1706,1715,1726,1735,1746,1755,1806,1815,1826,1835,1846,
				1855,1906,1915,1926,1935,1946,1955,2006,2015,2026,2035,2046,};
		
	
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Choose starting location:"+"\n1) South Campus" + "\n2) North Ithan Gate"
							+ "\n3) Falvey Library" + "\n4) Tolintine Hall" + "\n5) Garey Hall/Law School" 
							+ "\n6) West Campus");
		
		int start_loc= scan.nextInt(); //Start Location Value
		
		System.out.println("Choose destination:"+"\n1) South Campus" + "\n2) North Ithan Gate"
				+ "\n3) Falvey Library" + "\n4) Tolintine Hall" + "\n5) Garey Hall/Law School" 
				+ "\n6) West Campus");
		
		int end_loc = scan.nextInt(); //End Location Value
		
		System.out.println("Enter the time you want to arrive before in the following format: hh:mm");
		
		String time_string = scan.next(); //Stores Time String
		
		System.out.println("1)AM \n2)PM");
		
		int meridiem = scan.nextInt(); //AM/PM Value
		
		//get returns a four digit integer in 24hr  format.
		int time_int = formatTime(time_string, meridiem);
		
	}
	
	

	private static int formatTime(String time_string, int meridiem) {
		
		//Remove colon from string convert to integer
		int arrive_before = Integer.parseInt(time_string.replaceAll("[\\D]",""));
		//Convert back to string
		time_string = Integer.toString(arrive_before);
		
		
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
				
			}else{} 
		
		return arrive_before;
	}//End of method
	
	
}

