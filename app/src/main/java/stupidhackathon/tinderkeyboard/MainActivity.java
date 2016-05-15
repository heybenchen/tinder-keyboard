package stupidhackathon.tinderkeyboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {

    private TinderAdapter mAdapter;
    private SwipeFlingAdapterView mFlingContainer;
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get view
        mFlingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);
        mEditText = (EditText) findViewById(R.id.text);

        // Setup adapter for keyboard
        mAdapter = new TinderAdapter(MainActivity.this);
        fillAdapter(mAdapter);
        mFlingContainer.setAdapter(mAdapter);
        mFlingContainer.setFlingListener(new TinderFlingListener(MainActivity.this, mEditText, mAdapter));

        // Set keyboard show/hide
        mEditText.setOnClickListener(mEditClickListener);
        hideKeyboard(true);
    }

    @Override
    public void onBackPressed() {
        hideKeyboard(false);
    }

    private void fillAdapter(TinderAdapter adapter) {
        String[] alphabet = new String[] {"a", "b", "c", "d", "e", "f", "g"};
        for (String letter : alphabet) {
            Card card = new Card(MainActivity.this, letter);
            adapter.addCard(card);
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
                .translationY(mFlingContainer.getHeight())
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
}
