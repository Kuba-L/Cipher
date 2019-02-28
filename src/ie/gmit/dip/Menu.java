package ie.gmit.dip;

import java.util.*;
import java.io.*;
public class Menu{


	
	/*
	 * Constructor
	 */
	
	public Menu() {
		start();
		
	}
	
	/*
	 * 
	 */
	
	public void start(){
	    
	    FourSquareCipher Square = new FourSquareCipher();
		Scanner in = new Scanner(System.in);	
		String plainText = "";
		String menuInput = null;
		String methodInput = null;
		String file = " ";
		String plainTextFromFile = "";
		String line = null;
		BufferedReader br = null;;
        /*
         * Initializes menu loop
         */
			do{
				showOptions();      // method that shows Menu options
				menuInput = in.nextLine();  //reads console input
				
			    /*
			     * Displays plain text input choices
			     */
				if (menuInput.equals("1")){
					System.out.println("Enter the plain text");
					plainText = in.nextLine();
					System.out.println("You have entered plain text: " + plainText);
				}else if (menuInput.equals("2")){
					System.out.println("Enter the file name or path (IDE)");
					file = in.nextLine();
					System.out.println("You have entered the file name: " + file);
				}else if (menuInput.equals("3")){
					System.out.println("Quiting...");
					System.exit(0);
				}else {
					System.out.println("Invalid choice. Please select 1-3 ");
				}
				}while (!menuInput.equals("1") && !menuInput.equals("2"));
				
					if (menuInput.equals("1")){ //Encrypting console input plain text
				do {
				 /*
				  * Choosing methods
				  */
				 
					showMethods(); //Prints methods
					methodInput = in.nextLine();
					if (methodInput.equals("1")){	
						System.out.println(" Encrypting and decrypting...");
						Square.convertToBigram(plainText);
						Square.encrypt();
						Square.decrypt();		
					}else if(methodInput.equals("2")){
						System.out.println(" Printing Alphabet and keyword Squares...");
						Square.printAlphabet();
						Square.printKeySquare1();
						Square.printKeySquare2();
					}else if(methodInput.equals("3")){
						Square.printBigram();
						Square.printEncryptedMessage();
						Square.printDecryptedMessage();

					}else if(methodInput.equals("4")){
						
						System.out.println("Saving to a file...");	
						/*
						 * Saving to a file
						 */
						try {

							BufferedWriter wr = new BufferedWriter(new FileWriter("plainText.txt"));

							wr.write(Square.getDecryptedText());

							wr.close();
							} catch(IOException e) {
							e.printStackTrace();
							System.out.println("Caught I/O Exception " + e);
							}
							try {

							BufferedWriter wr = new BufferedWriter(new FileWriter("cipherText.txt"));

							wr.write(Square.getEncryptedText());

							wr.close();
							}catch(IOException e) {
							e.printStackTrace();
							System.out.println("Caught I/O Exception " + e);
						}

					
				}else if(methodInput.equals("5")){
					System.exit(0);

				}
					
				}while (!methodInput.equals("0"));
			
			}else if(menuInput.equals("2")) { //Encrypting plain text from a file
			    /*
			     * Reads text from a file
			     */
				try { //try catch block looking for IOExceptions
		br = new BufferedReader(new FileReader(file));

		while((line = br.readLine()) != null) {

		plainTextFromFile += line;
			}
		}

		
    catch(IOException e) {
            System.out.println("Caught I/O Exception " + e);
            return;
        }
        
        
    } 

		
		do {
			showMethods(); //Prints methods
			methodInput = in.nextLine();
                
                /*
                 * Choosing a method
                 */
                
			if (methodInput.equals("1")){		
				System.out.println(" Encrypting and decrypting...");
				Square.convertToBigram(plainTextFromFile);
				Square.encrypt();
				Square.decrypt();
				
			}else if(methodInput.equals("2")){
				System.out.println(" Printing Alphabet and keyword Squares...");
				Square.printAlphabet();
				Square.printKeySquare1();
				Square.printKeySquare2();
			}else if(methodInput.equals("3")){
				Square.printBigram();
				Square.printEncryptedMessage();
				Square.printDecryptedMessage();

			}else if(methodInput.equals("4")){
				
				System.out.println("Saving to a file...");	
				try {

					BufferedWriter wr = new BufferedWriter(new FileWriter("plainText.txt"));

					wr.write(Square.getDecryptedText());

					wr.close();
					}catch(IOException e) {
					e.printStackTrace();
					System.out.println("Caught I/O Exception " + e);
					}
					try {

					BufferedWriter wr = new BufferedWriter(new FileWriter("cipherText.txt"));

					wr.write(Square.getEncryptedText());

					wr.close();
					}catch(IOException e) {

					e.printStackTrace();
					System.out.println("Caught I/O Exception " + e);
				}

			
		}else if(methodInput.equals("5")){
			System.exit(0);

		}
			
		}while (!methodInput.equals("0"));
	
		}
		

	/*
	 * Methods that print the menu
	 */
	
	 private void showOptions(){
         System.out.println("########################");
         System.out.println("###Four Square Cipher###");
         System.out.println("########################");  
         System.out.println("(1) Enter the keyword ");
         System.out.println("(2) Load a keyword from a file ");
         System.out.println("(3) Quit");
         System.out.println("Select an option [1-3]> ");
	}
	 private void showMethods(){
         System.out.println("########################");
         System.out.println("###Four Square Cipher###");
         System.out.println("########################");  
         System.out.println("(1) Encrypt and decrypt ");
         System.out.println("(2) Print alphabet Squares and keyword Suares ");
         System.out.println("(3) Show plain text and cipher text");
         System.out.println("(4) Save to a text file");
         System.out.println("(5) Quit");

         System.out.println("Select an option [1-5]> ");
	}
}

