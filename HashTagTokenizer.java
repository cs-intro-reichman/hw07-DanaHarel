

public class HashTagTokenizer {

	public static void main(String[] args) {

		String hashTag = args[0];
		String []dictionary = readDictionary("dictionary.txt");
		breakHashTag(hashTag, dictionary);
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

	public static boolean existInDictionary(String word, String []dictionary) {
		// Your code here
		boolean flag = false;
		for (int i = 0; i < dictionary.length; i++) {
			if (word.equals(dictionary[i])) {
				flag = true;
				
			}
		}
		return flag;
	}

	public static void breakHashTag(String hashtag, String[] dictionary) {

		// Base case: do nothing (return) if hashtag is an empty string.
		if (hashtag.isEmpty()) {
			return;
		}
	
		int N = hashtag.length();
		int start = 0;
	
		for (int i = 1; i <= N; i++) {
			String wordCheck = hashtag.substring(start, i);
			if (existInDictionary(wordCheck, dictionary)) {
				System.out.println(wordCheck);
				start = i;
			}
		}
	}

}