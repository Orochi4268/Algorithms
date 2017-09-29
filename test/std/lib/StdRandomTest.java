package std.lib;

import org.junit.Test;

/**
 */
public class StdRandomTest {

    @Test
    public void testUniform(){
        System.out.println(StdRandom.uniform(10,11));
    }

    @Test
    public void testOther(){
        StdOut.println('a');
        String s = "";
        for (int n = 4; n > 0; n /= 2){
            s = (n % 2) + s;
        }
        System.out.println(s);

        System.out.println(Math.log(3) / Math.log(2));
    }
}
