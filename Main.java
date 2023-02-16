import java.util.*;

public class Main {
    //We want to make a row of bricks that is goal inches long. We have a number of small bricks (1 inch each) 
    //and big bricks (5 inches each). Return true if it is possible to make the goal by choosing from the given bricks.
    public boolean makeBricks(int small, int big, int goal) {
        // 1 inch - small
        // 5 inch - big
        // goal must equal to sum if possible
        int sum = 0;

        for (int i = 0; i<=big; i++) {
            sum = 5*i;
            if (sum == goal) {
                return true;
            }
            else {
                for (int count = 0; count<=small; count++) {
                    sum = sum + count;
                    if (sum == goal) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();

        boolean d = in.nextBoolean();

        in.close();
    }
}
