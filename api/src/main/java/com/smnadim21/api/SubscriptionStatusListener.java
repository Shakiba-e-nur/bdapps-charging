package com.smnadim21.api;

public interface SubscriptionStatusListener {
    void onSuccess(boolean isSubscribed);
    void onFailed(String message);
}
