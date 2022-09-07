package module6problem2;

//create class substitution cipher implement MessageEncoder with private int shift and constructor
//create encode method that returns encoded message after calling shiftSingle as many times based on the message length
//create shiftSingle method that has char as a parameter and add int shift to char while casting it to char, return char
//create shuffle cipher class that implements messageEncoder with private int shuffle and constructor with shuffle as args
//create encode method that returns encoded message with String as parameter

//create shuffleSingle method with String plaintext as parameter
//convert plainText to charArray and create another charArray named target with length of plainText
//divide charArray by 2 to find int mid 
//create for loop that updates the targetMsg array in pairs of (i, mid + i )
//return String form of targetMsg char array

import java.util.Scanner;

public class TestEncoder {
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
    }

}


class SubstitutionCipher implements MessageEncoder {
	
	private int shift;
	
	public SubstitutionCipher(int shift) {
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
	
	public char shiftSingle(char letter) {
		return (char)(letter + this.shift);
	}
}

class ShuffleCipher implements MessageEncoder {
	private int shuffle;
	
	public ShuffleCipher(int shuffle) {
		this.shuffle = shuffle;
	}
	
	public String encode(String plainText) {
		String encodedMsg = plainText;
		for (int i = 0; i < shuffle; i++) {
			encodedMsg = shuffleSingle(encodedMsg);
		}
		return encodedMsg;
	}
	
	public String shuffleSingle(String plainText) {
		char[] origS = plainText.toCharArray();
		char[] targetS = new char[origS.length];
		int mid = origS.length / 2 + origS.length % 2;
		for (int i = 0; i < mid; i++) {
			int updateIndex1 = 2 * i;
			int updateIndex2 = 2 * i +1;
			targetS[updateIndex1] = origS[i];
			if ((mid + i) < origS.length) {
				targetS[updateIndex2] = origS[mid + i];
			}
		}
		return String.valueOf(targetS);
	}
	
}

