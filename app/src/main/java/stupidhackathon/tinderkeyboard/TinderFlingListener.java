package stupidhackathon.tinderkeyboard;

import android.content.Context;
import android.util.Log;
import android.widget.EditText;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.List;

public class TinderFlingListener implements SwipeFlingAdapterView.onFlingListener {

    private Context mContext;
    private TinderAdapter mAdapter;
    private EditText mTextInput;
    private List<String> mPreviousGuesses;

    String[] ALPHABET = new String[] {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

    private static final int NUM_SUGGESTIONS = 3;

    public TinderFlingListener(Context context, EditText textInput, TinderAdapter tinderAdapter) {
        mContext = context;
        mTextInput = textInput;
        mAdapter = tinderAdapter;
        mPreviousGuesses = new ArrayList<>();
    }

    @Override
    public void removeFirstObjectInAdapter() {
        mAdapter.removeFirstCard();
    }

    @Override
    public void onLeftCardExit(Object dataObject) {
        // Skip
        Card card = (Card) dataObject;
        mPreviousGuesses.add(card.getLetter().toUpperCase());

        // If we guessed all of the letters, reset
        if (mPreviousGuesses.size() > ALPHABET.length) {
            mPreviousGuesses.clear();
        }
    }

    @Override
    public void onRightCardExit(Object dataObject) {
        // Type it!
        Card card = (Card) dataObject;
        mTextInput.setText(mTextInput.getText() + card.getLetter());
        mPreviousGuesses.clear();
    }

    @Override
    public void onAdapterAboutToEmpty(int itemsInAdapter) {
        // Ask for more data here
        if (itemsInAdapter <= 2) {
            String token = getLastToken();
            mPreviousGuesses.addAll(mAdapter.getAllLetters());
            Log.d("REMOVE", "token: " + token);
            Log.d("REMOVE", "previous: " + mPreviousGuesses.toString());
            List<String> results = LetterSearch.getsInstance().search(token, mPreviousGuesses);
            Log.d("REMOVE", "results: " + results);
            while(results.size() < NUM_SUGGESTIONS) {
                results.add(getRandomLetter());
            }
            if (results.size() > NUM_SUGGESTIONS) {
                results = results.subList(0, NUM_SUGGESTIONS);
            }
            mAdapter.addCards(getCardsFromLetters(results));
        }
    }

    @Override
    public void onScroll(final float v) {

    }

    private String getLastToken() {
        String input = mTextInput.getText().toString();
        if (input.isEmpty()) {
            return "";
        }
        if (input.charAt(input.length() - 1) == ' ') {
            return "";
        }
        String[] tokens = input.split("\\s");
        return tokens[tokens.length - 1];
    }

    private List<Card> getCardsFromLetters(List<String> letters) {
        List<Card> cards = new ArrayList<>(letters.size());
        for (String letter : letters) {
            cards.add(new Card(mContext, letter));
        }
        return cards;
    }

    private String getRandomLetter() {
        return ALPHABET[(int) (Math.random() * ALPHABET.length)];
    }
}
