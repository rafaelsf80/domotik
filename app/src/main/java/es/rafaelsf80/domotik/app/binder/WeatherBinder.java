package es.rafaelsf80.domotik.app.binder;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import es.rafaelsf80.domotik.R;
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
                Log.d(TAG, "onClick itemLayoutView");
            }
        });


        return new ViewHolder(view);
    }

    @Override
    public void bindViewHolder(ViewHolder holder, int position) {
        //holder.mImageView.setImageResource(R.drawable.bird);
        //Picasso.with(holder.mImageView.getContext())
        //        .load(R.drawable.bird)
        //        .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "number of Weather cards: 1 fixed");
        return 1;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(View rowView) {
            super(rowView);
            // store UI elements in a variable to be dynamically changed

        }
    }
}
