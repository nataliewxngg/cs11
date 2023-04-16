package u4.assignments;
import java.util.Scanner;
import java.io.*;

public class Cipher {
    public static String characterSubstitutions(String s) {
        
    }

    public static void Main(String[] args) throws IOException {

        // Global Variables (some initiated by user input)
        Scanner in = new Scanner(System.in);

        System.out.print("Are you encrypting (0) or decrypting (1) an existing file? ");
        int encryptOrDecrypt = in.nextInt();
        while (encryptOrDecrypt != 0 || encryptOrDecrypt != 1) {
            System.out.print("Invalid input. Are you encrypting (0) or decrypting (1) and existing file? ");
            encryptOrDecrypt = in.nextInt();
        }

        System.out.print("What is the name of the input text file? ");
        String inputTXTName = in.nextLine();

        System.out.print("What is the intended name for your output text file? ");
        String outputTXTName = in.nextLine();

        Scanner inputFile = new Scanner(new File(inputTXTName));
        PrintWriter outputFile = new PrintWriter(new FileWriter(outputTXTName));

        String s;

        // Main Body Code

        // Encryption Process
        if (encryptOrDecrypt == 0) { 
            while (inputFile.hasNextLine()) {

                s = (inputFile.nextLine()).toUpperCase(); // step 1 - to uppercase

                // step 2 - character substitutions
                
                
            }
        }
        
        // Decryption Process
        else { 

        }


        // Termination of Variables
        in.close();
        

    }
}
