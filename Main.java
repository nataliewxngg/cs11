import java.util.*;

public class Main { 
  //If the numbers are all different from each other, the result is 0. If all of the numbers are the same, the result is 20. If two of the numbers are the same, the result is 10.
  public static int greenTicket(int a, int b, int c) {
    if (a == b && b == c) {
      return 20;
    }
    if (a == b || b == c || c == a) {
      return 10;
    }
    else {
      return 0;
    }
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    int a = in.nextInt();
    int b = in.nextInt();
    int c = in.nextInt();

    boolean d = in.nextBoolean();

    greenTicket(a,b,c);
    in.close();
  }
}