package es.rafaelsf80.domotik.app;

import android.content.Context;
import android.util.Log;

import es.rafaelsf80.domotik.app.binder.CameraBinder;
import es.rafaelsf80.domotik.app.binder.NetworkingBinder;
import es.rafaelsf80.domotik.app.binder.RemoteBinder;
import es.rafaelsf80.domotik.app.binder.WeatherBinder;
import es.rafaelsf80.domotik.app.multipleviewtypesabstractadapter.EnumMapBindAdapter;

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

public class SampleEnumMapAdapter extends EnumMapBindAdapter<SampleEnumMapAdapter.SampleViewType> {

    private final String TAG = getClass().getSimpleName();


    enum SampleViewType {
        MACHINE, CAMERA, WEATHER, REMOTE
    }

    public SampleEnumMapAdapter() {

        //putBinder(SampleViewType.SAMPLE1, new Sample1Binder(this));
        putBinder(SampleViewType.MACHINE, new NetworkingBinder(this));
        putBinder(SampleViewType.CAMERA, new CameraBinder(this));
        putBinder(SampleViewType.WEATHER, new WeatherBinder(this));
        putBinder(SampleViewType.REMOTE, new RemoteBinder(this));
    }

    public void add(Context context, Machine machine) {
        ((NetworkingBinder) getDataBinder(SampleViewType.MACHINE)).add(context, machine);
    }


    //public void setSample2Data(List<SampleData> dataSet) {
    //    //((Sample2Binder) getDataBinder(SampleViewType.SAMPLE2)).addAll(dataSet);
    //}

    @Override
    public SampleViewType getEnumFromPosition(int position) {


        // todo: select ordering SI NO DA throwIndexOutOfBoundsException
        // now position:0 camera; position:1 wether; position > 2: device

        int total =
        ((NetworkingBinder) getDataBinder(SampleViewType.MACHINE)).getItemCount() +
                ((WeatherBinder) getDataBinder(SampleViewType.WEATHER)).getItemCount() +
                ((CameraBinder) getDataBinder(SampleViewType.CAMERA)).getItemCount() +
                ((RemoteBinder) getDataBinder(SampleViewType.REMOTE)).getItemCount();


        Log.d(TAG, "position****: " + Integer.toString(position) + "total" + Integer.toString(total));

        // MANUAL: position IS MANUAL FOR THE TIME BEING
        if (position == 0) return SampleViewType.MACHINE;
        if (position == 1) return SampleViewType.REMOTE;
        if (position == 2) return SampleViewType.REMOTE;
        if (position == 3) return SampleViewType.CAMERA;
        if (position == 4) return SampleViewType.MACHINE;
        if (position == 5) return SampleViewType.REMOTE;
        if (position == 6) return SampleViewType.REMOTE;
        if (position == 7) return SampleViewType.WEATHER;
        return SampleViewType.MACHINE;
    }

    @Override
    public SampleViewType getEnumFromOrdinal(int ordinal) {
        return SampleViewType.values()[ordinal];
    }
}
