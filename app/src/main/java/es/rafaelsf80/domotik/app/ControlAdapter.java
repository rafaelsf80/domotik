//package es.rafaelsf80.domotik.app;
//
//import android.app.Activity;
//import android.content.Context;
//import android.support.v7.widget.CardView;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.squareup.picasso.Picasso;
//
//import java.util.ArrayList;
//
//import es.rafaelsf80.domotik.R;
//
//import static android.os.Build.DEVICE;
//
///**
// * Copyright 2016 Rafael Sanchez Fuentes
// * <p>
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// * <p>
// * http://www.apache.org/licenses/LICENSE-2.0
// * <p>
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// * <p>
// * Author: Rafael Sanchez Fuentes rafaelsf80 at gmail dot com
// */
//public class ControlAdapter extends RecyclerView.Adapter<ControlAdapter.ViewHolder> {
//
//    private final String TAG = getClass().getSimpleName();
//
//    public static final int WEATHER = 0;
//    public static final int DEVICE = 1;
//    public static final int DETECTOR = 2;
//    public static final int CAMERAS = 3;
//
//    public Context context;
//    public Activity activity;
//    private ArrayList<Machine> machines = new ArrayList<>();
//
//    private String mColor;
//
//    public ControlAdapter(Context context, Activity activity) {//}, ArrayList<Machine> machines, String color) {
//        //super(context, R.layout.rowlayout, values);
//        this.context = context;
//        this.machines = machines;
//        this.activity = activity;
//        //this.mColor = color;
//    }
//
//    public void add(Machine machine) {
//        machines.add(machine);
//    }
//
//    @Override
//    public int getItemCount() {
//        if (machines != null)
//            return machines.size();
//        else
//            return 0;
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder rowView, int position) {
//
//        // get the item which has been pressed and store current item data into variables
//        if (machines != null) {
//            Machine i = machines.get(position);
////        final String itemName = i.itemName;
////        final String brand = i.brand;
////        final String size = i.size;
////        final String thumbnailURL = i.imageURL;
////        final String sizeGuide = i.sizeGuideURL;
////        final String videoPreview = i.videoPreviewURL;
////        final String itemPrice = i.itemPrice;
////        final String inventoryCount = i.inventoryCount;
////        final String itemLocation = i.itemLocation;
////        final String stockForecast = i.stockForecast;
////
////        if (stockForecast == "true") {
////            rowView.cardView.setBackgroundColor(Color.parseColor(mColor));
////        } else {
////            rowView.cardView.setBackgroundColor(context.getResources().getColor(R.color.card_nostock));
////        }
//
//
//            // set list menu content to variables
//            rowView.tvTitle.setText(i.getType());
//            rowView.btDetails.setText(i.getIpAddress());
//            rowView.btDemand.setText(i.getHwAddress());
//
//            rowView.tvDescription.setText(i.getName());
//            rowView.btInventory.setText(i.getFlags());
//            rowView.btSize.setText(i.getPort());
//
//            //rowView.btPrice.setText(context.getResources().getString(R.string.card_price_label) + itemPrice);
//
//            // download thumbnail
//            Picasso.with(context)
//                    .load(i.getUrlPhoto())
//                    .into(rowView.imThumbnail);
//        }
//        // clicking the Details button will open a Details page
//        rowView.btDetails.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
////                Log.d(TAG, "onClick btDetails");
////                Intent intent = new Intent(v.getContext(), ItemDetails.class);
////
////                // when a list item has been pressed move to the item details screen,  passing the following data
////                intent.putExtra("itemName", itemName);
////                intent.putExtra("mainImage", thumbnailURL);
////                intent.putExtra("brand", brand);
////                intent.putExtra("size", size);
////                intent.putExtra("sizeGuide", sizeGuide);
////                intent.putExtra("videoPreview", videoPreview);
////                intent.putExtra("itemPrice", itemPrice);
////                intent.putExtra("inventoryCount", inventoryCount);
////                intent.putExtra("itemLocation", itemLocation);
////                intent.putExtra("stockForecast", stockForecast);
//
//                // move to the details screen
//                //context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity).toBundle());
//            }
//        });
//
//        // clicking the demand button will automatically prefill a form and submit a response
// //       rowView.btDemand.setOnClickListener(new View.OnClickListener() {
//
// //           @Override
// //           public void onClick(View v) {
//
////                Log.d(TAG, "onClick btDemand");
////                Intent intent = new Intent(v.getContext(), ItemDetails.class);
////
////                // when a list item has been pressed move to the item details screen,  passing the following data
////                intent.putExtra("itemName", itemName);
////                intent.putExtra("mainImage", thumbnailURL);
////                intent.putExtra("brand", brand);
////                intent.putExtra("size", size);
////                intent.putExtra("sizeGuide", sizeGuide);
////                intent.putExtra("videoPreview", videoPreview);
////                intent.putExtra("itemPrice", itemPrice);
////                intent.putExtra("inventoryCount", inventoryCount);
////                intent.putExtra("stockForecast", stockForecast);
////
////                // How to Pre-populate form and automatically submit answers:
////                // Steps:
////                // 1) Settings->Get pre-filled link and submit one answer to obtain the pre-filled URL
////                // 2) Replace viewform? with formResponse?ifq&
////                // 3) Add &submit=Submit at the end
////                // https://docs.google.com/forms/d/17LzKVxCQ68EWiVVkhZ8_Sotn4cI46rk9TZvGG9FwkyY/viewform?entry.744206449=item_2&entry.1979146659=size_2&entry.875592217=brand_2
////
////                RequestQueue mQueue = Volley.newRequestQueue(context);
////
////                // remove spaces for URL
////                String regexItemName = itemName.replaceAll("\\s+","");
////                String regexSize = size.replaceAll("\\s+","");
////                String regexBrand = brand.replaceAll("\\s+","");
////
////                String FormUrl = "https://docs.google.com/forms/d/17LzKVxCQ68EWiVVkhZ8_Sotn4cI46rk9TZvGG9FwkyY/formResponse?ifq&entry.744206449=" + regexItemName + "&entry.1979146659=" + regexSize + "&entry.875592217=" + regexBrand + "&submit=Submit";
////
////                StringRequest mRequest = new StringRequest(Request.Method.GET, FormUrl, new Response.Listener<String>() {
////                    @Override
////                    public void onResponse(String s) {
////                        ///handle response from service
////                        Log.d(TAG, "Form Response: " + s);
////                        // create new toast to confirm user about demand generation
////                        Toast.makeText(context, context.getResources().getString(R.string.demand_generation) + itemName,
////                                Toast.LENGTH_LONG).show();
////
////                    }
////                }, new Response.ErrorListener() {
////
////                    @Override
////                    public void onErrorResponse(VolleyError volleyError) {
////                        Log.d(TAG, "Error sending Demand Info through Form: " + volleyError.toString());
////                    }
////                });
////
////                mQueue.add(mRequest);
////            }
////        });
//
//        // listener to detect whether a card has been pressed
//        rowView.tvTitle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//   //             Log.d(TAG, "onClick tvTitle");
// //               Intent intent = new Intent(v.getContext(), ItemDetails.class);
////
////                // when a list item has been pressed move to the item details screen,  passing the following data
////                intent.putExtra("itemName", itemName);
////                intent.putExtra("mainImage", thumbnailURL);
////                intent.putExtra("brand", brand);
////                intent.putExtra("size", size);
////                intent.putExtra("sizeGuide", sizeGuide);
////                intent.putExtra("videoPreview", videoPreview);
////                intent.putExtra("itemPrice", itemPrice);
////                intent.putExtra("inventoryCount", inventoryCount);
////                intent.putExtra("stockForecast", stockForecast);
//
//                // move to the details screen
//  //              context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity).toBundle());
//            }
//        });
//    }
//
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
//
//        View v;
//        if (viewType == WEATHER) {
//            v = LayoutInflater.from(viewGroup.getContext())
//                    .inflate(R.layout.card_weather, viewGroup, false);
//
//            return new WeatherViewHolder(v);
//        } else if (viewType == DEVICE) {
//            v = LayoutInflater.from(viewGroup.getContext())
//                    .inflate(R.layout.card_machine, viewGroup, false);
//            v.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Log.d(TAG, "onClick itemLayoutView");
//                }
//            });
//            return new DeviceViewHolder(v);
//
//        } else if (viewType == CAMERAS) {
//            v = LayoutInflater.from(viewGroup.getContext())
//                    .inflate(R.layout.card_camera, viewGroup, false);
//            return new CameraViewHolder(v);
//        }
//    }
//
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//
//        public ViewHolder(View v) {
//            super(v);
//        }
//    }
//
//    public  class DeviceViewHolder extends ViewHolder {
//        public CardView cardView;
//        public TextView tvTitle;
//        public TextView tvDescription;
//        public ImageView imThumbnail;
//        public Button btDetails;
//        public Button btDemand;
//        public Button btInventory;
//        public Button btSize;
//        public Button btPrice;
//
//        public DeviceViewHolder(View rowView) {
//            super(rowView);
//            // store UI elements in a variable to be dynamically changed
//            cardView = (CardView) rowView.findViewById(R.id.my_card_view);
//            tvTitle = (TextView) rowView.findViewById(R.id.tv_title);
//            tvDescription = (TextView) rowView.findViewById(R.id.tv_description);
//            imThumbnail = (ImageView) rowView.findViewById(R.id.im_thumbnail);
//
//            btDetails = (Button) rowView.findViewById(R.id.bt_card_details);
//            btDemand = (Button) rowView.findViewById(R.id.bt_card_demand);
//            btInventory = (Button) rowView.findViewById(R.id.bt_inventory);
//            btSize = (Button) rowView.findViewById(R.id.bt_size);
//            btPrice = (Button) rowView.findViewById(R.id.bt_price);
//        }
//    }
//
//    public  class WeatherViewHolder extends ViewHolder {
//
//        public WeatherViewHolder(View rowView) {
//            super(rowView);
//
//        }
//    }
//
//    public  class CameraViewHolder extends ViewHolder {
//
//        public CameraViewHolder(View rowView) {
//            super(rowView);
//
//        }
//    }
//}
//
