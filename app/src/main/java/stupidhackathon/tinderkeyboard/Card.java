package stupidhackathon.tinderkeyboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Card {

    private Context mContext;
    private String mLetter;

    public Card(Context context, String letter) {
        mContext = context;
        mLetter = letter;
    }

    public View getLetterView(ViewGroup parentView) {
        TextView textView = (TextView) LayoutInflater.from(mContext).inflate(R.layout.letter_simple, parentView, false);
        textView.setText(mLetter);
        return textView;
    }
}
