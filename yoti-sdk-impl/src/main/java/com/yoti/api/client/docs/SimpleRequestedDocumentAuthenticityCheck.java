package com.yoti.api.client.docs;

public class SimpleRequestedDocumentAuthenticityCheck implements RequestedDocumentAuthenticityCheck {

    private final RequestedDocumentAuthenticityConfig config;

    // FIXME: PUBLIC constructors on these builders?
    public SimpleRequestedDocumentAuthenticityCheck(RequestedDocumentAuthenticityConfig config) {
        this.config = config;
    }

    @Override
    public RequestedDocumentAuthenticityConfig getConfig() {
        return config;
    }

}
