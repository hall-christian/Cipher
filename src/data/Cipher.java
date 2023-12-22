package data;

import java.util.ArrayList;
import java.util.Random;

import utilities.FileUtility;

public class Cipher extends FileUtility {
	
	
	private static char offset = 32;
	
	private char[][] encrypter = new char[95][2];
	private char[][] decrypter = new char[95][2];	
	
	
	public Cipher(String filePath) {
		super(filePath);
	}
	
	
	// build a new key from scratch using Random()
	public void buildCipher() {
		System.out.println("building cipher key");
		ArrayList<Integer> shuffled = new ArrayList<Integer>();
		for (int i = offset; i < 127; i++) {
			shuffled.add(i);
		}
		
		Random rand = new Random();
		for (int key = 32; key < 127; key++)
		{
			int idx = rand.nextInt(shuffled.size());
			int val = shuffled.get(idx);
			shuffled.remove(idx);
			setChar(key, val);
		}
	}
	
	
	// import cipher data from an existing *.key file
	public void importCipher() {
		System.out.println("Importing cipher");
		this.read();
		String rawKey = this.view();
		for (int i = 0; i < rawKey.length() - 1; i+= 2) {
			char key = rawKey.charAt(i);
			char val = rawKey.charAt(i + 1);
			setChar(key, val);
		}
	}
	
	
	// write out cipher to specified file
	public void exportCipher() {
		System.out.println("Exporting cypher");
		StringBuilder rawKey = new StringBuilder();
		for (int i = 0; i < encrypter.length; i++)
		{
			rawKey.append(encrypter[i][0]);
			rawKey.append(encrypter[i][1]);
		}
		this.update(rawKey.toString());
		this.write();
	}
	
	
	// translate with the encrypter
	public String encryptMessage(String message)
	{
		System.out.println("Encrypting message");
		return translate(message, encrypter);
	}
	
	
	// translate with the decrypter
	public String decryptMessage(String message)
	{
		System.out.println("Decrypting message");
		return translate(message, decrypter);
	}
	
	
	// encrypt or decrypt a string based on the encrypter or decrypter provided
	private String translate(String message, char[][] codex)
	{
		StringBuilder translated = new StringBuilder();
		
		for (int m = 0; m < message.length(); m++)
		{
			char c = message.charAt(m);
			int i = (int) c - 32;
			if (0 <= i && i < codex.length) {
				translated.append(codex[i][1]);
			} else {
				translated.append(c);
			}
		}
		return translated.toString();
	}
	
	
	// set the encrypter and decrypter at the same time
	private void setChar(int x, int y) {
		char eKey = (char) x;
		char dKey = (char) y;
		
		encrypter[x - offset][0] = eKey;
		encrypter[x - offset][1] = dKey;
		
		decrypter[y - offset][0] = dKey;
		decrypter[y - offset][1] = eKey;
	}
	
	
	@Override
	public String toString()
	{
		String toString = new String();
		toString += "Encrypter | Decrypter\n";
		for (int i = 0; i < encrypter.length; i++) {
			toString+= encrypter[i][0] + " " + encrypter[i][1] + " | " + decrypter[i][0] + " " + decrypter[i][1] + "\n";
		}
		return toString;
	}
}
