package other;


import java.util.*;

/**
 * @author leongfeng created on 2017-11-10.
 */
public class Main {
    static int solveMeFirst(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int a = in.nextInt();
            int b = in.nextInt();
            int sum = solveMeFirst(a, b);
            System.out.println(sum);
        }
    }
}
