import java.util.*;

public class Main {
  //0=Sun, 1=Mon, 2=Tue, ...6=Sat, and a boolean indicating if we are on vacation, 
  //return a string of the form "7:00" indicating when the alarm clock should ring. 
  //Weekdays, the alarm should be "7:00" and on the weekend it should be "10:00". 
  //Unless we are on vacation -- then on weekdays it should be "10:00" and weekends it should be "off".
  public static boolean specialEleven(int n) {
    
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    int a = in.nextInt();
    int b = in.nextInt();
    int c = in.nextInt();

    boolean d = in.nextBoolean();

    specialEleven(a);
    in.close();
  }
}