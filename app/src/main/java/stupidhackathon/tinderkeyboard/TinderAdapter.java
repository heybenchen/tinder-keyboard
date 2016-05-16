package stupidhackathon.tinderkeyboard;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.ButterKnife;


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

    public List<String> getAllLetters() {
        List<String> letters = new ArrayList<>(mCards.size());
        for (Card card : mCards) {
            letters.add(card.getLetter().toUpperCase());
        }
        return letters;
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
        ViewGroup cardContainer = ButterKnife.findById(convertView, R.id.card_container);
        cardContainer.addView(card.getLetterView(cardContainer));

        TextView fontName = ButterKnife.findById(convertView, R.id.font_name);
        fontName.setText(card.getCardName());

        ColorDrawable drawable = new ColorDrawable();
        RoundedImageView bkg = (RoundedImageView) convertView.findViewById(R.id.bkg);
        bkg.setImageDrawable(card.loadDrawable());

        return convertView;
    }
}
