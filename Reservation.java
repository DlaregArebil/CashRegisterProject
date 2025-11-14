package FinalProject;
import java.util.*;

public class Reservation {


    static boolean isLeapYear (int year){
        return (year % 4 == 0 && (year % 100 !=0 || year % 400 == 0));
    }

    static int getDays(int month, int year){
        int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if(month == 2 && isLeapYear(year)){
            return 29;
        }

        return days[month - 1];
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> ReservationDate = new ArrayList<>();

        // ints
        int year;
        int month;
        int day;
        int monthDays;

        System.out.print("LET'S DO THIS");
        System.out.println("Reservation Date");

        System.out.print("Please select a month (1 = January - 12 = December): ");
        month = sc.nextInt();

        if(month < 1 || month > 12){
            System.out.println("Invalid month selected. Please try again bitch.");
        } 
        try {
            
        } catch (Exception e) {
            // TODO: handle exception
        }

        System.out.print("Select a year (e.g., 2025, 2026): ");
        year = sc.nextInt();

        try {
            
        } catch (Exception e) {
            // TODO: handle exception
        }

        monthDays = getDays(month, year);
        System.out.println(month + " has " + monthDays + " days");
        System.out.print("Select a day (1- " + monthDays + "): ");
        day = sc.nextInt();

        if(day < 1 || day > monthDays){
            System.out.println("Invalid day selected. Please try again bitch");
        } else{
            System.out.println("You've choosen" + month + "/" + day + "/" + year);
        }
        

        System.out.println("Number of guest");

        System.out.println("Facility Type");

        System.out.println("Number of Faclities to Rerve");
    
    
    
    }
}
