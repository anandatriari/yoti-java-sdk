package com.yoti.api.client.docs;

public interface RequestedDocumentAuthenticityCheck extends RequestedCheck<RequestedDocumentAuthenticityConfig> {

    @Override
    RequestedDocumentAuthenticityConfig getConfig();

}
