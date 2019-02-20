public class Vigenere implements CipherInterface {
    String key;
    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    char[][] table = new char[26][26];
    
    public boolean setKey(String key) {
        // accepts string keys

        for(int i = 0; i < key.length(); i++) {
            if(!Character.isLetter(key.charAt(i))) {
                System.out.print("<KEY> invalid parameter. Enter a keyword.\n");
                return false;
            }
        }

        this.key = key;
        System.out.print("Key: " + this.key + "\n");
        return true;
    }

    public String encrypt(String plainText) {
        String cipherText = "";
        int row;
        int col;
        int keyIndex = 0;

        createTable();
        System.out.print(plainText + "\n");

        for (int i = 0; i < plainText.length(); i++) {
            if (keyIndex == key.length()) {
                keyIndex = 0;
            }

            row = alphabet.indexOf(key.charAt(keyIndex));
            col = alphabet.indexOf(plainText.charAt(i));
            
            cipherText += table[row][col];
            keyIndex++;
        }

        System.out.print(cipherText + "\n");
        return cipherText;
    }

    public String decrypt(String cipherText) {
        String plainText = "";
        int row;
        int keyIndex = 0;

        createTable();
        System.out.print(cipherText + "\n");

        for (int i = 0; i < cipherText.length(); i++) {
            if (keyIndex == key.length()) {
                keyIndex = 0;
            }

            row = alphabet.indexOf(key.charAt(keyIndex));
            for (int j = 0; j < 26; j++) {
                if (table[row][j] == cipherText.charAt(i)) {
                    plainText += alphabet.charAt(j);
                    keyIndex++;
                    break;
                }
            }
        }

        System.out.print(plainText + "\n");
        return plainText;
    }

    public void createTable() {
        Caesar c = new Caesar();
        String caesarEncryption;

        table[0] = alphabet.toCharArray();
        for(int i = 1; i < 26; i++) {
            c.setKey(Integer.toString(i));
            caesarEncryption = c.encrypt(alphabet);
            table[i] = caesarEncryption.toCharArray();
        }

        for(int i = 0; i < 26; i++) {
            for(int j = 0; j < 26; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.print("\n");
        }
    }
}
