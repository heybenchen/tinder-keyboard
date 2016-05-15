package stupidhackathon.tinderkeyboard;

import android.content.Context;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

public class TinderFlingListener implements SwipeFlingAdapterView.onFlingListener {

    private Context mContext;
    private TinderAdapter mAdapter;
    private EditText mTextInput;

    public TinderFlingListener(Context context, EditText textInput, TinderAdapter tinderAdapter) {
        mContext = context;
        mTextInput = textInput;
        mAdapter = tinderAdapter;
    }

    @Override
    public void removeFirstObjectInAdapter() {
        mAdapter.removeFirstCard();
    }

    @Override
    public void onLeftCardExit(Object dataObject) {
        // Skip
    }

    @Override
    public void onRightCardExit(Object dataObject) {
        // Type it!
        Card card = (Card) dataObject;
        mTextInput.setText(mTextInput.getText() + card.getLetter());
    }

    @Override
    public void onAdapterAboutToEmpty(int itemsInAdapter) {
        // Ask for more data here
        if (itemsInAdapter <= 1) {
            // Get elements
        }
    }

    @Override
    public void onScroll(final float v) {

    }
}
