package es.rafaelsf80.domotik.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import es.rafaelsf80.domotik.R;

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
public class NetworkingDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_networking);

        setSupportActionBar((Toolbar) findViewById(R.id.tb_networking));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Set Collapsing Toolbar layout to the screen
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        ImageView imDevicePhoto = (ImageView) findViewById(R.id.im_details_networking_device_photo);
        TextView tvIpHwAddress = (TextView) findViewById(R.id.tv_ip_hw_address);
        TextView tvDeviceFeatures = (TextView) findViewById(R.id.tv_device_details);

        if (savedInstanceState == null) {
            // Get info from bundle
            Intent fromListItem = getIntent();
            final String name = fromListItem.getStringExtra("name");
            String flags = fromListItem.getStringExtra("flags");
            final String hwAddress = fromListItem.getStringExtra("hwAddress");
            final String ipAddress = fromListItem.getStringExtra("ipAddress");
            final String port = fromListItem.getStringExtra("port");

            String type = fromListItem.getStringExtra("type");
            final String model = fromListItem.getStringExtra("model");
            final String processor = fromListItem.getStringExtra("processor");
            final String ram = fromListItem.getStringExtra("ram");
            final String screen = fromListItem.getStringExtra("screen");
            final String hardDisk = fromListItem.getStringExtra("hardDisk");
            final String urlPhoto = fromListItem.getStringExtra("urlPhoto");

            tvIpHwAddress.setText("IP address: " + ipAddress + "\n" + "MAC address: " + hwAddress +
                                    "Flags: " + flags + "\n" + "Port: " + port);

            if (urlPhoto != null) {
                collapsingToolbar.setTitle( model );
                String composed = "Processor: " + processor + "\n" + "RAM: " + ram + "\n" +
                        "Screen size: " + screen + "\n" + "Hard disk: " + hardDisk;
                // download device image
                Picasso.with(getApplicationContext())
                        .load(urlPhoto)
                        .into(imDevicePhoto);

                tvDeviceFeatures.setText(composed);
            }
            else {
                collapsingToolbar.setTitle( "Unknown" );
            }
            // set UI information to the data which has been parsed through
            //setTitle(itemName + getResources().getString(R.string.details_title_by_) + brand);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }
}
