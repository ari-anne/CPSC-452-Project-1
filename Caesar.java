public class Caesar implements CipherInterface {
    int key;
    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    @Override
    public boolean setKey(String key) {
        // accepts numerical keys 1-25

        try {
            if(Integer.parseInt(key) > 0 && Integer.parseInt(key) < 26) {
                this.key = Integer.parseInt(key);
                return true;
            }
            else {
                System.out.print("<KEY> invalid parameter. Enter a number between 1-25.\n");
            }
        } catch (NumberFormatException nfe) {
            System.out.print("<KEY> invalid parameter. Enter a number between 1-25.\n");
        }

        return false;
    }

    @Override
    public String encrypt(String plainText) {
        String cipherText = "";
        
        for(int i = 0; i < plainText.length(); i++) {
            int j = alphabet.indexOf(plainText.charAt(i)) + key;
            j = j%26;
            cipherText += alphabet.charAt(j);
        }

        System.out.print(plainText + "\n");
        System.out.print(cipherText + "\n");
        return cipherText;
    }

    @Override
    public String decrypt(String cipherText) {
        String plainText = "";

        for (int i = 0; i < cipherText.length(); i++) {
            int j = alphabet.indexOf(cipherText.charAt(i)) - key;
            j = Math.abs(j)%26;
            plainText += alphabet.charAt(j);
        }

        System.out.print(cipherText + "\n");
        System.out.print(plainText + "\n");
        return plainText;
    }
}
