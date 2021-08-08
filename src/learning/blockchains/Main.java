package learning.blockchains;

public class Main {

    public static void main(String[] args) {
        // write your code here
        BlockChain chain = new BlockChain(4);
//        System.out.println(chain.getLatestBlock());
        System.out.println(chain);
        chain.addBlock(new Block(1, "{amount 4}"));
        chain.addBlock(new Block(2, "{amount 10}"));

        System.out.println(chain);

        chain.addBlock(new Block(3, "{amount 15}"));
        System.out.println(chain);
    }
}
