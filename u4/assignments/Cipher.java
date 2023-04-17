package u4.assignments;
import java.util.Scanner;
import java.io.*;

public class Cipher {
    public static String characterSubstitutions(String s) { // for step 2
        int num;
        String sub = "@___=___!?____*#_&$+_^_%";
        String newStr = "";

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == 'A' || s.charAt(i) == 'E' || s.charAt(i) == 'I' || s.charAt(i) == 'J'
                    || s.charAt(i) == 'O' || s.charAt(i) == 'P' || s.charAt(i) == 'R' || s.charAt(i) == 'S'
                    || s.charAt(i) == 'T' || s.charAt(i) == 'V' || s.charAt(i) == 'X' || s.charAt(i) == ' ') {

                num = s.charAt(i) - 65;
                newStr += sub.charAt(num);

            }

            else if (s.charAt(i) == ' ') {
                newStr += "_";
            }

            else {
                newStr += s.charAt(i);
            }

        }
        return newStr;

    }

    public static String swapFirstAndLast2Chars(String s) { // for step 4
        String firstTwo = s.substring(0, 2);
        String mid = s.substring(2, s.length() - 2);
        String lastTwo = s.substring(s.length() - 2);

        return lastTwo + mid + firstTwo;

    }

    public static String swapMiddle2(String s) { // for step 5
        if (s.length()%2==0) { // even length
            String middleLeft = s.substring(s.length()/2-2, s.length()/2);
            String middleRight = s.substring(s.length()/2, s.length()/2+2);
    
            return s.substring(0,s.length()/2-2) + middleRight + middleLeft + s.substring(s.length()/2+2);
        }
        else {
            String middleLeft = s.substring(s.length()/2-1, s.length()/2+1);
            String middleRight = s.substring(s.length()/2+1, s.length()/2+3);
        
            return s.substring(0,s.length()/2-1) + middleRight + middleLeft + s.substring(s.length()/2+3);
        }
    }

    public static String swapEvery2(String s) { // for step 6
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
                
                // step 1 - to uppercase
                s = (inputFile.nextLine()).toUpperCase(); 

                // step 2 - character substitutions
                s = characterSubstitutions(s);
                
                // step 3 - first half -> last half
                if ((s.length() % 2) == 0)
                    s = s.substring(s.length() / 2) + s.substring(0, s.length() / 2); // even length of letters
                else
                    s = s.substring(s.length() / 2 + 1) + s.substring(0, s.length() / 2 + 1); // odd length of letters
                
                // step 4 - swap 1st 2 chars with last 2
                s = swapFirstAndLast2Chars(s);
                
                // step 5 - swap mid two chars
                s = swapMiddle2(s);

                // step 6 - swap every 2 letters
                s = swapEvery2(s);
                
            }
        }
        
        // Decryption Process
        else { 
            System.out.println("Decryption not yet completed.");
        }

        // Termination of Variables
        in.close();
        inputFile.close();
        outputFile.close();        

    }
}
