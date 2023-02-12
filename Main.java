import java.util.*;

public class Main {
  //Given a number n, return true if n is in the range 1..10, inclusive. 
  //Unless outsideMode is true, in which case return true if the number is less or equal to 1, or greater or equal to 10.
  public static boolean in1To10(int n, boolean outsideMode) {
    if (outsideMode == false) {
      if (n >= 1 && n <= 10) {
        return true;
      }
    }
    if (n <= 1 || n >= 10) {
      return true;
    } else {
      return false;
    }
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    int a = in.nextInt();
    int b = in.nextInt();
    // int c = in.nextInt();

    boolean d = in.nextBoolean();

    in1To10(a,d);
    in.close();
  }
}