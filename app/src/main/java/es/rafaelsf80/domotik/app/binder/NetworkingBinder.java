package es.rafaelsf80.domotik.app.binder;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.media.RingtoneManager;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
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
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import es.rafaelsf80.domotik.R;
import es.rafaelsf80.domotik.app.Database;
import es.rafaelsf80.domotik.app.Machine;
import es.rafaelsf80.domotik.app.Main;
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
    private ArrayList<Machine> mMachines = new ArrayList<>();
    private ChildEventListener mListener;
    private Context mContext;

    private static final int NOTIFICATION_ID = 3004;

    public NetworkingBinder(DataBindAdapter dataBindAdapter) {

        super(dataBindAdapter);

        Firebase fRef = new Firebase("https://domoclick.firebaseio.com/machines");

        // Look for all child events. We will then map them to our own internal ArrayList mMachines, which backs ListView
        mListener = fRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChildName) {

                Machine machine = snapshot.getValue(Machine.class);
                notifyBinderDataSetChanged();
                mMachines.add(machine);
                Log.d(TAG, "Firebase onChildAdded():" + machine.getIpAddress());

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                // One of the mModels changed. Replace it in our list and name mapping
                String key = dataSnapshot.getKey();
//                T newModel = dataSnapshot.getValue(FirebaseListAdapter.this.mModelClass);
//                int index = mKeys.indexOf(key);
//
//                mModels.set(index, newModel);

                notifyBinderDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

                // A model was removed from the list. Remove it from our list and the name mapping
                String key = dataSnapshot.getKey();
//                int index = mKeys.indexOf(key);
//
//                mKeys.remove(index);
//                mModels.remove(index);

                notifyBinderDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {

                // A model changed position in the list. Update our list accordingly
                String key = dataSnapshot.getKey();
//                T newModel = dataSnapshot.getValue(FirebaseListAdapter.this.mModelClass);
//                int index = mKeys.indexOf(key);
//                mModels.remove(index);
//                mKeys.remove(index);
//                if (previousChildName == null) {
//                    mModels.add(0, newModel);
//                    mKeys.add(0, key);
//                } else {
//                    int previousIndex = mKeys.indexOf(previousChildName);
//                    int nextIndex = previousIndex + 1;
//                    if (nextIndex == mModels.size()) {
//                        mModels.add(newModel);
//                        mKeys.add(key);
//                    } else {
//                        mModels.add(nextIndex, newModel);
//                        mKeys.add(nextIndex, key);
//                    }
//                }
                notifyBinderDataSetChanged();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.e(TAG, "Listen was cancelled, no more updates will occur");
            }

        });
    }

    @Override
    public ViewHolder newViewHolder(ViewGroup parent) {
        mContext = parent.getContext();
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
        if ((mMachines != null) && (mMachines.size()>0)) {
            Machine i = mMachines.get(position);

            // TODO: CREATE CLOUD SQL FOR DEVICE DETAILS
            Database deviceDetails = new Database(i.getHwAddress());
            // Details coming from ARP response
            final String name = i.getName();
            final String flags = i.getFlags();
            final String hwAddress = i.getHwAddress();
            final String ipAddress = i.getIpAddress();
            final String port = i.getPort();
            // Details from database
            final String model = deviceDetails.getModel();
            final String processor = deviceDetails.getProcessor();
            final String ram = deviceDetails.getRam();
            final String screen = deviceDetails.getScreen();
            final String type = deviceDetails.getType();
            final String hardDisk = deviceDetails.getHardDisk();
            final String urlPhoto = deviceDetails.getUrlPhoto();

            // Show UI card
            rowView.tvDeviceName.setText( model );
            rowView.tvIpAddress.setText(i.getIpAddress());
            // Download thumbnail
            Picasso.with(rowView.itemView.getContext())
                    .load(urlPhoto)
                    .into(rowView.imDevicePhoto);

            rowView.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "onClick Networking card number: " + String.valueOf(item_number));
                    Intent intent = new Intent(v.getContext(), NetworkingDetailsActivity.class);

                    // when a list item has been pressed move to the iNetworkingDetailsActivity class, passing the following data
                    intent.putExtra("name", name);
                    intent.putExtra("flags", flags);
                    intent.putExtra("hwAddress", hwAddress);
                    intent.putExtra("ipAddress", ipAddress);
                    intent.putExtra("port", port);
                    intent.putExtra("type", type);
                    intent.putExtra("model", model);
                    intent.putExtra("processor", processor);
                    intent.putExtra("ram", ram);
                    intent.putExtra("screen", screen);
                    intent.putExtra("hardDisk", hardDisk);
                    intent.putExtra("urlPhoto", urlPhoto);

                    // move to the details screen
                    //v.getContext().startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(((Activity) v.getContext())).toBundle());
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
    public int getItemCount() { return mMachines.size(); }

    public void add(Context ctx, final Machine machine) {

        final Firebase machinesRef = Main.myFirebaseRef.child("machines");
        final Context context = ctx;
        machinesRef.child(machine.getHwAddress()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {
                    // check if Hw address exists
                } else {
                    // if Hw address does not exist, add machine
                    Log.d(TAG, "Firebase New machine added: " + machine.getHwAddress());
                    machinesRef.child(machine.getHwAddress()).setValue(machine);
                    showNotification(context, "New machine: " + machine.getHwAddress());
                }
            }
            @Override
            public void onCancelled(FirebaseError arg0) {
                Log.d(TAG, "error adding new machine");
            }
        });
    }

    public void showNotification(Context context, String notificationMessage) {

        //checking the last update and notify if it' the first of the day
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String displayNotificationsKey = context.getString(R.string.pref_enable_notifications_key);
        boolean displayNotifications = prefs.getBoolean(displayNotificationsKey,
                Boolean.parseBoolean(context.getString(R.string.pref_enable_notifications_default)));

        displayNotifications = true;

        if ( displayNotifications ) {

            String lastNotificationKey = context.getString(R.string.pref_last_notification);
            long lastSync = prefs.getLong(lastNotificationKey, 0);

            if (System.currentTimeMillis() - lastSync >= 0) { //DAY_IN_MILLIS) {

                Resources resources = context.getResources();
                String title = context.getString(R.string.app_name);
                Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

                // Define the text of the notification
                //    String contentText = String.format(context.getString(R.string.format_notification),
                //            String.valueOf(mAdapter.getItemCount()),
                //            desc,
                //            Utility.formatTemperature(context, high),
                //            Utility.formatTemperature(context, low));

                //ImageView imNotificationPhoto = (ImageView) resources.findViewById(R.id.im_notification_end);

                RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.notification);
                remoteViews.setImageViewResource(R.id.im_notification, R.drawable.ic_home_icon);
                remoteViews.setTextViewText(R.id.tv_notification_title, title);
                remoteViews.setTextViewText(R.id.tv_notification_text_message, notificationMessage);
                remoteViews.setImageViewResource(R.id.im_notification_end, R.drawable.ic_home_icon);

                //Picasso.with(context)
                //        .load(urlPhoto)
                //        .into(imNotificationPhoto);

                // NotificationCompatBuilder is a very convenient way to build backward-compatible
                // notifications.  Just throw in some data.
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_home_black_24dp)
                        .setColor(resources.getColor(R.color.colorAccent))
                        .setSound(defaultSoundUri)
                        .setAutoCancel(true)
                        .setCustomContentView(remoteViews)
                        //.setStyle(new Notification.DecoratedCustomViewStyle())
                        .setPriority(Notification.PRIORITY_HIGH)
                        .setVibrate(new long[0]);

                // Make something interesting happen when the user clicks on the notification.
                // In this case, opening the app is sufficient.
                Intent resultIntent = new Intent(context, Main.class);

                // The stack builder object will contain an artificial back stack for the
                // started Activity.
                // This ensures that navigating backward from the Activity leads out of
                // your application to the Home screen.
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
                stackBuilder.addNextIntent(resultIntent);
                PendingIntent resultPendingIntent =
                        stackBuilder.getPendingIntent(
                                0,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );
                mBuilder.setContentIntent(resultPendingIntent);

                NotificationManager mNotificationManager =
                        (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);

                // NOTIFICATION_ID allows you to update the notification later on.
                Log.d(TAG, "Showing NOTIFICATION_ID" + String.valueOf(NOTIFICATION_ID));
                mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());

                //refreshing last sync
                SharedPreferences.Editor editor = prefs.edit();
                editor.putLong(lastNotificationKey, System.currentTimeMillis());
                editor.commit();
            }
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private TextView tvDeviceName;
        private TextView tvIpAddress;
        private ImageView imDevicePhoto;
        private ImageView imMenu;

        private ViewHolder(View rowView) {
            super(rowView);
            // store UI elements
            cardView = (CardView) rowView.findViewById(R.id.cv_networking);
            tvDeviceName = (TextView) rowView.findViewById(R.id.tv_device_name);
            tvIpAddress = (TextView) rowView.findViewById(R.id.tv_ip_address);
            imDevicePhoto = (ImageView) rowView.findViewById(R.id.im_device);
            imMenu = (ImageView) rowView.findViewById(R.id.im_card_networking_menu);
        }
    }
}
