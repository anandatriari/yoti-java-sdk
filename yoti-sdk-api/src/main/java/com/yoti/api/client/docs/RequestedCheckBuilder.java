package com.yoti.api.client.docs;

// FIXME: Interface??
public abstract class RequestedCheckBuilder {

    public abstract RequestedDocumentAuthenticityCheckBuilder forAuthenticityCheck();

    public abstract RequestedLivenessCheckBuilder forLivenessCheck();

    public abstract RequestedFaceMatchCheckBuilder forFaceMatchCheck();

}
