// import java.util.*;

public class Main {
    public static boolean sameStarChar(String str) {
  
        while (str.indexOf("*") != -1) {
          
          // NOT first/last letter
          if (str.indexOf("*") != 0 && str.indexOf("*") != str.length()-1) { 
            if (str.charAt(str.indexOf("*")-1) != str.charAt(str.indexOf("*")+1)) {
              return false;
            }
          }
          str = str.substring(0, str.indexOf("*")) + "o" + str.substring(str.indexOf("*")+1);
        }
        return true;
      }
      

    public static void main(String[] args) {
        System.out.println(sameStarChar("***"));
    }
}
