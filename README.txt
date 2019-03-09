Arianne Arcebal: hharin@csu.fullerton.edu
Vanessa Delfin:	 vmdelfin@csu.fullerton.edu

Language Used
	Java

Execution
	$ make
	$ java cipher <CIPHER> <KEY> <ENC/DEC> <INPUT FILE> <OUTPUT FILE>
		<CIPHER>: accepts parameters:
			PLF: Playfair
			RTS: Row Transposition
			RFC: Railfence
			VIG: Vigenere
			CES: Caesar
			HIL: Hill
		<KEY>: accepts parameters:
			case PLF: keyword
			case RTS: array of numbers, from 1 to the total number of columns (separated by spaces, e.g. "2 3 1")
			case RFC: number
			case VIG: keyword
			case CES: number
			case HIL: array of 4 numbers (separated by spaces, e.g. "3 1 2 4")
		<ENC>: accepts parameters
			ENC: encrypt
			DEC: decrypt
		<INPUT FILE>: file to read input
		<OUTPUT FILE>: file to write output

Notes:
* The hill cipher extra credit was implemented
* The hill cipher is the 2x2 matrix version
* Encryption and decryption does not remove punctuation and spaces
