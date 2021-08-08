package learning.blockchains;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;

public class Test {
    public static void main(String[] args) {
        Block block = new Block(1, "{amount: 1}");
        System.out.println(block.getHash().substring(0, 5));
        String difficulty = Collections.nCopies(5, "0").stream().reduce((str1, str2) -> str1 + str2).get();
        String str = "0";
        System.out.println(str.repeat(5));
//        System.out.println(Collections.nCopies(5, "0").stream().reduce((str1, str2) -> str1 + str2).get());
    }
}
