package stupidhackathon.tinderkeyboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Collection;


public class TinderAdapter extends ArrayAdapter<Card> {

    private Context mContext;
    private ArrayList<Card> mCards;

    public TinderAdapter(Context context) {
        super(context, 0);
        mCards = new ArrayList<>();
        mContext = context;
    }

    public void addCard(Card card) {
        mCards.add(card);
        notifyDataSetChanged();
    }

    public void addCards(Collection<Card> cards) {
        mCards.addAll(cards);
        notifyDataSetChanged();
    }

    public void removeFirstCard() {
        mCards.remove(0);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mCards.size();
    }

    @Override
    public Card getItem(int position) {
        return mCards.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.card, parent, false);
        }

        Card card = mCards.get(position);
        ViewGroup cardContainer = (ViewGroup) convertView.findViewById(R.id.card_container);
        cardContainer.addView(card.getLetterView(cardContainer));

        return convertView;
    }
}
