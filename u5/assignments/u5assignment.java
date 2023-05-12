package u5.assignments;

import java.util.Scanner;
import java.io.*;

public class u5assignment {

    public static int[] addVal(int[] x, int y) {
        int[] output = new int[x.length + 1];
        for (int i = 0; i < x.length; i++) {
            output[i] = x[i];
        }
        output[x.length] = y;

        return output;
    }

    public static int[] addVal(double[] x, double y) {
        int[] output = new int[x.length + 1];
        for (int i = 0; i < x.length; i++) {
            output[i] = x[i];
        }
        output[x.length] = y;

        return output;
    }

    public static void main(String[] args) throws IOException {

        // Main Variables
        Scanner in = new Scanner(System.in);
        int year;

        // Main Body
        System.out.print("Year: ");
        year = in.nextInt();

        Scanner inputFile = new Scanner(new File(year+"_sales_summary.txt"));
        PrintWriter outputFile = new PrintWriter(new FileWriter(year+"_bonus_summary.txt"));

        outputFile.println("Employee ID    Sales Total   Bonus Total");
        outputFile.println("****************************************");

        inputFile.nextLine(); 
        inputFile.nextLine(); // skip first two lines

        while (inputFile.hasNextLine()) {

            // employee id
            // outputFile.printf("%-15d", employee);

            // sales
            // outputFile.printf("%11.2f",sales);
            // salesAcc += sales;

            // bonus
            // outputFile.printf("%14.2f%n",sale*0.05);
            // bonusAcc += sale*0.05;
        }

        in.close();
        inputFile.close();
        outputFile.close();

    }
}
