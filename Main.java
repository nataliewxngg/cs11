import java.util.Scanner;
import java.io.*;

public class Main {
  public static void main(String[] args) throws IOException {
    String s = "Wong Natalie";
    System.out.println(s.substring(s.indexOf(" ") + 1) + " " + s.substring(0, s.indexOf(" ")));

  }
}