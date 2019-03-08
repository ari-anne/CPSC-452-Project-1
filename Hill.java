public class Hill implements CipherInterface {
    int[] key;

    public boolean setKey(String key) {
        // string key must be separated by spaces
        // accepts array of numbers

		String[] tokens = key.split(" ");
		this.key = new int[tokens.length];

		for (int i = 0; i < tokens.length; i++) {
			this.key[i] = Integer.valueOf(tokens[i]);
		}

		if(this.key.length != 4){
			System.out.print("<KEY> invalid parameter. Enter an array of 4 numbers.\n");
			return false;
		}
	 
        return true;
    }

    @Override
    public String encrypt(String plainText) {
		String cipherText = "";
		String newText = "";
		int letter1 = 0;
		int letter2 = 0;
		int index = 0;
		int x = 0;
		int y = 1;

		
		int[] letters; //array for ascii value of plaintext
		int[][] keyMatrix; //array of keys

		keyMatrix = new int [2][2];

		//insert key to 2x2 matrix
		for(int i = 0; i < 2; i++){
			for(int j = 0; j < 2; j++){
			keyMatrix[i][j] = key[index++];
			}
		}

		//check length of plaintext
		if(plainText.length() % 2 == 1){
			newText = plainText + "A";
		}
		else{
			newText = plainText;
		}
		
		letters = new int[newText.length()];
		
			//insert ascii value of text to array
		for(int i = 0; i < newText.length(); i++){
			letters[i] = (int)(newText.charAt(i)) - 65;
		}

		while(cipherText.length() < newText.length()){
			letter1 = (letters[x] * keyMatrix[0][0] + letters[y] * keyMatrix[0][1]) % 26 + 65;
			letter2 = (letters[x] * keyMatrix[1][0] + letters[y] * keyMatrix[1][1]) % 26 + 65;

			cipherText = cipherText + (char) letter1 + (char) letter2;

			x += 2;
			y += 2;
		}
		
			System.out.print(plainText + "\n");
			System.out.print(cipherText + "\n");
			return cipherText;
    }

    @Override
    public String decrypt(String cipherText) {
        String plainText = "";
		int letter1 = 0;
		int letter2 = 0;
		int x = 0;
		int y = 0;
		int det = 0;
		int fact = 0;
		int a;
		int b;

		int[] letters;
		int[][] inverseMatrix;

		inverseMatrix = new int [2][2];

		a = key[0] * key[3];
		b = key[1] * key[2];
		
		det = a-b;	
		fact = 27 / det;	    

		inverseMatrix[0][0] = key[3];
		inverseMatrix[0][1] = -1*key[1];
		inverseMatrix[1][0] = -1*key[2];
		inverseMatrix[1][1] = key[0];

		//create inverse matrix
		for(int i = 0; i < 2; i++){
			for(int j = 0; j < 2; j++){
			inverseMatrix[i][j] = (fact*(inverseMatrix[i][j] % 26));
			if(inverseMatrix[i][j] < 0){
				inverseMatrix[i][j] += 26;
			}
			}
		}

		letters = new int[cipherText.length()];
		
		//insert ascii value of text to array
		for(int i = 0; i < cipherText.length(); i++){
			letters[i] = (int)(cipherText.charAt(i)) - 65;
		}

		//
		while(plainText.length() < cipherText.length()){
			letter1 = (letters[x] * inverseMatrix[0][0] + letters[y] * inverseMatrix[0][1]) % 26 + 65;
			letter2 = (letters[x] * inverseMatrix[1][0] + letters[y] * inverseMatrix[1][1]) % 26 + 65;

			plainText = plainText + (char) letter1 + (char) letter2;

			x += 2;
			y += 2;
		}

			System.out.print(cipherText + "\n");
			System.out.print(plainText + "\n");
			return plainText;
    }
}
