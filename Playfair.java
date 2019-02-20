public class Playfair implements CipherInterface {
    String key = "";
    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    char[][] table = new char[5][5];

    @Override
    public boolean setKey(String key) {
        // accepts string keys

        for(int i = 0; i < key.length(); i++) {
            if(!Character.isLetter(key.charAt(i))) {
                System.out.print("<KEY> invalid parameter. Enter a keyword.\n");
                return false;
            }
        }

        // skip repeating letters
        for(int i = 0; i < key.length(); i++) {
            if(this.key.indexOf(key.charAt(i)) < 0) {
                this.key += key.charAt(i);
            }
        }

        System.out.print("Key: " + this.key + "\n");
        return true;
    }

    @Override
    public String encrypt(String plainText) {
        String cipherText = "";
        String modifiedPlainText;
        int firstPTIndex = 0;    // index of first letter in plaintext
        int secondPTIndex = 1;   // index of second letter in plaintext
        int[] firstTableIndex = new int[2];    // indexes of first letter in table
        int[] secondTableIndex = new int[2];   // indexes of second letter in table

        // create table with given key
        createTable();

        // insert filler 'X' for same-letter pairs and to uneven plaintext lengths
        modifiedPlainText = createPairs(plainText);
        System.out.print(modifiedPlainText + "\n");

        for (int i = 0; i < modifiedPlainText.length()/2; i++) {
            // get indexes of letter pairs
            firstTableIndex = getIndex(modifiedPlainText.charAt(firstPTIndex));
            secondTableIndex = getIndex(modifiedPlainText.charAt(secondPTIndex));

            // same row
            if(firstTableIndex[0] == secondTableIndex[0]) {
                firstTableIndex[1] = plusOne(firstTableIndex[1]);
                secondTableIndex[1] = plusOne(secondTableIndex[1]);

                cipherText += table[firstTableIndex[0]][firstTableIndex[1]];
                cipherText += table[secondTableIndex[0]][secondTableIndex[1]];
            }
            // same col
            else if(firstTableIndex[1] == secondTableIndex[1]) {
                firstTableIndex[0] = plusOne(firstTableIndex[0]);
                secondTableIndex[0] = plusOne(secondTableIndex[0]);

                cipherText += table[firstTableIndex[0]][firstTableIndex[1]];
                cipherText += table[secondTableIndex[0]][secondTableIndex[1]];
            }
            // different row and col
            else {
                cipherText += table[firstTableIndex[0]][secondTableIndex[1]];
                cipherText += table[secondTableIndex[0]][firstTableIndex[1]];
            }

            firstPTIndex += 2;
            secondPTIndex += 2;
        }

        System.out.print(cipherText + "\n");
        return cipherText;
    }

    @Override
    public String decrypt(String cipherText) {
        String plainText = "";
        int firstPTIndex = 0;    // index of first letter in plaintext
        int secondPTIndex = 1;   // index of second letter in plaintext
        int[] firstTableIndex = new int[2];    // indexes of first letter in table
        int[] secondTableIndex = new int[2];   // indexes of second letter in table

        // create table
        createTable();
        System.out.print(cipherText + "\n");

        for (int i = 0; i < cipherText.length()/2; i++) {
            // get indexes of letter pairs
            firstTableIndex = getIndex(cipherText.charAt(firstPTIndex));
            secondTableIndex = getIndex(cipherText.charAt(secondPTIndex));

            // same row
            if(firstTableIndex[0] == secondTableIndex[0]) {
                firstTableIndex[1] = minusOne(firstTableIndex[1]);
                secondTableIndex[1] = minusOne(secondTableIndex[1]);

                plainText += table[firstTableIndex[0]][firstTableIndex[1]];
                plainText += table[secondTableIndex[0]][secondTableIndex[1]];
            }
            // same col
            else if(firstTableIndex[1] == secondTableIndex[1]) {
                firstTableIndex[0] = minusOne(firstTableIndex[0]);
                secondTableIndex[0] = minusOne(secondTableIndex[0]);

                plainText += table[firstTableIndex[0]][firstTableIndex[1]];
                plainText += table[secondTableIndex[0]][secondTableIndex[1]];
            }
            // different row and col
            else {
                plainText += table[firstTableIndex[0]][secondTableIndex[1]];
                plainText += table[secondTableIndex[0]][firstTableIndex[1]];
            }

            firstPTIndex += 2;
            secondPTIndex += 2;
        }
        
        System.out.print(plainText + "\n");
        return plainText;
    }

    public void createTable() {
        String temp = key;
        int ignore; // index of either I or J

        // if letter does not exist in temp, add to temp
        for(int i = 0; i < 26; i++) {
            if (temp.indexOf(alphabet.charAt(i)) < 0) {
                temp += alphabet.charAt(i);
            }
        }

        // ignore either I or J
        if (temp.indexOf('I') < temp.indexOf('J')) {
            ignore = temp.indexOf('J');
        }
        else {
            ignore = temp.indexOf('I');
        }

        // populate the table
        int k = 0;
        for(int i = 0; i < 5; i++) {
            System.out.print("\t");
            for(int j = 0; j < 5; j++) {
                if (k == ignore) {
                    k++;
                }
                if (temp.charAt(k) == 'J') {
                    table[i][j] = 'I';
                }
                else {
                    table[i][j] = temp.charAt(k);
                }
                k++;
                System.out.print(table[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    public String createPairs(String input) {
        int pairOne = 0;    // index of first letter of the pair
        int pairTwo = 1;    // index of second letter of the pair
        String temp = "";

        while (pairOne < input.length()){
            // last letter has no pair
            if (pairTwo >= input.length()) {
                temp += input.charAt(pairOne);
                temp += 'X';
                pairOne += 1;
            }
            // pairs are different letters
            else if (input.charAt(pairOne) != input.charAt(pairTwo)) {
                temp += input.charAt(pairOne);
                temp += input.charAt(pairTwo);
                pairOne += 2;
                pairTwo += 2;
            }
            // pairs are same letters
            else {
                temp += input.charAt(pairOne);
                temp += 'X';
                pairOne += 1;
                pairTwo += 1;
            }
        }

        return temp;
    }

    public int[] getIndex(char letter) {
        int[] index = new int[2];

        if (letter == 'J') {
            letter = 'I';
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (letter == table[i][j]) {
                    index[0] = i;
                    index[1] = j;
                    break;
                }
            }
        }

        return index;
    }

    public int plusOne(int currentIndex) {
        if (currentIndex < 4) {
            return currentIndex += 1;
        }
        else {
            return 0;
        }
    }

    public int minusOne(int currentIndex) {
        if (currentIndex == 0) {
            return 4;
        }
        else {
            return currentIndex -= 1;
        }
    }
}
