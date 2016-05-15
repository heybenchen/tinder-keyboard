package stupidhackathon.tinderkeyboard;

import android.content.Context;
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
        mPreviousGuesses.add(card.getLetter());
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
            List<String> results = LetterSearch.getsInstance().search(mTextInput.getText().toString(), mPreviousGuesses);
            while(results.size() < NUM_SUGGESTIONS) {
                results.add(getRandomLetter());
            }
            mAdapter.addCards(getCardsFromLetters(results));
        }
    }

    @Override
    public void onScroll(final float v) {

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
