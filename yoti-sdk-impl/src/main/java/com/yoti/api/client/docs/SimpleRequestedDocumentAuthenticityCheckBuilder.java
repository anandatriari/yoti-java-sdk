package com.yoti.api.client.docs;

public class SimpleRequestedDocumentAuthenticityCheckBuilder extends RequestedDocumentAuthenticityCheckBuilder {

    @Override
    public RequestedDocumentAuthenticityCheck build() {
        return new SimpleRequestedDocumentAuthenticityCheck(new SimpleRequestedDocumentAuthenticityConfig());
    }

}
