package stupidhackathon.tinderkeyboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //add the view via xml or programmatically
        mFlingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);

        //set the listener and the adapter
        mAdapter = new TinderAdapter(MainActivity.this);
        fillAdapter(mAdapter);
        mFlingContainer.setAdapter(mAdapter);
        mFlingContainer.setFlingListener(new TinderFlingListener(MainActivity.this, mAdapter));
    }

    private void fillAdapter(TinderAdapter adapter) {
        String[] alphabet = new String[] {"a", "b", "c", "d", "e", "f", "g"};
        for (String letter : alphabet) {
            Card card = new Card(MainActivity.this, letter);
            adapter.addCard(card);
        }
    }
}
