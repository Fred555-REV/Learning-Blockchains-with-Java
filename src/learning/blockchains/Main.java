package learning.blockchains;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        // write your code here
        BlockChain javaCoin = new BlockChain(2, 1);
        javaCoin.createTransaction(new Transaction("Fred", "Bob", 1));
        javaCoin.createTransaction(new Transaction("Bob", "Fred", 5));
        System.out.println("STARTING");
        javaCoin.minePendingTransactions("Fred");
        System.out.println("Balance of Fred = "+javaCoin.getBalanceOfAddress("Fred"));
//        javaCoin.createTransaction(new Transaction("Carl", "Fred", 10));

//        System.out.println(chain.getLatestBlock());

    }
}
