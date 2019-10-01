package com.yoti.api.client.docs;

public class SimpleRequestedCheckBuilder extends RequestedCheckBuilder {

    @Override
    public RequestedDocumentAuthenticityCheckBuilder forAuthenticityCheck() {
        return new SimpleRequestedDocumentAuthenticityCheckBuilder();
    }

    @Override
    public RequestedLivenessCheckBuilder forLivenessCheck() {
        return new SimpleRequestedLivenessCheckBuilder();
    }

    @Override
    public RequestedFaceMatchCheckBuilder forFaceMatchCheck() {
        return new SimpleRequestedFaceMatchCheckBuilder();
    }

}
