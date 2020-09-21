
package com.smnadim21.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Data {

    @SerializedName("appId")
    @Expose
    private String appId;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("frequency")
    @Expose
    private String frequency;
    @SerializedName("version")
    @Expose
    private String version;
    @SerializedName("subscriberId")
    @Expose
    private String subscriberId;
    @SerializedName("otp")
    @Expose
    private String otp;
    @SerializedName("deviceId")
    @Expose
    private String deviceId;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String toString() {
        return "Data{" +
                "appId='" + appId + '\'' +
                ", status='" + status + '\'' +
                ", frequency='" + frequency + '\'' +
                ", version='" + version + '\'' +
                ", subscriberId='" + subscriberId + '\'' +
                ", otp='" + otp + '\'' +
                ", deviceId='" + deviceId + '\'' +
                '}';
    }
}
