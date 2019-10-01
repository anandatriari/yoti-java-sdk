package com.yoti.api.client.docs;

public class SimpleRequestedFaceMatchCheck implements RequestedFaceMatchCheck {

    private final RequestedFaceMatchConfig config;

    public SimpleRequestedFaceMatchCheck(RequestedFaceMatchConfig config) {
        this.config = config;
    }

    @Override
    public RequestedFaceMatchConfig getConfig() {
        return config;
    }

}
