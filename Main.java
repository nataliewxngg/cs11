import java.util.Scanner;
import java.io.*;

public class Main {
  public static void main(String[] args) throws IOException {
      
    // Global Variables
    Scanner in = new Scanner(System.in);
    PrintWriter outputFile = new PrintWriter(new FileWriter("person.txt"));
    String name;
    int age;
    double time;

    // Main Code
    System.out.print("What is your name? ");
    name = in.nextLine();

    System.out.print("How old are you? ");
    age = in.nextInt();

    System.out.print("What's the time right now? ");
    time = in.nextDouble();

    outputFile.print("Name: " + name + "\nAge: " + age + "\nTime: " + time);

    // Closing Global Variables
    in.close();
    outputFile.close();

  }
}
