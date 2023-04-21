
import java.io.*;

public class Main {
  public static boolean isPalindrome(String s) {
    String newStr = "";
    for (int i = s.length()-1; i >= 0; i--) {
      newStr += s.charAt(i);
    }
    return newStr.equals(s);
  }

  public static String longestPalindrome(String s) {
    // Regular Case
    for (int searchLength = s.length(); searchLength >= 1; searchLength--) {
      for (int index = 0; index <= Math.abs(searchLength - s.length()); index++) {
        if (isPalindrome(s.substring(index, searchLength+index))) {
          return s.substring(index, index + searchLength);
        }
      }
    }
    return s;
  }
  
  public static void main(String[] args) throws IOException {
    // System.out.println(afterCH("YRDSB",'Z'));

    System.out.println(longestPalindrome("abaCDDC"));

  }
}
