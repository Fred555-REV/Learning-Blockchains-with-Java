package learning.blockchains;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;

public class Block {
    private final int index;
    private final LocalDate timeStamp;
    private final String data;
    private String previousHash;
    private String hash;

    public Block(int index, String data) {
        this.index = index;
        timeStamp = LocalDate.now();
        this.data = data;
        hash = calculateHash();
    }

    public Block(int index, String data,
                 String previousHash) {
        this.index = index;
        timeStamp = LocalDate.now();
        this.data = data;
        this.previousHash = previousHash;
        hash = calculateHash();
    }

    private String calculateHash() {
        try {
            return SHA3_256((index + previousHash + timeStamp + data));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

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

}
