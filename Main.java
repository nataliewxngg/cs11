import java.util.Scanner;
import java.io.*;

public class Main {
  public static void main(String[] args) throws IOException {
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < i; j++) {
        System.out.println("*");
      }
      System.out.print("*");
    }
  }
}