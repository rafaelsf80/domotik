package es.rafaelsf80.domotik.app;

import android.content.Context;
import android.util.Base64;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.SecureRandom;

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

public class Attest {

    private Context mContext;

    public static final String TAG = Attest.class.getSimpleName();

    public Attest(Context mContext) {
        this.mContext = mContext;
    }

    void runCheck() {

        GoogleApiClient mGoogleApiClient = new GoogleApiClient.Builder(mContext)
                .addApi(SafetyNet.API)
                //.addConnectionCallbacks(this)
                //.addOnConnectionFailedListener(this)
                .build();
        mGoogleApiClient.connect();

        byte[] nonce = new byte[32];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(nonce);

        Log.d(TAG, "Running Attestation (SafetyNet)");
        SafetyNet.SafetyNetApi.attest(mGoogleApiClient, nonce)
                .setResultCallback(new ResultCallback<SafetyNetApi.AttestationResult>() {
                    @Override
                    public void onResult(SafetyNetApi.AttestationResult result) {
                        Status status = result.getStatus();
                        if (status.isSuccess()) {
                            // Indicates communication with the service was successful.
                            // result.getJwsResult() contains the result data
                            Log.d(TAG, "Attest successful: " + result.getJwsResult());
                            //the JWT (JSON WEB TOKEN) is just a 3 base64 encoded parts concatenated by a . character
                            final String[] jwtParts = result.getJwsResult().split("\\.");

                            if (jwtParts.length == 3) {
                                //we're only really interested in the body/payload
                                String decodedPayload = new String(Base64.decode(jwtParts[1], Base64.DEFAULT));

                                Log.d(TAG, "Attest decode payload: " + parseSafetyNetResponse(decodedPayload));
                            }
                        } else {
                            // An error occurred while communicating with the service
                            Log.d(TAG, "Attest ERROR: " + result.getJwsResult());

                        }
                    }
                });
    }

    public String parseSafetyNetResponse(String decodedJWTPayload) {

        String response = null;

        try {
            JSONObject root = new JSONObject(decodedJWTPayload);
            if(root.has("nonce")) {
                response = "nonce: " + root.getString("nonce") + "\n";
            }

            if(root.has("timestampMs")) {
                response += "timeStampMs: " + root.getLong("timestampMs") + "\n";;
            }

            if(root.has("apkPackageName")) {
                response += "apkPackageName: " + root.getString("apkPackageName") + "\n";;
            }

            if(root.has("apkCertificateDigestSha256")) {
                JSONArray jsonArray = root.getJSONArray("apkCertificateDigestSha256");
                if(jsonArray!=null){
                    String[] certDigests = new String[jsonArray.length()];
                    for (int i = 0; i < jsonArray.length(); i++) {
                        certDigests[i]=jsonArray.getString(i);
                    }
                    response += "certDigest: " + certDigests + "\n";
                }
            }

            if(root.has("apkDigestSha256")) {
                response += "apkDigestSha256: " + root.getString("apkDigestSha256") + "\n";;
            }

            if(root.has("ctsProfileMatch")) {
                response += "ctsProfileMatch: " + root.getBoolean("ctsProfileMatch") + "\n";;
            }
            return response;

        } catch (JSONException e) {
            Log.e(TAG, "problem parsing decodedJWTPayload:"+ e.getMessage(), e);
        }
        return null;
    }
}

