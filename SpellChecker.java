
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		// Implementation for getting the tail of the string
		if (str.length() <= 1) {
			return "";
		}
		return str.substring(1);
	}

	public static int levenshtein(String word1, String word2) {
		// Base cases
		//if either of the strings is empty, the distance between them is the length of the non-empty string.
		if (word1.isEmpty()) {
			return word2.length();
		}
	
		if (word2.isEmpty()) {
			return word1.length();
		}
	
		// Check if the first characters are equal
		int difference;
		//first characters of word1 and word2 are the same - difference is set to 0
          if (word1.charAt(0) == word2.charAt(0)) 
		   {difference = 0;} 
		   //first characters are different - difference is set to 1
		   else 
		   {difference = 1;}

		//calculates the cost of the insertion operation
		int insert = levenshtein(word1, tail(word2)) + 1;

	    //calculates the cost of the deletion operation
		int delete = levenshtein(tail(word1), word2) + 1;

		//calculates the cost of the substitution operation
		int substitute = levenshtein(tail(word1), tail(word2)) + difference;
	
		// Return the minimum of the three operations
           
		if (insert <= delete && insert <= substitute) //insert is minimum
		   {return insert;} 

			 else if (delete <= insert && delete <= substitute) //delete is minimum
			  {return delete;} 

			  else 
			   {return substitute;} //substitute is minimum        

	}
	

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];
	
		In in = new In(fileName);
	
		// Your code here
		for (int i = 0; i < dictionary.length; i++) {
			String word = in.readString();
			dictionary[i] = word;
			}

	
		return dictionary;
	}
	

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		// Your code goes here
		 int minimalDistance = 10000; //ensure that the first distance calculated will always be smaller.
		 int closestWord = -1; //no closest word has been found yet.

		// check the dictionary to find the closest word
		for (int i = 0; i < dictionary.length; i++) {
			//  check for null values before using toLowerCase()
			if (dictionary[i] != null) {
				// Calculate the Levenshtein distance between the given word and the current word in the dictionary
				int distance = levenshtein(word.toLowerCase(), dictionary[i].toLowerCase());
	
				// Check if the current distance is smaller than the minimum distance
				if (distance < minimalDistance) {
					minimalDistance = distance;
					closestWord = i;
				}
			}
		}
		   // Check if the minimum distance is in the threshold
		   if (minimalDistance <= threshold)
		   {return dictionary[closestWord];}
	       else
		   {return word;}
	 
	}

}
