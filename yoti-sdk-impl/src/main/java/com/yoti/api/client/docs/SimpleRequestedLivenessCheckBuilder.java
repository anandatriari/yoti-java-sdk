package com.yoti.api.client.docs;

public class SimpleRequestedLivenessCheckBuilder extends RequestedLivenessCheckBuilder {

    private String livenessType;
    private int maxRetries;

    @Override
    public RequestedLivenessCheckBuilder forZoomLiveness() {
        this.livenessType = Constants.ZOOM;
        return this;
    }

    @Override
    public RequestedLivenessCheckBuilder forLivenessType(String livenessType) {
        this.livenessType = livenessType;
        return this;
    }

    @Override
    public RequestedLivenessCheckBuilder withMaxRetries(int maxRetries) {
        this.maxRetries = maxRetries;
        return this;
    }

    @Override
    public RequestedLivenessCheck build() {
        RequestedLivenessConfig config = new SimpleRequestedLivenessConfig(maxRetries, livenessType);
        return new SimpleRequestedLivenessCheck(config);
    }

}
