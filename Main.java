
import java.io.*;

public class Main {
  public static String swapEvery2(String s) {
    String newStr = "";

    for (int i = 0; i < s.length(); i+=2) {
      if (i == s.length()-1) {
        newStr += s.charAt(i);
      }
      else {
        newStr += s.charAt(i+1);
        newStr += s.charAt(i);
      }
    }
    return newStr;
  }

  public static void main(String[] args) throws IOException {
    System.out.println(swapEvery2("PINEAPPLE"));
  }
}
