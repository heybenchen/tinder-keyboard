import java.io.*;
import java.util.*;
import java.lang.*;

public class LetterSearch {

  public static List<ArrayList<String>> generate() throws IOException {
    List<ArrayList<String>> listOfLists = new ArrayList<ArrayList<String>>();
    char currentChar = 'A';
    List<String> dictionary = new ArrayList<String>();
    FileReader fr = new FileReader("dictionary.txt");
    BufferedReader br = new BufferedReader(fr);
    int index = 0;
    // create 26 Arraylists for the List of list
    for(int i=0;i<26;i++) {
      listOfLists.add(new ArrayList<String>());
    }
    try {
        String line = br.readLine();

        while (line != null) {
            if(line.charAt(0) == currentChar) {
              listOfLists.get(index).add(line);
            } else {
              index++;
              currentChar++;
              listOfLists.get(index).add(line);
            }
            line = br.readLine();
        }
    } finally {
        br.close();
    }
    return listOfLists;
  }

  public static ArrayList<Character> search(List<ArrayList<String>> listOfLists, String currentWord, ArrayList<Character> previousGuess) {
    ArrayList<Character> result = new ArrayList<Character>();
    ArrayList<String> dict = new ArrayList<String>();
    int firstCharIndex = (int)currentWord.charAt(0);
    int length = currentWord.length(); 
    firstCharIndex = firstCharIndex - 65; // 65 ->0  - 90->25
    char currentGuess;

    // brute force on this ArrayList
    dict = listOfLists.get(firstCharIndex);

    for(int i=0;i<dict.size();i++) {
      
      // only valid if greater than the length of the current word
      if(dict.get(i).length() > length) {
        
        // check if letter at currentWord.length-1 is in the alreadyTriedList
        // otherwise we gucci
        currentGuess = dict.get(i).charAt(length);

        // check if chars up to length are the same
        if(currentWord.equals(dict.get(i).substring(0, length)) && !previousGuess.contains(currentGuess) && !result.contains(currentGuess)) {
          result.add(currentGuess);
        }
      }
    }

    return result;
  }
  public static void main(String[] args) throws IOException {
    String inputString = "HEADPIN";
    ArrayList<Character> previousGuess = new ArrayList<Character>();
    List<ArrayList<String>> dict = generate();
    System.out.println(search(dict, inputString.toUpperCase(), previousGuess));
  }
}
