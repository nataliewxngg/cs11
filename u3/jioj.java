package u3;

public class jioj {

    public static int chow(int a, int b) {
        for (;a<=b; a++,b-=2) {
            System.out.println(a+""+b);
        }
        return(b);
    }

    public static double chow(int a, double b) {
        int acc = 0;
        for (int c = a; b>=c; b--) {
            System.out.println(b);
            acc+=a;
        }
        return acc;
    }

    public static double chow(double a, double b) {
        return a+b;
    }

    public static void main(String[] args) {
        System.out.println(chow(5,12));
        System.out.println(chow(chow(5,7.4),2.8));
    }
}
