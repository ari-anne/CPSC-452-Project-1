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
		<KEY>: accepts parameters:
			case PLF: keyword
			case RTS: array of numbers (separated by spaces, e.g. "3 2 1")
			case RFC: number
			case VIG: keyword
			case CES: number
		<ENC>: accepts parameters
			ENC: encrypt
			DEC: decrypt
		<INPUT FILE>: file to read input
		<OUTPUT FILE>: file to write output
