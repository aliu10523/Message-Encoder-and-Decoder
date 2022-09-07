package module6problem2;

//add all methods and classes from test encoder
//modify substitution cipher so it implements message encoder and decoder
//create abstract method decode(cipherText) that returns decodedMsg after calling shiftLeft 
//create shiftLeft method similar to shiftSingle except subtracting int shift to char and returning value

//create abstract method decode(cipherText) that returns decodedMsg after calling reverseSingle based off int shuffle
//create reverseSingle that reverses the output of shuffleSingle method back to the original
 //inverse the values of the equation in the for loop so it the characters return to original reference
//return string value of target character array

import java.util.Scanner;

public class TestDecoder {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		//Encoding with substitution cipher
		System.out.println("Enter message to be encoded using Substitution Cipher: ");
		SubstitutionCipher shiftMessage = new SubstitutionCipher(2);
		String origMessage = in.next();
		String encodedMessage = shiftMessage.encode(origMessage);
		System.out.println("Encoded Message using Substitution Cipher: " + encodedMessage);
		System.out.println();
		
		//Encoding with shuffle cipher
		ShuffleCipher shuffleMessage = new ShuffleCipher(1);
		System.out.println("Enter message to be encoded using Shuffle Cipher: ");
		String origMsg = in.next();
		String encodedMsg = shuffleMessage.encode(origMsg);
		System.out.println("Encoded Message using Shuffle Cipher: " + encodedMsg);
		System.out.println();
		
		//Decoding with subtitution cipher
		System.out.println("Enter encoded message to be decoded with Substitution cipher: ");
		SubstitutionCipherExt reverseMessage = new SubstitutionCipherExt(2);
		String codedMsg = in.next();
		String originalMessage = reverseMessage.decode(codedMsg);
		System.out.println("Decoded Message with Subtitution Cipher: " + originalMessage);
		System.out.println();
		//Decoding with shuffle cipher
		System.out.println("Enter encoded message to be decoded with Shuffle cipher: ");
		ShuffleCipherExt reverseMsg = new ShuffleCipherExt(1);
		String codedMessage = in.next();
		String originalMsg = reverseMsg.decode(codedMessage);
		System.out.println("Decoded Message with Shuffle Cipher: " + originalMsg);
		
	    }
	
	
}



class SubstitutionCipherExt implements MessageDecoder, MessageEncoder {
	private int shift;
	
	public SubstitutionCipherExt(int shift) {
		this.shift = shift;
	}
	
	public String encode(String plainText) {
		char[] message = plainText.toCharArray();
		String encodedMsg;
		for (int i = 0; i < message.length; i ++) {
			message[i] = shiftSingle(message[i]);
		}
		encodedMsg = String.valueOf(message);
		return encodedMsg;
	}

	public String decode(String cipherText) {
		char[] message = cipherText.toCharArray();
		String decodedMsg;
		for (int i = 0; i < message.length; i ++) {
			message[i] = shiftLeft(message[i]);
		}
		decodedMsg = String.valueOf(message);
		return decodedMsg;
	}
	
	public char shiftLeft(char letter) {
		return (char)(letter - this.shift);
	}
	
	public char shiftSingle(char letter) {
		return (char)(letter + this.shift);
	}
	
}

class ShuffleCipherExt implements MessageDecoder, MessageEncoder {
	private int shuffle;
	
	public ShuffleCipherExt(int shuffle) {
		this.shuffle = shuffle;
	}
	
	public String encode(String plainText) {
		String encodedMsg = plainText;
		for (int i = 0; i < shuffle; i++) {
			encodedMsg = shuffleSingle(encodedMsg);
		}
		return encodedMsg;
	}

	
	public String decode(String cipherText) {
		String decodedMsg = cipherText;
		for (int i = 0; i < shuffle; i++) {
			decodedMsg = reverseSingle(decodedMsg);
		}
		return decodedMsg;
	}
	
	public String shuffleSingle(String plainText) {
		char[] origS = plainText.toCharArray();
		char[] targetS = new char[origS.length];
		int mid = origS.length / 2 + origS.length % 2;
		
		for (int i = 0; i < mid; i ++) {
			int updateIndex1 = 2 * i;
			int updateIndex2 = 2 * i +1;
			targetS[updateIndex1] = origS[i];
			if ((mid + i) < origS.length) {
				targetS[updateIndex2] = origS[mid + i];
			}
		}
		return String.valueOf(targetS);
	}
	
	public String reverseSingle(String cipherText) {
		char[] origS = cipherText.toCharArray();
		char[] targetS = new char[origS.length];
		int mid = origS.length / 2 + origS.length % 2;
		
		for (int i = 0; i < mid; i ++) {
			int updateIndex1 = 2 * i;
			int updateIndex2 = 2 * i +1;
			targetS[i] = origS[updateIndex1];
			if ((mid + i) < origS.length) {
				targetS[mid + i] = origS[updateIndex2];
			}
		}
		return String.valueOf(targetS);
	}
	
}

