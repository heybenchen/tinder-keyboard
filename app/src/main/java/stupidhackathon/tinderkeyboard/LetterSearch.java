package stupidhackathon.tinderkeyboard;

import android.content.res.AssetManager;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.*;
import java.util.*;
import java.lang.*;

public class LetterSearch {

    private static LetterSearch sInstance = new LetterSearch();
    private List<ArrayList<String>> mDictionary;

    public static LetterSearch getsInstance() {
        return sInstance;
    }

    private LetterSearch() {
        mDictionary = generate();
    }

    public List<ArrayList<String>> generate() {
        try {
            List<ArrayList<String>> listOfLists = new ArrayList<ArrayList<String>>();
            char currentChar = 'A';
            AssetManager assetManager = MainApplication.getContext().getAssets();
            BufferedReader br = new BufferedReader(new InputStreamReader(assetManager.open("dictionary.txt")));
            int index = 0;

            // create 26 Arraylists for the List of list
            for (int i = 0; i < 26; i++) {
                listOfLists.add(new ArrayList<String>());
            }
            try {
                String line = br.readLine();

                while (line != null) {
                    if (line.charAt(0) == currentChar) {
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
        } catch (Exception e) {
            Log.e("ERROR", "Whatever");
        }
        return null;
    }

    @NonNull
    public List<String> search(String currentWord, List<String> previousGuess) {
        if (currentWord == null || currentWord.isEmpty()) {
            return new ArrayList<>();
        }
        currentWord = currentWord.toUpperCase();
        ArrayList<String> result = new ArrayList<>();
        int firstCharIndex = (int) currentWord.charAt(0);
        int length = currentWord.length();
        firstCharIndex = firstCharIndex - 65; // 65 ->0  - 90->25
        String currentGuess;

        // brute force on this ArrayList
        ArrayList<String> dict = mDictionary.get(firstCharIndex);

        for (int i = 0; i < dict.size(); i++) {

            // only valid if greater than the length of the current word
            if (dict.get(i).length() > length) {

                // check if letter at currentWord.length-1 is in the alreadyTriedList
                // otherwise we gucci
                currentGuess = String.valueOf(dict.get(i).charAt(length));

                // check if chars up to length are the same
                if (currentWord.equals(dict.get(i).substring(0, length)) && !previousGuess.contains(currentGuess) && !result.contains(currentGuess)) {
                    result.add(currentGuess);
                }
            }
        }

        return result;
    }

}
