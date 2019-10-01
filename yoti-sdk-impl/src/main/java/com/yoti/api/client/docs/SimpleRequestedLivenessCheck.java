package com.yoti.api.client.docs;

public class SimpleRequestedLivenessCheck implements RequestedLivenessCheck {

    private final RequestedLivenessConfig config;

    SimpleRequestedLivenessCheck(RequestedLivenessConfig config) {
        this.config = config;
    }

    @Override
    public RequestedLivenessConfig getConfig() {
        return config;
    }

}
