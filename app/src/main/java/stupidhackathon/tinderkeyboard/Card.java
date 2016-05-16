package stupidhackathon.tinderkeyboard;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Random;

public class Card {

    private Context mContext;
    private String mLetter;
    private Typeface mTypeface;
    private String mFontName;
    private String mBackgroundName;
    private int mFontSize;

    private String[] mFontNames;
    private String[] mBackgroundNames;

    public Card(Context context, String letter) {
        final Random random = new Random();

        mContext = context;
        mLetter = letter.toLowerCase();
        mFontSize = 60 + random.nextInt(70);

        try {
            mFontNames = context.getAssets().list("fonts");
            mBackgroundNames = context.getAssets().list("backgrounds");
        } catch (IOException e) {
            e.printStackTrace();
        }

        mFontName = "fonts/" + mFontNames[random.nextInt(mFontNames.length)];
        mTypeface = Typeface.createFromAsset(mContext.getAssets(), mFontName);
        mBackgroundName = "backgrounds/" + mBackgroundNames[random.nextInt(mBackgroundNames.length)];
    }

    public View getLetterView(ViewGroup parentView) {
                TextView textView = (TextView) LayoutInflater.from(mContext)
                .inflate(R.layout.letter_simple, parentView, false);
        textView.setText(mLetter);
        textView.setTypeface(mTypeface);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, mFontSize);
        return textView;
    }

    public String getLetter() {
        return mLetter;
    }

    public String getCardName() {
        String simpleFontName = mFontName.substring(6, mFontName.length() - 4);
        return String.format(Locale.US, "%s, %dpt", simpleFontName, mFontSize);
    }

    public Drawable loadDrawable() {
        try {
            InputStream ims = mContext.getAssets().open(mBackgroundName);
            return Drawable.createFromStream(ims, null);
        }
        catch(IOException ex) {
            return null;
        }
    }
}


