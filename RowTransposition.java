public class RowTransposition implements CipherInterface {
    int[] key;

    public boolean setKey(String key) {
        // string key must be separated by spaces
        // accepts array of numbers

        String[] tokens = key.split(" ");
        this.key = new int[tokens.length];
        boolean hasNum;

        for (int i = 0; i < tokens.length; i++) {
            this.key[i] = Integer.valueOf(tokens[i]);
        }

        // check if the array contains the numbers 1 to the total number of columns
        for (int i = 1; i <= this.key.length; i++) {
            hasNum = false;
            for (int j = 0; j < this.key.length; j++) {
                if (this.key[j] == i) {
                    hasNum = true;
                }
            }

            if (hasNum == false) {
                System.out.print("<KEY> invalid parameter. Enter an array of numbers.\n");
                return false;
            }
        }

        return true;
    }

    public String encrypt(String plainText) {
        String cipherText = "";
        int row = plainText.length() / key.length;
        int col = key.length;
        int remain = plainText.length() % key.length;
        char[][] table;
        int k = 0;  // plaintext index

        if (remain > 0) {
            row++;
        }

        table = new char[row][col];

        // append X to empty spots
        for (int i = 0; i < remain; i++) {
            plainText += "X";
        }

        System.out.print(plainText + "\n");

        // create table
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                table[i][j] = plainText.charAt(k);
                k++;
                System.out.print(table[i][j] + " ");
            }
            System.out.print("\n");
        }

        // create cipher
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                cipherText += table[j][key[i] - 1];
            }
        }

        System.out.print(cipherText +"\n");
        return cipherText;
    }

    public String decrypt(String cipherText) {
        String plainText = "";
        int row = cipherText.length() / key.length;
        int col = key.length;
        char[][] table = new char[row][col];
        char[][] arranged = new char[row][col];
        int k = 0;

        System.out.print(cipherText +"\n");

        // create table
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                table[j][i] = cipherText.charAt(k);
                k++;
            }
        }

        // print table
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.print("\n");
        }

        System.out.print("\n");

        // rearrange according to key
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                arranged[i][key[j] - 1] += table[i][j];
            }
        }

        // print rearranged
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(arranged[i][j] + " ");
            }
            System.out.print("\n");
        }


        // create plaintext
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                plainText += arranged[i][j];
            }
        }

        System.out.print(plainText + "\n");      
        return plainText;
    }

}
