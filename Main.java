import java.util.*;

public class Main {
  //We want to make a row of bricks that is goal inches long. 
  //We have a number of small bricks (1 inch each) and big bricks (5 inches each). 
  //Return true if it is possible to make the goal by choosing from the given bricks. 
  //This is a little harder than it looks and can be done without any loops. 
  public static boolean makeBricks(int small, int big, int goal) {
    // int sum = 0;
    // // small = 1 inch, big - 5 inches
    // for (int i = 0; i < big; i++) {
    //   sum = sum + 5;
    //   for (int y = 0; y < small; y++) {
    //     sum++;

    //     if (sum == goal) {
    //       break;
    //     }
    //   }
    // }
    
    // if (sum == goal) {
    //   return true;
    // }
    // else {
    //   return false;
    // }

  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    int a = in.nextInt();
    int b = in.nextInt();
    int c = in.nextInt();

    boolean d = in.nextBoolean();
    boolean e = in.nextBoolean();
    boolean f = in.nextBoolean();

    makeBricks(a,b,d);
    in.close();
  }
}