package stupidhackathon.tinderkeyboard;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
import java.util.Random;

public class Card {

    private Context mContext;
    private String mLetter;
    private Typeface mTypeface;

    private String[] mFontNames;

    public Card(Context context, String letter) {
        final Random random = new Random();

        mContext = context;
        mLetter = letter;

        try {
            mFontNames = context.getAssets().list("fonts");
        } catch (IOException e) {
            e.printStackTrace();
        }

        final String fontName = "fonts/" +  mFontNames[random.nextInt(mFontNames.length)];
        mTypeface = Typeface.createFromAsset(mContext.getAssets(), fontName);
    }

    public View getLetterView(ViewGroup parentView) {
        TextView textView = (TextView) LayoutInflater.from(mContext)
                .inflate(R.layout.letter_simple, parentView, false);
        textView.setText(mLetter);
        textView.setTypeface(mTypeface);
        return textView;
    }
}
