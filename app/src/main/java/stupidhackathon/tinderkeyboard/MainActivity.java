package stupidhackathon.tinderkeyboard;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.EditText;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

    private ViewGroup mFlingContainer;
    private SwipeFlingAdapterView mFlinger;
    private EditText mEditText;

    private TinderAdapter mAdapter;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get view
        mFlingContainer = (ViewGroup) findViewById(R.id.fling_container);
        mFlinger = (SwipeFlingAdapterView) findViewById(R.id.flinger);
        mEditText = (EditText) findViewById(R.id.text);

        // Setup adapter for keyboard
        mAdapter = new TinderAdapter(MainActivity.this);
        fillAdapter(mAdapter);
        mFlinger.setAdapter(mAdapter);
        mFlinger.setFlingListener(new TinderFlingListener(MainActivity.this, mEditText, mAdapter));

        // Set keyboard show/hide
        mEditText.setOnClickListener(mEditClickListener);

        // Hide keyboard at start
        mFlingContainer.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                hideKeyboard(true);
                mFlingContainer.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

        // Backspace and Space buttons
        findViewById(R.id.back_button).setOnClickListener(mBackClickListener);
        findViewById(R.id.space_button).setOnClickListener(mSpaceClickListener);
    }

    @Override
    public void onBackPressed() {
        hideKeyboard(false);
    }

    private void fillAdapter(TinderAdapter adapter) {
        String[] alphabet = new String[] {"a", "b", "c", "d", "e", "f", "g"};
        for (int i = 0; i < 20; i++) { // Need more letters
            for (String letter : alphabet) {
                Card card = new Card(MainActivity.this, letter);
                adapter.addCard(card);
            }
        }
    }

    private void showKeyboard(boolean immediate) {
        long time = immediate ? 0 : 250;
        mFlingContainer.animate()
                .translationY(0)
                .setDuration(time)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .start();
    }

    private void hideKeyboard(boolean immediate) {
        long time = immediate ? 0 : 250;
        mFlingContainer.animate()
                .translationY(mFlinger.getHeight())
                .setDuration(time)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .start();
    }

    private View.OnClickListener mEditClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            showKeyboard(false);
        }
    };

    private View.OnClickListener mBackClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            mEditText.setText(mEditText.getText().subSequence(0, mEditText.length() -1));
        }
    };

    private View.OnClickListener mSpaceClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            mEditText.setText(mEditText.getText() + " ");
        }
    };
}
