/*
ID: paulcru1
LANG: JAVA
TASK: friday
*/
import java.io.*;
import java.util.*;

class friday {
  public static final String[] months = {"JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVERMBER", "DECEMBER"};
  public static final String[] days = {"SATURDAY","SUNDAY","MONDAY","TUESDAY","WEDNESDAY","THURSDAY","FRIDAY"};
  public static final int[] numDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
  public static Map<String,Integer> daysMap;
  public static void main (String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader("friday.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("friday.out")));
    int n = Integer.parseInt(f.readLine());
    makeMap();
    int year = 1900;
    int i = 1;
    String today = "MONDAY";
    String currMonth = "JANUARY";
    HashMap<String, Integer> counts = new HashMap<String,Integer>();
    while(year < 1900+n){
      int numDays = getNumDays(currMonth, year);
      if(i+1 <= numDays){
        i++;
        today = getNextDay(today);
        if(i == 13){
          counts.put(today, counts.getOrDefault(today, 0)+1);
        }
      }
      else if(currMonth.equals("DECEMBER")){
        i = 1;
        today = getNextDay(today);
        year++;
        currMonth = "JANUARY";
      }
      else{
        i = 1;
        today = getNextDay(today);
        currMonth = getNextMonth(currMonth);
      }
    }
    for(String s: days){
      out.printf("%d ", counts.get(s));
    }
    out.println();
    out.close();
    System.exit(0);
  }
  public static String getNextMonth(String month){
    for(int i = 0;i < months.length;i++){
      if(months[i].equals(month)){
        return months[(i+1)%months.length];
      }
    }
    return "JANUARY";
  }
  public static String getNextDay(String day){
    for(int i = 0;i < days.length;i++){
      if(days[i].equals(day)){
        return days[(i+1)%days.length];
      }
    }
    return "MONDAY";
  }
  public static void makeMap(){
    daysMap = new HashMap<String,Integer>();
    for(int i = 0;i < 12;i++){
      daysMap.put(months[i], numDays[i]);
    }
  }
  public static int getNumDays(String month, int year){
    if(month.equals("FEBRUARY") && isLeapYear(year)){
      return 29;
    }
    else{
      return daysMap.get(month);
    }
  }
  public static boolean isLeapYear(int year){
    return (year%400==0) || ((year%4==0)&&(year%100!=0));
  }
}