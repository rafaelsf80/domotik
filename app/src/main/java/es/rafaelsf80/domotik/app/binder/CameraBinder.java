package es.rafaelsf80.domotik.app.binder;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import es.rafaelsf80.domotik.R;
import es.rafaelsf80.domotik.app.CameraDetailsActivity;
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

public class CameraBinder extends DataBinder<CameraBinder.ViewHolder> {

    private final String TAG = getClass().getSimpleName();


    public CameraBinder(DataBindAdapter dataBindAdapter) {
        super(dataBindAdapter);
    }

    @Override
    public ViewHolder newViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.card_camera, parent, false);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick Camera card");
            }
        });


        return new ViewHolder(view);
    }

    @Override
    public void bindViewHolder(ViewHolder rowViewHolder, int position) {
        //holder.mImageView.setImageResource(R.drawable.bird);
        //Picasso.with(holder.mImageView.getContext())
        //        .load(R.drawable.bird)
        //        .into(holder.mImageView);
        final int item_number = position;
        rowViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick Camera card number: " + String.valueOf(item_number));
                Intent intent = new Intent(v.getContext(), CameraDetailsActivity.class);
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
                //v.getContext().startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(((Activity) v.getContext())).toBundle());
                v.getContext().startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "number of Camera cards -- 1 fixed");
        return 1;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public CardView cardView;

        public ViewHolder(View rowView) {
            super(rowView);
            // store UI elements in a variable to be dynamically changed
            cardView = (CardView) rowView.findViewById(R.id.cv_camera);
        }
    }
}
