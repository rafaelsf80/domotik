package es.rafaelsf80.domotik.app.binder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import es.rafaelsf80.domotik.R;
import es.rafaelsf80.domotik.app.Machine;
import es.rafaelsf80.domotik.app.NetworkingDetailsActivity;
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
                Log.d(TAG, "onClick Networking card");
            }
        });

        return new ViewHolder(view);
    }

    @Override
    public void bindViewHolder(final ViewHolder rowView, int position) {
        //holder.mImageView.setImageResource(R.drawable.bird);
        //Picasso.with(holder.mImageView.getContext())
        //        .load(R.drawable.bird)
        //        .into(holder.mImageView);

        if (rowView.imMenu != null)
            rowView.imMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showPopupMenu(rowView.imMenu);
                }
            });

        final int item_number = position;

        // get the item
        if ((machines != null) && (machines.size()>0)) {
            Machine i = machines.get(position);

            // set list menu content to variables
            rowView.tvDeviceName.setText(i.getHwAddress());
            rowView.tvIpAddress.setText(i.getIpAddress());

            final String name = i.getName();
            final String flags = i.getFlags();
            final String hardDisk = i.getHardDisk();
            final String urlPhoto = i.getUrlPhoto();
            final String hwAddress = i.getHwAddress();
            final String ipAddress = i.getIpAddress();
            final String port = i.getPort();
            final String processor = i.getProcessor();
            final String ram = i.getRam();
            final String screen = i.getScreen();
            final String type = i.getType();

            // download thumbnail
            // Picasso.with(context)
            //        .load(i.getUrlPhoto())
            //        .into(rowView.imDevice);

            rowView.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "onClick Networking card number: " + String.valueOf(item_number));
                    Intent intent = new Intent(v.getContext(), NetworkingDetailsActivity.class);

                    // when a list item has been pressed move to the item details screen,  passing the following data
                    intent.putExtra("name", name);
                    intent.putExtra("flags", flags);
                    intent.putExtra("hardDisk", hardDisk);
                    intent.putExtra("urlPhoto", urlPhoto);
                    intent.putExtra("hwAddress", hwAddress);
                    intent.putExtra("ipAddress", ipAddress);
                    intent.putExtra("port", port);
                    intent.putExtra("processor", processor);
                    intent.putExtra("ram", ram);
                    intent.putExtra("screen", screen);
                    intent.putExtra("type", type);

                    // move to the details screen
                    //v.getContext().startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(acti).toBundle());
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(view.getContext(), view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.card_networking_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(new NetworkingBinder.MyMenuItemClickListener(view.getContext()));
        popup.show();
    }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        Context mContext;

        public MyMenuItemClickListener(Context ctx) {
            mContext = ctx;
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                    Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_play_next:
                    Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
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
        public ImageView imMenu;


        public ViewHolder(View rowView) {
            super(rowView);
            // store UI elements in a variable to be dynamically changed
            cardView = (CardView) rowView.findViewById(R.id.cv_networking);
            tvDeviceName = (TextView) rowView.findViewById(R.id.tv_device_name);
            tvIpAddress = (TextView) rowView.findViewById(R.id.tv_ip_address);
            imDevice = (ImageView) rowView.findViewById(R.id.im_device);
            imMenu = (ImageView) rowView.findViewById(R.id.im_card_networking_menu);
        }
    }
}
