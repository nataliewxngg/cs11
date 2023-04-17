
import java.io.*;

public class Main {
  public static String charSubDecrypt(String s) {
    int num;
    String sub = "aj_e_________________to___rxspvi";
    String newStr = "";

    for (int i = 0; i < s.length(); i++) {

      if (s.charAt(i) == '@' || s.charAt(i) == '=' || s.charAt(i) == '!' || s.charAt(i) == '?'
          || s.charAt(i) == '*' || s.charAt(i) == '#' || s.charAt(i) == '&' || s.charAt(i) == '$'
          || s.charAt(i) == '+' || s.charAt(i) == '^' || s.charAt(i) == '%') {
            num =
      } 
      else if (s.charAt(i) == '_') {
        newStr += " ";
      }

      else {
        newStr += s.charAt(i);
      }

    }
    return newStr;
}

  public static void main(String[] args) throws IOException {
    System.out.println(charSubDecrypt("PINEAPPLE"));
  }
}
