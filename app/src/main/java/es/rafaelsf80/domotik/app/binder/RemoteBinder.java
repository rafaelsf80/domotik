package es.rafaelsf80.domotik.app.binder;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import es.rafaelsf80.domotik.R;
import es.rafaelsf80.domotik.app.multipleviewtypesabstractadapter.DataBindAdapter;
import es.rafaelsf80.domotik.app.multipleviewtypesabstractadapter.DataBinder;
import es.rafaelsf80.domotik.app.Machine;

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

public class RemoteBinder extends DataBinder<RemoteBinder.ViewHolder> {

    private final String TAG = getClass().getSimpleName();
    private ArrayList<Machine> machines = new ArrayList<>();


    public RemoteBinder(DataBindAdapter dataBindAdapter) {
        super(dataBindAdapter);
    }

    @Override
    public ViewHolder newViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.card_remote, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick itemLayoutView");
            }
        });


        return new ViewHolder(view);
    }

    @Override
    public void bindViewHolder(ViewHolder rowView, int position) {
        //holder.mImageView.setImageResource(R.drawable.bird);
        //Picasso.with(holder.mImageView.getContext())
        //        .load(R.drawable.bird)
        //        .into(holder.mImageView);

        // get the item which has been pressed and store current item data into variables
        if ((machines != null) && (machines.size()>0)) {
            Log.d(TAG, "position: " + Integer.toString(position) + "size: " + Integer.toString(machines.size()));
            Machine i = machines.get(position);

            // set list menu content to variables
            rowView.tvTitle.setText(i.getType());
            rowView.btDetails.setText(i.getIpAddress());
            rowView.btDemand.setText(i.getHwAddress());

            rowView.tvDescription.setText(i.getName());
            rowView.btInventory.setText(i.getFlags());
            rowView.btSize.setText(i.getPort());

            //rowView.btPrice.setText(context.getResources().getString(R.string.card_price_label) + itemPrice);

            // download thumbnail
            //Picasso.with(context)
            //        .load(i.getUrlPhoto())
            //        .into(rowView.imThumbnail);
        }
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "checking device size: " + Integer.toString(machines.size()));
        return machines.size();
    }

    public void add(Machine machine) {
        machines.add(machine);
        Log.d(TAG, "size: " + Integer.toString(machines.size()));
        notifyBinderDataSetChanged();
    }




    static class ViewHolder extends RecyclerView.ViewHolder {

        public CardView cardView;
        public TextView tvTitle;
        public TextView tvDescription;
        public ImageView imThumbnail;
        public Button btDetails;
        public Button btDemand;
        public Button btInventory;
        public Button btSize;
        public Button btPrice;

        public ViewHolder(View rowView) {
            super(rowView);
            // store UI elements in a variable to be dynamically changed
            cardView = (CardView) rowView.findViewById(R.id.cv_networking);
            tvTitle = (TextView) rowView.findViewById(R.id.tv_title);
            tvDescription = (TextView) rowView.findViewById(R.id.tv_description);
            imThumbnail = (ImageView) rowView.findViewById(R.id.im_thumbnail);

            btDetails = (Button) rowView.findViewById(R.id.bt_card_details);
            btDemand = (Button) rowView.findViewById(R.id.bt_card_demand);
            btInventory = (Button) rowView.findViewById(R.id.bt_inventory);
            btSize = (Button) rowView.findViewById(R.id.bt_size);
            btPrice = (Button) rowView.findViewById(R.id.bt_price);
        }
    }
}
