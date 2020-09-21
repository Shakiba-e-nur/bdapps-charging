# bdapps-charging
![Jitpack - Version](https://img.shields.io/jitpack/v/github/smnadim21/bdapps?color=green)
![PyPI - Status](https://img.shields.io/pypi/status/django)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

android support library for BDapps.

### How to 



Step 1. Add the JitPack repository and google gsm service to your build file

Add it in your root build.gradle at the end of dependencies:

     buildscript  {
     dependencies {
      ..

        classpath 'com.google.gms:google-services:4.3.3'//add this classpath
          }

    }
Add it in your root build.gradle at the end of repositories:

     allprojects {
      repositories {
        ...
        maven { url 'https://jitpack.io' }
      }
    }
    
    
Step 2. Add compile in your module build.gradle at the end of android:

    android {
      ....
        compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
      }

    }

Add the dependency    

    dependencies {
                implementation 'com.github.Shakiba-e-nur.bdapps-charging:api:0.0.3'
      }
    
Step 3.Implement SubscriptionStatusListener.
 
        //In activity or fragment will have to implements SubscriptionStatusListener
        
         public class MainActivity extends AppCompatActivity implements SubscriptionStatusListener {
         
        //flag is to check Subscribtion status
        boolean flag=false;
    
        //Overriding methods of SubscriptionStatusListener
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
            }
Step 4. Register Your App with server.

      Write below lines inside onCreate()// of any activity to sync and register your app with BDApps server! 

        Constants.MSG_TEXT = "start whatever!";
        Constants.APP_ID = "APP_123456";
        Constants.APP_PASSWORD = "Write Your App Password!";
        
        
Step 5. USAGE TIME!

        BdApps.registerAPP();// use this method to register

        BdApps.checkSubscriptionStatus(this);  // use this method to check if user is subscribed or not!
     
        //lock any content anywhere!

         if(!flag)// this line checks if the content is locked or not
                // your content is locked here
        {
            BdApps.showDialog(this, MainActivity.this);// BdApps shows dialogue for charging!  [  pass Activity.this/ getActivity() / (Activity) context inside  as first parameter and Activity.this/Fragement.this as Second parameter in BdApps.showDialog() method! ]
           
           } else {
                //Your content is unlocked here!
            }

                
Step 5. ONE MORE THING!
      inside AndroidManifest.xml add below lines on desired section

          <?xml version="1.0" encoding="utf-8"?>
          <manifest ...
                    ...>
               <uses-permission android:name="android.permission.INTERNET" />

              <application
                    ...
                    android:usesCleartextTraffic="true">
                    <activity>
                    ....
                    </activity>
              </application>

          </manifest>
          
##Example:
          
        package com.shakiba.testproject;

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

            public void CheckPayment(View view) {
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
