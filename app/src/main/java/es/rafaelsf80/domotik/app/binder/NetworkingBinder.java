package es.rafaelsf80.domotik.app.binder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import es.rafaelsf80.domotik.R;
import es.rafaelsf80.domotik.app.Machine;
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

public class NetworkingBinder extends DataBinder<NetworkingBinder.ViewHolder> {

    private final String TAG = getClass().getSimpleName();
    private ArrayList<Machine> machines = new ArrayList<>();


    public NetworkingBinder(DataBindAdapter dataBindAdapter) {
        super(dataBindAdapter);
    }

    @Override
    public ViewHolder newViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.card_networking, parent, false);
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

        // get the item
        if ((machines != null) && (machines.size()>0)) {
            Machine i = machines.get(position);

            // set list menu content to variables
            rowView.tvDeviceName.setText(i.getHwAddress());
            rowView.tvIpAddress.setText(i.getIpAddress());

            // download thumbnail
            //Picasso.with(context)
            //        .load(i.getUrlPhoto())
            //        .into(rowView.imDevice);
        }
    }

    @Override
    public int getItemCount() { return machines.size(); }

    public void add(Machine machine) {
        machines.add(machine);
        notifyBinderDataSetChanged();
    }




    static class ViewHolder extends RecyclerView.ViewHolder {

        public CardView cardView;
        public TextView tvDeviceName;
        public TextView tvIpAddress;
        public ImageView imDevice;


        public ViewHolder(View rowView) {
            super(rowView);
            // store UI elements in a variable to be dynamically changed
            cardView = (CardView) rowView.findViewById(R.id.cv_networking);
            tvDeviceName = (TextView) rowView.findViewById(R.id.tv_device_name);
            tvIpAddress = (TextView) rowView.findViewById(R.id.tv_ip_address);
            imDevice = (ImageView) rowView.findViewById(R.id.im_device);
        }
    }
}
