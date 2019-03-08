public class Railfence implements CipherInterface {
    int key;
    
    @Override
    public boolean setKey(String key) {
        // accepts numerical keys 1-100

        try {
            if(Integer.parseInt(key) > 0 && Integer.parseInt(key) < 101){
                this.key = Integer.parseInt(key);
                return true;
            }
            else {
                System.out.print("<KEY> invalid parameter. Enter a number between 1-100.\n");
            }
        } catch (NumberFormatException nfe) {
            System.out.print("<KEY> invalid parameter. Enter a number between 1-100.\n");
        }

        return false;
    }

    @Override
    public String encrypt(String plainText) {
	String cipherText = "";
	int row = key;
	int col = plainText.length();
	int plen = plainText.length();
	int step = (key-1)*2; //How many steps from 1st char to 2nd to nth char
	int pos; //current position 
	char table[][];
	table = new char[row][col];
	
	//steps over each letter 
	for(int i = 0; i < row; ++i){
	    for(int j = i; j < col; j+=step){
		pos = j + (step -i*2);
		table[i][j] = plainText.charAt(j);
		if(pos < plen){
		    table[i][pos] = plainText.charAt(pos);
		}
	    }
	}

	//generate cipher text
	for(int i = 0; i < row; i++){
	    for(int j = 0; j < col; j++){
		cipherText += table[i][j];
	    }
	}

	
	System.out.print(plainText + "\n");
        System.out.print(cipherText + "\n");
	return cipherText;
    }

    @Override
    public String decrypt(String cipherText) {
        String plainText = "";
	int clen = cipherText.length();
	int plen = plainText.length();
	int row = key;
	
	char[][] table;
	table = new char[key][clen];

	for(int i = 0; i < key; i++){
	    for(int j = 0; j < clen; j++){
		table[i][j] = 0;
	    }
	}

	int index = 0;
	for(int i = 0; i < key; i++){
	    for(int j = 0; j < clen; j+=key){
		if(index < clen){
		    table[i][j] = cipherText.charAt(index++);
		}
	    }
	}

	for(int j = 0; j < clen; j += key){
	    for(int i = 0; i < key; i++){
		if(table[i][j] != 0){
		    plainText += table[i][j];
		}
	    }
	}
	
        System.out.print(cipherText + "\n");     
	System.out.print(plainText + "\n");
         
       
        return plainText;
    } 
}
