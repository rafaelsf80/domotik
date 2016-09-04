package es.rafaelsf80.domotik.app;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import es.rafaelsf80.domotik.R;

/**
 * Copyright 2016 Rafael Sanchez Fuentes
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * <p/>
 * Author: Rafael Sanchez Fuentes rafaelsf80 at gmail dot com
 */
public class MainFragment extends Fragment {

    //
    //  TODO: FOR TABLET LAYOUT !!!!
    //

    public static final String TAG = Main.class.getSimpleName();

    final static String urlGoogleChart
            = "http://chart.apis.google.com/chart";
    final static String urlp3Api
            = "?cht=p3&chs=400x150&chl=A|B|C&chd=t:";


    ImageView imControlar, imSalir;


    public MainFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Add this line in order for this fragment to handle menu events.
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.detail_camera, container, false);

        // Get a reference to the ListView, and attach this adapter to it.
        imControlar = (ImageView) rootView.findViewById(R.id.im_camera1);
        imSalir = (ImageView) rootView.findViewById(R.id.im_camera2);

        String A = "456";
        String B = "123";
        String C = "599";

        String urlRqs3DPie = urlGoogleChart
                + urlp3Api
                + A + "," + B + "," + C;

        // download thumbnail
        Picasso.with(getActivity())
                .load(urlRqs3DPie)
                .into(imControlar);


        // onClick Listeners
        imControlar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "estar clicked");
                //Intent intent = new Intent(getActivity(), ControlActivity.class);
                //startActivity(intent);

            }
        });

        imSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "detail_weather clicked");
                Intent intent = new Intent(getActivity(), WeatherDetailsActivity.class);
                startActivity(intent);

            }
        });

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
