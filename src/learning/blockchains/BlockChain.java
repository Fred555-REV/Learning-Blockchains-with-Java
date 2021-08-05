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

}
