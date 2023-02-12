import java.util.*;

public class Main {
  // Given 2 positive int values, return the larger value that is in the range 10..20 inclusive, 
  // or return 0 if neither is in that range.
  public static int max1020(int a, int b) {
    int maxVal = 0;

    if (a>=10 && a<=20) {
      if (a>maxVal) {
        maxVal = a;
      }
    }
    if (b>=10 && b<=20) {
      if (b>maxVal) {
        maxVal = b;
      }
    }
    return maxVal;
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    int a = in.nextInt();
    int b = in.nextInt();
    int c = in.nextInt();

    max1020(a, b);
    in.close();
  }
}