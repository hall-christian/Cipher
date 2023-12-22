package data;

import utilities.FileUtility;

public class Message {
	
	
	private Input input;
	private Output output;
	private Cipher cipher;
	
	
	public Message(String inputFilePath, String outputFilePath, String cypherFilePath)
	{
		super();
		this.input = new Input(inputFilePath);
		this.output = new Output(outputFilePath);
		this.cipher = new Cipher(cypherFilePath);
	}
	
	
	public void newKey()
	{
		this.cipher.buildCipher();
		this.cipher.exportCipher();
		this.output.export(cipher.encryptMessage(input.view()));
	}
	
	
	public void encrypt()
	{
		this.cipher.importCipher();
		this.output.export(cipher.encryptMessage(input.view()));
	}
	
	
	public void decrypt()
	{
		this.cipher.importCipher();
		System.out.println(this.cipher.toString());
		this.output.export(cipher.decryptMessage(input.view()));
	}
	
	
	public class Input extends FileUtility
	{
		public Input(String filePath) {
			super(filePath);
			this.read();
		}
		
	}
	
	
	public class Output extends FileUtility
	{
		public Output(String filePath) {
			super(filePath);
		}
		
		public void export(String content)
		{
			this.update(content);
			this.write();
		}

	}
}
