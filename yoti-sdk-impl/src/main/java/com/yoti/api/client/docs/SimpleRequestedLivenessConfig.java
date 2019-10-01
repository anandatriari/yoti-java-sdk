package com.yoti.api.client.docs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SimpleRequestedLivenessConfig implements RequestedLivenessConfig {

    @JsonProperty("max_retries")
    private final int maxRetries;

    @JsonProperty("liveness_type")
    private final String livenessType;

    SimpleRequestedLivenessConfig(int maxRetries, String livenessType) {
        this.maxRetries = maxRetries;
        this.livenessType = livenessType;
    }

    @Override
    public int getMaxRetries() {
        return maxRetries;
    }

    @Override
    public String getLivenessType() {
        return livenessType;
    }

}
