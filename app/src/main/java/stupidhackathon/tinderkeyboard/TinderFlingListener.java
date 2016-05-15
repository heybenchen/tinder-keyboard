package stupidhackathon.tinderkeyboard;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

public class TinderFlingListener implements SwipeFlingAdapterView.onFlingListener {

    private Context mContext;
    private TinderAdapter mAdapter;

    public TinderFlingListener(Context context, TinderAdapter tinderAdapter) {
        mContext = context;
        mAdapter = tinderAdapter;
    }

    @Override
    public void removeFirstObjectInAdapter() {
        // this is the simplest way to delete an object from the Adapter (/AdapterView)
        Log.d("LIST", "removed object!");
        mAdapter.removeFirstCard();
    }

    @Override
    public void onLeftCardExit(Object dataObject) {
        //Do something on the left!
        //You also have access to the original object.
        //If you want to use it just cast it (String) dataObject
        Toast.makeText(mContext, "Left!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRightCardExit(Object dataObject) {
        Toast.makeText(mContext, "Right!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAdapterAboutToEmpty(int itemsInAdapter) {
        // Ask for more data here
        if (itemsInAdapter <= 1) {
            Toast.makeText(mContext, "About to empty!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onScroll(final float v) {

    }
}
