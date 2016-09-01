package es.rafaelsf80.domotik.app.binder;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import es.rafaelsf80.domotik.R;
import es.rafaelsf80.domotik.app.WeatherDetailsActivity;
import es.rafaelsf80.domotik.app.multipleviewtypesabstractadapter.DataBindAdapter;
import es.rafaelsf80.domotik.app.multipleviewtypesabstractadapter.DataBinder;

public class WeatherBinder extends DataBinder<WeatherBinder.ViewHolder> {

    private final String TAG = getClass().getSimpleName();


    public WeatherBinder(DataBindAdapter dataBindAdapter) {
        super(dataBindAdapter);
    }

    @Override
    public ViewHolder newViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.card_weather, parent, false);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick Weather card");
            }
        });


        return new ViewHolder(view);

    }

    @Override
    public void bindViewHolder(ViewHolder rowViewHolder, int position) {

        final int item_number = position;
        rowViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick Weather card number: " + String.valueOf(item_number));
                Intent intent = new Intent(v.getContext(), WeatherDetailsActivity.class);
//
//                // when a list item has been pressed move to the item details screen,  passing the following data
//                intent.putExtra("itemName", itemName);
//                intent.putExtra("mainImage", thumbnailURL);
//                intent.putExtra("brand", brand);
//                intent.putExtra("size", size);
//                intent.putExtra("sizeGuide", sizeGuide);
//                intent.putExtra("videoPreview", videoPreview);
//                intent.putExtra("itemPrice", itemPrice);
//                intent.putExtra("inventoryCount", inventoryCount);
//                intent.putExtra("stockForecast", stockForecast);
//
                // move to the details screen
                v.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "number of Weather cards: 1 fixed");
        return 1;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public CardView cardView;

        public ViewHolder(View rowView) {
            super(rowView);
            // store UI elements
            cardView = (CardView) rowView.findViewById(R.id.cv_weather);
        }
    }
}
