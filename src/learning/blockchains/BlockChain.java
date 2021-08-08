package learning.blockchains;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BlockChain {
    private final List<Block> chain;
    private int difficulty;
    private List<Transaction> pendingTransactions;
    private int miningReward;

    public BlockChain(int difficulty, int miningReward) {
        this.chain = new ArrayList<>();
        this.pendingTransactions = new ArrayList<>();
        this.miningReward = miningReward;
        this.difficulty = difficulty;
        this.chain.add(getGenesisBlock());
    }

    private Block getGenesisBlock() {
        Block genesisBlock = new Block();
        genesisBlock.mineBlock(difficulty);
        return genesisBlock;
    }

    public Block getLatestBlock() {
        return chain.get(chain.size() - 1);
    }

    public void minePendingTransactions(String miningRewardAddress) {
        Block block = new Block(pendingTransactions);
        block.mineBlock(difficulty);
        System.out.println("Block successfully mined!");
        chain.add(block);
        pendingTransactions = List.of(new Transaction(null, miningRewardAddress, this.miningReward));
    }

    public void createTransaction(Transaction transaction) {
        pendingTransactions.add(transaction);
    }

    public int getBalanceOfAddress(String address) {
        int balance = 0;
        for (Block block : chain) {
            for (Transaction trans : block.transactions) {
                if (trans.fromAddress.equals(address)) {
                    balance -= trans.amount;
                }
                if (trans.toAddress.equals(address)) {
                    balance += trans.amount;
                }
            }
        }
        return balance;
    }

    public void addBlock(Block newBlock) {
        System.out.println(isChainValid());
        newBlock.setPreviousHash(getLatestBlock().getHash());
        newBlock.mineBlock(difficulty);
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
//                for (int i = 1; i < chain.size(); i++) {
//                    Block currentBlock = chain.get(i);
//                    Block previousBlock = chain.get(i - 1);
//                    currentBlock.setPreviousHash(previousBlock.getHash());
//                    currentBlock.setHash(currentBlock.calculateHash());
//                }
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
