package u2.assignments.formatives;

public class formative4 {
    public static void main(String[] args) {
        
        // question 1
        int a = 5;
        int b = 1;
        int c = 6;

        while (b <= 7) {
            System.out.printf("%5d", a);
            System.out.printf("%5d%n", b);
            if (a >= b && a > c)
                System.out.printf("%5s", "ONE");
            else if (a <= b || b > c)
                System.out.printf("%5s", "TWO");
            else if (a + 2 >= c)
                System.out.printf("%10s", "THREE");
            a--;
            b += 3;
        }

        // question 2
        int sum = 0;
        for (int num = 10; num <= 20; num += 3)
            sum += num * num;
        System.out.println(sum);

        // question 3
        for (int i = 1; i <= 4; i++) {
            for (int j = 4; j >= i; j--) {
                System.out.print("*");
            }
            System.out.println();
        }

        // programs for a), b), c), d), e)
        

    }
}
