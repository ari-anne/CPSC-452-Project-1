import java.io.*;

public class cipher {

    // CIPHER                           KEY
    // [x] PLF = playfair               string
    // [x] RTS = row transposition      array of numbers
    // [ ] RFC = railfence              number
    // [x] VIG = vigenere               string
    // [x] CES = caesar                 number
    // [ ] HIL = hill
    // [ ] ENG = 3 rotor enigma

    public static String cipher_choice;
    public static String key;
    public static String encDec;
    public static String inFile;
    public static String inFileText = "";
    public static String outFile;
    public static String outFileText = "";

    public static void main(String[] args) {
        // check if appropriate number of paramenters
        if (args.length != 5) {
            System.out.print("Invalid number of parameters. To run the program, try 'java cipher <CIPHER> <KEY> <ENC/DEC> <INPUT FILE> <OUTPUT FILE>'\n");
        }

        cipher_choice = args[0].toUpperCase();
        key = args[1].toUpperCase();
        encDec = args[2].toUpperCase();
        inFile = args[3];   // case sensitive
        outFile = args[4];  // case sensitive

        CipherInterface cipher;

        // read input file
        try {
            readFile();
            inFileText = inFileText.toUpperCase();
        } catch(Exception e){
            System.out.print(e.getMessage());
        }

        switch(cipher_choice) {
            case "PLF":
                cipher = new Playfair();
                encoding(cipher);
                break;
            case "RTS":
                cipher = new RowTransposition();
                encoding(cipher);
                break;
            case "RFC":
                cipher = new Railfence();
                encoding(cipher);
                break;
            case "VIG":
                cipher = new Vigenere();
                encoding(cipher);
                break;
            case "CES":
                cipher = new Caesar();
                encoding(cipher);
                break;
            default:
                System.out.print("<CIPHER> invalid parameter.\n");
        }

        // write output file
        try {
            writeFile();
        } catch(Exception e) {
            System.out.print(e.getMessage());
        }
    }

    public static void readFile() throws Exception{
        BufferedReader reader = new BufferedReader(new FileReader(inFile));
        String line;
        
        while((line = reader.readLine()) != null) {
            inFileText += line;
        }

        // System.out.print(inFileText);
        reader.close();
    }

    public static void writeFile() throws Exception{
        BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));
        writer.write(outFileText);
        writer.close();
    }

    public static void encoding(CipherInterface cipher) {
        if (cipher.setKey(key)){
            switch(encDec) {
                case "ENC":
                    outFileText = cipher.encrypt(inFileText);
                    break;
                case "DEC":
                    outFileText = cipher.decrypt(inFileText);
                    break;
                default:
                    System.out.print("<ENC/DEC> invalid parameter.\n");
            }
        }
    }
}
