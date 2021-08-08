package learning.blockchains;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Block {
    //    private final int index;
    private final Date timeStamp;
    //    private final String data;
    public List<Transaction> transactions;
    private String previousHash;
    private String hash;
    private int nonce;

    public Block() {
        timeStamp = Date.from(Instant.now());
        this.transactions = new ArrayList<>();
        hash = calculateHash();
        nonce = 0;
    }

    public Block(List<Transaction> pendingTransactions) {
        timeStamp = Date.from(Instant.now());
        this.transactions = new ArrayList<>();
        hash = calculateHash();
        nonce = 0;
        this.transactions = pendingTransactions;
    }

    public Block(String previousHash) {
        timeStamp = Date.from(Instant.now());
        this.transactions = new ArrayList<>();
        this.previousHash = previousHash;
        hash = calculateHash();
        nonce = 0;
    }

    public String calculateHash() {
        try {
            return SHA3_256((previousHash + timeStamp + transactions + nonce));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void mineBlock(int difficulty) {
        while (!hash.substring(0, difficulty).equals("0".repeat(difficulty))) {
            this.nonce++;
            this.hash = calculateHash();
        }
        System.out.println("Block mined: " + hash);
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public String getHash() {
        return hash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

//    public void setHash(String hash) {
//        this.hash = hash;
//    }

    private String SHA3_256(final String originalString) throws NoSuchAlgorithmException {
        final MessageDigest digest = MessageDigest.getInstance("SHA3-256");
        final byte[] hashbytes = digest.digest(
                originalString.getBytes(StandardCharsets.UTF_8));
        String sha3Hex = bytesToHex(hashbytes);
        return sha3Hex;
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte aByte : bytes) {
            result.append(String.format("%02x", aByte));
            // upper case
            // result.append(String.format("%02X", aByte));
        }
        return result.toString();
    }
//    private static String bytesToHex(byte[] hash) {
//        StringBuilder hexString = new StringBuilder(2 * hash.length);
//        for (int i = 0; i < hash.length; i++) {
//            String hex = Integer.toHexString(0xff & hash[i]);
//            if(hex.length() == 1) {
//                hexString.append('0');
//            }
//            hexString.append(hex);
//        }
//        return hexString.toString();
//    }
//    public static String hashWithJavaMessageDigestJDK9(final String originalString) throws NoSuchAlgorithmException {
//        final MessageDigest digest = MessageDigest.getInstance(SHA3_256);
//        final byte[] hashbytes = digest.digest(originalString.getBytes(StandardCharsets.UTF_8));
//        return bytesToHex(hashbytes);
//    }

    @Override
    public String toString() {
        return "\n\tBlock{" +
                "\n\t\ttimeStamp=" + timeStamp +
                ",\n\t\ttransactions='" + transactions + '\'' +
                ",\n\t\tpreviousHash='" + previousHash + '\'' +
                ",\n\t\thash='" + hash + '\'' +
                "\n\t}\n";
    }
}
