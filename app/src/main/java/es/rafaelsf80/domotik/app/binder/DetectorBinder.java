package es.rafaelsf80.domotik.app.binder;

import android.content.res.Resources;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import es.rafaelsf80.domotik.R;
import es.rafaelsf80.domotik.app.multipleviewtypesabstractadapter.DataBindAdapter;
import es.rafaelsf80.domotik.app.multipleviewtypesabstractadapter.DataBinder;

/**
 * Copyright 2016 Rafael Sanchez Fuentes
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * <p>
 * Author: Rafael Sanchez Fuentes rafaelsf80 at gmail dot com
 */

public class DetectorBinder extends DataBinder<DetectorBinder.ViewHolder> {

    private final String TAG = getClass().getSimpleName();

    enum DetectorType {
        LIVING_ROOM, ROOM_1, HALL, DOOR
    }

    public DetectorBinder(DataBindAdapter dataBindAdapter) {
        super(dataBindAdapter);
    }

    @Override
    public ViewHolder newViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.card_detector, parent, false);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick Detector card");
            }
        });

        return new ViewHolder(view);

    }

    @Override
    public void bindViewHolder(ViewHolder rowView, int position) {

        DetectorType pos = DetectorType.values()[position];
        Resources resources = rowView.cardView.getContext().getResources();

        switch (pos) {
            case HALL:
                rowView.tvTitle.setText(resources.getString(R.string.hall));
                rowView.tvMessage.setText(resources.getString(R.string.no_presence));
                rowView.tvSince.setText(R.string.since);
                break;
            case LIVING_ROOM:
                rowView.tvTitle.setText(resources.getString(R.string.living_room));
                rowView.tvMessage.setText(resources.getString(R.string.no_presence));
                rowView.tvSince.setText(R.string.since);
                break;
            case ROOM_1:
                rowView.tvTitle.setText(resources.getString(R.string.room_1));
                rowView.tvMessage.setText(resources.getString(R.string.no_presence));
                rowView.tvSince.setText(R.string.since);
                break;
            case DOOR:
                rowView.tvTitle.setText(resources.getString(R.string.door));
                rowView.tvMessage.setText(resources.getString(R.string.no_presence));
                rowView.tvSince.setText(R.string.since);
                break;
        }




        final int item_number = position;
        rowView.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick Detector card number: " + String.valueOf(item_number));
                //Intent intent = new Intent(v.getContext(), DetectorDetailsActivity.class);
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
                //v.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "number of Detector cards: " + String.valueOf((DetectorType.values()).length));
        return (DetectorType.values()).length;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView tvTitle, tvMessage, tvSince;

        private ViewHolder(View rowView) {
            super(rowView);
            // store UI elements
            cardView = (CardView) rowView.findViewById(R.id.cv_detector);
            tvTitle = (TextView) rowView.findViewById(R.id.tv_detector_title);
            tvMessage = (TextView) rowView.findViewById(R.id.tv_detector_message);
            tvSince = (TextView) rowView.findViewById(R.id.tv_detector_since);
        }
    }
}

