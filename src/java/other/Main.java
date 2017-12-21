package other;


import org.junit.Test;

import java.util.*;

import static edu.princeton.cs.algs4.StdOut.println;

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

    @Test
    public void testArray(){
        char numChar = '0';
        int intNum = (int) numChar;
        println(intNum);
        char ch = (char)30007;
        println(ch);
        char[] chars = {numChar};
        intNum = Integer.parseInt(new String(chars));
        println(intNum);
    }

    @Test
    public void testSet(){
        Set<String> set = new HashSet<>(10);
        set.add(null);
        for (String s : set) {
            println(s);
        }
        Set<String> set2 = new TreeSet<>();

    }
}
