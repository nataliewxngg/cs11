package u1.assignments;

import java.util.Scanner;

public class receiptGenerator {
    public static String displayHeader(String header) { // displays the ><><>...<><><
        String headerFinal = "";
        for (int i = 0; i < 25; i++) {
            headerFinal = headerFinal + header;
        }
        System.out.println(headerFinal);
        return headerFinal;
    }

    public static void main(String[] args) {

        // variables
        String header = "><"; // display repeatedly 25x
        
        int numOfApples = 0;
        int numOfOranges = 0;
        double numOfLychees = 0;
        double numOfBlueberries = 0;

        Scanner in = new Scanner(System.in);

        // main program/code

        // displays the header + title of receipt
        displayHeader(header);
        System.out.printf("%36s%n", "Chow Chow Fruit Centre");
        displayHeader(header);

        System.out.println();

        // the initial prompt/inputs
        System.out.printf("%-35s", "Number of apples purchased:");
        numOfApples = in.nextInt();

        System.out.printf("%-35s", "Number of oranges purchased:");
        numOfOranges = in.nextInt();

        System.out.printf("%-35s", "Amount of lychees purchased:");
        numOfLychees = in.nextDouble();

        System.out.printf("%-35s", "Amount of blueberries purchased:");
        numOfBlueberries = in.nextDouble();

        System.out.println();

        // receipt body (description, quantity, price)
        for (int i = 0; i < 44; i++) {
            if (i == 22) {
                System.out.print("Receipt");
            } else {
                System.out.print("-");
            }
        }
        
        // price description quantity
        System.out.printf("\n\n%-32s%-13sPrice%n", "Description", "Quantity");

        System.out.printf("%-33s%7d%10.2f%n", "Apples @ $0.83/each", numOfApples, 0.83*numOfApples);
        System.out.printf("%-33s%7d%10.2f%n", "Oranges @ $0.75/each", numOfOranges, 0.75*numOfOranges);
        System.out.printf("%-33s%7d%10.2f%n", "Lychees @ $2.49/lbs", numOfLychees, 2.49*numOfLychees);

        in.close();
    }
}