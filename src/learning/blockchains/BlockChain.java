package learning.blockchains;

import java.util.ArrayList;
import java.util.List;

public class BlockChain {
    private List<Block> chain = new ArrayList<>();

    public BlockChain() {
        this.chain.add(getGenesisBlock());
    }

    public Block getGenesisBlock() {
        Block genesisBlock = new Block(0, "", "0");
        return genesisBlock;
    }

    public Block getLatestBlock() {
        return chain.get(chain.size() - 1);
    }

    public void addBlock(Block newBlock) {
        System.out.println(isChainValid());
        newBlock.setPreviousHash(getLatestBlock().getHash());
        newBlock.setHash(newBlock.calculateHash());
        chain.add(newBlock);
    }

    private boolean isChainValid() {
        boolean output = true;
        if (chain.size() > 1) {
            for (int i = 1; i < chain.size(); i++) {
                Block currentBlock = chain.get(i);
                Block previousBlock = chain.get(i - 1);
                if (!currentBlock.getPreviousHash().equals(previousBlock.getHash()) || !currentBlock.getHash().equals(currentBlock.calculateHash())) {
                    output = false;
                    chain.remove(currentBlock);
                    System.out.println("Chain Invalid, removing tampered block");
                }
            }
            if (!output) {
                for (int i = 1; i < chain.size(); i++) {
                    Block currentBlock = chain.get(i);
                    Block previousBlock = chain.get(i - 1);
                    currentBlock.setPreviousHash(previousBlock.getHash());
                    currentBlock.setHash(currentBlock.calculateHash());
                }
            } else {
                System.out.println("Chain Valid");
            }
        }
        return output;
    }

    @Override
    public String toString() {
        return "chain" + chain;
    }
}
