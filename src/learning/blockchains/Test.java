package learning.blockchains;

import java.math.BigDecimal;
import java.util.Collections;

public class Test {
    public static void main(String[] args) {
        Block block = new Block();
        System.out.println(block.getHash().substring(0, 5));
        String difficulty = Collections.nCopies(5, "0").stream().reduce((str1, str2) -> str1 + str2).get();
        String str = "0";
        System.out.println(str.repeat(5));
//        System.out.println(Collections.nCopies(5, "0").stream().reduce((str1, str2) -> str1 + str2).get());
    }
}
