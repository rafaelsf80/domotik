//package es.rafaelsf80.domotik.app;
//
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.DefaultItemAnimator;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.Menu;
//import android.view.MenuItem;
//
//import es.rafaelsf80.domotik.R;
//
///**
// * Copyright 2016 Rafael Sanchez Fuentes
// * <p/>
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// * <p/>
// * http://www.apache.org/licenses/LICENSE-2.0
// * <p/>
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// * <p/>
// * Author: Rafael Sanchez Fuentes rafaelsf80 at gmail dot com
// */
//public class ControlActivity extends AppCompatActivity {
//
//    public RecyclerView recyclerView;
//    public ControlAdapter controlAdapter;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.control);
//
//        if (savedInstanceState == null) {
//            // Create the detail fragment and add it to the activity
//            // using a fragment transaction.
//
//            Bundle arguments = new Bundle();
//
//        }
//
//        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        recyclerView.setHasFixedSize(true);
//        controlAdapter = new ControlAdapter(this, this);//, items, config.colorScheme);
//        recyclerView.setAdapter( controlAdapter );
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//
//
//        Comtrend comtrend = new Comtrend(this, controlAdapter);
//        comtrend.start();
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        return super.onOptionsItemSelected(item);
//    }
//}
