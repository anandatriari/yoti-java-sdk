package com.yoti.api.client.docs;

public interface RequestedLivenessConfig extends RequestedCheckConfig {

    int getMaxRetries();

    String getLivenessType();

}
