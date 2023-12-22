package main;

import java.io.File;

import data.Cipher;
import data.Message;

public class Main {
	
	private static final String CWD = System.getProperty("user.dir") + "/";
	private static final String ITEXT = CWD + "input.txt";
	private static final String ICRYPT = CWD + "input.cpt";
	private static final String OTEXT = CWD + "output.txt";
	private static final String OCRYPT = CWD + "output.cpt";
	private static final String CIPHER = CWD + "cipher.txt";



	public static void main(String[] args) {
		
		Message message;
		
		if (new File(ITEXT).exists()) {
			message = new Message(ITEXT, OCRYPT, CIPHER);
			if (new File(CIPHER).exists()) {
				// case for encryption with existing key
				message.encrypt();
			} else {
				// case for encryption, need a new key
				message.newKey();
			}
			
		} else if (new File(ICRYPT).exists() && new File(CIPHER).exists()) {
			// case for de-cryption with existing key
			message = new Message(ICRYPT, OTEXT, CIPHER);
			message.decrypt();
		} else {
			Cipher cipher = new Cipher(CIPHER);
			cipher.buildCipher();
			cipher.exportCipher();
		}
	}
}
