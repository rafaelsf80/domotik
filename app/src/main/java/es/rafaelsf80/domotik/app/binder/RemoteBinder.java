package es.rafaelsf80.domotik.app.binder;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.ToggleButton;

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
        Bitmap icon;
        Resources resources = rowView.cardView.getContext().getResources();

        switch (pos) {
            case NATURAL_GAS:
                icon = BitmapFactory.decodeResource(resources,
                        R.drawable.heat);
                rowView.tvProvider.setText(resources.getString(R.string.gas_provider));
                rowView.tvType.setText(resources.getString(R.string.gas_type));
                rowView.tbOnOff.setText(resources.getString(R.string.on));
                rowView.imIcon.setImageBitmap(icon);
                break;
            case ELECTRICITY:
                icon = BitmapFactory.decodeResource(resources,
                        R.drawable.estar);
                rowView.tvProvider.setText(resources.getString(R.string.electricity_provider));
                rowView.tvType.setText(resources.getString(R.string.electrivity_type));
                rowView.tbOnOff.setText(resources.getString(R.string.on));
                rowView.imIcon.setImageBitmap(icon);
                break;
            case AIR_CONDITIONING:
                icon = BitmapFactory.decodeResource(resources,
                        R.drawable.heat);
                rowView.tvProvider.setText(resources.getString(R.string.air_conditioning_provider));
                rowView.tvType.setText(resources.getString(R.string.air_conditioning_type));
                rowView.tbOnOff.setText(resources.getString(R.string.on));
                rowView.imIcon.setImageBitmap(icon);
                break;
            case WATER:
                icon = BitmapFactory.decodeResource(resources,
                        R.drawable.water);
                rowView.tvProvider.setText(resources.getString(R.string.water_provider));
                rowView.tvType.setText(resources.getString(R.string.water_type));
                rowView.tbOnOff.setText(resources.getString(R.string.on));
                rowView.imIcon.setImageBitmap(icon);
                break;
        }

        rowView.imMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(rowView.imMenu);
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
    public int getItemCount() {
        Log.d(TAG, "number of Remote cards: " + String.valueOf((RemoteType.values()).length));
        return (RemoteType.values()).length;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        ImageView imIcon;
        TextView tvProvider;
        ToggleButton tbOnOff;
        TextView tvType;
        ImageView imMenu;

        public ViewHolder(View rowView) {
            super(rowView);
            // store UI elements in a variable to be dynamically changed
            cardView = (CardView) rowView.findViewById(R.id.cv_remote);
            imIcon = (ImageView) rowView.findViewById(R.id.im_device_icon);
            tvProvider = (TextView) rowView.findViewById(R.id.tv_provider);
            tbOnOff = (ToggleButton) rowView.findViewById(R.id.tb_on_off);
            tvType = (TextView) rowView.findViewById(R.id.tv_type);
            imMenu = (ImageView) rowView.findViewById(R.id.im_card_remote_menu);
        }
    }
}
