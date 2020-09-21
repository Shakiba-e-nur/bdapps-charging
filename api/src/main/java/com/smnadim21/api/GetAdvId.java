package com.smnadim21.api;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;

import java.io.IOException;

class GetAdvId {
     static void getAdId(final AdidListener listener)
     {
         AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {
             @Override
             protected String doInBackground(Void... params) {
                 AdvertisingIdClient.Info idInfo = null;
                 try {
                     idInfo = AdvertisingIdClient.getAdvertisingIdInfo(GetContext.getContext());
                 } catch (GooglePlayServicesNotAvailableException e) {
                     e.printStackTrace();
                 } catch (GooglePlayServicesRepairableException e) {
                     e.printStackTrace();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
                 String advertId = null;
                 try{
                     advertId = idInfo.getId();
                 }catch (NullPointerException e){
                     e.printStackTrace();
                 }

                 return advertId;
             }

             @Override
             protected void onPostExecute(String advertId) {
                 Log.d("chkadlog","adid:"+advertId);
                 if(advertId!=null)
                 {
                     sendCallback(listener,true,advertId);
                 }
                 else {
                     sendCallback(listener,false,advertId);
                 }
             }

         };
         task.execute();
     }
     private static void sendCallback(AdidListener listener,boolean isSuccess,String data)
     {
         if(listener!=null)
         {
             listener.onSuccess(isSuccess,data);
         }
     }
}

