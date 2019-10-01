package com.yoti.api.client.docs;

public class SimpleRequestedTextExtractionTask implements RequestedTextExtractionTask {

    private final RequestedTextExtractionTaskConfig config;

    public SimpleRequestedTextExtractionTask(RequestedTextExtractionTaskConfig config) {
        this.config = config;
    }

    @Override
    public RequestedTextExtractionTaskConfig getConfig() {
        return config;
    }

}
