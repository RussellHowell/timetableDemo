//Russell Howell
//Created on 2/25/15

import java.util.Scanner;

public class Timetable {

	private static Scanner scan;

	public static void main(String[] args) {
		
		
		scan = new Scanner(System.in);
		
		System.out.println("Choose starting location:"+"\n1) South Campus" + "\n2) North Ithan Gate"
							+ "\n3) Falvey Library" + "\n4) Tolintine Hall" + "\n5) Garey Hall/Law School" 
							+ "\n6) West Campus");
		
		try{
		int start_loc= scan.nextInt(); //Start Location Value (1-6)
	
		if (start_loc > 6 || start_loc < 1){
			System.out.println("ERROR: Incorrect Menu Choice, please choose a value between 1 and 6");
			System.exit(0);
		}
		
		System.out.println("Choose destination:"+"\n1) South Campus" + "\n2) North Ithan Gate"
				+ "\n3) Falvey Library" + "\n4) Tolintine Hall" + "\n5) Garey Hall/Law School" 
				+ "\n6) West Campus");
		
		int end_loc = scan.nextInt(); //End Location Value (1-6)
		
		if (end_loc > 6 || end_loc < 1){
			System.err.println("ERROR: Incorrect Menu Choice, please choose a value between 1 and 6");
			System.exit(0);
			}
		
		System.out.println("Enter the time you want to arrive before in the following format: hh:mm");
		
		String time_string = scan.next(); //Stores Time String
		
		System.out.println("1)AM \n2)PM");
		
		int meridiem = scan.nextInt(); //AM/PM Value
		if (meridiem > 2 || meridiem < 1){
			System.err.println("ERROR: Incorrect Menu Choice, please choose a value between 1 and 2");
			System.exit(0);
		}
		//Calls Constructor of Shuttle to find trip information
		Shuttle shuttle = new Shuttle(start_loc, end_loc, time_string, meridiem);

		//Print Result
		System.out.println(shuttle);
		
		}catch(Exception e){ //Error handling
			System.err.println("ERROR: Incorrect Datatype Entered.");
			System.exit(0);
			}
		
	}
	
}

