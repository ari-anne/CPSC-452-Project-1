public interface CipherInterface {
    boolean setKey(String key);
    String encrypt(String plainText);
    String decrypt(String cipherText);
}
