package com.shakiba.bdappscharging;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.smnadim21.api.BdApps;
import com.smnadim21.api.Constants;
import com.smnadim21.api.SubscriptionStatusListener;

public class MainActivity extends AppCompatActivity implements SubscriptionStatusListener {

    boolean flag=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Constants.MSG_TEXT = "start 123sa";
        Constants.APP_ID = "APP_016475";
        Constants.APP_PASSWORD = "f36f24ba800203e608718261e2d7d725";

        BdApps.registerAPP();// use this method to register

        BdApps.checkSubscriptionStatus(this);

    }

    @Override
    public void onSuccess(boolean isSubscribed) {
        if (!isSubscribed) {
            flag = false;
        }
        else {
            flag = true;
        }


    }

    @Override
    public void onFailed(String message) {

    }

    public void CheckPaymentApi(View view) {
        if(!flag)// this line checks if the content is locked or not
        // your content is locked here
        {
            BdApps.showDialog(this, MainActivity.this);// BdApps shows dialogue for charging!  [  pass Activity.this/ getActivity() / (Activity) context inside  as first parameter and Activity.this/Fragement.this as Second parameter in BdApps.showDialog() method! ]
        } else {
            //Your content is unlocked here!
            startActivity(new Intent(this,SecondActivity.class));
        }

    }
}