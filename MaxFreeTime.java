/*****
	author: Maria Pouliou
	Date created: 27th November 2017
******/

public class MaxFreeTime {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String S = "Mon 01:00-23:00\n"+
				"Tue 01:00-23:00\n"+
				"Wed 01:00-23:00\n"+
				"Thu 01:00-23:00\n"+
				"Fri 01:00-23:00\n"+
				"Sat 01:00-23:00";
		
		
		String[] S_slot = S.split("\n"); //split every row when meet \n
		
		String[][] schedule = new String[1440][7];  /* set up an array called schedule
		*that has 60*24 = 1440 rows (since a day has 1140 minutes)
		*and 7 columns (each per day)
		*Every minute that James is in a meeting will be registered in the schedule array
		*having as a value: "meeting"
		*/

		//Variables
		String time_range_day1;
		String day1 = "0";
		int result = 0;
		int day = 0;
		int count_no_meeting = 0;
		
		for(int i = 0; i < S_slot.length; i++){
			 //split every row when meet " " so as to extract the name of the day and save it in day1 variable
			time_range_day1 = S_slot[i].split(" ")[1];			
			day1 = S_slot[i].split(" ")[0];
			
			//switch-case statement to transform the day name into an integer value
			switch(day1){
				case "Mon":
					day = 0;
					break;
				case "Tue":
					day = 1;
					break;
				case "Wed":
					day = 2;
					break;
				case "Thu":
					day = 3;
					break;
				case "Fri":
					day = 4;
					break;
				case "Sat":
					day = 5;
					break;
				case "Sun":
					day = 6;
					break;
			}
		
			//extracting the time range and split it into start_time and end_time
			String[] day_1_range_split = time_range_day1.substring(0, 11).split("-");
			String[] day_1_start_time = day_1_range_split[0].split(":");
			String[] day_1_end_time = day_1_range_split[1].split(":");
		
			/*transform string hours and minutes extracted into integer values so as to 
			make calculations*/
			Integer day_1_start_hours = Integer.parseInt(day_1_start_time[0]);
			Integer day_1_start_minutes = Integer.parseInt(day_1_start_time[1]); 
			Integer day_1_end_hours = Integer.parseInt(day_1_end_time[0]);
			Integer day_1_end_minutes = Integer.parseInt(day_1_end_time[1]); 
		

			//calculating hour and minute difference of the end & start time of the meeting
			int hour_difference = day_1_end_hours - day_1_start_hours;
			int minute_difference = day_1_end_minutes - day_1_start_minutes;
					   
			//check if the difference values are negative numbers	
			if (hour_difference < 0) {
				hour_difference += 24;
			}
			if (minute_difference < 0) {
				minute_difference = 60 + minute_difference;
			}
				
			//calculating the duration and the start hour in minutes
			int range = hour_difference * 60 + minute_difference;
			int start_hour_in_minutes = day_1_start_hours * 60 + day_1_start_minutes;	
			
			//for every minute that James has a meeting, the array is updated setting a value: "meeting" 
			for(int y = 0; y < range; y++){
				schedule[start_hour_in_minutes+y][day] = "meeting";
			}
		}	
		
		
		//scanning the array across so as to find the max length James does not have a meeting
		for(int y = 0; y < schedule[0].length; y++){
			for(int i =0; i< schedule.length; i++){
				if(schedule[i][y] == null){
					count_no_meeting += 1;
				}else if(schedule[i][y] == "meeting"){
					count_no_meeting = 0;
				}
				if(count_no_meeting > result){
					result = count_no_meeting;
				}
			}
		}
		System.out.println("Result:" +result);
		
	}
}
