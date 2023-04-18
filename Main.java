
import java.io.*;

public class Main {
  public static String step4(String s) {
    if ((s.length() % 2) == 0)
        s = s.substring(s.length() / 2) + s.substring(0, s.length() / 2); // even length of letters
    else
        s = s.substring(s.length() / 2) + s.substring(0, s.length() / 2); // odd length of letters

    return s;
}

  public static void main(String[] args) throws IOException {
    System.out.println(step4("PPLEPINEA"));
  }
}
