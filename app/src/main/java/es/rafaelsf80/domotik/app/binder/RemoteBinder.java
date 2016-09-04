package es.rafaelsf80.domotik.app.binder;

import android.content.Context;
import android.content.res.Resources;
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
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

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

public class RemoteBinder extends DataBinder<RemoteBinder.ViewHolder> {

    private final String TAG = getClass().getSimpleName();

    enum RemoteType {
        NATURAL_GAS, ELECTRICITY, WATER, AIR_CONDITIONING
    }

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
                Log.d(TAG, "onClick Remote card");
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

        // get the item which has been pressed and store current item data into variables

        RemoteType pos = RemoteType.values()[position];
        Resources resources = rowView.cardView.getContext().getResources();

        switch (pos) {
            case NATURAL_GAS:
                rowView.tvTypeProvider.setText(resources.getString(R.string.gas_provider) + " - " + resources.getString(R.string.gas_type));
                rowView.swOnOff.setText(resources.getString(R.string.off));
                rowView.imIcon.setImageResource(R.drawable.ic_heating_black_24dp);
                break;
            case ELECTRICITY:
                rowView.tvTypeProvider.setText(resources.getString(R.string.electricity_provider) + " - " + resources.getString(R.string.electricity_type));
                rowView.swOnOff.setText(resources.getString(R.string.off));
                rowView.imIcon.setImageResource(R.drawable.ic_light_on_icon);
                break;
            case AIR_CONDITIONING:
                rowView.tvTypeProvider.setText(resources.getString(R.string.air_conditioning_provider) + " - " + resources.getString(R.string.air_conditioning_type));
                rowView.swOnOff.setText(resources.getString(R.string.off));
                rowView.imIcon.setImageResource(R.drawable.ic_air_conditioning_black_24dp);
                break;
            case WATER:
                rowView.tvTypeProvider.setText(resources.getString(R.string.water_provider) + " - " + resources.getString(R.string.water_type));
                rowView.swOnOff.setText(resources.getString(R.string.off));
                rowView.imIcon.setImageResource(R.drawable.ic_water_tap);
                break;
        }

        if (rowView.imMenu != null)
            rowView.imMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showPopupMenu(rowView.imMenu);
                }
            });

        if ( rowView.swOnOff != null)
            rowView.swOnOff.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Resources resources = rowView.cardView.getContext().getResources();
                    String toast_string = (String) rowView.tvTypeProvider.getText();
                    if (rowView.swOnOff.isChecked()) {
                        rowView.swOnOff.setText(resources.getString(R.string.on));
                        rowView.swOnOff.setTextColor(resources.getColor(R.color.colorAccent));
                        Toast.makeText(view.getContext(), toast_string + " is On", Toast.LENGTH_LONG).show();
                    } else {
                        rowView.swOnOff.setText(resources.getString(R.string.off));
                        rowView.swOnOff.setTextColor(resources.getColor(R.color.secondary_text));
                        Toast.makeText(view.getContext(), toast_string + " is Off", Toast.LENGTH_LONG).show();
                    }
                }

            });
            //rowView.btPrice.setText(context.getResources().getString(R.string.card_price_label) + itemPrice);

            // download thumbnail
            //Picasso.with(context)
            //        .load(i.getUrlPhoto())
            //        .into(rowView.imThumbnail);
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(view.getContext(), view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.card_remote_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener(view.getContext()));
        popup.show();
    }

    /**
     * Click listener for popup menu items
     */
    private class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        Context mContext;

        private MyMenuItemClickListener(Context ctx) {
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
    public int getItemCount() {
        Log.d(TAG, "number of Remote cards: " + String.valueOf((RemoteType.values()).length));
        return (RemoteType.values()).length;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        ImageView imIcon;
        TextView tvTypeProvider;
        Switch swOnOff;
        ImageView imMenu;

        private ViewHolder(View rowView) {
            super(rowView);
            // store UI elements
            cardView = (CardView) rowView.findViewById(R.id.cv_remote);
            imIcon = (ImageView) rowView.findViewById(R.id.im_device_icon);
            tvTypeProvider = (TextView) rowView.findViewById(R.id.tv_type_provider);
            swOnOff = (Switch) rowView.findViewById(R.id.sw_on_off);
            imMenu = (ImageView) rowView.findViewById(R.id.im_card_remote_menu);
        }
    }
}
