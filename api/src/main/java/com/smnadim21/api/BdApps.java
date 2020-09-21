package com.smnadim21.api;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.smnadim21.api.model.CheckStatusModel;
import com.smnadim21.api.network.ApiClient;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BdApps extends Constants {

    public static void registerAPP() {
        ApiClient
                .getStringInstance()
                .register(APP_ID, APP_PASSWORD)
                .enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        Log.e("registercheck", "reg" + response.toString());

                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        Log.e("registercheck", "failed" + t.getMessage());
                    }
                });
    }


    public static void showDialog(final Activity activity, final SubscriptionStatusListener statusListener) {
        GetAdvId.getAdId((isSuccess, data) -> {
            if (isSuccess) {
                final Dialog dialog = new Dialog(activity);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(false);
                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(false);
                dialog.setContentView(R.layout.sub);


                final EditText getCode = dialog.findViewById(R.id.otp_code);


                Button dialogButton = (Button) dialog.findViewById(R.id.button_s_daily);

                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Uri uri = Uri.parse("smsto:21213");
                        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                        intent.putExtra("sms_body", Constants.MSG_TEXT);
                        activity.startActivity(intent);
                        dialog.dismiss();

                    }
                });


                dialog.findViewById(R.id.submit_code)
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (getCode.getText().toString().isEmpty()) {
                                    Toaster.ShowLogToast("Write a valid code");
                                } else {

                                    sendOtpWithDeviceId(dialog, getCode.getText().toString(), data, statusListener);


                                }

                            }
                        });

                if(!flag)
                {
                    dialog.show();
                }
            } else {
                Toaster.ShowLogToast("" + data);
            }
        });

    }


    //shakiba
    private  static boolean flag = false;

    public static void checkSubscriptionStatus(final SubscriptionStatusListener statusListener) {


        GetAdvId.getAdId((isSuccess, data) -> {
            if (isSuccess) {
                ApiClient
                        .getStringInstance()
                        .checkSubStatus(APP_ID, data)
                        .enqueue(new Callback<CheckStatusModel>() {
                            @Override
                            public void onResponse(Call<CheckStatusModel> call, Response<CheckStatusModel> response) {
                                Log.e("responsenew", response.toString());

                                if (response.body() != null) {

                                    CheckStatusModel checkStatusModel = response.body();

                                    if (statusListener != null) {
                                        if (checkStatusModel.getStatus() == TypeCode.RESPONSE_OK) {
                                            if (checkStatusModel.getData() != null) {
                                                if (checkStatusModel.getData().getStatus().equals(TypeStatus.STATUS_REGISTED)) {
                                                    statusListener.onSuccess(true);
                                                    Toaster.ShowLogToast("Subscribed!");
                                                    flag=true;
                                                    return;
                                                }
                                            }

                                        }
                                    }
                                    if (statusListener != null) {
                                        statusListener.onSuccess(false);
                                    }
                                    Toaster.ShowLogToast("not subscribed!");
                                    flag = false;

                                }
                            }

                            @Override
                            public void onFailure(Call call, Throwable t) {
                                if (statusListener != null) {
                                    statusListener.onFailed("" + t.getMessage());

                                    Toaster.ShowLogToast("" + t.getMessage());
                                }
                                Log.e("responsenew", t.getMessage());

                            }
                        });
            } else {
                Toaster.ShowLogToast("" + data);
            }
        });

    }

    public static void sendOtpWithDeviceId(Dialog dialog, String code, String deviceId, final SubscriptionStatusListener statusListener) {
        ApiClient
                .getStringInstance()
                .sumitOtpwithDevice(code, deviceId)
                .enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        Log.e("response", "reg" + response.toString());
                        boolean isActive = Boolean.parseBoolean("" + response.body().get("isActive"));
                        if (isActive) {//valid otp
                            checkSubscriptionStatus(statusListener);
                            dialog.dismiss();
                        } else {
                            Toaster.ShowLogToast("Invalid OTP");
                           // dialog.dismiss();
                        }

                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        Toaster.ShowLogToast("Otp Failed:" + t.getMessage());
                       // dialog.dismiss();
                    }
                });
    }

}
