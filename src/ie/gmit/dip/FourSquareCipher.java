package ie.gmit.dip;

import java.util.ArrayList;
import java.util.Random;


public class FourSquareCipher {

	/**
	 * visual representation of the Four Square cipher= { 
	 * {"A", "B", "C", "D", "E",  "Z", "G", "P", "T", "F" }, 
	 * {"F", "G", "H", "I", "K",  "O", "I", "H", "M", "U" }, 
	 * {"L", "M", "N", "O", "P",  "W", "D", "R", "C", "N" }, 
	 * {"Q", "R", "S", "T", "U",  "Y", "K", "E", "Q", "A" }, 
	 * {"V", "W", "X", "Y", "Z",  "X", "V", "S", "B", "L" }, 
	 * 
	 * {"M", "F", "N", "B", "D",  "A", "B", "C", "D", "E" }, 
	 * {"C", "R", "H", "S", "A",  "F", "G", "H", "I", "K" }, 
	 * {"X", "Y", "O", "G", "V",  "L", "M", "N", "O", "P" }, 
	 * {"I", "T", "U", "E", "W",  "Q", "R", "S", "T", "U" }, 
	 * {"L", "Q", "Z", "K", "P",  "V", "W", "X", "Y", "Z" } 
	 * };
	 */
	
	/*
	 * Square variables
	 */
	private char[][] keywordSquare1; // Top right square

	private char[][] keywordSquare2; // Bottom left square

	private char[][] alphabetSquare; // Top left & bottom right square -- alphabetSquare will represent both Top left and Bottom right square !

	/*
	 * Array List shuffled by Fisher-Yates algorithm
	 */

	private static ArrayList<Character> shuffledAlphabet1;
	private static ArrayList<Character> shuffledAlphabet2;

	/*
	 * Variables
	 */
	private String plainText;
	private String encryptedTextString;
	private String decryptedTextString;
	private String[] encryptedText;
	private String[] decryptedText;
	private int sizeBigram;
	private String[] bigram;

	/*
	 * Constructor
	 */

	public FourSquareCipher() {

		alphabetSquare = new char[5][5];
		keywordSquare1 = new char[5][5];
		keywordSquare2 = new char[5][5];
		shuffledAlphabet1 = new ArrayList<Character>();
		shuffledAlphabet2 = new ArrayList<Character>();
		sizeBigram = 0;
		
		
		populateArrayList();
		shuffleArrayList(shuffledAlphabet1);
		shuffleArrayList(shuffledAlphabet2);
		populateKeywordSquare();
		populateAlphabetSquare();
	}
	
	public static void populateArrayList() {
		for(int i = 0; i <= 25 ; i++){
			if(i == 9) i++;
			char n = (char) (i + 'A');
		shuffledAlphabet1.add(n);
		shuffledAlphabet2.add(n);

		}
	}
	/*
	 * 
	 * Using Fisher-Yikes algorithm to shuffle array list.
	 */
	public static void shuffleArrayList(ArrayList<Character> a) {
		
		     int n = a.size(); // ArrayList's use .size method instead of .length
		     Random random = new Random();
		     random.nextInt();
		     for (int i = 0; i < n; i++) {
		         int change = i + random.nextInt(n - i);
		         swap(a, i, change);
		     }
		 }

		 private static void swap(ArrayList<Character> a, int i, int change) {
		     int helper = a.get(i);
		     a.set(i, a.get(change));
		     a.set(change, (char) helper);
		 }	
		
	
		 /*
		  * Populating alphabet square array with sorted alphabet
		  */

	public void populateAlphabetSquare() {
		int count = 0;


		  for (int i = 0; i < 5; i++) {

		   for (int j = 0; j < 5; j++) {

		    if(count == 9) count++;

		    alphabetSquare[i][j] = (char)('A' + count);

		    count++;
		   }
		}
		   
	}
	
	/*
	 * Populating keyword square 1 and 2 with randomly shuffled alphabet
	 */
	
	public void populateKeywordSquare() {
		int count1 = 0;
		int count2 = 0;
			for (int i = 0; i < 5; i++){
				for (int j = 0; j<5; j++){
					keywordSquare1[i][j] = shuffledAlphabet1.get(count1);
					count1++;
					}	
			}
			for (int i = 0; i < 5; i++){
				for(int j = 0; j < 5; j++){
					keywordSquare2[i][j] = shuffledAlphabet2.get(count2);
					count2++;
				}
			}
		
	}
	/*
	 * Converts plain text string to bigram
	 */
	 public void convertToBigram(String key) {

		  plainText = key;

		  plainText = plainText.toUpperCase().replaceAll("[^A-Za-z0-9]","").replace('J', 'I');
		  //Converts text to Upper case, replaces all number, replaces J for I.
		  
		  boolean verify = bigramIsOdd(plainText);


		  if(verify) sizeBigram = (plainText.length() / 2) + 1;

		  else sizeBigram = plainText.length() / 2;


		  bigram = new String[sizeBigram]; 

		  /*
		   * 
		   * Adds X if the bigram is not even size
		   */

		  if (verify) {
			char x = 'X';
			
			plainText += x;

		  }


		  int count = 0;


		  for (int i = 0; i < bigram.length; i++) {

		   bigram[i] = plainText.substring(count, count+2);
		   count += 2;
		   
		  }
		  
		 }
		 
	 
	 /*
	 *Checks if bigram is even 
	 */
	 public boolean bigramIsOdd(String key) {

	  boolean verify = true;

	  int size = 0;


	  for (int i = 0; i < key.length(); i++) size++;


	  if (size % 2 == 0) verify = false;


	  return verify;

	 }
	 /**
	  * 
	  * Encrypting and decrypting methods
	  */
	 
	public void encrypt(){
		
		int colTopLeft = 0, colBotRight = 0, lineTopLeft = 0, lineBotRight = 0;

		  char first, second;

		  encryptedText = new String[sizeBigram];
        
		  /*
		   * Looping over bigram plain text array
		   */
		  for(int i = 0; i < bigram.length; i++) {

		   first = bigram[i].charAt(0); //finding first letter of a pair

		   second = bigram[i].charAt(1); // finding second letter of a pair

		   for(int m = 0; m < 5; m++) {

		    for(int n = 0; n < 5; n++) {

		      if(alphabetSquare[m][n] == first) { // Found row and column of first letter in the alphabet square

		      lineTopLeft = m; 	//assigning row to variable

		      colTopLeft = n;	//assigning column to variable

		     } else if(alphabetSquare[m][n] == second) { // Found row and column of second letter in the alphabet square

		      lineBotRight = m; //assigning row to variable

		      colBotRight = n; //assigning column to variable

		     }

		    }

		   }

		   encryptedText[i] = "" + keywordSquare1[lineTopLeft][colBotRight] + keywordSquare2[lineBotRight][colTopLeft];
		   /*Finding the character at the intersection of the row 
		    *of the first plaintext character in the top left alphabet square 
		    *with the column of the second plaintext character in the bottom right square.
			*
		   	*Finding the character at the intersection of the column 
		    *of the first plaintext character in the top left alphabet square 
		    *with the row of the second plaintext character in the bottom right square.
			*/
		  }

		 }


	
	public void decrypt(){
		int colTopRight = 0, colBotLeft = 0, lineTopRight = 0, lineBotLeft = 0;

		  char first, second;

		  decryptedText = new String[sizeBigram];


		  for(int i = 0; i < encryptedText.length; i++) {

		   first = encryptedText[i].charAt(0);

		   second = encryptedText[i].charAt(1);

		   for(int m = 0; m < 5; m++) {

		    for(int n = 0; n < 5; n++) {

		     if(keywordSquare1[m][n] == first) {

		      lineTopRight = m;

		      colTopRight = n;

		     } else if(keywordSquare2[m][n] == second) {

		      lineBotLeft = m;

		      colBotLeft = n;

		     }

		    }

		   }

		   decryptedText[i] = "" + alphabetSquare[lineTopRight][colBotLeft] + alphabetSquare[lineBotLeft][colTopRight];
		   /*Finding the character at the intersection of the row 
		    *of the first encrypted character in the top right keyword square 
		    *with the column of the second encrypted character in the bottom left keyword square.
			*
		   	*Finding the character at the intersection of the row 
		    *of the first encrypted character in the bottom left keyword square 
		    *with the row of the second encrypted character in the top right square.
			*/
		  }


		  arrayToString();

		 }
        
        /*
         * Gets text from array to string
         */
        
		public void arrayToString() {

	  for(int i = 0; i < encryptedText.length; i++) {

	   encryptedTextString += encryptedText[i];

	   decryptedTextString += decryptedText[i];

	  }

	 }


	/*
	 *Methods to print Squares 
	 */
	
	public void printAlphabet(){
		  System.out.println("Alphabet Square");

		  //print the alphabet square

		  for (int i = 0; i < 5; i++) {

		   System.out.println("");

		   for (int j = 0; j < 5; j++) {

		    System.out.print(alphabetSquare[i][j] + " ");
		   }
		  }
		}
	public void printKeySquare1(){



		  System.out.println("");

		  System.out.println("Keyword Square 1");

		  //prints the keyword square 1

		  for (int i = 0; i < 5; i++) {

		   System.out.println("");

		   for (int j = 0; j < 5; j++) {

		    System.out.print(keywordSquare1[i][j] + " ");
		     }	  
		   }
		  System.out.println("");

		}
	public void printKeySquare2(){


		  System.out.println("");

		  System.out.println("Keyword Square 2");

		  //prints the keyword square 2

		  for (int i = 0; i < 5; i++) {

		   System.out.println("");

		   for (int j = 0; j < 5; j++) {

		    System.out.print(keywordSquare2[i][j] + " ");

		   }
		  }
		  System.out.println("");

		}
		
		/*
		 * Prints bigram to be encrypted
		 */
		
	public void printBigram(){


		  System.out.println("");

		  System.out.println("Bigram to be encrypted: ");

		  //prints the bigram

		  for (int i = 0; i < encryptedText.length; i++) {

		   System.out.print(bigram[i]);
		  	}
		  System.out.println("");

		  }
		  
		  /*
		   * Prints Encrypted text 
		   */
		  
	public void printEncryptedMessage(){



		  System.out.println("");

		  System.out.println("Encrypted message: ");

		  //prints the encrypted message

		  for (int i = 0; i < encryptedText.length; i++) {

		   System.out.print(encryptedText[i]);

		  }
		  System.out.println("");

		}
		
		/*
		 *Prints decrypted text 
		 */
		
			public void printDecryptedMessage(){


		  System.out.println("");

		  System.out.println("Decrypted message: ");

		  for (int i = 0; i < decryptedText.length; i++) {

		   System.out.print(decryptedText[i]);

		  }


		  System.out.println("");
		  System.out.println("-------------------------");
		 }
		 
		 /*
		  * Getter methods for encrypted and decrypted text
		  */

    public String getEncryptedText(){
        return encryptedTextString;
        
    }
     public String getDecryptedText(){
        return decryptedTextString;
        
    }
		}
