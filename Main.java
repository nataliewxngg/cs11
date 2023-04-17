
import java.io.*;

public class Main {
  public static void main(String[] args) throws IOException {
      
    String newStr = "PINEAPPLE";

    if ((newStr.length() % 2) == 0) newStr = newStr.substring(newStr.length() / 2) + newStr.substring(0, newStr.length() / 2); // even length of letters
    else
      newStr = newStr.substring(newStr.length() / 2+1) + newStr.substring(0, newStr.length() / 2 + 1);

    System.out.println(newStr);
  }
}
